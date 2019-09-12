package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Companyia;

public class DaoCompanyia extends DaoGenericoHibernate<Companyia, Integer>{

	public static List<Object[]> companyiaYGrupos() throws BusinessException {
		// cancion a discos n:n
		List<Object[]> canciones;
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		// lo mismo que en el dao generico
		try {
			s.beginTransaction();
			/*
			 *De cada compañía, nombre de la compañía y nombre de los grupos que
			 *guardan alguna relación con ella.
			 */
			String hql = "Select c.nombre, g.nombre from Companyia c join c.discos d join d.grupo g group by g.nombre";
			
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
	
	public static List<Object[]> guposArtistasYFunciones() throws BusinessException {
		// cancion a discos n:n
		List<Object[]> canciones;
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		// lo mismo que en el dao generico
		try {
			s.beginTransaction();
			/*
			 *De cada compañía, nombre de la compañía y nombre de los artistas que
			 *guardan alguna relación con ella.
			 */
			String hql = "Select c.nombre, a.nombre from Companyia c join c.discos d join d.grupo g join g.perteneces p join p.artista a group by a.nombre order by c.nombre";
			
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
