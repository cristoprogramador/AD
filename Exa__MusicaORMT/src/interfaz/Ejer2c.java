package interfaz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dao.DaoArtista;
import dao.DaoCancion;
import dao.DaoClub;
import dao.DaoCompanyia;
import dao.DaoDisco;
import dao.DaoGrupo;
import dao.DaoPertenece;
import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import pojos.Disco;
import pojos.Grupo;

public class Ejer2c {

	public static void main(String[] args) {
		UtilesHibernate.openSession();
		Scanner tec = new Scanner(System.in);

		System.out.println("Introduca nombre del Disco: ");
		String nombre = tec.nextLine();
		System.out.println("Introduca direccion del Disco 10/2/2015: ");
		String fecha = tec.nextLine();
		System.out.println("Introduca grupo del Disco: ");
		String grupo = tec.nextLine();
		Date fechaDisco =null;
		try {
			fechaDisco = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		DaoArtista daoArtista = new DaoArtista();
		DaoCancion daoCancion = new DaoCancion();
		DaoClub daoClub = new DaoClub();
		DaoCompanyia daoCompanyia = new DaoCompanyia();
		DaoDisco daoDisco = new DaoDisco();
		DaoGrupo daoGrupo = new DaoGrupo();

		try {
			
			//Creamos un disco
			Disco d = new Disco();
			d.setNombre(nombre);
			d.setFecha(fechaDisco);
			Grupo g = daoGrupo.buscarPorNombre(grupo);
			d.setGrupo(g);
			//Guardamos el disco
			if (DaoDisco.grabar_disco(d))System.out.println("Guardado correctamente");
			else System.out.println("El grupo tiene mas de dos discos ese año");

			
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
