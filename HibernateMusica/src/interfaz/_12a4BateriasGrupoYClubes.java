package interfaz;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.DaoCompanyia;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Companyia;
import pojo.Disco;
import pojo.Grupo;
import pojo.Pertenece;

public class _12a4BateriasGrupoYClubes {

	public static void main(String[] args) {
		/* Ejercicio 12 una n:n
		 * si lo que quieres es acceder a una lista(muchos) necesitas join, 
		 * si es a uno se puede acceder al objeto		 */
	
		/* vi. De cada compañía, nombre de la compañía y nombre de los artistas que
		 * guardan alguna relación con ella.		 */
	
		UtilesHibernate.openSession();

		DaoCompanyia daoCompanyia = new DaoCompanyia();
		try {
			System.out.println("COMPAÑIA Y ARTISTAS");
			System.out.println("==========================================");
			//Adquirimos la lista de compañias
			List<Companyia> listaCompanyias = daoCompanyia.buscarTodos();
			//Por cada compañia
			for (Companyia c : listaCompanyias) {
				//Imprimimos el nombre de la compañia
				System.out.println("COMPAÑIA: "+c.getNombre());
				//Sacamos la lista de discos
				Set<Disco> d = c.getDiscos();
				//De cada disco sacamos el nombre de los grupos
				Set<Grupo> g = new HashSet<Grupo>();
				for (Disco disco : d) {
					//los guardamos en una lista que no permite duplicados
					g.add(disco.getGrupo());				
				}
				//Imprimimos el nombre de los artistas de cada grupo
				for (Grupo grupo : g){
					//Creamos una lista de sus componenetes
					Set<Pertenece> componentes = grupo.getPerteneces();
					//para cada componente
					for (Pertenece p : componentes) {
						//Imprimimos su nombre y su función
						System.out.println("Nombre: " + p.getArtista().getNombre());
					}
				}
				System.out.println();
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
			UtilesHibernate.closeSessionFactory();;
		}
	}
}
