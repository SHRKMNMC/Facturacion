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

                        seleccionMenuFacturas = vista.leerOpcionMenu(1, 4);

                        switch (seleccionMenuFacturas) {

                            case 1 -> System.out.println("Nueva factura");
                            case 2 -> System.out.println("Consultar facturas");
                            case 3 -> System.out.println("Mostrar lista de facturas");//CREAR CLIENTE
                            case 4 -> {
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

    //MÉTODOS MENÚ FACTURAS POR ACABAR

    public void crearFactura() {

        boolean reintentar;

        do {
            reintentar = false; // Reiniciamos en cada iteración

            System.out.println("Introduzca DNI del cliente: ");
            String clienteSeleccionado = vista.leerString(9, 9);

            String[] consultado = modelo.consultarCliente(clienteSeleccionado);

            if (consultado == null) {
                System.out.println("Cliente no encontrado en el sistema, ¿volver a intentar?");
                System.out.println(" 1. Sí   2. No");
                int seleccion = vista.leerOpcionMenu(1, 2);

                if (seleccion == 1) {
                    reintentar = true; // Volver a intentar
                }
                // si selecciona 2, reintentar queda false y el bucle termina
            } else {
                System.out.println("Cliente encontrado: " + consultado[0]); // ejemplo: nombre
                // Aquí continuarías con la creación de la factura

                int agregarMas = 0;

                while (agregarMas == 1){

                    System.out.println("Introduce nombre del producto: ");
                    String producto = vista.leerString(1,40);



                }



            }

        } while (reintentar);
    }


}
