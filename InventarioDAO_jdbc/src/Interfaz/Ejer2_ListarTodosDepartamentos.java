package Interfaz;

import java.util.ArrayList;
import java.util.List;

import Dao.DaoDepartamento;
import jdbc.ConexionJdbc;
import pojos.Departamento;

public class Ejer2_ListarTodosDepartamentos {

	public static void main(String[] args) {

		ConexionJdbc conJdbc = null;
		List<Departamento> departamentos = new ArrayList<Departamento>();
		
		try{
			//conectamos con la BD
			conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conJdbc.conectar();
			
			
			//Creamos un DaoDepartamento y volcamos la busquedad de todos los departamentos
			DaoDepartamento daoDepartamento = new DaoDepartamento();
			departamentos = daoDepartamento.buscarTodos();
			
			//Listamos todos los departamentos
			for (Departamento departamento : departamentos) {
				System.out.println(departamento.toString());
			}
			
		}catch(Exception e){
			System.out.println("Error: pongase en contacto con el admin");
		}finally{
			//finalmente desconectamos		
			System.out.println(conJdbc.desconectar());
		}
	
	}

}
