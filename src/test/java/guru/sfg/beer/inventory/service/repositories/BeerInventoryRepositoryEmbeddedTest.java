package guru.sfg.beer.inventory.service.repositories;

import guru.sfg.beer.inventory.service.bootstrap.BeerInventoryLoader;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
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

@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
class BeerInventoryRepositoryEmbeddedTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BeerInventoryRepository repository;

    @BeforeEach
    void setUp() {
        BeerInventory b1 = BeerInventory.builder()
                .beerId(UUID.fromString("c05d52d0-b2fb-455d-96d7-e8a1f053720a"))
                .upc(BeerInventoryLoader.BEER_1_UPC)
                .quantityOnHand(12)
                .build();

        repository.save(b1);

        List<BeerInventory> beerInventoryList = repository.findAll();
        beerInventoryList.forEach(System.out::println);
        log.info("#####  setUp - beerInventoryList >> " + beerInventoryList.size());
    }

    @Test
    void findAll() {
        List<BeerInventory> beerInventoryList = repository.findAll();
        beerInventoryList.forEach(System.out::println);
        log.debug("beerInventoryList: " + beerInventoryList.size());
    }

    @Test
    void findAllByBeerId() {
        List<BeerInventory> beerInventoryList = repository.findAllByBeerId(UUID.fromString("c05d52d0-b2fb-455d-96d7-e8a1f053720a"));
        beerInventoryList.forEach(System.out::println);
        log.debug("beerInventoryList: " + beerInventoryList.size());
    }

    @Test
    void findAllByUpc() {
        List<BeerInventory> beerInventoryList = repository.findAllByUpc(BeerInventoryLoader.BEER_1_UPC);
        beerInventoryList.forEach(System.out::println);
        log.debug("beerInventoryList: " + beerInventoryList.size());
    }
}