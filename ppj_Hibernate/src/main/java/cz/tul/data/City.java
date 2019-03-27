package cz.tul.data;

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
    private String cityName;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="statename")
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City(int id, String cityName, State state) {
        this.id = id;
        this.cityName = cityName;
        this.state = state;
    }
    public City(String cityName, State state) {
        this.cityName = cityName;
        this.state = state;
    }

    public City() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStateName() {
        return state.getStateName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getId() == city.getId() &&
                Objects.equals(getCityName(), city.getCityName()) &&
                Objects.equals(getState(), city.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCityName(), getState());
    }

    @Override
    public String toString() {
        return "#City: id: " + id + ", cityName: " + cityName + ", State: " + state.getStateName();
    }
}
