package dao;

import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Grupo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoGenerico;

public class DaoGrupo extends DaoGenerico<Grupo, Integer>{


	@Override
	public void grabar(Grupo g) throws BusinessException {

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;



		try {
			/* Statement.RETURN_GENERATED_KEYS solo cuando tengamos un valor auto generado (AI),
			 * para obtener el valor*/

			String sql = "INSERT into grupo (nombre) VALUES (?)";

			pstm=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
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
		}finally {
			ConexionJdbc.cerrar(pstm);
			ConexionJdbc.cerrar(rs);
		}
	}

	@Override
	public void actualizar(Grupo g) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;

		String sql = "UPDATE grupo set nombre=? where idgrupo=?";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, g.getNombre());
			pstm.setString(2, g.getIdgrupo());

			if (pstm.executeUpdate()==0) {
				throw new BusinessException("Error al insertar");
			}


		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al insertar");
		}finally {
			ConexionJdbc.cerrar(pstm);
		}


	}
	
	@Override
	public void grabarOActualizar(Grupo d) throws BusinessException {
		if (buscarPorId(d.getIdgrupo())== null) {
			grabar(d);
		}else {
			actualizar(d);
		}
	}

	@Override
	public void borrar(Integer id) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		String idString = Integer.toString(id);

		String sql = "DELETE FROM grupo where idgrupo=?";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, idString);
			Integer num = pstm.executeUpdate();

			if (num==0) {
				throw new BusinessException("Error al insertar");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al insertar");	
		}finally {
			ConexionJdbc.cerrar(pstm);
		}
	}
	
	@Override
	public void borrar(Grupo d) throws BusinessException {
		borrar(d.getIdgrupo());
	}
	
	

	@Override
	public Grupo buscarPorId(Integer id) throws BusinessException {
		Grupo g = null;
		String idString = Integer.toString(id);
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "SELECT *  FROM grupo where idgrupo=?";

		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, idString);
			rs=pstm.executeQuery();
			if (rs.first()) {
				g = new Grupo(idString,rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al insertar");	
		}finally {
			ConexionJdbc.cerrar(pstm);
		}

		return g;

	}

	@Override
	public List<Grupo> buscarTodos() throws BusinessException {
		List<Grupo> lista = new ArrayList<>();

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "SELECT *  FROM departamento";

		try {
			pstm=con.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while (rs.next()) {
				Grupo g = new Grupo(rs.getString(1),rs.getString(2));
				lista.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al insertar");	
		}finally {
			ConexionJdbc.cerrar(pstm);
		}
		
		return lista;	

	}
	
	

}
