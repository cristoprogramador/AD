package dao;

import java.util.Date;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Disco;
import pojo.Grupo;

public class DaoDisco extends DaoGenericoHibernate<Disco, Integer> {

	public static boolean grabar_disco(Disco disco) throws BusinessException {
		boolean correcto = true;
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		try {
			s.beginTransaction();
			Grupo grupo = disco.getGrupo();
			Date fecha = null;
			int anyo = fecha.getYear();
			Set<Disco> listaDiscos = grupo.getDiscos();
			Set<Disco> cantidad = null;
			for (Disco disco2 : listaDiscos) {

				if (disco2.getFecha().getYear() == anyo) {
					cantidad.add(disco2);
				}
			}
			if (cantidad.size() <= 1) {
				s.save(disco);
			} else {
				correcto = false;
			}

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
		return correcto;
	}
}
