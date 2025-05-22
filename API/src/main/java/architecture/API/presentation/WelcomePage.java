package architecture.API.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomePage {

    @GetMapping("/")
    public String index() {
        return "Hello User";
    }
}