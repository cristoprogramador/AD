package ficheros;
import java.io.File;
import java.util.Scanner;
/**
 * Mostrar el nº de archivos y directorios que contiene una carpeta.
 * @author alumno
 *
 */
public class Ej3_NumeroElementosCarpeta {

	public static void main(String[] args) {

		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce ruta de una carpeta");
		String nombreCarpeta = tec.nextLine(); 
		
		File car = new File(nombreCarpeta);
		if (car.exists()) {
			if (car.isDirectory()) {
				
				File[] listaFiles = car.listFiles();
				int countDir = 0;
				int countArch = 0;
				for (File file : listaFiles) {
					if (file.isDirectory())
						countDir++;
					else
						countArch++;
				}
				
				System.out.println("Numero de directorios: " + countDir);
				System.out.println("Numero de archivos: " + countArch);
				
			} else {
				System.out.println(car.getAbsolutePath() + " no es una carpeta");
			}
		} else {
			System.out.println("la carpeta " + car.getName() + " no existe");
		}
	}

}
