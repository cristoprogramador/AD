package lecturaEscrituraEnFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Crea un fichero de texto y escribe una frase en él. 
 * Utiliza las clase FileWriter y FileReader
 * @author elsal
 *
 */
public class Ej08_EscribirFicheroTexto {
	
	public static void main(String[] args) {
		FileWriter fos = null;
		try{
			fos = new FileWriter("nombre.txt");
			//Escribir caracter a acarcter
			fos.write('h');
			fos.write('o');
			fos.write('l');
			fos.write('a');
			fos.write('\n');//caracter para salto de linea
		
			fos.write("€uro");
		}catch(FileNotFoundException e){
			System.out.println("no se puede crear o abrir el fichero");
		}catch (IOException e){
				System.out.println("Error");
		}finally{
			if (fos!= null){
				try{
					fos.close();
				}catch(IOException e){
					System.out.println("error al cerrar el fichero");
				}
			}				
		}
		
		//Leer el fichero nombre.txt
		FileReader fis = null;
		try{
			fis = new FileReader("nombre.txt");
			int caracter;
			
			do{
				caracter = fis.read();
				//si no es el fin del fichero
				if(caracter != -1){
					System.out.print((char)caracter);
				}
			}while(caracter != -1);
			//-1 es el codigo de fin de fichero
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
				System.out.print("Error de lectura");
		}finally{
			if (fis!= null){
				try{
					fis.close();
				}catch(IOException e){
					System.out.println("error al cerrar el fichero");
				}
			}				
		}
		System.out.println();
		
		//Leer el fichero linea a linea
		try{
			fis = new FileReader("nombre.txt");
			BufferedReader bf = new BufferedReader(fis);
			String linea;
			
			//Mientras la linea no sea null
			int cont = 0;
			while((linea=bf.readLine()) != null){
				cont++;
				System.out.println(cont + ": " + linea);
			}
			System.out.print("Total lineas: " + cont);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
				System.out.print("Error de lectura");
		}finally{
			if (fis!= null){
				try{
					fis.close();
				}catch(IOException e){
					System.out.println("error al cerrar el fichero");
				}
			}				
		}
		System.out.println();
		
		//Escrivir el fichero linea a linea Revisar
		try {
			FileWriter fobf=new FileWriter("nuevo1.txt");
			BufferedWriter bfw=new BufferedWriter(fobf);
			bfw.write("hola");
			bfw.newLine();
			bfw.write("adios");
			bfw.newLine();
			bfw.close();
							
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			System.out.print("error de lectura");
		}finally
		{
			if (fos!= null){
			try{
				fos.close();
			}catch(IOException e){
				System.out.println("error al cerrar el fichero");
			}
		}	
		
		}
	}
}
