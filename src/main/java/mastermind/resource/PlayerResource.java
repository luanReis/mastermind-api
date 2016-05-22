package mastermind.resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mastermind.Player;

@NoArgsConstructor
@Getter
@Setter
public class PlayerResource {

    private String name;

    public Player toDomain() {
        return new Player(name);
    }
}
