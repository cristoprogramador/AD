package _03ejercicios;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * (BuscarDuplicados) Dadas dos carpetas cuyos nombres introduce el usuario,
 * mostrar por pantalla los nombre de los ficheros que están en ambas: ficheros
 * con el mismo nombre y mismo tamaño
 * 
 * @author alumno
 *
 */
public class _08BuscarDuplicados {

	public static void main(String[] args) {

		Scanner tec = new Scanner(System.in);
		System.out.println("Carpeta 1: ");
		String nombreCarpeta = tec.nextLine();
		System.out.println("Carpeta 2: ");
		String nombreCarpeta2 = tec.nextLine();

		File carpeta = new File(nombreCarpeta);
		File carpeta2 = new File(nombreCarpeta2);
		if (!carpeta.exists() || !carpeta2.exists()) {
			System.out.println("Al menos una de las carpetas no existe");
		} else if (!carpeta.isDirectory() || !carpeta2.isDirectory()) {
			System.out.println("Al menos una no es una carpeta");
		} else {
			// Recorremos los ficheros contenidos en la carpeta
			// guardando el numero de carpteas y de ficheros
			HashSet<File> listaContenido = new HashSet<>();

			File contenido[] = carpeta.listFiles();
			for (File f : contenido) {
				if (f.isFile()) {
					listaContenido.add(f);
				}
			}
			File contenido2[] = carpeta2.listFiles();
			for (File f : contenido2) {
				if (listaContenido.contains(f))
					System.out.println(f.getName());
			}

		}
	}
}
