package mastermind.configuration;

import mastermind.core.usecase.GetAllExamplesUseCase;
import mastermind.entrypoints.GetAllExamplesEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    public GetAllExamplesEndpoint getAllExamplesEndpoint(GetAllExamplesUseCase getAllExamplesUseCase) {
        return new GetAllExamplesEndpoint(getAllExamplesUseCase);
    }
}
