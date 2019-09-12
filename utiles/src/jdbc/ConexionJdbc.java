package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import excepciones.ConnectionException;

public class ConexionJdbc {
	// Atributo para guardar la conexi�n que se obtiene al conectar
	private static Connection con;
	// Atributos para almacenar los datos con los que se ha conectado
	private String driver;
	private String url;
	private String usr;
	private String pwd;
	// Atributo para almacenar el nombre del fichero de configuraci�n con el que
	// se ha conectado
	private String ficheroConfiguracion;

	/**
	 * Constructor: Crea el objeto conocidos el driver,url,usr y pwd, que se
	 * almacenan en atributos privados
	 */
	public ConexionJdbc(String driver, String url, String usr, String pwd) {
		this.driver = driver;
		this.url = url;
		this.usr = usr;
		this.pwd = pwd;
	}

	/**
	 * Constructor: Crea el objeto conocido el nombre del fichero de
	 * configuraci�n y lo almacena en un atributo privado.
	 */
	public ConexionJdbc(String ficheroConfiguracion) {
		this.ficheroConfiguracion = ficheroConfiguracion;
	}

	/**
	 * Conecta con la base de datos. Si el atributo ficheroConfiguracion tiene
	 * valor (no nulo), lo utilizar� para conectar. En caso de que
	 * ficheroConfiguracion sea nulo, conecta utilizando los atributos
	 * driver,url, usr y pwd. Una vez establecida la conexi�n, �sta se almacena
	 * en el atributo con Para conectar har� uso de los m�todos privados
	 * implementados anteriormente
	 */
	private void conectar(String driver, String url, String usr, String pwd) throws ConnectionException {
		con = null;
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(usr);
		ds.setPassword(pwd);

		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			throw new ConnectionException();
		}
	}

	/**
	 * Metodo privado de conexion mediante fichero
	 * @param ficheroConfiguracion Fichero con los datos de la conexion
	 * @throws ConnectionException Excepci�n de conexion
	 */
	private void conectar(String ficheroConfiguracion) throws ConnectionException {
		Properties propiedades  = new Properties();
		try {
			propiedades.load(new FileInputStream(ficheroConfiguracion));
			//Crear conexion DataSource
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Metodo publico que llamamos para conectar
	 */
	public void conectar() {
		//si no hemos conectado aun
		if (con == null) {
			//si el fichero existe lo utilizamos
			if (ficheroConfiguracion != null) {
				conectar(ficheroConfiguracion);
				//Si no existe usamos los parametros dados al constructor
			} else {
				conectar(driver, url, usr, pwd);
			}
		}
	}
	
	/**
	 * 
	 * @return devuelbe el parametro de la conexi�n a la base de datos
	 */
	public static Connection getConnection(){
		return con;
	}
	
	/**
	* Cierra la conexi�n almacenada en el atributo con (si no es null y est� abierta)
	*/
	public String desconectar(){
		String desc = null;
		try{
			if (con != null && !con.isClosed()){
				con.close();
				desc = "Desconectada BD";
			}
		}catch(SQLException e){
			e.printStackTrace();
			desc = "Fallo en la desconexion";
		}
		return desc;
	}
	
	/**
	* Cierra un ResultSet (si no es null y est� abierto)
	*/
	public static void cerrar(ResultSet rs){
		try{
			if( rs != null && !rs.isClosed()){
				rs.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	* Cierra un Statement(si no es null y est� abierto)
	* Este m�todo servir� tanto para cerrar Statemen como para PreparedStatement ya que
	* el segundo es subclase del primero.
	*/
	public static void cerrar(PreparedStatement pstm){
		try{
			if( pstm != null && !pstm.isClosed()){
				pstm.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
