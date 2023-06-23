package Domicilios.EstructuraDeDatos;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.insertAtBeginning("Node 1");
        linkedList.insertAtBeginning("Node 2");
        linkedList.insertAtBeginning("Node 3");
        linkedList.insertAtBeginning("Node 4");
        linkedList.insertAtBeginning("Node 5");

        System.out.println("Linked List:");
        linkedList.displayList();

        int index = 2;
        try {
            Node<String> node = linkedList.getNodeAtIndex(index);
            System.out.println("Node at index " + index + ": " + node.getValue());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid index.");
        }
    }
}
