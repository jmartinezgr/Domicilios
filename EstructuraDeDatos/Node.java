package Domicilios.EstructuraDeDatos;

//Clase nodo, que corresponde a la implementacion inicial para construir listas y colas

public class Node<T> { //EL nodo guarda un valor de cualquier tipo ademas de atributo que es el siguiente nodo relacionado
    private T value; //valor de tipo T (cualquier valor)
    private Node<T> next; //El siguiente nodo relacionado

    public Node(T data) { //Constructor
        this.value = data; // Se instancia el valor del dato que se recibe por el constructor
        this.next = null; //Se inicializa como nulo el nodo relacionado
    }

    public T getValue() {
        return value; //Metodo para obtener el valor
    }

    public void setValue(T data) {
        this.value = data; // metodo para cambiar el valor
    }

    public Node<T> getNext() {
        return next; // metodo para obtener el nodo relacionado
    }

    public void setNext(Node<T> next) {
        this.next = next; //Metodo para dar valor del nodo relacionado
    }

    @Override
    public String toString() {
        return String.valueOf(value); //Sobre escrituria de la funcion toString
    }
}