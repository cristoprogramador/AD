package ejemplo1_jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.ConexionJdbc;

import excepciones.BusinessException;

public class ejer4_metadatos {
	public static void main(String[] args) {

		ConexionJdbc conJdbc = null;
		try {
			conJdbc = new ConexionJdbc("configuracion/PropiedadesCiclismo.txt");
			conJdbc.conectar();
			listarpropiedades();
			columnaspuerto();
			listarTablas();

		} catch (BusinessException e) {
			System.out.println(e.getMessage());

		} finally {
			conJdbc.desconectar();
		}
	}

	public static void listarpropiedades() throws BusinessException {

		Connection con = null;
		DatabaseMetaData dbmd = null;
	
		try {
			con = ConexionJdbc.getConnection();
			dbmd = con.getMetaData();
			
			// Nombre del SGBD
			String producto;
			System.out.println();
			System.out.println("------ INFORMACION SOBRE EL DBMS -------");

			producto = dbmd.getDatabaseProductName();
			System.out.println("Base datos: " + producto);

			// Versióndel SGBD
			String version;
			version = dbmd.getDatabaseProductVersion();

			// Usuario conectado
			String nombreUsr;
			nombreUsr = dbmd.getUserName();
			System.out.println("Usuario actual " + nombreUsr);
			
			// Url de la base de datos
			String url;
			url = dbmd.getURL();
			System.out.println("URL: " + url);

		} catch (SQLException e) {
			throw new BusinessException("Error al consultar propiedades");
		}

	}

	public static void listarTablas() throws BusinessException {
		Connection con = null;
		ResultSet rsTablas = null;
		DatabaseMetaData dbmdt = null;
		try {
			con = ConexionJdbc.getConnection();
			dbmdt = con.getMetaData();
			// Tablas de la base de datos
			rsTablas = dbmdt.getTables(null, null, null, new String[] { "TABLE" });

			// Recorremos rsTablas
			System.out.println("LISTAR TABLAS");

			System.out.format("%20s %20s %20s %20s %20s\n", "catalogo", "esquema", "nombre tabla", "tipo",
					"comentario");
			while (rsTablas.next()) {
				System.out.format("%20s %20s %20s %20s %20s\n", rsTablas.getString("TABLE_CAT"),
						rsTablas.getString("TABLE_SCHEM"), rsTablas.getString("TABLE_NAME"),
						rsTablas.getString("TABLE_TYPE"), rsTablas.getString("REMARKS"));
			}

		} catch (SQLException e) {
			throw new BusinessException("Error al listar tablas");
		} finally {
			ConexionJdbc.cerrar(rsTablas);
		}

	}

	public static void columnaspuerto() throws BusinessException {

		Connection con = null;
		ResultSet rsp = null;
		DatabaseMetaData dbmdp = null;

		try {
			con = ConexionJdbc.getConnection();

			Statement stm = con.createStatement();
			rsp = stm.executeQuery("Select * from puerto");

			dbmdp = con.getMetaData();
			ResultSetMetaData rsmd = rsp.getMetaData();
			int numColumnas = rsmd.getColumnCount();

			System.out.println("numero columnas" + numColumnas);

		} catch (SQLException e) {
			throw new BusinessException("Error al consultar columnas Puerto");
		}
	}
}
