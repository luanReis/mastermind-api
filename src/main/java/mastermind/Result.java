package mastermind;

import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class Result {

    private final List<CodePeg> guessedPassword;
    private final int numberOfWhiteKeyPegs;
    private final int numberOfBlackKeyPegs;

    public Result(List<CodePeg> guessedPassword, int numberOfWhiteKeyPegs, int numberOfBlackKeyPegs) {
        this.guessedPassword = guessedPassword;
        this.numberOfWhiteKeyPegs = numberOfWhiteKeyPegs;
        this.numberOfBlackKeyPegs = numberOfBlackKeyPegs;
    }
}
