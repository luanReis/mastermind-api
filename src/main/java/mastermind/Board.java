package mastermind;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<CodePeg> password;

    public Board(List<CodePeg> password) {
        this.password = password;
    }

    public boolean isPasswordCorrect(List<CodePeg> password) {
        return this.password.equals(password);
    }

    public int countWhiteKeyPegs(List<CodePeg> guessedPassword) {
        List<CodePeg> correctPassword = new ArrayList<>();
        correctPassword.addAll(this.password);

        int numberOfWhiteKeyPegs = 0;

        for (int index = 0; index < guessedPassword.size(); index++) {
            CodePeg guessedCodePeg = guessedPassword.get(index);
            CodePeg codePeg = password.get(index);

            if (guessedCodePeg.equals(codePeg)) {
                continue;
            } else if (correctPassword.remove(guessedCodePeg)) {
                numberOfWhiteKeyPegs++;
            }
        }
        return numberOfWhiteKeyPegs;
    }

    public int countBlackKeyPegs(List<CodePeg> guessedPassword) {
        int numberOfBlackKeyPegs = 0;

        for (int index = 0; index < guessedPassword.size(); index++) {
            CodePeg guessedCodePeg = guessedPassword.get(index);
            CodePeg codePeg = password.get(index);

            if (guessedCodePeg.equals(codePeg)) {
                numberOfBlackKeyPegs++;
            }
        }
        return numberOfBlackKeyPegs;
    }
}
