package interfaz;

import java.util.List;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Empleado;

public class _04NumeroYNombresEmpleadosDeDepartamento {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {
			//Generamos una lista para guardar los empleados
			List<Empleado> lsEmp;
			//Generamos un metodo listarEmpleados en el DaoDepartamento
			lsEmp = daoDepartamento.listarEmpleados(1);
			//Indicamos el numero de empleados
			System.out.println("Empleados del departamento id 1 -- "+ daoDepartamento.buscarPorId(1).getNombre() 
					+ " : "	+ lsEmp.size());
			
			for (Empleado empleado : lsEmp) {
				System.out.println("Nombre: " + empleado.getNombre());
			}

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
