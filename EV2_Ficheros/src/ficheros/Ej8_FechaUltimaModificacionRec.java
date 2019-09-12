package ficheros;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

/**
 * Dada una carpeta, calcular la fecha de su última modificación. La fecha será
 * la fecha de última modificación del archivo que se ha modificado más
 * recientemente (recursivo).
 * 
 * @author alumno
 *
 */
public class Ej8_FechaUltimaModificacionRec {

	private static Date fechaUltima;
	
	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce ruta de carpeta");
		String nombreCarpeta = tec.nextLine();

		File car = new File(nombreCarpeta);
		if (car.exists()) {
			if (car.isDirectory()) {
				
				fechaUltima = new Date(car.lastModified());
				calcularTamanyo(car);
				System.out.println("Fecha de ultima modificación: " + fechaUltima);
				
			} else {
				System.out.println(car.getAbsolutePath() + " no es una carpeta");
			}
		} else {
			System.out.println("la carpeta " + car.getName() + " no existe");
		}
	}

	public static void calcularTamanyo(File carpeta) {		
		// Guardamos la lista del contendido de la carpeta
		File[] contenido = carpeta.listFiles();
		// Lo recorremos
		for (int i = 0; i < contenido.length; i++) {
			// Guardamos la fecha de la carpeta
			Date fechaFile = new Date(contenido[i].lastModified());
			// si la ultima fecha guardada es anterior a la de la carpeta
			if (fechaUltima.before(fechaFile))
				// Guardamos la fecha de la carpeta como la ultima fecha
				fechaUltima = fechaFile;
			// Si se encuentra una carpeta
			if (contenido[i].isDirectory()) {
				// Pongo la carpeta como pendiente de procesar
				calcularTamanyo(contenido[i]);
			}
		}
	}
}
