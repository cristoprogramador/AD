package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Departamento;
import pojo.Empleado;
import pojo.Proyecto;

public class DaoProyecto extends DaoGenericoHibernate<Proyecto, Integer> {

	public Proyecto buscarPorNombre (String nombre) throws BusinessException {		
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		
		Proyecto proyecto;
		List<Proyecto> listaDepartamentos;

		try {
			s.beginTransaction();
			//El departamento que coincida con el nombre
			String hql = "Select p from Proyecto p where p.nombre = :nombre";
			//Añadimos valor a la etiqueta y guardamos el resultado
			listaDepartamentos = (List<Proyecto>) s.createQuery(hql).setString("nombre", nombre).list();
			//Realizamos la transacción
			s.getTransaction().commit();
			
			if (listaDepartamentos.size() != 1){
				proyecto = null;
			} else {
				proyecto = listaDepartamentos.get(0);
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
		return proyecto;
	}
	
	/**
	 * Devuelve la lista de usuarios del proyecto indicado
	 * @param id Integer Identificador del proyecto
	 * @return List<Empleado> Lista de empleados del proyecto especificado
	 * @throws BusinessException
	 */
	public List<Empleado> listarEmpleados (Integer id) throws BusinessException {		
		Session s = UtilesHibernate.getSessionFactory().getCurrentSession();
		
		List<Empleado> listaDepartamentos;

		try {
			s.beginTransaction();
			//Los empleados del departamento que están el el proyecto id
			//Select * from empleado join departamento d, proyecto p 
			//where d.NumDep = p.departamento and p.num = 1;
			String hql = "Select e from Empleado e JOIN e.departamentoByDepartamento d join d.proyectos p"+
						" where d.numDep = p.departamento and p.num = :id";

			//Añadimos valor a la etiqueta y guardamos el resultado
			listaDepartamentos = (List<Empleado>) s.createQuery(hql).setInteger("id", id).list();
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
		return listaDepartamentos;
	}
}
