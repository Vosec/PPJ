package cz.tul.service;

import cz.tul.model.City;
import cz.tul.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getCities() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void create(City city) {
        cityRepository.save(city);
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public boolean exists(int cityId){return cityRepository.exists(cityId);}

    public void delete(City city) {
            cityRepository.delete(city);
    }

    public void deleteCities() {
        cityRepository.deleteAll();
    }

    //for city id from API
    public City getCityByCityId(int cityId){return cityRepository.findByCityId(cityId);}

    public List<City>  getCitiesByStateName(String name){return cityRepository.findByStateName(name);}


}
