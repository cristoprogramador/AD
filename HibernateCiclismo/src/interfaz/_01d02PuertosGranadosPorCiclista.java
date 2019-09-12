package interfaz;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Puerto;

public class _01d02PuertosGranadosPorCiclista {

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
			
			Ciclista c = (Ciclista) s.get(Ciclista.class, dorsal);
			for (Puerto p : c.getPuertos()) {
				System.out.println(p.getNompuerto() +  " Altura: " + p.getAltura() +
									" Categoria: " + p.getCategoria());
			}
			
			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();

		}
	}

}
