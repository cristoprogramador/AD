package interfaz;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Equipo;

public class _02cCambiarDeEquipo {
	
	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		String nombreEquipo;
		Integer dorsalCiclista;

		// Pedir nombre de equipo al usuario
		System.out.println("Nombre del equipo: ");
		nombreEquipo = tec.nextLine();

		// Pedir nombre del nuevo director al usuario
		System.out.println("Dorsal del ciclista: ");
		dorsalCiclista = tec.nextInt();

		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {

			// Empezar Transacción
			s.beginTransaction();
			Equipo e = (Equipo) s.get(Equipo.class, nombreEquipo);
			Ciclista c = (Ciclista) s.get(Ciclista.class, dorsalCiclista);

			if (!(e == null) && !(c == null)) {
				// Modificación del valor en el objeto
				c.setEquipo(e);
				
			} else {
				System.out.println("El equipo o el ciclista no existe");
			}
			s.getTransaction().commit();

		} catch (ConstraintViolationException e) {

			s.getTransaction().rollback();

		} finally {

			factory.close();

		}
	}
}
