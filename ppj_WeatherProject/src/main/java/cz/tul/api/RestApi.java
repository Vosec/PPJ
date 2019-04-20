package cz.tul.api;

import cz.tul.model.City;
import cz.tul.model.Measurement;
import cz.tul.model.State;
import cz.tul.service.MeasurementAvg;
import org.bson.types.ObjectId;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

//Retrofit - https://www.vogella.com/tutorials/Retrofit/article.html
public interface RestApi {
    String CITIES_PATH = "/cities";
    String CITY_PATH = CITIES_PATH + "/{cityId}";
    String STATES_PATH = "/states";
    String STATE_PATH = STATES_PATH + "/{stateName}";
    String MEASUREMENTS_PATH = "/measurements";
    String MEASUREMENT_PATH = MEASUREMENTS_PATH + "/{id}";
    String AVG_MEASUREMENT_PATH = "/avg-measurements" + "/{cityId}";
    String ACTUAL_MEASUREMENT_PATH = "/actual-measurements" + "/{cityId}";


    //states
    @GET(STATES_PATH)
    Call<List<State>> getStates();

    //states/America
    @GET(STATE_PATH)
    Call<State> getState(@Path("stateName") String stateName);

    //{"stateName":"Spain"}
    @POST(STATES_PATH)
    Call<State> createState(@Body State state);


    @POST(STATE_PATH)
    Call<State> updateState(@Body State state);

    //states/Greece
    @DELETE(STATE_PATH)
    Call<Void> deleteState(@Path("stateName") String stateName);

    //************************************************************************************************
    //cities
    @GET(CITIES_PATH)
    Call<List<City>> getCities();

    //cities/155645
    @GET(CITY_PATH)
    Call<City> getCity(@Path("cityId") int cityId);

    //musí existovat nejdříve stát!
    //{
    //	"cityName": "Oxford",
    //	"state": {
    //	"stateName": "United Kingdom"
    //  },
    //  "cityId": 123456,
    //  "stateName": "United Kingdom"
    //}
    @POST(CITIES_PATH)
    Call<City> createCity(@Body City city);

    @POST(CITY_PATH)
    Call<City> updateCity(@Body City city);

    //cities/156165
    @DELETE(CITY_PATH)
    Call<Void> deleteCity(@Path("cityId") int cityId);

    //************************************************************************************************
    //measurements
    @GET(MEASUREMENTS_PATH)
    Call<List<Measurement>> getMeasurements();

    @GET(MEASUREMENT_PATH)
    Call<Measurement> getMeasurement(@Path("id") ObjectId id);

    @POST(MEASUREMENTS_PATH)
    Call<List<Measurement>> createMeasurement(@Body Measurement Measurement);

    //localhost:8080/measurements/5cb98fe677a3978e0a56fa4e
    // {
    //	 "id": "5cb98fe677a3978e0a56fa4e",
    //    "temperature": 156.0,
    //    "humidity": 20.0,
    //    "pressure": 60.0,
    //    "cityName": "Oxford",
    //    "saveTime": 1555620956671,
    //    "cityId": 123456
    //  }
    @POST(MEASUREMENT_PATH)
    Call<Measurement> updateMeasurement(@Body Measurement Measurement);

    @DELETE(MEASUREMENT_PATH)
    Call<Void> deleteMeasurement(@Path("id") ObjectId id);

    //localhost:8080/avg-measurements/123456?from=one_week
    @GET(AVG_MEASUREMENT_PATH)
    Call<MeasurementAvg> getAvgMeasurement(@Path("cityId") int cityId, @Query("from") String from);

    @GET(ACTUAL_MEASUREMENT_PATH)
    Call<Measurement> getActualMeasurement(@Path("cityId") int cityId);

}
