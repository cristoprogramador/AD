package _03ejercicios;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class _03MostrarPersonas {

	public static void main(String[] args) {
		DataInputStream f = null;
		try {
			f = new DataInputStream(new FileInputStream(new File("personas.dat")));
			
			while(true) {
				String nombre = f.readUTF();
				int edad = f.readInt();
				double peso = f.readDouble();
				double est = f.readDouble();
				
				System.out.println("Nombre: "+ nombre);
				System.out.println("Edad: "+ edad);
				System.out.println("Peso: "+ peso);
				System.out.println("Estatura: "+ est);

			}
		}catch (EOFException e){
			System.out.println("Fin");
			
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
