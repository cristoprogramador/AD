package _02ejemplos;
/**
 * copiar los datos del archivo jugadores a otro archivo
 * con el nombre en mayusculas
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class _06PasarDatos {

	public static void main(String[] args) {
		Scanner fe = null;
		PrintWriter fs = null;

		try {
			fe = new Scanner(new File("jugadores.txt"));
			fs = new PrintWriter(new File("copiaj.txt"));
			while(fe.hasNext()) {
				//leer
				int dorsal = fe.nextInt(); fe.nextLine();
				String nombre = fe.nextLine();
				int peso = fe.nextInt();
				double est = fe.nextDouble();
				
				fs.println(dorsal);
				fs.println(nombre.toUpperCase());
				fs.println(peso);
				fs.println(est);

			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error de apertura");
		} finally {
			if (fe != null) {
				fe.close();
			}
			if (fs != null) {
				fs.close();
			}
		}

	}

}
