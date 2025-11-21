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

    public Factura(String fecha, Cliente cliente, int iva) {
        this.numeroFactura = numeroFactura + contadorFacturas;
        contadorFacturas++;
        this.fecha = fecha;
        this.iva = iva;
        this.cliente = cliente;
        this.dniCliente = cliente.getDni();
        this.lineasFacturas = new LineaFacturas[MAX_LINEAS_FACTURAS];
        this.precioTotal = 0;
        this.precioFinal = 0;
    }

    private double redondear2(double num) {
        return (int)(num * 100 + 0.5) / 100.0;
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
        precioTotal = redondear2(sumarLineasFacturas());
        precioFinal = redondear2(calcularPrecioFinal());
    }

    public double sumarLineasFacturas() {
        double total = 0;

        for (int i = 0; i < cantidadLineasFacturas; i++) {
            total += lineasFacturas[i].calcularCantidadPrecio();
        }

        return redondear2(total);
    }

    public double calcularPrecioFinal() {
        double total = precioTotal + (precioTotal * iva) / 100;
        return redondear2(total);
    }

    public void agregarLineaFactura(LineaFacturas lineaFacturas) {
        if (cantidadLineasFacturas < MAX_LINEAS_FACTURAS) {
            lineasFacturas[cantidadLineasFacturas] = lineaFacturas;
            cantidadLineasFacturas++;

            recalcularTotales();
        }
    }
}
