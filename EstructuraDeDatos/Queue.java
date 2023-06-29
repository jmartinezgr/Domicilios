package Domicilios.EstructuraDeDatos;

public class Queue<T> {

    //La cola es una estructura que implementa una linked list, pero que su caracteristica principal es que el nodo que sale(dequee) es
    //el primero en ser encolado (enquee)

    public LinkedList<T> linkedList;

    public Queue() {
        linkedList = new LinkedList<>();
    } //EL constructor crea una lista interna

    public boolean isEmpty() {
        return linkedList.isEmpty();
    } //Se instancia un metodo para saber si esta vacia

    public void enqueue(T data) {
        linkedList.insertAtBeginning(data);
    } //El metodo enquee inserta un valor al inicio de la lista

    public T dequeue() { //Y el valor dequee saca el ultimo valor de la lista, osea el que lleva mas tiempo
        if (isEmpty()) {
            throw new IllegalStateException("The queue is empty.");
        }

        Node<T> firstNode = linkedList.getHead();
        linkedList.head = firstNode.getNext();

        return firstNode.getValue();
    }

    public int size(){
        return linkedList.size();
    } //Se implementa una funcion para saber cuantos elementos tiene la lista

}
