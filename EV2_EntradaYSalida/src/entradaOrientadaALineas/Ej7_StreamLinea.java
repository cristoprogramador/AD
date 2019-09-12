package entradaOrientadaALineas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Entrada “orientada a líneas”.
 * En los ejercicios anteriores, las limitaciones de la clase utilizada (InputStream), 
 * nos obliga a incluir en el programa instrucciones que detecten que el usuario ha terminado 
 * su entrada (ha pulsado INTRO). 
 * La clase BuffereReader dispone del método readLine(), capaz de leer una línea completa 
 * (la propia instrucción detecta el final de la línea) y devolver un String.
 * 
 * Repite el ejercicio 1 utilizando un BufferedReader asociado a la entrada estándar. 
 * La clase BufferedReader, está orientada a leer caracteres en lugar de bytes. 
 * ¿Qué ocurre ahora si el usuario introduce un carácter “extraño” en su nombre?
 * 
 * @author elsal
 *
 */
public class Ej7_StreamLinea {
	//Examen no va desde la pagina 17
	public static void main(String[] args) {
		System.out.println("Escriba su nombre");
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));

		try {	
				
			System.out.println("Su nombre es " + br.readLine());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
