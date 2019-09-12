package ejercicios2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class Ejer2Autocomit {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;

		boolean autocommitActual = false;

		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo"));
			// crear conexión datasource
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");

			// (Transaction) Crea una tabla con una columna representate. Crea
			// una transacción
			// que cuando se inserte un representante tenga que insertar un
			// nuevo ciclista con
			// ese representante.
			autocommitActual = con.getAutoCommit();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement("INSERT INTO representante_ciclista VALUES (?,?)");

			pstmt.setInt(1, 1);
			pstmt.setString(2, "Juan Garcia");
			pstmt.executeUpdate();

			pstmt = con.prepareStatement("INSERT INTO ciclista VALUES (?,?,?,?,?)");

			pstmt.setInt(1, 103);
			pstmt.setString(2, "ciclista_rep");
			pstmt.setString(3, "TVH");
			pstmt.setDate(4, null);
			pstmt.setInt(5, 1);
			pstmt.executeUpdate();
			System.out.println("Inserto 2");

			// Confirmo transaccion

			con.commit();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// Cerramos el statement
			try {
				con.setAutoCommit(autocommitActual);
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
				System.out.println("Desconectado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
