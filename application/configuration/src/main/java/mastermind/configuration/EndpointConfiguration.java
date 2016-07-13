package mastermind.configuration;

import mastermind.core.HelloWorldUseCase;
import mastermind.entrypoints.HelloWorldEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    public HelloWorldEndpoint helloWorldEndpoint(HelloWorldUseCase helloWorldUseCase) {
        return new HelloWorldEndpoint(helloWorldUseCase);
    }
}
