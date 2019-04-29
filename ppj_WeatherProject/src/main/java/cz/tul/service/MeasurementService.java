package cz.tul.service;

import cz.tul.model.MeasurementAvg;
import org.springframework.data.mongodb.core.query.Criteria;
import cz.tul.model.Measurement;
import cz.tul.repositories.CityRepository;
import cz.tul.repositories.MeasurementRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MeasurementService {

    private final MongoTemplate mongoTemplate;
    private final MeasurementRepository measurementRepository;
    private final CityService cityService;
    private final CityRepository cityRepository;
    private Calendar cal;

    @Autowired
    public MeasurementService(MongoTemplate mongoTemplate, MeasurementRepository measurementRepository, CityRepository cityRepository, CityService cityService) {
        this.mongoTemplate = mongoTemplate;
        this.measurementRepository = measurementRepository;
        this.cityRepository = cityRepository;
        this.cityService = cityService;
    }

    public void addMeasurement(Measurement measurement){
        mongoTemplate.insert(measurement);
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

    public Measurement getMeasurement(ObjectId id){return measurementRepository.findOne(id);}

    public void deleteMeasurement(Measurement measurement){measurementRepository.delete(measurement);}

    public Measurement save(Measurement measurement){return measurementRepository.save(measurement);}

    public MeasurementAvg MeasurementAvgOneDay(int cityId){return getAverageValues(cityId, yesterday());}

    public MeasurementAvg MeasurementAvgOneWeek(int cityId){return getAverageValues(cityId, oneWeek());}

    public MeasurementAvg MeasurementAvgTwoWeeks(int cityId){return getAverageValues(cityId, twoWeeks());}

    private MeasurementAvg getAverageValues(int cityId, Date date){
        double totalTemp = 0;
        double totalPress = 0;
        double totalHum = 0;
        int count = 0;
        List<Measurement> measurements = measurementRepository.findByCityIdAndAndSaveTimeGreaterThan(cityId, date);

        for (Measurement measurement : measurements) {
            totalTemp += measurement.getTemperature();
            totalPress += measurement.getPressure();
            totalHum += measurement.getHumidity();
            count++;
        }
        double avgTemp = Math.round(totalTemp/count*100)/100.0d;
        double avgPress = Math.round(totalPress/count*100)/100.0d;
        double avgHum = Math.round(totalHum/count*100)/100.0d;

        MeasurementAvg res = new MeasurementAvg(avgTemp, avgPress, avgHum);
        res.setCityId(cityId);
        res.setCityName(cityService.getCityByCityId(cityId).getCityName());
        return res;
    }

    public Measurement getActualMeasurement(int cityId){
        return measurementRepository.findFirsByCityIdOrderBySaveTimeDesc(cityId);
    }

    private Date yesterday() {
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    private Date oneWeek(){
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    private Date twoWeeks(){
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -14);
        return cal.getTime();
    }

    //Since mongoDB 3.6 agragation functions has been edited - it needs cursor
    //And cursor needs springboot version 1.5.10, I got 1.3.5 - problem...
    //Test wont run, a lot of refactoring etc...
    //So I solved it in MeasurementRepository by creating query
    private MeasurementAvg getAverageValuesMongo(int cityId, Date date){
        GroupOperation group = Aggregation.group()
                .avg("humidity").as("humidityAvg")
                .avg("pressure").as("pressureAvg")
                .avg("temperature").as("temperatureAvg");

        MatchOperation match = Aggregation.match(new Criteria("saveTime").gt(date).and("cityID").is(cityId));
        ProjectionOperation projection = Aggregation.project("temperatureAvg", "humidityAvg", "pressureAvg");
        Aggregation aggregation = Aggregation.newAggregation(match, group, projection);

        //result to list
        AggregationResults<MeasurementAvg> groupResults
                = mongoTemplate.aggregate(aggregation, "measurement", MeasurementAvg.class);

        //return Object
        return groupResults.getUniqueMappedResult();
    }




}
