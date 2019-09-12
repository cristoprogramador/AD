package interfaz;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Departamento;

public class _01InsertarDepartamento {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {
			
			//Creamos el departamento
			Departamento d = new Departamento();
			d.setNumDep(5);
			d.setNombre("INFORMATICA");
			//Guardamos el departamento
			daoDepartamento.grabar(d);
		
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			UtilesHibernate.closeSession();
			UtilesHibernate.closeSessionFactory();			
		}		
		System.out.println("Proceso terminado correctamente");
	}

}
