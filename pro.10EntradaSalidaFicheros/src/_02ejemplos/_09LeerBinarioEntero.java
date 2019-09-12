package _02ejemplos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class _09LeerBinarioEntero {
	// Lee el primer jugador del fichero "jugadores.bin"
	public static void main(String[] args) {
		DataInputStream f = null;

		try {
			f = new DataInputStream(new FileInputStream(new File("jugadores.bin")));
			
			while(true) {
			// Leemos los datos del primer jugador
			int dorsal = f.readInt();
			String nombre = f.readUTF();
			double estatura = f.readDouble();
			boolean esCapitan = f.readBoolean();

			// Mostramos información del jugador
			System.out.println("Dorsal " + dorsal);// Dorsal
			System.out.println("Nombre " + nombre);// Nombre
			System.out.println("Estatura " + estatura);// Estatura
			System.out.println(esCapitan? "Es capitan" : "No es capitán");// Si es capitán o no
			}

		} catch (EOFException e) {
			System.out.println("Fin");
		} catch (FileNotFoundException e) {
			System.out.println("Error de apertura");
		} catch (IOException e) {
			System.out.println("Error de escritura");
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					// silenciamos la excepción dejandola vacía
				}
			}
		}
	}
}
