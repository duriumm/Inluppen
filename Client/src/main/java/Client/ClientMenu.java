package Client;

import Models.Reading;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class ClientMenu {



    public static void getReportsFromDB(int choiceInput){
        if(choiceInput == 1){   // HUMIDITY REPORT // HUMIDITY REPORT // HUMIDITY REPORT
            //////////////////////////////////////////////////////////////////////
            final String uri = "http://localhost:8080/databaseGetHumidityReport";
            RestTemplate restTemplate = new RestTemplate();
            Reading[] resultList = restTemplate.getForObject(uri, Reading[].class);

            double averageValue = 0;
            for(Reading reading : resultList){
                averageValue += reading.getHumidity();
                System.out.println("Id: "+reading.getId()+", Humidity: "+
                        reading.getHumidity()+", Created: "+reading.getCreated()+
                        ", Last updated: "+reading.getUpdated());
            }
            averageValue = averageValue / resultList.length;
            System.out.println("Average Humidity for last week: "+averageValue);
        }
        else if(choiceInput == 2){ // TEMPERATURE REPORT // TEMPERATURE REPORT // TEMPERATURE REPORT
            ///////////////////////////////////////////////////////////////////////
            final String uri = "http://localhost:8080/databaseGetTemperatureReport";
            RestTemplate restTemplate = new RestTemplate();
            Reading[] resultList = restTemplate.getForObject(uri, Reading[].class);

            double averageValue = 0;
            for(Reading reading : resultList){
                averageValue += reading.getTemperature();
                System.out.println("Id: "+reading.getId()+", Temperature: "+
                        reading.getTemperature()+", Created: "+reading.getCreated()+
                        ", Last updated: "+reading.getUpdated());
            }
            averageValue = averageValue / resultList.length;
            System.out.println("Average Temperature for last week: "+averageValue);
        }
        else if(choiceInput == 3){ // ILLUMINATION REPORT // ILLUMINATION REPORT // ILLUMINATION REPORT
            //////////////////////////////////////////////////////////////////////////
            final String uri = "http://localhost:8080/databaseGetIlluminationReport";
            RestTemplate restTemplate = new RestTemplate();
            Reading[] resultList = restTemplate.getForObject(uri, Reading[].class);

            double averageValue = 0;
            for(Reading reading : resultList){
                averageValue += reading.getIllumination();
                System.out.println("Id: "+reading.getId()+", Illumination: "+
                        reading.getIllumination()+", Created: "+reading.getCreated()+
                        ", Last updated: "+reading.getUpdated());
            }
            averageValue = averageValue / resultList.length;
            System.out.println("Average Illumination for last week: "+averageValue);
        }
        else if(choiceInput == 4){ // ENERGY COST REPORT // ENERGY COST REPORT // ENERGY COST REPORT
            //////////////////////////////////////////////////////////////////////////
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Energycost to set (kr/kWh): ");
            double pricePerKwH = scanner.nextDouble();
            //String stringDataToSet = Double.toString(dataToSet);

            final String uri = "http://localhost:8080/databaseGetEnergyCostReport";
            RestTemplate restTemplate = new RestTemplate();
            Reading[] resultList = restTemplate.getForObject(uri, Reading[].class);


            int amountOfKwhUsed = 0;
            for(Reading reading : resultList){
                amountOfKwhUsed += reading.getUsedElectricity();
            }
            double totalEnergyCostForLastWeek = pricePerKwH * amountOfKwhUsed;

            System.out.println("Total energy cost for last week: "+totalEnergyCostForLastWeek+" kr.");
        }

    }
    public static void getDataFromGreenhouse(String choiceInput){
        if(choiceInput.equalsIgnoreCase("1")){
            final String uri = "http://localhost:8080/getCurrentHumidity"; // denna går till Controller
            RestTemplate restTemplate = new RestTemplate();
            Double humidityResult = restTemplate.getForObject(uri, Double.class);
            System.out.println("Current Humidity from greenhouse: "+humidityResult);
        }
        else if(choiceInput.equalsIgnoreCase("2")){
            final String uri = "http://localhost:8080/getCurrentTemperature"; // denna går till Controller
            RestTemplate restTemplate = new RestTemplate();
            Double temperatureResult = restTemplate.getForObject(uri, Double.class);
            System.out.println("Current Temperature from greenhouse: "+temperatureResult);
        }
        else if(choiceInput.equalsIgnoreCase("3")){
            final String uri = "http://localhost:8080/getCurrentIllumination"; // denna går till Controller
            RestTemplate restTemplate = new RestTemplate();
            Double illuminationResult = restTemplate.getForObject(uri, Double.class);
            System.out.println("Current Illmunination from greenhouse: "+illuminationResult);
        }
        else if(choiceInput.equalsIgnoreCase("4")){
            final String uri = "http://localhost:8080/getCurrentEnergyconsumption"; // denna går till Controller
            RestTemplate restTemplate = new RestTemplate();
            Double energyConsumptionResult = restTemplate.getForObject(uri, Double.class);
            System.out.println("Current Energy consumption from greenhouse: "+energyConsumptionResult);
        }
        else if(choiceInput.equalsIgnoreCase("5")){
            final String uri = "http://localhost:8080/getAlldataFromGreenhouse"; // denna går till Controller
            RestTemplate restTemplate = new RestTemplate();
            String allDataFromGreenhouse = restTemplate.getForObject(uri, String.class);
            System.out.println(allDataFromGreenhouse); // skickar all data fårn greenhouse
        }
        else{
            System.out.println("Sorry you entered the wrong info, try again!");
        }

    }

    public static void setDataIntoGreenhouse(int choiceInput){
        Scanner scanner = new Scanner(System.in);

        if(choiceInput == 1){

            System.out.println("Enter Humidity to set: ");
            double dataToSet = scanner.nextDouble();
            String stringDataToSet = Double.toString(dataToSet);

            final String uri = "http://localhost:8080/setCurrentHumidity"; // denna går till Controller
            RestTemplate restTemplate = new RestTemplate();
            String humidityResult = restTemplate.postForObject(uri, stringDataToSet, String.class);
            System.out.println("New humidity after update: "+humidityResult);
        }
        else if(choiceInput == 2){

            System.out.println("Enter Temperature to set: ");
            double dataToSet = scanner.nextDouble();
            String stringDataToSet = Double.toString(dataToSet);

            final String uri = "http://localhost:8080/setCurrentTemperature"; // denna går till Controller
            RestTemplate restTemplate = new RestTemplate();
            String temperatureResult = restTemplate.postForObject(uri, stringDataToSet, String.class);
            System.out.println("New temperature after update: "+temperatureResult);
        }
        else if(choiceInput == 3){

            System.out.println("Enter Illumination to set: ");
            double dataToSet = scanner.nextDouble();
            String stringDataToSet = Double.toString(dataToSet);

            final String uri = "http://localhost:8080/setCurrentIllumination"; // denna går till Controller
            RestTemplate restTemplate = new RestTemplate();
            String illuminationResult = restTemplate.postForObject(uri, stringDataToSet ,String.class);
            System.out.println("Current Illmunination from greenhouse: "+illuminationResult);
        }
        else{
            System.out.println("Sorry you entered the wrong info, try again!");
        }
    }

    public static void sendGreenhouseReadingsToDb(){
        final String uri = "http://localhost:8080/insertGreenhouseReadingsToDB";
        RestTemplate restTemplate = new RestTemplate();
        String greenhouseData = restTemplate.getForObject(uri, String.class);
        System.out.println(greenhouseData);
    }


    public static void main(String[] args) {
        while(true){
            System.out.println("--Welcome to the menu--\n\n" +
                    "1. Get readings from Greenhouse.\n" +
                    "2. Enter new value into specific reading in Greenhouse.\n" +
                    "3. Get last weeks reports.\n" +
                    "4. Log current Greenhouse readings to database.");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1){ // GET readings from Greenhouse
                System.out.println("Enter the number of what data you would like to GET from the greenhouse\n" +
                        "1. Humidity\n" +
                        "2. Temperature\n" +
                        "3. Illumination\n" +
                        "4. Energy consumption\n" +
                        "5. Get ALL of above data from greenhouse");
                String dataToGet = scanner.nextLine();
                getDataFromGreenhouse(dataToGet);
            }
            else if(choice == 2){ // SET values to greenhouse
                System.out.println("Enter the number of what data you would like to UPDATE in the greenhouse\n" +
                        "1. Humidity\n" +
                        "2. Temperature\n" +
                        "3. Illumination\n");
                int choiceInput = scanner.nextInt();
                setDataIntoGreenhouse(choiceInput);

            }
            else if(choice == 3){ // GET reports from DB
                System.out.println("What kind of report would you like to see?\n" +
                        "1. Humidity report\n" +
                        "2. Temperature report\n" +
                        "3. Illumination report\n" +
                        "4. Energy cost report");
                int choiceInput = scanner.nextInt();
                getReportsFromDB(choiceInput);
            }
            else if(choice == 4) { // SEND GREENHOUSE READINGS TO DB
                sendGreenhouseReadingsToDb();
            }
            else{
                System.out.println("FEL haha");
            }
        }
    }
}
