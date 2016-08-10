package mastermind.integration.rest;

import mastermind.core.domain.Example;
import mastermind.core.usecase.GetAllExamplesUseCase;
import mastermind.entrypoints.GetAllExamplesEndpoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class GetAllExamplesIntegrationTest {

    GetAllExamplesUseCase getAllExamplesUseCase = mock(GetAllExamplesUseCase.class);

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new GetAllExamplesEndpoint(getAllExamplesUseCase)).build();
    }

    @Test
    public void shouldRetrieveAllExamples() throws Exception {
        givenThereAreExamples(
                example("Hello world"),
                example("Hello world again")
        );

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"message\":\"Hello world\"},{\"message\":\"Hello world again\"}]"));
    }

    @Test
    public void shouldReturnAnEmptyListWhenThereAreNoExamples() throws Exception {
        givenThereAreNoExamples();

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    private void givenThereAreNoExamples() {
        when(getAllExamplesUseCase.getAllExamples()).thenReturn(new ArrayList<>());
    }

    private void givenThereAreExamples(Example... examples) {
        given(getAllExamplesUseCase.getAllExamples()).willReturn(Arrays.asList(examples));
    }

    private Example example(String message) {
        return new Example(message);
    }
}
