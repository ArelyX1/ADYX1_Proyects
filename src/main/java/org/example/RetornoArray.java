package org.example;

import java.util.ArrayList;

public class RetornoArray {
    public static <T> T obtenerPenultimoElemento(ArrayList<T> lista) {
        if (lista.size() < 2) {
            return null; // O lanzar una excepción si no hay suficientes elementos
        }
        return lista.get(lista.size() - 2); // Retorna el penúltimo elemento
    }
    public static <T> T obtenerUltimoElemento(ArrayList<T> lista) {
        if (lista.isEmpty()) {
            return null; // O lanzar una excepción si la lista está vacía
        }
        return lista.get(lista.size() - 1); // Retorna el último elemento
    }   
}
