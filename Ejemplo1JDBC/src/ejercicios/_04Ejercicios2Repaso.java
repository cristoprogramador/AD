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

public class _04Ejercicios2Repaso {
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

			//1(ResultSet) modifica la base de datos a traés de un Resultset.
			
			//a. Actualiza el valor de los maillot de tipo CD1. Prueva a incrementar el valor
			//del premio en 100€
			/*String sql = "SELECT * FROM maillot where codigo='CD1'";					
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);	
			rs=pstmt.executeQuery();
			
			rs.first();
			int valor = rs.getInt(4);		
		
			rs.updateInt(4, valor+100);
			rs.updateRow();*/
			
			//b.Muestra el valor del último registro de la tabla ETAPA
			/*String sql = "SELECT * FROM etapa";
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs= pstmt.executeQuery();
			rs.last();
			
			System.out.println("Numero de etapa: " +rs.getInt(1) + " - kilometos: " + rs.getInt(2) 
					+ " - Salida: " + rs.getString(3) + " - Llegada: " + rs.getString(4) 
					+ " - Dorsal: " + rs.getInt(5));
			
			//c.Cuál el la ciudad de salidad de la etapa que se encuentra en el registro 2
			rs.absolute(2);
			System.out.println("Registro 2, Salida: " + rs.getString(3));
			
			//d. Modifica su valor por una ciudad de salida que introduzca el usuario
			System.out.println("Introduza ciudad de salida para el registro 2: ");
			String salidaNueva = tec.next();
			
			rs.updateString(3, salidaNueva);
			rs.updateRow();*/
			
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
