package guru.sfg.beer.inventory.service.web.controllers;

import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.beer.inventory.service.web.mappers.BeerInventoryMapper;
import guru.sfg.brewery.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerInventoryController {

    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper beerInventoryMapper;

    /**
     * Devuelve el inventario de una cerveza
     * @param beerId
     * @return
     */
    @GetMapping("/api/v1/beer/{beerId}/inventory")
    @ResponseStatus(HttpStatus.OK)
    List<BeerInventoryDto> listBeersById(@PathVariable UUID beerId){
        log.debug("Finding Inventory for beerId: " + beerId);
        List<BeerInventory> beerInventoryList = beerInventoryRepository.findAllByBeerId(beerId);
        log.debug("Finding Inventory for beerInventoryList: " + beerInventoryList);
        return beerInventoryList.stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .toList();
    }
}
