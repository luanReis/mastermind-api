package mastermind.resource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mastermind.core.Player;
import mastermind.core.Result;

import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class PlayerResource {

    private String name;
    private List<Result> results;

    public Player toDomain() {
        return new Player(name);
    }

    public static PlayerResource fromDomain(Player player) {
        PlayerResource playerResource = new PlayerResource();
        playerResource.setName(player.getName());
        playerResource.setResults(player.getResults());

        return playerResource;
    }
}
