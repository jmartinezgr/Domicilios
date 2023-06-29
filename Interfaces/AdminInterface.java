package Domicilios.Interfaces;

import Domicilios.EstructuraDeDatos.LinkedList;
import Domicilios.Writers.ProductsWriters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import Domicilios.UserTypes.Admin;
import Domicilios.Writers.UsersWriters;

public class AdminInterface extends JFrame implements ActionListener {
    private MainApp mainApp = new MainApp();
    private int currentIndex = 0;

    private LinkedList<Map<String, Object>> users;
    private Container contenedor;
    private Admin administrador;
    private JButton agregarProducto,verificar,quejas,logout,crearProducto,verificarUsuario,DenegarUsuario;
    private JLabel nombreProducto,cantidadProducto,raitingProducto,tipoProducto,valorProducto,nombreUsuarioVerificar;
    private JTextField nombreProductoF,cantidadProductoF,raitingProductoF,tipoProductoF,valorProductoF;

    public AdminInterface(Map<String,Object> administrador) {
        this.administrador = new Admin(administrador);
        setTitle("Admin");
        setSize(450, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio();
        UsersWriters usersWriters = new UsersWriters("Users");
        users = usersWriters.getUsersWithVerificate();
    }

    public void inicio(){

        contenedor = getContentPane();
        contenedor.setLayout(null);

        //Botones principales
        agregarProducto = new JButton("Agregar Producto");
        agregarProducto.setBounds(150,70,150,23);
        agregarProducto.addActionListener(this);
        contenedor.add(agregarProducto);

        verificar = new JButton("Verificar Usuarios");
        verificar.setBounds(150,110,150,23);
        verificar.addActionListener(this);
        contenedor.add(verificar);

        quejas = new JButton("Ver quejas");
        quejas.setBounds(150,150,150,23);
        quejas.addActionListener(this);
        contenedor.add(quejas);

        logout = new JButton("Cerrar Sesion");
        logout.setBounds(150,190,150,23);
        logout.addActionListener(this);
        contenedor.add(logout);

        //Campos para agregar un producto

        nombreProducto = new JLabel("Nombre: ");
        nombreProducto.setBounds(100,50,80,23);
        contenedor.add(nombreProducto);
        nombreProducto.setVisible(false);

        nombreProductoF = new JTextField();
        nombreProductoF.setBounds(200,50,120,23);
        nombreProductoF.setVisible(false);
        contenedor.add(nombreProductoF);

        cantidadProducto = new JLabel("Cantidad: ");
        cantidadProducto.setBounds(100,80,80,23);
        cantidadProducto.setVisible(false);
        contenedor.add(cantidadProducto);

        cantidadProductoF = new JTextField();
        cantidadProductoF.setBounds(200,80,120,23);
        cantidadProductoF.setVisible(false);
        contenedor.add(cantidadProductoF);

        raitingProducto = new JLabel("Calificación: ");
        raitingProducto.setBounds(100,110,80,23);
        raitingProducto.setVisible(false);
        contenedor.add(raitingProducto);

        raitingProductoF = new JTextField();
        raitingProductoF.setBounds(200,110,120,23);
        raitingProductoF.setVisible(false);
        contenedor.add(raitingProductoF);

        tipoProducto = new JLabel("Tipo: ");
        tipoProducto.setBounds(100,140,80,23);
        tipoProducto.setVisible(false);
        contenedor.add(tipoProducto);

        tipoProductoF = new JTextField();
        tipoProductoF.setBounds(200,140,120,23);
        tipoProductoF.setVisible(false);
        contenedor.add(tipoProductoF);

        valorProducto = new JLabel("Precio U");
        valorProducto.setBounds(100,170,80,23);
        valorProducto.setVisible(false);
        contenedor.add(valorProducto);

        valorProductoF = new JTextField();
        valorProductoF.setBounds(200,170,120,23);
        valorProductoF.setVisible(false);
        contenedor.add(valorProductoF);

        crearProducto = new JButton("Crear");
        crearProducto.setBounds(165,210,100,23);
        crearProducto.addActionListener(this);
        crearProducto.setVisible(false);
        contenedor.add(crearProducto);

        //Verificar

        nombreUsuarioVerificar = new JLabel();
        nombreUsuarioVerificar.setBounds(60,150,120,23);
        nombreUsuarioVerificar.setVisible(false);
        contenedor.add(nombreUsuarioVerificar);

        verificarUsuario = new JButton("Verificar");
        verificarUsuario.setBounds(200,150,100,23);
        verificarUsuario.addActionListener(this);
        contenedor.add(verificarUsuario);
        verificarUsuario.setVisible(false);

        DenegarUsuario = new JButton("Denegar");
        DenegarUsuario.setBounds(310,150,100,23);
        DenegarUsuario.addActionListener(this);
        contenedor.add(DenegarUsuario);
        DenegarUsuario.setVisible(false);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logout){
            mainApp.showLoginInterface();
            dispose();
        } else if (e.getSource() == agregarProducto) {
            agregarProducto.setVisible(false);
            logout.setVisible(false);
            verificar.setVisible(false);
            quejas.setVisible(false);

            nombreProductoF.setVisible(true);
            nombreProducto.setVisible(true);
            cantidadProducto.setVisible(true);
            cantidadProductoF.setVisible(true);
            raitingProducto.setVisible(true);
            raitingProductoF.setVisible(true);
            tipoProducto.setVisible(true);
            tipoProductoF.setVisible(true);
            valorProducto.setVisible(true);
            valorProductoF.setVisible(true);
            crearProducto.setVisible(true);
        } else if (e.getSource() == crearProducto) {
            boolean flag = true;

            Map<String,Object> info = new HashMap<>();

            info.put("name",nombreProductoF.getText());
            info.put("raiting",raitingProductoF.getText());
            if(!cantidadProductoF.getText().isEmpty()) {
                info.put("quantity",cantidadProductoF.getText());
            }else{
                JOptionPane.showMessageDialog(this,"Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                info.put("quantity",0);
                flag = false;
            }

            info.put("type",tipoProductoF.getText());
            if(!valorProductoF.getText().isEmpty()) {
                info.put("value",valorProductoF.getText());
            }else{
                JOptionPane.showMessageDialog(this,"Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                info.put("value",0);
                flag = false;
            }

            if(flag){
                ProductsWriters productos = new ProductsWriters();
                productos.create(nombreProductoF.getText(),info);
                agregarProducto.setVisible(true);
                logout.setVisible(true);
                verificar.setVisible(true);
                quejas.setVisible(true);

                nombreProductoF.setVisible(false);
                nombreProductoF.setText("");
                nombreProducto.setVisible(false);
                cantidadProducto.setVisible(false);
                cantidadProductoF.setVisible(false);
                cantidadProductoF.setText("");
                raitingProducto.setVisible(false);
                raitingProductoF.setVisible(false);
                raitingProductoF.setText("");
                tipoProducto.setVisible(false);
                tipoProductoF.setVisible(false);
                tipoProductoF.setText("");
                valorProducto.setVisible(false);
                valorProductoF.setVisible(false);
                valorProductoF.setText("");
                crearProducto.setVisible(false);

                JOptionPane.showMessageDialog(this,info.get("name")+" se ha creado con exito!", "Exitoso!!", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == verificar) {
            verificacion();
        } else if (e.getSource() == verificarUsuario) {
            Map<String,Object> info = users.getNodeAtIndex(currentIndex).getValue();

            UsersWriters admins = new UsersWriters("Admins");
            UsersWriters usuarios = new UsersWriters("Users");
            UsersWriters domiciliarios = new UsersWriters("Deliverys");

            if(usuarios.keyExists((String) info.get("user"))){
                this.administrador.userVerification((String) info.get("user"),true);
                currentIndex++;
            } else if (domiciliarios.keyExists((String) info.get("user"))) {
                this.administrador.deliveryVerification((String) info.get("user"),true);
                currentIndex++;
            }else{
                this.administrador.adminVerification((String) info.get("user"),true);
            }
            JOptionPane.showMessageDialog(this,(String)info.get("user")+" ha sido verificado", "Exitoso!!", JOptionPane.INFORMATION_MESSAGE);
            verificacion();

        } else if (e.getSource() == DenegarUsuario) {
            Map<String,Object> info = users.getNodeAtIndex(currentIndex).getValue();

            UsersWriters admins = new UsersWriters("Admins");
            UsersWriters usuarios = new UsersWriters("Users");
            UsersWriters domiciliarios = new UsersWriters("Deliverys");

            if(usuarios.keyExists((String) info.get("user"))){
                this.administrador.userVerification((String) info.get("user"),false);
                currentIndex++;
            } else if (domiciliarios.keyExists((String) info.get("user"))) {
                this.administrador.deliveryVerification((String) info.get("user"),false);
                currentIndex++;
            }else {
                this.administrador.adminVerification((String) info.get("user"), false);
            }
            JOptionPane.showMessageDialog(this,(String)info.get("user")+" ha sido denegado", "Exitoso!!", JOptionPane.INFORMATION_MESSAGE);
            verificacion();
        }
    }

    private void verificacion(){
        agregarProducto.setVisible(false);
        logout.setVisible(false);
        verificar.setVisible(false);
        quejas.setVisible(false);
        nombreProductoF.setVisible(false);
        nombreProducto.setVisible(false);
        cantidadProducto.setVisible(false);
        cantidadProductoF.setVisible(false);
        raitingProducto.setVisible(false);
        raitingProductoF.setVisible(false);
        tipoProducto.setVisible(false);
        tipoProductoF.setVisible(false);
        valorProducto.setVisible(false);
        valorProductoF.setVisible(false);
        crearProducto.setVisible(false);


        if(!users.isEmpty() && currentIndex < users.size()){
            verificarUsuario.setVisible(true);
            DenegarUsuario.setVisible(true);
            nombreUsuarioVerificar.setVisible(true);

            Map<String, Object> user = users.getNodeAtIndex(currentIndex).getValue();
            String username = (String) user.get("user");
            nombreUsuarioVerificar.setText("Usuario: " + username);
        }
        else{
            JOptionPane.showMessageDialog(this,"No hay mas usuarios esperando Verificacion", "Exitoso!!", JOptionPane.INFORMATION_MESSAGE);

            agregarProducto.setVisible(true);
            logout.setVisible(true);
            verificar.setVisible(true);
            quejas.setVisible(true);

            verificarUsuario.setVisible(false);
            DenegarUsuario.setVisible(false);
            nombreUsuarioVerificar.setVisible(false);
            currentIndex=0;
        }
    }

}
