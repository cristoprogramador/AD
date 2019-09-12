package Dao;

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

public class __PlantillasDao {
	//Insertar datos en tabla, recibimos un pojo y lo mandamos
	public void grabar(Usuario u) throws BusinessException {
		
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			//Sentencia sql de busqueda
			String sql = "INSERT INTO usuario (username, password, tipo, rol, grupo, departamento"
					+ ", nombre, apellido1,apellido2,domicilio,poblacion,codpostal,email,telefono )"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			//indicamos al statement que devuelva la clave id autogenerada
			pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//colocamos los valores recibidos en el statement
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

			// insertamos en la base de datos
			pstm.executeUpdate();

			// obtener clave generada en el resultset
			rs = pstm.getGeneratedKeys();
			//sacamos la clabe generada del resultset y la añadimos al usuario recibido
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

	//Consultas por uno o mas parametros
	public List<Integer> filtarPorModDepat(Integer id_modelo, Integer id_dept) throws BusinessException{
		Connection con = ConexionJdbc.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Integer> result = new ArrayList<Integer>();
				
		try{
			//Añadimos el where true para poder realizar este tipo de consultas
			String sql = "SELECT idarticulo FROM articulo "
					+ "WHERE TRUE ";
			//Si el parametro que nos envia no es nulo, añadimos a la senticia el condicionante de busqueda
			if(id_modelo!=null)sql += "AND modelo =?";
			if(id_dept!=null)sql+= "AND departamento=?";	
			
			//generamos el prepare statement
			pstm = con.prepareStatement(sql);
			
			//contador para los parametroa a añadir
			int numParam=1;
			
			//se han de poner en el mismo orden, con ++ todos menos el ultimo
			if(id_modelo!=null)pstm.setInt(numParam++, id_modelo);
			if(id_dept!=null)pstm.setInt(numParam, id_dept);	
			
			//ejecutamos el preparestatemente y lo guardamos en el resultset
			rs=pstm.executeQuery();
			
			//añadimos a parametro a devolver los resultados guardados en el resultset
			while(rs.next()){	
				result.add(rs.getInt("idarticulo"));
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
