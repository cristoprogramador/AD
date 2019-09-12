package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojos.Rol;
import pojos.Articulo;
import jdbc.ConexionJdbc;
import excepciones.BusinessException;

public class DaoArticulo extends DaoGenerico<Articulo, Integer>{

	@Override
	public void grabar(Articulo a) throws BusinessException {

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			//Preparar para la inserción
			String sql = "INSERT INTO articulo "
					+ "(idarticulo, numserie, estado, fechaalta, fechabaja, usuarioalta, "
					+ "usuariobaja, modelo, departamento, espacio, dentrode, observaciones) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1,a.getIdArticulo());
			pstm.setString(2,a.getNumserie());
			pstm.setString(3,a.getEstado());
			pstm.setDate(4,(Date) a.getFechaalta());
			pstm.setDate(5,(Date) a.getFechabaja());
			pstm.setInt(6,a.getUsuarioalta());
			pstm.setInt(7,a.getUsuariobaja());
			pstm.setInt(8,a.getModelo());
			pstm.setInt(9,a.getDepartamento());
			pstm.setInt(10,a.getEspacio());
			pstm.setInt(11,a.getDentrode());
			pstm.setString(12,a.getObservaciones());
			//insertar
			pstm.executeUpdate();
			
			//obtener clave generada
			rs= pstm.getGeneratedKeys();
			if (rs.first()) {
				Integer id = rs.getInt(1);
				a.setIdArticulo(id);
			}
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
			//Preparar la actualización.
			String sql = "UPDATE articulo "
					+ " SET numserie= ?, estado = ?, fechaalta= ?, fechabaja= ?,"
					+ " usuarioalta = ?, usuariobaja = ?, modelo = ?, departamento = ?, espacio = ?,"
					+ "dentrode = ?, observaciones = ?"
					+ " WHERE idarticulo = ?";
			
			pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
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
				a.setDentrode(rs.getInt("dentrode"));
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
	
	
	
	public int consultarNumeroArticulosDepartamento(String nombre) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int cantidad = 0;

		String sql = "SELECT count(idarticulo) FROM articulo, departamento "
				+ "WHERE iddepartamento=departamento "
				+ "and departamento.nombre=?;";
		try {

			pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();
			
			rs.next();
			cantidad = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexionJdbc.cerrar(pstm);
		}
		return cantidad;
	}
	
	public List<Articulo> consultarArticulosDepartamento(String nombre) {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Articulo> lista = new ArrayList<>();

		String sql = "SELECT numserie, modelo from departamento, articulo "
				+ "where iddepartamento=departamento "
				+ "and departamento.nombre=?;";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Articulo a = new Articulo();
				a.setNumserie(rs.getString(1));
				a.setModelo(rs.getInt(2));
				lista.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexionJdbc.cerrar(pstm);
		}

		return lista;
	}
	
	//Examen 2 Ejercicio 1 
	public List<Integer> filtarPorModMarca(String id_modelo, String id_marca) throws BusinessException{
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
			if(id_modelo!=null)pstm.setString(numParam++, id_modelo);
			if(id_marca!=null)pstm.setString(numParam, id_marca);	
			
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