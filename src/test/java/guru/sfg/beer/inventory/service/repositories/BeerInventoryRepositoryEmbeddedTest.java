package guru.sfg.beer.inventory.service.repositories;

import guru.sfg.beer.inventory.service.bootstrap.BeerInventoryLoader;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests que se lanzan sobre la BD H2 en memoria para poder realizar test sin llamar a la BD MySQL
 */
@DataJpaTest
//@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
class BeerInventoryRepositoryEmbeddedTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BeerInventoryRepository repository;

    @BeforeEach
    void setUp() {

        BeerInventory b1 = BeerInventory.builder()
                .beerId(UUID.fromString("8552039b-1979-4ddf-a368-65814dff99fd"))
                .upc(BeerInventoryLoader.BEER_1_UPC)
                .quantityOnHand(12)
                .build();

        repository.save(b1);

        BeerInventory b2 = BeerInventory.builder()
                .beerId(UUID.fromString("c05d52d0-b2fb-455d-96d7-e8a1f053720a"))
                .upc(BeerInventoryLoader.BEER_2_UPC)
                .quantityOnHand(12)
                .build();
        repository.save(b2);

        BeerInventory b3 = BeerInventory.builder()
                .beerId(UUID.fromString("45772dd4-3e82-4d49-9951-4b4d8d3a0a1b"))
                .upc(BeerInventoryLoader.BEER_3_UPC)
                .quantityOnHand(12)
                .build();
        repository.save(b3);

        List<BeerInventory> beerInventoryList = repository.findAll();
        beerInventoryList.forEach(System.out::println);
        log.info("#####  setUp - beerInventoryList >> " + beerInventoryList.size());
    }

    @Test
    void findAll() {
        List<BeerInventory> beerInventoryList = repository.findAll();
        beerInventoryList.forEach(System.out::println);
        log.debug("beerInventoryList: " + beerInventoryList.size());
        assertThat(3).isEqualTo(beerInventoryList.size());
    }

    @Test
    void findAllByBeerId() {
        List<BeerInventory> beerInventoryList = repository.findAllByBeerId(UUID.fromString("c05d52d0-b2fb-455d-96d7-e8a1f053720a"));
        beerInventoryList.forEach(System.out::println);
        log.debug("beerInventoryList: " + beerInventoryList.size());
        assertThat(1).isEqualTo(beerInventoryList.size());
    }

    @Test
    void findAllByUpc() {
        List<BeerInventory> beerInventoryList = repository.findAllByUpc(BeerInventoryLoader.BEER_1_UPC);
        beerInventoryList.forEach(System.out::println);
        log.debug("beerInventoryList: " + beerInventoryList.size());
        assertThat(1).isEqualTo(beerInventoryList.size());
    }
}