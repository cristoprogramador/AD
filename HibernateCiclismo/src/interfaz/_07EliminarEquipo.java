package interfaz;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Equipo;

public class _07EliminarEquipo {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);

		System.out.println("Equipo a eliminar: ");
		String equipoAEliminar = tec.nextLine();
		System.out.println("Equipo al que van los ciclistas: ");
		String equipoTraspaso = tec.nextLine();

		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {
			// Empezar Transacción
			s.beginTransaction();

			Equipo e = (Equipo) s.get(Equipo.class, equipoAEliminar);
			if (!(e == null)) {
				Equipo e2 = (Equipo) s.get(Equipo.class, equipoTraspaso);
				if(!(e2 == null)){
					for (Ciclista ciclista : e.getCiclistas()){
						ciclista.setEquipo(e2);
					}
					s.delete(e);
				}else{
					System.out.println("Equipo destino no existe");
				}
			}else{
				System.out.println("Equipo a eliminar no existe");
			}

			s.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();

		} finally {
			factory.close();

		}
	}

}
