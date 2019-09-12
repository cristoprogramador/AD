package interfaz;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Departamento;
import pojo.Empleado;

public class _06ModificarDirectorDeDepartamento {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {
			//En Empleado el Atributo director no puede estar duplicado por lo que hemos
			//de quitar antes al director para poner otro.
			
			//Guardamos y Comprobamos si el nuevo director existe
			Empleado directorNuevo = daoEmpleado.buscarPorId(1111);
			//Si no existe
			if ( directorNuevo == null)System.out.println("El Empleado No Existe");
			//Si existe cambiamos el director del departamento 1
			else{
				//Guardamos el departamento
				Departamento departamento = daoDepartamento.buscarPorId(1);
				//Guardamos el director actual del departamento
				Empleado directorActual = daoDepartamento.getDirector(1);
				//lo quitamos de director
				directorActual.setDepartamentoByDirige(null);
				//Actualizamos
				daoEmpleado.actualizar(directorActual);
				//Asignamos al nuevo director
				directorNuevo.setDepartamentoByDirige(departamento);				
				//Actualizamos			
				daoEmpleado.actualizar(directorNuevo);
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
