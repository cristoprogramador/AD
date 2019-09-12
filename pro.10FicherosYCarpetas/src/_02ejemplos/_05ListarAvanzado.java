package _02ejemplos;

import java.io.File;

public class _05ListarAvanzado {

	public static void main(String[] args) {
		File f = new File("/home/alumno");
		
		File[] contenido = f.listFiles();
		
		if (contenido !=null) {
			for (int i = 0; i < contenido.length; i++) {
				if(contenido[i].isDirectory()) {
					System.out.println(" - " + contenido[i].getName());
				}else {
					if(contenido[i].isFile()) {
						System.out.println("\t" + contenido[i].getName() +
								" - " + contenido[i].length());
					}
				}
			}
		}


	}

}
