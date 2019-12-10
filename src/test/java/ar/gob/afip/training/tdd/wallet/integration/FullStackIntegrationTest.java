package ar.gob.afip.training.tdd.wallet.integration;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullStackIntegrationTest {

    @LocalServerPort
    private int port;

    private URL base;
    private URL saveGreeting;
    private URL showAllGreetings;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/greeting/hello");
        this.saveGreeting = new URL("http://localhost:" + port + "/greeting/saveGreeting");
        this.showAllGreetings = new URL("http://localhost:" + port + "/greeting/showAllGreetings");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
    }

    @Test
    public void greetingsPersistenceTest() throws Exception {
        ResponseEntity<String> response1 = template.getForEntity(saveGreeting.toString(), String.class);
        assertThat(response1.getBody()).isEqualTo("Greeting saved");
        ResponseEntity<String> response2 = template.getForEntity(showAllGreetings.toString(), String.class);
        assertThat(response2.getBody()).isEqualTo("Howdy");
    }
}