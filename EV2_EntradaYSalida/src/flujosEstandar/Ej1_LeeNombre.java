package flujosEstandar;

import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Escribir un programa que solicite al usuario su nombre y, 
 * utilizando directamente System.in, lo lea de teclado y 
 * muestre por pantalla un mensaje del estilo 
 * �Su nombre es Miguel�. 
 * Recuerda que System.in es un objeto de tipo InputStream. 
 * 
 * La clase InputStream permite leer bytes utilizando el m�todo read().
 * Ser� tarea nuestra ir construyendo un String a partir de los bytes le�dos. 
 * Prueba el programa de manera que el usuario incluya en su nombre alg�n car�cter �extra�o�, 
 * por ejemplo el s�mbolo ����Funciona bien el programa? �Por qu�?
 * 
 * @author elsal
 *
 */
public class Ej1_LeeNombre {

	public static void main(String[] args) {
		System.out.println("Escriba su nombre");
		String nombre = "";
		char c;
		try {	
			
			do{		
				c = (char) System.in.read();
				nombre += c;				
			} while (c != '\n');
				
			System.out.println("Su nombre es " + nombre);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
