package entradaOrientadaALineas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Entrada �orientada a l�neas�.
 * En los ejercicios anteriores, las limitaciones de la clase utilizada (InputStream), 
 * nos obliga a incluir en el programa instrucciones que detecten que el usuario ha terminado 
 * su entrada (ha pulsado INTRO). 
 * La clase BuffereReader dispone del m�todo readLine(), capaz de leer una l�nea completa 
 * (la propia instrucci�n detecta el final de la l�nea) y devolver un String.
 * 
 * Repite el ejercicio 1 utilizando un BufferedReader asociado a la entrada est�ndar. 
 * La clase BufferedReader, est� orientada a leer caracteres en lugar de bytes. 
 * �Qu� ocurre ahora si el usuario introduce un car�cter �extra�o� en su nombre?
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
