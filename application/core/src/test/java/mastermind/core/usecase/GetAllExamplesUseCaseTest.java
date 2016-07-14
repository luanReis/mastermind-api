package mastermind.core.usecase;

import mastermind.core.domain.Example;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllExamplesUseCaseTest {

    GetAllExamples getAllExamples = mock(GetAllExamples.class);

    GetAllExamplesUseCase getAllExamplesUseCase = new GetAllExamplesUseCase(getAllExamples);

    @Test
    public void shouldRetrieveAllExamplesFromDataProvider() {
        givenThereAreExamples(
                example("Hello world"),
                example("Hello world again")
        );

        List<Example> allExamples = getAllExamplesUseCase.getAllExamples();

        assertThat(allExamples).hasSize(2);
        thenExampleHasBeenReturned(allExamples, "Hello world");
        thenExampleHasBeenReturned(allExamples, "Hello world again");
    }

    private void thenExampleHasBeenReturned(List<Example> allExamples, String message) {
        boolean containsExample = allExamples.stream().anyMatch(example ->
                example.getMessage().equals(message)
        );
        assertThat(containsExample).isTrue();
    }

    private void givenThereAreExamples(Example... examples) {
        when(getAllExamples.getAllExamples()).thenReturn(Arrays.asList(examples));
    }

    private Example example(String message) {
        return new Example(message);
    }
}