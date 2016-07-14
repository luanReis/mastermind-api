package mastermind.configuration;

import mastermind.core.usecase.GetAllExamples;
import mastermind.core.usecase.GetAllExamplesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public GetAllExamplesUseCase helloWorldUseCase(GetAllExamples getAllExamples) {
        return new GetAllExamplesUseCase(getAllExamples);
    }
}
