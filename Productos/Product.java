package Domicilios.Productos;

import Domicilios.Writers.ProductsWriters;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private String name;
    private int rating;
    private long quantity;
    private float value;
    private String type;
    private ProductsWriters writer;

    public Product(String name, int rating, int quantity, String type, float value) {
        this.name = name;
        this.rating = rating;
        this.quantity = quantity;
        this.type = type;
        this.value = value;
        this.writer = new ProductsWriters();
    }

    public Product(Map<String, Object> map) {
        this.name = (String) map.get("name");
        this.rating = ((Number) map.get("rating")).intValue();
        this.quantity = ((Number) map.get("quantity")).longValue();
        this.type = (String) map.get("type");
        this.value = ((Number) map.get("value")).floatValue();
        this.writer = new ProductsWriters();
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public long getQuantity() {
        return quantity;
    }

    public float getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean stockToSell(int quantity) {
        return this.quantity > quantity;
    }

    public boolean stockToSell() {
        return this.quantity > 0;
    }

    public void addInfoToData() {
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("rating", rating);
        stringMap.put("quantity", quantity);
        stringMap.put("type", type);
        stringMap.put("value", value);
        if(writer.keyExists(name)){
            writer.makeChange(name,stringMap);}
        else{
            writer.create(name, stringMap);
        }
    }
}
