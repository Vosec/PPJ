package cz.tul;

import cz.tul.api.RestApi;
import cz.tul.model.*;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
//@SpringBootTest(classes = {Main.class})
@ActiveProfiles({"test"})
public class CityServiceTests {
    //https://square.github.io/retrofit/
    private final String TEST_URL = "http://localhost:8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(TEST_URL).addConverterFactory(JacksonConverterFactory.create()).build();
    private RestApi restService = retrofit.create(RestApi.class);


    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;



    private State state1 = new State("Amerika");
    private State state2 = new State("Slovensko");
    private State state3 = new State("Anglie");
    private State state4 = new State("Německo");


    private City city1 = new City("New York", state1, 9);
    private City city2 = new City("Chicago", state1, 99);
    private City city3 = new City("Bratislava", state2, 8994);
    private City city4 = new City("Londýn", state3, 5646);
    private City city5 = new City("Berlín", state4, 5654);



    //TODO: Testy jdou spustit když běží server :) už jen dodělat testy, ale až po tom co budu mít metody
    // Hází to data z DB
    @Test
    public void getCities() throws IOException {
        List<City> cities = new ArrayList<>();
        stateService.create(state1);
        cityService.create(city1);
        cityService.create(city2);
        cities.add(city1);
        cities.add(city2);


        Response<List<City>> execute = restService.getCities().execute();
        List<City> result = execute.body();
        System.out.println(result);
        assertNotNull("Result should not be null", result);
        List<City> all = cityService.getCities();
        System.out.println(all);
        assertNotEquals("Result should not have same size", result.size(), all.size());

    }
    /*
     @Before
     public void init() {
     cityService.deleteCities();
     stateService.deleteStates();

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
        assertNotNull("City with ID " + retrieved1.getId() + " should not be null (deleted, actual)", retrieved1);

        cityDao.delete(city2.getId());

        City retrieved2 = cityDao.getCity(city2.getId());
        assertNull("City with ID " + retrieved1.getId() + " should be null (deleted, actual)", retrieved2);
    }
    @Test
    public void testGetById() {
        stateDao.create(state1);
        stateDao.create(state2);
        stateDao.create(state3);
        stateDao.create(state4);
        cityDao.saveOrUpdate(city1);
        cityDao.saveOrUpdate(city2);
        cityDao.saveOrUpdate(city3);
        cityDao.saveOrUpdate(city4);
        cityDao.saveOrUpdate(city5);


        City retrieved1 = cityDao.getCity(city1.getId());
        assertEquals("Cities should match", city1, retrieved1);

    }
    @Test
    public void testCreateRetrieve() {
        stateDao.create(state1);
        stateDao.create(state2);
        stateDao.create(state3);
        stateDao.create(state4);

        cityDao.saveOrUpdate(city1);

        List<City> cities1 = cityDao.getCities();
        assertEquals("Should be one city.", 1, cities1.size());

        assertEquals("Retrieved city should equal inserted user.", city1,
                cities1.get(0));

        cityDao.saveOrUpdate(city2);
        cityDao.saveOrUpdate(city3);
        cityDao.saveOrUpdate(city4);
        cityDao.saveOrUpdate(city5);


        List<City> cities2 = cityDao.getCities();
        assertEquals("Should be five cities", 5,
                cities2.size());
    }
    @Test
    public void testUpdate() {
        stateDao.create(state1);
        stateDao.create(state2);
        stateDao.create(state3);
        stateDao.create(state4);
        cityDao.saveOrUpdate(city2);
        cityDao.saveOrUpdate(city3);
        cityDao.saveOrUpdate(city4);
        cityDao.saveOrUpdate(city5);



        city3.setName("Praha");
        cityDao.saveOrUpdate(city3);

        City retrieved = cityDao.getCity(city3.getId());
        assertEquals("Město 3 by mělo mít název", city3, retrieved);
    }
    @Test
    public void testGetCityByName() {
        stateDao.create(state1);
        stateDao.create(state2);
        stateDao.create(state3);
        stateDao.create(state4);

        cityDao.saveOrUpdate(city1);
        cityDao.saveOrUpdate(city2);
        cityDao.saveOrUpdate(city3);
        cityDao.saveOrUpdate(city4);

        List<City> cities2 = cityDao.getCityByName(city5.getCityName());
        assertEquals("Should be zero cities", 0, cities2.size());

        List<City> cities3 = cityDao.getCityByName(city2.getCityName());
        assertEquals("Should be 1 city", 1, cities3.size());
    }
    **/



}
