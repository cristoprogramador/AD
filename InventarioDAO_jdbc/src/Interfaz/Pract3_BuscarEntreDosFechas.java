package Interfaz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dao.DaoSalida;
import jdbc.ConexionJdbc;
import pojos.Salida;

public class Pract3_BuscarEntreDosFechas {
/**
 * Buscar las salidas a partir de una fecha que da el usuario
 * @param args
 */
	public static void main(String[] args) {
		ConexionJdbc conexionJdbc = null;
		List<Salida> result = new ArrayList<>();
		String fechaInicial, fechaFinal; 

		
		try {
			//conectamos con la BD
			conexionJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conexionJdbc.conectar();
			Scanner tec = new Scanner(System.in);
			
			System.out.println("Introduzca la fecha y hora inicial (formato 2016-03-04 11:30:40): ");
			fechaInicial = tec.nextLine();			
			System.out.println("Introduzca la fecha y hora final (formato 2016-03-04 11:30:40): ");
			fechaFinal = tec.nextLine();
			
			//Transformamos la fecha a localdatetime
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTimeInicial = LocalDateTime.parse(fechaInicial, formatter);
			LocalDateTime dateTimeFinal = LocalDateTime.parse(fechaFinal, formatter);

			DaoSalida daoSalida = new DaoSalida();
			//Recibimos el listado de daos encontrados
			result = daoSalida.consultaEntreDosFechas(dateTimeInicial, dateTimeFinal);
			
			//Imprimimos los daos
			for (Salida s : result) {
				System.out.println(s.getArticulo()+" "+s.getFechaSalida().format(formatter));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pongase en contacto con el administrador");
		} finally {
			conexionJdbc.desconectar();
		}

	}
	
}
