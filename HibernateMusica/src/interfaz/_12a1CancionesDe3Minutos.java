package interfaz;

import java.util.Date;
import java.util.List;
import java.util.Set;

import dao.DaoCancion;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Cancion;
import pojo.Disco;

public class _12a1CancionesDe3Minutos {

	public static void main(String[] args) {
		// Ejercicio 12 una n:n
		// si lo que quieres es acceder a una lista(muchos) necesitas join, si es a uno se puede acceder al objeto
	
		/*i.De las canciones que duran más de 3 minutos, mostra
		r el título, la	duración, el nombre del disco en que se encuentran 
		y la fecha en la que se lanzó el disco.*/
	
		UtilesHibernate.openSession();

		DaoCancion daoCancion = new DaoCancion();
		try {
			//Adquirimos la lista de canciones de mas de tres minutos
			List<Cancion> l_canciones = daoCancion.cancionesPorDuracion(3.0);
			//Creamos un nuevo disco para guardar el objeto disco de cada canción
			Disco d = new Disco();
			
			System.out.println("Canciones de mas de 3 minutos");
			System.out.println("======================================================================");
			//Para cada canción
			for (Cancion cancion : l_canciones) {
				//Cogemos su lista de discos	
				Set<Disco> listaDiscos = cancion.getDiscos();
				//para cada lista de discos
				for (Disco disco : listaDiscos) {
					//si el disco contiene la canción
					if (disco.getCancions().contains(cancion)){
						//devolvemos el disco
						d = disco;
					}
				}
				System.out.println("Titulo: " + cancion.getTitulo() + " --- duracion: " 
				+ cancion.getDuracion() + " --- disco: " + d.getNombre());
			}
			
			/*
			 * 	ii. En la consulta anterior hacer que solo se muestren canciones que están en
			 *	discos que se lanzaron a partir de 1982 (puedes probar con otras fechas).
			 */
			System.out.println("Canciones de discos que se lanzarón a partir del 1982");
			System.out.println("======================================================================");
			for (Cancion cancion : l_canciones) {
				//Cogemos su lista de discos	
				Set<Disco> listaDiscos = cancion.getDiscos();
				//Creamos un objeto fecha con la deseada
				Date fecha = new Date (81, 12, 31);
				//para cada lista de discos
				for (Disco disco : listaDiscos) {
					//si el disco contiene la canción
					if (disco.getCancions().contains(cancion)){
						//devolvemos el disco
						d = disco;
					}
					//Si la fecha del disco es posterior a la indicada
					if (d.getFecha().after(fecha))
						//mostramos la fecha
						System.out.println("Titulo: " + cancion.getTitulo() + " --- duracion: " 
						+ cancion.getDuracion() + " --- disco: " + d.getNombre() + " Año: " + d.getFecha());
				}				
			}
			
//Devuelve un Lis<Cancion> iV
//SELECT c From Grupo g join g.discos d join d.canciones c 
//Where g.cod = :grupo and
//c.duracion  = (SELECT MIN((c2.duracion) from Disco d2 join d2.canciones c2 where d2.cod = d.cod group by d.cod);
			

		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			UtilesHibernate.closeSession();
			UtilesHibernate.closeSessionFactory();
		}
	}
}
