package interfaz;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Departamento;
import pojo.Empleado;
import pojo.Proyecto;

public class _12CambiarDepartamentoDeProyectoPorElDeEmpleadoQueDirige {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {			
			//Generamos un metodo getDepartamentoDirigido en DaoEmpleado
			//Buscamos y gurdamos el departamento que dirige el empleado con nss 1111
			Departamento departamento = daoEmpleado.getDepartamentoDirigido(33356);
			if(departamento == null)System.out.println("El empleado no dirige ningún departamento");
			else{
				//Sacamos y modificamos el proyecto py3 para modificar su departamento director
				//Generamos un metodo buscarPorNombre en el daoProyecto
				Proyecto proyecto = daoProyecto.buscarPorNombre("py3");
				//Modificamos el departamento
				proyecto.setDepartamento(departamento);
				//Actualizamos el proyecto
				daoProyecto.actualizar(proyecto);				
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
