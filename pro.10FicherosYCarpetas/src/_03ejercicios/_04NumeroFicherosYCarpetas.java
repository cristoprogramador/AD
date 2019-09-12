package _03ejercicios;

import java.io.File;
import java.util.Scanner;

/**
 * (NumeroFicherosYCarpetas) Mostrar cuántos ficheros y cuántas carpetas
 * contiene una carpeta cuyo nombre indica el usuario.
 * 
 * @author alumno
 *
 */
public class _04NumeroFicherosYCarpetas {

	public static void main(String[] args) {
		
		Scanner tec = new Scanner(System.in);
		System.out.println("Carpeta: ");
		String nombreCarpeta = tec.nextLine();
		
		File carpeta = new File(nombreCarpeta);
		if(!carpeta.exists()) {
			System.out.println(nombreCarpeta + " no existe");
		}else if(!carpeta.isDirectory()) {
			System.out.println(nombreCarpeta + " no es una carpeta");
		}else{
			//Recorremos los ficheros contenidos en la carpeta 
			//guardando el numero de carpteas y de ficheros

			int carpetas = 0 , ficheros = 0;
			File contenido[] = carpeta.listFiles();
			for(File f: contenido) {
				if (f.isFile()){
					ficheros++;
				}
				if (f.isDirectory()) {
					carpetas++;
				}
			}
			
		System.out.println(carpetas + " Carpetas");
		System.out.println(ficheros + " Ficheros");

		}
	}
}
