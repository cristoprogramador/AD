package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoGenerico;
import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Salida;

public class DaoSalida extends DaoGenerico<Salida,Integer> {
	/**
	 * Guarda una salida
	 */
	@Override
	public void grabar(Salida a) throws BusinessException {

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			//Preparar para la inserci�n
			String sql = "INSERT INTO salida "
					+ "(usuario, articulo, fechasalida, fechdevolucion) "
					+ "VALUES (?,?,?,?)";
			
			pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1,a.getUsuario());
			pstm.setInt(2,a.getArticulo());
			//pstm.setDate(3,(Date) a.getFechaSalida());
			//pstm.setDate(4,(Date) a.getFechaDevolucion());
			//insertar
			pstm.executeUpdate();
			
			//obtener clave generada
			rs= pstm.getGeneratedKeys();
			if (rs.first()) {
				Integer id = rs.getInt(1);
				a.setIdSalida(id);
			}
		} catch (SQLException e){
			e.printStackTrace();
			throw new BusinessException("Error al insertar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	/**
	 * Actualiza una salida
	 */
	@Override
	public void actualizar(Salida a) throws BusinessException {

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		try{
			//Preparar la actualizaci�n.
			String sql = "UPDATE salida "
					+ " SET usuario= ?, articulo = ?, fechasalida= ?, fechadevolucion= ?"
					+ " WHERE idsalida = ?";
			
			pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1,a.getUsuario());
			pstm.setInt(2,a.getArticulo());
		//	pstm.setDate(3,(Date) a.getFechaSalida());
		//	pstm.setDate(4,(Date) a.getFechaDevolucion());
			pstm.setInt(5,a.getIdSalida());
			
			//Ejecutar la actualizacion
			pstm.executeUpdate();

		} catch (SQLException e){
			throw new BusinessException("Error al actualizar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	/**
	 * Graba o actualiza una salida
	 */
	@Override
	public void grabarOActualizar(Salida a) throws BusinessException {
		if(buscarPorId(a.getIdSalida())!=null) grabar(a);
		else actualizar(a);
	}

	/**
	 * Borra una salida
	 */
	@Override
	public void borrar(Salida a) throws BusinessException {
		borrar(a.getIdSalida());
	}

	/**
	 * Borra una salida
	 */
	@Override
	public void borrar(Integer id) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		try{
			String sql = "DELETE FROM salida WHERE idsalida= ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (SQLException e){
			throw new BusinessException("Error al eliminar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	/**
	 * Busca por id
	 */
	@Override
	public Salida buscarPorId(Integer id)  throws BusinessException {
		Salida a = null;
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT * FROM salida WHERE idsalida=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if (rs.first()){
				a  = new Salida();
				
				a.setIdSalida(rs.getInt("idsalida"));
				a.setUsuario(rs.getInt("usuario"));
				a.setArticulo(rs.getInt("articulo"));
		//		a.setFechaSalida(rs.getDate("fechasalida"));
			//	a.setFechaDevolucion(rs.getDate("fechadevolucion"));
	
			}
			return a;
		} catch (SQLException e){
			throw new BusinessException("Error al consultar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
	
	/**
	 * Busca todas las salidas
	 */
	@Override
	public List<Salida> buscarTodos()  throws BusinessException {
		List<Salida> result = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT * FROM salida ORDER BY idsalida";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Salida a  = new Salida();
				
				a.setIdSalida(rs.getInt("idsalida"));
				a.setUsuario(rs.getInt("usuario"));
				a.setArticulo(rs.getInt("articulo"));
	//			a.setFechaSalida(rs.getDate("fechasalida"));
	//			a.setFechaDevolucion(rs.getDate("fechadevolucion"));

				
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
	
}
