package Interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dao.DaoArticulo;
import Dao.DaoDepartamento;
import Dao.DaoModeloArticulo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
// Buscar los articulos (idarticulo) filtrando por modelo, departamento.
// por los dos par�metros por uno de los dos o por ninguno

public class ejem_buscar_art_modelo_dept {
	private static ConexionJdbc conJdbc = null;
	private static String decision;
	private static Integer v_modelo;
	private static Integer v_dept;
	List<Integer> result = new ArrayList<>();

	public static void main(String[] args) {
		try {
			// Crea un objeto conexionJdbc y conecta
			conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conJdbc.conectar();
			DaoDepartamento daoDepartamento = new DaoDepartamento();
			DaoModeloArticulo daoModeloArticulo = new DaoModeloArticulo();
			DaoArticulo daoArticulo = new DaoArticulo();
			List<Integer> result = new ArrayList<>();
			v_modelo = null;
			v_dept = null;

			Scanner tec = new Scanner(System.in);
			System.out.println("Buscar por modelo de articulo si/no. ");
			decision = tec.next();

			if (decision.equals("si")) {
				System.out.println("Introduce el id del modelo");
				v_modelo = tec.nextInt();
				tec.nextLine();

				if (daoModeloArticulo.buscarPorId(v_modelo) == null) {
					System.out.println("el ide del modelo no existe");
					v_modelo = null;
				}
			}
			tec.nextLine();

			System.out.println("Buscar por departamento del articulo si/no. ");
			String busca_dept = tec.nextLine();

			if (busca_dept.equals("si")) {
				System.out.println("Introduce el id del departamento");
				v_dept = tec.nextInt();
				tec.nextLine();

				if (daoDepartamento.buscarPorId(v_dept) == null) {
					System.out.println("el ide del modelo no existe");
					v_dept = null;
				}
			}

			if (decision.equals("si") || busca_dept.equals("si")) {
				result = daoArticulo.filtarPorModDepat(v_modelo, v_dept);
				for (Integer integer : result) {
					System.out.println("Id de Articulo: " + integer);
				}
			} else
				System.out.println("No hay datos para la busqueda");

		} catch (BusinessException e) {
			System.out.println("No se pudo hacer la consulta");
		} finally {
			conJdbc.desconectar();
		}
	}

}
