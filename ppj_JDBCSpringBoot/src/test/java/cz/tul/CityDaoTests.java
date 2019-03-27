package cz.tul;

import cz.tul.data.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CityDaoTests {

    @Autowired
    private StateDao stateDao;

    @Autowired
    private CityDao cityDao;


    @Test
    public void Test1_createCity() {

        //usersDao.deleteUsers();

        State state = new State("Albania");

        assertTrue("User creation should return true", stateDao.create(state));

        City city = new City(1, "London", state);

        assertTrue("Offer creation should return true", cityDao.create(city));

    }

    @Test
    public void Test2_listCities() {
        List<City> cities = cityDao.getCities();
        // Get the offer with ID filled in.
        City city = cities.get(0);

        assertEquals("Should be one city in database.", 1, cities.size());

        assertEquals("Retrieved city should match created city.", city,
                cities.get(0));
    }

    @Test
    public void Test3_updateCity() {

        List<City> cities = cityDao.getCities();

        // Get the offer with ID filled in.
        City city = cities.get(0);

        city.setName("Praha");
        assertTrue("City update should return true", cityDao.update(city));

        City updated = cityDao.getCity(city.getId());

        assertEquals("Updated offer should match retrieved updated offer", city, updated);
    }

     @Test
     public void Test4_getCityById() {

         List<State> states = stateDao.getAllStates();

         // Test get by ID ///////
         City city2 = new City("Praha", states.get(0));

         assertTrue("City creation should return true", cityDao.create(city2));

         List<City> cities = cityDao.getCities();
         assertEquals("Should be two cities for state.", 2, cities.size());

         List<City> secondList = cityDao.getCities();
         System.out.println(secondList);

         for (City current : secondList) {
             City retrieved = cityDao.getCity(current.getId());

         assertEquals("City by ID should match city from list.", current, retrieved);
         }
     }

     @Test
     public void Test5_deleteCity() {

     List<City> cities = cityDao.getCities();

     // Get the offer with ID filled in.
         City city = cities.get(0);

     // Test deletion
         cityDao.delete(city.getId());

     List<City> finalList = cityDao.getCities();

     assertEquals("Citites lists should contain one city.", 1, finalList.size());
     }

}
