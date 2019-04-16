package cz.tul.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="states")
public class State {

    @Id
    @Column(name="statename")
    private String stateName;

    /**
    @JsonIgnore
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private List<City> cities;
    **/
    public State(String stateName) {
        this.stateName = stateName;
    }

    public State() {
    }

    /**
    public List<City> getCities() {
        return cities;
    }
    **/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(stateName, state.stateName);
    }

    /**
    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    **/

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }

    @Override
    public String toString() {
        return "State: " + stateName;
    }
}
