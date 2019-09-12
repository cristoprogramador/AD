package flujosEstandar;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
/**
 * Escribir un programa que solicite al usuario su edad y, utilizando directamente System.in,
 * la lea de teclado y muestre por pantalla un mensaje del estilo “Su edad es 32 años”. 
 * En este caso, será tarea nuestra construir un String 
 * a partir de los bytes leídos y transformarlo posteriormente en un entero.
 * 
 * @author elsal
 *
 */
public class Ej2_LeeEdad {

	public static void main(String[] args) {
		System.out.println("Escriba su edad");
		String edad = "";
		char c;
		
		try {	
			
			do{		
				c = (char) System.in.read();
				edad += c;				
			} while (c != '\n');
				
			System.out.println("Su edad es " + Integer.parseInt(edad.trim()) + " años");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
