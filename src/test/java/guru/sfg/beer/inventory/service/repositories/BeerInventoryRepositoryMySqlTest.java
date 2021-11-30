package guru.sfg.beer.inventory.service.repositories;

import guru.sfg.beer.inventory.service.bootstrap.BeerInventoryLoader;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


/**
 * @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 * to run tests against a real database you can use the @AutoConfigureTestDatabase
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = {"localmysql"})
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
class BeerInventoryRepositoryMySqlTest {

    /**
     * Data JPA tests may also inject a TestEntityManager bean, which provides an alternative to the standard JPA EntityManager
     * that is specifically designed for tests.  Provides a subset of EntityManager methods that are useful for tests as well
     * as helper methods for common testing tasks such as
     */
    @Autowired
    private TestEntityManager entityManager;

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