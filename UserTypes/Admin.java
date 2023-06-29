package Domicilios.UserTypes;

import Domicilios.Productos.Product;
import Domicilios.Writers.UsersWriters;
import java.util.HashMap;
import java.util.Map;

public class Admin {
    private String name;
    private int age;
    private String address;
    private String gender;
    private String id;
    private String user;
    private String password;
    private String verificate;
    private UsersWriters writer;
    private UsersWriters userverificator;
    private UsersWriters deliveryverificator;

    private UsersWriters adminverificator;

    public Admin(String name, int age, String address, String gender, String id, String user, String password) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.id = id;
        this.user = user;
        this.password = password;
        this.verificate = "Por verificar";
        this.writer = new UsersWriters("Admins");
        this.userverificator = new UsersWriters("Users");
        this.deliveryverificator = new UsersWriters("Deliverys");
        this.adminverificator = new UsersWriters("Admins");
    }

    public Admin(Map<String, Object> map) {
        this.name = (String) map.get("name");
        if (map.get("age") instanceof Long) {
            this.age = ((Long) map.get("age")).intValue();
        } else if (map.get("age") instanceof String) {
            this.age = Integer.parseInt((String) map.get("age"));
        } else {
            // Manejar otro tipo de valor o valor nulo si es necesario
            this.age = 0; // Valor predeterminado en caso de error
        }
        this.address = (String) map.getOrDefault("address", "");
        this.gender = (String) map.getOrDefault("gender", "");
        this.id = (String) map.getOrDefault("id", "");
        this.user = (String) map.get("user");
        this.password = (String) map.get("password");
        this.verificate = (String) map.get("verificate");
        this.writer = new UsersWriters("Admins");
        this.userverificator = new UsersWriters("Users");
        this.deliveryverificator = new UsersWriters("Deliverys");
        this.adminverificator = new UsersWriters("Admins");
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

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getVerificate() {
        return verificate;
    }

    public void setVerificate(String status) {
        this.verificate = status;
    }

    public void createProduct(Map<String, Object> map) {
        Product nuevoProducto = new Product(map);
        nuevoProducto.addInfoToData();
    }

    public void userVerification(String user, boolean verificate) {
        CurrentUser userToVerificate = new CurrentUser(userverificator.getByKey(user));
        if (verificate) {
            userToVerificate.setVerificate("Verificado");
        } else {
            userToVerificate.setVerificate("Denegado");
        }
        userToVerificate.addInfoToData();
    }

    public void adminVerification(String user, boolean verificate) {
        Admin userToVerificate = new Admin(adminverificator.getByKey(user));
        if (verificate) {
            userToVerificate.setVerificate("Verificado");
        } else {
            userToVerificate.setVerificate("Denegado");
        }
        userToVerificate.addInfoToData();
    }

    public void deliveryVerification(String delivery, boolean verificate) {
        DeliveryPerson deliveryToVerificate = new DeliveryPerson(deliveryverificator.getByKey(delivery));
        if (verificate) {
            deliveryToVerificate.setVerificate("Verificado");
        } else {
            deliveryToVerificate.setVerificate("Denegado");
        }
        deliveryToVerificate.addInfoToData();
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
        stringMap.put("verificate", verificate);
        if (writer.keyExists(user)) {
            writer.makeChange(user, stringMap);
        } else {
            writer.create(user, stringMap);
        }
    }
}
