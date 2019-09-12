package lecturaEscrituraEnFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej15_CuentaLineas {

	public static void main(String[] args) {
		FileReader fis = null;
		
		try{
			System.out.println("Ruta del archivo");
			BufferedReader br = new BufferedReader(new InputStreamReader (System.in));		
			File fich = new File(br.readLine());

			if (fich.exists()) {
				if (fich.isFile()) {
					fis = new FileReader(fich);

					BufferedReader bf = new BufferedReader(fis);
					String linea;
					//Mientras la linea no sea null
					int cont = 0;
					while((linea=bf.readLine()) != null){
						cont++;
						//System.out.println(cont + ": " + linea);
					}
					System.out.print("Total lineas: " + cont);
				}else{
					System.out.println("No es un archivo");
				}
			}else{
				System.out.println("El archivo no existe");
			}
			
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
