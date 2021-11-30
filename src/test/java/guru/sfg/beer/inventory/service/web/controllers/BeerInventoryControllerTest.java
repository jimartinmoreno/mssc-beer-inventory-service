package guru.sfg.beer.inventory.service.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.beer.inventory.service.web.mappers.BeerInventoryMapper;
import guru.sfg.brewery.model.BeerInventoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test unitario de la capa MVC, solamente se prueba el controller y se mockea la respuesta del servicio
 *
 * @WebMvcTest >> If you want to focus only on the web layer and not start a complete ApplicationContext
 * <p>
 * Annotation that can be used for a Spring MVC test that focuses only on Spring MVC components.
 * Using this annotation will disable full auto-configuration and instead apply only configuration relevant to MVC tests
 * (i.e. @Controller, @ControllerAdvice, @JsonComponent, Converter/GenericConverter, Filter, WebMvcConfigurer
 * and HandlerMethodArgumentResolver beans but not @Component, @Service or @Repository beans).
 * <p>
 * auto-configures the Spring MVC infrastructure and limits scanned beans to @Controller, @ControllerAdvice, @JsonComponent,
 * Converter, GenericConverter, Filter, HandlerInterceptor, WebMvcConfigurer, WebMvcRegistrations, and
 * HandlerMethodArgumentResolver
 * <p>
 * Regular @Component and @ConfigurationProperties beans are not scanned when the @WebMvcTest annotation is used.
 * @EnableConfigurationProperties can be used to include @ConfigurationProperties beans.
 */
@WebMvcTest(controllers = {BeerInventoryController.class})
class BeerInventoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerInventoryRepository beerInventoryRepository;

    @MockBean
    BeerInventoryMapper beerInventoryMapper;

    @Test
    @WithMockUser(username = "good", password = "beer", roles = "USER")
    void listBeersByIdTest() throws Exception {
        given(beerInventoryRepository.findAllByBeerId(any())).willReturn(getValidListBeer());
        given(beerInventoryMapper.beerInventoryToBeerInventoryDto(any())).willReturn(new BeerInventoryDto());

        mockMvc.perform(get("/api/v1/beer/{beerId}/inventory", UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(beerInventoryRepository).should(times(1)).findAllByBeerId(any());
        then(beerInventoryMapper).should(times(1)).beerInventoryToBeerInventoryDto(any());
    }


    @Test
    void listBeersByIdHeadersTest() throws Exception {
        given(beerInventoryRepository.findAllByBeerId(any())).willReturn(getValidListBeer());
        given(beerInventoryMapper.beerInventoryToBeerInventoryDto(any())).willReturn(new BeerInventoryDto());

        mockMvc.perform(get("/api/v1/beer/{beerId}/inventory", UUID.randomUUID().toString())
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("good:beer".getBytes()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        then(beerInventoryRepository).should(times(1)).findAllByBeerId(any());
        then(beerInventoryMapper).should(times(1)).beerInventoryToBeerInventoryDto(any());
    }

    List<BeerInventory> getValidListBeer() {
        return List.of(BeerInventory.builder()
                .beerId(UUID.randomUUID())
                .upc("11111111111")
                .beerId(UUID.randomUUID())
                .quantityOnHand(10)
                .version(0L)
//                .createdDate(Timestamp.valueOf(new Date().toString()))
                .build());
    }
}