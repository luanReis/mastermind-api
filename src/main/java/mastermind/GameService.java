package mastermind;

import mastermind.core.CodePeg;
import mastermind.core.Game;
import mastermind.core.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class GameService {

    private final Random random;
    private final List<Game> games;

    public GameService() {
        this(new Random());
    }

    public GameService(Random random) {
        this.random = random;
        this.games = new ArrayList<>();
    }

    public Game start(Player player) {
        Game game = new Game(generatePassword(), player);
        games.add(game);
        return game;
    }

    private List<CodePeg> generatePassword() {
        List<CodePeg> generatedPassword = new ArrayList<>();

        for (int index = 0; index < Game.DEFAULT_PASSWORD_LENGTH; index++) {
            int randomIndex = random.nextInt(Game.DEFAULT_PASSWORD_LENGTH);
            generatedPassword.add(Game.AVAILABLE_CODE_PEGS.get(randomIndex));
        }

        return generatedPassword;
    }

    public Game guess(String gameId, List<CodePeg> guessedPassword) {
        Game game = findGame(gameId);
        game.checkPassword(guessedPassword);
        return game;
    }

    private Game findGame(String gameId) {
        for (Game game: games) {
            if (game.getGameId().equals(gameId)) {
                return game;
            }
        }

        return null;
    }
}
