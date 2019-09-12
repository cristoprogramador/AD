package interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.DaoArticulo;
import jdbc.ConexionJdbc;
import pojos.Articulo;

public class Ejercicio_3 {
	
	public static void main(String[] args) {

		ConexionJdbc conJdbc = null;
		String departamento;
		List<Articulo> articulos = new ArrayList<Articulo>();
		
		try{
			//conectamos con la BD
			conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conJdbc.conectar();
			
			Scanner tec = new Scanner(System.in);
			System.out.println("Consulta de articulos por departamentos");
			System.out.println("Introduzca nombre del departamento: ");
			departamento = tec.nextLine();
			
			DaoArticulo daoArticulo = new DaoArticulo();
			articulos = daoArticulo.consultarArticulosDepartamento(departamento);
			
			//Listamos todos los articulos
			for (Articulo articulo : articulos) {
				System.out.println("Numero de serie: "+ articulo.getNumserie() 
				+ "  Modelo: "+articulo.getModelo());
			}
			
			System.out.println("Total de articulos en el departamento "
					+ departamento + ": " 
					+ daoArticulo.consultarNumeroArticulosDepartamento(departamento));
		}catch(Exception e){
			System.out.println("Error: pongase en contacto con el admin");
		}finally{
			//finalmente desconectamos		
			System.out.println(conJdbc.desconectar());
		}
	
	}
}
