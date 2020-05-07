package REST_service_Model.Controllers;

import REST_service_Model.Greenhouse;
import REST_service_Model.Reading;
import REST_service_Model.ReadingsDAO;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class Controller {

    ReadingsDAO readingsDAO = new ReadingsDAO();
    Greenhouse greenhouse = new Greenhouse();



    @RequestMapping(value = "/databaseGetHumidityReport", headers = "Accept=application/json")
    public ArrayList<Reading> humidityReportFromDB() throws SQLException, IOException, ClassNotFoundException {
        return readingsDAO.getReportsFromDB(1);
    }
    @RequestMapping(value = "/databaseGetTemperatureReport", headers = "Accept=application/json")
    public ArrayList<Reading> temperatureReportFromDB() throws SQLException, IOException, ClassNotFoundException {
        return readingsDAO.getReportsFromDB(2);
    }
    @RequestMapping(value = "/databaseGetIlluminationReport", headers = "Accept=application/json")
    public ArrayList<Reading> illuminationReportFromDB() throws SQLException, IOException, ClassNotFoundException {
        return readingsDAO.getReportsFromDB(3);
    }
    @RequestMapping(value = "/databaseGetEnergyCostReport", headers = "Accept=application/json")
    public ArrayList<Reading> energyCostReportFromDB() throws SQLException, IOException, ClassNotFoundException {
        return readingsDAO.getReportsFromDB(4);
    }


    @RequestMapping(value = "/getCurrentHumidity", headers = "Accept=application/json")
    public Double humidityFromGreenhouse() throws SQLException, IOException, ClassNotFoundException {
        return greenhouse.getHumidity();
    }
    @RequestMapping(value = "/getCurrentTemperature", headers = "Accept=application/json")
    public Double temperatureFromGreenhouse() throws SQLException, IOException, ClassNotFoundException {
        return greenhouse.getTemperature();
    }
    @RequestMapping(value = "/getCurrentIllumination", headers = "Accept=application/json")
    public Double illuminationFromGreenhouse() throws SQLException, IOException, ClassNotFoundException {
        return greenhouse.getIllumination();
    }
    @RequestMapping(value = "/getCurrentEnergyconsumption", headers = "Accept=application/json")
    public Double energyConsumptionFromGreenhouse() throws SQLException, IOException, ClassNotFoundException {
        return greenhouse.getEnergyConsumption();
    }
    @RequestMapping(value = "/getAlldataFromGreenhouse", headers = "Accept=application/json")
    public String allDataFromGreenhouse() throws SQLException, IOException, ClassNotFoundException {
        return greenhouse.getAllReadingsFromGreenHouse();
    }


    @PostMapping("/setCurrentHumidity")
    public String humidityIntoGreenhouse(@RequestBody String stringDataToSet) throws SQLException, IOException, ClassNotFoundException {
        Double doubleDataToSet = Double.parseDouble(stringDataToSet);
        greenhouse.setHumidity(doubleDataToSet);
        String humidityToSendBack = Double.toString(greenhouse.getHumidity());
        return humidityToSendBack;
    }
    @PostMapping("/setCurrentTemperature")
    public String temperatureIntoGreenhouse(@RequestBody String stringDataToSet) throws SQLException, IOException, ClassNotFoundException {
        Double doubleDataToSet = Double.parseDouble(stringDataToSet);
        greenhouse.setTemperature(doubleDataToSet);
        String temperatureToSendBack = Double.toString(greenhouse.getTemperature());
        return temperatureToSendBack;
    }
    @PostMapping("/setCurrentIllumination")
    public String illuminationIntoGreenhouse(@RequestBody String stringDataToSet) throws SQLException, IOException, ClassNotFoundException {
        Double doubleDataToSet = Double.parseDouble(stringDataToSet);
        greenhouse.setIllumination(doubleDataToSet);
        String illuminationToSendBack = Double.toString(greenhouse.getIllumination());
        return illuminationToSendBack;
    }

    @RequestMapping(value = "/insertGreenhouseReadingsToDB", headers = "Accept=application/json")
    public String greenhouseReadingsIntoDB() throws SQLException, IOException, ClassNotFoundException {
        return readingsDAO.enterGreenhouseReadingsIntoDB(greenhouse);
    }

}