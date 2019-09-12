package interfaz;

import java.util.List;

import dao.DaoDepartamento;
import dao.DaoEmpleado;
import dao.DaoProyecto;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Proyecto;

public class _09ProyectosQueDirigeUnDepartamento {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoDepartamento daoDepartamento = new DaoDepartamento();
		DaoEmpleado daoEmpleado = new DaoEmpleado();
		DaoProyecto daoProyecto = new DaoProyecto();

		try {
			//Generamos un metodo listarPoryectos en el DaoDepartamento
			//Buscamos y Guardamos la lista de proyectos del departamento 3 (no tiene ponemos el 1)
			List<Proyecto> listaProyecto = daoDepartamento.listarProyectos(1);
			//Listamos
			for (Proyecto proyecto : listaProyecto) {
				System.out.println(proyecto.getNombre());
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
