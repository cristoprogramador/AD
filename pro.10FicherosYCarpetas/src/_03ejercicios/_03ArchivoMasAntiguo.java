package _03ejercicios;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class _03ArchivoMasAntiguo {

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
			//guardando el mas antiguo cada vez
			File masAntiguo = null;
			long fechaMasAntiguo = Long.MAX_VALUE;
			
			File contenido[] = carpeta.listFiles();
			for(File f: contenido) {
				if (f.isFile()){
					if(f.lastModified() < fechaMasAntiguo) {
						masAntiguo = f;
						fechaMasAntiguo = f.lastModified();
					}
				}
			}
			
			if (masAntiguo == null) {
				System.out.println("No hay archivos");
			}else {
				System.out.println("Fichero mas antiguo: " + masAntiguo.getAbsolutePath());
				System.out.println("Fecha: " + new Date(masAntiguo.lastModified()));
			}
		}

	}

}
