package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.bootstrap.Bootstrap;
import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
 * Created by arunabhamidipati on 31/10/2019
 */

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        given(beerService.getBeerById(UUID.randomUUID(), false)).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String jsonString = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        UUID id = UUID.randomUUID();
        String jsonString = objectMapper.writeValueAsString(beerDto);

        given(beerService.updateBeer(any(),any())).willReturn(getValidBeerDto());
        mockMvc.perform(put("/api/v1/beer/"+id.toString())
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonString))
                .andExpect(status().isNoContent());

    }

    @Test
    void getBeerByUpc() throws Exception {
        given(beerService.getBeerByUpc(Bootstrap.BEER_1_UPC, false)).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beerUpc/" + Bootstrap.BEER_1_UPC, false
        ).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                ;
    }

    private BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("Sample Beer")
                .beerStyle(BeerStyle.LAGER)
                .price(new BigDecimal("2.29"))
                .upc(Bootstrap.BEER_1_UPC)
                .quantityOnHand(20)
                .build();
    }
}