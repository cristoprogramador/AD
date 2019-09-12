package interfaz;

import java.util.List;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Empleado;
import pojo.Proyecto;

public class _10DepartamentoConMasEmpleados {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {
			//Buscamos y Guardamos la lista de empleados de los departamentos 1 y 2
			List<Empleado> listaEmpleadosDp1 = daoDepartamento.listarEmpleados(1);
			List<Empleado> listaEmpleadosDp2 = daoDepartamento.listarEmpleados(2);

			//Mostramos y Comparamos y mostramos resultado.
			System.out.println("Empleados del departamento 1: "+listaEmpleadosDp1.size());
			System.out.println("Empleados del departamento 2: "+listaEmpleadosDp2.size());
			System.out.println();
			if (listaEmpleadosDp1.size() >  listaEmpleadosDp2.size()){
				System.out.println("El departamento 1 tiene mas empleados");
			}else if (listaEmpleadosDp1.size() <  listaEmpleadosDp2.size()){
				System.out.println("El departamento 2 tiene mas empleados");
			}else{
				System.out.println("Ambos departamentos tienen los mismos empleados");		
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
