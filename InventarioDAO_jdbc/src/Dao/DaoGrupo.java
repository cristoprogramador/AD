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
import pojos.Grupo;
import pojos.Grupo;

public class DaoGrupo extends DaoGenerico<Grupo, String>{
	
		@Override
		public void grabar(Grupo g) throws BusinessException {

			Connection con = ConexionJdbc.getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;

			try {

				String sql = "INSERT INTO grupo (nombre)" + "VALUES (?)";
				pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstm.setString(1, g.getNombre());
				pstm.executeUpdate();

				rs = pstm.getGeneratedKeys();
				if (rs.first()) {
					String id = rs.getString(1);
					g.setIdgrupo(id);
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException("Error al insertar");
			} finally {
				ConexionJdbc.cerrar(rs);
				ConexionJdbc.cerrar(pstm);
			}

		}

		@Override
		public void actualizar(Grupo g) throws BusinessException{
			Connection con = ConexionJdbc.getConnection();
			PreparedStatement pstm = null;

			try {
				String sql = "UPDATE grupo" + " SET nombre=?" + " WHERE idgrupo=?";

				pstm = con.prepareStatement(sql);
				pstm.setString(1, g.getNombre());
				pstm.executeUpdate();

				if (pstm.executeUpdate() == 0)
					throw new BusinessException("Elemento no encontrado");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException("Error al actualizar");

			} finally {
				ConexionJdbc.cerrar(pstm);
			}
		}
		
		@Override
		public void grabarOActualizar(Grupo g) throws BusinessException{
			
			if (buscarPorId(g.getIdgrupo()) == null){
				grabar(g);
			}else{
				actualizar(g);
			}
			
		}
		
		@Override
		public void borrar (String id) throws BusinessException{
			Connection con = ConexionJdbc.getConnection();
			PreparedStatement pstm = null;

			try {
				String sql = "DELETE FROM grupo" +  " WHERE idgrupo=?";

				pstm = con.prepareStatement(sql);
				pstm.setString(1, id);
				
				if (pstm.executeUpdate() == 0)
					throw new BusinessException("Elemento no encontrado");
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException("Error al borrar");

			} finally {
				ConexionJdbc.cerrar(pstm);
			}
		}
		
		@Override
		public void borrar (Grupo g) throws BusinessException{
			borrar(g.getIdgrupo());
		}
		
		@Override
		public Grupo buscarPorId (String id) throws BusinessException{
			Grupo g = null;
			
			Connection con = ConexionJdbc.getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;

			try {
				String sql = "SELECT * FROM grupo" +  " WHERE idgrupo=?";

				pstm = con.prepareStatement(sql);
				pstm.setString(1, id);
				rs=pstm.executeQuery();
			
				if (rs.first()){
					g = new Grupo(id, rs.getString("nombre"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException("Error al consultar");

			} finally {
				ConexionJdbc.cerrar(rs);
				ConexionJdbc.cerrar(pstm);
			}
			return g;
		}
		
		@Override
		public List<Grupo> buscarTodos() throws BusinessException{
			List<Grupo> result = new ArrayList<Grupo>();
			
			Connection con = ConexionJdbc.getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;

			try {
				String sql = "SELECT * FROM grupo";

				pstm = con.prepareStatement(sql);
				rs=pstm.executeQuery();
			
				while (rs.next()){
					Grupo g = new Grupo(rs.getString(1), rs.getString("nombre"));
					result.add(g);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException("Error al consultar");

			} finally {
				ConexionJdbc.cerrar(rs);
				ConexionJdbc.cerrar(pstm);
			}
			return result;
		}
		
		///EXTRAS///
		
		public boolean comprobarId(String id) throws BusinessException {
			boolean encontrado = false;
			List<Grupo> listaGrupos = new ArrayList<>();	
			listaGrupos = buscarTodos();
			
			for (Grupo grupo : listaGrupos) {
				encontrado = id.equals(grupo.getIdgrupo());
			}	
			return encontrado;
		}
		
		public boolean eliminarGrupoYSusUsuarios(String id) throws BusinessException{

			Connection con = ConexionJdbc.getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			boolean autocommitActual = false;
			boolean correct = false;
			DaoUsuario daousuario = new DaoUsuario();
			
			try {
				autocommitActual = con.getAutoCommit();
				con.setAutoCommit(false);
				
				//Sentencia sql 1
				pstm = con.prepareStatement("UPDATE usuario SET grupo=null WHERE grupo = ?");
				pstm.setString(1, id);
				pstm.executeUpdate();
				//Sentencia sql 2
				pstm = con.prepareStatement("DELETE FROM grupo WHERE idgrupo = ?");
				pstm.setString(1, id);	
				pstm.executeUpdate();								
				//escribismos en la base de datos
				con.commit();
				correct = true;	
				
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				throw new BusinessException("Error al insertar");
			} finally {
				try {
					con.setAutoCommit(autocommitActual);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ConexionJdbc.cerrar(rs);
				ConexionJdbc.cerrar(pstm);
			}
			
			return correct;
		}
}
