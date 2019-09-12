package Interfaz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Dao.DaoSalida;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Salida;

public class Ejer4_Tema1_DAO2_ComprobarFechasEntradaSalida {

	private static ConexionJdbc conJdbc = null;

	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();
		Integer idsalida;
		String fechaEntrada;
		DaoSalida daoSalida;

		try {			
			Scanner tec = new Scanner(System.in);
			daoSalida = new DaoSalida();
			Salida salida = new Salida();
			
			System.out.println("Introduzca id salida");
			idsalida = tec.nextInt();
			tec.nextLine();
			salida = daoSalida.buscarPorId(idsalida);
			
			System.out.println("Introduzca fecha entrada (formato 2016-03-04 11:30:40):");
			fechaEntrada = tec.nextLine();

			//Transformamos el string fecha a localdatetime
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(fechaEntrada, formatter);

			salida.setFechaDevolucion(dateTime);
			
			daoSalida.actualizar(salida);
			
			System.out.println("Fecha correcta");
			
		} catch (BusinessException e) {
			System.out.println(e);
		} finally {
			conJdbc.desconectar();
			System.out.println("Desconectado de la Base de datos");
		}
	}
}
