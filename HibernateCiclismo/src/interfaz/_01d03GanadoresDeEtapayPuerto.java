package interfaz;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Etapa;
import pojos.Puerto;

public class _01d03GanadoresDeEtapayPuerto {

	public static void main(String[] args) {

		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {
			// Empezar Transacción
			s.beginTransaction();
			// Recorremos los ciclistas
			List<Ciclista> ciclistas = (List<Ciclista>) s.createQuery("select c from Ciclista c").list();
			for (Ciclista c : ciclistas) {
				// Etapas ganadas por el ciclista
				if (c.getEtapas_ganadas().size() > 0) {
					System.out.println(c.getNombre() + " Etapa/s: ");
					for (Etapa e : c.getEtapas_ganadas()) {
						System.out.print(e.getSalida() + " Puerto/s: ");
						for (Puerto p : e.getPuertos()) {
							System.out.print(p.getNompuerto() + " ");
						}
						System.out.println();
					}
					System.out.println();
				}
			}

			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();

		}
	}

}
