package mastermind;

import mastermind.core.CodePeg;
import mastermind.core.Game;
import mastermind.core.Player;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static mastermind.core.CodePeg.ORANGE;
import static mastermind.core.CodePeg.PURPLE;
import static mastermind.core.Game.DEFAULT_PASSWORD_LENGTH;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameServiceTest {

    @Test
    public void shouldStartNewGameForSinglePlayer() {
        GameService gameService = new GameService();
        Game game = gameService.start(new Player("someName"));

        assertThat(game, not(is(nullValue())));
        assertThat(game.getPassword(), not(is(nullValue())));
        assertThat(game.getPlayer(), not(is(nullValue())));

        assertThat(game.getPassword().size(), is(DEFAULT_PASSWORD_LENGTH));
    }

    @Test
    public void shouldRandomlyCreatePasswordForNewGame() {
        Random randomMock = mock(Random.class);

        GameService gameService = new GameService(randomMock);
        gameService.start(new Player("someName"));

        verify(randomMock, times(DEFAULT_PASSWORD_LENGTH)).nextInt(DEFAULT_PASSWORD_LENGTH);
    }

    @Test
    public void shouldMakeGuessForPreviouslyCreatedGame() {
        GameService gameService = new GameService();
        Game game = gameService.start(new Player("playerName"));

        List<CodePeg> guessedPassword = Arrays.asList(ORANGE, PURPLE);
        Game playedGame = gameService.guess(game.getGameId(), guessedPassword);

        assertThat(playedGame, is(game));
    }
}
