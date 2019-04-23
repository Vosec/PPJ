package cz.tul;


import cz.tul.api.RestApi;
import cz.tul.model.Measurement;
import cz.tul.repositories.MeasurementRepository;
import cz.tul.service.MeasurementService;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;

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

    private Date d1 = new Date();

    private Measurement m1 = new Measurement(15456,28,113,48,"Praha",d1);
    private Measurement m2 = new Measurement(4554,22,554,33,"Bratislava",d1);

    @Before
    public void init() {
        measurementRepository.deleteAll();
        measurementRepository.save(Arrays.asList(m1,m2));

        RestAssured.port = port;

    }

    //TODO: Testy
    @Test
    public void getMeasurements(){

    }


}
