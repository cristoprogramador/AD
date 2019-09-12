package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConexionJdbc;
import pojos.Articulo;
import pojos.Departamento;
import pojos.Usuario;
import excepciones.BusinessException;


public class DaoDepartamento  extends DaoGenerico<Departamento, Integer>{
	

	@Override
	public void grabar(Departamento d) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			//Preparar para la inserci�n
			String sql = "INSERT INTO departamento "
					+ "(nombre ) "
					+ "VALUES (?)";
			
			pstm = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setString(1,d.getNombre());

			//insertar
			pstm.executeUpdate();
			
			//obtener clave generada
			rs= pstm.getGeneratedKeys();
			if (rs.first()) {
				Integer id = rs.getInt(1);
				d.setIddepartamento(id);
			}
		} catch (SQLException e){
			throw new BusinessException("Error al insertar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void actualizar(Departamento d) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		try{
			//Preparar la actualizaci�n.
			String sql = "UPDATE departamento "
					+ " SET nombre = ? "
					+ " WHERE iddepartamento = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1,d.getNombre());
			pstm.setInt(2,d.getIddepartamento());
			
			//Ejecutar la actualizacion
			if(pstm.executeUpdate()==0) 
				throw new BusinessException("Elemento no encontrado");

		} catch (SQLException e){
			throw new BusinessException("Error al actualizar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public void grabarOActualizar(Departamento d) throws BusinessException {
		if(buscarPorId(d.getIddepartamento())!=null) grabar(d);
		else actualizar(d);
	}

	@Override
	public void borrar(Departamento d) throws BusinessException {
		borrar(d.getIddepartamento());
	}

	@Override
	public void borrar(Integer id) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		try{
			String sql = "DELETE FROM departamento WHERE iddepartamento = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			if(pstm.executeUpdate()==0) throw new BusinessException("Elemento no encontrado");
		} catch (SQLException e){
			throw new BusinessException("Error al eliminar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public Departamento buscarPorId(Integer id) throws BusinessException {
		Departamento result = null;
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT * FROM departamento WHERE iddepartamento = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.first()){
				result = new Departamento(id, rs.getString("nombre"));
			}
			return result;
			
		} catch (SQLException e){
			throw new BusinessException("Error al consultar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}

	@Override
	public List<Departamento> buscarTodos() throws BusinessException {
		List<Departamento> result = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT * FROM departamento ORDER BY iddepartamento";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Departamento d = new Departamento(rs.getInt("iddepartamento"), rs.getString("nombre"));
				result.add(d);
			}
			return result;
			
		} catch (SQLException e){
			throw new BusinessException("Error al consultar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
}
