package _02ejemplos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class _01EscribirTextoLibre {

	public static void main(String[] args) {
		// Para escribir fichero de texto usaremos 
		// la clase PrintWrter
		
		//Los metodos de PrintWriter son:
		// - print
		// - println
		
		PrintWriter f = null;
		try {
			//Abrir el fichero
			// - Si no existe, se crea
			// - Si existe, se sobreescribe
			f= new PrintWriter (new File("textolibre.txt"));
			
			//Escribier en el fichero
			f.println("Esto es");
			f.println("un texto libre");
		}catch (FileNotFoundException e) {
			//Error al abrir el fichero. Posibles razones:
			// - El fichero no existia y no se ha podido crear.
			// - El fichero existia y no se ha podido abrir.
			System.out.println("Error de apertura");
		}finally {
			//Si el fichero se ha conseguido abrir, lo cerramos
			if(f != null) {
				f.close();
			}
		}
	}
}
