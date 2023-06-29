package Domicilios.EstructuraDeDatos;

public class Queue<T> {
    public LinkedList<T> linkedList;

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

        Node<T> firstNode = linkedList.getHead();
        linkedList.head = firstNode.getNext();

        return firstNode.getValue();
    }

    public int size(){
        return linkedList.size();
    }


    public Node<T> getHead() {
        return linkedList.getInitialSelection();
    }
}
