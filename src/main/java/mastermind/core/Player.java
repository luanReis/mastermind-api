package mastermind.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
public class Player {

    private final String name;
    private final List<Result> results;

    public Player(String name) {
        this.name = name;
        this.results = new ArrayList<>();
    }

    public void saveResult(Result result) {
        this.results.add(result);
    }
}
