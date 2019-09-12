package ficheros;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

import javax.swing.SingleSelectionModel;

/**
 * Dada una carpeta, renombrar todos sus archivos. El nombre será el original al
 * que añadiremos el sufijo “(copia)”. Si no fuera posible renombrar el fichero
 * porque ya existe uno con ese nombre, iremos añadiendo números (copia1) o
 * (copia2)
 * 
 * @author alumno
 *
 */
public class Ej9_Renombrar {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce ruta de carpeta");
		String nombreCarpeta = tec.nextLine();

		File car = new File(nombreCarpeta);
		if (car.exists()) {
			if (car.isDirectory()) {
				
				File[] listaArchivos = car.listFiles();
				for (File file : listaArchivos) {
					System.out.println(file.getName());							
					if (file.isFile()) {
						int numCopia = 1;
						String sufijo = "(copia)";
						boolean nombreCambiado = true;						
						do {
							nombreCambiado = file.renameTo(new File(file.getAbsoluteFile() + sufijo));
							if (!nombreCambiado) {
								sufijo = "(copia" + numCopia + ")";
								numCopia++;
							}
						} while (!nombreCambiado);
					}					
				}

			} else {
				System.out.println(car.getAbsolutePath() + " no es una carpeta");
			}
		} else {
			System.out.println("la carpeta " + car.getName() + " no existe");
		}
	}
}
