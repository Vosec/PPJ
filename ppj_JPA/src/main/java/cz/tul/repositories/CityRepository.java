package cz.tul.repositories;

import cz.tul.data.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    public List<City> findByCityName(@Param("cityname") String cityname);

}
