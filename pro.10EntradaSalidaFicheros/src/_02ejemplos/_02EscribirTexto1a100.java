package _02ejemplos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class _02EscribirTexto1a100 {

	public static void main(String[] args) {
		PrintWriter f = null;
		try {
			f = new PrintWriter(new File("numeros1a100.txt"));
			for(int i = 1; i<=100; i++) {
				f.println(i);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error de apertura");
		} finally {
			if(f != null) {
				f.close();
			}
		}

	}

}
