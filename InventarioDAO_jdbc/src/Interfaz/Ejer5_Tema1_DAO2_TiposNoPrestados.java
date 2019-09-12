package Interfaz;

import java.util.List;

import Dao.DaoArticulo;
import Dao.DaoTipoArticulo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.TipoArticulo;

public class Ejer5_Tema1_DAO2_TiposNoPrestados {
	private static ConexionJdbc conJdbc = null;

	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();
		DaoArticulo daoArticulo;
		DaoTipoArticulo daoTipoArticulo;
		TipoArticulo tipoArticulo;
		List<Integer> result;

		try {
			
			daoArticulo = new DaoArticulo();
			daoTipoArticulo = new DaoTipoArticulo();
			result = daoArticulo.tipoArticulosNoPrestados();
			
			for (Integer integer : result) {
				
				tipoArticulo = daoTipoArticulo.buscarPorId(integer);
				
				String out = "id: "+ integer +" Descripción: ";
				if (tipoArticulo != null){
					out += tipoArticulo.getNombre();
				}
				System.out.println(out);
				
			}
			
		} catch (BusinessException e) {
			System.out.println("No se pudo hacer la consulta");
		} finally {
			conJdbc.desconectar();
			System.out.println("Desconectado de la Base de datos");
		}
	}
}
