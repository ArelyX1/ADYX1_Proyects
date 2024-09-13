package org.example.NOdos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;



public class NodePanelv3 extends JPanel {
    private BufferedImage backgroundImage;
    public NodePanelv3() {
        try {
            // Load the background image (update the path to your image)
            backgroundImage = ImageIO.read(new File("src/main/java/org/example/NOdos/planisferio-mapa-del-mundo-3-scaled.jpg")); // Change this to your image path
        } catch (IOException e) {
            e.printStackTrace();
            // Optionally set a default background color if the image fails to load
            setBackground(Color.LIGHT_GRAY);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the background image
        if (backgroundImage != null) {
            int backgroundWidth = getWidth();  // Ancho del panel
            int backgroundHeight = getHeight(); // Alto del panel
            g.drawImage(backgroundImage, 0, 0, backgroundWidth, backgroundHeight, this);
        }
    }
}
