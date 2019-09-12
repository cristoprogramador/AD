package lecturaEscrituraEnFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Leer linea de caracteres de entrada estandar y escribir en fichero
 */
public class Ejem_DeEntradaEstandarAFichero {

	public static void main(String[] args) {
		System.out.println("Escriba su nombre");

		BufferedReader bfr;
		FileWriter fw = null;
		try {			
			//Abrimos el fiechero
			fw = new FileWriter("nuevo1.txt");
			//Escribimos en el fichero linea a linea
			BufferedWriter bfw = new BufferedWriter(fw);
			//preguntamos por algo
			System.out.println("introduce tu nombre");			
			//Leemos linea a linea de entrada estandar
			//declaramos la entrada de estream del system in
			InputStreamReader is = new InputStreamReader(System.in);
			//le decimos que lea lianea a linea
			bfr = new BufferedReader(is);
			
			//Escribimos en el ficher lo leido
			bfw.write(bfr.readLine());
			bfw.newLine();
			
			//lectura y escritura en bucle
			System.out.println("introduce tu nombre");			
			String s = bfr.readLine();
			while(!(s=bfr.readLine()).equals("fin")){
				bfw.write(s);
				bfw.newLine();
				System.out.println("introduce tu nombre");			
			}
			//Cerrar buffer
			bfw.close();
			bfr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("error de lectura");
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					System.out.println("error al cerrar el fichero");
				}
		}
	}
}
