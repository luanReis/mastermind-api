package mastermind;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void shouldReturnTrueWhenThePasswordIsCorrect() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.RED,
                CodePeg.BLUE,
                CodePeg.GREEN,
                CodePeg.YELLOW,
                CodePeg.ORANGE,
                CodePeg.PURPLE,
                CodePeg.CYAN,
                CodePeg.MAGENTA);

        Board board = new Board(password);

        assertThat(board.isPasswordCorrect(password), is(true));
    }

    @Test
    public void shouldReturnFalseWhenThePasswordIsNotCorrect() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.CYAN,
                CodePeg.PURPLE,
                CodePeg.YELLOW);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.BLUE,
                CodePeg.PURPLE,
                CodePeg.YELLOW);

        Board board = new Board(password);

        assertThat(board.isPasswordCorrect(guessedPassword), is(false));
    }

    @Test
    public void shouldCountOneWhiteKeyPegWhenOneCodePegIsInWrongPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.RED,
                CodePeg.GREEN,
                CodePeg.BLUE,
                CodePeg.RED);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.MAGENTA,
                CodePeg.MAGENTA,
                CodePeg.RED,
                CodePeg.RED);

        Board board = new Board(password);

        assertThat(board.countWhiteKeyPegs(guessedPassword), is(1));
    }

    @Test
    public void shouldCountTwoWhiteKeyPegsWhenTwoCodePegsAreInWrongPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.RED,
                CodePeg.BLUE);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.BLUE,
                CodePeg.RED);

        Board board = new Board(password);

        assertThat(board.countWhiteKeyPegs(guessedPassword), is(2));
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

        Board board = new Board(password);

        assertThat(board.countWhiteKeyPegs(guessedPassword), is(0));
    }

    @Test
    public void shouldNotCountWhiteKeyPegsMultipleTimesWhenOnlyOneCodePegExist() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.CYAN);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.GREEN);

        Board board = new Board(password);

        assertThat(board.countWhiteKeyPegs(guessedPassword), is(1));
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

        Board board = new Board(password);

        assertThat(board.countWhiteKeyPegs(guessedPassword), is(2));
    }

    @Test
    public void shouldCountOneWhiteBlackKeyPegWhenOneCodePegIsInCorrectPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.BLUE);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.YELLOW);

        Board board = new Board(password);

        assertThat(board.countBlackKeyPegs(guessedPassword), is(1));
    }

    @Test
    public void shouldCountZeroBlackKeyPegWhenNoCodePegIsInCorrectPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.YELLOW,
                CodePeg.CYAN);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.MAGENTA,
                CodePeg.MAGENTA);

        Board board = new Board(password);

        assertThat(board.countBlackKeyPegs(guessedPassword), is(0));
    }

    @Test
    public void shouldCountTwoBlackKeyPegWhenTwoCodePegAreInCorrectPosition() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.RED,
                CodePeg.RED);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.RED,
                CodePeg.RED);

        Board board = new Board(password);

        assertThat(board.countBlackKeyPegs(guessedPassword), is(2));
    }
}
