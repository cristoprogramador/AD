package interfaz;

import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthStyle;

import dao.DaoArticulo;
import dao.DaoSalida;
import dao.DaoUsuario;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Articulo;
import pojos.Salida;
import pojos.Usuario;

public class Ex2_Ejercicio3 {
	private static ConexionJdbc conJdbc = null;

	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();

		try {
			DaoSalida daoSalida = new DaoSalida();
			DaoUsuario daoUsuario = new DaoUsuario();
			DaoArticulo daoArticulo = new DaoArticulo();
			Salida salida = new Salida();
			Articulo articulo = new Articulo();
			Usuario usuario = new Usuario();
			Scanner tec = new Scanner(System.in);
			
			System.out.println("Introduzca id articulo");
			int idArticulo = tec.nextInt();	
			System.out.println("Introduzca id usuario");
			int idUsuario = tec.nextInt();
			
			salida.setArticulo(idArticulo);
			salida.setUsuario(idUsuario);
			salida.setFechaSalida(LocalDateTime.now());
			
			//comprobamos el departamento del id usuario
			usuario = daoUsuario.buscarPorId(idUsuario);
			int idDepartamentoUsuario = usuario.getDepartamento();
			
			//comprobamos el departamento del articulo
			articulo = daoArticulo.buscarPorId(idArticulo);
			int idDepartamentoArticulo = articulo.getDepartamento();
			
			if (idDepartamentoUsuario == idDepartamentoArticulo){
				daoSalida.grabar(salida);
				System.out.println("Salida grabada");
			}else 
				System.out.println("Salida no grabada el articulo no pertenece al tipo de usuario");
				
			
			daoSalida.grabar(salida);
		} catch (BusinessException e) {
			System.out.println("No se pudo hacer la petición");
		} finally {
			conJdbc.desconectar();
			System.out.println("Desconectado de la Base de datos");
		}
	}
}
