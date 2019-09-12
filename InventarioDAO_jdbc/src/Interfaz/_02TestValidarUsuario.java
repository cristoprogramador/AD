package Interfaz;

import java.util.Scanner;

import Dao.DaoUsuario;
import jdbc.ConexionJdbc;

public class _02TestValidarUsuario {

	public static void main(String[] args) {
		ConexionJdbc conexionJdbc = null;
		String usuario, password;

		try {
			conexionJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conexionJdbc.conectar();
			Scanner tec = new Scanner(System.in);

			System.out.println("Introduzca datos de usuario");
			System.out.print("Usuario: ");
			usuario = tec.next();
			System.out.print("Password: ");
			password = tec.next();
			
			DaoUsuario d = new DaoUsuario();

			if (d.ValidarUsuario(usuario, password) == true) {
				System.out.println("ACCESO AUTORIZADO");
			} else {
				System.err.println("ACCESO DENEGADO");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pongase en contacto con el administrador");
		} finally {
			conexionJdbc.desconectar();
		}

	}

}
