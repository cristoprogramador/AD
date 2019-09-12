package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Cancion;

public class DaoCancion extends DaoGenericoHibernate<Cancion, Integer>{
	/*
	 * i.De las canciones que duran más de 3 minutos, mostra r el título, la
	 * duración, el nombre del disco en que se encuentran y la fecha en la que
	 * se lanzó el disco.
	 */
	
	public static List<Cancion> cancionesPorDuracion (Double duracion) throws BusinessException {
		// cancion a discos n:n
		List<Cancion> canciones;
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();

		try {
			s.beginTransaction();
			String hql = "Select c from Cancion c join c.discos d where c.duracion > :dura";

			// una cancion puede estar en varios discos el nombre de disco puede variar, 
			// en vez de c.disco.nombre hacemos con un join, a las listas se accede con join

			canciones = (List<Cancion>) s.createQuery(hql).setDouble("dura", duracion).list();

			s.getTransaction().commit();
		} catch (ConstraintViolationException cve) {
			try {
				s.getTransaction().rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			throw new BusinessException(cve);
		} catch (Exception ex) {
			try {
				s.getTransaction().rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			throw ex;
		}
		return canciones;
	}
	
	public static List<Object[]> listar_canciones(Double duracion) throws BusinessException {
		// cancion a discos n:n
		List<Object[]> canciones;
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		// lo mismo que en el dao generico
		try {
			s.beginTransaction();
			
			String hql = "Select c.duracion, c.titulo, d.nombre from Cancion "
					+ "c join c.discos d where c.duracion >:dura";

			canciones = (List<Object[]>) s.createQuery(hql).setDouble("dura", duracion).list();

			s.getTransaction().commit();
		} catch (ConstraintViolationException cve) {
			try {
				s.getTransaction().rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			throw new BusinessException(cve);
		} catch (Exception ex) {
			try {
				s.getTransaction().rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			throw ex;
		}
		return canciones;
	}

	/*
	 * ii.En la consulta anterior hacer que solo se muestren canciones que están
	 * en discos que se lanzaron a partir de 1982 (puedes probar con otras
	 * fechas).
	 */
	public static List<Object[]> listar_cancionesAPartirDeFecha(Double duracion) throws BusinessException {
		// cancion a discos n:n
		List<Object[]> canciones;
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		// lo mismo que en el dao generico
		try {
			s.beginTransaction();
			/*
			 * De las canciones que duran mas de 3 min, mostra la duracion, el
			 * nombre disco y la fecha que se lanzo
			 */
			String hql = "Select c.duracion, d.fecha, c.titulo, d.nombre from Cancion "
					+ "c join c.discos d where c.duracion =:dura and fecha > 1985";
			
			canciones = (List<Object[]>) s.createQuery(hql).setDouble("dura", duracion).list();

			s.getTransaction().commit();
		} catch (ConstraintViolationException cve) {
			try {
				s.getTransaction().rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			throw new BusinessException(cve);
		} catch (Exception ex) {
			try {
				s.getTransaction().rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			throw ex;
		}
		return canciones;
	}


}
