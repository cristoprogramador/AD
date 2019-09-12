package interfaz;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import dao.DaoArtista;
import dao.DaoCancion;
import dao.DaoClub;
import dao.DaoCompanyia;
import dao.DaoDisco;
import dao.DaoGrupo;
import dao.DaoPertenece;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojos.Companyia;
import pojos.Disco;

public class Ejer2a {

	public static void main(String[] args) {
		UtilesHibernate.openSession();
		Scanner tec = new Scanner(System.in);

		System.out.println("Introduca nombre de la compañina: ");
		String nombre = tec.nextLine();
		System.out.println("Introduca direccion de la compañina: ");
		String direccion = tec.nextLine();
		System.out.println("Introduca telefono de la compañina: ");
		String telefono = tec.nextLine();

		DaoCompanyia daoCompanyia = new DaoCompanyia();
		DaoDisco daoDisco = new DaoDisco();

		try {

			Companyia c = new Companyia();
			c.setNombre(nombre);
			c.setDir(direccion);
			c.setTfno(telefono);
			daoCompanyia.grabar(c);

			if (daoCompanyia.buscarPorId(c.getCod()) == null)
				System.out.println("La compañnia no fue guardada correctamente");
			{
				List<Companyia> lista = daoCompanyia.buscarTodos();
				Companyia c2 = new Companyia();
				c2 = null;
				for (Companyia companyia : lista) {
					if (companyia.getNombre().equals("WEA")) {
						c2 = companyia;
						System.out.println("Compañaia Encontrada");
					}
				}
				if (c2 == null)
					System.out.println("La compañia no se ha encontrado");
				else {
					Set<Disco> listaDiscos = c2.getDiscos();
					for (Disco discos : listaDiscos) {
						discos.setCompanyia(c);
						daoDisco.actualizar(discos);
					}
					
					int idCompanyiaABorrar = c2.getCod();
					// Borramos y comprovamos
					daoCompanyia.borrar(c2);
					if (daoCompanyia.buscarPorId(idCompanyiaABorrar) != null)
						System.out.println("La compañnia no fue borrada correctamente");
					else
						System.out.println("Proceso terminado correctamente");
				}
			}

		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			UtilesHibernate.closeSession();
			UtilesHibernate.closeSessionFactory();
		}
	}

}
