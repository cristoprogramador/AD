package interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.DaoArticulo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;

public class Ex2_Ejercicio1 {
	private static ConexionJdbc conJdbc = null;
	private static String decision;
	private static String v_modelo;
	private static String v_marca;
	
	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();
		
		try {
			DaoArticulo daoArticulo = new DaoArticulo();
			List<Integer> result = new ArrayList<>();		
			Scanner tec = new Scanner(System.in);
			
			System.out.println("Buscar por modelo de articulo si/no. ");
			decision = tec.nextLine();

			if (decision.equals("si")) {
				System.out.println("Introduce el modelo del articulo");
				v_modelo = tec.nextLine();
			}

			System.out.println("Buscar por marca del articulo si/no. ");
			String busca_dept = tec.nextLine();

			if (busca_dept.equals("si")) {
				System.out.println("Introduce la marca del articulo");
				v_marca = tec.nextLine();
			}
			
			if (decision.equals("si") || busca_dept.equals("si")) {
				result = daoArticulo.filtarPorModMarca(v_modelo, v_marca);
				for (Integer integer : result) {
					System.out.println("Id de Articulo: " + integer);
				}
			} else
				System.out.println("No hay datos para la busqueda");


		} catch (BusinessException e) {
			System.out.println("No se pudo hacer la petición");
		} finally {
			conJdbc.desconectar();
			System.out.println("Desconectado de la Base de datos");
		}
	}
}
