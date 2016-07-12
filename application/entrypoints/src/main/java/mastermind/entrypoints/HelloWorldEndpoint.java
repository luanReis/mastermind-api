package mastermind.entrypoints;

import mastermind.core.HelloWorldUseCase;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

public class HelloWorldEndpoint {

    private HelloWorldUseCase helloWorldUseCase;

    public HelloWorldEndpoint(HelloWorldUseCase helloWorldUseCase) {
        this.helloWorldUseCase = helloWorldUseCase;
    }

    @RequestMapping(value = "/hello", method = GET)
    public ExampleDto hello() {
        String message = helloWorldUseCase.sayHello("clean architecture");
        return new ExampleDto(message);
    }

    class ExampleDto {
        private String message;

        ExampleDto(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
