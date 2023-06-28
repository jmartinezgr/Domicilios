package Domicilios.Interfaces;

import Domicilios.Writers.ProductsWriters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AdminInterface extends JFrame implements ActionListener {
    private MainApp mainApp = new MainApp();
    private Container contenedor;

    private JButton agregarProducto,verificar,quejas,logout,crearProducto;
    private JLabel nombreProducto,cantidadProducto,raitingProducto,tipoProducto,valorProducto;
    private JTextField nombreProductoF,cantidadProductoF,raitingProductoF,tipoProductoF,valorProductoF;

    public AdminInterface() {
        setTitle("Admin");
        setSize(450, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio();
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
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminInterface adminInterface = new AdminInterface();
                adminInterface.setVisible(true);
            }
        });
    }
}
