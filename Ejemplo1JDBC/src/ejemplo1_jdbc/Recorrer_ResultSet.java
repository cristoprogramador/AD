package ejemplo1_jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class Recorrer_ResultSet {

	// Listar las estapas que ha ganado pedro delgado y la ciudad de sallida
	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo"));
			// crear conexión datasource
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");

			stmt = con.createStatement();
			String sql = "SELECT netapa, salida " + "from ciclista natural join etapa where" 
			+ " nombre='Pedro Delgado'";
			rs = stmt.executeQuery(sql);

			/*
			 * "SELECT netapa, salida" + " from cilcista natural join etapa " +
			 * "were nombre='Pedro Delgado'";
			 */

			while (rs.next()) {
				Integer v_etapa = rs.getInt(1);
				String v_salida = rs.getString(2);

				System.out.println("Ha ganado la etapa: " + v_etapa + " " + v_salida);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerramos el statement
			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
				System.out.println("Desconectado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
