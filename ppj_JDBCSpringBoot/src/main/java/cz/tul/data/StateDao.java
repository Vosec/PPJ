package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StateDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(State state) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("statename", state.getStateName());

        return jdbc.update("insert into states (statename) values (:statename)", params) == 1;
    }
    public boolean exists(String stateName) {
        return jdbc.queryForObject("select count(*) from states where stateName=:stateName",
                new MapSqlParameterSource("statename", stateName), Integer.class) > 0;
    }

    public List<State> getAllStates() {
        return jdbc.query("select * from states", BeanPropertyRowMapper.newInstance(State.class));
    }

    public void deleteStates() {
        jdbc.getJdbcOperations().execute("DELETE FROM states");
        jdbc.getJdbcOperations().execute("DELETE FROM cities");
    }


}
