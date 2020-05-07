package REST_service_Model;

public class Greenhouse {

    double humidity;
    double temperature;
    double illumination;
    double energyConsumption;

    public Greenhouse(double humidity, double temperature, double illumination){
        this.humidity = humidity;
        this.temperature = temperature;
        this.illumination = illumination;
    }

    public Greenhouse(){
        setStarterValues();
    }

    public void setStarterValues(){
        humidity = 35;
        temperature = 17.4;
        illumination = 70;
        energyConsumption = (humidity+temperature+illumination) / 3;
    }
    public String getAllReadingsFromGreenHouse(){
        String returnString = "--Greenhouse current readings--\nHumidity: "+humidity+
                ", Temperature: "+temperature+", Illumination: "+illumination+", Energy consumption: "+
                getEnergyConsumption();
        return returnString;
    }

    public double getEnergyConsumption() {
        energyConsumption = (humidity+temperature+illumination) / 3;
        return energyConsumption;
    }

    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getIllumination() {
        return illumination;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setIllumination(double illumination) {
        this.illumination = illumination;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
