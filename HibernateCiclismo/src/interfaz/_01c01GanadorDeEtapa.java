package interfaz;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Etapa;

public class _01c01GanadorDeEtapa {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		
		System.out.println("Numero de Etapa: ");
		Integer netapa = tec.nextInt();

		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {
			// Empezar Transacción
			s.beginTransaction();
			
			Etapa e = (Etapa) s.get(Etapa.class, netapa);
			System.out.println(e.getCiclista_ganador().getNombre());					
			
			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();

		}
	}

}
