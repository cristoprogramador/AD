package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoGenerico;
import pojos.TipoArticulo;
import jdbc.ConexionJdbc;
import excepciones.BusinessException;

public class DaoTipoArticulo  extends DaoGenerico<TipoArticulo, Integer>{
	@Override
	/**
	 * Persiste el tipoarticulo dado. El identificador no se genera autom�ticamente
	 * sino que esta incluido en el objeto dado.
	 */
	public void grabar(TipoArticulo t) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try{
			String sql = "INSERT INTO tipoarticulo "
					+ "(nombre, padre ) "
					+ "VALUES (?,?)";
			
			pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstm.setString(1,t.getNombre());
			pstm.setInt(2,t.getPadre());

			//insertar
			pstm.executeUpdate();
			
			//obtener clave generada
			rs= pstm.getGeneratedKeys();
			Integer id;
			if (rs.first()) {
				id = rs.getInt(1);
				t.setIdtipoarticulo(id);
			}
			
		} catch (SQLException e){
			throw new BusinessException("Error al insertar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void actualizar(TipoArticulo t) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		try{
			//Preparar la actualizaci�n.
			String sql = "UPDATE tipoarticulo"
					+ " SET nombre = ? "
					+ ", padre = ?"
					+ " WHERE idtipoarticulo = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1,t.getNombre());
			pstm.setInt(2, t.getPadre());
			pstm.setInt(3,t.getIdtipoarticulo());
			
			//Ejecutar la actualizacion
			pstm.executeUpdate();

		} catch (SQLException e){
			throw new BusinessException("Error al actualizar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void grabarOActualizar(TipoArticulo t) throws BusinessException {
		if(buscarPorId(t.getIdtipoarticulo())!=null) grabar(t);
		else actualizar(t);
	}

	@Override
	public void borrar(TipoArticulo t) throws BusinessException {
		borrar(t.getIdtipoarticulo());
	}

	@Override
	public void borrar(Integer id) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		try{
			String sql = "DELETE FROM tipoarticulo WHERE idtipotipoarticulo = ?";
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
	public TipoArticulo buscarPorId(Integer id) throws BusinessException {
		TipoArticulo result = null;
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT * FROM tipoarticulo WHERE idtipoarticulo = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.first()){
				result = new TipoArticulo(id, rs.getString("nombre"), rs.getInt("padre"));
			}
			return result;
			
		} catch (SQLException e){
			throw new BusinessException("Error al consultar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public List<TipoArticulo> buscarTodos() throws BusinessException {
		List<TipoArticulo> result = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT * FROM tipoarticulo ORDER BY idtipoarticulo";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				TipoArticulo g = new TipoArticulo(rs.getInt("idtipoarticulo"), rs.getString("nombre"),rs.getInt("padre"));
				result.add(g);
			}
			return result;
			
		} catch (SQLException e){
			throw new BusinessException("Error al consultar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
	
	//EXTRA
	
	//Ejercicio 7 DAO2
	public Integer buscarId(String nombre, Integer idPadre) throws BusinessException {
		Integer result = null;
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT idtipoarticulo FROM tipoarticulo WHERE nombre = ? and padre = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			pstm.setInt(2, idPadre);
			rs = pstm.executeQuery();
			if(rs.first()){
				result = rs.getInt("idtipoarticulo");
			}
			return result;
			
		} catch (SQLException e){
			throw new BusinessException("Error al consultar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
	
}
