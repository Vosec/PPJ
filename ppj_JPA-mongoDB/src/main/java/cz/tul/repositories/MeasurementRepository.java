package cz.tul.repositories;

import cz.tul.model.Measurement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MeasurementRepository extends MongoRepository<Measurement, ObjectId> {

    List<Measurement> findByCityName(String cityName);


}
