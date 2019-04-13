package cz.tul.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DownloadService {

    @Value("$(download.api-key)")
    private String api_key;

    @Value("$(download.update-after)")
    private Integer update_after;

    

}
