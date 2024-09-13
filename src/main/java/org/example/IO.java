package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IO {
    public static void createTXT(){
        File urls = new File("urls.txt");
        try {
            if(urls.createNewFile()) {
                System.out.println("Archivo creado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeToFile(String text){
        try {
                FileWriter fw = new FileWriter("urls.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(text + "\n");
                bw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
