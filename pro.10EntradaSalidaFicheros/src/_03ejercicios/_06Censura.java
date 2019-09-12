package _03ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _06Censura {

	public static void main(String[] args) {
		PrintWriter fe = null;
		PrintWriter fc = null;
		try {
			//Abrir el fichero
			// - Si no existe, se crea
			// - Si existe, se sobreescribe
			fe= new PrintWriter (new File("Quijote1.txt"));
			fc= new PrintWriter (new File("Censura.txt"));
			//Escribier en el fichero
			fe.println("En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no \n" + 
					"ha mucho tiempo que vivía un hidalgo de los de lanza en astillero");
			fc.println("lugar sitio\n" + 
					"acordarme recordar\n" + 
					"hidalgo noble");	
		}catch (FileNotFoundException e) {
			//Error al abrir el fichero. Posibles razones:
			// - El fichero no existia y no se ha podido crear.
			// - El fichero existia y no se ha podido abrir.
			System.out.println("Error de apertura");
		}finally {
			//Si el fichero se ha conseguido abrir, lo cerramos
			if(fe != null) {
				fe.close();
			}
			if(fc != null) {
				fc.close();
			}
		}
		aplicaCensura("Quijote1.txt", "Censura.txt", "Quijote2.txt");
	}
	
	public static void aplicaCensura(String fichEntrada, String fichCensura, String fichSalida) {

		//Leemos el fichero de censura y lo volamos a un Map<String, String>
		Map<String,String> m = new HashMap<>();
		Scanner f = null;
		boolean ok = false;
		try {			
			f = new Scanner(new File(fichCensura));
			String prohibida, censura;
			while (f.hasNext()) {
				prohibida = f.next();
				censura = f.next();
				m.put(prohibida, censura);
			}
			ok = true;
			//System.out.println(m);
			while (f.hasNext()) {
				String palabra = f.next();
				if (m.containsKey(palabra)) {
					
				}
			}
				
			
		} catch (FileNotFoundException e) {
			System.out.println("Error apertura fichero entrada");
		} finally {
			if (f!=null) f.close();
		}

		//Leer fichero de entrad y escribir el de salida		
		if (ok) {
			Scanner fe = null;
			PrintWriter fs = null;
			try {
				fe = new Scanner(new File (fichEntrada));
				fs = new PrintWriter(new File (fichSalida));
				while (fe.hasNext()) {
					String palabra = fe.next();
					//Sustiturir palabra si esta censurada
					String sustituta = m.get(palabra);
					if (sustituta == null) {
						fs.print(palabra + " ");
					}else {
						fs.print(sustituta + " ");
					}
					//--------
					//fs.print(palabra + " ");
				}
			}catch (FileNotFoundException e) {
				System.out.println("Error de apertura");
			}finally {
				if (fe != null) {
					fe.close();
				}
				if (fs != null) {
					fs.close();
				}
			}
			
		}
	}
}
