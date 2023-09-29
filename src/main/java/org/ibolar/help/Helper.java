package org.ibolar.help;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Helper {
    public static ImageIcon loadImage(String resourcePath){

        try {
            BufferedImage image = ImageIO.read(new File(resourcePath));
            return new ImageIcon(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
