package Controlador;

import Modelo.Modelo;
import Vista.Vista;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

                            case 1 -> crearCliente();            //CREAR CLIENTE
                            case 2 -> consultarCliente();       //CONSULTAR CLIENTE POR DNI
                            case 3 -> vista.mostrarFicheroClientes();  //MOSTRAR FICHERO CLIENTES.TXT
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

                            case 1 -> añadirArticulo();            //CREAR CLIENTE
                            case 2 -> consultarArticulo();        //CONSULTAR CLIENTE POR DNI
                            case 3 -> vista.mostrarFicheroArticulos();   //MOSTRAR FICHERO ARTICULOS.TXT
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
                            case 2 -> consultarFactura(); //CONSULTAR FACTURA POR NÚMERO
                            case 3 -> vista.mostrarFicheroFacturas(); //FICHERO FACTURAS
                            case 4 -> vista.mostrarFicheroLineasFacturas(); //FICHERO LINEAS
                            case 5 -> {
                                System.out.println("Volviendo...");
                                salidaMenuArtículos = true;
                            }
                        }
                    } while (!salidaMenuArtículos);
                }

                case 4 -> {
                    int seleccionMenuConfiguracion;
                    boolean salidaMenuConfiguracion = false;
                    do {

                        vista.menuConfiguración();  //MENU CONFIGURACION

                        seleccionMenuConfiguracion = vista.leerOpcionMenu(1, 3);

                        switch (seleccionMenuConfiguracion) {

                            case 1 -> configurarIVA();
                            case 2 -> consultarIVA();
                            case 3 -> {
                                System.out.println("Volviendo...");
                                salidaMenuConfiguracion = true;
                            }
                        }
                    } while (!salidaMenuConfiguracion);
                }
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

        if (datos != null) {
            modelo.generarCliente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
        } else {
            System.out.println("Operación de creación de cliente cancelada.");
        }
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

    public void añadirArticulo(){

        String[] datos = vista.datosNuevoArticulo();

        if(datos != null){
            modelo.generarArticulo(datos[0],datos[1]);
        }else {
            System.out.println("Operación de creación de artículo cancelada.");
        }
    }


    public void consultarArticulo(){
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

    //MÉTODOS MENÚ FACTURAS

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

    //METODOS MENU CONFIGURACION

    public void configurarIVA() {
        int salidaConfiguracion = 0;

        do {
            System.out.println("Introduzca el nuevo IVA (en porcentaje 0-100):");
            int nuevoIVA = vista.leerOpcionMenu(0, 100);
            modelo.setIVA(nuevoIVA);

            // Reescribe completamente iva.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("iva.txt", false))) {
                writer.write(String.valueOf(nuevoIVA));
                writer.newLine();
                System.out.println("IVA actualizado y guardado correctamente en iva.txt");
            } catch (IOException e) {
                System.out.println("Error al guardar el IVA: " + e.getMessage());
            }

            System.out.println("IVA actualizado correctamente. Las próximas facturas usarán " + nuevoIVA + "%");
            System.out.println("Introduzca 1 para terminar, 2 para repetir");
            salidaConfiguracion = vista.leerOpcionMenu(1,2);

        } while (salidaConfiguracion != 1);
    }

    public void consultarIVA(){
        int salidaMuestraFicheros = 0;
        do {
            System.out.println("IVA actual al: " + modelo.getIVA() + " %");
            System.out.println("Introduzca 1 para salir");
            salidaMuestraFicheros = vista.leerOpcionMenu(1,1);
        } while (salidaMuestraFicheros != 1);
    }

}
