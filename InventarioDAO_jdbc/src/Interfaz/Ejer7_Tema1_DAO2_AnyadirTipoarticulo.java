package Interfaz;

import java.util.ArrayList;
import java.util.List;

import Dao.DaoModeloArticulo;
import Dao.DaoTipoArticulo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.ModeloArticulo;
import pojos.TipoArticulo;

public class Ejer7_Tema1_DAO2_AnyadirTipoarticulo {

	private static ConexionJdbc conJdbc = null;

	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();
		DaoTipoArticulo daoTipoArticulo;
		TipoArticulo tipoArticulo;
		DaoModeloArticulo daoModeloArticulo;
		List<ModeloArticulo> listaModeloArticulos;
		List<TipoArticulo> listaTipoArticulos;
		
		try {
			tipoArticulo = new TipoArticulo();
			daoTipoArticulo = new DaoTipoArticulo();
			daoModeloArticulo = new DaoModeloArticulo();
			listaModeloArticulos = new ArrayList<>();
			listaTipoArticulos = new ArrayList<>();
			
			//Generamos nuevo tipo de articulo
			tipoArticulo.setNombre("mesa de ordenador");
			tipoArticulo.setPadre(1);
			
			//Si el tipo articulo no existe en la bd
			if (!(daoTipoArticulo.buscarTodos().contains(tipoArticulo))){
				//grabamos
				daoTipoArticulo.grabar(tipoArticulo);
				//comprobamos que está en la BD
				if (daoTipoArticulo.buscarTodos().contains(tipoArticulo)){
					System.out.println("Articulo grabado con exito");
				}
			}else //indicamos que ya está en la BD
				System.out.println("Tipo de Articulo ya existe");
				
			//Cambiamos los tipos de articulos por otro
			
			//cogemos todos los modelos de articulos
			listaModeloArticulos = daoModeloArticulo.buscarTodos();	
			
			//buscamos el id de mesa de reuniones
			listaTipoArticulos = daoTipoArticulo.buscarTodos();
			Integer idTipoArticulo = null;
			
			//Si un tipo de articulo. tiene el nombre mesa de reuniones cogemos el id
			for (TipoArticulo tipoArticulo2 : listaTipoArticulos) {
				if (tipoArticulo2.getNombre().equals("mesa reuniones"))
					idTipoArticulo = tipoArticulo2.getIdtipoarticulo();
				System.out.println("id guardado "+idTipoArticulo);
			}
			
			//si el id de mesa reuniones no es nulo
			if (idTipoArticulo != null){
				//a los que tienen el id mesa de reuniones lo cambiamos por el de
				Integer idTipoNuevo = daoTipoArticulo.buscarId(tipoArticulo.getNombre(), tipoArticulo.getPadre());
				for (ModeloArticulo modeloArticulo : listaModeloArticulos) {
					if (modeloArticulo.getTipo() == idTipoArticulo){
						modeloArticulo.setTipo(idTipoNuevo);
						daoModeloArticulo.actualizar(modeloArticulo);
						System.out.println(modeloArticulo.toString()+" Actualizado");
					}
				}
			}

		} catch (BusinessException e) {
			System.out.println("No se pudo insertar tipo");
		} finally {
			conJdbc.desconectar();
			System.out.println("Desconectado de la Base de datos");
		}
	}
	

}
