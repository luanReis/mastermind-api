package mastermind.resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mastermind.core.CodePeg;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GuessResource {

    private String gameId;
    private List<CodePeg> password;
}
