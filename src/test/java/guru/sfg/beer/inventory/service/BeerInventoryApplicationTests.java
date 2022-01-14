package guru.sfg.beer.inventory.service;

import guru.sfg.beer.inventory.service.web.controllers.BeerInventoryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * Carga el contexto completo y ejecuta el CommandLineRunner que carga el inventario sobre una BD en memoria H2
 */
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Permite lanzar los test sin tener conflicto con los puertos en uso
public class BeerInventoryApplicationTests {

    /**
     *  @LocalServerPort Annotation at the field or method/constructor parameter level that injects the HTTP port that got
     *  allocated at runtime. Provides a convenient alternative for @Value("${local.server.port}").
     */
    @LocalServerPort
    int localServerPort;

    @Autowired
    BeerInventoryController beerInventoryController;

    @Test
    public void contextLoads() {
        System.out.println("localServerPort = " + localServerPort);
        assertThat(beerInventoryController).isNotNull();
        assertThat(0).isNotEqualTo(localServerPort);
    }

}
