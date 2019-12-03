package guru.springframework.msscbeerservice.services;

/*
 * Created by arunabhamidipati on 01/11/2019
 */

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("beerService")
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper mapper;

    @Cacheable(cacheNames = "beerCache", condition = "#enhanceInventory == false", key = "#beerId")
    @Override
    public BeerDto getBeerById(UUID beerId, Boolean enhanceInventory) {
       // System.out.println("GerBeerById method called");

        if (enhanceInventory) {
            return mapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }else{
            return mapper.beerToBeerDtoNoEnhance(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }

    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return mapper.beerToBeerDto(beerRepository.save(mapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return mapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Cacheable(cacheNames = "beerListCache", condition = "#enhanceInventory == false")
    @Override
    public BeerPagedList listBeers(String beerName, String beerStyle, PageRequest pageRequest, Boolean enhanceInventory) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        //System.out.println("ListBeers method called");
        if(!StringUtils.isEmpty(beerName) && ! StringUtils.isEmpty(beerStyle)){
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        }else{
            beerPage = beerRepository.findAll(pageRequest);
        }

        if (enhanceInventory) {
            beerPagedList = new BeerPagedList(beerPage.getContent()
                    .stream()
                    .map(mapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements()
            );
        }else{
            beerPagedList = new BeerPagedList(beerPage.getContent()
                    .stream()
                    .map(mapper::beerToBeerDtoNoEnhance)
                    .collect(Collectors.toList()),
                    PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements()
            );
        }

        return beerPagedList;
    }

    @Override
    public List<BeerDto> listAllBeers() {
        List<Beer> beers = new ArrayList<>();
        beerRepository.findAll().forEach(beers::add);
        List<BeerDto> beerDtos = new ArrayList<>();
        beers.stream().forEach(beer -> beerDtos.add(mapper.beerToBeerDto(beer)));
        return beerDtos;
    }
}
