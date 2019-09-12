package Interfaz;

import Dao.DaoArticulo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Articulo;

public class InsertarModeloYArticulo {
	private static ConexionJdbc conJdbc = null;

	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();
		DaoArticulo daoArticulo = new DaoArticulo();
		Articulo articulo = new Articulo();

		try {
			
			articulo.setIdArticulo(30);
			articulo.setModelo(2599);
			articulo.setUsuarioalta(498);
			articulo.setEspacio(1);
			
			daoArticulo.actualizar(articulo);			

		} catch (BusinessException e) {
			System.out.println("No se pudo hacer la consulta");
		} finally {
			conJdbc.desconectar();
			System.out.println("Desconectado de la Base de datos");
		}
	}
	
}
