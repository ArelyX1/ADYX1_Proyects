package org.example;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Crawler {
    private static final String DISALLOW = "Disallow:";

    public static void crawl(int level, String url, ArrayList<String> visitedUrls) {
        //todo Evitar copias de la var level por cada iteracion para respetar el limite de recursion
        if (true) {
            Document doc = request(url, visitedUrls);
            //! Control de errores -----------------------------------------
            if (doc != null) {                                           //!
                for (Element link : doc.select("a[href]")) {    //!
                    String next_link = link.absUrl("href"); //!
                    if (!visitedUrls.contains(next_link)) {              //!
                        crawl(++level, next_link, visitedUrls);          //!
                    }                                                    //!
                }                                                        //!
            }                                                            //! 
            //! ------------------------------------------------------------
        }
    }

    private static Document request(String url, ArrayList<String> visited) {
        try {
            URL urlObj = new URL(url);
            if (!robotSafe(urlObj)) {
                System.out.println("Acceso denegado por robots.txt: " + url);
                return null; // No continuar si no está permitido
            }

            Connection con = Jsoup.connect(url);
            Document doc = con.get();

            if (con.response().statusCode() == 200) {
                System.out.println("Title: " + doc.title());
                System.out.println("Link: " + url);
                visited.add(url);
                IO.writeToFile(GetIp.getIP(url));
                return doc;
            }
        } catch (IOException e) {
            Logger.getGlobal().log(Level.WARNING, "No se ha podido acceder a la URL " + url);
        }

        return null;
    }

    public static boolean robotSafe(URL url) {
        String strHost = url.getHost();
        String strRobot = "http://" + strHost + "/robots.txt";
        URL urlRobot;

        try {
            urlRobot = new URL(strRobot);
        } catch (MalformedURLException e) {
            return false; // URL mal formada
        }

        StringBuilder strCommands = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlRobot.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                strCommands.append(line).append("\n");
            }
        } catch (IOException e) {
            return true; // Si no hay robots.txt, se permite el rastreo
        }

        if (strCommands.toString().contains(DISALLOW)) {
            String[] split = strCommands.toString().split("\n");
            ArrayList<RobotRule> robotRules = new ArrayList<>();
            String mostRecentUserAgent = null;

            for (String line : split) {
                line = line.trim();
                if (line.toLowerCase().startsWith("user-agent")) {
                    mostRecentUserAgent = line.split(":")[1].trim();
                } else if (line.startsWith(DISALLOW)) {
                    if (mostRecentUserAgent != null) {
                        RobotRule r = new RobotRule();
                        r.userAgent = mostRecentUserAgent;
                        r.rule = line.split(":")[1].trim();
                        robotRules.add(r);
                    }
                }
            }

            // Verificar las reglas
            String path = url.getPath();
            for (RobotRule robotRule : robotRules) {
                if (robotRule.rule.isEmpty()) return true; // Permite todo si la regla está en blanco
                if (robotRule.rule.equals("/")) return false; // Bloquea todo si la regla es /

                if (path.startsWith(robotRule.rule)) {
                    return false; // Bloquea si la ruta comienza con la regla
                }
            }
        }
        return true; // Permite el rastreo si no hay restricciones
    }
}
