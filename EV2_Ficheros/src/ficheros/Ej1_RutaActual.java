package ficheros;
import java.io.File;
import java.util.Scanner;
/**
 * Programa que muestre la ruta absoluta de la ruta actual
 * @author alumno
 *
 */
public class Ej1_RutaActual {

	public static void main(String[] args) {

		File car = new File(".");
		System.out.println("Ruta absoluta de la actual: " + car.getAbsolutePath());
		
	}
}
