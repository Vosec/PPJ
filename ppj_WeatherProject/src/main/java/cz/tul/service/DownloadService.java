package cz.tul.service;

import com.jayway.jsonpath.JsonPath;
import cz.tul.model.Measurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Service
@RequestMapping("/download")
public class DownloadService {
    //limit pro volání je 60 za minutu - TODO: udělat metodu pro nějaký počítání?
    private int counter;

    @Value("${download.units}")
    private static String units;

    @Value("${download.apikey}")
    private String apikey;

    @Value("${download.updateafter}")
    private String updateafter;

    private String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    public Measurement getDataForCityId(int cityId){
        String rawData = downloadDataForCityId(cityId);
        return parseData(cityId, rawData);
    }

    private Measurement parseData(int cityId, String data){
        String cityName = JsonPath.read(data, "$.name");
        double temperature = JsonPath.read(data, "$.main.temp");
        double pressure = toDouble(JsonPath.read(data, "$.main.pressure"));
        double humidity = toDouble(JsonPath.read(data, "$.main.humidity"));
        //Timestamp t = JsonPath.read(data, "$.dt"); kdyby náhodou

        Measurement res = new Measurement(cityId, temperature, humidity, pressure, cityName);
        return res;
    }

    //java.lang.Integer cannot be cast to java.lang.Double
    //někdy to vrací Integer... :-)
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    //TODO: otestovat potom na MVC
    private boolean canDownload(int counter, Date start, Date now){
        long diff;
        long diffSeconds;

        if (counter > 0) {
            diff = start.getTime() - now.getTime();
            diffSeconds = diff / 1000 % 60;
            if (diffSeconds <= 50) {
                return true;
            } else if (diffSeconds > 50 & diffSeconds <= 65) {
                return false;
            } else if (diffSeconds > 65) {
                this.counter = 0;
                return true;
            }
        }
        return true;
    }



}
