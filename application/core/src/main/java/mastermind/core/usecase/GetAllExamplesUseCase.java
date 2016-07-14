package mastermind.core.usecase;

import mastermind.core.domain.Example;

import java.util.List;

public class GetAllExamplesUseCase {

    private final GetAllExamples getAllExamples;

    public GetAllExamplesUseCase(GetAllExamples getAllExamples) {
        this.getAllExamples = getAllExamples;
    }

    public List<Example> getAllExamples() {
        return getAllExamples.getAllExamples();
    }
}
