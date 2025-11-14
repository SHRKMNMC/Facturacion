package Modelo;

public class Factura {

    private int numeroFactura = 1000;
    private static int contadorFacturas = 0;
    private String fecha;
    private Cliente cliente;
    private String dniCliente;
    private LineaFacturas[] lineasFacturas;
    private int cantidadLineasFacturas = 0;
    private final static int MAX_LINEAS_FACTURAS = 10;
    private double precioTotal;
    private int iva;
    private double precioFinal;

    public Factura(String fecha, Cliente cliente) {
        this.numeroFactura = numeroFactura + contadorFacturas;
        contadorFacturas++;

        this.fecha = fecha;
        this.iva = 21;
        this.cliente = cliente;
        this.dniCliente = cliente.getDni();
        this.lineasFacturas = new LineaFacturas[MAX_LINEAS_FACTURAS];
        this.precioTotal = 0;
        this.precioFinal = 0;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public int getIva() {
        return iva;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void recalcularTotales() {
        precioTotal = sumarLineasFacturas();
        precioFinal = calcularPrecioFinal();
    }

    public double sumarLineasFacturas() {
        double total = 0;

        for (int i = 0; i < cantidadLineasFacturas; i++) {
            total += lineasFacturas[i].calcularCantidadPrecio();
        }
        return total;
    }

    public double calcularPrecioFinal() {
        return precioTotal + (precioTotal * iva) / 100;
    }

    public void agregarLineaFactura(LineaFacturas lineaFacturas) {
        if (cantidadLineasFacturas < MAX_LINEAS_FACTURAS) {
            lineasFacturas[cantidadLineasFacturas] = lineaFacturas;
            cantidadLineasFacturas++;

            recalcularTotales();
        }
    }

    public void MostrarInfo() {
        System.out.println("---------------------------------");
        System.out.println("Número de factura: " + numeroFactura +
                "\nFecha: " + fecha +
                "\nCif Cliente: " + dniCliente);
        System.out.println();
        System.out.println("Desglose de la compra: ");
        System.out.println();
        System.out.println("   Código de artículo   |   Descripción   |   Cantidad   |   Precio   ");
        for (int i = 0; i < cantidadLineasFacturas; i++) {
            lineasFacturas[i].mostrarInfo();
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
}
