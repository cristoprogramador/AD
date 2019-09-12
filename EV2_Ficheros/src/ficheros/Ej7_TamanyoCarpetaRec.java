package ficheros;
import java.io.File;
import java.util.Date;
import java.util.Scanner;

/**
 * Dada una carpeta, calcular su tamaño. 
 * El tamaño será la suma del tamaño de los archivos 
 * que contiene y carpetas que contiene (recursivo).
 * 
 * @author alumno
 *
 */
public class Ej7_TamanyoCarpetaRec {
	
	private static int tamanyoTotal = 0;
	
	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce ruta de carpeta");
		String nombreCarpeta = tec.nextLine();

		File car = new File(nombreCarpeta);
		if (car.exists()) {
			if (car.isDirectory()) {

				calcularTamanyo(car);
				System.out.println("Tamaño total de la carpeta " +car.getName()+": "+ tamanyoTotal + " bytes");
				
			} else {
				System.out.println(car.getAbsolutePath() + " no es una carpeta");
			}
		} else {
			System.out.println("la carpeta " + car.getName() + " no existe");
		}
	}
	
	public static void calcularTamanyo(File carpeta) {
		//Guardamos la lista del contendido de la carpeta
		File[] contenido = carpeta.listFiles();
		//Lo recorremos
		for (int i = 0; i < contenido.length; i++) {
			//Si se encuentra una carpeta
			if(contenido[i].isDirectory()) {
				//Pongo la carpeta como pendiente de procesar
				calcularTamanyo(contenido[i]);				
			}
			//Si es un archivo
			else {
				if (contenido[i].isFile()) {
					//sumo el tamaño el fichero
					tamanyoTotal += contenido[i].length();
				}
			}
		}
	}
}
