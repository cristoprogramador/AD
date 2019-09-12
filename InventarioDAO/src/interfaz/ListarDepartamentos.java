package interfaz;



import java.util.List;

import dao.DaoDepartamento;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Departamento;;

public class ListarDepartamentos {

	public static void main(String[] args) {
		ConexionJdbc conexionJdbc=null;
		
		conexionJdbc=new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conexionJdbc.conectar();
		
		List<Departamento> lista;
		
		DaoDepartamento daoDepartamento = new DaoDepartamento();
		
		try {
			lista = daoDepartamento.buscarTodos();
			
			for (int i = 0; i < lista.size(); i++) {
				System.out.println(lista.get(i));
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}finally {
			conexionJdbc.desconectar();
		}
		

	}

}
