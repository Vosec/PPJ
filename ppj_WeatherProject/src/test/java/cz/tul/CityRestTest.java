package cz.tul;


import cz.tul.model.*;
import cz.tul.repositories.CityRepository;
import cz.tul.repositories.StateRepository;
import io.restassured.RestAssured;
import net.minidev.json.JSONObject;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;

//https://blog.jayway.com/2014/07/04/integration-testing-a-spring-boot-application/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
@ActiveProfiles({"test"})
public class CityRestTest {

    @Value("${local.server.port}")
    int port;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CityRepository cityRepository;

    private State state1 = new State("Amerika");
    private State state2 = new State("Slovensko");
    private State state3 = new State("Anglie");
    private State state4 = new State("Německo");


    private City city1 = new City("New York", state1, 9);
    private City city2 = new City("Chicago", state1, 99);
    private City city3 = new City("Bratislava", state2, 8994);
    private City city4 = new City("Londýn", state3, 5646);

    @Before
    public void init() {
        cityRepository.deleteAll();
        stateRepository.deleteAll();

        stateRepository.save(Arrays.asList(state1, state2, state3));
        cityRepository.save(Arrays.asList(city1,city2,city3, city4));

        RestAssured.port = port;

    }

    @Test
    public void testGetCity(){
        when().
                get("/cities/{cityId}", city1.getCityId()).
                then().
                statusCode(HttpStatus.SC_OK).
                body("cityId", Matchers.is(9));
    }
    @Test
    public void testGetAllCities(){
        when().
                get("/cities").
                then().
                statusCode(HttpStatus.SC_OK).
                body("cityId", Matchers.hasItems(city1.getCityId(), city2.getCityId(), city3.getCityId(),
                                                    city4.getCityId()));
    }
    @Test
    public void testDeleteCity() {

        when()
                .delete("/cities/{cityId}", city1.getCityId()).
                then().
                statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void testCreateCity() {
        JSONObject jo = new JSONObject();

        jo.put("cityName", "abcd");
        Map m = new LinkedHashMap(1);
        m.put("stateName", "Slovensko");
        jo.put("state", m);
        jo.put("cityId", 0);
        //System.out.println(jo);

        //"{\"cityId\":\1\", \"stateName\":\"Amerika\", \"cityName\":\"rh\"}"
        given().contentType("application/json").body(jo).when()
                .request("POST","/cities").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testUpdateCity(){
        JSONObject jo = new JSONObject();

        //změna názvu města
        jo.put("cityName", "xxx");
        Map m = new LinkedHashMap(1);
        m.put("stateName", "Slovensko");
        jo.put("state", m);
        jo.put("cityId", 8994);

        given().contentType("application/json").body(jo).when()
                .request("POST","/cities/{cityId}",8994).then().statusCode(HttpStatus.SC_OK);
    }

}
