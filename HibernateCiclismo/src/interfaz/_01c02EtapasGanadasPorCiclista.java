package interfaz;

import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Etapa;

public class _01c02EtapasGanadasPorCiclista {

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
			Set<Etapa> eg = c.getEtapas_ganadas();
			
			for (Etapa etapa : eg) {
				System.out.println("Salida: " + etapa.getSalida() + 
									", Llegada: " + etapa.getLlegada());
			}
			
			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();

		}

	}

}
