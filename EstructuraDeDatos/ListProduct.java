package Domicilios.EstructuraDeDatos;

import Domicilios.Productos.Product;

public class ListProduct extends LinkedList<Product> {

    public void sort() {
        if (!isEmpty()) {
            Node<Product> current = head;

            while (current != null) {
                Node<Product> nextNode = current.getNext();

                while (nextNode != null) {
                    if (current.getValue().getValue() > nextNode.getValue().getValue()) {
                        // Intercambiar los valores de current y nextNode
                        Product temp = current.getValue();
                        current.setValue(nextNode.getValue());
                        nextNode.setValue(temp);
                    }

                    nextNode = nextNode.getNext();
                }

                current = current.getNext();
            }
        } else {
            throw new IndexOutOfBoundsException("La lista está vacía. No se puede aplicar el ordenamiento.");
        }
    }

    public void displayList() {
        Node<Product> current = head;

        while (current != null) {
            if (current.getNext() != null) {
                System.out.print(current.getValue().getName() + " -> ");
                current = current.getNext();
            } else {
                System.out.print(current.getValue().getName());
                current = current.getNext();
            }
        }

        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<Product> current = head;

        while (current != null) {
            if (current.getNext() != null) {
                sb.append(current.getValue().getName()).append(" (").append(current.getValue().getQuantity()).append(") -> ");
                current = current.getNext();
            } else {
                sb.append(current.getValue().getName()).append(" (").append(current.getValue().getQuantity()).append(")");
                current = current.getNext();
            }
        }

        return sb.toString();
    }
}
