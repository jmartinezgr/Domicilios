package Domicilios.EstructuraDeDatos;

/*
En esta clase se implemente el codigo de una ListaElanzada, ya que toda la informacion de guarda
sobre un nodo llamado cabeza, y enlaces internos y como datos independientes ahorrando espacio de memoria
y tiempo de ejecucion
 */

public class LinkedList<T> {
    protected Node<T> head;
    protected Node<T> current;

    public LinkedList() {
        this.head = null;
        this.current = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertAtBeginning(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(head);
        head = newNode;
        if (current == null) {
            current = head;
        }
    }

    public void displayList() {
        Node<T> current = head;
        while (current != null) {
            if(current.getNext() != null){
                System.out.print(current.getValue() + " -> ");
                current = current.getNext();
            } else {
                System.out.print(current.getValue());
                current = current.getNext();
            }
        }
        System.out.println();
    }

    public Node<T> getInitialSelection() {
        current = head;
        return current;
    }

    public Node<T> getNodeAtIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative.");
        }

        Node<T> node = head;
        int currentIndex = 0;

        while (node != null && currentIndex < index) {
            node = node.getNext();
            currentIndex++;
        }

        if (node == null) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        return node;
    }

    public void setNodeValueAtIndex(int index, T value) {
        Node<T> node = getNodeAtIndex(index);
        node.setValue(value);
    }

    public Node<T> getHead() {
        return head;
    }

    public void clear() {
        head = null;
        current = null;
    }
}
