package cz.tul.api;

import cz.tul.model.City;
import cz.tul.model.State;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface RestApi {
    String CITIES_PATH = "/cities";
    String CITY_PATH = CITIES_PATH + "/{id}";
    String STATES_PATH = "/states";
    String STATE_PATH = STATES_PATH + "/{id}";
    String MEASUREMENTS_PATH = "/measurements";
    String MEASUREMENT_PATH = MEASUREMENTS_PATH + "/{id}";


    @GET(STATES_PATH)
    Call<List<State>> getStates();

    @GET(CITIES_PATH)
    Call<List<City>> getCities();

}
