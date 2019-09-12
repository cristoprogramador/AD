package _02ejemplos;

import java.util.List;
import java.io.File;
import java.util.LinkedList;

public class _07ListarEnProfundidadRecursivo {

	public static void main(String[] args) {
		File f = new File(".");
		mostrarContenido(f);
	}
	
	public static void mostrarContenido(File carpeta) {
		File[] contenido = carpeta.listFiles();
		for (int i = 0; i < contenido.length; i++) {
			if(contenido[i].isDirectory()) {
				//Pongo la carpeta como pendiente de procesar
				mostrarContenido(contenido[i]);
				
			}else {
				if (contenido[i].isFile()) {
					//Muestro el fichero
					System.out.println("\t - " + contenido[i].getPath());
				}
			}
		}
	}
	
/**
 * EJERCICIO1 
 * El ususario introduce el nombre de la ruta de una carpeta
 * -mostrar cuantos bytes ocupa (el primer nivel)
 * -mostrar cuantos bytes ocupa (todos los niveles)
 * 
 * si la carpeta no existe-> mensaje error
 * si la carpeta no es carpeta -> mensaje error
 * 
 * EJERCICIO2
 * El ususario introduce el nombre de la ruta de una carpeta
 * -mostar nombre del archivo del primer nivel mas antiguo
 * 
 * si no contiene ningun archivo -> mensaje error
 * si no es una carpeta -> error
 */
}
