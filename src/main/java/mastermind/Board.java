package mastermind;

import java.util.List;

public class Board {

    private final List<CodePeg> password;

    public Board(List<CodePeg> password) {
        this.password = password;
    }

    public boolean isPasswordCorrect(List<CodePeg> password) {
        return this.password.equals(password);
    }
}
