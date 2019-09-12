package ficheros;
import java.io.File;
import java.util.Date;
import java.util.Scanner;

/**
 * (FechaUltimaModificacion) Dada una carpeta, mostrar la fecha de última
 * modificación de cada uno de los archivos que contiene.
 * 
 * @author alumno
 *
 */
public class Ej6_FechaUltimaModificacion {

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
					System.out.println(
							"Archivo: " + file.getName() + " - Ultima modifivación: " + new Date(file.lastModified()));
				}
			} else {
				System.out.println(car.getAbsolutePath() + " no es una carpeta");
			}
		} else {
			System.out.println("la carpeta " + car.getName() + " no existe");
		}
	}

}
