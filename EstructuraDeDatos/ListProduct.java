package Domicilios.EstructuraDeDatos;

import Domicilios.Productos.Product;

import java.util.ArrayList;
import java.util.List;

public class ListProduct extends LinkedList<Product> {

    //Esta estructura implementa la lista enlazada pero asegura que el unico objeto que recibira sera de tipo Product, a diferencia
    //de la lista enlazada que puede recibir cualquier tipo de dato, ya que es de tipo T. Lo que permite implementar metodos mas
    //especificos, sabiendo que sus nodos van a tener un tipo de dato especifico


    //La funcion sort ordena la lista de menor a mayor por el parametro valor de los productos en los nodos de la lista
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

    //Se sobreescribe la funcion displayList, para imprimir en este caso el nombre del producto separado por ->
    public void displayList() { //Ya que si se impriemse solo el valor del nodo saldria un codigo antinatural
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

    public String[] getProductNames() { //Esta funcion devuelve en una lista elemental de java los nombres de los productos en la lista
        List<String> names = new ArrayList<>();
        Node<Product> current = head;

        while (current != null) {
            names.add(current.getValue().getName());
            current = current.getNext();
        }

        return names.toArray(new String[0]);
    }

    @Override  //Se sobreescribe la funcion toString, para que al imprimi la lista se muestre los valores de sus nombres
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
