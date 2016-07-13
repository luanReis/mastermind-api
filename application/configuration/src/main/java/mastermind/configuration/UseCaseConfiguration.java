package mastermind.configuration;

import mastermind.core.HelloWorldUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public HelloWorldUseCase helloWorldUseCase() {
        return new HelloWorldUseCase();
    }
}
