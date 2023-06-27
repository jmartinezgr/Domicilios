package Domicilios.Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface extends JFrame implements ActionListener {

    private Container contenedor;
    private JLabel usernameLabel,passwordLabel,nameLabel,idLabel,ageLabel,addressLabel,genderLabel,userLabel,passwordLabel2;
    private JTextField usernameField,nameField,idField,ageField,addressField,genderField,userField;
    private JPasswordField passwordField,passwordField2;

    private JButton loginButton,registerButton,confirmButton;

    private MainApp mainApp;

    public LoginInterface(){
        setTitle("Login");
        setSize(450, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio();
    }

    private void inicio(){
        contenedor = getContentPane();
        contenedor.setLayout(null);

        usernameLabel = new JLabel("Usuario:");
        usernameLabel.setBounds(100,100,80,23);
        usernameField = new JTextField();
        usernameField.setBounds(200,100,150,23);

        passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(100,150,80,23);
        passwordField = new JPasswordField();
        passwordField.setBounds(200,150,150,23);

        loginButton = new JButton("Iniciar sesión");
        loginButton.addActionListener(this);
        loginButton.setBounds(110, 200, 120, 23);

        registerButton = new JButton("Registrarse");
        registerButton.addActionListener(this);
        registerButton.setBounds(240, 200, 100, 23);

        contenedor.add(usernameLabel);
        contenedor.add(usernameField);
        contenedor.add(passwordLabel);
        contenedor.add(passwordField);
        contenedor.add(loginButton);
        contenedor.add(registerButton);

        nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(100, 20, 80, 23);
        nameLabel.setVisible(false);
        nameField = new JTextField();
        nameField.setBounds(200, 20, 150, 23);
        nameField.setVisible(false);

        ageLabel = new JLabel("Edad:");
        ageLabel.setBounds(100, 50, 80, 23);
        ageLabel.setVisible(false);
        ageField = new JTextField();
        ageField.setBounds(200, 50, 150, 23);
        ageField.setVisible(false);

        addressLabel = new JLabel("Dirección:");
        addressLabel.setBounds(100, 90, 80, 23);
        addressLabel.setVisible(false);
        addressField = new JTextField();
        addressField.setBounds(200, 90, 150, 23);
        addressField.setVisible(false);

        genderLabel = new JLabel("Género:");
        genderLabel.setBounds(100, 120, 80, 23);
        genderLabel.setVisible(false);
        genderField = new JTextField();
        genderField.setBounds(200, 120, 150, 23);
        genderField.setVisible(false);

        idLabel = new JLabel("Identificación:");
        idLabel.setBounds(100, 150, 80, 23);
        idLabel.setVisible(false);
        idField = new JTextField();
        idField.setBounds(200, 150, 150, 23);
        idField.setVisible(false);

        userLabel = new JLabel("Usuario:");
        userLabel.setBounds(100, 180, 80, 23);
        userLabel.setVisible(false);
        userField = new JTextField();
        userField.setBounds(200, 180, 150, 23);
        userField.setVisible(false);

        passwordLabel2 = new JLabel("Contraseña:");
        passwordLabel2.setBounds(100, 210, 80, 23);
        passwordLabel2.setVisible(false);
        passwordField2 = new JPasswordField();
        passwordField2.setBounds(200, 210, 150, 23);
        passwordField2.setVisible(false);

        confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(this);
        confirmButton.setBounds(150, 270, 150, 23);
        confirmButton.setVisible(false);

        contenedor.add(nameLabel);
        contenedor.add(nameField);
        contenedor.add(ageLabel);
        contenedor.add(ageField);
        contenedor.add(addressLabel);
        contenedor.add(addressField);
        contenedor.add(genderLabel);
        contenedor.add(genderField);
        contenedor.add(idLabel);
        contenedor.add(idField);
        contenedor.add(userLabel);
        contenedor.add(userField);
        contenedor.add(passwordLabel2);
        contenedor.add(passwordField2);
        contenedor.add(confirmButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Lógica para iniciar sesión
        } else if (e.getSource() == registerButton) {
            // Ocultar los componentes de inicio de sesión
            usernameField.setVisible(false);
            passwordField.setVisible(false);
            usernameLabel.setVisible(false);
            passwordLabel.setVisible(false);
            loginButton.setVisible(false);
            registerButton.setVisible(false);

            // Mostrar los componentes para el registro
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            ageLabel.setVisible(true);
            ageField.setVisible(true);
            addressLabel.setVisible(true);
            addressField.setVisible(true);
            genderLabel.setVisible(true);
            genderField.setVisible(true);
            idLabel.setVisible(true);
            idField.setVisible(true);
            userLabel.setVisible(true);
            userField.setVisible(true);
            passwordLabel2.setVisible(true);
            passwordField2.setVisible(true);
            confirmButton.setVisible(true);

        } else if (e.getSource() == confirmButton) {
            // Obtener los valores ingresados por el usuario
            String name = nameField.getText();
            String ageText = ageField.getText();
            int age = 0; // Valor predeterminado en caso de que el campo esté vacío
            if (!ageText.isEmpty()) {
                age = Integer.parseInt(ageText);
            }
            String address = addressField.getText();
            String gender = genderField.getText();
            String id = idField.getText();
            String user = userField.getText();
            String password = new String(passwordField2.getPassword());

            // Realizar el registro con los valores obtenidos

            // Mostrar los componentes iniciales para el próximo inicio de sesión
            nameLabel.setVisible(false);
            nameField.setVisible(false);
            ageLabel.setVisible(false);
            ageField.setVisible(false);
            addressLabel.setVisible(false);
            addressField.setVisible(false);
            genderLabel.setVisible(false);
            genderField.setVisible(false);
            idLabel.setVisible(false);
            idField.setVisible(false);
            userLabel.setVisible(false);
            userField.setVisible(false);
            passwordLabel2.setVisible(false);
            passwordField2.setVisible(false);
            confirmButton.setVisible(false);

            usernameField.setVisible(true);
            passwordField.setVisible(true);
            loginButton.setVisible(true);
            registerButton.setVisible(true);
            usernameLabel.setVisible(true);
            passwordLabel.setVisible(true);
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}

