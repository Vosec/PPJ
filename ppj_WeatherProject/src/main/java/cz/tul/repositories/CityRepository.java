package cz.tul.repositories;

import cz.tul.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    City findByCityId(@Param("cityid") int cityId);

    //trying some query :)
    @Query("select c from City as c where state.name=:name")
    List<City> findByStateName(@Param("name") String name);

}
