package guru.sfg.common.events;

/*
 * Created by arunabhamidipati on 29/12/2019
 */

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -6816411330002278581L;

    private  BeerDto beerDto;
}
