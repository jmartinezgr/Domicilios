package Domicilios.UserTypes;

import Domicilios.EstructuraDeDatos.*;
import Domicilios.Productos.Product;


public class CurrentUser {
    private String name;
    private int age;
    private String address;
    private String gender;
    private String id;
    private ListProduct shoppingCart;
    private LinkedList<Integer> quantities;
    private String verificate;
    private String user;
    private String password;

    public CurrentUser(String name, int age, String address, String gender, String id,String user, String password) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.id = id;
        this.user = user;
        this.password = password;
        this.shoppingCart = new ListProduct();
        this.quantities = new LinkedList<>();
        this.verificate = "Por verificar";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public ListProduct getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ListProduct shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setVerificate(String status){
        this.verificate = status;
    }

    public void clearShoppingCart() {
        shoppingCart.clear();
        quantities.clear();
    }

    public void addToShoppingCart(Product product, int quantity) {
        shoppingCart.insertAtBeginning(product);
        quantities.insertAtBeginning(quantity);
    }

    public void addInfoToData(){

    }

    public void doDelivery() {
        double totalValue = 0;

        Node<Product> currentProduct = shoppingCart.getInitialSelection();
        Node<Integer> currentQuantity = quantities.getInitialSelection();

        while (currentProduct != null && currentQuantity != null) {
            int quantity = currentQuantity.getValue();
            for (int i = 0; i < quantity; i++) {
                totalValue += currentProduct.getValue().getValue();
            }
            currentProduct = currentProduct.getNext();
            currentQuantity = currentQuantity.getNext();
        }

        System.out.println("Valor total del pedido: $" + totalValue);

        shoppingCart.clear();
        quantities.clear();
    }
}
