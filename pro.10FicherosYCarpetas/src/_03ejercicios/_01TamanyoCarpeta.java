package _03ejercicios;

import java.io.File;
import java.util.Scanner;

public class _01TamanyoCarpeta {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Carpeta: ");
		String nombreCarpeta = tec.nextLine();
		
		File carpeta = new File(nombreCarpeta);
		if(!carpeta.exists()) {
			System.out.println(nombreCarpeta + " no existe");
		}else if(!carpeta.isDirectory()) {
			System.out.println(nombreCarpeta + " no es una carpeta");
		}else {
			//Recorremos los ficheros contenidos en la carpeta 
			//y sumamos sus tamanyos
			long total = 0;
			File contenido[] = carpeta.listFiles();
			for(File f: contenido) {
				if (f.isFile()){
					total = total + f.length();
					
				}
			}
			System.out.println(total + " bytes");
		}

	}

}
