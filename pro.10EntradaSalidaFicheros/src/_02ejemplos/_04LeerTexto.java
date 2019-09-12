package _02ejemplos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _04LeerTexto {
	// Leer los numeros del fichero numeros1a100.txt
	// y mostrar la media
	public static void main(String[] args) {
		Scanner f = null;

		try {
			// Abrir el fichero.
			// - El fichero se pone en uso y estamos al comienzo del fichero
			f = new Scanner(new File("numeros1a100.txt"));	
		
			//Leemos del fichero mientras queden enteros por leer
			int total = 0;
			int cantidad = 0;
			while(f.hasNextInt()) {
				int num = f.nextInt();
				total += num;
				cantidad ++;
			}
			System.out.println("Media: " + total + "/"+ cantidad + " : " + (total / cantidad));
			
		} catch (FileNotFoundException e) {
			System.out.println("Error de apertura");
		}
	}
}
