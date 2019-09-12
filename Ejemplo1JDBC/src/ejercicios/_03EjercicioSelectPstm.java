package ejercicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _03EjercicioSelectPstm {

	/*
	 * Crea una clase que lea de un array con ciudad de salida de etapa y con el
	 * codigo de maillot y que muestre el nombre del ciclista que lo llevaba
	 * ["Córdoba", "Granada", "Salamanca"} ["MGE", "MGE", "MMO"] public class
	 * Ejemplo _SelectPstm
	 */

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String[] salida = { "Córdoba", "Granada", "Salamanca" };
		String[] codigo = { "MGE", "MGE", "MMO" };
		int cont = 0;

		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo"));
			// crear conexión datasource
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");

			// Creamos la sentencia SQL que inserte en la tabla nacionalidad
			String sql = "SELECT ciclista.nombre FROM ciclista, llevar, etapa "
					+ "where ciclista.dorsal=llevar.dorsal and llevar.netapa=etapa.netapa "
					+ "and codigo=? and salida=?";

			pstmt = con.prepareStatement(sql);

			while (cont < salida.length) {
				pstmt.setString(1, codigo[cont]);
				pstmt.setString(2, salida[cont]);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println("En la etapa de " + salida[cont] + " Nombre: " + rs.getString(1));
				}
				cont++;
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
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
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
