package mastermind;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class GameService {

    private final Random random;

    public GameService() {
        this.random = new Random();
    }

    public GameService(Random random) {
        this.random = random;
    }

    public Game start(Player player) {
        return new Game(generatePassword(), player);
    }

    private List<CodePeg> generatePassword() {
        List<CodePeg> generatedPassword = new ArrayList<>();

        for (int index = 0; index < Game.DEFAULT_PASSWORD_LENGTH; index++) {
            int randomIndex = random.nextInt(Game.DEFAULT_PASSWORD_LENGTH);
            generatedPassword.add(Game.AVAILABLE_CODE_PEGS.get(randomIndex));
        }

        return generatedPassword;
    }
}
