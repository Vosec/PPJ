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


    public List<City> getCityByName(String cityname) {

        if (cityname == null) {
            return null;
        }

        List<City> cities = cityRepository.findByCityName(cityname);

        return cities;
    }


    public City getCity(Integer id) {
        return cityRepository.findOne(id);
    }


    public void delete(City city) {
            cityRepository.delete(city);
    }

    public void deleteCities() {
        cityRepository.deleteAll();
    }

    //pro cityID z rest api
    public City getCityByCityId(int cityId){return cityRepository.findByCityId(cityId);}

    //získání všech měst dle jména státu
    public List<City>  getCitiesByStateName(String stateName){return cityRepository.findByStateName(stateName);}


}
