package _03ejercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 10.- (Notas) Escribir un programa que almacene en un fichero binario
 * (notas.dat) las notas de 20 alumnos. El programa tendrá el siguiente
 * funcionamiento: 
 *  En el fichero se guardarán como máximo 20 notas, pero se
 * pueden guardar menos. El proceso de introducción de notas (y en consecuencia,
 * el programa) finalizará cuando el usuario introduzca una nota no válida
 * (menor que cero o mayor que 10). 
 *  Si, al comenzar la ejecución, el fichero
 * ya contiene notas, se indicará al usuario cuántas faltan por añadir y las
 * notas que introduzca el usuario se añadirán a continuación de las que hay. 
 *  Si, al comenzar la ejecución, el fichero ya contiene 20 notas, se le
 * preguntará al usuario si desea sobrescribirlas. En caso afirmativo las notas
 * que introduzca sustituirán a las que hay y en caso negativo el fichero no se
 * modificará.
 * 
 * @author alumno
 *
 */
public class _10Notas {
	static Scanner tec = new Scanner(System.in);
	static int numNotas = 0;
	
	public static void main(String[] args) {
		
		File notas = new File("notas.bin");
		//Averiguamos numero notas ya introducidas: Forma1
		if(notas.exists() && notas.isFile()) {
			numNotas = (int)notas.length() / Integer.BYTES;
		}
		
		if (numNotas == 20) {
			System.out.println("El fichero contiene 20 notas\n Desea sobreescribirlas? s/n");
			String r = tec.next();
			if (r.toLowerCase().equals("s")) {
				numNotas = 0;
				escribirNotas("notas.bin", numNotas);
			}else {
				System.out.println("El fichero no se modificará\n Notas:\n" + notas);
			}
		}else {
			System.out.println("Faltan " + (20-numNotas) + " por anyadir");
			escribirNotas("notas.bin", numNotas);
		}
	
	}

	
	public static void escribirNotas (String ficheroNotas, int numNotas) {
		DataOutputStream f = null;
		int nota = 0;
		boolean anyadir = false;
		
		if (numNotas != 0){
			anyadir = true;
		}
		
		try {
			f = new DataOutputStream(new FileOutputStream(new File(ficheroNotas),anyadir));
			
			while(numNotas < 20 && nota >= 0 && nota < 11) {
				System.out.println("Introduzca nota numero " + (numNotas));
				nota = tec.nextInt();
				f.write(nota);
				numNotas++;
			}
			
		}catch (EOFException e ) {			
		}catch (FileNotFoundException e) {
		}catch (IOException e) {
		}finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					//Silenciada
				}
			}
		}
	}
	/*
	//Averiguamos numero notas ya introducidas: Forma2
	try {
		f = new DataInputStream(new FileInputStream(new File("notas.bin")));
		while(true) {
			f.readInt();
			numNotas++;
		}
		
	}catch (EOFException e ) {			
	}catch (FileNotFoundException e) {
	}catch (IOException e) {
	}finally {
		if (f != null) {
			try {
				f.close();
			} catch (IOException e) {
				//Silenciada
			}
		}
	}*/
}
