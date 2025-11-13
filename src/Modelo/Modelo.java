package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

    private List<Cliente> clientesList;
    private List<Articulo> articulosList;
    private List<Factura> facturasList;

    public Modelo(){

        this.clientesList = new ArrayList<>();
        this.articulosList = new ArrayList<>();
        this.facturasList = new ArrayList<>();
    }

    //MÉTODOS GENERADORES

    public void generarCliente(String nombre,String dni,String poblacion, String direccion, String codigoPostal, String provincia, String telefono){

        //Comprueba que no exista ya un cliente con el mismo dni(PK)

        boolean existe = clientesList.stream().anyMatch(c -> c.getDni().equalsIgnoreCase(dni));

        if (existe) {
            System.out.println("Error: ya existe un cliente con DNI " + dni);
            return; // No se agrega ni guarda
        }

        //Genera instancia

        Cliente cliente = new Cliente(nombre,dni,poblacion,direccion,codigoPostal,provincia,telefono);

        clientesList.add(cliente);

        // Guarda en fichero
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            // La opción 'true' en FileWriter es para añadir al fichero sin borrar lo anterior
            writer.write(nombre + ";" + dni + ";" + poblacion + ";" + direccion + ";" + codigoPostal + ";" + provincia + ";" + telefono);
            writer.newLine(); // NUEVA LINEA PARA CADA CLIENTE
            System.out.println("Cliente guardado correctamente");
        } catch (IOException e) {
            System.out.println("Error al guardar el cliente: " + e.getMessage());
        }
    }

    public void generarArtículo(String nombre, String precio){

        //Comprueba que no exista ya un artículo con el mismo nombre

        boolean existe = articulosList.stream().anyMatch(c -> c.getNombre().equalsIgnoreCase(nombre));

        if (existe) {
            System.out.println("Error: ya existe este artículo en el sistema: " + nombre);
            return; // No se agrega ni guarda
        }

        //Genera instancia

        double precioInstancia = Double.parseDouble(precio);

        Articulo articulo = new Articulo(nombre,precioInstancia);

        articulosList.add(articulo);

        // Guarda en fichero

        String precioFormateado = String.format("%.2f", precioInstancia); // "12.50"

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("articulos.txt", true))) {
            // La opción 'true' en FileWriter es para añadir al fichero sin borrar lo anterior
            writer.write(articulo.getCodigoArticulo() + ";" + nombre + ";" + precioFormateado);
            writer.newLine(); // NUEVA LINEA PARA CADA CLIENTE
            System.out.println("Artículo agregado correctamente");
        } catch (IOException e) {
            System.out.println("Error al guardar el artículo: " + e.getMessage());
        }
    }

    public void generarFactura(String fecha, String dniCliente,
                               ArrayList<String> productos, ArrayList<Integer> cantidades) {

        // Obtener cliente
        Cliente cliente = obtenerCliente(dniCliente);
        if (cliente == null) {
            System.out.println("No se puede generar la factura. Cliente no encontrado.");
            return;
        }

        // Crear instancia de factura
        Factura factura = new Factura(fecha, cliente);

        // Crear líneas de factura (máximo 10)
        ArrayList<LineaFacturas> lineas = new ArrayList<>();
        for (int i = 0; i < productos.size() && i < 10; i++) {
            String nombreProducto = productos.get(i);
            int cantidad = cantidades.get(i);

            Articulo articulo = obtenerArticulo(nombreProducto);
            if (articulo != null) {
                LineaFacturas linea = new LineaFacturas(articulo, cantidad);
                factura.agregarLineaFactura(linea);
                lineas.add(linea);  // Para escribir en archivo separado
            } else {
                System.out.println("El producto '" + nombreProducto + "' no se encontró en el sistema.");
            }
        }

        // Guardar info general en facturas.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("facturas.txt", true))) {
            writer.write(factura.getNumeroFactura() + ";" + fecha + ";" + cliente.getNombre() + ";" + cliente.getDni() +";"+factura.getIva());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la factura: " + e.getMessage());
        }

        // Guardar info detallada en lineas_factura.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("lineas_factura.txt", true))) {
            for (LineaFacturas linea : lineas) {
                Articulo art = linea.getArticulo();
                // Guardamos código de factura para relacionarlo
                writer.write(factura.getNumeroFactura() + ";" + art.getCodigoArticulo() + ";" + art.getNombre() + ";" +
                        linea.getCantidadArtículo() + ";" + linea.calcularCantidadPrecio());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las líneas de la factura: " + e.getMessage());
        }

        factura.MostrarInfo();
        System.out.println("Factura y líneas guardadas correctamente.");
    }

    public Articulo obtenerArticulo(String nombreArticulo) {
        for (Articulo a : articulosList) {
            if (a.getNombre().equals(nombreArticulo)) {
                System.out.println("Artículo encontrado");
                return a;
            }
        }
        System.out.println("Artículo no encontrado");
        return null;
    }

    public Cliente obtenerCliente(String dniCliente) {
        for (Cliente c : clientesList) {
            if (c.getDni().equals(dniCliente)) {
                System.out.println("Cliente encontrado");
                return c;
            }
        }
        System.out.println("Cliente no encontrado");
        return null;
    }

    //MÉTODOS PARA CONSULTAS

    public String[] consultarCliente(String dni) {
        for (Cliente c : clientesList) {
            if (c.getDni().equals(dni)) {
                System.out.println("Cliente encontrado");
                // Devolvemos un array con todos los datos
                return new String[] {
                        c.getNombre(),
                        c.getDni(),
                        c.getPoblacion(),
                        c.getDireccion(),
                        c.getCodigoPostal(),
                        c.getProvincia(),
                        c.getTelefono()
                };
            }
        }
        System.out.println("Cliente no encontrado");
        return null; // Retornamos null si no se encuentra
    }

    public String[] consultarArtículo(String nombreArticulo) {
        for (Articulo a : articulosList) {
            if (a.getNombre().equals(nombreArticulo)) {
                System.out.println("Artículo encontrado");
                String precioFormateado = String.format("%.2f", a.getPrecio()); // "12.50"

                // Devolvemos un array con todos los datos
                return new String[] {
                        a.getNombre(),
                        precioFormateado
                };
            }
        }
        System.out.println("Artículo no encontrado");
        return null; // Retornamos null si no se encuentra
    }

    public String[] consultarFacturas(String numeroFactura) {
        int facturaConsultada = Integer.parseInt(numeroFactura);
        for (Factura f : facturasList) {
            if (f.getNumeroFactura() == facturaConsultada) {
                System.out.println("Artículo encontrado");
                // Devolvemos un array con todos los datos
                String numeroConsultado = String.valueOf(f.getNumeroFactura());
                String ivaConsultado = String.valueOf(f.getIva());
                String precioConsultado = String.valueOf(f.getPrecioFinal());
                return new String[] {
                        numeroConsultado,
                        f.getFecha(),
                        f.getDniCliente(),
                        ivaConsultado,
                        precioConsultado
                };
            }
        }
        System.out.println("Artículo no encontrado");
        return null; // Retornamos null si no se encuentra
    }



    //MÉTODOS AUXILIARES

    public void persistencia() {
        clientesList.clear(); // Limpiar la lista antes de cargar

        //Carga clientes
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Cada línea tiene los campos separados por ";"
                String[] datos = linea.split(";");
                if (datos.length == 7) { // Validamos que tenga todos los campos
                    Cliente cliente = new Cliente(
                            datos[0], // nombre
                            datos[1], // dni
                            datos[2], // poblacion
                            datos[3], // direccion
                            datos[4], // codigoPostal
                            datos[5], // provincia
                            datos[6]  // telefono
                    );
                    clientesList.add(cliente);
                }
            }
            System.out.println("Clientes cargados desde el fichero: " + clientesList.size());
        } catch (IOException e) {
            System.out.println("Error al leer el fichero de clientes: " + e.getMessage());
        }

        articulosList.clear(); // Limpiar la lista antes de cargar

        try (BufferedReader reader = new BufferedReader(new FileReader("articulos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 2) {
                    String nombre = datos[1].trim();
                    double precio;
                    try {
                        precio = Double.parseDouble(datos[2].trim().replace(",","."));
                    } catch (NumberFormatException e) {
                        System.out.println("Precio inválido en línea: " + linea);
                        continue; // Saltar línea inválida
                    }
                    Articulo articulo = new Articulo(nombre, precio);
                    articulosList.add(articulo);
                }
            }
            System.out.println("Artículos cargados: " + articulosList.size());
        } catch (IOException e) {
            System.out.println("Error leyendo artículos: " + e.getMessage());
        }
    }

    public List<Cliente> getClientesList() {
        return clientesList;
    }

    public List<Articulo> getArticulosList() {
        return articulosList;
    }

    public List<Factura> getFacturasList() {
        return facturasList;
    }
}
