package ejercicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class _02consultasSimpesPreparedStatement {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql=null;
		String codigo= null;
		String tipo=null;
		String color=null;
		int premio;
		int res;
		
		Integer[] edad={20,25};
		String[] equipo={"Banesto", "ONCE"};
		
		Scanner tec = new Scanner(System.in);

		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("Configuracion/PropiedadesCiclismo"));
			// Crear conexión DataSource
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");
			// Crear el objeto Statement para lanzar la Base de Datos
			
//a. Inserta los valores de un nuevo maillot que introduza el usuario.
			/*sql="INSERT INTO maillot (codigo, tipo, color, premio) VALUES (?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			do{
				System.out.println("Codigo: (0 para salir) ");
				codigo = tec.next();
				tec.nextLine();
				if (!codigo.equals("0")){
					System.out.println("Tipo: ");			
					tipo = tec.nextLine();
					System.out.println("Color: ");
					color = tec.next();
					System.out.println("Premio: ");
					premio = tec.nextInt();
					
					pstmt.setString(1, codigo);
					pstmt.setString(2, tipo);
					pstmt.setString(3, color);
					pstmt.setInt(4, premio);
					
					res=pstmt.executeUpdate();
				}
			}while (!codigo.equals("0"));
			System.out.println("Se han insertado: " + res + " fila/s");*/
			
//b. Consulta en primer lugar los ciclistas del equipo BANESTO de mas de 20 años
		/*	sql="SELECT nombre from ciclista WHERE nomeq='Banesto' and year(current_date()) - year(nacimiento) > 20";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String nombre = rs.getString(1);
				System.out.println("Mayor de 20 años: " + nombre);
			}*/
			
//c. Consulta en segundo lugar los ciclistas del equipo ONCE DE MÁS de 25 años.
			/*sql="SELECT nombre from ciclista WHERE nomeq=? and year(current_date()) - year(nacimiento) > ?";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String nombre = rs.getString(1);
				System.out.println("Mayor de 25 años: " + nombre);
			}*/
			
//b-c Que el ususario indique el equipo y la edad
			/*sql="SELECT nombre from ciclista WHERE nomeq=? and year(current_date()) - year(nacimiento) > ?";
			pstmt = con.prepareStatement(sql);				
			
			int len = edad.length;
			
			for (int i=0; i<len; i++){			
				pstmt.setString(1, equipo[i]);
				pstmt.setInt(2, edad[i]);
				rs = pstmt.executeQuery();			
				while (rs.next()) {					
					System.out.println("ciclista: "+ edad[i]);
					System.out.println("Edad > " + rs.getString(1));
				}
			}*/
			
//d. Actualizar el premio del maillot AMARILLO en 100€
			/*sql="UPDATE maillot SET premio=premio+100 WHERE codigo='MGE'";
			pstmt = con.prepareStatement(sql);
			res=pstmt.executeUpdate();
			System.out.println("Se han insertado: " + res + " fila/s");
			*/
//e. Actualizar el premio de maillots del tipo que indique el usuario en 50€
			System.out.println("Indique el codigo del maillot: ");
			codigo = tec.next();
			sql="UPDATE maillot SET premio=premio+50 WHERE codigo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, codigo);
			res=pstmt.executeUpdate();
			System.out.println("Se han insertado: " + res + " fila/s");

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
