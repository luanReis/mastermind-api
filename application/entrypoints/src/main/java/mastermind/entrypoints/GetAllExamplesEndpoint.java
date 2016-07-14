package mastermind.entrypoints;

import mastermind.core.domain.Example;
import mastermind.core.usecase.GetAllExamplesUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GetAllExamplesEndpoint {

    private GetAllExamplesUseCase getAllExamplesUseCase;

    public GetAllExamplesEndpoint(GetAllExamplesUseCase getAllExamplesUseCase) {
        this.getAllExamplesUseCase = getAllExamplesUseCase;
    }

    @RequestMapping(value = "/hello", method = GET)
    public List<ExampleDto> getAllExamples() {
        List<Example> allExamples = getAllExamplesUseCase.getAllExamples();
        return toDtos(allExamples);
    }

    private List<ExampleDto> toDtos(List<Example> allExamples) {
        return allExamples.stream()
                .map(example -> new ExampleDto(example.getMessage()))
                .collect(Collectors.toList());
    }
}
