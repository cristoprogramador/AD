package ficheros;
import java.io.File;
import java.util.Scanner;

/**
 * 1. Obtener la siguiente información de un archivo
 * a. Nombre
 * b. Path, Path absoluto
 * c. Directorio padre
 * d. (prueba con distintas rutas)
 * 
 * @author alumno
 *
 */
public class Ejem1_InformacionDeArchivo {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce ruta del archivo: ");
		String nombreArchivo = tec.nextLine(); 
		// Creamosobjeto File
		// pararepresentar el archivo
		File f = new File(nombreArchivo); 		
		
		System.out.println("Nombre: " + f.getName());
		System.out.println("Ruta: " + f.getPath());
		System.out.println("Ruta absoluta: " + f.getAbsolutePath());
		System.out.println("padre: " + f.getParent());
	}

}
