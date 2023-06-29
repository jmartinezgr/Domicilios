    package Domicilios.Interfaces;

    import Domicilios.EstructuraDeDatos.ListProduct;
    import Domicilios.UserTypes.Admin;
    import Domicilios.UserTypes.CurrentUser;
    import Domicilios.Writers.DeliverysWriters;
    import Domicilios.Writers.ProductsWriters;
    import Domicilios.Writers.UsersWriters;

    import Domicilios.Productos.Product;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.Arrays;
    import java.util.List;
    import java.util.Map;

    public class UserInterface extends JFrame implements ActionListener {
        private Container contenedor;
        private MainApp mainApp;
        private CurrentUser usuario;
        private JComboBox<String> comboBox;
        private ListProduct ListaProductos;

        private JButton agregarProducto,pedido,vaciar,logout,agregarCarrito;
        private JLabel cantidad;
        private JTextField cantidadF;

        public UserInterface(Map<String,Object> usuario) {
            this.usuario = new CurrentUser(usuario);
            setTitle("User");
            setSize(450, 380);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio();
            ProductsWriters productsWriters = new ProductsWriters();
            ListaProductos = productsWriters.productos();
        }

        private void inicio(){
            contenedor = getContentPane();
            contenedor.setLayout(null);

            //Botones Principales
            agregarProducto = new JButton("Agregar Producto");
            agregarProducto.setBounds(150,70,150,23);
            agregarProducto.addActionListener(this);
            contenedor.add(agregarProducto);

            pedido = new JButton("Hacer Pedido");
            pedido.setBounds(150,110,150,23);
            pedido.addActionListener(this);
            contenedor.add(pedido);

            vaciar = new JButton("Vaciar Carrito");
            vaciar.setBounds(150,150,150,23);
            vaciar.addActionListener(this);
            contenedor.add(vaciar);

            logout = new JButton("Cerrar Sesion");
            logout.setBounds(150,190,150,23);
            logout.addActionListener(this);
            contenedor.add(logout);

            //Agregar producto

            comboBox = new JComboBox<>();
            comboBox.setBounds(80,100,100,23);
            comboBox.setVisible(false);
            contenedor.add(comboBox);

            cantidad = new JLabel("Cantidad:");
            cantidad.setBounds(190,100,60,23);
            cantidad.setVisible(false);
            contenedor.add(cantidad);

            cantidadF = new JTextField();
            cantidadF.setBounds(260,100,120,23);
            cantidadF.setVisible(false);
            contenedor.add(cantidadF);

            agregarCarrito = new JButton("Agregar");
            agregarCarrito.setBounds(160,200,80,23);
            agregarCarrito.addActionListener(this);
            agregarCarrito.setVisible(false);
            contenedor.add(agregarCarrito);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == logout){
                mainApp.showLoginInterface();
                dispose();
            } else if (e.getSource() == agregarProducto) {

                setTitle("Agregar Producto");

                agregarProducto.setVisible(false);
                pedido.setVisible(false);
                vaciar.setVisible(false);
                logout.setVisible(false);

                ProductsWriters productsWriters = new ProductsWriters();
                ListaProductos = productsWriters.productos();
                ListaProductos.sort();

                comboBox.removeAllItems();
                for (String productName : ListaProductos.getProductNames()) {
                    comboBox.addItem(productName);
                }

                comboBox.setVisible(true);
                cantidad.setVisible(true);
                cantidadF.setVisible(true);
                agregarCarrito.setVisible(true);
            } else if (e.getSource() == agregarCarrito) {

                List<String> ayuda = Arrays.asList(ListaProductos.getProductNames());

                String seleccionado = (String) comboBox.getSelectedItem();

                int indice = ayuda.indexOf(seleccionado);

                Product product = ListaProductos.getNodeAtIndex(indice).getValue();

                int cantidad = Integer.parseInt(cantidadF.getText());

                if(product.stockToSell(cantidad)){
                    usuario.addToShoppingCart(product,cantidad);
                    JOptionPane.showMessageDialog(null, "Se han agreado "+cantidad+" unidades de "+seleccionado, "Agreado!!", JOptionPane.INFORMATION_MESSAGE);

                    agregarProducto.setVisible(true);
                    pedido.setVisible(true);
                    vaciar.setVisible(true);
                    logout.setVisible(true);

                    this.cantidadF.setText("");
                    this.cantidadF.setVisible(false);
                    this.cantidad.setVisible(false);
                    agregarCarrito.setVisible(false);
                    comboBox.setVisible(false);

                }else{
                    JOptionPane.showMessageDialog(null, "No hay suficiente stock", "Cantidad Insuficiente", JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == pedido) {
                if(!usuario.shoppingCart.isEmpty()){
                    DeliverysWriters deliverysWriters = new DeliverysWriters();
                    int numero = deliverysWriters.getLastDeliveryNumber()+1;
                    JOptionPane.showMessageDialog(null, "Domicilio con codigo D-"+numero+" \n Valor Total: "+usuario.carritoValue() , "Pedido Realizado", JOptionPane.INFORMATION_MESSAGE);

                    int currentIndex = 0;

                    while(currentIndex < usuario.shoppingCart.size()){
                        String nombre = usuario.shoppingCart.getNodeAtIndex(currentIndex).getValue().getName();
                        ProductsWriters productsWriters = new ProductsWriters();
                        Product productoAuxiliar = new Product(productsWriters.getByKey(nombre));
                        productoAuxiliar.setQuantity(productoAuxiliar.getQuantity() - usuario.quantities.getNodeAtIndex(currentIndex).getValue());
                        productoAuxiliar.addInfoToData();
                        currentIndex++;
                    }
                    usuario.doDelivery(numero);
                    usuario.shoppingCart.clear();}
                else{
                    JOptionPane.showMessageDialog(null, "El carrito esta vacio, agrega algun producto" , "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == vaciar) {
                usuario.shoppingCart.clear();
                JOptionPane.showMessageDialog(null, "Se ha vaciado el carrito" , "Carrito vacio", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        public void setMainApp(MainApp mainApp) {
            this.mainApp = mainApp;
        }

    }
