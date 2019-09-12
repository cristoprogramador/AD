package Interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dao.DaoUsuario;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Usuario;

public class Ejer2_2_Tema1_DAO2 {

	private static ConexionJdbc conJdbc = null;
	private static Integer tipoUsuario;
	private static String departamento;
	private static String decision;
	private static List<Usuario> result;

	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();
		tipoUsuario = null;
		departamento = null;
		result = new ArrayList<>();
		DaoUsuario daoUsuario = new DaoUsuario();

		try {
			Scanner tec = new Scanner(System.in);

			System.out.println("Buscar por tipo de usuario si/no. ");
			decision = tec.nextLine();

			if (decision.equals("si")) {
				System.out.println("Introduce el id del tipo de usuario");
				tipoUsuario = tec.nextInt();
				tec.nextLine();
			}

			System.out.println("Buscar por departamento del articulo si/no. ");
			decision = tec.nextLine();

			if (decision.equals("si")) {
				System.out.println("Introduce el nombre del departamento");
				departamento = tec.next();
			}
			// si no hay respuestas nulas
			if (tipoUsuario != null || departamento != null) {
				result = daoUsuario.filtarPorTipoUsuarioYDepartamento(tipoUsuario, departamento);
			} else
				System.out.println("No hay datos para la busqueda");
		
			if(result.size()>0){
				for (Usuario u : result) {
					System.out.println(u.toString());
				}
			}else
				System.out.println("Sin sesultados en la busqueda");
			
		} catch (BusinessException e) {
			System.out.println("No se pudo hacer la consulta");
		} finally {
			conJdbc.desconectar();
			System.out.println("Desconectado de la Base de datos");
		}
	}

}
