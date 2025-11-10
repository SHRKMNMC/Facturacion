package Modelo;

public class Cliente {

    //ATRIBUTOS

    private String nombre;
    private String dni;
    private String poblacion;
    private String direccion;
    private String codigoPostal;
    private String provincia;
    private String telefono;

    //CONSTRUCTOR

    public Cliente (String nombre,String dni,String poblacion, String direccion, String codigoPostal, String provincia, String telefono){
        this.nombre = nombre;
        this.dni = dni;
        this.poblacion = poblacion;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.telefono = telefono;
    }

    //GETTERS

    public String getNombre(){
        return nombre;
    }

    public String getDni(){
        return dni;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public  String getCodigoPostal(){
        return codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getTelefono() {
        return telefono;
    }
}
