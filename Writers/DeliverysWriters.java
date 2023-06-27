package Domicilios.Writers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Domicilios.EstructuraDeDatos.Queue;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DeliverysWriters {
    private String filePath;
    private JSONParser parser;
    private JSONObject data;

    public DeliverysWriters() {
        filePath = "src/Domicilios/Data/Deliveries.json";
        parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(this.filePath));
            data = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
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
            System.out.println("La clave no existe en los datos.");
            return null;
        }
    }


    public Queue<Map<String, Object>> deliverysWaitingToDelivery() {
        Queue<Map<String, Object>> queue = new Queue<>();

        // Crear una lista de claves para ordenarlas
        List<String> keys = new ArrayList<>(data.keySet());
        Collections.sort(keys);

        for (String key : keys) {
            Map<String, Object> deliveryInfo = getByKey(key);

            if (deliveryInfo.containsKey("Status") && deliveryInfo.get("Status").equals("Esperando repartidor")) {
                queue.enqueue(deliveryInfo);
            }
        }

        try {
            Object obj = parser.parse(new FileReader(this.filePath));
            data = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return queue;
    }



    public void makeChange(String key, Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(field, value);
        }
        data.put(key, jsonObject);
        saveChanges();
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

    private void saveChanges() {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(data.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object obj = parser.parse(new FileReader(this.filePath));
            data = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public int getLastDeliveryNumber() {
        int maxNumber = 0;

        for (Object key : data.keySet()) {
            String deliveryId = (String) key;
            if (deliveryId.startsWith("D-")) {
                String numberString = deliveryId.substring(2); // Obtener el número después de "D-"
                int number = Integer.parseInt(numberString);
                if (number > maxNumber) {
                    maxNumber = number;
                }
            }
        }

        return maxNumber;
    }

}
