package interfaz;

import dao.DaoDepartamento;
import dao.DaoUsuario;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Departamento;
import pojos.Usuario;

public class _01CrearDepartamentoYUsuario {

	public static void main(String[] args) {
		
		ConexionJdbc conexionJdbc=null;
		/*
		 * 1 Insertar -> departamento Artes
		 * 2 Consular el id de Artes con el metodo buscarPorNombre en DaoDepartamento
		 * 3 Insertar -> Usuario*/
		
		String nombre = "Artes";
		String[] names = {"usu1","usu2","usu3"};
	
		Departamento d = new Departamento();
		d.setNombre(nombre);
		
		DaoDepartamento daoDepartamento = new DaoDepartamento();
		try {
			conexionJdbc=new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conexionJdbc.conectar();
			
			daoDepartamento.grabar(d);
			Integer id = daoDepartamento.buscarPorNombre(nombre).getIdDepartamento();
		
			DaoUsuario daoUsuario = new DaoUsuario();
			Usuario u = new Usuario();
			
			for (String s : names) {
				u.setUsername(s);
				u.setPassword(s);
				u.setTipo(1);
				u.setRol(4);
				u.setDepartamento(id);
				daoUsuario.grabar(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pongase en contacto con el administrador");
		}finally {
			conexionJdbc.desconectar();
		}

	}

}
