package interfaz;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Equipo;

public class _06EliminarEquipoyCiclistas {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		
		System.out.println("Equipo a eliminar: ");
		String equipo = tec.nextLine();
		
		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {
			// Empezar Transacción
			s.beginTransaction();
		
			Equipo e = (Equipo) s.get(Equipo.class, equipo);
			s.delete(e);
			
			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();

		}
	}

}
