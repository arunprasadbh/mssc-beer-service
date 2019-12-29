package guru.springframework.msscbeerservice.events;

/*
 * Created by arunabhamidipati on 29/12/2019
 */


import guru.springframework.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent{
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
