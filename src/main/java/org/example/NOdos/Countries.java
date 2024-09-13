package org.example.NOdos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return edad == persona.edad && Objects.equals(nombre, persona.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, edad);
    }
}

public class Countries {
    public static void main(String[] args) {
        String rutaArchivo = "src\\main\\java\\org\\example\\NOdos\\country_codes.json";
        // Crear una lista de personas
        List<Persona> personas = leerPersonasDesdeArchivo(rutaArchivo);
        
        // Nuevas personas a agregar
        List<Persona> nuevasPersonas = List.of(
            new Persona("Juan", 25),
            new Persona("Maria", 30),
            new Persona("Pedro", 22),
            new Persona("Juan", 25) // Duplicado para demostrar
        );

        // Agregar nuevas personas solo si no existen
        for (Persona nuevaPersona : nuevasPersonas) {
            if (!personas.contains(nuevaPersona)) {
                personas.add(nuevaPersona);
            } else {
                System.out.println("La persona " + nuevaPersona.getNombre() + " ya existe y no se agregará.");
            }
        }

        // Escribir la lista actualizada en el archivo JSON
        escribirPersonasEnArchivo(personas, rutaArchivo);
    }

    private static List<Persona> leerPersonasDesdeArchivo(String rutaArchivo) {
        List<Persona> personas = new ArrayList<>();
        Gson gson = new Gson();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            Type personaListType = new TypeToken<List<Persona>>() {}.getType();
            personas = gson.fromJson(reader, personaListType);
            if (personas == null) {
                personas = new ArrayList<>();
            }
        } catch (IOException e) {
            // Si el archivo no existe, simplemente retornamos una lista vacía
            System.out.println("Archivo no encontrado, se creará uno nuevo.");
        }
        
        return personas;
    }

    private static void escribirPersonasEnArchivo(List<Persona> personas, String rutaArchivo) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(personas, writer);
            System.out.println("Archivo JSON actualizado exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}