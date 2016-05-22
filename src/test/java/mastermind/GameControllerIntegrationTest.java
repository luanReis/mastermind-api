package mastermind;

import com.fasterxml.jackson.databind.ObjectMapper;
import mastermind.core.CodePeg;
import mastermind.core.Player;
import mastermind.core.Result;
import mastermind.resource.GameResource;
import mastermind.resource.GuessResource;
import mastermind.resource.PlayerResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static mastermind.core.CodePeg.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class GameControllerIntegrationTest {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate template;
    private ObjectMapper mapper;
    private HttpHeaders headers;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
        mapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void shouldStartNewGame() throws Exception {
        PlayerResource playerResource = new PlayerResource();
        playerResource.setName("someName");
        playerResource.setResults(new ArrayList<>());

        final HttpEntity<String> requestEntity = new HttpEntity<>(mapper.writeValueAsString(playerResource), headers);

        URL newGameUrl = new URL("http://localhost:" + port + "/new_game");

        ResponseEntity<GameResource> response = template.postForEntity(
                newGameUrl.toString(),
                requestEntity,
                GameResource.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getGameId(), not(is(nullValue())));
        assertThat(response.getBody().getCodeLength(), is(8));
        assertThat(response.getBody().isSolved(), is(false));
        assertThat(response.getBody().getPlayerResource(), is(playerResource));
        assertThat(response.getBody().getAvailableCodePegs(), is(Arrays.asList(
                RED, BLUE, GREEN, YELLOW, ORANGE, PURPLE, CYAN, MAGENTA)));
    }

    @Test
    public void shouldCheckPasswordGuessForGame() throws Exception {
        PlayerResource playerResource = new PlayerResource();
        playerResource.setName("playerName");

        final HttpEntity<String> newGameRequest = new HttpEntity<>(mapper.writeValueAsString(playerResource), headers);

        URL newGameUrl = new URL("http://localhost:" + port + "/new_game");

        ResponseEntity<GameResource> newGameResponse =
                template.postForEntity(newGameUrl.toString(), newGameRequest, GameResource.class);

        String gameId = newGameResponse.getBody().getGameId();

        List<CodePeg> guess = Arrays.asList(RED, RED, RED, RED, RED, RED, RED, RED);
        GuessResource guessResource = new GuessResource();
        guessResource.setGameId(gameId);
        guessResource.setPassword(guess);

        final HttpEntity<String> guessRequest = new HttpEntity<>(mapper.writeValueAsString(guessResource), headers);

        URL guessUrl = new URL("http://localhost:" + port + "/guess");

        ResponseEntity<GameResource> response =
                template.postForEntity(guessUrl.toString(), guessRequest, GameResource.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getGameId(), is(gameId));
        assertThat(response.getBody().getCodeLength(), is(8));
        assertThat(response.getBody().isSolved(), is(false));
        assertThat(response.getBody().getPlayerResource().getResults().size(), is(1));
        assertThat(response.getBody().getAvailableCodePegs(), is(Arrays.asList(
                RED, BLUE, GREEN, YELLOW, ORANGE, PURPLE, CYAN, MAGENTA)));
    }
}
