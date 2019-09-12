package pojos;
// Generated 19-ene-2016 14:06:58 by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Pertenece generated by hbm2java
 */
@Entity
@Table(name = "pertenece", catalog = "musicaconclavesajenas")
public class Pertenece implements java.io.Serializable {

	private PerteneceId id;
	private Artista artista;
	private Grupo grupo;
	private String funcion;

	public Pertenece() {
	}

	public Pertenece(PerteneceId id, Artista artista, Grupo grupo) {
		this.id = id;
		this.artista = artista;
		this.grupo = grupo;
	}

	public Pertenece(PerteneceId id, Artista artista, Grupo grupo, String funcion) {
		this.id = id;
		this.artista = artista;
		this.grupo = grupo;
		this.funcion = funcion;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "dni", column = @Column(name = "dni", nullable = false, length = 10) ),
			@AttributeOverride(name = "cod", column = @Column(name = "cod", nullable = false) ) })
	public PerteneceId getId() {
		return this.id;
	}

	public void setId(PerteneceId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dni", nullable = false, insertable = false, updatable = false)
	public Artista getArtista() {
		return this.artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod", nullable = false, insertable = false, updatable = false)
	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Column(name = "funcion", length = 15)
	public String getFuncion() {
		return this.funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

}