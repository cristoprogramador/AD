package interfaz;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Etapa;

public class _09HQLVariasTablas {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		
		System.out.println("");

		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {
			// Empezar Transacción
			s.beginTransaction();
			
			//a) De cada etapa, ciudad de salida y llegad y nombre del ciclista que la ha ganado
			/*Query q = s.createQuery("SELECT salida, llegada, ciclista_ganador.nombre from Etapa e");
			//Query q = s.createQuery("SELECT e.salida, e.llegada, c.nombre from Etapa e join e.ciclista_ganador c");

			List<Object[]> lista = q.list();
			
			for (Object[] etapa : lista) {
				System.out.println("Salida: "+ etapa[0] + " Llegada: " + etapa[1]+
						" Ganador: " + etapa[2]);
			}*/
			
			//b) De cada equipo, nombre, director y número de cilcista que tiene.
			/*Query q = s.createQuery("SELECT nombre, director, ciclistas.size from Equipo e ORDER BY ciclistas.size");
			/*Query q = s.createQuery("SELECT nombre, director, count(c) from Equipo e join e.ciclistas c grup by e");

			List<Object[]> lista = q.list();
			
			for (Object[] etapa : lista) {
				System.out.println("Nombre: "+ etapa[0] + " Director: " + etapa[1]+
						" Numero de ciclistas: " + etapa[2]);
			}*/			
			
			//c)De cada equipo, nombe del equipo y numero de etapas que han ganado sus ciclistas
			//De cada equico coge sus ciclistas y de cada cilista coje sus etapas ganadas, agrupamos por equipo y contamos las etaps ganadas
			//Query q = s.createQuery("SELECT e.nombre, COUNT(et) FROM Equipo e JOIN e.ciclistas c JOIN c.etapas_ganadas et group by e");			
			/*Query q = s.createQuery("SELECT e.nombre, SUM(c.etapas_ganadas.size) FROM Equipo e JOIN e.ciclistas c group by e");

			List<Object[]> lista = q.list();
			
			for (Object[] etapa : lista) {
				System.out.println("Nombre: "+ etapa[0] + " Etapas ganadas por sus ciclistas: " + etapa[1]);
			}*/
			
			//d) De cada etapa, ciclista que la ha ganado y equipo alque pertenece
			/*Query q = s.createQuery("SELECT netapa, ciclista_ganador.nombre, e.ciclista_ganador.equipo.nombre FROM Etapa e order by netapa");

			List<Object[]> lista = q.list();
			
			for (Object[] etapa : lista) {
				System.out.println("Etapa: "+ etapa[0] + " - Ciclista Ganador: " + etapa[1] + " - Equipo: " + etapa[2]);
			}*/
			
			//e) Ciclistas que han ganado alguna etapa que salia o llegaba a Benidorm.
			/*Query q = s.createQuery("SELECT salida, llegada, ciclista_ganador.nombre FROM Etapa e where salida = 'Benidorm' or llegada = 'Benidorm'");

			List<Object[]> lista = q.list();
			
			System.out.println("Ciclistas que han ganado una etapa con salida o llegada a Benidor");
			for (Object[] etapa : lista) {
				
				System.out.println("Salida: "+ etapa[0] + " - Llegada: " + etapa[1] + " - Ciclista: " + etapa[2]);
			}*/
			
			//f) Ciclistas que han ganado alguna etapa con salida y llegada de la misma ciudad
			/*Query q = s.createQuery("SELECT salida, llegada, ciclista_ganador.nombre FROM Etapa e where salida = llegada");

			List<Object[]> lista = q.list();
			
			System.out.println("Ciclistas que han ganado una etapa con salida o llegada a Benidor");
			for (Object[] etapa : lista) {
				
				System.out.println("Salida: "+ etapa[0] + " - Llegada: " + etapa[1] + " - Ciclista: " + etapa[2]);
			}*/
			
			//g) Equipos cuyos ciclistas han ganado alguna etapa con salida y llegada en la isma ciudad
			Query q = s.createQuery("SELECT e.nombre, e.ciclistas.nombre from Equipo e where e.ciclistas in (SELECT et.ciclista_ganador FROM Etapa et where et.salida = 'Benidorm' or et.llegada = 'Benidorm')");

			List<Object[]> lista = q.list();
			
			System.out.println("Ciclistas que han ganado una etapa con salida o llegada a Benidor");
			for (Object[] etapa : lista) {
				
				System.out.println("Equipo: "+ etapa[0] + " - Ciclista: " + etapa[1]);
			}
			
			//h) De cada año, número de ciclistas que han nacido ese año.
			
			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();

		}
	}

}
