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

public class Ejem_PreparedStatement1 {
	public static void main(String[] args) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		
		Scanner tec = new Scanner(System.in);
		
		//Leer el dato que se le pide al usuario
		System.out.println("Categoria del puerto");
		String categoria = tec.nextLine();
		
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo"));
			// crear conexión datasource
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");		
			//Crea el objeto Statement para lanzar la base de datos
			
			//Obtener el nombre de los ciclistas que han ganado un puerto
			//de categoria que indica el usuario
			pstm = con.prepareStatement(" select nombre from ciclista natural join puerto where categoria=?");
			
			//Le asignamos valor al ? como acategoria y ejecutamos
			pstm.setString(1, categoria);			
			rs=pstm.executeQuery();
			
			while (rs.next()){
				System.out.println("Nombre: " + rs.getString(1));
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
