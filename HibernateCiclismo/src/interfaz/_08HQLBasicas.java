package interfaz;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Ciclista;

public class _08HQLBasicas {
	public static void main(String[] args) {
		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {
			Scanner tec = new Scanner(System.in);
			s.beginTransaction();

			// a) Ciclistas nacidos entre el 1/1/1979 y 31/12/1980
			/*
			Query q = s.createQuery("SELECT c FROM Ciclista c WHERE nacimiento between '1979-01-01' and '1980--12-31' ORDER BY nacimiento"); 
			List<Ciclista> lista = (List<Ciclista>) q.list(); 
			for (Ciclista ciclista : lista) { 
			System.out.println("Ciclista: " + ciclista.getNombre()); 
			}
			 */
			
			// b) Ciclistas cuyo nombre empiece o termine por A
			/*
			Query q = s.createQuery("SELECT c FROM Ciclista c WHERE nombre LIKE 'a%' or nombre LIKE '%a'"); 
			List<Ciclista> lista = (List<Ciclista>) q.list(); 
			for (Ciclista ciclista : lista) { 
				System.out.println("Ciclista: " + ciclista.getNombre());
			}*/
			
			//Preguntando al ususario la letra
			/*
			System.out.println("Introducir letra: ");
			String letra = "'"+tec.nextLine()+"%'";
			
			Query q = s.createQuery("SELECT c FROM Ciclista c WHERE nombre LIKE ?"); 
			q.setString(0,letra);
			System.out.println(letra);
			List<Ciclista> lista = (List<Ciclista>) q.list(); 
			for (Ciclista ciclista : lista) { 
				System.out.println("Ciclista: " + ciclista.getNombre());
			}*/
			
			
			// c) Número total de ciclistas
			// d) Número total de ciclistas del equipo Banesto
			/*
			Query q = s.createQuery("SELECT count(*) FROM Ciclista c WHERE equipo.nombre='Banesto'");
			//Query q = s.createQuery("SELECT e.ciclistas.size FROM Equipo e WHERE equipo.nombre='Banesto'");
			//Integer tot_ciclista_banesto = (Integer) q.uniqueResult();
			Long count = (Long) q.uniqueResult();
			System.out.println("Ciclistas de Banesto: " + count);
			*/
			
			// e) Fecha de nacimiento del ciclista más joven
			//Seleccionando solo Fecha			
			/*Query q = s.createQuery("SELECT max(nacimiento) FROM Ciclista c");
			Date masJoven = (Date) q.uniqueResult();
			System.out.println("Ciclista mas joven: " + masJoven);
			*/
			
			//Seleccionando el ciclista
			/*Query q = s.createQuery("SELECT c  FROM Ciclista c where nacimiento = (SELECT max(nacimiento) FROM Ciclista c)");
			Ciclista masJoven = (Ciclista) q.uniqueResult(); 
			System.out.println("Ciclista: " + masJoven.getNombre() + " Fecha: " + masJoven.getNacimiento()); 			
			*/
			
			// f) Tamaño medio de los nombres de los ciclistas
			// g) Ciclistas cuyo nombre tiene más de 15 caracteres
			
			// h) Ciclistas cuyo nombre tiene más tamaño que la media
			/*
			Query q = s.createQuery("SELECT c.nombre FROM Ciclista c WHERE length(c.nombre)>(SELECT avg(length(c.nombre)) FROM Ciclista c)");
			List<String> nombres = (List<String>) q.list();
			for (String string : nombres) {
				System.out.println("Nombre: " + string);
			}*/
			
			// i) Ciclistas ordenados por fecha de nacimiento de menor a mayor.
			// A igual fecha, ordenados por nombre de mayor a menor.

			s.getTransaction().commit();

		} catch (ConstraintViolationException e) {
			s.getTransaction().rollback();
		} finally {
			factory.close();
		}
	}

}
