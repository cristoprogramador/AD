package interfaz;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Departamento;
import pojo.Empleado;

public class _02InsertarEmpleado {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {
			//Buscamos el departamento de informatica
			//Generamos un metodo buscarPorNombre para buscar por nombre en el DaoDepartamento
			//Guardamos el departamento que recivimos
			
			Departamento departamento = daoDepartamento.buscarPorNombre("INFORMATICA");
			//Si devuelve nulo informamos que el nombre no se encuentra
			if (departamento == null){
				System.out.println("El nombre del departamento no existe o está mas de una vez");
			}//Si recivimos un departamento continuamos con el programa
			else{
				//Generamos un nuevo empleado 33356, Abtonio Villanueva, está y dirige INFORMATICA
				Empleado empleado = new Empleado();
				empleado.setNss(33365);
				empleado.setNombre("Antonio Villanueva");
				empleado.setDepartamentoByDepartamento(departamento);
				empleado.setDepartamentoByDirige(departamento);
				
				//Guardamos el empleado
				daoEmpleado.grabar(empleado);
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
