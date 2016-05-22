package mastermind;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<CodePeg> password;
    private final Player player;

    public Game(List<CodePeg> password, Player player) {
        this.password = password;
        this.player = player;
    }

    public boolean isPasswordCorrect(List<CodePeg> password) {
        return this.password.equals(password);
    }

    public Result checkPassword(List<CodePeg> guessedPassword) {
        List<CodePeg> correctPassword = new ArrayList<>();
        correctPassword.addAll(this.password);

        int numberOfBlackKeyPegs = getNumberOfBlackKeyPegs(guessedPassword, correctPassword);
        int numberOfWhiteKeyPegs = getNumberOfWhiteKeyPegs(guessedPassword, correctPassword);

        Result result = new Result(guessedPassword, numberOfWhiteKeyPegs, numberOfBlackKeyPegs);

        this.player.saveResult(result);

        return result;
    }

    private int getNumberOfWhiteKeyPegs(List<CodePeg> guessedPassword, List<CodePeg> correctPassword) {
        int numberOfWhiteKeyPegs = 0;

        for (CodePeg guessedCodePeg : guessedPassword) {
            if (correctPassword.remove(guessedCodePeg)) {
                numberOfWhiteKeyPegs++;
            }
        }
        return numberOfWhiteKeyPegs;
    }

    private int getNumberOfBlackKeyPegs(List<CodePeg> guessedPassword, List<CodePeg> correctPassword) {
        int numberOfBlackKeyPegs = 0;

        for (int index = 0; index < guessedPassword.size(); index++) {
            CodePeg guessedCodePeg = guessedPassword.get(index);
            CodePeg codePeg = password.get(index);

            if (guessedCodePeg.equals(codePeg)) {
                correctPassword.remove(codePeg);
                numberOfBlackKeyPegs++;
            }
        }
        return numberOfBlackKeyPegs;
    }
}
