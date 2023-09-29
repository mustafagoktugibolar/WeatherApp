package org.ibolar.gui;

import org.ibolar.help.Helper;

import javax.swing.*;
import java.awt.*;


public class MainScreen extends JFrame {

    public MainScreen(){
        super("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 650);
        setResizable(false);
        //setLocation((Config.SCREEN_WIDTH - 500) / 2, (Config.SCREEN_HEIGHT - 700) / 2);
        setLocationRelativeTo(null);
        setLayout(null);
        addGuiComponents();

    }

    private void addGuiComponents() {
        //Text field
        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(15, 20, 421, 47);
        searchTextField.setFont(new Font("DialogInput", Font.PLAIN, 24));
        add(searchTextField);

        //Search Button
        JButton searchButton = new JButton(Helper.loadImage("src/assets/search.png"));
        searchButton.setBounds(440, 22, 45, 43);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(searchButton);

        //Weather Image
        JLabel weatherImage = new JLabel(Helper.loadImage("src/assets/sun.png"));
        weatherImage.setBounds(186, 130, 128,128);
        add(weatherImage);



        // Weather text
        JLabel weatherText = new JLabel("25 C");
        weatherText.setBounds(0, 290, 500, 54);
        weatherText.setFont(new Font("DialogInput", Font.BOLD, 48));
        weatherText.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherText);


        // WeatherStatus text
        JLabel weatherStatus = new JLabel("Sunny");
        weatherStatus.setBounds(0, 340, 500, 54);
        weatherStatus.setFont(new Font("DialogInout", Font.PLAIN, 30));
        weatherStatus.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherStatus);

        //Humidity
        JLabel humidityImage = new JLabel(Helper.loadImage("src/assets/humidity.png"));
        humidityImage.setBounds(20, 475, 64,64);
        add(humidityImage);
    }


}

