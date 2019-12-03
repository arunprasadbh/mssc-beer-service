package guru.springframework.msscbeerservice.services.inventory.model;

/*
 * Created by arunabhamidipati on 03/11/2019
 */

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerInventoryDto {
    private UUID id;
    private OffsetDateTime createdTime;
    private OffsetDateTime updatedTime;
    private UUID beerId;
    private Integer quantityOnHand;
}
