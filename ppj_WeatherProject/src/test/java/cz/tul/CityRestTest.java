package cz.tul;


import cz.tul.model.*;
import cz.tul.repositories.CityRepository;
import cz.tul.repositories.StateRepository;
import io.restassured.RestAssured;
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

    //TODO: Testy
    @Test
    public void getCity(){

    }

}
