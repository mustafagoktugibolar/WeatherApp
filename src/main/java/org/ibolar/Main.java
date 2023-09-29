package org.ibolar;

import org.ibolar.gui.MainScreen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){

                new MainScreen().setVisible(true);

            }
        });
    }
}