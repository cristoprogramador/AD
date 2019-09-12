package ejercicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _01consultasSimplesStatement {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String nombre= null;
		String nombre_eq=null;
		String nombre_cicl=null;
		
		Scanner tec = new Scanner(System.in);

		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo"));
			// Crear conexión DataSource
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");
			// Crear el objeto Statement para lanzar la Base de Datos
			stmt = con.createStatement();
			
//a. Dorsal, nombre y equipo de todos los ciclistas
			/*String sql="SELECT dorsal, nombre, nomeq from ciclista";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				System.out.println("Ciclista: " + rs.getInt(1)
				+ " Nombre: " + rs.getString(2)
				+ " Equipo: " + rs.getString(3));
			}*/

//b. Dorsal, nombre y director de todos los ciclistas
			/*String sql="SELECT dorsal, nombre, nomeq, director from ciclista natural join equipo";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				System.out.println("Ciclista: " + rs.getInt(1)
				+ " Nombre: " + rs.getString(2)
				+ " Equipo: " + rs.getString(3)
				+ " Director: " + rs.getString(4));
			}*/
			
//c. Dorsal, nombre y director de los ciclistas del equipo "ONCE"
			/*String sql="SELECT dorsal, nombre, nomeq from ciclista natural join equipo"
					+" where ciclista.nomeq='ONCE'";
				String sql="SELECT dorsal, nombre, nomeq, director from ciclista natural join equipo";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				System.out.println("Ciclista: " + rs.getInt(1)
				+ " Nombre: " + rs.getString(2)
				+ " Equipo: " + rs.getString(3)
				+ " Director: " + rs.getString(4));
			}*/
			
//d. Dorsal, nombre y equipo de los ciclistas del equipo cuyo nombre indique el usuario
			/*System.out.println("Nombre del equipo: ");
			nombre = tec.nextLine();
			String sql="SELECT dorsal, nombre, nomeq from ciclista where nomeq='"+nombre+"'";

			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				System.out.println("Ciclista: " + rs.getInt(1)
				+ " Nombre: " + rs.getString(2)
				+ " Equipo: " + rs.getString(3));
			}*/
			
//e. Dorsal, nombre y equipo de los ciclistas del equipo cuyo nombre indique el usuario y cuyo nombre de corredor contenga el texto que indique el usuario
			System.out.println("Nombre del equipo: ");
			nombre_eq = tec.nextLine();
			System.out.println("Nombre ciclista: ");
			nombre_cicl = tec.nextLine();
			String sql="SELECT dorsal, nombre, nomeq from ciclista where nomeq='"+nombre_eq+"'"
			+ " and nombre like ('%" + nombre_cicl + "%')";

			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				System.out.println("Ciclista: " + rs.getInt(1)
				+ " Nombre: " + rs.getString(2)
				+ " Equipo: " + rs.getString(3));
			}			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
