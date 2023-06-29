package Domicilios.Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Domicilios.Writers.*;
import Domicilios.UserTypes.*;

import java.util.HashMap;
import java.util.Map;

public class LoginInterface extends JFrame implements ActionListener {

    private Container contenedor;
    private JLabel usernameLabel,passwordLabel,nameLabel,idLabel,ageLabel,addressLabel,genderLabel,userLabel,passwordLabel2,type;
    private JTextField usernameField,nameField,idField,ageField,addressField,genderField,userField,typefield;
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
        usernameField.setBounds(200,100,160,23);

        passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(100,150,80,23);
        passwordField = new JPasswordField();
        passwordField.setBounds(200,150,160,23);

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
        nameLabel.setBounds(100, 30, 80, 23);
        nameLabel.setVisible(false);
        nameField = new JTextField();
        nameField.setBounds(200, 30, 150, 23);
        nameField.setVisible(false);

        ageLabel = new JLabel("Edad:");
        ageLabel.setBounds(100, 60, 80, 23);
        ageLabel.setVisible(false);
        ageField = new JTextField();
        ageField.setBounds(200, 60, 150, 23);
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

        type = new JLabel("Tipo");
        type.setBounds(100,240,80,23);
        type.setVisible(false);
        typefield = new JTextField();
        typefield.setBounds(200,240,150,23);
        typefield.setVisible(false);

        confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(this);
        confirmButton.setBounds(150, 290, 150, 23);
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
        contenedor.add(type);
        contenedor.add(typefield);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {

            String user = usernameField.getText();
            String password = passwordField.getText();

            UsersWriters writersUsuarios = new UsersWriters("Users");
            UsersWriters writersDomiciliarios = new UsersWriters("Deliverys");
            UsersWriters writersAdmins = new UsersWriters("Admins");

            //Hacer funcion que que saque la cola de domicilios faltantessss y asigne el ultimo

            Map<String,Object> info = writersUsuarios.getByKey(user);

            if(info.isEmpty()){
                info = writersDomiciliarios.getByKey(user);
                if(info.isEmpty()){
                    info = writersAdmins.getByKey(user);
                    if(!info.isEmpty()){
                        Admin nuevousuario = new Admin(info);
                        if(info.get("password").equals(password)){
                            if(info.get("verificate").equals("Verificado")){
                                JOptionPane.showMessageDialog(null, "Bienvenido "+nuevousuario.getName(), "Welcome", JOptionPane.INFORMATION_MESSAGE);
                                this.mainApp.showAdminInterface(info);
                                dispose();
                            } else if (info.get("verificate").equals("Por verificar")) {
                                JOptionPane.showMessageDialog(null, "El usuario no ha sido verificado aun", "Error", JOptionPane.ERROR_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null, "El usuario se le ha denegado la entrada", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "La clave es erronea", "Error", JOptionPane.ERROR_MESSAGE);
                            passwordField.setText("");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
                        passwordField.setText("");
                        usernameField.setText("");
                    }
                }else{
                    DeliveryPerson nuevousuario = new DeliveryPerson(info);
                    if(info.get("password").equals(password)){
                        if(info.get("verificate").equals("Verificado")){
                            JOptionPane.showMessageDialog(null, "Bienvenido "+nuevousuario.getName(), "Welcome", JOptionPane.INFORMATION_MESSAGE);
                        } else if (info.get("verificate").equals("Por verificar")) {
                            JOptionPane.showMessageDialog(null, "El usuario no ha sido verificado aun", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "El usuario se le ha denegado la entrada", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "La clave es erronea", "Error", JOptionPane.ERROR_MESSAGE);
                        passwordField.setText("");
                    }
                }
            }else{
                CurrentUser nuevousuario = new CurrentUser(info);
                if(info.get("password").equals(password)){
                    if(info.get("verificate").equals("Verificado")){
                        JOptionPane.showMessageDialog(null, "Bienvenido "+nuevousuario.getName(), "Welcome", JOptionPane.INFORMATION_MESSAGE);
                        this.mainApp.showUserInterface(info);
                        dispose();
                    } else if (info.get("verificate").equals("Por verificar")) {
                        JOptionPane.showMessageDialog(null, "El usuario no ha sido verificado aun", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "El usuario se le ha denegado la entrada", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "La clave es erronea", "Error", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                }
            }

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
            typefield.setVisible(true);
            type.setVisible(true);
            setTitle("Register");

        } else if (e.getSource() == confirmButton) {
            // Obtener los valores ingresados por el usuario

            boolean flag = true;

            Map<String,Object> info = new HashMap<>();

            info.put("name",nameField.getText());
            if(!ageField.getText().isEmpty()) {
                info.put("age", ageField.getText());
            }else{
                JOptionPane.showMessageDialog(this,"Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                info.put("age",0);
                flag = false;
            }

            info.put("address",addressField.getText());
            info.put("gender",genderField.getText());
            info.put("id",idField.getText());
            info.put("user",userField.getText());
            info.put("password",new String(passwordField2.getPassword()));
            info.put("verificate","Por verificar");

            if(!typefield.getText().equals("Admin") && !typefield.getText().equals("Usuario") && !typefield.equals("Domiciliario")){
                flag = false;
                JOptionPane.showMessageDialog(this,"Debes escribir Usuario o Admin o Domiciliario", "Tipo invalido", JOptionPane.ERROR_MESSAGE);
                typefield.setText("");
            }
            UsersWriters admins = new UsersWriters("Admins");
            UsersWriters usuarios = new UsersWriters("Users");
            UsersWriters domiciliarios = new UsersWriters("Deliverys");
            if(flag){
                if(typefield.getText().equals("Admin")){
                    if(!admins.keyExists(userField.getText()) && !domiciliarios.keyExists(userField.getText()) && !usuarios.keyExists(userField.getText())){
                        Admin nuevoUsuario = new Admin(info);
                        nuevoUsuario.addInfoToData();
                        JOptionPane.showMessageDialog(null, "Se ha creado el nuevo administrador", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre de usuario", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (typefield.getText().equals("Usuario")) {
                    if(!admins.keyExists(userField.getText()) && !domiciliarios.keyExists(userField.getText()) && !usuarios.keyExists(userField.getText())){
                        CurrentUser nuevoUsuario = new CurrentUser(info);
                        nuevoUsuario.addInfoToData();
                        JOptionPane.showMessageDialog(null, "Se ha creado el nuevo usuario", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre de usuario", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{

                    info.put("vehicle",JOptionPane.showInputDialog(null, "Ingrese su tipo de vehiculo"));
                    if(!admins.keyExists(userField.getText()) && !domiciliarios.keyExists(userField.getText()) && !usuarios.keyExists(userField.getText())){
                        DeliveryPerson nuevoUsuario = new DeliveryPerson(info);
                        nuevoUsuario.addInfoToData();
                        JOptionPane.showMessageDialog(null, "Se ha creado el nuevo domiciliario", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre de usuario", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                nameLabel.setText("");
                nameField.setText("");
                ageLabel.setText("");
                ageField.setText("");
                addressLabel.setText("");
                addressField.setText("");
                genderLabel.setText("");
                genderField.setText("");
                idLabel.setText("");
                idField.setText("");
                userLabel.setText("");
                userField.setText("");
                passwordLabel2.setText("");
                passwordField2.setText("");
                confirmButton.setText("");
                typefield.setText("");
                type.setText("");


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
                typefield.setVisible(false);
                type.setVisible(false);

                usernameField.setVisible(true);
                passwordField.setVisible(true);
                loginButton.setVisible(true);
                registerButton.setVisible(true);
                usernameLabel.setVisible(true);
                passwordLabel.setVisible(true);

                usernameField.setText("");
                passwordField.setText("");

                setTitle("Login");
            }
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}

