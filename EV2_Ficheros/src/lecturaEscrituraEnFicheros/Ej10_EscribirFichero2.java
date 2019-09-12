package lecturaEscrituraEnFicheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Repetir el ejercicio anterior utilizando las clases FileReader y FileWriter
 * @author elsal
 *
 */
public class Ej10_EscribirFichero2 {
	//Examen no va desde la pagina 17
	public static void main(String[] args) {
		FileWriter fw = null;
		FileReader fr = null;
		
		try{
			fw = new FileWriter("nombre.txt");
			//Escribir array de caracteres
			fw.write("Cristobal");
			
			fw.close();
			
			fr = new FileReader("nombre.txt");
			int valor = fr.read();
			
			while (valor != -1){
				System.out.print((char)valor);
				valor = fr.read();
			}
			
		}catch(FileNotFoundException e){
			System.out.println("no se puede crear o abrir el fichero");
		}catch (IOException e){
				System.out.println("Error");
		}finally{
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
