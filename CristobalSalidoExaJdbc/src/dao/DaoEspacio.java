package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Espacio;

public class DaoEspacio extends DaoGenerico<Espacio, Integer>{
	
	@Override
	public void grabar(Espacio es) throws BusinessException{
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		
		try{
			//Preparar para la inserción
			String sql = "INSERT INTO espacio "
					+ "(idespacio ) VALUES (?)"
					+ "(nombre ) VALUES (?)"
					+ "(descripcion ) VALUES (?)"
					+ "(padre ) VALUES (?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, es.getIdespacio());
			pstm.setString(2, es.getNombre());
			pstm.setString(3, es.getDescripcion());
			pstm.setInt(4, es.getPadre());

			//insertar
			pstm.executeUpdate();
			
		} catch (SQLException e){
			throw new BusinessException("Error al insertar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}	
	}
	
	@Override
	public void borrar(Integer id) throws BusinessException{
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		
		try{
			String sql = "DELETE FROM espacio WHERE idespacio = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			if(pstm.executeUpdate()==0) 
				throw new BusinessException("Elemento no encontrado");
		} catch (SQLException e){
			throw new BusinessException("Error al eliminar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
	
	@Override
	public List<Espacio> buscarTodos() throws BusinessException{		
		List<Espacio> result = new ArrayList<>();
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try{
			String sql = "SELECT * FROM espacio ORDER BY idespacio";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				Espacio e = new Espacio(rs.getInt("idespacio"), rs.getString("nombre"),
						rs.getString("descripcion"),rs.getInt("padre"));
				result.add(e);
			}
			return result;
			
		} catch (SQLException e){
			throw new BusinessException("Error al consultar");
		} finally{
			ConexionJdbc.cerrar(pstm);
		}
	}
}
