package dao;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Departamento;
import pojo.Empleado;

public class DaoEmpleado extends DaoGenericoHibernate<Empleado, Integer> {

	public Departamento getDepartamentoDirigido (Integer id) throws BusinessException {		
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		
		Departamento departamento;

		try {
			s.beginTransaction();
			//El departamento que coincida con el nombre
			String hql = "Select d from Departamento d join d.empleadosForDirige e where e.nss = :id";
			//Añadimos valor a la etiqueta y guardamos el resultado
			departamento = (Departamento) s.createQuery(hql).setInteger("id", id).uniqueResult();
			//Realizamos la transacción
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
		return departamento;
	}
}
