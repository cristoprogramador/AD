package interfaz;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Ciclista;

public class _01b01DirectorDelCiclista {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		
		System.out.println("Dorsal: ");
		Integer dorsal = tec.nextInt();

		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {
			// Empezar Transacción
			s.beginTransaction();
			
			//Guardamos el cliclista con dorsal indicado (dorsal es clave primaria)
			Ciclista c = (Ciclista) s.get(Ciclista.class, dorsal);
			
			//Como equipo es un objeto dentro de ciclista, obtendremos el director
			//mediante el metodo getDirector del mismo sin generar un objeto Equipo
			System.out.println(c.getEquipo().getDirector());
			
			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();

		}
	}

}
