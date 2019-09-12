package Interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dao.DaoUsuario;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Usuario;

public class Ejer2_Tema1_DAO2 {

		private static ConexionJdbc conJdbc = null;
		private static String decision;
		private static String nombre;
		private static String apellido1;
		private static String apellido2;
		List<Integer> result = new ArrayList<>();

		public static void main(String[] args) {
			try {
				// Crea un objeto conexionJdbc y conecta
				conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
				conJdbc.conectar();
				DaoUsuario daoUsuario = new DaoUsuario();
				List<Usuario> result = new ArrayList<>();

				nombre = null;
				apellido1 = null;
				apellido2 = null;

				Scanner tec = new Scanner(System.in);
				System.out.println("Buscar por usuario por nombre si/no. ");
				decision = tec.nextLine();

				if (decision.equals("si")) {
					System.out.println("Introduce nombre: ");
					nombre = tec.nextLine();
				}


				System.out.println("Buscar por usuario por primer apellido si/no. ");
				decision = tec.nextLine();

				if (decision.equals("si")) {
					System.out.println("Introduce el primer apellido");
					apellido1 = tec.nextLine();
				}
				
				System.out.println("Buscar por usuario por segundo apellido si/no. ");
				decision = tec.nextLine();

				if (decision.equals("si")) {
					System.out.println("Introduce el segundo apellido");
					apellido1 = tec.nextLine();
				}

				if (nombre != null || apellido1 != null || apellido2 != null){
					result = daoUsuario.filtrarPorNombre(nombre, apellido1, apellido2);
				
					for (Usuario usuario : result) {
						System.out.println(usuario.getNombre()+" "
					+usuario.getApellido1()+" " +usuario.getApellido2());
					}
					
				} else
					System.out.println("No se puede realizar busqueda.");				
				
			} catch (BusinessException e) {
				System.out.println("No se pudo hacer la consulta");
			} finally {
				conJdbc.desconectar();
			}
		}

}
