package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Departamento;

public class PlantillaDao extends DaoGenericoHibernate<Pojo, Integer> {

	/**
	 * Busca los departamentos por nombre
	 * @param nombre String Nombre del departamento
	 * @return Departamento encontrado Null si está duplicado o no existe
	 * @throws BusinessException
	 */
	public Departamento buscarPorNombre (String nombre) throws BusinessException {		
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		
		Departamento departamento;
		List<Departamento> listaDepartamentos;

		try {
			s.beginTransaction();
			//El departamento que coincida con el nombre
			String hql = "Select p from Departamento p where p.nombre = :nombre";
			//Añadimos valor a la etiqueta y guardamos el resultado
			listaDepartamentos = (List<Departamento>) s.createQuery(hql).setString("nombre", nombre).list();
			//Realizamos la transacción
			s.getTransaction().commit();
			
			if (listaDepartamentos.size() != 1){
				departamento = null;
			} else {
				departamento = listaDepartamentos.get(0);
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
		return departamento;
	}
}
