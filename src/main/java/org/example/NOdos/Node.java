package org.example.NOdos;

class Position{
    public int x;
    public int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Node<T>{
    private T data; // data que almacena el nodo
    private Node<T> next; // Referencia al next nodo
    Position position;
    // Constructor
    public Node(T data, int x, int y) {
        this.data = data;
        this.next = null; // Inicialmente, el next nodo es nulo
        this.position = new Position(x, y);
    }

    // Métodos getter y setter
    public T getData() {
        return data;
    }

    public void setdata(T data) {
        this.data = data;
    }

    public Node<T> getnext() {
        return next;
    }

    public void setnext(Node<T> next) {
        this.next = next;
    }
}
