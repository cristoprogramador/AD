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
import pojos.Usuario;

public class DaoUsuario extends DaoGenerico<Usuario, Integer> {

	@Override
	public void grabar(Usuario u) throws BusinessException {
		
		comprobarTipoUsuario(u);
		
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		
		try {
			String sql = "INSERT INTO usuario (username, password, tipo, rol, grupo, departamento"
					+ ", nombre, apellido1,apellido2,domicilio,poblacion,codpostal,email,telefono )"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

			// insertar
			pstm.executeUpdate();

			// obtener clave generada
			rs = pstm.getGeneratedKeys();
			if (rs.first()) {
				Integer id = rs.getInt(1);
				u.setIdusuario(id);
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
	public void actualizar(Usuario u) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;

		try {
			String sql = "UPDATE usuario" + " SET (username=?, password=?, tipo=?, rol=?, grupo=?, departamento=?"
					+ ", nombre=?, apellido1=?,apellido2=?,domicilio=?,poblacion=?,codpostal=?,email=?,telefono=?)"
					+ " WHERE iddusuario=?";

			pstm = con.prepareStatement(sql);
			
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
			pstm.setString(13, u.getTelefono());
			//indicamos a que usuario actualizamos
			pstm.setInt(14, u.getIdusuario());
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

		try {
			String sql = "DELETE FROM usuario" + " WHERE idusuario=?";

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
	public void borrar(Usuario u) throws BusinessException {
		borrar(u.getIdusuario());
	}

	@Override
	public Usuario buscarPorId(Integer id) throws BusinessException {
		Usuario d = null;

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM usuario" + " WHERE idusuario=?";

			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			if (rs.first()) {
				d = new Usuario(rs.getInt("idusuario"), rs.getString("username"), rs.getString("password"),
						rs.getInt("tipo"), rs.getInt("rol"), rs.getString("grupo"), rs.getInt("departamento"),
						rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"),
						rs.getString("domicilio"), rs.getString("poblacion"), rs.getString("codpostal"),
						rs.getString("email"), rs.getString("telefono"));
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

	@Override
	public List<Usuario> buscarTodos() throws BusinessException {
		List<Usuario> result = new ArrayList<Usuario>();

		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM usuario";

			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Usuario d = new Usuario(rs.getInt("idusuario"), rs.getString("username"), rs.getString("password"),
						rs.getInt("tipo"), rs.getInt("rol"), rs.getString("grupo"), rs.getInt("departamento"),
						rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"),
						rs.getString("domicilio"), rs.getString("poblacion"), rs.getString("codpostal"),
						rs.getString("email"), rs.getString("telefono"));
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

	
	///Extras///
	
	public boolean comprobarId(Integer id) throws BusinessException {
		boolean encontrado = false;
		List<Usuario> listaUsuarios = new ArrayList<>();	
		listaUsuarios = buscarTodos();
		
		for (Usuario usuario : listaUsuarios) {
			encontrado = id.equals(usuario.getIdusuario());
		}	
		return encontrado;
	}
	
	public boolean ValidarUsuario(String username, String password) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean encontrado = false;

		String sql = "SELECT count(*) FROM usuario WHERE username=? and password=?;";
		try {

			/*
			 * Contamos todas la filas en la tabla ususario que tengan el nombre
			 * y passwor recibidos por los parametros
			 */
			pstm = con.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			rs = pstm.executeQuery();

			// Recorremos el resultset
			while (rs.next()) {
				// Si el resultado del conteo es 0
				if (rs.getInt(1) == 0) {
					encontrado = false; // No valido
				} else {
					encontrado = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexionJdbc.cerrar(pstm);
		}
		return encontrado;
	}

	public List<Usuario> cosultarUsuarioPorRol(String nombre) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Usuario> lista = new ArrayList<>();

		String sql = "SELECT usuario.nombre, username from usuario,rol "
				+ "where usuario.rol=rol.idrol and rol.nombre =?;";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Usuario u = new Usuario();
				u.setNombre(rs.getString(1));
				u.setUsername(rs.getString(2));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexionJdbc.cerrar(pstm);
		}

		return lista;
	}

	public List<Usuario> cosultarUsuarioPorDepartamento(String nombre) throws BusinessException {
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Usuario> lista = new ArrayList<>();

		String sql = "SELECT usuario.nombre, username from usuario,departamento "
				+ "where usuario.departamento=departamento.iddepartamento " + "and departamento.nombre =?;";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Usuario u = new Usuario();
				u.setNombre(rs.getString(1));
				u.setUsername(rs.getString(2));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexionJdbc.cerrar(pstm);
		}

		return lista;
	}

	//Si el tipo de usuario no tiene el departamento correspondiente, salta excepcion y no continua
	private void comprobarTipoUsuario(Usuario u) throws BusinessException{
		Integer  tipo=u.getTipo();
		
		if(tipo==DaoTipoUsuario.PROFESOR){
			if(u.getGrupo()!=null)
				
				throw new BusinessException ("El profesor tiene asignado un grupo");
			if(u.getDepartamento()==null)
				throw new BusinessException ("El profesor no tiene asignado un departamento");
			
		}else if(tipo==DaoTipoUsuario.ALUMNO){
			
			if(u.getGrupo()!=null)
				throw new BusinessException ("El alumno no tiene asignado un grupo");
			if(u.getDepartamento()==null)
				throw new BusinessException ("El alumno tiene asignado un departamento");
			
		}else{
			
			if(u.getGrupo()!=null)
				throw new BusinessException ("PAS tiene asignado un grupo");
			if(u.getDepartamento()==null)
				throw new BusinessException ("PAS tiene asignado un departamento");
		}	
	}

	//Ejercicio 2.1 ejer2DAO
	public List<Usuario> filtrarPorNombre(String nombre, String apellido1, String apellido2)  throws BusinessException{
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Usuario> lista = new ArrayList<>();

		try {
			String sql = "SELECT * from usuario WHERE TRUE ";
			
			if(nombre!=null)sql += "AND nombre =?";
			if(apellido1!=null)sql+= "AND apellido1=?";
			if(apellido2!=null)sql+= "AND apellido2=?";
			pstm = con.prepareStatement(sql);

			//contamos los interrogantes
			int numParam=1;
			
			//se han de poner en el mismo orden a mas 
			if(nombre!=null)pstm.setString(numParam++, nombre);
			if(apellido1!=null)pstm.setString(numParam++, apellido1);
			if(apellido2!=null)pstm.setString(numParam, apellido2);

			rs = pstm.executeQuery();

			while (rs.next()) {
				Usuario d = new Usuario(rs.getInt("idusuario"), rs.getString("username"), rs.getString("password"),
						rs.getInt("tipo"), rs.getInt("rol"), rs.getString("grupo"), rs.getInt("departamento"),
						rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"),
						rs.getString("domicilio"), rs.getString("poblacion"), rs.getString("codpostal"),
						rs.getString("email"), rs.getString("telefono"));
				lista.add(d);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexionJdbc.cerrar(pstm);
		}

		return lista;
	}


	//Ejercicio 2.2 ejer2DAO
	public List<Usuario> filtarPorTipoUsuarioYDepartamento(Integer tipoUsuario, String departamento)throws BusinessException{
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<Usuario> result = new ArrayList<Usuario>();
				
		try{
			//Añadimos el where true para poder realizar este tipo de consultas
			String sql = "SELECT * FROM usuario, departamento "
					+ "WHERE TRUE ";
			//Si el parametro que nos envia no es nulo, añadimos a la senticia el condicionante de busqueda
			if(tipoUsuario!=null)sql += "AND tipo =?";
			if(departamento!=null)sql+= "AND departamento.iddepartamento = usuario.departamento and departamento.nombre like ?";	
			
		
			//generamos el prepare statement
			pstm = con.prepareStatement(sql);
			
			//contador para los parametroa a añadir
			int numParam=1;
			
			//se han de poner en el mismo orden, con ++ todos menos el ultimo
			if(tipoUsuario!=null)pstm.setInt(numParam++, tipoUsuario);
			if(departamento!=null)pstm.setString(numParam, '%'+departamento+'%');	
			//System.out.println(pstm);(si queremos comprobar la sentencia que se envia)
			
			//ejecutamos el preparestatemente y lo guardamos en el resultset
			rs=pstm.executeQuery();
			
			//añadimos a parametro a devolver los resultados guardados en el resultset
			while(rs.next()){
				Usuario d = new Usuario(rs.getInt("idusuario"), rs.getString("username"), rs.getString("password"),
						rs.getInt("tipo"), rs.getInt("rol"), rs.getString("grupo"), rs.getInt("departamento"),
						rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"),
						rs.getString("domicilio"), rs.getString("poblacion"), rs.getString("codpostal"),
						rs.getString("email"), rs.getString("telefono"));
				result.add(d);
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
