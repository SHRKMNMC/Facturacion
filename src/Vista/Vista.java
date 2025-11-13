package Vista;

import java.io.*;
import java.util.Scanner;

public class Vista {

    //SCANNER GLOBAL PARA TODOS LOS INPUTS DE VISTA

    private final Scanner scanner;

    public Vista (Scanner scanner){
        this.scanner = scanner;
    }

    //VARIABLE PARA SELECCIONAR OPCIONES

    int seleccionMenu;

    //MENU PRINCIPAL

    public void mostrarMenu(){

            System.out.println("===============================================");
            System.out.println();
            System.out.println("            1. Gestión de Clientes             ");
            System.out.println("            2. Gestión de Artículos            ");
            System.out.println("            3. Gestión de Facturas             ");
            System.out.println("            4. Configuración                   ");
            System.out.println("            5. Salir                           ");
            System.out.println();
            System.out.println("   Introduzca el número de la opción deseada   ");
            System.out.println();
            System.out.println("===============================================");
    }

    //MENUS CLIENTES, ARTÍCULOS

    public void menuClientes(){

        System.out.println("===============================================");
        System.out.println();
        System.out.println("            1. Añadir Cliente                  ");
        System.out.println("            2. Consultar Cliente               ");
        System.out.println("            3. Lista de Clientes               ");
        System.out.println("            4. Salir                           ");
        System.out.println();
        System.out.println("   Introduzca el número de la opción deseada   ");
        System.out.println();
        System.out.println("===============================================");
    }

    public void menuArtículos(){

        System.out.println("===============================================");
        System.out.println();
        System.out.println("            1. Añadir Artículo                 ");
        System.out.println("            2. Consultar Artículo              ");
        System.out.println("            3. Lista de Artículos              ");
        System.out.println("            4. Salir                           ");
        System.out.println();
        System.out.println("   Introduzca el número de la opción deseada   ");
        System.out.println();
        System.out.println("===============================================");
    }

    public void menuFacturas(){
        System.out.println("===============================================");
        System.out.println();
        System.out.println("            1. Nueva Factura                   ");
        System.out.println("            2. Consultar Facturas              ");
        System.out.println("            3. Lista de Facturas               ");
        System.out.println("            4. Lista de Lineas de facturas     ");
        System.out.println("            5. Salir                           ");
        System.out.println();
        System.out.println("   Introduzca el número de la opción deseada   ");
        System.out.println();
        System.out.println("===============================================");
    }

    //METODOS PARA RECOPILAR DATOS DE CLIENTES

    public String[] datosNuevoCliente(){

        String[] datos = new String[7];

        int confirmar = 0;

        do {

            //NOMBRE
            System.out.println("Introduzca nombre completo:");
            datos[0] = leerString(1,40);

            //DNI
            System.out.println("Introduzca DNI:");
            datos[1] = leerString(9,9);

            //POBLACION
            System.out.println("Población:");
            datos[2]= leerString(1,20);

            //DIRECCION
            System.out.println("Dirección:");
            datos[3] = leerString(1,40);

            //CODIGO POSTAL
            System.out.println("Código postal:");
            datos[4] = leerString(5,5);

            //PROVINCIA
            System.out.println("Provincia:");
            datos[5]= leerString(1,20);

            //TELEFONO
            System.out.println("Número de teléfono:");
            datos[6] = leerString(9,9);

            System.out.println("|||||| Datos introducidos |||||| ");
            System.out.println("|");
            System.out.println("|Nombre: "+ datos[0]);
            System.out.println("|DNI: "+datos[1]);
            System.out.println("|Población: "+datos[2]);
            System.out.println("|Dirección: "+datos[3]);
            System.out.println("|Código postal: "+datos[4]);
            System.out.println("|Provincia: "+datos[5]);
            System.out.println("|Teléfono: "+datos[6]);
            System.out.println("|______________________________");
            System.out.println("Introduzca 1 para confirmar datos, 2 para repetir");

            confirmar = leerOpcionMenu(1,2);

            if (confirmar == 2){
                System.out.println("Repitiendo operación...");
            }

        }while (confirmar !=1);

        return datos;
    }

    //METODO PARA RECOPILAR DATOS DEL ARTÍCULO

    public String[] datosNuevoArticulo(){

        String[] datos = new String[2];

        int confirmar = 0;

        do {

            //NOMBRE
            System.out.println("Introduzca nombre del producto:");
            datos[0] = leerString(1,40);

            //PRECIO
            System.out.println("Introduzca precio del producto");
            datos[1] = leerStringToDouble(1,6);

            System.out.println("|||||| Datos introducidos |||||| ");
            System.out.println("|");
            System.out.println("|Nombre: "+ datos[0]);
            System.out.println("|Precio: "+datos[1]);
            System.out.println("|______________________________");
            System.out.println("Introduzca 1 para confirmar datos, 2 para repetir");

            confirmar = leerOpcionMenu(1,2);

            if (confirmar == 2){
                System.out.println("Repitiendo operación...");
            }

        }while (confirmar !=1);

        return datos;
    }

    //METODO PARA MOSTRAR CLIENTE CONCRETO EN CONSOLA

