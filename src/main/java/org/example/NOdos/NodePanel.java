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
            int backgroundWidth = getWidth();  // Ancho del panel
            int backgroundHeight = getHeight(); // Alto del panel
            g.drawImage(backgroundImage, 0, 0, backgroundWidth, backgroundHeight, this);
        }
        
        // Calculate node positions based on panel size
        int node1X = (int) (getWidth() * 0.22); // 10% del ancho
        int node1Y = (int) (getHeight() * 0.40); // 10% del alto

        int node2X = (int) (getWidth() * 0.25); // 25% del ancho
        int node2Y = (int) (getHeight() * 0.1); // 10% del alto

        // Draw nodes
        g.setColor(Color.BLUE);
        g.fillOval(node1X, node1Y, 10, 10); // Draw node 1
        g.drawString("Node 1", node1X + 5, node1Y + 20); // Label node 1
        
        g.fillOval(node2X, node2Y, 10, 10); // Draw node 2
        g.drawString("Node 2", node2X + 5, node2Y + 20); // Label node 2
    }

    

    public static void drawNode(Graphics g, int x, int y, int size) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Node Panel with JPG Background");
        NodePanel panel = new NodePanel();
        frame.add(panel);
        
        // Make the JFrame resizable
        frame.setResizable(true);
        
        // Set the JFrame size to full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}