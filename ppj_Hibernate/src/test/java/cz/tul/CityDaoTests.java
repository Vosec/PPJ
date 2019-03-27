package cz.tul;

import cz.tul.data.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
public class CityDaoTests {

    @Autowired
    private StateDao stateDao;

    @Autowired
    private CityDao cityDao;

    private State state1 = new State("Amerika");
    private State state2 = new State("Slovensko");
    private State state3 = new State("Anglie");
    private State state4 = new State("Německo");


    private City city1 = new City("New York", state1);
    private City city2 = new City("Chicago", state1);
    private City city3 = new City("Bratislava", state1);
    private City city4 = new City("Londýn", state1);
    private City city5 = new City("Berlín", state1);

    @Before
    public void init() {
        cityDao.deleteCities();
        stateDao.deleteStates();
    }
    @Test
    public void testDelete() {
        stateDao.create(state1);
        stateDao.create(state2);
        stateDao.create(state3);
        stateDao.create(state4);
        cityDao.saveOrUpdate(city2);
        cityDao.saveOrUpdate(city3);
        cityDao.saveOrUpdate(city4);
        cityDao.saveOrUpdate(city5);


        City retrieved1 = cityDao.getCity(city2.getId());
        assertNotNull("Offer with ID " + retrieved1.getId() + " should not be null (deleted, actual)", retrieved1);

        cityDao.delete(city2.getId());

        City retrieved2 = cityDao.getCity(city2.getId());
        assertNull("Offer with ID " + retrieved1.getId() + " should be null (deleted, actual)", retrieved2);
    }





}
