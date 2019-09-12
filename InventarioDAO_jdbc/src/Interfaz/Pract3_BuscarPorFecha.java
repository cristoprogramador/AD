package Interfaz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dao.DaoSalida;
import jdbc.ConexionJdbc;
import pojos.Salida;

public class Pract3_BuscarPorFecha {
/**
 * Buscar las salidas a partir de una fecha que da el usuario
 * @param args
 */
	public static void main(String[] args) {
		ConexionJdbc conexionJdbc = null;
		List<Salida> result = new ArrayList<>();
		String fecha; 

		
		try {
			//conectamos con la BD
			conexionJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conexionJdbc.conectar();
			Scanner tec = new Scanner(System.in);
			
			System.out.println("Introduzca la fecha y hora (formato 2016-03-04 11:30:40): ");
			fecha = tec.nextLine();
			
			//Transformamos la fecha a localdatetime
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);

			DaoSalida daoSalida = new DaoSalida();
			//Recibimos el listado de daos encontrados
			result = daoSalida.consultar_por_fecha(dateTime);
			
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
