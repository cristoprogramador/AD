package _02ejemplos;

import java.util.List;
import java.io.File;
import java.util.LinkedList;

public class _06ListarEnProfundidad {

	public static void main(String[] args) {
		File f = new File(".");
		List<File> carpetasPendientes = new LinkedList<>();
		
		carpetasPendientes.add(f);
		//Mientras queden caprpetas pendientes
		while(!carpetasPendientes.isEmpty()) {
			File carpeta = carpetasPendientes.get(0);
			carpetasPendientes.remove(0);
			File[] contenido = carpeta.listFiles();
			for (int i = 0; i < contenido.length; i++) {
				if(contenido[i].isDirectory()) {
					//Pongo la carpeta como pendiente de procesar
					carpetasPendientes.add(0, contenido[i]);
					
				}else {
					if (contenido[i].isFile()) {
						//Muestro el fichero
						System.out.println("\t - " + contenido[i].getPath());
					}
				}
			}
		}
	}

}
