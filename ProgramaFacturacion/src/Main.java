import Controlador.Controlador;
import Modelo.*;
import Vista.Vista;
import java.util.Scanner;

public class Main{
    static void main() {

        Scanner scanner = new Scanner(System.in);

        Modelo modelo = new Modelo();
        Vista vista = new Vista(scanner);
        Controlador controlador = new Controlador(modelo,vista);

        controlador.Start();

    }
}