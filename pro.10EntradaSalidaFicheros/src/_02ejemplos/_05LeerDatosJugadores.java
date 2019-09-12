package _02ejemplos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _05LeerDatosJugadores {

	public static void main(String[] args) {
		Scanner f = null;
		try {
			f = new Scanner(new File("jugadores.txt"));
			while(f.hasNext()) {
				//leer
				int dorsal = f.nextInt(); f.nextLine();
				String nombre = f.nextLine();
				int peso = f.nextInt();
				double est = f.nextDouble();
				//mostar 
				System.out.println("Dorsal: " + dorsal);
				System.out.println("Nombre: " + nombre);
				System.out.println("Peso: " + peso);
				System.out.println("Estatura: " + est);
				System.out.println("----------------");

			}
		} catch (FileNotFoundException e) {
			System.out.println("Error de apertura");
		} finally {
			if (f != null) {
				f.close();
			}
		}

	}

}
