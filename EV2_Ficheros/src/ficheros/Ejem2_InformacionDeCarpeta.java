package ficheros;
import java.io.File;
import java.util.Scanner;

import javax.swing.SingleSelectionModel;
/**
 * Dada una carpeta obtener la siguiente información
 * a. Permisos de lectura/escritura
 * b. Oculta/visible
 * c. Contenido
 * d. Si no es una carpeta lo que introduce el usuario 
 *    mostrar que no existe, formando el path
 * @author alumno
 *
 */
public class Ejem2_InformacionDeCarpeta {

	public static void main(String[] args) {

		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce ruta de una carpeta: ");
		String nombreCarpeta = tec.nextLine(); 
		// Creamosobjeto File para representar a lacarpeta
		File car = new File(nombreCarpeta); 
		
		// Comprobamossiexiste
		if (car.exists()){
			//Comprobamos que es una carpeta
			if(car.isDirectory()){
				//Se puede Leer?
				if(car.canRead())
					System.out.println("Lectura Permitida");
				else
					System.out.println("Lectura No Permitida");
				//Se puede Escribir?
				if (car.canWrite())
					System.out.println("Escritura Permitida");
				else
					System.out.println("Escritura No permitida");
				//Está oculta?
				if(car.isHidden())
					System.out.println("Carpeta Oculta");
				else
					System.out.println("Carpeta Visible");
				//Listamos contnido
				System.out.println("Contenido: ");
				String[] contenido = car.list();
				for (String string : contenido) {
					System.out.println(string);
				}
			}else
				System.out.println("El archivo no es una carpeta:  " + car.getAbsolutePath());
		}else
			System.out.println("la carpeta " + car.getName() + " no existe");
	}

}
