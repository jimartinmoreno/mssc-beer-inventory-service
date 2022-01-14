package guru.sfg.beer.inventory.service.repositories;

import guru.sfg.beer.inventory.service.bootstrap.BeerInventoryLoader;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;


/**
 * @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 * to run tests against a real database you can use the @AutoConfigureTestDatabase
 *
 * @ActiveProfiles is a class-level annotation that is used to declare which active bean definition profiles should be
 * used when loading an ApplicationContext for test classes.
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = {"localmysql"})
@DataJpaTest // Annotation for a JPA test that focuses only on JPA components.
//@Transactional(propagation = Propagation.REQUIRED) // Support a current transaction, create a new one if none exists.
@Slf4j
class BeerInventoryRepositoryMySqlTest {

    /**
     * Data JPA tests may also inject a TestEntityManager bean, which provides an alternative to the standard JPA EntityManager
     * that is specifically designed for tests.  Provides a subset of EntityManager methods that are useful for tests as well
     * as helper methods for common testing tasks such as
     */
    //    @Autowired
    //    private TestEntityManager entityManager;

    @Autowired
    private BeerInventoryRepository repository;

    @Test
    void findAll() {
        List<BeerInventory> beerInventoryList = repository.findAll();
        log.debug("beerInventoryList: " + beerInventoryList);
        beerInventoryList.forEach(System.out::println);
    }

    @Test
    void findAllByBeerId() {
        List<BeerInventory> beerInventoryList = repository.findAllByBeerId(UUID.fromString("c05d52d0-b2fb-455d-96d7-e8a1f053720a"));
        log.debug("findAllByBeerId - beerInventoryList: " + beerInventoryList);
        beerInventoryList.forEach(System.out::println);
    }

    @Test
    void findAllByUpc() {
        List<BeerInventory> beerInventoryList = repository.findAllByUpc(BeerInventoryLoader.BEER_1_UPC);
        log.debug("findAllByUpc - beerInventoryList: " + beerInventoryList);
        beerInventoryList.forEach(System.out::println);
    }
}