package cz.tul.service;

import cz.tul.model.Measurement;
import cz.tul.repositories.CityRepository;
import cz.tul.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final MongoTemplate mongoTemplate;
    private final MeasurementRepository measurementRepository;
    private final CityRepository cityRepository;

    @Autowired
    public MeasurementService(MongoTemplate mongoTemplate, MeasurementRepository measurementRepository, CityRepository cityRepository) {
        this.mongoTemplate = mongoTemplate;
        this.measurementRepository = measurementRepository;
        this.cityRepository = cityRepository;
    }

    public void removeMeasurement(Measurement measurement){
        mongoTemplate.remove(measurement);
    }

    public void addMeasurement(Measurement measurement){
        mongoTemplate.insert(measurement);
    }

    public List<Measurement> getMeasByCityName(String cityName){
        return measurementRepository.findByCityName(cityName);
    }
    public List<Measurement> getMeasByCityId(int cityId){
        return measurementRepository.findByCityId(cityId);
    }

    public void deleteAll(){
        measurementRepository.deleteAll();
    }
    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }


}
