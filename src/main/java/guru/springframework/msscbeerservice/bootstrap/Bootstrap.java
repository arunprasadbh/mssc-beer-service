package guru.springframework.msscbeerservice.bootstrap;

/*
 * Created by arunabhamidipati on 31/10/2019
 */

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.util.UUID;

//@Component
public class Bootstrap implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

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
               // .id(BEER_1_UUID)
                .beerName("Heineken")
                .beerStyle("LAGER")
                .upc(BEER_1_UPC)
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("2.97"))
                .build();
        repository.save(beer1);

        Beer beer2 = Beer.builder()
               // .id(BEER_2_UUID)
                .beerName("Carona")
                .beerStyle("ALE")
                .minOnHand(12)
                .upc(BEER_2_UPC)
                .price(new BigDecimal("12.95"))
                .build();
        repository.save(beer2);

        Beer beer3 = Beer.builder()
                //.id(BEER_3_UUID)
                .beerName("Galaxy Cat")
                .beerStyle("PALE_ALE")
                .minOnHand(12)
                //.quantityToBreew
                .upc(BEER_3_UPC)
                .price(new BigDecimal("11.95"))
                .build();
        repository.save(beer3);
    }
}
