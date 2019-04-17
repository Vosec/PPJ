package cz.tul.controllers;

import cz.tul.api.RestApi;
import cz.tul.model.City;
import cz.tul.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
    private CityService cityService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = RestApi.CITIES_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<City>> getCities() {
        List<City> cities = cityService.getCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

}
