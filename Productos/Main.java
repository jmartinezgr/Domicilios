package Domicilios.Productos;

import Domicilios.EstructuraDeDatos.*;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de ListProduct
        ListProduct listProduct = new ListProduct();

        // Agregar algunos productos a la lista
        listProduct.insertAtBeginning(new Product("Producto C", 3, 5, "Tipo C", 10.99f));
        listProduct.insertAtBeginning(new Product("Producto A", 5, 8, "Tipo A", 15.99f));
        listProduct.insertAtBeginning(new Product("Producto B", 4, 3, "Tipo B", 12.99f));

        // Mostrar la lista antes de ordenar
        System.out.println("Lista antes de ordenar:");
        listProduct.displayList();

        // Ordenar la lista
        listProduct.sort();

        // Mostrar la lista después de ordenar
        System.out.println("Lista después de ordenar:");
        listProduct.displayList();
    }
}
