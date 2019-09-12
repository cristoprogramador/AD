package _03ejercicios;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class _04CalculosPersonas {

	public static void main(String[] args) {
		DataInputStream f = null;
		double total = 0;
		int cont = 0;
		try {
			f = new DataInputStream(new FileInputStream(new File("personas.dat")));
			
			while(true) {
				String nombre = f.readUTF();
				int edad = f.readInt();
				double peso = f.readDouble();
				double est = f.readDouble();
				
				if (edad >= 20 && edad <= 30) {
					total = total + est;
					cont++;
				}

			}
		}catch (EOFException e){
			if (cont>0) {
				System.out.println("Estatura media personas entre 20 y 30: " + (total/cont));
			}else {
				System.out.println("No hay personas entre 20 y 30 años");
			}
			
		}catch (FileNotFoundException e) {
			System.out.println("Error de apertura");
		}catch (IOException e) {
			System.out.println("Error de lectura");
		}finally {
			if (f != null) {
				try {
					f.close();
				}catch (IOException e){
					
				}
			}
		}

	}

}
