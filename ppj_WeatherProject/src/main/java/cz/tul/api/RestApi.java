package cz.tul.api;

import cz.tul.model.City;
import cz.tul.model.Measurement;
import cz.tul.model.State;
import org.springframework.web.bind.annotation.RequestMapping;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

//Retrofit - https://www.vogella.com/tutorials/Retrofit/article.html
public interface RestApi {
    String CITIES_PATH = "/cities";
    String CITY_PATH = CITIES_PATH + "/{id}";
    String STATES_PATH = "/states";
    String STATE_PATH = STATES_PATH + "/{stateName}";
    String MEASUREMENTS_PATH = "/measurements";
    String MEASUREMENT_PATH = MEASUREMENTS_PATH + "/{id}";


    //states
    @GET(STATES_PATH)
    Call<List<State>> getStates();

    //states/America
    @GET(STATE_PATH)
    Call<State> getState(@Path("stateName") String stateName);

    //{"stateName":"Spain"}
    @POST(STATES_PATH)
    Call<State> createState(@Body State state);

    //asi nefunguje??
    @POST(STATE_PATH)
    Call<State> updateState(@Body State state);

    //states/Greece
    @DELETE(STATE_PATH)
    Call<State> deleteState(@Path("stateName") String stateName);





    @GET(CITIES_PATH)
    Call<List<City>> getCities();

    @GET(MEASUREMENTS_PATH)
    Call<List<Measurement>> getMeasurements();

}
