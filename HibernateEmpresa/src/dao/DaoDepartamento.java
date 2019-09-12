package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Departamento;
import pojo.Empleado;
import pojo.Proyecto;

public class DaoDepartamento extends DaoGenericoHibernate<Departamento, Integer> {

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
	
	public List<Empleado> listarEmpleados (Integer id) throws BusinessException {		
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		
		Departamento departamento;
		List<Empleado> listaEmpleados;

		try {
			s.beginTransaction();
			//Empleados del departamento
			String hql = "Select e from Empleado e where e.departamentoByDepartamento = :id";
			//Añadimos valor a la etiqueta y guardamos el resultado
			listaEmpleados = (List<Empleado>) s.createQuery(hql).setInteger("id", id).list();
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
		return listaEmpleados;
	}
	
	public Empleado getDirector (Integer id) throws BusinessException {		
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		
		Empleado empleadoDirector;

		try {
			s.beginTransaction();
			//El departamento que coincida con el nombre
			String hql = "Select e from Empleado e where e.departamentoByDirige = :id";
			//Añadimos valor a la etiqueta y guardamos el resultado
			empleadoDirector = (Empleado) s.createQuery(hql).setInteger("id", id).uniqueResult();
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
		return empleadoDirector;
	}

	public List<Proyecto> listarProyectos (Integer id) throws BusinessException {		
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();

		List<Proyecto> listaProyectos;

		try {
			s.beginTransaction();
			//Empleados del departamento
			String hql = "Select p from Proyecto p where p.departamento = :id";
			//Añadimos valor a la etiqueta y guardamos el resultado
			listaProyectos = (List<Proyecto>) s.createQuery(hql).setInteger("id", id).list();
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
		return listaProyectos;
	}
}
