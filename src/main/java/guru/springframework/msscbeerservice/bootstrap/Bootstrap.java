package guru.springframework.msscbeerservice.bootstrap;

/*
 * Created by arunabhamidipati on 31/10/2019
 */

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.BeerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    BeerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0) {
            loadBeerObjects();
             Iterable<Beer> beers = repository.findAll();
            for (Beer beer : beers) {
                System.out.println("Beer: " + beer);
            }
        }
    }

    private void loadBeerObjects() {
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Beer1")
                .beerStyle("LAGER")
                .upc(123412341234L)
                .quantityOnHand(200)
                .price(new BigDecimal("2.97"))
                .build();
        repository.save(beer1);

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Beer2")
                .beerStyle("ALE")
                .upc(123123123123L)
                .price(new BigDecimal("12.95"))
                .build();
        repository.save(beer2);
    }
}
