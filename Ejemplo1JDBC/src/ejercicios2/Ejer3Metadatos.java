package ejercicios2;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import java.sql.SQLException;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class Ejer3Metadatos {
	public static void main(String[] args) {
		Connection con = null;

		try {
			// Conexión -------------
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("PropiedadesCiclismo.txt"));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");
			// DatabaseMetadata
			DatabaseMetaData dbmd = con.getMetaData();
			// Informaciondela base dedatos
			// infoProducto(dbmd);
			// Informaciondel driver
			// infoDriver(dbmd);
			// Informaciondelasfuncionessoportadaspor el sgbd
			// infoFunciones(dbmd);
			// Informaciondelastablas, vistas, etc ...
			// infoTablas(dbmd);
			// Informaciondelosproc. almacenados
			// infoProcedimientos(dbmd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
