package com.belem.beerstore.service;


import com.belem.beerstore.model.Beer;
import com.belem.beerstore.model.BeerType;
import com.belem.beerstore.repository.Beers;
import com.belem.beerstore.service.exception.BeerAlreadyExistException;
import com.belem.beerstore.service.exception.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

public class BeerServiceTest {
    private BeerService beerService;

    @Mock
    private Beers beersMocked;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); // inicie os mocks dessa classe
        beerService = new BeerService(beersMocked);
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists() {
        Beer beerInDatabase = createBeerInDatabase();
        when(beersMocked.findByNameAndType("Heineken", BeerType.LAGER))
                .thenReturn(Optional.of(beerInDatabase));
        // quando findByNameAndType de Beers for chamado com os paremostro Heineken e BeerType.LAGER
        // vai retornar um Optional com o beerInDatabase.. Atencao, nao vai efetuar a consulta, pq está mockado!

        Beer beer = new Beer();
        beer.setName("Heineken");
        beer.setType(BeerType.LAGER);
        beer.setVolume(new BigDecimal("355"));

        beerService.save(beer);
    }

    @Test()
    public void should_create_new_beer() {
        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        when(beersMocked.save(newBeer)).thenReturn(createBeerInDatabase());

        Beer beerSaved = beerService.save(newBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }

    public void should_update_beer_volume() {
        final Beer beerInDatabase = new Beer();
        beerInDatabase.setId(10L);
        beerInDatabase.setName("Devassa");
        beerInDatabase.setType(BeerType.PILSEN);
        beerInDatabase.setVolume(new BigDecimal("300"));

        when(beersMocked.findByNameAndType("Devassa",
                BeerType.PILSEN)).thenReturn(Optional.of(beerInDatabase));

        final Beer beerToUpdate = new Beer();
        beerToUpdate.setId(10L);
        beerToUpdate.setName("Devassa");
        beerToUpdate.setType(BeerType.PILSEN);
        beerToUpdate.setVolume(new BigDecimal("200"));

        final Beer beerMocked = new Beer();
        beerMocked.setId(10L);
        beerMocked.setName("Devassa");
        beerMocked.setType(BeerType.PILSEN);
        beerMocked.setVolume(new BigDecimal("200"));

        when(beersMocked.save(beerToUpdate)).thenReturn(beerMocked);

        final Beer beerUpdated = beerService.save(beerToUpdate);
        assertThat(beerUpdated.getId(), equalTo(10L));
        assertThat(beerUpdated.getName(), equalTo("Devassa"));
        assertThat(beerUpdated.getType(), equalTo(BeerType.PILSEN));
        assertThat(beerUpdated.getVolume(), equalTo(new BigDecimal
                ("200")));
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete_when_beer_not_exist() {
        when(beersMocked.findById(5L)).thenReturn(Optional.empty());
        beerService.delete(5L);
    }

    @Test
    public void delete_of_an_existing_beer_that_already_exist() {
        final Beer beerInDatabse = createBeerInDatabase();
        when(beersMocked.findById(10L))
                .thenReturn(Optional.of(beerInDatabse));

        beerService.delete(10L);
    }

    private Beer createBeerInDatabase() {
        return new Beer(10L, "Heineken", BeerType.LAGER, new BigDecimal(355));
    }
    
}