    public void mostrarCliente(String[] datosClienteConsultado) {
        if (datosClienteConsultado == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        String[] campos = {"Nombre", "DNI", "Población", "Dirección", "Código Postal", "Provincia", "Teléfono"};

        for (int i = 0; i < campos.length; i++) {
            System.out.println(campos[i] + ": " + datosClienteConsultado[i]);
        }
    }

    //METODO PARA MOSTRAR LOS CLIENTES ACTUALES

    public void mostrarFicheroClientes() {

        System.out.println("======= Lista de clientes =======");
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        System.out.println("================================");
    }

    //METODO PARA MOSTRAR LAS FACTURAS GUARDADAS

    public void mostrarFicheroFacturas() {

        System.out.println("====== Facturas guardadas =======");
        try (BufferedReader reader = new BufferedReader(new FileReader("facturas.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        System.out.println("================================");
    }

    //METODO PARA MOSTRAR FACTURA CONCRETA

    public void mostrarFactura(String[] datosFacturaConsultada) {
        if (datosFacturaConsultada == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        String[] campos = {"Número", "Fecha", "Cliente", "IVA aplicado", "Precio final"};

        for (int i = 0; i < campos.length; i++) {
            System.out.println(campos[i] + ": " + datosFacturaConsultada[i]);
        }
    }


    //METODO PARA MOSTRAR LAS LINEAS DE FACTURA GUARDADAS

    public void mostrarFicheroLineasFacturas() {

        System.out.println("======= Lineas guardadas =======");
        try (BufferedReader reader = new BufferedReader(new FileReader("lineas_factura.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        System.out.println("================================");
    }


    //METODO PARA MOSTRAR ARTÍCULO CONCRETO EN CONSOLA

    public void mostrarArtículo(String[] datosArticuloConsultado) {
        if (datosArticuloConsultado == null) {
            System.out.println("Artículo no encontrado.");
            return;
        }

        String[] campos = {"Nombre", "Precio"};

        for (int i = 0; i < campos.length; i++) {
            System.out.println(campos[i] + ": " + datosArticuloConsultado[i]);
        }
    }

    //METODO PARA MOSTRAR LOS ARTÍCULOS ACTUALES

    public void mostrarFicheroArticulos() {

        System.out.println("====== Lista de artículos ======");
        try (BufferedReader reader = new BufferedReader(new FileReader("articulos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        System.out.println("================================");
    }

    //METODO PARA CONTROLAR INPUTS NUMÉRICOS DE LOS MENUS

    public int leerOpcionMenu(int min, int max) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int numero = Integer.parseInt(input);
                if (numero < min || numero > max) {
                    System.out.print("Opción fuera de rango, intente de nuevo: ");
                    continue;
                }
                return numero;
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Introduzca un número: ");
            }
        }
    }

    //METODO PARA CONTROLAR INPUTS QUE SEAN STRING

    public String leerString(int min, int max) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.contains(";")) {
                System.out.print("Entrada no válida: no se permiten ';'. Intente de nuevo: ");
                continue;
            }
            if (input.length() < min || input.length() > max) {
                System.out.print("Longitud inválida (Mínimo " + min + "- Máximo " + max + "). Intente de nuevo: ");
                continue;
            }
            return input;
        }
    }

    //METODO PARA COMPROBAR Y FORZAR LA ENTRADA DE INPUT STRING Y QUE SEA UN VALOR NUMÉRICO (EJ.: EL PRECIO)

    public String leerStringToDouble(int min, int max) {
        while (true) {
            System.out.print("Ingrese un precio: ");
            String input = scanner.nextLine().trim();

            // Valida que no contenga ';'
            if (input.contains(";")) {
                System.out.println("Entrada no válida: no se permiten ';'. Intente de nuevo.");
                continue;
            }

            // Valida que se pueda convertir a double
            double valor;
            try {
                valor = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida: debe ser un número. Intente de nuevo.");
                continue;
            }

            // Valida longitud del string original
            if (input.length() < min || input.length() > max) {
                System.out.println("Longitud inválida (Mínimo " + min + " - Máximo " + max + "). Intente de nuevo.");
                continue;
            }

            // Valida que tenga máximo 2 decimales
            if (input.contains(".")) {
                String[] partes = input.split("\\.");
                String decimales = partes.length > 1 ? partes[1] : "";
                if (decimales.length() > 2) {
                    System.out.println("Entrada no válida: el número no puede tener más de 2 decimales. Intente de nuevo.");
                    continue;
                }
            }

            // Si pasa todas las validaciones, lo devolvuelve como String
            return input;
        }
    }

    public String leerFecha() {
        int dia = 0, mes = 0, anio = 0;

        while (true) {
            try {
                System.out.print("Introduce el día (1-31): ");
                dia = Integer.parseInt(scanner.nextLine().trim());
                if (dia < 1 || dia > 31) {
                    System.out.println("Día inválido. Intente de nuevo.");
                    continue;
                }

                System.out.print("Introduce el mes (1-12): ");
                mes = Integer.parseInt(scanner.nextLine().trim());
                if (mes < 1 || mes > 12) {
                    System.out.println("Mes inválido. Intente de nuevo.");
                    continue;
                }

                System.out.print("Introduce el año (por ejemplo 2025): ");
                anio = Integer.parseInt(scanner.nextLine().trim());
                if (anio < 1900 || anio > 2100) {
                    System.out.println("Año inválido. Intente de nuevo.");
                    continue;
                }

                // Validar que el día sea válido para el mes (sin años bisiestos por simplicidad)
                if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
                    System.out.println("El mes seleccionado tiene máximo 30 días. Intente de nuevo.");
                    continue;
                } else if (mes == 2 && dia > 29) { // Febrero simplificado
                    System.out.println("Febrero tiene máximo 29 días. Intente de nuevo.");
                    continue;
                }

                //Formateo
                return String.format("%02d-%02d-%04d", dia, mes, anio);

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, debe ser un número. Intente de nuevo.");
            }
        }
    }


}

