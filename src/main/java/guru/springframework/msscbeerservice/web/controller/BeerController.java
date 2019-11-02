package guru.springframework.msscbeerservice.web.controller;

/*
 * Created by arunabhamidipati on 31/10/2019
 */

import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity <>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto){
        return new ResponseEntity <>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto) {
        return new ResponseEntity <>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
    }

    public static class NotFoundException extends RuntimeException{
    }
}
