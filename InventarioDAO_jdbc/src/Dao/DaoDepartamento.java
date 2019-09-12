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
import pojos.Departamento;

public class DaoDepartamento extends DaoGenerico<Departamento, Integer> {

	@Override
	public void grabar(Departamento d) throws BusinessException {

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String sql = "INSERT INTO departamento (nombre)" + "VALUES (?)";
			pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, d.getNombre());
			pstm.executeUpdate();

			// obtener clave generada
			rs = pstm.getGeneratedKeys();
			if (rs.first()) {
				Integer id = rs.getInt(1);
				d.setIdDepartamento(id);
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
	public void actualizar(Departamento d) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;

		try {
			String sql = "UPDATE departamento" + " SET nombre=?" + " WHERE iddepartamento=?";

			pstm = con.prepareStatement(sql);
			pstm.setString(1, d.getNombre());
			pstm.setInt(2, d.getIdDepartamento());
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
	public void grabarOActualizar(Departamento d) throws BusinessException {

		if (buscarPorId(d.getIdDepartamento()) == null) {
			grabar(d);
		} else {
			actualizar(d);
		}

	}

	@Override
	public void borrar(Integer id) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;

		try {
			String sql = "DELETE FROM departamento" + " WHERE iddepartamento=?";

			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);

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
	public void borrar(Departamento d) throws BusinessException {
		borrar(d.getIdDepartamento());
	}

	@Override
	public Departamento buscarPorId(Integer id) throws BusinessException {
		Departamento d = null;

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM departamento" + " WHERE iddepartamento=?";

			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			if (rs.first()) {
				d = new Departamento(id, rs.getString("nombre"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al consultar");

		} finally {
			ConexionJdbc.cerrar(rs);
			ConexionJdbc.cerrar(pstm);
		}

		return d;
	}

	public Integer buscarPorNombre(String nombre) throws BusinessException {

		Integer id = null;
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT iddepartamento FROM departamento " + "WHERE nombre =?";

			pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();
			if (rs.first()) {
				id = rs.getInt("iddepartamento");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al consultar departamento");
		} finally {
			ConexionJdbc.cerrar(pstm);
			ConexionJdbc.cerrar(rs);
		}

		return id;

	}

	@Override
	public List<Departamento> buscarTodos() throws BusinessException {
		List<Departamento> result = new ArrayList<Departamento>();

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM departamento";

			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Departamento d = new Departamento(rs.getInt(1), rs.getString("nombre"));
				result.add(d);
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

}
