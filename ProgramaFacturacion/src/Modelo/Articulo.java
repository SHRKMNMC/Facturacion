package Modelo;

public class Articulo {

    private String codigoArticulo;
    private static int contadorArticulo = 0;
    private String nombre;
    private double precio;

    public Articulo (String nombre,double precio){
        this.codigoArticulo = generarCodigoArticulo();
        this.nombre = nombre;
        this.precio = precio;
        this.contadorArticulo = contadorArticulo++;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public String getNombre(){
        return nombre;
    }

    public double getPrecio(){
        return precio;
    }

    public String generarCodigoArticulo(){
        return "RTCL"+contadorArticulo;
    }


}
