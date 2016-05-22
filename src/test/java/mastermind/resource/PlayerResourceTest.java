package mastermind.resource;

import mastermind.core.CodePeg;
import mastermind.core.Player;
import mastermind.core.Result;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static mastermind.core.CodePeg.BLUE;
import static mastermind.core.CodePeg.CYAN;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PlayerResourceTest {

    @Test
    public void shouldParseToDomainObject() {
        PlayerResource resource = new PlayerResource();
        resource.setName("playerName");

        Player player = resource.toDomain();
        Player expectedPlayer = new Player("playerName");

        assertThat(player, is(expectedPlayer));
    }

    @Test
    public void shouldCreateResourceFromDomainObject() {
        Player player = new Player("playerName");

        List<CodePeg> guessedPassword = Arrays.asList(BLUE, CYAN);
        Result result = new Result(guessedPassword, 0, 0);

        player.saveResult(result);

        PlayerResource expectedResource = new PlayerResource();
        expectedResource.setName("playerName");
        expectedResource.setResults(Arrays.asList(result));

        assertThat(PlayerResource.fromDomain(player), is(expectedResource));
    }
}
