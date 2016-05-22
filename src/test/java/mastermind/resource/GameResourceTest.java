package mastermind.resource;

import mastermind.core.CodePeg;
import mastermind.core.Game;
import mastermind.core.Player;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static mastermind.core.CodePeg.CYAN;
import static mastermind.core.CodePeg.MAGENTA;
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
        expectedResource.setGameId(game.getGameId());
        expectedResource.setAvailableCodePegs(Game.AVAILABLE_CODE_PEGS);
        expectedResource.setCodeLength(Game.DEFAULT_PASSWORD_LENGTH);
        expectedResource.setSolved(false);
        expectedResource.setPlayerResource(PlayerResource.fromDomain(player));

        assertThat(gameResource, is(expectedResource));
    }
}
