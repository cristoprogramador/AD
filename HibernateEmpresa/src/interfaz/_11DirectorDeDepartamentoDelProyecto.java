package interfaz;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Departamento;
import pojo.Empleado;
import pojo.Proyecto;

public class _11DirectorDeDepartamentoDelProyecto {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {
			//Buscamos y guardamos el proyecto 2
			Proyecto proyecto = daoProyecto.buscarPorId(2);
			//Sacamos su departamento
			Departamento departamento =proyecto.getDepartamento();
			//Buscamos el director del departamento y lo imprimimos por patnalla
			Empleado director = daoDepartamento.getDirector(departamento.getNumDep());
			System.out.println(director.getNombre());			
			
			System.out.println("Proceso terminado correctamente");
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			UtilesHibernate.closeSession();
			UtilesHibernate.closeSessionFactory();			
		}
	}

}
