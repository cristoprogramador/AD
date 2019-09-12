package interfaz;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import hibernate.UtilesHibernate;



//mostrar la ciudad de salida y llagada de las ciudades de mas Km que diga el usuario
public class _08EjemploHQL {

	public static void main(String[] args) {		
		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {		
			Scanner tec = new Scanner(System.in);

			System.out.println("Introducir km: ");
			Integer km = tec.nextInt();
			tec.nextLine();

			s.beginTransaction();

			// Creamos la query (en este caso parametrizada)
			Query q = s.createQuery("SELECT e.ciclista_ganador.equipo.nombre, salida, llegada FROM Etapa e WHERE km > ?");
			// asignamos valor al parametro ?
			q.setInteger(0, km);
			// Guardamos en lista de aray de objetos los datos obtenidos Salida
			// y Llegada
			List<Object[]> listaEtapas = q.list();
			
			//Recorremos la lista que devuelve la select del HQL
			for (Object[] objects : listaEtapas) {
				System.out.println("Equipo ganador: "+ objects[0] + " -> Salida: " + objects[1] + " -> Llegada: " + objects[2]);
			}

			s.getTransaction().commit();

		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();
		} finally {
			factory.close();
		}
	}
}
