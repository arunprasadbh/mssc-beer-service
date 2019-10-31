package guru.springframework.msscbeerservice.repositories;

/*
 * Created by arunabhamidipati on 31/10/2019
 */

import guru.springframework.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
