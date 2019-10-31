package guru.springframework.msscbeerservice.web.model;

/*
 * Created by arunabhamidipati on 31/10/2019
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

    private UUID id;

    private String beerName;

    private BeerStyle beerStyle;

    private Long upc;

    private Integer quantityOnHand;

    private BigDecimal price;

    private OffsetDateTime createDate;

    private OffsetDateTime lastModifiedDate;

}
