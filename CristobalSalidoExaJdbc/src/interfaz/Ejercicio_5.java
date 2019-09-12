package interfaz;

import dao.DaoGrupo;
import dao.DaoUsuario;
import jdbc.ConexionJdbc;
import pojos.Grupo;
import pojos.Usuario;

public class Ejercicio_5 {

	public static void main(String[] args) {
		ConexionJdbc conJdbc = null;
		Grupo grupo;
		Usuario usuario;
		
		try{
			//conectamos con la BD
			conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conJdbc.conectar();
			
			DaoGrupo daoGrupo = new DaoGrupo();
			grupo = new Grupo("2dam", "Segundo DAM");
			daoGrupo.grabar(grupo);
			
			DaoUsuario daoUsuario = new DaoUsuario();
			
			daoUsuario.insertarGrupo(grupo,"Carmen", "Escolano", "Escolano");
			
			
		}catch(Exception e){
			System.out.println("Error: pongase en contacto con el admin");
		}finally{
			//finalmente desconectamos		
			System.out.println(conJdbc.desconectar());
		}
	
	}

}
