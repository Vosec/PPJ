package cz.tul.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import java.util.Date;

@Document(collection = "measurement")
public class Measurement {

    @Id
    private ObjectId id;

    private double temperature;
    private double humidity;
    private double pressure;
    private String cityName;
    //Pro tu 14ti denni expiraci
    private Date saveTime = new Date();

    private int cityId;

    public Measurement() {
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Measurement(int cityId, double temperature, double humidity, double pressure, String cityName, Date saveTime) {
        this.cityId = cityId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.cityName = cityName;
        this.saveTime = saveTime;
    }

    public Measurement(int cityId, double temperature, double humidity, double pressure, String cityName) {
        this.cityId = cityId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    //vracim hexString, abych v json http response viděl jen to Id a ne celej object (jinak se to id neukazalo)
    public String getId() {
        return id.toHexString();
    }

    public ObjectId getIdObj(){return id;}

    public void setId(ObjectId id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "objectId=" + id +
                "cityId=" + cityId +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", cityName='" + cityName + '\'' +
                ", saveTime=" + saveTime +
                '}';
    }
}
