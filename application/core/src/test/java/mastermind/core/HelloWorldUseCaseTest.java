package mastermind.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldUseCaseTest {

    @Test
    public void shouldRun() {
        HelloWorldUseCase useCase = new HelloWorldUseCase();
        assertThat(useCase.sayHello("world")).isEqualTo("Hello world");
    }
}