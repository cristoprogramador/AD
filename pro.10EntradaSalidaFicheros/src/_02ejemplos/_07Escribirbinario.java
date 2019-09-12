package _02ejemplos;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class _07Escribirbinario {

	public static void main(String[] args) {
		//Escribimos en un fichero BINARIO datos de jugadores de baloncesto
		
		DataOutputStream f = null;
		
		try {
			//Si el fichero exite se sobreescribirá
			//Si el fichero no existe, se creará.
			f = new DataOutputStream(new FileOutputStream (new File ("jugadores.bin")));
			//Escribimos información del jugador
			f.writeInt(89);//Dorsal
			f.writeUTF("Pepe");//Nombre
			f.writeDouble(1.99);//Estatura
			f.writeBoolean(false);//Si es capitán o no
			
			f.writeInt(80);//Dorsal
			f.writeUTF("Luis");//Nombre
			f.writeDouble(2.09);//Estatura
			f.writeBoolean(false);//Si es capitán o no
			
			f.writeInt(23);//Dorsal
			f.writeUTF("Antonio");//Nombre
			f.writeDouble(1.89);//Estatura
			f.writeBoolean(false);//Si es capitán o no
			
			f.writeInt(44);//Dorsal
			f.writeUTF("Roberto");//Nombre
			f.writeDouble(2.10);//Estatura
			f.writeBoolean(false);//Si es capitán o no
			
		} catch (FileNotFoundException e) {
			System.out.println("Error de apertura");
		} catch (IOException e) {
			System.out.println("Error de escritura");
		} finally {
			if(f !=null) {
				try {
					f.close();
				} catch (IOException e) {
					//silenciamos la excepción dejandola vacía
				}
			}
		}
	}

}
