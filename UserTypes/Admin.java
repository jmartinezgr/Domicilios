package Domicilios.UserTypes;

public class Admin {
    private String name;
    private String user;
    private String password;
    private String verificate;

    public Admin(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
        this.verificate = "Por verificar";
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

    public void userVerification(){

    }

    public void deliberyVerification(){

    }

    public void addInfoToData(){

    }
}
