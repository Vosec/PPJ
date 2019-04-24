package cz.tul;

import cz.tul.model.City;
import cz.tul.model.Measurement;
import cz.tul.model.State;
import cz.tul.repositories.CityRepository;
import cz.tul.repositories.MeasurementRepository;
import cz.tul.repositories.StateRepository;
import io.restassured.RestAssured;
import net.minidev.json.JSONObject;
import org.apache.http.HttpStatus;
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
import static com.jayway.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;

import java.util.*;

//https://blog.jayway.com/2014/07/04/integration-testing-a-spring-boot-application/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
@ActiveProfiles({"test"})
public class MeasurementRestTest {

    @Value("${local.server.port}")
    int port;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    private Measurement m1 = new Measurement(15456,28,113,48,"Praha");
    private Measurement m2 = new Measurement(4554,22,554,33,"Bratislava");

    private State state1 = new State("CR");
    private City city1 = new City("Praha", state1, 15456);

    @Before
    public void init() {
        measurementRepository.deleteAll();
        stateRepository.deleteAll();
        cityRepository.deleteAll();

        stateRepository.save(state1);
        cityRepository.save(city1);
        measurementRepository.save(Arrays.asList(m1,m2));

        RestAssured.port = port;
    }

    @Test
    public void testGetMeasurements(){
        when().
                get("/measurements").
                then().
                statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void testGetMeasurement(){
        when().
                get("/measurements/{id}", m1.getId()).
                then().
                statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void testDeleteMeasurement(){
        when()
                .delete("/measurements/{id}", m1.getId()).
                then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testUpdateMeasurement(){
        JSONObject jo = new JSONObject();

        //změna názvu města
        jo.put("id", m1.getId());
        jo.put("cityName", "Oslo");
        jo.put("cityId", 15456);
        jo.put("temperature", 1);
        jo.put("pressure", 1);
        jo.put("humidity", 1);
        jo.put("saveTime", 1646584);

        given().contentType("application/json").body(jo).when()
                .request("POST","/measurements/{id}",m1.getId()).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testGetActualMeasurement(){
        when().
                get("/actual-measurements/{cityId}", m1.getCityId()).
                then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testGetOneDayMeasurement(){
        String from = "day";

       given().param("from", from).when().
                get("/avg-measurements/{cityId}", m1.getCityId()).
                then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testGetOneWeekMeasurement(){
        String from = "one_week";

        given().param("from", from).when().
                get("/avg-measurements/{cityId}", m1.getCityId()).
                then().
                statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void testGetTwoWeeksMeasurement(){
        String from = "two_weeks";

        given().param("from", from).when().
                get("/avg-measurements/{cityId}", m1.getCityId()).
                then().
                statusCode(HttpStatus.SC_OK);

    }
}
