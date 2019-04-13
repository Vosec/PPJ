package cz.tul.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/download")
public class DownloadService {

    @Value("${download.apiKey}")
    private String apiKey;

    @Value("${download.updateAfter}")
    private String updateAfter;

    private String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    public String downloadDataForCity(int cityId){
        String data = "";
        String URL = API_URL + String.format("?id=%d&APPID=%s&units=metric",cityId, apiKey);


        return URL;
    }



}
