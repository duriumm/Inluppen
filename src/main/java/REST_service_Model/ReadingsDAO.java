package REST_service_Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Properties;


import java.util.Date;

public class ReadingsDAO {

    ArrayList<Reading> listOfReadings = new ArrayList<>();

    public ReadingsDAO(){

    }
    public String enterGreenhouseReadingsIntoDB(Greenhouse greenhouseInput) throws IOException,
            ClassNotFoundException, SQLException {

        Properties p = new Properties();
        p.load(new FileInputStream("C:\\Users\\lasse\\IdeaProjects\\Inluppen\\src\\main\\java\\REST_service_Model\\settings.properties"));

        Class.forName("com.mysql.cj.jdbc.Driver");
        ResultSet rs = null;

        double temperature = greenhouseInput.getTemperature();
        double humidity = greenhouseInput.getHumidity();
        double illumination = greenhouseInput.getIllumination();
        double energyUsed = greenhouseInput.getEnergyConsumption();
        boolean isManualInput = true;


        try (
                Connection con = DriverManager.getConnection(
                        p.getProperty("connectionString"),
                        p.getProperty("username"),
                        p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement("" +
                        "INSERT INTO readings(temperature, humidity, illumination, usedElectricityKw, inputIsManual) " +
                        "VALUES (?, ?, ?, ?, ?)")) {
            stmt.setDouble(1, temperature);
            stmt.setDouble(2, humidity);
            stmt.setDouble(3, illumination);
            stmt.setDouble(4, energyUsed);
            stmt.setBoolean(5, isManualInput);
            stmt.executeUpdate();
        }
        String returnString = "--Data inserted from Greenhouse to database--\n\n" +
                "Humidity: "+humidity+", Temperature: "+temperature+", Illumination: "+illumination+
                ", Energy used in kWh: "+energyUsed+", Is input manual? = "+isManualInput;
        return returnString;
    }

    public ArrayList<Reading> getReportsFromDB(int input) throws ClassNotFoundException,
            SQLException, IOException {

        listOfReadings.clear();
        Properties p = new Properties();
        p.load(new FileInputStream("C:\\Users\\lasse\\IdeaProjects\\Inluppen\\src\\main\\java\\REST_service_Model\\settings.properties"));

        Class.forName("com.mysql.cj.jdbc.Driver");
        ResultSet rs = null;

        if (input == 1) { // INPUT 1 BETYDER FUKTIGHET

            try (
                    Connection con = DriverManager.getConnection(
                            p.getProperty("connectionString"),
                            p.getProperty("username"),
                            p.getProperty("password"));
                    PreparedStatement stmt = con.prepareStatement("SELECT * FROM readings ORDER BY id DESC LIMIT 7")) {
                //stmt.setString(1, itemNameInput);
                rs = stmt.executeQuery();

                while (rs.next()) {

                    int readingId = rs.getInt("readings.id");
                    double humidity = rs.getDouble("readings.humidity");
                    Date created = rs.getDate("readings.created");
                    Date updated = rs.getDate("readings.updated");

                    Reading newReading = new Reading();
                    newReading.setId(readingId);
                    newReading.setHumidity(humidity);
                    newReading.setCreated(created);
                    newReading.setUpdated(updated);
                    listOfReadings.add(newReading);
                }
            }
        }
        else if (input == 2) { // INPUT 2 BETYDER TEMPERATUR
            try (
                    Connection con = DriverManager.getConnection(
                            p.getProperty("connectionString"),
                            p.getProperty("username"),
                            p.getProperty("password"));
                    PreparedStatement stmt = con.prepareStatement("SELECT * FROM readings ORDER BY id DESC LIMIT 7")) {
                //stmt.setString(1, itemNameInput);
                rs = stmt.executeQuery();

                while (rs.next()) {

                    int readingId = rs.getInt("readings.id");
                    double temperature = rs.getDouble("readings.temperature");
                    Date created = rs.getDate("readings.created");
                    Date updated = rs.getDate("readings.updated");

                    Reading newReading = new Reading();
                    newReading.setId(readingId);
                    newReading.setTemperature(temperature);
                    newReading.setCreated(created);
                    newReading.setUpdated(updated);

                    listOfReadings.add(newReading);
                }
            }
        }
        else if (input == 3) { // INPUT 3 BETYDER LJUSSTYRKA
            try (
                    Connection con = DriverManager.getConnection(
                            p.getProperty("connectionString"),
                            p.getProperty("username"),
                            p.getProperty("password"));
                    PreparedStatement stmt = con.prepareStatement("SELECT * FROM readings ORDER BY id DESC LIMIT 7")) {
                //stmt.setString(1, itemNameInput);
                rs = stmt.executeQuery();

                while (rs.next()) {

                    int readingId = rs.getInt("readings.id");
                    double illumination = rs.getDouble("readings.illumination");
                    Date created = rs.getDate("readings.created");
                    Date updated = rs.getDate("readings.updated");

                    Reading newReading = new Reading();
                    newReading.setId(readingId);
                    newReading.setIllumination(illumination);
                    newReading.setCreated(created);
                    newReading.setUpdated(updated);

                    listOfReadings.add(newReading);
                }
            }
        }
        else if (input == 4) { // INPUT 4 BETYDER ENERGYCOSTRAPPORT
            try (
                    Connection con = DriverManager.getConnection(
                            p.getProperty("connectionString"),
                            p.getProperty("username"),
                            p.getProperty("password"));
                    PreparedStatement stmt = con.prepareStatement("SELECT * FROM readings ORDER BY id DESC LIMIT 7")) {
                //stmt.setString(1, itemNameInput);
                rs = stmt.executeQuery();

                while (rs.next()) {

                    int readingId = rs.getInt("readings.id");
                    double usedElectricity = rs.getDouble("readings.usedElectricityKw");
                    Date created = rs.getDate("readings.created");
                    Date updated = rs.getDate("readings.updated");

                    Reading newReading = new Reading();
                    newReading.setId(readingId);
                    newReading.setUsedElectricity(usedElectricity);
                    newReading.setCreated(created);
                    newReading.setUpdated(updated);

                    listOfReadings.add(newReading);
                }
            }
        }
        return listOfReadings;
    }
}
