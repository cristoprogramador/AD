package ejemplo1_jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class _02ConexionDatasource {

	final static String DRIVER = "com.mysql.jdbc.Driver";
	final static String URL = "jdbc:mysql://localhost:3306/ciclismo";
	final static String USR = "root";
	final static String PWD = "mysql";

	public static void main(String[] args) {
		Connection con = null;
		// Creamos datasource y configuramos sus par�metros.
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DRIVER);
		ds.setUrl(URL);
		ds.setUsername(USR);
		ds.setPassword(PWD);
		// Realizamos la conexi�n
		try {
			con = ds.getConnection();
			System.out.println("Conectado");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Cerramos la conexi�n
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
