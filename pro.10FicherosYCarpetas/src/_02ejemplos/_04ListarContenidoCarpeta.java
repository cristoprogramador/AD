package _02ejemplos;

import java.io.File;

public class _04ListarContenidoCarpeta {

	public static void main(String[] args) {
		File f = new File("/home");
		
		String[] contenido = f.list();
		for (int i = 0; i < contenido.length; i++) {
			System.out.println(contenido[i]);
		}

	}

}
