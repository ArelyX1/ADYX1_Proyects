package org.example.NOdos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class NodePanel extends JPanel {
    private BufferedImage backgroundImage;

    public NodePanel() {
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
            // Cambia el tamaño deseado del fondo
            int backgroundWidth = getWidth();  // Ancho del panel
            int backgroundHeight = getHeight(); // Alto del panel
            g.drawImage(backgroundImage, 0, 0, backgroundWidth, backgroundHeight, this);
        }
        
        // Draw a node
        g.setColor(Color.BLUE);
        g.fillOval(50, 50, 30, 30); // Draw a node
        g.drawString("Node 1", 55, 70); // Label the node
        
        // Draw another node
        g.fillOval(150, 50, 30, 30); // Draw another node
        g.drawString("Node 2", 155, 70); // Label the second node
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Node Panel with JPG Background");
        NodePanel panel = new NodePanel();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
