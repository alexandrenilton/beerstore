package com.belem.beerstore.repository;

import com.belem.beerstore.model.Beer;
import com.belem.beerstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Beers extends JpaRepository<Beer, Long> {
    Optional<Beer> findByNameAndType(String name, BeerType type);
}
