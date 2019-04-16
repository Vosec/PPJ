package cz.tul.repositories;

import cz.tul.model.Measurement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends MongoRepository<Measurement, ObjectId> {

    List<Measurement> findByCityName(String cityName);
    List<Measurement> findByCityId(int cityId);
    void deleteAll();
    List<Measurement> findAll();


}
