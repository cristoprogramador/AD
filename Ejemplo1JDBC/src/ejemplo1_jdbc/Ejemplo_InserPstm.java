package ejemplo1_jdbc;

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

/*
 * Realizar insercciones de forma consecutiva pididiendo valores
 * al usuario en la tabla nacionalidades como id=0 ya no pide más datos
 */
public class Ejemplo_InserPstm {
	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstm = null;
		
		Integer id=0;
		String nombre=null;
		String pais=null;
		
		Scanner tec = new Scanner(System.in);
		
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo"));
			// crear conexión datasource
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");
			
			//Crea el objeto Statement para lanzar la base de datos
			
			//Creamos la sentencia SQL que inserte en la tabla nacionalidad
			pstm = con.prepareStatement("INSERT INTO nacionalidad (id, nombre, pais) VALUES (?,?,?)");
			
			/*Crear un bucle
			 * - Condicion bucle id<>0
			 * - Pedir los valores
			 * - Asignar a los parámetros(?) del insert
			 * - Ejecutar la sentencia
			 */
			do {
				//pedir parametros
				System.out.println("id: ");
				id = tec.nextInt();
				
				if (id !=0){
					System.out.println("Nombre: ");
					nombre = tec.next();
					System.out.println("Pais: ");
					pais = tec.next();
					//Asignar parametros
					pstm.setInt(1, id);
					pstm.setString(2, nombre);
					pstm.setString(3, pais);
					//Ejecutar la sentencia
					pstm.executeUpdate();
					}
			}while (id != 0);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerramos el statement
			try {
				if (pstm != null && !pstm.isClosed())
					pstm.close();
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
