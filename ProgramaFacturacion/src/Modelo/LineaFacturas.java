package Modelo;

public class LineaFacturas {

    private Articulo articulo;
    private int cantidadArtículo;

    public LineaFacturas(Articulo articulo, int cantidadArtículo) {
        this.articulo = articulo;
        this.cantidadArtículo = cantidadArtículo;
    }

    public double calcularCantidadPrecio() {
        double cantidadPrecio = cantidadArtículo * articulo.getPrecio();
        return redondear2(cantidadPrecio);
    }

    private double redondear2(double num) {
        return (int)(num * 100 + 0.5) / 100.0;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public int getCantidadArtículo() {
        return cantidadArtículo;
    }

}
