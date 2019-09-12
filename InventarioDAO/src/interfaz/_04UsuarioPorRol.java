package interfaz;


import java.util.List;
import java.util.Scanner;

import dao.DaoUsuario;
import jdbc.ConexionJdbc;
import pojos.Usuario;

public class _04UsuarioPorRol {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		ConexionJdbc conexionJdbc=null;
		List<Usuario> usuarios;
		
		DaoUsuario daoUsuario = new DaoUsuario();	
		String nombre;
		try {
			conexionJdbc=new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conexionJdbc.conectar();
			
			System.out.print("Rol: ");
			nombre= tec.next();
			
			usuarios = daoUsuario.cosultarUsuarioPorRol(nombre);
			for (Usuario u : usuarios) {
				System.out.println(u.getUsername() + " - " + u.getNombre());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pongase en contacto con el administrador");
		}finally {
			conexionJdbc.desconectar();
		}
		
	}

}
