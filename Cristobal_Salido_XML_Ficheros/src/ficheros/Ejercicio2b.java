package ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio2b {

	public static void main(String[] args) {
		FileReader fis = null;
		
		try{
			
			fis = new FileReader("Alumnos.txt");
			BufferedReader bf = new BufferedReader(fis);
			boolean enc = false;
			String linea;		

			while((linea=bf.readLine()) != null){	
				if (enc){
					System.out.println("Grupo: " + linea);
					enc = false;
				}
				if (linea.equals("grupo")){					
					enc = true;
				}				
			}
			
			bf.close();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
				System.out.print("Error de lectura");
		}finally{
			if (fis!= null){
				try{
					fis.close();
				}catch(IOException e){
					System.out.println("error al cerrar el fichero");
				}
			}				
		}
	}
}
