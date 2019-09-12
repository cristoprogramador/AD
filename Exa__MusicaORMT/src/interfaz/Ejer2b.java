package interfaz;

import java.util.List;
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
import pojos.Grupo;

public class Ejer2b {

	public static void main(String[] args) {
		UtilesHibernate.openSession();
		Scanner tec = new Scanner(System.in);
		
		
		System.out.println("Introduca nombre de pais: ");
		String nombre = tec.nextLine();		
		
		try {

			List<Object[]> lista = DaoGrupo.gruposArtistasYFuncionesPorPais(nombre);

			for (Object[] objects : lista) {
				System.out.println("Nombre grupo: " +objects[0] +" --- Nombre Artista: " + objects[1] +
						" --- Funcion: " + objects[2]);
			}
			
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
