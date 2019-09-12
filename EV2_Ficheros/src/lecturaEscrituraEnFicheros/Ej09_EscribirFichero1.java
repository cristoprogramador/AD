package lecturaEscrituraEnFicheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Escribe un programa que, usando las clases FileOutputStream y FileInputStream.
 *  escriba los caracteres de tu nombre en un fichero (nombre.txt).
 *  lea el fichero creado y lo muestre por pantalla.
 *  Si abrimos el fichero creado con un editor de textos, ¿su contenido es legible?
 * @author elsal
 *
 */
public class Ej09_EscribirFichero1 {
	//Examen no va desde la pagina 17
	public static void main(String[] args) {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		
		try{
			fos = new FileOutputStream("nombre.txt");
			//Escribir array de caracteres
			fos.write("Cristobal".getBytes());
			fis = new FileInputStream("nombre.txt");
			int valor = fis.read();
			while (valor != -1){
				System.out.print((char)valor);
				valor = fis.read();
			}
			
		}catch(FileNotFoundException e){
			System.out.println("no se puede crear o abrir el fichero");
		}catch (IOException e){
				System.out.println("Error");
		}finally{
			if (fos!= null){
				try{
					fos.close();
				}catch(IOException e){
					System.out.println("error al cerrar el fichero");
				}
			}	
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
