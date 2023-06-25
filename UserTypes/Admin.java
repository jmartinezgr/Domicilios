package Domicilios.UserTypes;

import Domicilios.Writers.UsersWriters;

import java.util.HashMap;
import java.util.Map;

public class Admin {
    private String name;
    private String user;
    private String password;
    private String verificate;
    private UsersWriters writer;

    public Admin(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
        this.verificate = "Por verificar";
        this.writer = new UsersWriters("Admins");
    }

    public Admin(Map<String, Object> map) {
        this.name = (String) map.get("name");
        this.user = (String) map.get("user");
        this.password = (String) map.get("password");
        this.verificate = (String) map.get("verificate");
        this.writer = new UsersWriters("Admins");
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

    public void userVerification() {
        // Implementación de verificación de usuario
    }

    public void deliveryVerification() {
        // Implementación de verificación de repartidor
    }

    public void addInfoToData() {
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("user", user);
        stringMap.put("password", password);
        stringMap.put("verificate", verificate);
        writer.makeChange(user,stringMap);
    }
}
