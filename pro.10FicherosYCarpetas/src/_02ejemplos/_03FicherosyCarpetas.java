package _02ejemplos;

import java.io.File;
import java.util.Date;

public class _03FicherosyCarpetas {

	public static void main(String[] args) {
		//Se pueden hacer comprovabicones sobre un obejto File:
		
		File f1 = new File("carta.txt");
		System.out.println(f1);
		System.out.println("Existe?: " + f1.exists());
		System.out.println("Es fichero NORMAL?: " + f1.isFile());
		System.out.println("Es carpeta?: " + f1.isDirectory());

		File f2 = new File("src/_02ejemplos/_01ClaseFile.java");
		System.out.println(f2);
		System.out.println("Existe?: " + f2.exists());
		System.out.println("Es fichero NORMAL?: " + f2.isFile());
		System.out.println("Es carpeta?: " + f2.isDirectory());
	
		File f3 = new File("src/_02ejemplos/_01ClaseFile.java");
		System.out.println(f3);
		System.out.println("Existe?: " + f3.exists());
		System.out.println("Es fichero NORMAL?: " + f3.isFile());
		System.out.println("Es carpeta?: " + f3.isDirectory());
		
		//Permisos de lectura escritura
		System.out.println("----------Permisos----------");
		System.out.println(f1.canRead());
		System.out.println(f1.canWrite());

		System.out.println(f2.canRead());
		System.out.println(f2.canWrite());

		System.out.println(f3.canRead());
		System.out.println(f3.canWrite());
		
		//Tamanyo
		System.out.println("----------Tamanyo----------");
		System.out.println("Fichero que no existe: " + f1.length());
		System.out.println("Fichero que existe: " + f2.length());
		System.out.println("Carpeta: " + f3.length());
		
		//Ultima modificacion
		System.out.println("----------LastModified----------");
		System.out.println("Fichero que no existe: " + f1.lastModified());
		System.out.println("Fichero que existe: " + f2.lastModified());
		System.out.println("Carpeta: " + f3.lastModified());
		
		System.out.println("Fichero que no existe: " + new Date(f1.lastModified()));
		System.out.println("Fichero que existe: " + new Date(f2.lastModified()));
		System.out.println("Carpeta: " + new Date(f3.lastModified()));
	}

	
	
}
