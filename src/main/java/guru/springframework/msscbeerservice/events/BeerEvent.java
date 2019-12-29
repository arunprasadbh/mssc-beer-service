package guru.springframework.msscbeerservice.events;

/*
 * Created by arunabhamidipati on 29/12/2019
 */

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -6816411330002278581L;

    private final BeerDto beerDto;
}
