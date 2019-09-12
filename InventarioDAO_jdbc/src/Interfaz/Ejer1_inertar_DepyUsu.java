package Interfaz;

import java.util.Scanner;

import Dao.DaoDepartamento;
import Dao.DaoUsuario;
import jdbc.ConexionJdbc;
import pojos.Departamento;
import pojos.Usuario;

public class Ejer1_inertar_DepyUsu {

	public static void main(String[] args) {

		ConexionJdbc conJdbc = null;
		String nombre;
		int id;
		String[] nombreDep = {"usu1", "usu2", "usu3"};
		
		try{
			//conectamos con la BD
			conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
			conJdbc.conectar();
			
			//Pedimos datos a usuario para anyadir el nuevo departamenteo
			Scanner tec = new Scanner(System.in);
			System.out.println("Nombre del nuevo departamento: ");
			nombre= tec.nextLine();
			
			//Creamos un DaoDepartamento y un pojo Departamento que le mandamos
			DaoDepartamento daoDepartamento = new DaoDepartamento();
			Departamento d = new Departamento(null, nombre);
			
			//Grabamos el nuevo departamento con el DaoDepartamento
			daoDepartamento.grabar(d);
			
			//INSERTAMOS USUARIOS EN EL DEPARTAMENTO Y EN EL GRUPO 1ESOA
			//Averiguamos el id del departamento
			id = daoDepartamento.buscarPorNombre(nombre);
			
			//Generamos 3 usuarios y los insertamos
			DaoUsuario daoUsuario = new DaoUsuario();
			for (int i = 0; i<3; i++){
				Usuario us = new Usuario(null,nombreDep[i],"1234",2,6,"1ESOA",id,null,null,null,null,null,null,null,null);
				daoUsuario.grabar(us);
			}
			
			
		}catch(Exception e){
			System.out.println("Error: pongase en contacto con el admin");
		}finally{
			//finalmente desconectamos		
			System.out.println(conJdbc.desconectar());
		}
	
	}

}
