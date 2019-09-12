package interfaz;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Equipo;

public class _02aCrearEquipo {

	public static void main(String[] args) {
		Scanner tec = new Scanner (System.in);
		
		System.out.println("Nombre del equipo: ");
		String equipo = tec.nextLine();
		
		System.out.println("Nombre del director: ");
		String director  = tec.nextLine();
		
		// TODO Auto-generated method stub
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();
		try{
			s.beginTransaction();
			
			//Creamos instancia equipo
			Equipo e = new Equipo();
			//añadimos atributos Nombre y Director
			e.setNombre(equipo);
			e.setDirector(director);
			//Guardamos
			s.save(e);
			
			s.getTransaction().commit();
			
		}catch(ConstraintViolationException e){			
			s.getTransaction().rollback();
					}finally{
			factory.close();
		}
		
	}

}
