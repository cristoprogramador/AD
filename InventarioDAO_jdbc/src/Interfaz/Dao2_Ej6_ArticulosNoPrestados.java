package Interfaz;

import java.util.HashMap;
import java.util.Iterator;

import Dao.DaoArticulo;
import Dao.DaoTipoArticulo;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.TipoArticulo;

public class Dao2_Ej6_ArticulosNoPrestados {

	private static ConexionJdbc conJdbc = null;
	

	public static void main(String[] args) {
		conJdbc = new ConexionJdbc("configuracion/PropiedadesInventario.txt");
		conJdbc.conectar();
		DaoArticulo daoArticulo= new DaoArticulo();
		DaoTipoArticulo daoTipoArticulo = new DaoTipoArticulo();
		TipoArticulo tipoArticulo = new TipoArticulo();
		HashMap<Integer, Integer> result;
		Iterator<Integer> it;

		try {			
			
			result = daoArticulo.articulosNoPrestadosPorTipo();
			
			// Imprimimos el Map con un Iterador
			it = result.keySet().iterator();
			while(it.hasNext()){
			  Integer key = it.next();
			  
			  //Al recivir el id y la cantidad mediante un map, haremos uso
			  //del metodo buscarPorId del tipoarticullo y obtendremos las descripciones
			  //de las que dispongamos para cada tipo de articulo
			  tipoArticulo = daoTipoArticulo.buscarPorId(key);
			  if (tipoArticulo != null){
			  System.out.println("Tipo: " + key + " " + tipoArticulo.getNombre() 
			  + " -> No prestados: " + result.get(key));
			  }else
				  System.out.println("Tipo: " + key + " sin descripción" 
				  + " -> No prestados: " + result.get(key));
			}
			

		} catch (BusinessException e) {
			System.out.println("No se pudo hacer la consulta");
		} finally {
			conJdbc.desconectar();
		}
	}


}
