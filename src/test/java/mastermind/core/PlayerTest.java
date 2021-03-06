package mastermind.core;

import mastermind.core.CodePeg;
import mastermind.core.Player;
import mastermind.core.Result;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Test
    public void shouldStorePastResults() {
        Player player = new Player("someName");

        List<CodePeg> guessedPassword = Arrays.asList(
                CodePeg.CYAN,
                CodePeg.CYAN);

        int numberOfWhitePegs = 0;
        int numberOfBlackPegs = 0;

        Result result = new Result(guessedPassword, numberOfWhitePegs, numberOfBlackPegs);

        player.saveResult(result);

        assertThat(player.getResults().size(), is(1));
    }
}
