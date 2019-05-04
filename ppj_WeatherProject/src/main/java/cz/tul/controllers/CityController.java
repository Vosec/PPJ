package cz.tul.controllers;

import cz.tul.api.RestApi;
import cz.tul.helper.InputHelper;
import cz.tul.model.City;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CityController {
    private CityService cityService;
    private StateService stateService;
    private InputHelper ih = new InputHelper();

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setStateService(StateService stateService){this.stateService = stateService;}

    @RequestMapping(value = RestApi.CITIES_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<City>> getCities() {
        List<City> cities = cityService.getCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    //City has to belong to already created State, else 400-BAD RQST
    @RequestMapping(value = RestApi.CITIES_PATH, method = RequestMethod.POST)
    public ResponseEntity<City> createCity(@RequestBody City city) {
        if (cityService.exists(city.getCityId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!stateService.exists(city.getStateName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (city.getName().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (ih.isValidName(city.getName()) | ih.isValidCityId(city.getCityId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            cityService.create(city);
            return new ResponseEntity<>(city, HttpStatus.OK);
        }
    }

    @RequestMapping(value = RestApi.CITY_PATH, method = RequestMethod.GET)
    public ResponseEntity<City> getCity(@PathVariable("cityId") int cityId) {
        City city = cityService.getCityByCityId(cityId);
        if (city != null) {
            return new ResponseEntity<>(city, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = RestApi.CITY_PATH, method = RequestMethod.POST)
    public ResponseEntity<City> updateCity(@RequestBody City city) {
        City requestedCity = cityService.getCityByCityId(city.getCityId());
        if (requestedCity != null) {
            cityService.delete(requestedCity);
            City updatedCity = cityService.save(city);
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = RestApi.CITY_PATH, method = RequestMethod.DELETE)
    public ResponseEntity deleteCity(@PathVariable("cityId") int cityId) {
        City city = cityService.getCityByCityId(cityId);
        if (city != null) {
            cityService.delete(city);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
