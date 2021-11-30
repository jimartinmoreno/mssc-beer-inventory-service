package guru.sfg.beer.inventory.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeerInventoryApplicationTests {

    /**
     *  @LocalServerPort Annotation at the field or method/constructor parameter level that injects the HTTP port that got
     *  allocated at runtime. Provides a convenient alternative for @Value("${local.server.port}").
     */
    @LocalServerPort
    int localServerPort;

    @Test
    public void contextLoads() {
    }

}
