package org.ibolar;

import org.ibolar.gui.MainScreen;
import org.ibolar.service.WeatherApp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){

                new MainScreen().setVisible(true);
                WeatherApp weatherApp = new WeatherApp();
                weatherApp.getLocationData("istanbul");
            }
        });
    }
}