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
}
