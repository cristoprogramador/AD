package Interfaz;

import java.util.Scanner;

import Dao.DaoGrupo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;

public class Ejer3_Tema1_DAO2_BorrarGrupoYUsuarios {
	private static ConexionJdbc conJdbc = null;
	private static String idGrupo;
	private static DaoGrupo daoGrupo;

	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();		
		daoGrupo = new DaoGrupo();
		boolean correct = false;
		
		try {
			Scanner tec = new Scanner(System.in);
			System.out.println("Introduzca id del grupo a eliminar: ");
			idGrupo = tec.nextLine();
			
			if (!daoGrupo.comprobarId(idGrupo)){
				System.out.println("id grupo no existe");
			}else{
			
			correct = daoGrupo.eliminarGrupoYSusUsuarios(idGrupo);

			if (correct)
				System.out.println("Grupo y usuarios eliminados");
			else
				System.out.println("Grupo y usuarios no eliminados");
			}
				
		} catch (BusinessException e) {
			System.out.println("No se pudo hacer la consulta");
		} finally {
			conJdbc.desconectar();
			System.out.println("Desconectado de la Base de datos");
		}
	}
	
}
