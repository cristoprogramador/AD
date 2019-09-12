package ejercicios2;

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

public class Ejer1 {
	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		
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

			/*AMPLIACION RESULTSET*/
			
			//Actualiza los puertos de altitud mayor a 1500m, su categoria a X
			
			/*String sql = "SELECT * FROM puerto WHERE altura > 1500";					
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);	
			rs=pstmt.executeQuery();
						
			rs.beforeFirst();
			
			while (rs.next()){
			rs.updateString(3, "X");
			rs.updateRow();
			}*/
			
			//Inserta un nuevo maillot código CD2, color 2, premio 200
			String sql = "SELECT * FROM maillot";
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			
			rs.moveToInsertRow();
			rs.updateString(1, "CD2");
			rs.updateString(2, "");
			rs.updateString(3, "Color 2");
			rs.updateInt(4, 200);
			rs.insertRow();
			
			//Borra los maillots que no han sido llevados nunca
			
			
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
