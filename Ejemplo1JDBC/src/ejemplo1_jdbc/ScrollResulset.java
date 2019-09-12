package ejemplo1_jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class ScrollResulset {
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

			// Obtener el ciclista más mayor, el más joven 
			// El tercer ciclista más joven, el segundo ciclista más joven
			String sql = "SELECT nombre, dorsal, nacimiento FROM ciclista ORDER BY nacimiento desc";
					
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			rs=pstmt.executeQuery();
			
			//El mayor
			rs.last();
			System.out.println("El ciclista mayor: " + rs.getString(1) + " " + rs.getDate(3));
			
			//El más joven
			rs.first();
			System.out.println("El ciclista mas joven: " + rs.getString(1) + " " + rs.getDate(3));
			
			//El tercero más joven
			rs.absolute(3);
			System.out.println("El ciclista tercero más joven: " + rs.getString(1) + " " + rs.getDate(3));
			
			//El segundo mas joven
			rs.previous();
			System.out.println("El ciclista segundo más joven: " + rs.getString(1) + " " + rs.getDate(3));
			
			//El segundo mas viejo
			rs.absolute(-2);
			System.out.println("El ciclistas segundo más viejo: "+ rs.getString(1) + " " + rs.getDate(3));		

			//SACAR TODOS LOS REGISTROS DE CICLISTA
			rs.beforeFirst();
			while (rs.next()){
				System.out.println("El ciclista: "+rs.getString(1));
			}
			
			//MODIFICAR RESULTSET
			//Modificar los ciclistas del equipo Amore Vita al equipo Wordperfect utilizando
			//el resultset
			//Con Sql sería "UPDATE ciclista SET nomeq='Equipo1' WHERE nomeq='Amore vita'"
			//1º Escribir la select que guarde en el ResultSet las filas que vamos a modificar ->PrepareStatement
			//"UPDATE cilista X WHERE nomeq ='Amore vita'
			//SELECT dorsal, nomeq FROM ciclista WHERE nomeq=?;
			
			
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
