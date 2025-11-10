package Modelo;

public class LineaFacturas {

    private Articulo articulo;
    private int cantidadArtículo;

    public LineaFacturas (Articulo articulo, int cantidadArtículo){
        this.articulo = articulo;
        this.cantidadArtículo = cantidadArtículo;
    }


    public double calcularCantidadPrecio(){
        double cantidadPrecio = cantidadArtículo*articulo.getPrecio();
        return cantidadPrecio;
    }

    public void mostrarInfo(){

        System.out.println("         "+articulo.getCodigoArticulo()+"          | "+articulo.getNombre()+" |        "+cantidadArtículo+"        |   "+calcularCantidadPrecio()+"   ");
    }
}
