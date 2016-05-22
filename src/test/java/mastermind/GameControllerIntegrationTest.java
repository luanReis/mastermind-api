package mastermind;

import com.fasterxml.jackson.databind.ObjectMapper;
import mastermind.resource.GameResource;
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
import java.util.Arrays;

import static mastermind.CodePeg.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class GameControllerIntegrationTest {

    @Value("${local.server.port}")
    private int port;

    private URL newGameUrl;
    private RestTemplate template;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        newGameUrl = new URL("http://localhost:" + port + "/new_game");
        template = new TestRestTemplate();
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldStartNewGame() throws Exception {
        PlayerResource playerResource = new PlayerResource();
        playerResource.setName("someName");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<String>(mapper.writeValueAsString(playerResource), headers);

        ResponseEntity<GameResource> response = template.postForEntity(
                newGameUrl.toString(),
                requestEntity,
                GameResource.class);

        GameResource expectedResource = new GameResource();
        expectedResource.setGameId(1);
        expectedResource.setAvailableCodePegs(Arrays.asList(RED, BLUE, GREEN, YELLOW, ORANGE, PURPLE, CYAN, MAGENTA));
        expectedResource.setCodeLength(8);
        expectedResource.setSolved(false);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(expectedResource));
    }
}
