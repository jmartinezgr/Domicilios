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
        // Constructor que recibe los datos del usuario actual y los asigna a las variables miembro.
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
        // Constructor que recibe un mapa de datos y extrae los valores correspondientes para inicializar el usuario actual.
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
        // Devuelve el nombre del usuario actual.
        return name;
    }

    public int getAge() {
        // Devuelve la edad del usuario actual.
        return age;
    }

    public String getAddress() {
        // Devuelve la dirección del usuario actual.
        return address;
    }

    public void setAddress(String address) {
        // Establece la dirección del usuario actual.
        this.address = address;
    }

    public String getGender() {
        // Devuelve el género del usuario actual.
        return gender;
    }

    public String getId() {
        // Devuelve el ID del usuario actual.
        return id;
    }

    public ListProduct getShoppingCart() {
        // Devuelve el carrito de compras del usuario actual.
        return shoppingCart;
    }

    public void setShoppingCart(ListProduct shoppingCart) {
        // Establece el carrito de compras del usuario actual.
        this.shoppingCart = shoppingCart;
    }

    public void setVerificate(String status) {
        // Establece el estado de verificación del usuario actual.
        this.verificate = status;
    }

    public void clearShoppingCart() {
        // Limpia el carrito de compras del usuario actual.
        shoppingCart.clear();
        quantities.clear();
    }

    public void addToShoppingCart(Product product, int quantity) {
        // Agrega un producto con una cantidad determinada al carrito de compras del usuario actual.
        shoppingCart.insertAtBeginning(product);
        quantities.insertAtBeginning(quantity);
    }

    public void addInfoToData() {
        // Agrega la información del usuario actual a los datos.
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
            // Si el usuario ya existe en los datos, se realiza un cambio en la información del usuario actual.
            writer.makeChange(user, stringMap);
        }
        else{
            // Si el usuario no existe en los datos, se crea una nueva entrada para el usuario actual.
            writer.create(user,stringMap);
        }
    }

    public double carritoValue(){
        // Calcula el valor total del carrito de compras del usuario actual.
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

        return totalValue;
    }

    public void doDelivery(int num) {
        // Realiza la entrega de los productos en el carrito de compras del usuario actual.
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

}
