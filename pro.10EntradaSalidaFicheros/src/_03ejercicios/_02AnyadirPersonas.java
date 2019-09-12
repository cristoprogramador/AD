package _03ejercicios;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class _02AnyadirPersonas {

	public static void main(String[] args) {
		DataOutputStream f = null;
		Scanner tec = new Scanner(System.in);

		try {
			
			//Sobreescribir o anyadir
			String modo = "";

			do {
				System.out.println("anyadir info (A) o sobreescribir (S)");
				modo = tec.next().toUpperCase();
				tec.nextLine();
			} while (!modo.equals("A") && !modo.equals("S"));

			boolean append;
			if (modo.equals("A"))
				append = true;
			else
				append = false;

			// ---------------------------------------------------------//

			String nombre;
			int edad;
			double peso;
			double estatura;

			f = new DataOutputStream(new FileOutputStream(new File("personas.dat"), append));

			// Escribimos informacion de los double con , . sino da error

			do {

				System.out.println("nombre");
				nombre = tec.nextLine();

				if (!nombre.isEmpty()) {
					System.out.println("edad");
					edad = tec.nextInt();
					System.out.println("peso");
					peso = tec.nextDouble();
					System.out.println("estatura");
					estatura = tec.nextDouble();
					tec.nextLine();

					f.writeUTF(nombre);// Nombre
					f.writeInt(edad);// Edad
					f.writeDouble(peso);// Peso
					f.writeDouble(estatura);// Estatura

				}

			} while (!nombre.isEmpty());

			System.out.println("fin");

		} catch (FileNotFoundException e) {
			System.out.println("Error de apertura");

		} catch (IOException e) {
			System.out.println("Error de lectura");
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
				}
			}
		}

	}

}
