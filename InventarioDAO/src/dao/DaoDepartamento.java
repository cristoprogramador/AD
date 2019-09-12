package dao;

import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoGenerico;

public class DaoDepartamento extends DaoGenerico<Departamento, Integer>{


	@Override
	public void grabar(Departamento d) throws BusinessException {

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;



		try {
			/* Statement.RETURN_GENERATED_KEYS solo cuando tengamos un valor auto generado (AI),
			 * para obtener el valor*/

			String sql = "INSERT into departamento (nombre) VALUES (?)";

			pstm=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, d.getNombre());
			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();
			if (rs.first()) {
				Integer id = rs.getInt(1);
				d.setIdDepartamento(id);
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
	public void actualizar(Departamento d) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;

		String sql = "UPDATE departamento set nombre=? where iddepartamento=?";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, d.getNombre());
			pstm.setInt(2, d.getIdDepartamento());

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
	public void grabarOActualizar(Departamento d) throws BusinessException {
		if (buscarPorId(d.getIdDepartamento())== null) {
			grabar(d);
		}else {
			actualizar(d);
		}
	}

	@Override
	public void borrar(Integer id) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;

		String sql = "DELETE FROM departamento where iddepartamento=?";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, id);
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
	public void borrar(Departamento d) throws BusinessException {
		borrar(d.getIdDepartamento());
	}
	
	

	@Override
	public Departamento buscarPorId(Integer id) throws BusinessException {
		Departamento d = null;
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "SELECT *  FROM departamento where iddepartamento=?";

		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs=pstm.executeQuery();
			if (rs.first()) {
				d = new Departamento(id,rs.getString("nombre"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al insertar");	
		}finally {
			ConexionJdbc.cerrar(pstm);
		}

		return d;

	}

	@Override
	public List<Departamento> buscarTodos() throws BusinessException {
		List<Departamento> lista = new ArrayList<>();

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "SELECT *  FROM departamento";

		try {
			pstm=con.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while (rs.next()) {
				Departamento d = new Departamento(rs.getInt(1),rs.getString(2));
				lista.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al insertar");	
		}finally {
			ConexionJdbc.cerrar(pstm);
		}
		
		return lista;	

	}
	
	public Departamento buscarPorNombre(String nombre) throws BusinessException {
		Departamento d = null;
		Connection con = ConexionJdbc.getConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;

		try {
			String sql = "select * from departamento "
					+ "where nombre=?";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();
			
			if (rs.first()) {
				d = new Departamento(rs.getInt(1),rs.getString(2));
			}


		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al consultar");
		} finally {
			ConexionJdbc.cerrar(pstm);
			ConexionJdbc.cerrar(rs);
		}

		return d;
	}

	
	

}
