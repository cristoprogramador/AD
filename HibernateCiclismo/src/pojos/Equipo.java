package pojos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="equipo")

public class Equipo implements Serializable{
	@Id
	@Column(name="nomeq")
	private String nombre;
	private String director;
	
	//Relaciones:
	//equipo con ciclista 1:n, este equipo que mapeamos es 
	//el atributo equipo que relacionamos en la clase ciclista
	@OneToMany(fetch=FetchType.LAZY, mappedBy="equipo")
	private Set<Ciclista> ciclistas = new HashSet<>();
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="equipo_representado")
	private Representante representante;

	public Equipo() {
		
	}
	
	public Equipo(String nombre, String director, Representante representante) {
		super();
		this.nombre = nombre;
		this.director = director;
		this.representante = representante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Set<Ciclista> getCiclistas() {
		return ciclistas;
	}

	public void setCiclistas(Set<Ciclista> ciclistas) {
		this.ciclistas = ciclistas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciclistas == null) ? 0 : ciclistas.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		if (ciclistas == null) {
			if (other.ciclistas != null)
				return false;
		} else if (!ciclistas.equals(other.ciclistas))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", director=" + director + ", ciclistas=" + ciclistas + "]";
	}


}
