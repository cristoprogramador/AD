package flujosEstandar;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Escribir un método void sumaEdades() que lea de teclado las edades de una
 * serie de personas y muestre cuanto suman. El método finalizará cuando el
 * usuario introduzca una edad negativa Escribe un método main que llame al
 * método anterior para probarlo.
 * 
 * @author elsal
 *
 */
public class Ej3_SumarEdades {

	public static void main(String[] args) {
		sumaEdades();
	}

	private static void sumaEdades() {

		char c;		
		int sumaEdades = 0;		
		boolean continuar = true;
		try {
			do {
				System.out.println("Escriba su edad");
				String edad = "";
				int edadInt = 0;
				
				do {
					c = (char) System.in.read();
					edad += c;
				} while (c != '\n');
				
				edadInt = Integer.parseInt(edad.trim());
				
				if (edadInt >= 0)
					sumaEdades += edadInt;
				else
					continuar = false;

			} while (continuar);
			System.out.println("Edad total: " + sumaEdades + " años");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
