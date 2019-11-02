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

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
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
            System.out.println("Count of Beers: " +  repository.count());
        }
    }

    private void loadBeerObjects() {
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Heineken")
                .beerStyle("LAGER")
                .upc(BEER_1_UPC)
                .quantityOnHand(200)
                .price(new BigDecimal("2.97"))
                .build();
        repository.save(beer1);

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Carona")
                .beerStyle("ALE")
                .upc(BEER_2_UPC)
                .price(new BigDecimal("12.95"))
                .build();
        repository.save(beer2);

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("PALE_ALE")
                .upc(BEER_3_UPC)
                .price(new BigDecimal("11.95"))
                .build();
        repository.save(beer3);
    }
}
