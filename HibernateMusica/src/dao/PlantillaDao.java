package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Artista;

public class PlantillaDao extends DaoGenericoHibernate<Pojo, Integer> {

	/**
	 * Busca los departamentos por nombre
	 * @param nombre String Nombre del departamento
	 * @return Departamento encontrado Null si está duplicado o no existe
	 * @throws BusinessException
	 */
	public Artista buscarPorNombre (String nombre) throws BusinessException {		
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		
		Artista artista;
		List<Artista> listaArtistas;

		try {
			s.beginTransaction();
			//El departamento que coincida con el nombre
			String hql = "Select p from Departamento p where p.nombre = :nombre";
			//Añadimos valor a la etiqueta y guardamos el resultado
			listaArtistas = (List<Artista>) s.createQuery(hql).setString("nombre", nombre).list();
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
