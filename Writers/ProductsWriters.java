package Domicilios.Writers;

import Domicilios.EstructuraDeDatos.ListProduct;
import Domicilios.Productos.Product;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductsWriters {

    private String filePath;
    private JSONParser parser;
    private String type;
    private JSONObject data;

    public ProductsWriters() {
        filePath = "src/Domicilios/Data/Products.json";
        parser = new JSONParser();
        firstLoad();
    }

    private void firstLoad() {
        try {
            Object obj = parser.parse(new FileReader(this.filePath));
            data = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

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

    public boolean keyExists(String key) {
        return data.containsKey(key);
    }

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
            Map<String, Object> resultMap = new HashMap<>();
            return resultMap;
        }
    }

    private void saveChanges() {
        try{
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(data.toJSONString());
            writer.flush();
            writer.close();
            firstLoad();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public ListProduct productos() {
        ListProduct lista = new ListProduct();

        for (Object key : data.keySet()) {
            String clase = (String) key;
            JSONObject objetoClase = (JSONObject) data.get(clase);

            Product product = new Product(objetoClase);
            lista.insertAtBeginning(product);
        }

        return lista;
    }
}
