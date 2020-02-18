package com.belem.beerstore.resource;

import com.belem.beerstore.model.Beer;
import com.belem.beerstore.repository.Beers;
import com.belem.beerstore.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    Beers beers;

    @Autowired
    BeerService beerService;

    @GetMapping
    public List<Beer> all() {
        return beers.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer) {
        beerService.save(beer);
        return beer;
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable("id") Long id,
                       @Valid @RequestBody Beer beer){
        beer.setId(id);
        return beerService.save(beer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        beerService.delete(id);
    }

}