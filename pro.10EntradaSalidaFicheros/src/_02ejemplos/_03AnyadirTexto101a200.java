package _02ejemplos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class _03AnyadirTexto101a200 {

	public static void main(String[] args) {
		PrintWriter f = null;
		try {
			
			//f = new PrintWriter(new File("numeros1a100.txt"));
			//Usamos FileOoutputStream(file, append), con append a true
			f = new PrintWriter(
					new FileOutputStream(
							new File("numeros1a100.txt"), true));
			for(int i = 101; i <= 200; i++) {
				f.println(i);
			}
		}catch(FileNotFoundException e) {
			System.out.println("Error de apertura");
		}finally {
			if (f != null);
			f.close();
		}
		//Cada vez que se ejecute añadirá al fichero del 101 al 200 de nuevo
	}

}
