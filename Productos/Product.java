package Domicilios.Productos;

public class Product {
    private String name;
    private int rating;
    private int quantity;
    private float value;
    private String type;

    public Product(String name, int rating, int quantity, String type, float value) {
        this.name = name;
        this.rating = rating;
        this.quantity = quantity;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean stockToSell(int quantity) {
        return this.quantity > quantity;
    }

    public boolean stockToSell(){
        return this.quantity > 0;
    }

    public void addInfoToData(){

    }
}
