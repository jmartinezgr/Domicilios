package Domicilios.EstructuraDeDatos;

public class Queue<T> {
    private LinkedList<T> linkedList;

    public Queue() {
        linkedList = new LinkedList<>();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public void enqueue(T data) {
        linkedList.insertAtBeginning(data);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("The queue is empty.");
        }

        Node<T> current = linkedList.getInitialSelection();
        Node<T> previous = null;

        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }

        if (previous != null) {
            previous.setNext(null);
        } else {
            linkedList.getInitialSelection().setNext(null);
        }

        return current.getValue();
    }

    public Node<T> getHead() {
        return linkedList.getInitialSelection();
    }
}
