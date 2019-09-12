package ficheros;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Recorrer el árbol a partir de un directorio padre.
 * 
 * @author alumno
 *
 */
public class Ejem3_RecorrerArbolDirectorio {

	public static void main(String[] args) {

		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce ruta de una carpeta: ");
		String nombreCarpeta = tec.nextLine();
		// Creamosobjeto File para representar a lacarpeta
		File car = new File(nombreCarpeta);

		// Comprobamossiexiste
		if (car.exists()) {
			// Comprobamos que es una carpeta
			if (car.isDirectory()) {
				System.out.println("Contenido: ");

				List<File> listaDirectorios = new ArrayList<>();
				// Añadimos nuestro directorio inicial una lista de directorios
				listaDirectorios.add(car);
				// Mientras existan directorios en nuestra lista
				while (!listaDirectorios.isEmpty()) {
					//cogemos la primera carpeta de la lista de directorios
					File carpeta = listaDirectorios.get(0);
					//la quitamos de la lista
					listaDirectorios.remove(0);
					//Cogemos su contenido
					File[] contenido = carpeta.listFiles();
					//Recorremos su contenido
					for (int i = 0; i < contenido.length; i++) {
						//Si un elemento es un directorio
						if (contenido[i].isDirectory()) {
							// Pongo el directorio en la lista para procesarlo mas tarde
							listaDirectorios.add(0, contenido[i]);
							
						} 
						//Si el elemento no es directorio 
						else {
							//mostramos su dirección
							if (contenido[i].isFile()) {
								// Muestro el fichero
								System.out.println("\t - " + contenido[i].getPath());
							}
						}
					}
				}

			} else
				System.out.println("El archivo no es una carpeta:  " + car.getAbsolutePath());
		} else
			System.out.println("la carpeta " + car.getName() + " no existe");
	}

}
