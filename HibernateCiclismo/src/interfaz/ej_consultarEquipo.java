package interfaz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Equipo;
import pojos.Puerto;

public class ej_consultarEquipo {
	
	public static void main(String[] args) {
		
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();
		try{
			s.beginTransaction();
			
//			Equipo e = (Equipo) s.get(Equipo.class, "Kelme"); //buscar por i
//			//En e tenemos ahora todos los equipos
//			System.out.println("El director del equipo: "+ e.getDirector());
			
			Puerto p = (Puerto) s.get(Puerto.class, "Arcalis");
			System.out.println("El cnombre " + p.getCiclista());
//			
//			for(Ciclista c : e.getCiclistas()){
//				System.out.println("El ciclista: "+ c.getNombre());
//			}
			
			s.getTransaction().commit();
			
			
			
		}catch (ConstraintViolationException e) {
			s.getTransaction().rollback();
			
		}finally{
			factory.close();
		}
		
	}

}
