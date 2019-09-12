package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Artista;
import pojo.Grupo;

public class DaoGrupo extends DaoGenericoHibernate<Grupo, Integer>{
	
	public static List<Object[]> guposArtistasYFunciones() throws BusinessException {
		// cancion a discos n:n
		List<Object[]> canciones;
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		// lo mismo que en el dao generico
		try {
			s.beginTransaction();
			/*
			 * De cada grupo, el nombre del grupo, nombre de los artistas que lo
			 * integran y función que desempeña el artista dentro del grupo.
			 */
			String hql = "Select g.nombre, a.nombre, p.funcion from Grupo g join g.perteneces p join p.artista a";
			
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

	public Grupo buscarPorNombre (String nombre) throws BusinessException {		
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		
		Grupo artista;
		List<Grupo> listaArtistas;

		try {
			s.beginTransaction();
			//El departamento que coincida con el nombre
			String hql = "Select g from Grupo g where g.nombre = :nombre";
			//Añadimos valor a la etiqueta y guardamos el resultado
			listaArtistas = (List<Grupo>) s.createQuery(hql).setString("nombre", nombre).list();
			//Realizamos la transacción
			s.getTransaction().commit();
			
			if (listaArtistas.size() != 1){
				artista = null;
			} else {
				artista = listaArtistas.get(0);
			}
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
		return artista;
	}
}
