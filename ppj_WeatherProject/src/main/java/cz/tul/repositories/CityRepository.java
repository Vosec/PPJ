package cz.tul.repositories;

import cz.tul.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    List<City> findByCityName(@Param("cityname") String cityname);
    City findByCityId(@Param("cityid") int cityId);

    //zkouška query :)
    @Query("select c from City as c where state.stateName=:stateName")
    List<City> findByStateName(@Param("stateName") String stateName);

}
