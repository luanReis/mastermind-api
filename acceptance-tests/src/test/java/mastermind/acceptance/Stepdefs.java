package mastermind.acceptance;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import mastermind.core.domain.Example;
import mastermind.core.usecase.GetAllExamples;
import mastermind.core.usecase.GetAllExamplesUseCase;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Stepdefs {

    GetAllExamples getAllExamples = mock(GetAllExamples.class);

    GetAllExamplesUseCase getAllExamplesUseCase = new GetAllExamplesUseCase(getAllExamples);

    List<Example> allExamplesFound;

    @Given("^there are some examples$")
    public void there_are_some_examples(List<String> messages) throws Throwable {
        List<Example> examplesInTheSystem = new ArrayList<>();
        messages.stream().forEach(message -> examplesInTheSystem.add(example(message)));
        when(getAllExamples.getAllExamples()).thenReturn(examplesInTheSystem);
    }

    @When("^I get all examples$")
    public void i_get_all_examples() throws Throwable {
        allExamplesFound = getAllExamplesUseCase.getAllExamples();
    }

    @Then("^all examples are found$")
    public void all_examples_are_found(List<String> expectedMessages) throws Throwable {
        assertThat(allExamplesFound).hasSize(2);
        expectedMessages.stream().forEach(expectedMessage -> exampleHasBeenFound(example(expectedMessage)));
    }

    private void exampleHasBeenFound(Example expectedExample) {
        boolean containsExample = allExamplesFound.stream().anyMatch(example ->
                example.getMessage().equals(expectedExample.getMessage())
        );
        assertThat(containsExample).isTrue();
    }

    private Example example(String message) {
        return new Example(message);
    }
}
