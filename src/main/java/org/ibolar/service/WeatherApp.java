package org.ibolar.service;

import org.ibolar.help.Helper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.util.Scanner;

public class WeatherApp {
    public static JSONObject getWeatherData(String locationName){
        //locationName = locationName.replaceAll(" ", "+");
        JSONArray locationData = getLocationData(locationName);
        JSONObject location = (JSONObject) locationData.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");

        String urlStr = "https://api.open-meteo.com/v1/forecast?latitude=" +latitude+"&longitude=" +longitude+"&hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m&timezone=auto";
        try {
            HttpURLConnection connection = Helper.fetchApiResponse(urlStr, "GET");

            if(connection.getResponseCode() != 200){
                System.out.println("Not connected to api");
                return null;
            }
            else {
                StringBuilder resultJson = new StringBuilder();
                System.out.println(resultJson.toString());
                Scanner scanner = new Scanner(connection.getInputStream());

                // read and store the resulting json data into our string builder
                while(scanner.hasNext()){
                    resultJson.append(scanner.nextLine());
                }

                // close scanner
                scanner.close();

                // close url connection
                connection.disconnect();

                // parse through our data
                JSONParser parser = new JSONParser();
                JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                // retrieve hourly data
                JSONObject hourly = (JSONObject) resultJsonObj.get("hourly");

                // we want to get the current hour's data
                // so we need to get the index of our current hour
                JSONArray time = (JSONArray) hourly.get("time");
                int index = findIndexOfCurrentTime(time);

                //Get temperature
                JSONArray temperatureData = (JSONArray) hourly.get("temperature_2m");
                double temperature = (double) temperatureData.get(index);

                //Get Weather Code
                JSONArray weatherCode = (JSONArray) hourly.get("weathercode");
                String weatherContidion = Helper.convertWeatherCode((long) weatherCode.get(index));

                //Get windspeed
                JSONArray windspeedData = (JSONArray) hourly.get("windspeed_10m");
                double windspeed = (double) windspeedData.get(index);

                //Get Humidity
                JSONArray humidityData = (JSONArray) hourly.get("relativehumidity_2m");
                long humidity = (long) humidityData.get(index);

                JSONObject weatherData = new JSONObject();
                weatherData.put("temperature", temperature);
                weatherData.put("weatherCondition", weatherContidion);
                weatherData.put("humidity", humidity);
                weatherData.put("windspeed", windspeed);

                return weatherData;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray getLocationData(String locationName){
        locationName = locationName.replaceAll(" ", "+");

        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + locationName + "&count=10&language=en&format=json";

        try {
            HttpURLConnection connection = Helper.fetchApiResponse(url,"GET");
            if(connection.getResponseCode() != 200){
                System.out.println("Not connected to api");
                return null;
            }
            else {
                StringBuilder result = new StringBuilder();
                Scanner sc = new Scanner(connection.getInputStream());
                while (sc.hasNext()){
                    result.append(sc.nextLine());
                }
                sc.close();
                connection.disconnect();

                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(result));

                JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
                System.out.println(locationData.toString());
                return locationData;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }


//    private static int findIndexOfCurrentTime(JSONArray timeList){
//        String currentTime = Helper.getCurrentTime();
//        for (int i = 0; i < timeList.size(); i++) {
//            String time = (String) timeList.get(i);
//            if(time.equalsIgnoreCase(currentTime)){
//                return i;
//            }
//        }
//        return 0;
//    }
private static int findIndexOfCurrentTime(JSONArray timeList){
    if (timeList == null) {
        // Handle the case when timeList is null, e.g., return a default value or throw an exception.
        return -1; // or throw new IllegalArgumentException("timeList is null");
    }

    String currentTime = Helper.getCurrentTime();
    for (int i = 0; i < timeList.size(); i++) {
        String time = (String) timeList.get(i);
        if(time.equalsIgnoreCase(currentTime)){
            return i;
        }
    }
    return -1; // Return -1 to indicate that the current time was not found in the list.
}

}

