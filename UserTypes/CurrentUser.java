package Domicilios.UserTypes;

import Domicilios.EstructuraDeDatos.*;
import Domicilios.Productos.Product;
import Domicilios.Writers.*;

import java.util.HashMap;
import java.util.Map;

public class CurrentUser {
    private String name;
    private int age;
    private String address;
    private String gender;
    private String id;
    public ListProduct shoppingCart;
    public LinkedList<Integer> quantities;
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

        if (map.get("age") instanceof Long) {
            this.age = ((Long) map.get("age")).intValue();
        } else if (map.get("age") instanceof String) {
            this.age = Integer.parseInt((String) map.get("age"));
        } else {
            // Manejar otro tipo de valor o valor nulo si es necesario
            this.age = 0; // Valor predeterminado en caso de error
        }

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
        if(writer.keyExists(user)){
            writer.makeChange(user, stringMap);}
        else{
            writer.create(user,stringMap);
        }
    }

    public void doDelivery(int num) {
        double totalValue = 0;
        StringBuilder sb = new StringBuilder();

        Node<Product> currentProduct = shoppingCart.getInitialSelection();
        Node<Integer> currentQuantity = quantities.getInitialSelection();

        while (currentProduct != null && currentQuantity != null) {
            int quantity = currentQuantity.getValue();
            for (int i = 0; i < quantity; i++) {
                totalValue += currentProduct.getValue().getValue();
            }
            sb.append(currentProduct.getValue().getName()).append("(").append(currentQuantity.getValue()).append(") -> ");
            currentProduct = currentProduct.getNext();
            currentQuantity = currentQuantity.getNext();
        }

        String productList = sb.toString();
        if (productList.endsWith(" -> ")) {
            productList = productList.substring(0, productList.length() - 4);
        }

        DeliverysWriters deliverysWriters = new DeliverysWriters();
        //String deliveryCode = generateDeliveryCode();
        Map<String, Object> map = new HashMap<>();
        map.put("User", name);
        map.put("Delivery", "");
        map.put("ProductList", productList);
        map.put("Value", totalValue);
        map.put("Status", "Esperando repartidor");
        map.put("key","D-"+num);

        deliverysWriters.create("D-"+num, map);

        shoppingCart.clear();
        quantities.clear();
    }

    private String generateDeliveryCode() {
        int lastDigit = Integer.parseInt(id.substring(id.length() - 1));
        int newDigit = (lastDigit + 1) % 10;
        return id.substring(0, id.length() - 1) + newDigit;
    }
}
