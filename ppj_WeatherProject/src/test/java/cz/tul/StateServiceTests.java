package cz.tul;

import cz.tul.api.RestApi;
import cz.tul.model.State;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
public class StateServiceTests {

    //https://square.github.io/retrofit/
    private final String TEST_URL = "http://localhost:8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(TEST_URL).addConverterFactory(JacksonConverterFactory.create()).build();
    private RestApi restService = retrofit.create(RestApi.class);

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    private State state1 = new State("Amerika");
    private State state2 = new State("Česká republika");
    private State state3 = new State("Anglie");
    private State state4 = new State("Slovensko");

    @Before
    public void init() {
        cityService.deleteCities();
        stateService.deleteStates();
    }

    @Test
    public void createStateTest() throws IOException {
        //zaroven test pro getAll
        stateService.create(state1);
        Response<State> response1 = restService.createState(state1).execute();
        assertNotNull("Should not be null",response1);
        Response<List<State>> execute = restService.getStates().execute();
        List<State> states = execute.body();
        assertEquals("Should be one state",1,states.size());
    }

    @Test
    public void getStateTest() throws IOException {
        stateService.create(state1);
        State states = stateService.get(state1.getStateName());
        Response<State> execute = restService.getState(states.getStateName()).execute();
        State state = execute.body();
        assertNotNull("should not be null", state);
        assertEquals("should be Amerika","Amerika",state.getStateName());
    }

    @Test
    public void updateStateTest() throws IOException{
        stateService.create(state1);
        State state = stateService.get(state1.getStateName());
        state.setStateName("Amerika");
        Response<State> response = restService.updateState(state).execute();
        State result = response.body();
        assertNotNull("Should not be null", result);
        assertEquals("State name should be updated", state.getStateName(), result.getStateName());

    }
    @Test
    public void deleteStateTest() throws IOException {
        Response<State> response1 = restService.createState(state2).execute();
        restService.deleteState(state2.getStateName()).execute();
        Response<State> execute = restService.getState(state2.getStateName()).execute();
        State state = execute.body();
        assertNull("should be null", state);
    }



}
