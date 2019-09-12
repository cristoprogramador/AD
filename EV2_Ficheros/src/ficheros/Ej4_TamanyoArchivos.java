package ficheros;
import java.io.File;
import java.util.Date;
import java.util.Scanner;
/**
 * (TamanyoArchivos) Calcular y mostrar el tamaño de 
 * los archivos contenidos en una carpeta (sin incluir las subcarpetas)
 * @author alumno
 *
 */
public class Ej4_TamanyoArchivos {

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
				int acumulado = 0;
				for (File file : listaFiles) {
					if (!file.isDirectory())
						acumulado += file.length();
				}
				System.out.println("Tamaño Total de los archivos contenidos en la carpeta " + car.getName());
				System.out.println(acumulado + " bytes");
				
			} else {
				System.out.println(car.getAbsolutePath() + " no es una carpeta");
			}
		} else {
			System.out.println("la carpeta " + car.getName() + " no existe");
		}
	}
}
