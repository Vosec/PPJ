package cz.tul.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@RequestMapping("/download")
public class DownloadService {

    private static final String units = "metric";

    @Value("${download.apikey}")
    private String apikey;

    @Value("${download.updateafter}")
    private String updateafter;

    private String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    private String downloadDataForCityId(int cityId){
        String data = "";
        String myURL = API_URL + String.format("?id=%d&APPID=%s&units=%s",cityId, apikey, units);
        StringBuilder res = new StringBuilder();
        try {

            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while((data = in.readLine()) != null){
                res.append(data);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }



}
