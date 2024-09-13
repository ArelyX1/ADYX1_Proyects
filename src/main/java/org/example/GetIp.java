package org.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIp {
    public static String getIP(String _url) {
        String url = _url; // URL codificada en el código
        // Extrae solo el nombre del host de la URL
        String host = url.replaceAll("https?://", "").split("/")[0];

        try {
            // Obtiene la dirección IP a partir del nombre del host
            InetAddress address = InetAddress.getByName(host);
            String ip = address.getHostAddress(); // Obtiene la IP en formato String
            return ip;
        } catch (UnknownHostException e) {
            System.out.println("No se pudo encontrar la dirección IP para " + host);
            e.printStackTrace();
            return null;
        }
    }
}
