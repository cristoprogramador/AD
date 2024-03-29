package pojo;
// Generated 01-feb-2019 0:48:51 by Hibernate Tools 4.0.0.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Grupo generated by hbm2java
 */
@Entity
@Table(name = "grupo", catalog = "musicaconclavesajenas")
public class Grupo implements java.io.Serializable {

	private Integer cod;
	private String nombre;
	private Date fecha;
	private String pais;
	private Set<Pertenece> perteneces = new HashSet<Pertenece>(0);
	private Set<Club> clubs = new HashSet<Club>(0);
	private Set<Disco> discos = new HashSet<Disco>(0);

	public Grupo() {
	}

	public Grupo(String nombre) {
		this.nombre = nombre;
	}

	public Grupo(String nombre, Date fecha, String pais, Set<Pertenece> perteneces, Set<Club> clubs,
			Set<Disco> discos) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.pais = pais;
		this.perteneces = perteneces;
		this.clubs = clubs;
		this.discos = discos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "cod", unique = true, nullable = false)
	public Integer getCod() {
		return this.cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	@Column(name = "nombre", nullable = false, length = 30)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", length = 19)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "pais", length = 10)
	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	public Set<Pertenece> getPerteneces() {
		return this.perteneces;
	}

	public void setPerteneces(Set<Pertenece> perteneces) {
		this.perteneces = perteneces;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	public Set<Club> getClubs() {
		return this.clubs;
	}

	public void setClubs(Set<Club> clubs) {
		this.clubs = clubs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	public Set<Disco> getDiscos() {
		return this.discos;
	}

	public void setDiscos(Set<Disco> discos) {
		this.discos = discos;
	}

}
