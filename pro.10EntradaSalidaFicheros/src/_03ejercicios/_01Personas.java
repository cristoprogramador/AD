package _03ejercicios;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class _01Personas {

	public static void main(String[] args) {
		DataOutputStream f = null;
		Scanner tec = new Scanner(System.in);

		try {
			String nombre;
			int edad;
			double peso;
			double estatura;

			f = new DataOutputStream(new FileOutputStream(new File("personas.dat")));

			// Escribimos informacion de los double con , . sino da error

			do {

				System.out.println("nombre");
				nombre = tec.nextLine();
				f.writeUTF(nombre);// Nombre

				if (!nombre.isEmpty()) {

				System.out.println("edad");
				edad = tec.nextInt();
				f.writeInt(edad);// Edad

				System.out.println("peso");
				peso = tec.nextDouble();
				f.writeDouble(peso);// Peso

				System.out.println("estatura");
				estatura = tec.nextDouble();
				f.writeDouble(estatura);// Estatura
				tec.nextLine();
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
