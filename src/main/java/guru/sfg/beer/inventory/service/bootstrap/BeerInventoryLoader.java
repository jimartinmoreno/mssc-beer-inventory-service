package guru.sfg.beer.inventory.service.bootstrap;

import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Se ejecuta al inciarse el servicio
 * Solo se ejecuta si no hay beers en la BD creados con el script data.sql
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class BeerInventoryLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerInventoryRepository beerInventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("run beer Inventory count: " + beerInventoryRepository.count());
        if (beerInventoryRepository.count() == 0) { // Comprueba si existe inventario de cervezas
            loadBeerObjects();
        }
    }

    private void loadBeerObjects() {
        BeerInventory b1 = BeerInventory.builder()
                .beerId(UUID.fromString("8552039b-1979-4ddf-a368-65814dff99fd"))
                .upc(BEER_1_UPC)
                .quantityOnHand(12)
                .build();

        beerInventoryRepository.save(b1);

        BeerInventory b2 = BeerInventory.builder()
                .beerId(UUID.fromString("c05d52d0-b2fb-455d-96d7-e8a1f053720a"))
                .upc(BEER_2_UPC)
                .quantityOnHand(12)
                .build();
        beerInventoryRepository.save(b2);

        BeerInventory b3 = BeerInventory.builder()
                .beerId(UUID.fromString("45772dd4-3e82-4d49-9951-4b4d8d3a0a1b"))
                .upc(BEER_3_UPC)
                .quantityOnHand(12)
                .build();
        beerInventoryRepository.save(b3);

        List<BeerInventory> beerInventoryList = beerInventoryRepository.findAll();
        log.info("##### beerInventoryList >> " + beerInventoryList);
    }
}
