package mastermind.entrypoints;

import mastermind.core.domain.Example;
import mastermind.core.usecase.GetAllExamplesUseCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllExamplesEndpointTest {

    GetAllExamplesUseCase getAllExamplesUseCase = mock(GetAllExamplesUseCase.class);

    GetAllExamplesEndpoint getAllExamplesEndpoint = new GetAllExamplesEndpoint(getAllExamplesUseCase);

    @Test
    public void shouldRetrieveAllExamples() {
        givenThereAreExamples(
                example("Hello world"),
                example("Hello world again")
        );

        List<ExampleDto> allExamples = getAllExamplesEndpoint.getAllExamples();

        assertThat(allExamples).hasSize(2);
        thenExampleHasBeenReturned(allExamples, "Hello world");
        thenExampleHasBeenReturned(allExamples, "Hello world again");
    }

    private void thenExampleHasBeenReturned(List<ExampleDto> allExamples, String message) {
        boolean containsExample = allExamples.stream().anyMatch(example ->
                example.getMessage().equals(message)
        );
        assertThat(containsExample).isTrue();
    }

    private void givenThereAreExamples(Example... examples) {
        when(getAllExamplesUseCase.getAllExamples()).thenReturn(Arrays.asList(examples));
    }

    private Example example(String message) {
        return new Example(message);
    }
}