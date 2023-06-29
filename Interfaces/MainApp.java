package Domicilios.Interfaces;

import Domicilios.UserTypes.Admin;

import javax.swing.*;
import java.util.Map;

public class MainApp {
    private LoginInterface loginInterface;
    private AdminInterface AdminInterface;

    private UserInterface UserInterface;

    public MainApp() {
        loginInterface = new LoginInterface();
        loginInterface.setMainApp(this);
    }

    public void showAdminInterface(Map<String,Object> administrador) {
        loginInterface.setVisible(false);
        AdminInterface = new AdminInterface(administrador);
        AdminInterface.setMainApp(this);
        AdminInterface.setVisible(true);
    }

    public void showLoginInterface() {
        if (AdminInterface != null) {
            AdminInterface.setVisible(false);
            AdminInterface = null;
        } else if (UserInterface != null) {
            UserInterface.setVisible(false);
            UserInterface = null;
        }
        loginInterface.setVisible(true);
    }

    public void showUserInterface(Map<String,Object> usuario){
        loginInterface.setVisible(false);
        UserInterface = new UserInterface(usuario);
        UserInterface.setMainApp(this);
        UserInterface.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApp mainApp = new MainApp();
            mainApp.showLoginInterface();
        });
    }
}
