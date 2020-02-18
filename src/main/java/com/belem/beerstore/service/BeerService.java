package com.belem.beerstore.service;

import com.belem.beerstore.model.Beer;
import com.belem.beerstore.repository.Beers;
import com.belem.beerstore.service.exception.BeerAlreadyExistException;
import com.belem.beerstore.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    private Beers beers;

    public BeerService(@Autowired Beers beers) {
        this.beers = beers;
    }

    public Beer save(Beer beer) {
        Optional<Beer> beerByNameAndType = beers.findByNameAndType(beer.getName(), beer.getType()) ;

        if ( beerByNameAndType.isPresent()) {
            throw new BeerAlreadyExistException();
        }

        return beers.save(beer);
    }

    public void delete(Long id) {
        Optional<Beer> beerOptional = beers.findById(id);

        if (!beerOptional.isPresent()) {
            throw new EntityNotFoundException();
        }
        beers.delete(beerOptional.get());
    }

}
