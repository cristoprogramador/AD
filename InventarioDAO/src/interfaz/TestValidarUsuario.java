package interfaz;

import java.util.Scanner;

import dao.DaoUsuario;
import jdbc.ConexionJdbc;


public class TestValidarUsuario {

	public static void main(String[] args) {
		ConexionJdbc conexionJdbc=null;
		String usuario, password;
		
		try {
			conexionJdbc=new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conexionJdbc.conectar();
			
			Scanner tec = new Scanner(System.in);
			

			System.out.print("Usuario: ");
			usuario = tec.next();
			System.out.print("Password: ");
			password = tec.next();
			
			DaoUsuario d = new DaoUsuario();
			
			if(d.ValidarUsuario(usuario, password)==true){
				System.out.println("--- PUEDES ENTRAR ---");
			}else{
				System.out.println("--- NO PUEDES ENTRAR ---");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pongase en contacto con el administrador");
		}finally {
			conexionJdbc.desconectar();
		}
		


	}

}
