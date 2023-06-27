package Domicilios.UserTypes;

import Domicilios.Writers.DeliverysWriters;
import Domicilios.Writers.UsersWriters;

import java.util.HashMap;
import java.util.Map;

public class DeliveryPerson {
    private String name;
    private int age;
    private String id;
    private String vehicle;
    private String gender;
    private String codeDelivery;
    private String verificate;
    private String user;
    private String password;
    private UsersWriters writer;

    public DeliveryPerson(String name, int age, String id, String vehicle, String gender, String codeDelivery, String user, String password) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.vehicle = vehicle;
        this.gender = gender;
        this.codeDelivery = codeDelivery;
        this.user = user;
        this.password = password;
        this.verificate = "Por verificar";
        this.writer = new UsersWriters("Deliverys");
    }

    public DeliveryPerson(Map<String, Object> map) {
        this.name = (String) map.get("name");
        if (map.get("age") instanceof Long) {
            this.age = ((Long) map.get("age")).intValue();
        } else if (map.get("age") instanceof String) {
            this.age = Integer.parseInt((String) map.get("age"));
        } else {
            // Manejar otro tipo de valor o valor nulo si es necesario
            this.age = 0; // Valor predeterminado en caso de error
        }

        this.id = (String) map.get("id");
        this.vehicle = (String) map.get("vehicle");
        this.gender = (String) map.get("gender");
        this.codeDelivery = (String) map.get("codeDelivery");
        this.user = (String) map.get("user");
        this.password = (String) map.get("password");
        this.verificate = (String) map.get("verificate");
        this.writer = new UsersWriters("Deliverys");
    }

    public void setCodeDelivery(String codeDelivery) {
        this.codeDelivery = codeDelivery;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getGender() {
        return gender;
    }

    public String getCodeDelivery() {
        return codeDelivery;
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

    public void choiceDelivery(String code) {
        codeDelivery = code;

        addInfoToData();

        DeliverysWriters delivery = new DeliverysWriters();

        Map<String, Object> map = delivery.getByKey(code);

        map.put("Delivery", user);
        map.put("Status", "En camino");

        delivery.makeChange(code, map);
    }

    public void deliverToUser() {
        DeliverysWriters delivery = new DeliverysWriters();
        Map<String, Object> map = new HashMap<>();
        map = delivery.getByKey(codeDelivery);

        map.put("Status", "Recibido");
        delivery.makeChange(codeDelivery, map);
        codeDelivery = "";
    }

    public void addInfoToData() {
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("age", age);
        stringMap.put("id", id);
        stringMap.put("vehicle", vehicle);
        stringMap.put("gender", gender);
        stringMap.put("codeDelivery", codeDelivery);
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
