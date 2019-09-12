package interfaz;

import dao.DaoCancion;
import dao.DaoCompanyia;
import dao.DaoDisco;
import dao.DaoGrupo;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojo.Cancion;
import pojo.Companyia;
import pojo.Disco;
import pojo.Grupo;

public class _12b1AnyadirCancionDiscoGrupoYCompa�ia {

	public static void main(String[] args) {
		//Abrir sesion y la transacion
		UtilesHibernate.openSession();
		
		/*
		 * i. Programa que cree un disco y una canci�n. La nueva canci�n pertenecer�
		 * al nuevo disco. El disco pertenecer� a un grupo y a una compa��a ya
		 * existente. Se mostrar�n (en la capa interfaz) los mensajes de error
		 * correspondientes en los siguientes casos:
		 * � La compa��a a la que pertenece el disco no existe.
		 * � El grupo al que pertenece el disco no existe.
		 */
		
		//en hibernate en lugade de usar save usamos grabar y buscar en lugar de get		

		DaoCancion daoCancion = new DaoCancion();
		DaoDisco daoDisco = new DaoDisco();
		DaoGrupo daoGrupo = new DaoGrupo();
		DaoCompanyia daoCompanyia = new DaoCompanyia();
		
		try{
			//Buscamos una compa�ia
			Companyia com = daoCompanyia.buscarPorId(1);
			//Buscamos un grupo
			Grupo g = daoGrupo.buscarPorId(1);
			
			//Creamos la canci�n
			Cancion c = new Cancion();
			//aA�adimos los datos de la canci�n
			c.setTitulo("lalala");
			c.setDuracion(3.0);		
			//Guradamos la canci�n
			daoCancion.grabar(c);
			
			//Creamos un disco
			Disco d = new Disco();
			d.setCompanyia(com);
			d.setGrupo(g);
			//Guardamos el disco
			daoDisco.grabar(d);

			//A�adimos el disco a la lista de discos de la canci�n
			c.getDiscos().add(d);		
			daoCancion.grabar(c);
			
			System.out.println("Canci�n grabada");
		
		}catch(BusinessException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			UtilesHibernate.closeSession();
			UtilesHibernate.closeSessionFactory();;
		}	

	}

}
