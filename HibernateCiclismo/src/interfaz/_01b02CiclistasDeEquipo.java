package interfaz;

import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Equipo;

public class _01b02CiclistasDeEquipo {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);

		System.out.println("Nombre de equipo: ");
		String nombreEquipo = tec.nextLine();

		// Conexi�n de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {
			// Empezar Transacci�n
			s.beginTransaction();

			Equipo e = (Equipo) s.get(Equipo.class, nombreEquipo);
			if (!(e == null)) {
				Set<Ciclista> c = e.getCiclistas();

				for (Ciclista ciclista : c) {
					System.out.println(ciclista.getNombre());
				}
			} else {
				System.out.println("Nombre de equipo no existe");
			}
			
			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();
		}
	}

}
