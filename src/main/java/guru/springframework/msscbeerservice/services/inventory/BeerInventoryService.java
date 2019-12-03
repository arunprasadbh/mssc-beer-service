package guru.springframework.msscbeerservice.services.inventory;

/*
 * Created by arunabhamidipati on 03/11/2019
 */

import java.util.UUID;

public interface BeerInventoryService {
    Integer getOnHandInventory(UUID beerId);
}
