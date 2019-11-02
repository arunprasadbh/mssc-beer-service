package guru.springframework.msscbeerservice.service;

/*
 * Created by arunabhamidipati on 01/11/2019
 */

import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}