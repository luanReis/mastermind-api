package mastermind.resource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mastermind.CodePeg;
import mastermind.Game;

import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class GameResource {

    private int gameId;
    private List<CodePeg> availableCodePegs;
    private int codeLength;
    private boolean solved;

    public static GameResource fromDomain(Game game) {
        GameResource resource = new GameResource();
        resource.setGameId(1);
        resource.setAvailableCodePegs(Game.AVAILABLE_CODE_PEGS);
        resource.setCodeLength(Game.DEFAULT_PASSWORD_LENGTH);
        resource.setSolved(game.isSolved());

        return resource;
    }
}
