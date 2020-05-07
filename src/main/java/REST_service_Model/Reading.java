package REST_service_Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Reading implements Serializable {

    int id;
    double temperature;
    double humidity;
    double illumination;
    double averageValue;
    double usedElectricity;
    Date created;
    Date updated;

   public Reading(double temperature, double humidity, double illumination){
        this.temperature = temperature;
        this.humidity = humidity;
        this.illumination = illumination;
   }

    public Reading(){

    }

    public double getUsedElectricity() {
        return usedElectricity;
    }

    public void setUsedElectricity(double usedElectricity) {
        this.usedElectricity = usedElectricity;
    }

    public double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(double averageValue) {
        this.averageValue = averageValue;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getIllumination() {
        return illumination;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setIllumination(double illumination) {
        this.illumination = illumination;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
