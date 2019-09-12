package lecturaEscrituraEnFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Leer un fichero de texto y copiarlo en uno nuevo transformado en mayusculas
 * @author alumno
 *
 */
public class Ejem_DeFicheroAFichero {

	public static void main(String[] args) {


		
		//Leer el fichero linea a linea
		FileReader fis = null;
		FileWriter fos = null;
		try{
			fis = new FileReader("nuevo1.txt");
			fos = new FileWriter("nuevo1Mayusculas.txt");
			
			BufferedReader bfr = new BufferedReader(fis);
			BufferedWriter bfw = new BufferedWriter(fos);
			
			String linea;			
			//Mientras la linea no sea null
			while((linea=bfr.readLine()) != null){
				bfw.write(linea.toUpperCase());
				bfw.newLine();
			}		
			
			bfw.close();
			bfr.close();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
				System.out.print("Error de lectura");
		}finally{
			try {
				fos.close();
			} catch (IOException e1) {
				System.out.println("error al cerrar el fichero de escritura");
				e1.printStackTrace();
			}
			if (fis!= null){
				try{
					fis.close();
				}catch(IOException e){
					System.out.println("error al cerrar el fichero de lectura");
				}
			}				
		}		
	}
}
