package interfaz;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthStyle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Equipo;

public class _02bModificarEquipo {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		String nombreEquipo;
		String nombreNuevoDirector;

		// Pedir nombre de equipo al usuario
		System.out.println("Nombre del equipo: ");
		nombreEquipo = tec.nextLine();

		// Pedir nombre del nuevo director al usuario
		System.out.println("Nombre del nuevo dierector: ");
		nombreNuevoDirector = tec.nextLine();

		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {

			// Empezar Transacción
			s.beginTransaction();
			Equipo e = (Equipo) s.get(Equipo.class, nombreEquipo);

			if (e != null) {
				// Modificación del valor en el objeto
				e.setDirector(nombreNuevoDirector);
			} else {
				System.out.println("El equipo no existe");
			}
			s.getTransaction().commit();

		} catch (ConstraintViolationException e) {

			s.getTransaction().rollback();

		} finally {

			factory.close();

		}
	}
}
