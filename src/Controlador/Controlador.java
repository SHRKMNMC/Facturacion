package Controlador;

import Modelo.Modelo;
import Vista.Vista;

import java.util.ArrayList;
import java.util.List;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista){

         this.modelo = modelo;
         this.vista = vista;

    }

    public void Start(){

        int seleccionMenu;
        boolean salida = false;

        do {

            modelo.persistencia();  //METODO QUE RECUPERA LOS CLIENTES DEL FICHERO

            vista.mostrarMenu();   //MENU PRINCIPAL

            seleccionMenu = vista.leerOpcionMenu(1,5);

            switch(seleccionMenu){

                case 1 -> {
                    int seleccionMenuClientes;
                    boolean salidaMenuClientes = false;
                    do {

                        vista.menuClientes();  //MENU CLIENTES

                        seleccionMenuClientes = vista.leerOpcionMenu(1, 4);

                        switch (seleccionMenuClientes) {

                            case 1 -> crearCliente();       //CREAR CLIENTE
                            case 2 -> consultarCliente();   //CONSULTAR CLIENTE POR DNI
                            case 3 -> mostrarListaClientes();       //MOSTRAR FICHERO CLIENTES.TXT
                            case 4 -> {
                                System.out.println("Volviendo...");
                                salidaMenuClientes = true;
                            }
                        }
                    } while (!salidaMenuClientes);
                }


                case 2 -> {
                    int seleccionMenuArtículos;
                    boolean salidaMenuArtículos = false;
                    do {

                        vista.menuArtículos();  //MENU ARTÍCULOS

                        seleccionMenuArtículos = vista.leerOpcionMenu(1, 4);

                        switch (seleccionMenuArtículos) {

                            case 1 -> añadirArtículo();       //CREAR CLIENTE
                            case 2 -> consultarArtículo();   //CONSULTAR CLIENTE POR DNI
                            case 3 -> mostrarListaArticulos();       //MOSTRAR FICHERO ARTICULOS.TXT
                            case 4 -> {
                                System.out.println("Volviendo...");
                                salidaMenuArtículos = true;
                            }
                        }
                    } while (!salidaMenuArtículos);
                }

                case 3 -> {
                    int seleccionMenuFacturas;
                    boolean salidaMenuArtículos = false;
                    do {

                        vista.menuFacturas();  //MENU FACTURAS

                        seleccionMenuFacturas = vista.leerOpcionMenu(1, 5);

                        switch (seleccionMenuFacturas) {

                            case 1 -> crearFactura(); // NUEVA FACTURA
                            case 2 -> System.out.println("Consultar facturas");
                            case 3 -> vista.mostrarFicheroFacturas();
                            case 4 -> vista.mostrarFicheroLineasFacturas();//CREAR CLIENTE
                            case 5 -> {
                                System.out.println("Volviendo...");
                                salidaMenuArtículos = true;
                            }
                        }
                    } while (!salidaMenuArtículos);
                }

                case 4 -> System.out.println("➡ Configuración");
                case 5 -> {
                    System.out.println("Saliendo...");
                    salida = true;
                }
            }

        }while (!salida);

    }

    //METODOS MENU CLIENTES

    public void crearCliente(){

        String[] datos = vista.datosNuevoCliente();

        modelo.generarCliente(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],datos[6]);

    }

    public void mostrarListaClientes(){

        int salidaMuestraFicheros = 0;
        do {
            vista.mostrarFicheroClientes();
            System.out.println("Introduzca 1 para salir");
            salidaMuestraFicheros = vista.leerOpcionMenu(1,1);
        } while (salidaMuestraFicheros != 1);

    }

    public void consultarCliente(){
        int salidaConsultas = 0;
        do {
            System.out.println("Introduzca DNI del cliente a consultar");
            String dniCliente = vista.leerString(9,9);
            String[] datosConsulta = modelo.consultarCliente(dniCliente);
            vista.mostrarCliente(datosConsulta);
            System.out.println("Introduzca 1 para salir");
            salidaConsultas = vista.leerOpcionMenu(1,1);
        }while (salidaConsultas != 1);
    }

    //METODOS MENU ARTÍCULOS

    public void añadirArtículo(){

        String[] datos = vista.datosNuevoArticulo();

        modelo.generarArtículo(datos[0],datos[1]);

    }

    public void mostrarListaArticulos(){

        int salidaMuestraFicheros = 0;
        do {
            vista.mostrarFicheroArticulos();
            System.out.println("Introduzca 1 para salir");
            salidaMuestraFicheros = vista.leerOpcionMenu(1,1);
        } while (salidaMuestraFicheros != 1);

    }

    public void consultarArtículo(){
        int salidaConsultas = 0;
        do {
            System.out.println("Introduzca nombre del artículo a consultar");
            String nombreArticulo = vista.leerString(0,40);
            String[] datosConsulta = modelo.consultarArtículo(nombreArticulo);
            vista.mostrarArtículo(datosConsulta);
            System.out.println("Introduzca 1 para salir");
            salidaConsultas = vista.leerOpcionMenu(1,1);
        }while (salidaConsultas != 1);
    }

    public void consultarFactura(){
        int salidaConsultas = 0;
        do {
            System.out.println("Introduzca numero de la factura a consultar");
            String numeroFactura = vista.leerString(0,40);
            String[] datosConsulta = modelo.consultarFacturas(numeroFactura);
            vista.mostrarFactura(datosConsulta);
            System.out.println("Introduzca 1 para salir");
            salidaConsultas = vista.leerOpcionMenu(1,1);
        }while (salidaConsultas != 1);
    }

    //MÉTODOS MENÚ FACTURAS POR ACABAR

    public void crearFactura() {

        boolean salidaCreacionFacturas;

        do {
            salidaCreacionFacturas = false;

            System.out.println("Introduzca DNI del cliente: ");
            String clienteSeleccionado = vista.leerString(9, 9);

            String[] consultado = modelo.consultarCliente(clienteSeleccionado);

            if (consultado == null) {
                System.out.println("Cliente no encontrado en el sistema, ¿volver a intentar?");
                System.out.println(" 1. Sí   2. No");
                int seleccion = vista.leerOpcionMenu(1, 2);
                if (seleccion == 1) salidaCreacionFacturas = true;

            } else {

                System.out.println("Cliente encontrado: " + consultado[0]);

                ArrayList<String> productos = new ArrayList<>();
                ArrayList<Integer> cantidades = new ArrayList<>();

                boolean agregarMas = true;
                boolean cancelarFactura = false;

                while (agregarMas) {

                    if (productos.size() >= 10) {
                        System.out.println("No puede agregar más productos. Límite de 10 alcanzado.");
                        break;
                    }

                    System.out.println("Introduce nombre del producto: ");
                    String producto = vista.leerString(1, 40);

                    String[] productoConsultado = modelo.consultarArtículo(producto);

                    if (productoConsultado == null) {
                        System.out.println("Artículo no encontrado en el sistema, ¿volver a intentar?");
                        System.out.println(" 1. Sí   2. No");
                        int seleccion = vista.leerOpcionMenu(1, 2);

                        if (seleccion == 1) continue;
                        else {
                            cancelarFactura = true;
                            break;
                        }

                    } else {
                        productos.add(producto);

                        System.out.println("Introduce cantidad del producto:");
                        int cantidad = vista.leerOpcionMenu(1, 999);
                        cantidades.add(cantidad);

                        if (productos.size() >= 10) {
                            System.out.println("Se alcanzó el máximo de 10 productos.");
                            break;
                        }

                        System.out.println("¿Agregar otro producto?    1.Sí    2.No");
                        agregarMas = vista.leerOpcionMenu(1, 2) == 1;
                    }
                }

                // Si se canceló la factura, volvemos al menú directamente
                if (cancelarFactura || productos.isEmpty()) {
                    System.out.println("Creación de factura cancelada. Volviendo al menú...");
                    continue; // Vuelve al inicio del bucle 'do...while'
                }

                System.out.println("Introduzca fecha:");
                String fecha = vista.leerFecha();

                modelo.generarFactura(fecha, clienteSeleccionado, productos, cantidades);

                System.out.println("Factura creada correctamente.");
            }

        } while (salidaCreacionFacturas);
    }

}
