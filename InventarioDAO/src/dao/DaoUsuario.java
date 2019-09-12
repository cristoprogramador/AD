package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import excepciones.BusinessException;
import jdbc.ConexionJdbc;
import pojos.Usuario;

public class DaoUsuario extends DaoGenerico<Usuario, Integer> {

	@Override
	public void grabar(Usuario u) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "INSERT into usuario (username,password,tipo,rol,grupo,departamento,nombre,"
				+ "apellido1,apellido2,domicilio,poblacion,codpostal,email,telefono)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			pstm=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, u.getUsername());
			pstm.setString(2, u.getPassword());
			pstm.setInt(3, u.getTipo());
			pstm.setInt(4, u.getRol());
			pstm.setString(5, u.getGrupo());
			pstm.setInt(6, u.getDepartamento());
			pstm.setString(7, u.getNombre());
			pstm.setString(8, u.getApellido1());
			pstm.setString(9, u.getApellido2());
			pstm.setString(10, u.getDomicilio());
			pstm.setString(11, u.getPoblacion());
			pstm.setString(12, u.getCodpostal());
			pstm.setString(13, u.getEmail());
			pstm.setString(14, u.getTelefono());
			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();

			if (rs.first()) {
				Integer i = rs.getInt(1);
				u.setIdusuario(i);
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
	public void actualizar(Usuario u) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;

		String sql = "UPDATE usario set username=?,password=? ,tipo=?,rol=?,"
				+ "grupo=?,departamento=?,nombre=?,apellido1=?,apellido2=?,"
				+ "domicilio=?,poblacion=?,codpostal=?,email=?,telefono=? "
				+ "WHERE idusuario=?";
		try{
			pstm=con.prepareStatement(sql);
			pstm.setString(1, u.getUsername());
			pstm.setString(2, u.getPassword());
			pstm.setInt(3, u.getTipo());
			pstm.setInt(4, u.getRol());
			pstm.setString(5, u.getGrupo());
			pstm.setInt(6, u.getDepartamento());
			pstm.setString(7, u.getNombre());
			pstm.setString(8, u.getApellido1());
			pstm.setString(9, u.getApellido2());
			pstm.setString(10, u.getDomicilio());
			pstm.setString(11, u.getPoblacion());
			pstm.setString(12, u.getCodpostal());
			pstm.setString(13, u.getEmail());
			pstm.setString(14, u.getTelefono());
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al insertar");
		}finally {
			ConexionJdbc.cerrar(pstm);
		}


	}

	@Override
	public void grabarOActualizar(Usuario u) throws BusinessException {
		if (buscarPorId(u.getIdusuario()) == null) {
			grabar(u);
		} else {
			actualizar(u);
		}
	}
	
	@Override
	public void borrar(Integer id) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		
		String sql = "DELETE * FROM usuario where idusuario=?";
		
		try {
			pstm = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void borrar(Usuario u) throws BusinessException {
		borrar(u.getDepartamento());
		
	}

	@Override
	public Usuario buscarPorId(Integer id) throws BusinessException {
		Usuario u = null;
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM usuario WHERE idusuario=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.first()) {
				u = new Usuario(id,rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al insertar");	
		}finally {
			ConexionJdbc.cerrar(pstm);
		}

		return u;

	}

	@Override
	public List<Usuario> buscarTodos() throws BusinessException {
		List<Usuario> lista = new ArrayList<>();

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM usuario";

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Usuario u = new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Error al insertar");	
		}finally {
			ConexionJdbc.cerrar(pstm);
		}

		return lista;
	}
	
	public boolean ValidarUsuario(String username, String password) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean encontrado = false;
		
		String sql = "SELECT count(*) FROM usuario WHERE username=? and password=?;";
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			rs = pstm.executeQuery();
			
			
		while (rs.first()) {
			if (rs.getInt(1)==0) {
				encontrado = false;
			} else {
				encontrado= true;
			}
		}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			ConexionJdbc.cerrar(pstm);
		}
		
		return encontrado;
		
		
	}
	
	public List<Usuario> cosultarUsuarioPorRol(String nombre){
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Usuario> lista = new ArrayList<>();
		
		String sql = "SELECT usuario.nombre, username from usuario,rol where usuario.rol=rol.idrol and rol.nombre =?;";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();
			while(rs.next()){
				Usuario u = new Usuario();
				u.setNombre(rs.getString(1));
				u.setUsername(rs.getString(2));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConexionJdbc.cerrar(pstm);
		}
		
		return lista;
		
		
		
	}



}
