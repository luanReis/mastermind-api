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

    public int countKeyPegs(List<CodePeg> guessedPassword) {
        List<CodePeg> correctPassword = new ArrayList<>();
        correctPassword.addAll(this.password);

        int numberOfKeyPegs = 0;

        for (CodePeg codePeg : guessedPassword) {
            if(correctPassword.remove(codePeg)) {
                numberOfKeyPegs++;
            }
        }

        return numberOfKeyPegs;
    }
}
