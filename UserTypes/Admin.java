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
        // Constructor que recibe los datos del administrador y los asigna a las variables miembro.
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
        // Constructor que recibe un mapa de datos y extrae los valores correspondientes para inicializar el administrador.
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
        // Devuelve el nombre del administrador.
        return name;
    }

    public int getAge() {
        // Devuelve la edad del administrador.
        return age;
    }

    public String getAddress() {
        // Devuelve la dirección del administrador.
        return address;
    }

    public String getGender() {
        // Devuelve el género del administrador.
        return gender;
    }

    public String getId() {
        // Devuelve el ID del administrador.
        return id;
    }

    public String getUser() {
        // Devuelve el usuario del administrador.
        return user;
    }

    public String getPassword() {
        // Devuelve la contraseña del administrador.
        return password;
    }

    public String getVerificate() {
        // Devuelve el estado de verificación del administrador.
        return verificate;
    }

    public void setVerificate(String status) {
        // Establece el estado de verificación del administrador.
        this.verificate = status;
    }

    public void createProduct(Map<String, Object> map) {
        // Crea un nuevo producto utilizando los datos proporcionados en el mapa y lo agrega a los datos.
        Product nuevoProducto = new Product(map);
        nuevoProducto.addInfoToData();
    }

    public void userVerification(String user, boolean verificate) {
        // Verifica el estado de verificación de un usuario y actualiza su estado en los datos.
        CurrentUser userToVerificate = new CurrentUser(userverificator.getByKey(user));
        if (verificate) {
            userToVerificate.setVerificate("Verificado");
        } else {
            userToVerificate.setVerificate("Denegado");
        }
        userToVerificate.addInfoToData();
    }

    public void adminVerification(String user, boolean verificate) {
        // Verifica el estado de verificación de un administrador y actualiza su estado en los datos.
        Admin userToVerificate = new Admin(adminverificator.getByKey(user));
        if (verificate) {
            userToVerificate.setVerificate("Verificado");
        } else {
            userToVerificate.setVerificate("Denegado");
        }
        userToVerificate.addInfoToData();
    }

    public void deliveryVerification(String delivery, boolean verificate) {
        // Verifica el estado de verificación de una persona de entrega y actualiza su estado en los datos.
        DeliveryPerson deliveryToVerificate = new DeliveryPerson(deliveryverificator.getByKey(delivery));
        if (verificate) {
            deliveryToVerificate.setVerificate("Verificado");
        } else {
            deliveryToVerificate.setVerificate("Denegado");
        }
        deliveryToVerificate.addInfoToData();
    }

    public void addInfoToData() {
        // Agrega la información del administrador a los datos.
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
            // Si el usuario ya existe en los datos, se realiza un cambio en la información del administrador.
            writer.makeChange(user, stringMap);
        } else {
            // Si el usuario no existe en los datos, se crea una nueva entrada para el administrador.
            writer.create(user, stringMap);
        }
    }
}
