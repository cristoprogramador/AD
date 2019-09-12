package pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jugadores")
public class Jugadores implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	
	private String nombre;
	private String altura;
	private String posicion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Nombre_equipo")
	private String nombre_equipo;

	public Jugadores() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Jugadores(Integer codigo, String nombre, String altura, String posicion, String nombre_equipo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.altura = altura;
		this.posicion = posicion;
		this.nombre_equipo = nombre_equipo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altura == null) ? 0 : altura.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombre_equipo == null) ? 0 : nombre_equipo.hashCode());
		result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
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
		Jugadores other = (Jugadores) obj;
		if (altura == null) {
			if (other.altura != null)
				return false;
		} else if (!altura.equals(other.altura))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nombre_equipo == null) {
			if (other.nombre_equipo != null)
				return false;
		} else if (!nombre_equipo.equals(other.nombre_equipo))
			return false;
		if (posicion == null) {
			if (other.posicion != null)
				return false;
		} else if (!posicion.equals(other.posicion))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Jugadores [codigo=" + codigo + ", nombre=" + nombre + ", altura=" + altura + ", posicion=" + posicion
				+ ", nombre_equipo=" + nombre_equipo + "]";
	}

}
