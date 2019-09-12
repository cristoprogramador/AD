package ficheros;
import java.io.File;
import java.util.Date;
import java.util.Scanner;
/**
 * Dada una carpeta mostrar su contenido: 
 * nombre de cada fichero o carpeta junto con el tamaño del fichero, 
 * o indicación de que es una carpeta.
 * @author alumno
 *
 */
public class Ej5_ContenidoCarpeta {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce ruta de carpeta");

		String nombreCarpeta = tec.nextLine();
		File[] listaFiles;
		// Creamosobjeto File
		// pararepresentar el archivo
		File car = new File(nombreCarpeta);
		if (car.exists()) {
			if (car.isDirectory()) {
				
				listaFiles = car.listFiles();
				System.out.println("Carpeta " + car.getName());
				for (File file : listaFiles) {
					if (!file.isDirectory())
						System.out.println(file.getName() + " - Tamaño: " + file.length() + " bytes");					
					if (file.isDirectory())
						System.out.println(file.getName() + " - Carpeta");
				}
				
			} else {
				System.out.println(car.getAbsolutePath() + " no es una carpeta");
			}
		} else {
			System.out.println("la carpeta " + car.getName() + " no existe");
		}
	}
}
