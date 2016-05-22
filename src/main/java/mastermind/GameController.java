package mastermind;

import mastermind.core.Game;
import mastermind.core.Player;
import mastermind.resource.GameResource;
import mastermind.resource.GuessResource;
import mastermind.resource.PlayerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/new_game", method = RequestMethod.POST)
    public ResponseEntity<GameResource> newGame(@RequestBody PlayerResource playerResource) {

        Player player = playerResource.toDomain();
        Game game = gameService.start(player);
        GameResource gameResource = GameResource.fromDomain(game);

        return new ResponseEntity<GameResource>(gameResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/guess", method = RequestMethod.POST)
    public ResponseEntity<GameResource> guess(@RequestBody GuessResource guessResource) {

        Game game = gameService.guess(guessResource.getGameId(), guessResource.getPassword());
        GameResource gameResource = GameResource.fromDomain(game);

        return new ResponseEntity<GameResource>(gameResource, HttpStatus.OK);
    }
}
