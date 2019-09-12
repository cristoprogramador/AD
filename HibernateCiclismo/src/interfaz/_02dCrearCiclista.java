package interfaz;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import hibernate.UtilesHibernate;
import pojos.Ciclista;
import pojos.Equipo;

public class _02dCrearCiclista {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		Integer dorsalNuevoCiclista;
		String nombreNuevoCiclista;
		String nacimientoNuevoCiclista;
		String equipoDelNuevoCiclista;

		// Pedir nombre de equipo al usuario
		System.out.println("Dorsal del nuevo ciclista: ");
		dorsalNuevoCiclista = tec.nextInt();
		tec.nextLine();
		// Pedir nombre del nuevo director al usuario
		System.out.println("Nombre del nuevo ciclista: ");
		nombreNuevoCiclista = tec.nextLine();
		// Pedir fecha de nacimiento
		System.out.println("Fecha de nacimiento del nuevo ciclista  (formato dd/MM/yyyy): ");
		nacimientoNuevoCiclista = tec.nextLine();

		// Transformamos el string fecha a localdatetime
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateFechaNacimiento = null;
		try {
			dateFechaNacimiento = sf.parse(nacimientoNuevoCiclista);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Pedimos el equipo
		System.out.println("Equipo del nuevo ciclista: ");
		equipoDelNuevoCiclista = tec.nextLine();

		// Conexión de hibernate
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		Session s = factory.getCurrentSession();

		try {

			// Empezar Transacción
			s.beginTransaction();

			// Comoprobamos si existe el ciclista
			Ciclista c = (Ciclista) s.get(Ciclista.class, dorsalNuevoCiclista);
			if (c == null) {
				// Si el ciclista no existe
				// Comprobamos si existe el Equipo
				Equipo e = (Equipo) s.get(Equipo.class, equipoDelNuevoCiclista);
				//Si existe el equipo
				if (e != null) {			
					// Creamos el ciclista
					c = new Ciclista(dorsalNuevoCiclista, nombreNuevoCiclista, dateFechaNacimiento);
					//Le añadimos el equipo
					c.setEquipo(e);
					//guardamos el ciclista
					s.save(c);

				}else{
					System.out.println("El equipo no existe");
				}				
			} else {
				System.out.println("El Ciclista ya existe");
			}
			s.getTransaction().commit();

		} catch (ConstraintViolationException e) {

			s.getTransaction().rollback();

		} finally {

			factory.close();

		}

	}

}
