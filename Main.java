package Domicilios;

import Domicilios.EstructuraDeDatos.*;
import Domicilios.Productos.Product;
import Domicilios.UserTypes.Admin;
import Domicilios.UserTypes.CurrentUser;
import Domicilios.UserTypes.DeliveryPerson;
import Domicilios.Writers.ProductsWriters;
import Domicilios.Writers.UsersWriters;
import Domicilios.Writers.DeliverysWriters;


import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
                /*
        DeliveryPerson usuario1 = new DeliveryPerson("Santiago",20,"1018584490385","Moto","Hombre","","santiago10","vivamedallo");
        usuario1.addInfoToData();
        Admin admin = new Admin("Avemaria","god","Viva dios");
        admin.addInfoToData();

        // Crear un mapa con la información del producto
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("name", "Arroz con Pollo");
        productInfo.put("quantity", 10);
        productInfo.put("value", 70000);
        productInfo.put("type", "Fast Food");
        productInfo.put("rating",4);

        // Llamar a la función createProduct para crear el producto
        admin.createProduct(productInfo);

        DeliverysWriters deliverysWriters = new DeliverysWriters();
        Queue<Map<String, Object>> domiciliosEsperandoRepartidor = deliverysWriters.deliverysWaitingToDelivery();

        // Mostrar cada elemento de la cola y los mapas en cada elemento de la cola
        Map<String, Object> deliveryInfo = domiciliosEsperandoRepartidor.dequeue();



        System.out.println("Delivery Info:");
        for (Map.Entry<String, Object> entry : deliveryInfo.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            System.out.println(field + ": " + value);
        }

        domiciliosEsperandoRepartidor.toString();

        //admin.userVerification("josemargri3",false);

        Product producto = new Product("Papas",5,10,"Fast Food",5000);

        ProductsWriters writerProductos = new ProductsWriters();

        Product producto1 = new Product(writerProductos.getByKey("Pizza"));

        String user = "josemargri3";
        String password = "manuelateamo";

        UsersWriters writers = new UsersWriters("Users");

        //Hacer funcion que que saque la cola de domicilios faltantessss y asigne el ultimo

        Map<String,Object> info = writers.getByKey(user);

        if(!info.isEmpty()){
            if(info.get("password").equals(password)){
                if(info.get("verificate").equals("Verificado")){
                    System.out.println("Bienvenido");
                    CurrentUser nuevousuario = new CurrentUser(info);
                    System.out.println(nuevousuario.getAge());}
                else{
                    if(info.get("verificate").equals("Por verificar")) {
                        System.out.println("El usuario no ha sido verificado");
                    }
                    else{
                        System.out.println("Ha sido denegada tu entrada");}
                }

            }else{
                System.out.println("Clave incorrecta");
            }
        }else {
            System.out.println("El usuario no existe");
        }

        if(producto1.stockToSell(5)){
            producto1.setQuantity(producto1.getQuantity()-5);
        }else{
            System.out.println("No hay stock");
        }
        producto1.addInfoToData();


        ProductsWriters productos = new ProductsWriters();
        DeliverysWriters deliverysWriters = new DeliverysWriters();
        UsersWriters writer = new UsersWriters("Users");
        CurrentUser ususario = new CurrentUser(writer.getByKey("manuela"));
        UsersWriters writerDomiciliarios = new UsersWriters("Deliverys");

        DeliveryPerson domiciliario = new DeliveryPerson(writerDomiciliarios.getByKey("santiago10"));

        Product producto = new Product(productos.getByKey("Pizza"));
        Product producto1 = new Product(productos.getByKey("Papas"));


        producto.stockToSell(5);
        ususario.addToShoppingCart(producto,5);
        ususario.addToShoppingCart(producto1,2);
        producto1.addInfoToData();
        producto.addInfoToData();
        ususario.shoppingCart.displayList();


        //ususario.doDelivery(deliverysWriters.getLastDeliveryNumber()+1);
        //ususario.shoppingCart.displayList();

        Queue<Map<String, Object>> domiciliosEsperandoRepartidor = deliverysWriters.deliverysWaitingToDelivery();
        String code = domiciliosEsperandoRepartidor.dequeue().get("key").toString();

        //domiciliario.choiceDelivery(code);

        //String code1 = domiciliosEsperandoRepartidor.dequeue().get("key").toString();

        //domiciliario.choiceDelivery(code1);

        System.out.println(domiciliario.getCodeDelivery());
        //domiciliario.deliverToUser();

        Queue<Map<String, Object>> domiciliosEsperandoRepartidor1 = deliverysWriters.deliverysWaitingToDelivery();

        System.out.println("Domicilios esperando repartidor:");
        while (!domiciliosEsperandoRepartidor1.isEmpty()) {
            Map<String, Object> deliveryInfo = domiciliosEsperandoRepartidor1.dequeue();
            String deliveryStatus = deliveryInfo.get("Status").toString();
            if (deliveryStatus.equals("Esperando repartidor")) {
                System.out.println("Delivery Info:");
                for (Map.Entry<String, Object> entry : deliveryInfo.entrySet()) {
                    String field = entry.getKey();
                    Object value = entry.getValue();
                    System.out.println(field + ": " + value);
                }
                System.out.println("--------------------");
            }
        }

    }

        UsersWriters usersWriters = new UsersWriters("Users");
        LinkedList<Map<String, Object>> users = usersWriters.getUsersWithVerificate();

        if (users.isEmpty()) {
            System.out.println("No hay usuarios con la característica 'verificate' igual a 'Por verificar'.");
        } else {
            System.out.println("Usuarios con la característica 'verificate' igual a 'Por verificar':");
            users.displayList();
        }
    }

*/
        ProductsWriters productsWriters = new ProductsWriters();
        ListProduct productList = productsWriters.productos();

        System.out.println("Lista de productos:");
        productList.displayList();
}}