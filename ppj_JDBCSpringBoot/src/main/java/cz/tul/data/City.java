package cz.tul.data;

public class City {
    private int id;
    private String cityName;
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
    public String toString() {
        return "#City: id: " + id + ", cityName: " + cityName + ", State: " + state.getStateName();
    }
}
