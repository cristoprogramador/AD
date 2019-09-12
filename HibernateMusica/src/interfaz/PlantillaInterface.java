package interfaz;

import dao.DaoArtista;
import dao.DaoCancion;
import dao.DaoClub;
import dao.DaoCompanyia;
import dao.DaoDisco;
import dao.DaoGrupo;
import dao.DaoPertenece;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;

public class PlantillaInterface {

	public static void main(String[] args) {
		UtilesHibernate.openSession();

		DaoArtista daoArtista = new DaoArtista();
		DaoCancion daoCancion = new DaoCancion();
		DaoClub daoClub = new DaoClub();
		DaoCompanyia daoCompanyia = new DaoCompanyia();
		DaoDisco daoDisco = new DaoDisco();
		DaoGrupo daoGrupo = new DaoGrupo();
		DaoPertenece daoPertenece = new DaoPertenece();

		try {
			
			System.out.println("Proceso terminado correctamente");
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			UtilesHibernate.closeSession();
			UtilesHibernate.closeSessionFactory();			
		}

	}

}
