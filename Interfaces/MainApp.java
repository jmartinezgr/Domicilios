package Domicilios.Interfaces;

import Domicilios.UserTypes.Admin;

import javax.swing.*;
import java.util.Map;

public class MainApp {
    private LoginInterface loginInterface;
    private AdminInterface AdminInterface;

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
        }
        loginInterface.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApp mainApp = new MainApp();
            mainApp.showLoginInterface();
        });
    }
}
