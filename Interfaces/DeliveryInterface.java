package Domicilios.Interfaces;

import Domicilios.EstructuraDeDatos.Queue;
import Domicilios.UserTypes.DeliveryPerson;
import Domicilios.Writers.DeliverysWriters;
import Domicilios.Writers.UsersWriters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;

public class DeliveryInterface extends JFrame implements ActionListener {
    private Container contenedor;
    private MainApp mainApp;
    private JList<String> myList;
    private DefaultListModel<String> listModel;
    private DeliveryPerson domiciliario;

    private JLabel codigo,status,valor,usuario;

    private JTextArea lista;

    private JButton showInfo,hacerDomicilio,entregarDomicilio,logout;

    private DeliverysWriters writer = new DeliverysWriters();

    private Queue<Map<String,Object>> colaDomicilios ;

    public DeliveryInterface(Map<String,Object> domiciliario){
        this.domiciliario = new DeliveryPerson(domiciliario);
        setTitle("Domicilios");
        setSize(450, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio();
    }

    private void inicio(){
        contenedor = getContentPane();
        contenedor.setLayout(null);

        listModel = new DefaultListModel<>();
        myList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(myList);
        scrollPane.setBounds(10, 10, 200, 275);
        contenedor.add(scrollPane);

        codigo = new JLabel("Codigo: ");
        codigo.setBounds(220,10,50,23);
        contenedor.add(codigo);

        usuario = new JLabel("Usuario: ");
        usuario.setBounds(220,40,200,23);
        contenedor.add(usuario);

        lista = new JTextArea();
        lista.setBounds(220, 65, 200, 135);
        lista.setEditable(false);
        JScrollPane scrollPaneLista = new JScrollPane(lista);
        scrollPaneLista.setBounds(220, 65, 200, 135);
        contenedor.add(scrollPaneLista);

        status = new JLabel("Estado: ");
        status.setBounds(220,200,200,23);
        contenedor.add(status);

        valor = new JLabel("Valor: ");
        valor.setBounds(220,240,200,23);
        contenedor.add(valor);

        hacerDomicilio = new JButton("Elegir");
        hacerDomicilio.setBounds(20,300,100,23);
        hacerDomicilio.addActionListener(this);
        contenedor.add(hacerDomicilio);

        showInfo = new JButton("Mostrar Info");
        showInfo.setBounds(140,300,120,23);
        showInfo.addActionListener(this);
        contenedor.add(showInfo);

        entregarDomicilio = new JButton("Entregar Domicilio");
        entregarDomicilio.setBounds(280,300,140,23);
        entregarDomicilio.addActionListener(this);
        contenedor.add(entregarDomicilio);

        logout = new JButton("Cerrar Sesion");
        logout.setBounds(280,10,150,23);
        logout.addActionListener(this);
        contenedor.add(logout);

        cargarInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == showInfo){
            String key = myList.getSelectedValue();

            Map<String,Object> info = writer.getByKey(key);

            codigo.setText("Codigo: "+info.get("key"));
            usuario.setText("Usuario: "+info.get("User"));
            valor.setText("Valor: "+info.get("Value"));
            status.setText("Estado: "+info.get("Status"));

            String productListString = (String) info.get("ProductList");
            String[] elementosArray = productListString.split("->");
            StringBuilder elementos = new StringBuilder();
            for (String elemento : elementosArray) {
                String[] partes = elemento.trim().split("\\(");
                String nombre = partes[0].trim();
                int cantidad = Integer.parseInt(partes[1].replace(")", "").trim());
                elementos.append(nombre).append(" (").append(cantidad).append(")\n");
            }
            lista.setText("Elementos:\n" + elementos.toString());

            cargarInfo();

        } else if (e.getSource() == hacerDomicilio) {
            if(domiciliario.getCodeDelivery().equals("")){
                String key = myList.getSelectedValue();
                domiciliario.choiceDelivery(key);
                JOptionPane.showMessageDialog(null, "Domicilio "+key+" selecionado", "Exitoso!!", JOptionPane.INFORMATION_MESSAGE);
                cargarInfo();
            }else{
                JOptionPane.showMessageDialog(null, "Ya tienes un domicilio, entregalo y podras elegir otro", "Error", JOptionPane.ERROR_MESSAGE);
                cargarInfo();
            }
        } else if (e.getSource() == entregarDomicilio) {
            if (!domiciliario.getCodeDelivery().equals("")) {
                domiciliario.deliverToUser();
                JOptionPane.showMessageDialog(null, "Domicilio Entregado con exito", "Exitoso!!", JOptionPane.INFORMATION_MESSAGE);
                cargarInfo();

            }else{
                JOptionPane.showMessageDialog(null, "Debes elegir un domicilio para poder entregar uno", "Error", JOptionPane.ERROR_MESSAGE);
                cargarInfo();
            }
        } else if (e.getSource() == logout) {
            mainApp.showLoginInterface();
            dispose();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void cargarInfo(){
        colaDomicilios = writer.deliverysWaitingToDelivery();
        listModel.clear();
        if(!colaDomicilios.isEmpty()){

            while (!colaDomicilios.isEmpty()){
                Map<String,Object> info = colaDomicilios.dequeue();
                listModel.addElement((String) info.get("key"));
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay domicilios aun", "Sin domicilios", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
