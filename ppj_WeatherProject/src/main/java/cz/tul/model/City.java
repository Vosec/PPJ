package cz.tul.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "cityname")
    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="statename")
    private State state;

    //for city id from API
    @Column(name = "cityid")
    private int cityId;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City(String name, State state, int cityId) {
        this.name = name;
        this.state = state;
        this.cityId = cityId;
    }

    public City() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getStateName() {
        return state.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getId() == city.getId() &&
                Objects.equals(getName(), city.getName()) &&
                Objects.equals(getState(), city.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getState());
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "#City: id: " + id + ", cityName: " + name + ", State: " + state.getName();
    }
}
