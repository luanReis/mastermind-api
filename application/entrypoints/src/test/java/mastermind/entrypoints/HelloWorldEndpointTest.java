package mastermind.entrypoints;

import mastermind.core.HelloWorldUseCase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloWorldEndpointTest {

    HelloWorldUseCase helloWorldUseCase = mock(HelloWorldUseCase.class);

    HelloWorldEndpoint helloWorldEndpoint = new HelloWorldEndpoint(helloWorldUseCase);

    @Test
    public void shouldReturnHello() {
        when(helloWorldUseCase.sayHello(any(String.class))).thenReturn("Hello clean architecture");
        HelloWorldEndpoint.ExampleDto hello = helloWorldEndpoint.hello();
        assertThat(hello.getMessage()).isEqualTo("Hello clean architecture");
    }
}