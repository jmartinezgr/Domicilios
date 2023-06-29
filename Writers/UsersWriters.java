package Domicilios.Writers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Domicilios.EstructuraDeDatos.LinkedList;

public class UsersWriters {
    private String filePath;
    private JSONParser parser;
    private String type;
    private JSONObject data;

    public UsersWriters(String type) {
        filePath = "src/Domicilios/Data/Users.json";
        parser = new JSONParser();
        this.type = type;
        firstLoad();
    }

    // Carga inicial del archivo JSON y obtención de los datos específicos del tipo de usuario
    private void firstLoad() {
        try {
            Object obj = parser.parse(new FileReader(this.filePath));
            JSONObject jsonObject = (JSONObject) obj;
            data = (JSONObject) jsonObject.get(this.type);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Realiza cambios en los datos de un usuario existente
    public void makeChange(String key, Map<String, Object> map) {
        if (data.containsKey(key)) {
            JSONObject jsonObject = (JSONObject) data.get(key);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();
                jsonObject.put(field, value);
            }
            saveChanges();
        } else {
            System.out.println("La clave no existe en los datos.");
        }
    }

    // Crea un nuevo usuario en los datos
    public void create(String key, Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(field, value);
        }
        data.put(key, jsonObject);
        saveChanges();
    }

    // Verifica si una clave existe en los datos
    public boolean keyExists(String key) {
        return data.containsKey(key);
    }

    // Obtiene la información de un usuario por su clave
    public Map<String, Object> getByKey(String key) {
        if (data.containsKey(key)) {
            JSONObject jsonObject = (JSONObject) data.get(key);
            Map<String, Object> resultMap = new HashMap<>();
            for (Object jsonKey : jsonObject.keySet()) {
                String field = (String) jsonKey;
                Object value = jsonObject.get(field);
                resultMap.put(field, value);
            }
            return resultMap;
        } else {
            // Si la clave no existe, se devuelve un mapa vacío
            Map<String, Object> resultMap = new HashMap<>();
            return resultMap;
        }
    }

    // Guarda los cambios realizados en el archivo JSON
    private void saveChanges() {
        try {
            Object obj = parser.parse(new FileReader(this.filePath));
            JSONObject jsonObject = (JSONObject) obj;
            jsonObject.put(this.type, data);

            FileWriter writer = new FileWriter(this.filePath);
            writer.write(jsonObject.toJSONString());
            writer.flush();
            writer.close();
            firstLoad();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Obtiene una lista de usuarios con verificación pendiente
    public LinkedList<Map<String, Object>> getUsersWithVerificate() {
        LinkedList<Map<String, Object>> users = new LinkedList<>();

        try {
            Object obj = parser.parse(new FileReader(this.filePath));
            JSONObject jsonObject = (JSONObject) obj;
            for (Object key : jsonObject.keySet()) {
                String clase = (String) key;
                JSONObject objetosclases = (JSONObject) jsonObject.get(clase);

                for (Object keys : objetosclases.keySet()) {
                    String username = (String) keys;
                    JSONObject userObject = (JSONObject) objetosclases.get(username);

                    String userVerificate = (String) userObject.get("verificate");
                    if (userVerificate.equals("Por verificar")) {
                        Map<String, Object> userMap = new HashMap<>();
                        for (Object field : userObject.keySet()) {
                            String fieldName = (String) field;
                            Object fieldValue = userObject.get(fieldName);
                            userMap.put(fieldName, fieldValue);
                        }
                        users.insertAtBeginning(userMap);
                    }
                }
            }
            return users;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return users;
    }
}
