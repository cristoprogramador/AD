package interfaz;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Equipo;

public class ej_listarEquipos {
	public static void main(String[] args) {

		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();
		
		try {
		
			s.beginTransaction();

			Query q = s.createQuery("SELECT e FROM Equipo e");
			List<Equipo> lista = (List<Equipo>) q.list();
			for (Equipo eq : lista) {
				System.out.format("%-15s %s%n", eq.getNombre(), eq.getDirector());
			}
				s.getTransaction().commit();
				
		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();
			
		}finally{
			factory.close();
		}
		
	}

}
