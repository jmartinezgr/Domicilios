package Domicilios.Interfaces;

import javax.swing.*;

public class MainApp {
    private LoginInterface loginInterface;
    private MainInterface mainInterface;

    public MainApp() {
        loginInterface = new LoginInterface();
        loginInterface.setMainApp(this);
    }

    public void showMainInterface() {
        loginInterface.setVisible(false);
        mainInterface = new MainInterface();
        mainInterface.setMainApp(this);
    }

    public void showLoginInterface() {
        if (mainInterface != null) {
            mainInterface.setVisible(false);
            mainInterface = null;
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
