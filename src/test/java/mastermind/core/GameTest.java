package mastermind.core;

import mastermind.core.CodePeg;
import mastermind.core.Game;
import mastermind.core.Player;
import mastermind.core.Result;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameTest {

    @Test
    public void shouldCountOneWhiteKeyPegWhenOneCodePegIsInWrongPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.RED,
                CodePeg.GREEN,
                CodePeg.BLUE);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.MAGENTA,
                CodePeg.MAGENTA,
                CodePeg.RED);

        Game game = new Game(password, new Player("someName"));
        Result result = game.checkPassword(guessedPassword);

        int numberOfWhiteKeyPegs = 1;
        int numberOfBlackKeyPegs = 0;
        Result expectedResult = new Result(guessedPassword, numberOfWhiteKeyPegs, numberOfBlackKeyPegs);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldCountTwoWhiteKeyPegsWhenTwoCodePegsAreInWrongPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.RED,
                CodePeg.BLUE);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.BLUE,
                CodePeg.RED);

        Game game = new Game(password, new Player("someName"));
        Result result = game.checkPassword(guessedPassword);

        int numberOfWhiteKeyPegs = 2;
        int numberOfBlackKeyPegs = 0;
        Result expectedResult = new Result(guessedPassword, numberOfWhiteKeyPegs, numberOfBlackKeyPegs);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldNotCountWhiteKeyPegsWhenCodePegsAreInCorrectPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.RED,
                CodePeg.PURPLE,
                CodePeg.BLUE);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.PURPLE,
                CodePeg.BLUE);

        Game game = new Game(password, new Player("someName"));
        Result result = game.checkPassword(guessedPassword);

        int numberOfWhiteKeyPegs = 0;
        int numberOfBlackKeyPegs = 2;
        Result expectedResult = new Result(guessedPassword, numberOfWhiteKeyPegs, numberOfBlackKeyPegs);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldNotCountWhiteKeyPegsMultipleTimesWhenOnlyOneCodePegExist() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.CYAN);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.GREEN);

        Game game = new Game(password, new Player("someName"));
        Result result = game.checkPassword(guessedPassword);

        int numberOfWhiteKeyPegs = 0;
        int numberOfBlackKeyPegs = 1;
        Result expectedResult = new Result(guessedPassword, numberOfWhiteKeyPegs, numberOfBlackKeyPegs);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldCountTwoWhiteKeyPegsWhenTwoDuplicatedCodePegsAreInWrongPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.PURPLE,
                CodePeg.PURPLE,
                CodePeg.CYAN,
                CodePeg.MAGENTA);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.BLUE,
                CodePeg.PURPLE,
                CodePeg.PURPLE);

        Game game = new Game(password, new Player("someName"));
        Result result = game.checkPassword(guessedPassword);

        int numberOfWhiteKeyPegs = 2;
        int numberOfBlackKeyPegs = 0;
        Result expectedResult = new Result(guessedPassword, numberOfWhiteKeyPegs, numberOfBlackKeyPegs);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldCountOneWhiteBlackKeyPegWhenOneCodePegIsInCorrectPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.BLUE);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.YELLOW);

        Game game = new Game(password, new Player("someName"));
        Result result = game.checkPassword(guessedPassword);

        int numberOfWhiteKeyPegs = 0;
        int numberOfBlackKeyPegs = 1;
        Result expectedResult = new Result(guessedPassword, numberOfWhiteKeyPegs, numberOfBlackKeyPegs);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldCountZeroBlackKeyPegWhenNoCodePegIsInCorrectPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.YELLOW,
                CodePeg.CYAN);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.MAGENTA,
                CodePeg.MAGENTA);

        Game game = new Game(password, new Player("someName"));
        Result result = game.checkPassword(guessedPassword);

        int numberOfWhiteKeyPegs = 0;
        int numberOfBlackKeyPegs = 0;
        Result expectedResult = new Result(guessedPassword, numberOfWhiteKeyPegs, numberOfBlackKeyPegs);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldCountTwoBlackKeyPegWhenTwoCodePegAreInCorrectPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.RED,
                CodePeg.RED);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.RED,
                CodePeg.RED);

        Game game = new Game(password, new Player("someName"));
        Result result = game.checkPassword(guessedPassword);

        int numberOfWhiteKeyPegs = 0;
        int numberOfBlackKeyPegs = 2;
        Result expectedResult = new Result(guessedPassword, numberOfWhiteKeyPegs, numberOfBlackKeyPegs);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldReturnResultForGuessedPassword() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.BLUE,
                CodePeg.CYAN,
                CodePeg.RED);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.RED,
                CodePeg.BLUE,
                CodePeg.RED);

        Game game = new Game(password, new Player("someName"));
        Result result = game.checkPassword(guessedPassword);

        int numberOfWhiteKeyPegs = 1;
        int numberOfBlackKeyPegs = 1;
        Result expectedResult = new Result(guessedPassword, numberOfWhiteKeyPegs, numberOfBlackKeyPegs);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldStoreResultForCurrentPlayer() {
        Player playerMock = mock(Player.class);

        List<CodePeg> password = Arrays.asList(
                CodePeg.YELLOW,
                CodePeg.BLUE);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.RED,
                CodePeg.CYAN);

        Game game = new Game(password, playerMock);
        Result result = game.checkPassword(guessedPassword);

        verify(playerMock, times(1)).saveResult(result);
    }

    @Test
    public void shouldSetSolvedValueToTrueWhenGuessedPasswordIsCorrect() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.ORANGE,
                CodePeg.ORANGE);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.ORANGE,
                CodePeg.ORANGE);

        Game game = new Game(password, new Player("someName"));
        game.checkPassword(guessedPassword);

        assertThat(game.isSolved(), is(true));
    }

    @Test
    public void shouldSetSolvedValueToFalseWhenNewGameIsCreated() {
        Game game = new Game(new ArrayList<>(), new Player("someName"));
        assertThat(game.isSolved(), is(false));
    }
}
