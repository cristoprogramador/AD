package _03ejercicios;

import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * (ListadoAlfabético) De una carpeta cuyo nombre indica el usuario, mostrar los
 * archivos y las carpetas que contiene en orden alfabético: Primero las
 * carpetas en orden alfabético y después los ficheros en orden alfabético.
 * Ayúdate de alguna Collection
 * 
 * @author alumno
 *
 */
public class _05ListadoAlfabetico {

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

			File contenido[] = carpeta.listFiles();
			for (File f : contenido) {
				if (f.isFile()) {
					
					listaFiles.add(f.getName());
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
