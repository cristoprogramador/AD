package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Artista;

public class DaoArtista extends DaoGenericoHibernate<Artista, String>{
	
	public static List<Object[]> grupoYClubesDeArtistasBateria() throws BusinessException {
		// cancion a discos n:n
		List<Object[]> canciones;
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		// lo mismo que en el dao generico
		try {
			s.beginTransaction();
			/*
			 *De cada artista que actúa como batería, grupo con el que lo hace y clubes
			 *en los que toca.
			 */
			String hql = "Select a.nombre, p.grupo.nombre, c.nombre from Artista a join a.perteneces p join p.grupo g join g.clubs c where p.funcion = 'batería'";
			
			canciones = (List<Object[]>) s.createQuery(hql).list();

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
