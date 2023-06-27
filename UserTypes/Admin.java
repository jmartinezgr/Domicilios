package Domicilios.UserTypes;

import Domicilios.Productos.Product;
import Domicilios.Writers.UsersWriters;
import java.util.HashMap;
import java.util.Map;

public class Admin {
    private String name;
    private String user;
    private String password;
    private String verificate;
    private UsersWriters writer;
    private UsersWriters userverificator;
    private UsersWriters deliveryverificator;

    public Admin(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
        this.verificate = "Por verificar";
        this.writer = new UsersWriters("Admins");
        this.userverificator = new UsersWriters("Users");
        this.deliveryverificator = new UsersWriters("Deliverys");
    }

    public Admin(Map<String, Object> map) {
        this.name = (String) map.get("name");
        this.user = (String) map.get("user");
        this.password = (String) map.get("password");
        this.verificate = (String) map.get("verificate");
        this.writer = new UsersWriters("Admins");
        this.userverificator = new UsersWriters("Users");
        this.deliveryverificator = new UsersWriters("Deliverys");
    }

    public String getName() {
        return name;
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

    public void createProduct(Map<String,Object> map){
        Product nuevoProducto = new Product(map);
        nuevoProducto.addInfoToData();
    }

    public void userVerification(String user, boolean verificate) {
        CurrentUser userToVerificate = new CurrentUser(userverificator.getByKey(user));
        if(verificate){
            userToVerificate.setVerificate("Verificado");
        }else{
            userToVerificate.setVerificate("Denegado");
        }
        userToVerificate.addInfoToData();
    }
    public void deliveryVerification(String delivery,boolean verificate) {
        DeliveryPerson deliveryToVerificate = new DeliveryPerson(deliveryverificator.getByKey(delivery));
        if(verificate){
            deliveryToVerificate.setVerificate("Verificado");
        }else{
            deliveryToVerificate.setVerificate("Denegado");
        }
        deliveryToVerificate.addInfoToData();
    }
    public void addInfoToData() {
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("user", user);
        stringMap.put("password", password);
        stringMap.put("verificate", verificate);
        if(writer.keyExists(user)){
            writer.makeChange(user, stringMap);}
        else {
            writer.create(user, stringMap);
        }
    }
}
