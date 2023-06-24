package Domicilios.UserTypes;

public class DeliveryPerson {
    private String name;
    private int age;
    private String id;
    private String vehicle;
    private String gender;
    private String codeDelivery;
    private String verificate;
    private String user;
    private String password;


    public DeliveryPerson(String name, int age, String id, String vehicle, String gender, String codeDelivery,String user, String password) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.vehicle = vehicle;
        this.gender = gender;
        this.codeDelivery = codeDelivery;
        this.user = user;
        this.password = password;
        this.verificate = "Por verificar";
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

    public void setVerificate(String status){
        this.verificate = status;
    }
    public void addInfoToData(){

    }
}
