package _03ejercicios;

import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * EJERCICIO1 
 * El ususario introduce el nombre de la ruta de una carpeta
 * -mostrar cuantos bytes ocupa (el primer nivel)
 * -mostrar cuantos bytes ocupa (todos los niveles)
 * 
 * si la carpeta no existe-> mensaje error
 * si la carpeta no es carpeta -> mensaje error
 */
public class _01MostrarTamanyo {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Carpeta: ");
		String nombreCarpeta = tec.nextLine();

		File carpeta = new File(nombreCarpeta);
		if (!carpeta.exists()) {
			System.out.println(nombreCarpeta + " no existe");
		} else if (!carpeta.isDirectory()) {
			System.out.println(nombreCarpeta + " no es una carpeta");
		} else {
			// Recorremos los ficheros contenidos en la carpeta
			// guardando el numero de carpteas y de ficheros

			TreeSet<String> listaFiles = new TreeSet<>();
			TreeSet<String> listaDirect = new TreeSet<>();
			long ocupa = 0;
			File contenido[] = carpeta.listFiles();
			for (File f : contenido) {
				if (f.isFile()) {				
					listaFiles.add(f.getName());
					ocupa += f.length();
				}
				if (f.isDirectory()) {
					listaDirect.add(f.getName());
				}
			}
			
			System.out.println("Carpetas: ");
			for (String s: listaDirect) {
				System.out.println(s);
			}
			System.out.println("\nFicheros: ");
			for (String s: listaFiles) {
				System.out.println(s);
			}

		}	
	}
}
