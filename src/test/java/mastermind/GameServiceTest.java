package mastermind;

import org.junit.Test;

import java.util.Random;

import static mastermind.Game.DEFAULT_PASSWORD_LENGTH;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameServiceTest {

    @Test
    public void shouldStartNewGameForSinglePlayer() {
        GameService gameService = new GameService(new Random());
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
}
