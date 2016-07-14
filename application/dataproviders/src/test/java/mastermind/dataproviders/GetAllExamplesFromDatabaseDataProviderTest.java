package mastermind.dataproviders;

import mastermind.core.domain.Example;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllExamplesFromDatabaseDataProviderTest {

    MongoTemplate mongoTemplate = mock(MongoTemplate.class);

    GetAllExamplesFromDatabaseDataProvider getAllExamplesFromDatabaseDataProvider =
            new GetAllExamplesFromDatabaseDataProvider(mongoTemplate);

    @Test
    public void shouldFindAllExamplesInDatabase() {
        givenThereAreExamples(
                example("Hello world"),
                example("Hello world again")
        );

        List<Example> allExamples = getAllExamplesFromDatabaseDataProvider.getAllExamples();

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
        when(mongoTemplate.findAll(Example.class, "examples")).thenReturn(Arrays.asList(examples));
    }

    private Example example(String message) {
        return new Example(message);
    }
}