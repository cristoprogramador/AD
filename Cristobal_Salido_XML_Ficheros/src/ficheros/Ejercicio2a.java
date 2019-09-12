package ficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2a {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		System.out.println("Introduce la ruta de carpeta");
		String nombreCarpeta = tec.nextLine();
		tec.close();

		FileWriter fwb = null;
		try {
			
			fwb = new FileWriter(nombreCarpeta + ".txt");
			BufferedWriter bfw = new BufferedWriter(fwb);
			File[] listaFiles;
			File car = new File(nombreCarpeta);

			if (car.exists()) {
				if (car.isDirectory()) {					
					listaFiles = car.listFiles();
					
					for (File file : listaFiles) {						
						if (file.isFile()) {
							bfw.write(file.getName() + " - Tamaño: " + file.length() + " bytes");
							bfw.newLine();
						}
					}
					bfw.close();
					System.out.println("Archivo Creado");
					
				} else 
					System.out.println(car.getAbsolutePath() + " no es una carpeta");				
			} else 
				System.out.println("la carpeta " + car.getName() + " no existe");
			
			
		} catch (IOException e) {
			System.out.print("error de lectura/escritura");
		} finally {
			if (fwb != null) {
				try {
					fwb.close();
				} catch (IOException e) {
					System.out.println("error al cerrar el fichero");
				}
			}
		}
	}
}
