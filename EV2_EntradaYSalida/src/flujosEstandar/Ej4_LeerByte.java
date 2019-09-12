package flujosEstandar;

import java.io.IOException;

/**
 * System.in (InputStream) est� orientado a lectura de bytes. 
 * Escribe un programa que lea un byte de teclado y muestre su valor (int) por pantalla. 
 * Pru�balo con un car�cter �extra�o�, por ejemplo ���.
 * 
 * @author elsal
 *
 */
public class Ej4_LeerByte {

	public static void main(String[] args) {
		System.out.println("Escriba su nombre");

		try {	
						
			System.out.println("Pulse una tecla");			
			System.out.println("El caracter es " + System.in.read());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
