package interfaz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Equipo;

public class ej_insertaEquipo {
	
	public static void main(String[] args) {
		
		//conexion a la bd
		
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();
		try{
			s.beginTransaction();
			
			Equipo equipo = new Equipo();
			equipo.setDirector("director1");
			equipo.setNombre("equipo_1");
			
			s.save(equipo);
			s.getTransaction().commit();
			
		}catch(ConstraintViolationException e){
			
			s.getTransaction().rollback();
			
		}finally{
			factory.close();
		}
		
	
	}

}
