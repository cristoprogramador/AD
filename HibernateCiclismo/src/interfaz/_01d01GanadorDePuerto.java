package interfaz;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Puerto;

public class _01d01GanadorDePuerto {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		
		System.out.println("Nombre de puerto: ");
		String nompuerto = tec.nextLine();

		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {
			// Empezar Transacción
			s.beginTransaction();
			
			Puerto p = (Puerto) s.get(Puerto.class, nompuerto);
			System.out.println(p.getCiclista().getNombre());
			
			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();

		}
	}

}
