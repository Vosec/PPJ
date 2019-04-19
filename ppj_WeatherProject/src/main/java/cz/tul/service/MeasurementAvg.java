package cz.tul.service;

public class MeasurementAvg {
    private double temperatureAvg;
    private double pressureAvg;
    private double humidityAvg;
    private String cityName;
    private int cityId;

    public MeasurementAvg() {
    }

    public MeasurementAvg(double temperatureAvg, double pressureAvg, double humidityAvg) {
        this.temperatureAvg = temperatureAvg;
        this.pressureAvg = pressureAvg;
        this.humidityAvg = humidityAvg;
    }

    public double getTemperatureAvg() {
        return temperatureAvg;
    }

    public void setTemperatureAvg(double temperatureAvg) {
        this.temperatureAvg = temperatureAvg;
    }

    public double getPressureAvg() {
        return pressureAvg;
    }

    public void setPressureAvg(double pressureAvg) {
        this.pressureAvg = pressureAvg;
    }

    public double getHumidityAvg() {
        return humidityAvg;
    }

    public void setHumidityAvg(double humidityAvg) {
        this.humidityAvg = humidityAvg;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
