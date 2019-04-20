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

    @Before
    public void init() {
        cityService.deleteCities();
        stateService.deleteStates();
    }

    @Test
    public void getCities() throws IOException {
        List<City> cities = new ArrayList<>();
        stateService.create(state1);
        cityService.create(city1);

        cities.add(city1);


        Response<List<City>> execute = restService.getCities().execute();
        List<City> result = execute.body();
        assertNotNull("Result should not be null", result);
        List<City> all = cityService.getCities();
        assertEquals("Result should be one", 1, all.size());

    }
    @Test
    public void testCreateCity() throws IOException {
        stateService.create(state1);
        cityService.create(city1);
        Response<State> s = restService.createState(state1).execute();
        Response<City> response = restService.createCity(city1).execute();
        City created = cityService.getCityByCityId(city1.getCityId());
        assertNotNull("Response should not be null", response.body());
        assertEquals("Created City does equals", city1.getCityName(), response.body().getCityName());
    }

    @Test
    public void testGetCity() throws IOException{
        stateService.create(state1);
        cityService.create(city1);
        City city = cityService.getCityByCityId(city1.getCityId());
        Response<City> response = restService.getCity(city.getCityId()).execute();
        City cityR = response.body();
        assertNotNull("Response should not be null", cityR);
        assertEquals("Created City does equals", city.getCityName(), cityR.getCityName());
    }
    @Test
    public void testUpdateCity() throws IOException{
        stateService.create(state1);
        cityService.create(city1);
        City city = cityService.getCityByCityId(city1.getCityId());
        city.setCityName("New York");
        Response<City> response = restService.updateCity(city).execute();
        assertNotNull("Response should not be null", response.body());
        assertEquals("Created City does equals", city.getCityName(), response.body().getCityName());
    }
    public void testDeleteCity() throws IOException{
        Response<State> s = restService.createState(state2).execute();
        Response<City> response1 = restService.createCity(city3).execute();
        restService.deleteCity(response1.body().getCityId()).execute();
        Response<City> execute = restService.getCity(city3.getCityId()).execute();
        City city = execute.body();
        assertNull("should be null", city);
    }
}
