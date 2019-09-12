package Interfaz;



import Dao.DaoUsuario;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Usuario;

public class AnyadirComprobandoTipo {
	private static ConexionJdbc conJdbc = null;

	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();
		String nombre;


		try{
			DaoUsuario daoUsuario = new DaoUsuario();
			Usuario u = new Usuario();
			u.setUsername("");
			u.setPassword("");
			u.setRol(1);
			u.setTipo(1);
			//u.setGrupo("");
			u.setDepartamento(1);
			
			daoUsuario.grabar(u);
			
		} catch (BusinessException e) {
			System.out.println("No se pudo hacer la consulta");
		} finally {
			conJdbc.desconectar();
		}
	}

}
