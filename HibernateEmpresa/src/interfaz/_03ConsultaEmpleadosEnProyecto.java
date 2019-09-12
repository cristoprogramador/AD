package interfaz;

import java.util.List;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Empleado;

public class _03ConsultaEmpleadosEnProyecto {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {
			//Generamos una lista de Empleados para mostrar el resultado
			List<Empleado> lsEmp;
			//Generamos un metodo listarEmpleados en el DaoProyecto
			//Llamamos al metodo para listar los del proyecto 1 Py1 Guardamos el resultado
			lsEmp = daoProyecto.listarEmpleados(4);
			//Imprimimos el resultado
			System.out.println("Empleados del proyecto id 1");
			for (Empleado empleado : lsEmp) {
				System.out.println("Nombre: " + empleado.getNombre());;
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
