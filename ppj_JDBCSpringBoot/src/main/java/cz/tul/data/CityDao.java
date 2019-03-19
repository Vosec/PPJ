package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CityDao {
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public List<City> getCities() {
        return jdbc
                .query("select * from cities, states where states.statename=cities.statename",
                        (ResultSet rs, int rowNum) -> {
                            State state = new State();
                            state.setStateName(rs.getString("statename"));

                            City city = new City();
                            city.setId(rs.getInt("id"));
                            city.setName(rs.getString("cityname"));
                            city.setState(state);

                            return city;
                        }
                );
    }
    public boolean update(City city) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                city);

        return jdbc.update("update cities set cityname=:cityname where id=:id", params) == 1;
    }


    public boolean create(City city) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                city);

        return jdbc
                .update("insert into cities (statename, cityname) values (:stateName, :cityName)",
                        params) == 1;
    }

    @Transactional
    public int[] create(List<City> cities) {

        SqlParameterSource[] params = SqlParameterSourceUtils
                .createBatch(cities.toArray());

        return jdbc
                .batchUpdate("insert into cities (statename, cityname) values (:stateName, :cityName)", params);
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        return jdbc.update("delete from cities where id=:id", params) == 1;
    }

    public City getCity(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from cities, states where cities.statename=states.statename and and id=:id", params,
                new RowMapper<City>() {

                    public City mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        State state = new State();
                        state.setStateName(rs.getString("statename"));


                        City city = new City();
                        city.setId(rs.getInt("id"));
                        city.setName(rs.getString("cityname"));
                        city.setState(state);

                        return city;
                    }

                });
    }

}
