package Domicilios.UserTypes;

public class DeliveryPerson {
    private String name;
    private int age;
    private String id;
    private String vehicle;
    private String gender;
    private String codeDelivery;

    public DeliveryPerson(String name, int age, String id, String vehicle, String gender, String codeDelivery) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.vehicle = vehicle;
        this.gender = gender;
        this.codeDelivery = codeDelivery;
    }

    public void setCodeDelivery(String codeDelivery) {
        this.codeDelivery = codeDelivery;
    }

    public void deliverToUser() {
        this.codeDelivery = "";
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
}
