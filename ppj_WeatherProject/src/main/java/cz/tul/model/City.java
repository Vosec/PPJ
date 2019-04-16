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
    private String cityName;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="statename")
    private State state;

    //pro id získané z rest api
    @Column(name = "cityid")
    private int cityId;



    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City(int id, String cityName, State state, int cityId) {
        this.id = id;
        this.cityName = cityName;
        this.state = state;
        this.cityId = cityId;
    }
    public City(String cityName, State state, int cityId) {
        this.cityName = cityName;
        this.state = state;
        this.cityId = cityId;
    }

    public City() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
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
    public void setStateName(String stateName) {
        this.state.setStateName(stateName);
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

    public long getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "#City: id: " + id + ", cityName: " + cityName + ", State: " + state.getStateName();
    }
}
