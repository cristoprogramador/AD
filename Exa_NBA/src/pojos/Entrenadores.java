package pojos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "entrenadores")
public class Entrenadores  implements Serializable {

	@Id
	private String dni;
	private String nombre;
	private Integer nume_carnet;
	private Date fecha;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="equipos_Nombre")
	private Equipos equipos_Nombre;

	public Entrenadores() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entrenadores(String dni, String nombre, Integer nume_carnet, Date fecha, Equipos equipos_Nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.nume_carnet = nume_carnet;
		this.fecha = fecha;
		this.equipos_Nombre = equipos_Nombre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((equipos_Nombre == null) ? 0 : equipos_Nombre.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nume_carnet == null) ? 0 : nume_carnet.hashCode());
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
		Entrenadores other = (Entrenadores) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (equipos_Nombre == null) {
			if (other.equipos_Nombre != null)
				return false;
		} else if (!equipos_Nombre.equals(other.equipos_Nombre))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nume_carnet == null) {
			if (other.nume_carnet != null)
				return false;
		} else if (!nume_carnet.equals(other.nume_carnet))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entrenadores [dni=" + dni + ", nombre=" + nombre + ", nume_carnet=" + nume_carnet + ", fecha=" + fecha
				+ ", equipos_Nombre=" + equipos_Nombre + "]";
	}
	
	
}
