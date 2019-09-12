package Interfaz;

import java.util.Scanner;

import Dao.DaoUsuario;
import jdbc.ConexionJdbc;

public class Ejer3_BorrarUsuario {

	public static void main(String[] args) {
		ConexionJdbc conJdbc = null;
		Scanner tec = new Scanner(System.in);

		try {
			conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conJdbc.conectar();

			System.out.println("Id de usuario a borrar: ");
			int id = tec.nextInt();

			DaoUsuario daoUsuario = new DaoUsuario();
			daoUsuario.borrar(id);

		} catch (Exception e) {
			System.out.println("Error: pongase en contacto con el admin");
			e.printStackTrace();
		} finally {
			//finalmente desconectamos		
			System.out.println(conJdbc.desconectar());
		}
	}
}
