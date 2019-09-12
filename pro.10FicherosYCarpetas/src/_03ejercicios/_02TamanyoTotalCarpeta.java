package _03ejercicios;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class _02TamanyoTotalCarpeta {

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
			// y sumamos sus tamanyos
			List<File> pendiente = new LinkedList<>();
			pendiente.add(carpeta);
			long total = 0;
			while (!pendiente.isEmpty()) {
				// Extraemos la priemra carpeta pendiente e procesar
				carpeta = pendiente.remove(0);//remove devuelve y elimina
				// Exploramos su contenido
				File contenido[] = carpeta.listFiles();
				for (File f : contenido) {
					if (f.isFile()) {
						total = total + f.length();

					} else {
						if (f.isDirectory()) {
							// Es una carpeta. La ponemos pendiente de procesar
							pendiente.add(f);
						}
					}
				}
			}
			System.out.println(total + " bytes");
		}
	}

}
