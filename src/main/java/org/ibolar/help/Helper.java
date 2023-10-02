package org.ibolar.help;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
    private Helper(){

    }
    public static ImageIcon loadImage(String resourcePath){

        try {
            BufferedImage image = ImageIO.read(new File(resourcePath));
            return new ImageIcon(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static HttpURLConnection fetchApiResponse(String urlS, String setMethod){
        try{
            URL url = new URL(urlS);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(setMethod);
            connection.connect();
            return connection;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentTime(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");
        String formattedDateTime = currentTime.format(formatter);

        return formattedDateTime;
    }

    public static String convertWeatherCode(long weatherCode){
        String condition = "";
        if(weatherCode == 0L){
            condition = "Clear";
        }
        else if (weatherCode > 0L && weatherCode <= 3L){
            condition = "Cloudy";
        }
        else if ((weatherCode > 51L && weatherCode <=67L)
                || (weatherCode >= 80L && weatherCode <= 99L)){
            condition = "Rain";
        }
        else if(weatherCode >= 71L && weatherCode <= 77L){
            condition = "Snow";
        }
        return condition;
    }

}
