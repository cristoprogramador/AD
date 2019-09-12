package _02ejemplos;

import java.io.File;

public class _01ClaseFile {
	public static void main(String[] args) {
		//la clase File representa UNA NOMBRE de fichero o carpeta
		File f = new File("carta.txt");
		File f2 = new File("c:\\docuementos\\carta.doc");
		File f3 = new File("/home/carta.doc");
		//Los nombres de ficheros y carpetas se pueden
		//expresar con rutas absolutas o relativas, Las
		//rutas relativas comienzan en la caprtea del poryecto.
	}

}
