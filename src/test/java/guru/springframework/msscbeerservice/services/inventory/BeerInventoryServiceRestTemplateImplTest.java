package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.bootstrap.Bootstrap;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


/*
 * Created by arunabhamidipati on 03/12/2019
 */

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void setBeerInventoryServiceHost() {
    }

    @Test
    void getOnHandInventory() {

      //  Integer qoh = beerInventoryService.getOnHandInventory(Bootstrap.BEER_1_UUID);
      //  System.out.println(qoh);
    }
}