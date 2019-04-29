package cz.tul.service;

import com.jayway.jsonpath.JsonPath;
import cz.tul.config.ReadOnlySetup.ReadOnlyConditionDisabled;
import cz.tul.model.City;
import cz.tul.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Conditional(ReadOnlyConditionDisabled.class)
@Service
public class DownloadService {
    //limit for api calls: 60 in 1 minute
    private int counter;
    private CityService cityService;
    private MeasurementService measurementService;
    private static final Logger LOGGER = Logger.getLogger( DownloadService.class.getName());
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setMeasurementService(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @Autowired
    public void setThreadPoolTaskScheduler(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
        startUpdatingData();
    }

    @Value("${download.units}")
    private String units;

    @Value("${download.apikey}")
    private String apikey;

    //ms !!!
    @Value("${download.updateafter}")
    private int updateafter;

    @Value("${download.url}")
    private String API_URL;

    private Measurement getDataForCityId(int cityId){
        String rawData = downloadDataForCityId(cityId);
        return parseData(cityId, rawData);
    }
    private List<City> getAllCities(){
        return cityService.getCities();
    }

    private void startUpdatingData(){
        threadPoolTaskScheduler.scheduleAtFixedRate(this::work, updateafter);
    }

    private void work(){
        List<City> cities = getAllCities();
        for (City city : cities) {
            Measurement m = getDataForCityId(city.getCityId());
            measurementService.save(m);
        }
    }

    private Measurement parseData(int cityId, String data){
        String cityName = JsonPath.read(data, "$.name");
        double temperature = JsonPath.read(data, "$.main.temp");
        double pressure = toDouble(JsonPath.read(data, "$.main.pressure"));
        double humidity = toDouble(JsonPath.read(data, "$.main.humidity"));
        //Timestamp t = JsonPath.read(data, "$.dt"); if I ever wanted to get time of measurement

        Measurement res = new Measurement(cityId, temperature, humidity, pressure, cityName);
        return res;
    }

    //java.lang.Integer cannot be cast to java.lang.Double
    //sometimes it returns integer... :-)
    private double toDouble(Object in){
        if(in instanceof Double){
            return (Double) in;
        }
        return ((Integer) in).doubleValue();
    }

    private String downloadDataForCityId(int cityId){
        String data;
        this.counter++;
        Date start = null;
        Date now = new Date();
        String myURL = API_URL + String.format("?id=%d&APPID=%s&units=%s",cityId, apikey, units);
        StringBuilder res = new StringBuilder();

        if (this.counter == 1) {
            start = new Date();
        }
        try {
            URL url = new URL(myURL);

            if(canDownload(this.counter, start, now)) {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                while((data = in.readLine()) != null){
                    res.append(data);
                }
                in.close();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    //max 60 calls in 1 minute
    private boolean canDownload(int counter, Date start, Date now){
        long diff;
        long diffSeconds;

        if (counter > 50) {
            diff = start.getTime() - now.getTime();
            diffSeconds = diff / 1000 % 60;
            if (diffSeconds <= 50) {
                return true;
            } else if (diffSeconds > 50 & diffSeconds <= 65) {
                LOGGER.log(Level.WARNING, "More than 50 api calls were called under 1 minute");
                return false;
            } else if (diffSeconds > 65) {
                this.counter = 0;
                return true;
            }
        }
        return true;
    }



}
