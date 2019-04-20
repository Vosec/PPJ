package cz.tul;


import cz.tul.api.RestApi;
import cz.tul.model.Measurement;
import cz.tul.service.MeasurementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
public class MeasurementServiceTest {
    //https://square.github.io/retrofit/
    private final String TEST_URL = "http://localhost:8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(TEST_URL).addConverterFactory(JacksonConverterFactory.create()).build();
    private RestApi restService = retrofit.create(RestApi.class);


    @Autowired
    private MeasurementService measurementService;

    private Date d1 = new Date();

    private Measurement m1 = new Measurement(15456,28,113,48,"Praha",d1);
    private Measurement m2 = new Measurement(4554,22,554,33,"Bratislava",d1);

    //TODO: Testy
    @Test
    public void getMeasurements(){

    }

    /*
    @Before
    public void init(){
        measurementService.deleteAll();
    }

    @Test
    public void testCreate(){
        measurementService.addMeasurement(m1);
        List<Measurement> measurements = measurementService.findAll();
        assertEquals("Should be 1 measurement", 1, measurements.size());
    }
    @Test
    public void testAddRemove(){
        measurementService.addMeasurement(m1);
        measurementService.addMeasurement(m2);
        measurementService.removeMeasurement(m1);
        List<Measurement> measurements = measurementService.findAll();
        assertEquals("Should be 1 measurement", 1, measurements.size());

    }
    */
}
