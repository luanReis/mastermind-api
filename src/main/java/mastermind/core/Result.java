package mastermind.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Result {

    private List<CodePeg> guessedPassword;
    private int numberOfWhiteKeyPegs;
    private int numberOfBlackKeyPegs;

    public Result(List<CodePeg> guessedPassword, int numberOfWhiteKeyPegs, int numberOfBlackKeyPegs) {
        this.guessedPassword = guessedPassword;
        this.numberOfWhiteKeyPegs = numberOfWhiteKeyPegs;
        this.numberOfBlackKeyPegs = numberOfBlackKeyPegs;
    }
}
