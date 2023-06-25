package Domicilios.UserTypes;

import Domicilios.EstructuraDeDatos.*;
import Domicilios.Productos.Product;
import Domicilios.Writers.UsersWriters;

import java.util.HashMap;
import java.util.Map;

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

    private UsersWriters writer;

    public CurrentUser(String name, int age, String address, String gender, String id, String user, String password) {
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
        this.writer = new UsersWriters("Users");
    }

    public CurrentUser(Map<String, Object> map) {
        this.name = (String) map.get("name");
        this.age = (int) map.get("age");
        this.address = (String) map.get("address");
        this.gender = (String) map.get("gender");
        this.id = (String) map.get("id");
        this.user = (String) map.get("user");
        this.password = (String) map.get("password");
        this.shoppingCart = new ListProduct();
        this.quantities = new LinkedList<>();
        this.verificate = (String) map.get("verificate");
        this.writer = new UsersWriters("Users");
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

    public void setVerificate(String status) {
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

    public void addInfoToData() {
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("age", age);
        stringMap.put("address", address);
        stringMap.put("gender", gender);
        stringMap.put("id", id);
        stringMap.put("user", user);
        stringMap.put("password", password);
        stringMap.put("verificate",verificate);
        writer.makeChange(user, stringMap);
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
