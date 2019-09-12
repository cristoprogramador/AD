package _02ejemplos;

import java.io.File;
import java.io.IOException;

public class _02NombresYRutas {
	public static void main(String[] args) throws IOException{
		File f1 = new File("carta.txt");
		System.out.println("getName: " + f1.getName());
		System.out.println("getPath: " + f1.getPath());
		System.out.println("getParent: " + f1.getParent());
		System.out.println("getAbsolutePath: " + f1.getAbsolutePath());
		System.out.println("getCanonicalPath: " + f1.getCanonicalPath());

		System.out.println("------------------------------------------");
		
		File f2 = new File("/home/carta.txt");
		System.out.println("getName: " + f2.getName());
		System.out.println("getPath: " + f2.getPath());
		System.out.println("getParent: " + f2.getParent());
		System.out.println("getAbsolutePath: " + f2.getAbsolutePath());
		System.out.println("getCanonicalPath: " + f2.getCanonicalPath());

		System.out.println("------------------------------------------");
		
		File f3 = new File("src/_03ejercicios/_01Ejercicio1.java");
		System.out.println("getName: " + f3.getName());
		System.out.println("getPath: " + f3.getPath());
		System.out.println("getParent: " + f3.getParent());
		System.out.println("getAbsolutePath: " + f3.getAbsolutePath());
		System.out.println("getCanonicalPath: " + f3.getCanonicalPath());

		System.out.println("------------------------------------------");
		
		File f4 = new File("src/../src/_03ejercicios/_01Ejercicio1.java");
		System.out.println("getName: " + f4.getName());
		System.out.println("getPath: " + f4.getPath());
		System.out.println("getParent: " + f4.getParent());
		System.out.println("getAbsolutePath: " + f4.getAbsolutePath());
		System.out.println("getCanonicalPath: " + f4.getCanonicalPath());

	}
	
}
