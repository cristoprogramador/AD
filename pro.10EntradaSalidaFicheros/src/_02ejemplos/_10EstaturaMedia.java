package _02ejemplos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class _10EstaturaMedia {
	// Lee el primer jugador del fichero "jugadores.bin"
	public static void main(String[] args) {
		DataInputStream f = null;
		int cont = 0;
		double sum = 0;

		try {
			f = new DataInputStream(new FileInputStream(new File("jugadores.bin")));
			
			while(true) {
			// Leemos los datos de los jugadores
			f.readInt();
			f.readUTF();
			sum += f.readDouble();
			f.readBoolean();

			cont ++;			
			}		
				
		} catch (EOFException e) {
			System.out.println("Estatura media: "+ (sum/cont));
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
