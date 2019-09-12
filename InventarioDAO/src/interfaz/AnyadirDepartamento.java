package interfaz;

import java.util.Scanner;

import dao.DaoDepartamento;
import jdbc.ConexionJdbc;
import pojos.Departamento;

public class AnyadirDepartamento {

	public static void main(String[] args) {
		ConexionJdbc conexionJdbc=null;
		String nombre;
		
		try {
			conexionJdbc=new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conexionJdbc.conectar();
			Scanner tec = new Scanner(System.in);
			System.out.print("Nombre de nuevo departamento: ");
			nombre = tec.nextLine();
			
			/* FORMAS DE CREAR EL POJO DEPARTAMENTO */
			/* 1ª FORMA */
			/*Departamento d = new Departamento(null,nombre);*/
			
			/* 2ª FORMA */
			Departamento d = new Departamento();
			d.setNombre(nombre);		
			
			DaoDepartamento daoDepartamento = new DaoDepartamento();
			daoDepartamento.grabar(d);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pongase en contacto con el administrador");
		}finally {
			conexionJdbc.desconectar();
		}

	}

}
