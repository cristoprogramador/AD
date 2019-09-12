package _03ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * programa que lea ficheros con notas y separe las notas por archivos, aprobadas.txt suspendidas.txt
 */
public class _00NotasAprobadosYSuspendidos {

	public static void main(String[] args) {
		Scanner fe = null; //fichero entrada
		PrintWriter fs1 = null; //fichero salida1
		PrintWriter fs2 = null; //fichero salida2
		
		try {
			
			fe = new Scanner(new File("notasAlumnos.txt"));
			fs1 = new PrintWriter(
					new FileOutputStream(
						new File("aprobados.txt"), true));
			fs2 = new PrintWriter(
				new FileOutputStream(
						new File("suspendidos.txt"), true));
						
			while (fe.hasNext()) {
				//leer
				
				double nota = fe.nextDouble();fe.nextLine();//nextLine:limpiar buffer memo
				String nombre = fe.nextLine();	
				
				//escribir		
				if (nota>=5.0) { 
				fs1.println(nota);
				fs1.println(nombre);		
				}else {
					fs2.println(nota);
					fs2.println(nombre);			
				}		
			}
		
		}catch(FileNotFoundException e) {
			System.out.println("Error de apertura");
		}finally {
			if(fe != null) {
				fe.close();
			}
			if( fs1 != null) {
				fs1.close();
			}
			if( fs2 != null) {
				fs2.close();
			}
		}

	}

}
