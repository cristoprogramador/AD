package dom;

import java.util.List;

public class CD {

	private String titulo_cd;
	private int num_canciones;
	private int num_disco;
	private List<String> generos;
	
	public CD() {
		super();
	}
	
	public CD(String titulo_cd, int num_canciones, int num_disco, List<String> generos) {
		super();
		this.titulo_cd = titulo_cd;
		this.num_canciones = num_canciones;
		this.num_disco = num_disco;
		this.generos = generos;
	}
	
	/**
	 * @return the titulo_cd
	 */
	public String getTitulo_cd() {
		return titulo_cd;
	}
	/**
	 * @param titulo_cd the titulo_cd to set
	 */
	public void setTitulo_cd(String titulo_cd) {
		this.titulo_cd = titulo_cd;
	}
	/**
	 * @return the num_canciones
	 */
	public int getNum_canciones() {
		return num_canciones;
	}
	/**
	 * @param num_canciones the num_canciones to set
	 */
	public void setNum_canciones(int num_canciones) {
		this.num_canciones = num_canciones;
	}
	/**
	 * @return the num_disco
	 */
	public int getNum_disco() {
		return num_disco;
	}
	/**
	 * @param num_disco the num_disco to set
	 */
	public void setNum_disco(int num_disco) {
		this.num_disco = num_disco;
	}
	/**
	 * @return the generos
	 */
	public List<String> getGeneros() {
		return generos;
	}
	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}
	
}
