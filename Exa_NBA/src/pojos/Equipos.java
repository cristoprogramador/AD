package pojos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "equipos")
public class Equipos implements Serializable {
	@Id
	@OneToOne(fetch=FetchType.LAZY, mappedBy="equipos_Nombre")
	private String nombre;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="nombre_equipo")
	private Set<Jugadores> jugadores = new HashSet<>();
	
	private String ciudad;
	private String conferencia;
	private String Division;
	
	public Equipos() {
	}

	public Equipos(String nombre, Set<Jugadores> jugadores, String ciudad, String conferencia, String division) {
		super();
		this.nombre = nombre;
		this.jugadores = jugadores;
		this.ciudad = ciudad;
		this.conferencia = conferencia;
		Division = division;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the jugadores
	 */
	public Set<Jugadores> getJugadores() {
		return jugadores;
	}

	/**
	 * @param jugadores the jugadores to set
	 */
	public void setJugadores(Set<Jugadores> jugadores) {
		this.jugadores = jugadores;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the conferencia
	 */
	public String getConferencia() {
		return conferencia;
	}

	/**
	 * @param conferencia the conferencia to set
	 */
	public void setConferencia(String conferencia) {
		this.conferencia = conferencia;
	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return Division;
	}

	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		Division = division;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Division == null) ? 0 : Division.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((conferencia == null) ? 0 : conferencia.hashCode());
		result = prime * result + ((jugadores == null) ? 0 : jugadores.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipos other = (Equipos) obj;
		if (Division == null) {
			if (other.Division != null)
				return false;
		} else if (!Division.equals(other.Division))
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (conferencia == null) {
			if (other.conferencia != null)
				return false;
		} else if (!conferencia.equals(other.conferencia))
			return false;
		if (jugadores == null) {
			if (other.jugadores != null)
				return false;
		} else if (!jugadores.equals(other.jugadores))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Equipos [nombre=" + nombre + ", jugadores=" + jugadores + ", ciudad=" + ciudad + ", conferencia="
				+ conferencia + ", Division=" + Division + "]";
	}
	
	
	
}
