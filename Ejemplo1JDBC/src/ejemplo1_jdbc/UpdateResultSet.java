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

public class UpdateResultSet {
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

			//MODIFICAR RESULTSET
			//Modificar los ciclistas del equipo Amore Vita al equipo Wordperfect utilizando
			//el resultset
			//Con Sql sería "UPDATE ciclista SET nomeq='Equipo1' WHERE nomeq='Amore vita'"
			//1º Escribir la select que guarde en el ResultSet las filas que vamos a modificar ->PrepareStatement
			//"UPDATE cilista X WHERE nomeq ='Amore vita'
			//SELECT dorsal, nomeq FROM ciclista WHERE nomeq=?;
			
			//Modificar los ciclistas del equipo Amore Vitae al 
			//equipo Wordperfect
			
			//Sql que contiene las filas sobre las que quiero hacer modificaciones
			//update ciclista set nomeq='Equipo1' where nomeq='Amore vita'
			String sql = "SELECT dorsal, nomeq FROM ciclista WHERE nomeq='Amore Vita'";
					
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			rs=pstmt.executeQuery();
			
			//Recorrer el resultset re y modificar fila a fila
			while (rs.next()){
				rs.updateString(2, "Wordperfect");
				rs.updateRow();
				cont++;
			}
			System.out.println("Se han actualizado "+cont+" filas");
			
			//Insertar resultset. Insertar un nuevo equipo
			/*1º hacemos un select que guarde en rs la tabla en la que 
			 * vamos a insertar un registro a traves del resultset
			 * rs SELECT * FROM EQUIPO
			 * 2º Mosverse al final rs.moveToInsertRow
			 * 3º Rellenar las columnas de la fila a insertar
			 * con los nuevos valores, un updateString por cada columna
			 * 4º Insertar en la BD rs.insertRow
			 */
			
			
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
