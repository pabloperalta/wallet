package ar.gob.afip.training.tdd.wallet.api;

import ar.gob.afip.training.tdd.wallet.core.model.Greeting;
import ar.gob.afip.training.tdd.wallet.persistence.GreetingRepository;
import com.google.inject.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.join;

@RestController("greeting")
@RequestMapping("/greeting")
public class GreetingController {
    @Autowired
    private GreetingRepository greetingRepository;

    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/saveGreeting")
    public String saveGreeting() {
        Greeting greeting = new Greeting();
        greeting.setText("Howdy");
        greetingRepository.save(greeting);
        return "Greeting saved";
    }

    @RequestMapping("/showAllGreetings")
    public String showAllGreetings() {
        Iterable<Greeting> greetings = greetingRepository.findAll();

        return join(Lists.newArrayList(greetings).stream().map(Greeting::getText).collect(Collectors.toList()), ", ");
    }
}
