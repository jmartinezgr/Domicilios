package Domicilios.EstructuraDeDatos;

//Estructura de datos que reemplaza la lista convencional e implementa el nodo como elemento de lista y conexion al siguiente elemento

public class LinkedList<T> {
    protected Node<T> head; //La linked list son listas ya que estan conectadas entre sus propios nodos
    protected Node<T> current; //Para recorrer estas listas se necesitara el nodo inicial y el nodo actual

    public LinkedList() {  //El constructor crea una lista vacia donde el nodo cabeza y el actual son nulos
        this.head = null;
        this.current = null;
    }

    //La funcion isEmpty comprueba si el nodo cabeza es nulo, lo que significa si la lista es vacia.
    public boolean isEmpty() {
        return head == null; //Si se cumple esa condicion, devuelve true
    }

    //La funcion insertAtBeginning agrega un nodo como nodo cabeza y reemplaza el current con el anterior nodo cabeza
    public void insertAtBeginning(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(head); //Conecta el nuevo nodo al anterior nodo cabeza
        head = newNode;
        if (current == null) {
            current = head;  //Esto se ejecuta en caso de la lista este vacio, ya que solo habria un nodo
        }
    }

    //Funcion que recorre la lista y devuelve un string con el valor de sus elementos separados ->
    public void displayList() {
        Node<T> current = head; //Se accede al nodo actual y al nodo siguiente
        while (current != null) {  //Mientras exista un nodo relacionado al nodo actual se sacara el valor
            if (current.getNext() != null) {
                System.out.print(current.getValue() + " -> ");
                current = current.getNext(); //Se asigna al nodo actual el nodo siguiente
            } else {
                System.out.print(current.getValue());
                current = current.getNext();
            }
        }
        System.out.println();
    }

    public Node<T> getInitialSelection() { //Funcion devuelve el nodo actual al cabeza para poder recorrer la lista desde el inicio
        current = head;
        return current;
    }

    public Node<T> getNodeAtIndex(int index) { //Lista que dado un indice devuelve el elemento en ese indice de la lista
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

    public void setNodeValueAtIndex(int index, T value) { //Usa la funcion que devuelve el nodo en un indice y reemplaza su valor
        Node<T> node = getNodeAtIndex(index);
        node.setValue(value);
    }

    public Node<T> getHead() { //Funcion que retorna el nodo cabeza
        return head;
    }

    public void clear() { //Funcion que elimina todos los elementos de la lista
        head = null;
        current = null;
    }

    public int size() { //Funcion que cuenta cuantos nodos existe en la lista y devuelve el numero
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}
