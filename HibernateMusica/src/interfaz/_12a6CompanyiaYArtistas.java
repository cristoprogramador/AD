package interfaz;

import java.util.List;
import java.util.Set;

import dao.DaoPertenece;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Club;
import pojo.Pertenece;

public class _12a6CompanyiaYArtistas {

	public static void main(String[] args) {
		/* Ejercicio 12 una n:n
		 * si lo que quieres es acceder a una lista(muchos) necesitas join, 
		 * si es a uno se puede acceder al objeto		 */
	
		/* iv. De cada artista que actúa como batería, grupo con el que lo hace y clubes
		 * en los que toca.		 */
	
		UtilesHibernate.openSession();

		DaoPertenece daoPertenece = new DaoPertenece();
		try {
			//Adquirimos la lista de artistas pertenecientes a grupos
			List<Pertenece> listaPertenecientes = daoPertenece.buscarTodos();
			
			System.out.println("Bateria - Grupo y Cubes donde actua");
			System.out.println("======================================================================");
			//Para cada artista
			for (Pertenece p : listaPertenecientes) {
				//Si es bateria Imprimimos su nombre
				if (p.getFuncion().equals("batería")){
					System.out.println("-----------------------------");
					System.out.println("Nombre del Bateria: " + p.getArtista().getNombre() 
										+ " Grupo: " + p.getGrupo().getNombre());
					System.out.println("-----------------------------");
					//Sacamos su lista de clubes
					Set<Club> listaClubes = p.getGrupo().getClubs();
					//para cada Club
					for (Club c : listaClubes) {
						//Imprimimos su nombre
						System.out.println("Nombre: " + c.getNombre());
					}				
					System.out.println();
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
			UtilesHibernate.closeSessionFactory();;
		}
	}
}
