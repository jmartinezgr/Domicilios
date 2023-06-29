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

    // La clase Product se refiere a los productos disponibles para la compra.
    // Al instanciar este objeto, se permite manipular su información
    // con funciones específicas para productos.

    public Product(String name, int rating, int quantity, String type, float value) {
        // Constructor que recibe los datos del producto uno a uno y los asigna a las variables miembro.
        this.name = name;
        this.rating = rating;
        this.quantity = quantity;
        this.type = type;
        this.value = value;
        this.writer = new ProductsWriters();
        // Se inicializa un serializador de productos, que trabaja sobre el archivo donde se guardan los productos.
    }

    public Product(Map<String, Object> map) {
        // Constructor que recibe un mapa de datos y extrae los valores correspondientes para inicializar el producto.
        this.name = Objects.requireNonNullElse((String) map.get("name"), "");
        this.rating = parseIntegerValue(map.get("rating"));
        this.quantity = parseLongValue(map.get("quantity"));
        this.type = Objects.requireNonNullElse((String) map.get("type"), "");
        this.value = parseFloatValue(map.get("value"));
        this.writer = new ProductsWriters();
    }

    private int parseIntegerValue(Object value) {
        // Convierte un valor en un entero.
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
        // Convierte un valor en un entero largo.
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
        // Convierte un valor en un flotante.
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
        // Devuelve el nombre del producto.
        return name;
    }

    public int getRating() {
        // Devuelve la clasificación del producto.
        return rating;
    }

    public long getQuantity() {
        // Devuelve la cantidad del producto.
        return quantity;
    }

    public float getValue() {
        // Devuelve el valor del producto.
        return value;
    }

    public String getType() {
        // Devuelve el tipo del producto.
        return type;
    }

    public void setQuantity(long quantity) {
        // Establece la cantidad del producto.
        this.quantity = quantity;
    }

    public boolean stockToSell(int quantity) {
        // Verifica si hay suficiente cantidad de producto en stock para vender la cantidad solicitada.
        return this.quantity > quantity;
    }

    public boolean stockToSell() {
        // Verifica si hay stock disponible del producto.
        return this.quantity > 0;
    }

    public void addInfoToData() {
        // Agrega la información del producto a los datos.
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("rating", rating);
        stringMap.put("quantity", quantity);
        stringMap.put("type", type);
        stringMap.put("value", value);
        if (writer.keyExists(name)) {
            // Si el nombre del producto ya existe en los datos, se realiza un cambio en la información del producto.
            writer.makeChange(name, stringMap);
        } else {
            // Si el nombre del producto no existe en los datos, se crea una nueva entrada para el producto.
            writer.create(name, stringMap);
        }
    }
}
