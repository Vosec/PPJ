package cz.tul;


import cz.tul.model.State;
import cz.tul.repositories.StateRepository;
import io.restassured.RestAssured;
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
import static com.jayway.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;

//https://blog.jayway.com/2014/07/04/integration-testing-a-spring-boot-application/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
@ActiveProfiles({"test"})
public class StateRestTest {

    @Autowired
    StateRepository repository;

    State america;
    State germany;
    State austria;

    @Value("${local.server.port}")
            int port;

    @Before
    public void setUp() {
        america = new State("America");
        austria = new State("Austria");
        germany = new State("Germany");


        repository.deleteAll();
        repository.save(Arrays.asList(america, austria, germany));

        RestAssured.port = port;
    }

    @Test
    public void getState() {
        String americaName = america.getStateName();

        when().
                get("/states/{stateName}", americaName).
                then().
                statusCode(HttpStatus.SC_OK).
                body("stateName", Matchers.is("America"));
    }
    @Test
    public void getAllStates(){
        when().
                get("/states").
                then().
                statusCode(HttpStatus.SC_OK).
                body("stateName", Matchers.hasItems("America", "Austria", "Germany"));

    }
    @Test
    public void deleteState() {
        String stateName = america.getStateName();

        when()
                .delete("/states/{stateName}", stateName).
                then().
                statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void createState() {
        given().contentType("application/json").body("{\"stateName\":\"Poland\"}").when()
                .request("POST","/states").then().statusCode(HttpStatus.SC_OK);
    }

}

