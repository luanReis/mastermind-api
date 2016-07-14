package mastermind.configuration;

import mastermind.core.usecase.GetAllExamplesUseCase;
import mastermind.entrypoints.GetAllExamplesEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    public GetAllExamplesEndpoint helloWorldEndpoint(GetAllExamplesUseCase getAllExamplesUseCase) {
        return new GetAllExamplesEndpoint(getAllExamplesUseCase);
    }
}
