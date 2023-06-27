package Domicilios.Interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface extends JFrame implements ActionListener {
    private MainApp mainApp;

    public MainInterface() {
        setTitle("Interfaz principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Bienvenido a la interfaz principal");
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(welcomeLabel);

        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.setAlignmentX(CENTER_ALIGNMENT);
        logoutButton.addActionListener(this);
        add(logoutButton);

        setVisible(true);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainApp.showLoginInterface();
        dispose(); // Cerrar la ventana principal
    }
}
