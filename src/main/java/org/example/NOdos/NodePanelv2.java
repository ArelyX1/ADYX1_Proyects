package org.example.NOdos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class NodePanelv2 extends JPanel {
    private BufferedImage backgroundImage;
    private ArrayList<Node> nodes; // Lista para almacenar nodos

    // Clase interna para representar un nodo
    private static class Node {
        int x;
        int y;
        String label;
        ArrayList<Node> connections; // Nodos relacionados

        Node(int x, int y, String label) {
            this.x = x;
            this.y = y;
            this.label = label;
            this.connections = new ArrayList<>(); // Inicializa la lista de conexiones
        }

        // Método para agregar una conexión
        void addConnection(Node node) {
            connections.add(node);
        }
    }

    public NodePanelv2() {
        try {
            // Load the background image (update the path to your image)
            backgroundImage = ImageIO.read(new File("src/main/java/org/example/NOdos/planisferio-mapa-del-mundo-3-scaled.jpg")); // Change this to your image path
        } catch (IOException e) {
            e.printStackTrace();
            // Optionally set a default background color if the image fails to load
            setBackground(Color.LIGHT_GRAY);
        }

        nodes = new ArrayList<>(); // Inicializar la lista de nodos
        initializeNodes(); // Llenar la lista de nodos
    }

    private void initializeNodes() {
        // Agregar nodos con posiciones escalables
        Node node1 = new Node((int) (getWidth() * 0.1), (int) (getHeight() * 0.1), "Node 1");
        Node node2 = new Node((int) (getWidth() * 0.25), (int) (getHeight() * 0.1), "Node 2");
        Node node3 = new Node((int) (getWidth() * 0.4), (int) (getHeight() * 0.3), "Node 3");

        // Establecer relaciones entre nodos
        node1.addConnection(node2); // Node 1 está relacionado con Node 2
        node2.addConnection(node3); // Node 2 está relacionado con Node 3
        node3.addConnection(node1); // Node 3 está relacionado con Node 1 (ciclo)

        // Agregar nodos a la lista
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
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

        // Calcular posiciones de nodos escalables y dibujar conexiones
        for (Node node : nodes) {
            int scaledX = (int) (getWidth() * node.x / (double) getWidth());
            int scaledY = (int) (getHeight() * node.y / (double) getHeight());
            drawNode(g, scaledX, scaledY, 10, node.label);

            // Dibujar conexiones
            for (Node connectedNode : node.connections) {
                int connectedX = (int) (getWidth() * connectedNode.x / (double) getWidth());
                int connectedY = (int) (getHeight() * connectedNode.y / (double) getHeight());
                drawConnection(g, scaledX, scaledY, connectedX, connectedY);
            }
        }
    }

    public static void drawNode(Graphics g, int x, int y, int size, String label) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, size, size); // Dibuja el nodo
        g.drawString(label, x + 5, y + 20); // Etiqueta del nodo
    }

    public static void drawConnection(Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(Color.RED); // Color de la conexión
        g.drawLine(x1 + 5, y1 + 5, x2 + 5, y2 + 5); // Dibuja una línea entre nodos
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Node Panel with JPG Background");
        NodePanelv2 panel = new NodePanelv2();
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