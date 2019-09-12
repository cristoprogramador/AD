package flujosEstandar;

import java.io.IOException;
import java.io.InputStreamReader;
/**
 * InputStreamReader (StreamReader) está orientado a carácteres. 
 * Escribe un programa que lea un carácter de teclado usando un InputStreamReader 
 * y muestre su valor (int) por pantalla. Pruébalo con un carácter “extraño”, 
 * por ejemplo ‘€’. ¿Se obtiene el mismo resultado que en el ejercicio anterior?.
 * 
 * @author elsal
 *
 */
public class Ej5_leerCaracter {
	//Examen no va desde la pagina 17

	public static void main(String[] args) {
		System.out.println("Escriba su nombre");
		InputStreamReader isr =  new InputStreamReader(System.in);

		try {
						
			System.out.println("Pulse una tecla");			
			System.out.println("El caracter es " + isr.read());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
