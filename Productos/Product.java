package Domicilios.Productos;

import Domicilios.Writers.ProductsWriters;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        this.name = Objects.requireNonNullElse((String) map.get("name"), "");
        this.rating = parseIntegerValue(map.get("rating"));
        this.quantity = parseLongValue(map.get("quantity"));
        this.type = Objects.requireNonNullElse((String) map.get("type"), "");
        this.value = parseFloatValue(map.get("value"));
        this.writer = new ProductsWriters();
    }

    private int parseIntegerValue(Object value) {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    private long parseLongValue(Object value) {
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            try {
                return Long.parseLong((String) value);
            } catch (NumberFormatException e) {
                return 0L;
            }
        } else {
            return 0L;
        }
    }

    private float parseFloatValue(Object value) {
        if (value instanceof Number) {
            return ((Number) value).floatValue();
        } else if (value instanceof String) {
            try {
                return Float.parseFloat((String) value);
            } catch (NumberFormatException e) {
                return 0.0f;
            }
        } else {
            return 0.0f;
        }
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
