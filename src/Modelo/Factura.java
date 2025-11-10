package Modelo;

public class Factura {

    private static int numeroFactura = 1000;
    private String fecha;
    private Cliente cliente;
    private String dniCliente;
    private LineaFacturas[] lineasFacturas;
    private int cantidadLineasFacturas = 0;
    private final static int MAX_LINEAS_FACTURAS = 10;

    public Factura (String fecha, Cliente cliente){
        this.numeroFactura = numeroFactura;
        numeroFactura++;
        this.fecha = fecha;
        this.cliente = cliente;
        this.lineasFacturas = new LineaFacturas[MAX_LINEAS_FACTURAS];
        this.dniCliente = cliente.getDni();
    }

    public int getNumeroFactura(){
        return numeroFactura;
    }

    public String getFecha(){
        return fecha;
    }

    public String getDniCliente(){
        return dniCliente;
    }

    public void agregarLineaFactura(LineaFacturas lineaFacturas){

        if (cantidadLineasFacturas<MAX_LINEAS_FACTURAS){
            lineasFacturas[cantidadLineasFacturas] = lineaFacturas;
            cantidadLineasFacturas++;
        }
    }

    public void MostrarInfo(){
        System.out.println("---------------------------------");
        System.out.println("Número de factura: "+numeroFactura+"\nFecha: "+fecha +"\nCif Cliente: "+dniCliente);
        System.out.println();
        System.out.println("Desglose de la compra: ");
        System.out.println();
        System.out.println("   Código de artículo   |   Descripción   |   Cantidad   |   Precio   ");
        for (int i=0;i<cantidadLineasFacturas;i++){
            lineasFacturas[i].mostrarInfo();
            System.out.println();
        }
        System.out.println("---------------------------------");

    }
}
