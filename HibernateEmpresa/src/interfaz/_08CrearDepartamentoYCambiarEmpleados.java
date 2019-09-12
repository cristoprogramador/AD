package interfaz;

import java.util.List;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Departamento;
import pojo.Empleado;

public class _08CrearDepartamentoYCambiarEmpleados {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {
			
			//Creamos un nuevo departamento id 6, Nuevas Tecnologias
			Departamento departamento = new Departamento();
			departamento.setNumDep(6);
			departamento.setNombre("Nuevas Tecnologias");
			//Guardamos el departamento
			daoDepartamento.grabar(departamento);
			
			//Quitamos los trabajadores del departamento 6
			//Sacamos la lista de empleados del departamento 6
			List<Empleado> lsEmp5 = daoDepartamento.listarEmpleados(5);
			//Damos de baja en departamento 6 y de alta en 5 a todos
			for (Empleado empleado : lsEmp5) {
				empleado.setDepartamentoByDepartamento(null);
				empleado.setDepartamentoByDepartamento(departamento);
				daoEmpleado.actualizar(empleado);
			}
			
			
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
