package Interfaz;

import java.util.List;
import java.util.Scanner;

import Dao.DaoUsuario;
import jdbc.ConexionJdbc;
import pojos.Usuario;

public class Ejer5_UsuarioPorDepartamento {

	public static void main(String[] args) {
		ConexionJdbc conexionJdbc = null;
		String nombre;
		DaoUsuario daoUsuario = new DaoUsuario();
		List<Usuario> usuarios;
		
		try {
			conexionJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conexionJdbc.conectar();
			
			Scanner tec = new Scanner(System.in);
			System.out.print("Departamento: ");
			nombre = tec.nextLine();

			usuarios = daoUsuario.cosultarUsuarioPorDepartamento(nombre);
			for (Usuario u : usuarios) {
				System.out.println(u.getUsername() + " - " + u.getNombre());
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pongase en contacto con el administrador");
		} finally {
			conexionJdbc.desconectar();
		}

	}

}
