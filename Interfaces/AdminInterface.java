package Domicilios.Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminInterface extends JFrame implements ActionListener {
    private MainApp mainApp = new MainApp();
    private Container contenedor;

    private JButton agregarProducto,verificar,quejas,logout;
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

        nombreProducto = new JLabel("Nombre");
        nombreProducto.setBounds(100,50,80,23);
        contenedor.add(nombreProducto);
        nombreProducto.setVisible(false);

        nombreProductoF = new JTextField();
        nombreProductoF.setBounds(200,50,100,23);
        nombreProductoF.setVisible(false);
        contenedor.add(nombreProductoF);

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
