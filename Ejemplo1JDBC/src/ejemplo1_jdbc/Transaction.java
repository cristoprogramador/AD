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

public class Transaction {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean autocommitActual = false;
		
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("configuracion\\propiedadesCiclismo"));
			//Crear conexion DataSource
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
			System.out.println("Conexion realizada");
			
			//Crear una transaccion que al insertar/actualizar dos equipos y si no los insertamos que no inserte ninguno.
						
			autocommitActual = con.getAutoCommit();
			con.setAutoCommit(false); 
			
			String sql="select * from equipo";
			
			pstm = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			rs=pstm.executeQuery();
			rs.moveToInsertRow();
			rs.updateString(1, "equipazo 1");
			rs.updateString(2, "director equipazo 1");
			rs.insertRow();
			
			rs.moveToInsertRow();
			rs.updateString(1, "equipazo 2");
			rs.updateString(2, "director equipazo 2");
			rs.insertRow();

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
			//Cerramos el statement
			try {
				con.setAutoCommit(autocommitActual);
				if (pstm != null && !pstm.isClosed())
					pstm.close();
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
