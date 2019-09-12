package interfaz;

import java.util.List;
import java.util.Set;

import dao.DaoGrupo;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Grupo;
import pojo.Pertenece;

public class _12a3GrupoComponentesYFunciones {

	public static void main(String[] args) {
		/* Ejercicio 12 una n:n
		 * si lo que quieres es acceder a una lista(muchos) necesitas join, 
		 * si es a uno se puede acceder al objeto		 */
	
		/* iii. De cada grupo, el nombre del grupo, nombre de los artistas que lo
		 * integran y función que desempeña el artista dentro del grupo.		 */
	
		UtilesHibernate.openSession();

		DaoGrupo daoGrupo = new DaoGrupo();
		try {
			//Adquirimos la lista de canciones de mas de tres minutos
			List<Grupo> listaGrupos = daoGrupo.buscarTodos();
			
			System.out.println("Grupos - Artistas y Funciones");
			System.out.println("======================================================================");
			//Para cada grupo
			for (Grupo grupo : listaGrupos) {
				//Imprimimos su nombre
				System.out.println("-----------------------------");
				System.out.println("Grupo: " + grupo.getNombre());
				System.out.println("-----------------------------");
				//Creamos una lista de sus componenetes
				Set<Pertenece> componentes = grupo.getPerteneces();
				//para cada componente
				for (Pertenece p : componentes) {
					//Imprimimos su nombre y su función
					System.out.println("Nombre: " + p.getArtista().getNombre() + " Función " + p.getFuncion());
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
