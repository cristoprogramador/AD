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

public class _12b1AnyadirCancionDiscoGrupoYCompañia {

	public static void main(String[] args) {
		//Abrir sesion y la transacion
		UtilesHibernate.openSession();
		
		/*
		 * i. Programa que cree un disco y una canción. La nueva canción pertenecerá
		 * al nuevo disco. El disco pertenecerá a un grupo y a una compañía ya
		 * existente. Se mostrarán (en la capa interfaz) los mensajes de error
		 * correspondientes en los siguientes casos:
		 * · La compañía a la que pertenece el disco no existe.
		 * · El grupo al que pertenece el disco no existe.
		 */
		
		//en hibernate en lugade de usar save usamos grabar y buscar en lugar de get		

		DaoCancion daoCancion = new DaoCancion();
		DaoDisco daoDisco = new DaoDisco();
		DaoGrupo daoGrupo = new DaoGrupo();
		DaoCompanyia daoCompanyia = new DaoCompanyia();
		
		try{
			//Buscamos una compañia
			Companyia com = daoCompanyia.buscarPorId(1);
			//Buscamos un grupo
			Grupo g = daoGrupo.buscarPorId(1);
			
			//Creamos la canción
			Cancion c = new Cancion();
			//aAñadimos los datos de la canción
			c.setTitulo("lalala");
			c.setDuracion(3.0);		
			//Guradamos la canción
			daoCancion.grabar(c);
			
			//Creamos un disco
			Disco d = new Disco();
			d.setCompanyia(com);
			d.setGrupo(g);
			//Guardamos el disco
			daoDisco.grabar(d);

			//Añadimos el disco a la lista de discos de la canción
			c.getDiscos().add(d);		
			daoCancion.grabar(c);
			
			System.out.println("Canción grabada");
		
		}catch(BusinessException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			UtilesHibernate.closeSession();
			UtilesHibernate.closeSessionFactory();;
		}	

	}

}
