package ejemplo1_jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _03ConexionProperty {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs= null;

		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo"));
			
			// Crear conexión DataSource
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");
			
			//Crear el objeto Statement para lanzar la Base de Datos
			stmt = con.createStatement();
			
			//CREAR UNA TABLA
			//Creamos la sentencia sql que queremos lanzar
			String sql = "CREATE TABLE IF NOT EXISTS nacionalidad (id int(10),"
					+ "nombre varchar(50) NOT NULL,"
					+ "pais varchar (50), PRIMARY KEY (id))";
			//INSERTAR UNA FILA EN LA TABLA
			//Como es una modificación sobre la BD se ejecuta executeUpdate
//			stmt.executeUpdate(sql);
	//		sql="INSERT INTO nacionalidad VALUES (1, 'española','España')";
		//	stmt.executeUpdate(sql);
		//  +System.out.println("insercción realizada");
			
			//CONSULTA-RECUPERACIÓN DE INFORMACIÓN DE LA BASE DE DATOS
			sql = "SELECT count(*) FROM equipo";
			rs = stmt.executeQuery(sql);
			rs.first();
			System.out.println(rs.getInt(1));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerramos la conexión
			try {
				if (con != null && !con.isClosed())
					con.close();
				System.out.println("Desconectado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}