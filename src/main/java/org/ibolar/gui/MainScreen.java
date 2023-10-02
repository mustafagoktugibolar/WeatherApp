package org.ibolar.gui;

import com.google.gson.Gson;
import org.ibolar.help.Config;
import org.ibolar.help.Helper;
import org.ibolar.service.WeatherApp;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;


public class MainScreen extends JFrame {
    private JSONObject weatherData;

    public MainScreen(){
        super("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        setResizable(false);
        //setLocation((Config.SCREEN_WIDTH - 500) / 2, (Config.SCREEN_HEIGHT - 700) / 2);
        setLocationRelativeTo(null);
        setLayout(null);
        addGuiComponents();

    }

    private void addGuiComponents() {
        //Text field
        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(15, 20, 380, 30);
        searchTextField.setFont(new Font("DialogInput", Font.PLAIN, 24));
        add(searchTextField);


        //Weather Image
        JLabel weatherImage = new JLabel(Helper.loadImage("src/assets/sun.png"));
        weatherImage.setBounds(161, 120, 128,128);
        add(weatherImage);


        // Weather text
        JLabel weatherText = new JLabel("25 C");
        weatherText.setBounds(0, 280, Config.SCREEN_WIDTH, 54);
        weatherText.setFont(new Font("DialogInput", Font.BOLD, 48));
        weatherText.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherText);


        // WeatherStatus text
        JLabel weatherStatus = new JLabel("Sunny");
        weatherStatus.setBounds(0, 330, Config.SCREEN_WIDTH, 54);
        weatherStatus.setFont(new Font("DialogInout", Font.PLAIN, 30));
        weatherStatus.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherStatus);

        //Humidity image
        JLabel humidityImage = new JLabel(Helper.loadImage("src/assets/humidity.png"));
        humidityImage.setBounds(20, 430, 64,64);
        add(humidityImage);

        //Humidity text
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(85, 432, 85, 55);
        humidityText.setFont(new Font("DialogInput", Font.PLAIN, 16));
        add(humidityText);

        //Windspeed image
        JLabel windspeedImage = new JLabel(Helper.loadImage("src/assets/wind.png"));
        windspeedImage.setBounds(270, 430, 64,64);
        add(windspeedImage);

        //windspeed text
        JLabel windspeedText = new JLabel("<html><b>Windspeed<b> 20km/h</html>");
        windspeedText.setBounds(337, 431, 90,55);
        windspeedText.setFont(new Font("DialogInput", Font.PLAIN, 16));
        add(windspeedText);

        //Search Button
        JButton searchButton = new JButton(Helper.loadImage("src/assets/search.png"));
        searchButton.setBounds(402, 20, 30, 30);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(e -> {
            String locationInput = searchTextField.getText();
            if(locationInput.replaceAll("\\s", "").length() <= 0){
                return;
            }

            //weather data
            weatherData = WeatherApp.getWeatherData(locationInput);

            String weatherCondition = (String) weatherData.get("weatherCondition");

            // depending on the condition, we will update the weather image that corresponds with the condition
            switch(weatherCondition){
                case "Clear":
                    weatherImage.setIcon(Helper.loadImage("src/assets/clear.png"));
                    break;
                case "Cloudy":
                    weatherImage.setIcon(Helper.loadImage("src/assets/cloudy.png"));
                    break;
                case "Rain":
                    weatherImage.setIcon(Helper.loadImage("src/assets/rain.png"));
                    break;
                case "Snow":
                    weatherImage.setIcon(Helper.loadImage("src/assets/snow.pngImage"));
                    break;
            }

            // update temperature text
            double temperature = (double) weatherData.get("temperature");
            weatherText.setText(temperature + " C");

            // update weather condition text
            weatherStatus.setText(weatherCondition);

            // update humidity text
            long humidity = (long) weatherData.get("humidity");
            humidityText.setText("<html><b>Humidity</b> " + humidity + "%</html>");

            // update windspeed text
            double windspeed = (double) weatherData.get("windspeed");
            windspeedText.setText("<html><b>Windspeed</b> " + windspeed + "km/h</html>");

        });
        add(searchButton);


    }


}

