package interfaz;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import hibernate.UtilesHibernate;

public class _00Pantilla {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		
		System.out.println("");

		// Conexi�n de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {
			// Empezar Transacci�n
			s.beginTransaction();
		
			
			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();

		}
	}
}
