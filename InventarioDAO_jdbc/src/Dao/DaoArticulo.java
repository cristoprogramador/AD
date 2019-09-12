package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.DaoGenerico;
import pojos.Articulo;
import jdbc.ConexionJdbc;
import excepciones.BusinessException;

public class DaoArticulo extends DaoGenerico<Articulo, Integer>{
	
	@Override
	public void grabar(Articulo a) throws BusinessException {

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;

		try{
			//Preparar para la inserciï¿½n
			String sql = "INSERT INTO articulo "
					+ "(idarticulo, numserie, estado, fechaalta, fechabaja, usuarioalta, "
					+ "usuariobaja, modelo, departamento, espacio, dentrode, observaciones) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1,a.getIdArticulo());
			pstm.setString(2,a.getNumserie());
			pstm.setString(3,a.getEstado());
			pstm.setDate(4,(Date) a.getFechaalta());
			pstm.setDate(5,(Date) a.getFechabaja());
			pstm.setNull(6,a.getUsuarioalta());
			pstm.setNull(7,a.getUsuariobaja());
			pstm.setInt(8,a.getModelo());
			pstm.setNull(9,a.getDepartamento());
			pstm.setInt(10,a.getEspacio());
			pstm.setNull(11,a.getDentrode());
			pstm.setString(12,a.getObservaciones());
			//insertar
			pstm.executeUpdate();		
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new BusinessException("Error al insertar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void actualizar(Articulo a) throws BusinessException {

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		try{
			//Preparar la actualizaciï¿½n.
			String sql = "UPDATE articulo "
					+ " SET numserie= ?, estado = ?, fechaalta= ?, fechabaja= ?,"
					+ " usuarioalta = ?, usuariobaja = ?, modelo = ?, departamento = ?, espacio = ?,"
					+ "dentrode = ?, observaciones = ?"
					+ " WHERE idarticulo = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1,a.getNumserie());
			pstm.setString(2,a.getEstado());
			pstm.setDate(3,(Date) a.getFechaalta());
			pstm.setDate(4,(Date) a.getFechabaja());
			pstm.setInt(5,a.getUsuarioalta());
			pstm.setInt(6,a.getUsuariobaja());
			pstm.setInt(7,a.getModelo());
			pstm.setInt(8,a.getDepartamento());
			pstm.setInt(9,a.getEspacio());
			pstm.setInt(10,a.getDentrode());
			pstm.setString(11,a.getObservaciones());
			pstm.setInt(12,a.getIdArticulo());
			
			//Ejecutar la actualizacion
			pstm.executeUpdate();

		} catch (SQLException e){
			throw new BusinessException("Error al actualizar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void grabarOActualizar(Articulo a) throws BusinessException {
		if(buscarPorId(a.getIdArticulo())!=null) grabar(a);
		else actualizar(a);
	}

	@Override
	public void borrar(Articulo a) throws BusinessException {
		borrar(a.getIdArticulo());
	}

	@Override
	public void borrar(Integer id) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		try{
			String sql = "DELETE FROM articulo WHERE idarticulo= ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (SQLException e){
			throw new BusinessException("Error al eliminar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public Articulo buscarPorId(Integer id)  throws BusinessException {
		Articulo a = null;
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT * FROM articulo WHERE idarticulo=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if (rs.first()){
				a  = new Articulo();
				
				a.setIdArticulo(rs.getInt("idarticulo"));
				a.setNumserie(rs.getString("numserie"));
				a.setEstado(rs.getString("estado"));
				a.setFechaalta(rs.getDate("fechaalta"));
				a.setFechabaja(rs.getDate("fechabaja"));
				a.setUsuarioalta(rs.getInt("usuarioalta"));
				a.setUsuariobaja(rs.getInt("usuariobaja"));
				a.setModelo(rs.getInt("modelo"));
				a.setDepartamento(rs.getInt("departamento"));
				a.setEspacio(rs.getInt("espacio"));
				a.setDentrode(rs.getInt("dentrode"));
				a.setObservaciones(rs.getString("observaciones"));
			}
			return a;
		} catch (SQLException e){
			throw new BusinessException("Error al consultar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
	
	@Override
	public List<Articulo> buscarTodos()  throws BusinessException {
		List<Articulo> result = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT * FROM articulo ORDER BY idarticulo";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Articulo a  = new Articulo();
				
				a.setIdArticulo(rs.getInt("idarticulo"));
				a.setNumserie(rs.getString("numserie"));
				a.setEstado(rs.getString("estado"));
				a.setFechaalta(rs.getDate("fechaalta"));
				a.setFechabaja(rs.getDate("fechabaja"));
				a.setUsuarioalta(rs.getInt("usuarioalta"));
				a.setUsuariobaja(rs.getInt("usuariobaja"));
				a.setModelo(rs.getInt("modelo"));
				a.setDepartamento(rs.getInt("departamento"));
				a.setEspacio(rs.getInt("espacio"));
				//a.setDentrode(rs.getInt("dentrode"));
				a.setObservaciones(rs.getString("observaciones"));
				
				result.add(a);
			}
			return result;
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new BusinessException("Error al consultar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	///Extras///
	
	//Ejercicio 5 ejer2DAO devuelve lista de los tipos de articulos que no han sido prestados
	public List<Integer> tipoArticulosNoPrestados() throws BusinessException{
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Integer> result = new ArrayList<>();
				
		try{
			String sql = "select modeloarticulo.tipo from articulo, modeloarticulo "
					+ "where articulo.modelo = modeloarticulo.idmodeloarticulo "
					+ "and articulo.idarticulo not in (select articulo from salida) "
					+ "group by modeloarticulo.tipo";
			
			pstm = con.prepareStatement(sql);	
			rs=pstm.executeQuery();
			
			while(rs.next()){	
				result.add(rs.getInt(1));
			}
			
			return result;
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new BusinessException("Error al insertar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
	
	//Ejercicio 6 ejer2DAO
	public HashMap<Integer, Integer> articulosNoPrestadosPorTipo() throws BusinessException{
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
				
		//Como hay mas tipos de articulos que los que están en la tabla tipoarticulo
		//Cogemos los tipos de la tabla modeloarticulo
		try{
			String sql = "select modeloarticulo.tipo, count(articulo.idarticulo) from articulo, modeloarticulo "
					+ "where articulo.modelo = modeloarticulo.idmodeloarticulo "
					+ "and articulo.idarticulo not in (select articulo from salida)"
					+ "group by modeloarticulo.tipo;";
			
			pstm = con.prepareStatement(sql);	
			rs=pstm.executeQuery();
			
			while(rs.next()){	
				result.put(rs.getInt(1), rs.getInt(2));
			}
			
			return result;
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new BusinessException("Error al insertar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
	
	public List<Integer> filtarPorModDepat(Integer id_modelo, Integer id_dept) throws BusinessException{
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Integer> result = new ArrayList<Integer>();
				
		try{
			String sql = "SELECT idarticulo FROM articulo "
					+ "WHERE TRUE ";
			if(id_modelo!=null)sql += "AND modelo =?";
			if(id_dept!=null)sql+= "AND departamento=?";	
			
			pstm = con.prepareStatement(sql);
			//contamos los interrogantes
			int numParam=1;
			
			//se han de poner en el mismo orden 
			// a mas 
			if(id_modelo!=null)pstm.setInt(numParam++, id_modelo);
			if(id_dept!=null)pstm.setInt(numParam, id_dept);	
			
			rs=pstm.executeQuery();
			
			while(rs.next()){	
				result.add(rs.getInt("idarticulo"));
			}
			
			return result;
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new BusinessException("Error al insertar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
	
	//Examen 2 Ejercicio 1 
	public List<Integer> filtarPorModMarca(Integer id_modelo, Integer id_marca) throws BusinessException{
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Integer> result = new ArrayList<Integer>();
				
		try{
			String sql = "SELECT articulo.idarticulo FROM articulo, modeloarticulo "
					+ "WHERE articulo.modelo = modeloarticulo.idmodeloarticulo "
					+ "And TRUE ";
			
			if(id_modelo!=null)sql += "AND modeloarticulo.modelo = ?";
			if(id_marca!=null)sql+= "AND modeloarticulo.marca = ?";	
			
			pstm = con.prepareStatement(sql);
			//contamos los interrogantes
			int numParam=1;
			
			//se han de poner en el mismo orden 
			// a mas 
			if(id_modelo!=null)pstm.setInt(numParam++, id_modelo);
			if(id_marca!=null)pstm.setInt(numParam, id_marca);	
			
			rs=pstm.executeQuery();
			
			while(rs.next()){	
				result.add(rs.getInt("idarticulo"));
			}
			
			return result;
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new BusinessException("Error al insertar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

}