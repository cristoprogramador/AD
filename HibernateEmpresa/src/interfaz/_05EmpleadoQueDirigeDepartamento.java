package interfaz;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Empleado;

public class _05EmpleadoQueDirigeDepartamento {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {
			//Generamos un metodo getDirector en DaoDepartamento para buscar el director
			//Llamamos al metodoy guardamos al empleado
			Empleado empleado = daoDepartamento.getDirector(2);
			
			System.out.println(empleado.getNombre());
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
