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
    public void shouldCountOneKeyPegWhenOneCodePegIsCorrect() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.RED,
                CodePeg.BLUE);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.MAGENTA,
                CodePeg.RED);

        Board board = new Board(password);

        assertThat(board.countKeyPegs(guessedPassword), is(1));
    }

    @Test
    public void shouldCountTwoKeyPegsWhenTwoCodePegsAreCorrect() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.RED,
                CodePeg.BLUE);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.BLUE,
                CodePeg.RED);

        Board board = new Board(password);

        assertThat(board.countKeyPegs(guessedPassword), is(2));
    }

    @Test
    public void shouldNotCountKeyPegsMultipleTimesWhenOnlyOneCodePegExist() {
        List<CodePeg> password = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.CYAN);

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.GREEN,
                CodePeg.GREEN);

        Board board = new Board(password);

        assertThat(board.countKeyPegs(guessedPassword), is(1));
    }

    @Test
    public void shouldCountTwoKeyPegsWhenTwoDuplicatedCodePegsAreCorrect() {
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

        assertThat(board.countKeyPegs(guessedPassword), is(2));
    }
}
