package mastermind.resource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mastermind.core.CodePeg;
import mastermind.core.Game;
import mastermind.core.Player;

import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class GameResource {

    private String gameId;
    private List<CodePeg> availableCodePegs;
    private int codeLength;
    private boolean solved;
    private PlayerResource playerResource;

    public static GameResource fromDomain(Game game) {
        GameResource resource = new GameResource();
        resource.setGameId(game.getGameId());
        resource.setAvailableCodePegs(Game.AVAILABLE_CODE_PEGS);
        resource.setCodeLength(Game.DEFAULT_PASSWORD_LENGTH);
        resource.setSolved(game.isSolved());
        resource.setPlayerResource(PlayerResource.fromDomain(game.getPlayer()));

        return resource;
    }
}
