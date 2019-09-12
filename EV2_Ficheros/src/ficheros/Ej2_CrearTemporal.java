package ficheros;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * Crear un archivo temporal y mostrar por pantalla la ruta en que se encuentra
 * y el nombre del fichero (en dos líneas distintas). 
 * El fichero se tendrá que eliminar automáticamente cuando el programa finalice.
 * @author alumno
 *
 */
public class Ej2_CrearTemporal {

	public static void main(String[] args) {
		String nombreCarpeta;
		try {
		Scanner tec = new Scanner(System.in);
		do{
		System.out.println("Introduce prefijo del archivo (minimo 3 digitos): ");
		nombreCarpeta = tec.nextLine();
		if(nombreCarpeta.length()<3)
			System.out.println("Minimo tres digitos");
		}while(nombreCarpeta.length()<3);

		//Creamos el archivo en la carpeta de archivos temporales y 
		// con el nombre indicado por el usuario y con el sufijo tmp	
		File newFile = File.createTempFile(nombreCarpeta, "tmp");
		
		System.out.println("Ruta: " + newFile.getAbsolutePath());
		System.out.println("Nombre: " + newFile.getName());
		
		System.out.println("Pulse enter para salir y destruir el archivo");
		String out = tec.nextLine();
		//Borramos el archivo temporal al salir.
		newFile.deleteOnExit();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
