package interfaz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.DaoArticulo;
import dao.DaoSalida;
import dao.DaoUsuario;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Articulo;
import pojos.Salida;
import pojos.Usuario;

public class Ex2_Ejercicio2 {
	private static ConexionJdbc conJdbc = null;

	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();

		try {
			Scanner tec = new Scanner(System.in);
			Salida salida = new Salida();
			Usuario usuario = new Usuario();
			Articulo articulo = new Articulo();
			DaoSalida daoSalida = new DaoSalida();
			DaoArticulo daoArticulo = new DaoArticulo();
			DaoUsuario daoUsuario = new DaoUsuario();
			List<Salida> listaSalida = new ArrayList<>();

			System.out.println("Introduzca id articulo");
			int idArticulo = tec.nextInt();
			System.out.println("Introduzca id usuario");
			int idUsuario = tec.nextInt();
			tec.nextLine();
			System.out.println("Introduzca fecha salida (formato 2016-03-04 11:30:40):");
			String fechaSalida = tec.nextLine();

			// Transformamos la fecha a localdatetime
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(fechaSalida, formatter);

			salida.setArticulo(idArticulo);
			salida.setUsuario(idUsuario);
			salida.setFechaSalida(dateTime);
			salida.setFechaDevolucion(null);

			// Comprobamos que el idarticulo y el id usuario exiten
			articulo = daoArticulo.buscarPorId(idArticulo);
			usuario = daoUsuario.buscarPorId(idUsuario);

			if (articulo != null && usuario != null) {
				// Ejecutamos
				if (dateTime.compareTo(LocalDateTime.now()) <= 0) {
					// Ejecutamos
					listaSalida = daoSalida.buscarTodos();
					boolean articuloOk = false;
					boolean devueltoOk = false;

					for (Salida salida2 : listaSalida) {
						if (salida2.getArticulo() == idArticulo) {
							if (salida2.getFechaSalida() == null){
								devueltoOk = true;
								articuloOk = true;
							}				
						}else articuloOk = true;
					}

					if (articuloOk && devueltoOk) {
						daoSalida.grabar(salida);
						System.out.println("Articulo grabado con exito");
					} else {
						System.out.println("Articulo ya perestado y no devuelto");
						System.out.println("Articulo no grabado con exito");
					}
				} else {
					System.out.println("La fecha no puede ser mayor a la actula");
					System.out.println("Articulo no grabado con exito");
				}
			} else {
				System.out.println("Usuario o Articulo no existen");
				System.out.println("Articulo no grabado con exito");
			}

		} catch (BusinessException e) {
			System.out.println("No se pudo hacer petición");
		} finally {
			conJdbc.desconectar();
			System.out.println("Desconectado de la Base de datos");
		}
	}
}
