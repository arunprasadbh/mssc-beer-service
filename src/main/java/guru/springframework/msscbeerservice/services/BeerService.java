package guru.springframework.msscbeerservice.services;

/*
 * Created by arunabhamidipati on 01/11/2019
 */

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId, Boolean enhanceInventory);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, String beerStyle, PageRequest of, Boolean enhanceInventory);

    List<BeerDto> listAllBeers();
}