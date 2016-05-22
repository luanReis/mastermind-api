package mastermind.resource;

import mastermind.CodePeg;
import mastermind.Game;
import mastermind.Player;
import mastermind.resource.GameResource;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static mastermind.CodePeg.CYAN;
import static mastermind.CodePeg.MAGENTA;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameResourceTest {

    @Test
    public void shouldCreateResourceFromDomainObject() {
        List<CodePeg> password = Arrays.asList(CYAN, MAGENTA);
        Player player = new Player("someName");

        Game game = new Game(password, player);

        GameResource gameResource = GameResource.fromDomain(game);

        GameResource expectedResource = new GameResource();
        expectedResource.setGameId(1);
        expectedResource.setAvailableCodePegs(Game.AVAILABLE_CODE_PEGS);
        expectedResource.setCodeLength(Game.DEFAULT_PASSWORD_LENGTH);
        expectedResource.setSolved(false);

        assertThat(gameResource, is(expectedResource));
    }
}
