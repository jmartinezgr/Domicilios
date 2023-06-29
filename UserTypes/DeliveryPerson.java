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
        // Constructor para la clase DeliveryPerson que inicializa las variables miembro
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
        // Constructor que recibe un mapa y inicializa el objeto DeliveryPerson basándose en los valores del mapa
        this.name = (String) map.get("name");
        if (map.get("age") instanceof Long) {
            this.age = ((Long) map.get("age")).intValue();
        } else if (map.get("age") instanceof String) {
            this.age = Integer.parseInt((String) map.get("age"));
        } else {
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
        // Método setter para la variable codeDelivery
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
        // Método setter para la variable verificate
        this.verificate = status;
    }

    public void choiceDelivery(String code) {
        // Establece el codeDelivery y actualiza la información en los datos
        codeDelivery = code;

        addInfoToData();

        DeliverysWriters delivery = new DeliverysWriters();

        Map<String, Object> map = delivery.getByKey(code);

        map.put("Delivery", user);
        map.put("Status", "En camino");

        delivery.makeChange(code, map);
    }

    public void deliverToUser() {
        // Actualiza el estado de entrega a "Recibido" y limpia el codeDelivery
        DeliverysWriters delivery = new DeliverysWriters();
        Map<String, Object> map = new HashMap<>();
        map = delivery.getByKey(codeDelivery);

        map.put("Status", "Recibido");
        delivery.makeChange(codeDelivery, map);
        codeDelivery = "";
    }

    public void addInfoToData() {
        // Agrega la información del repartidor a los datos utilizando un objeto UsersWriters
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
