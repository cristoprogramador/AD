package pojo;
// Generated 01-feb-2019 0:48:51 by Hibernate Tools 4.0.0.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PerteneceId generated by hbm2java
 */
@Embeddable
public class PerteneceId implements java.io.Serializable {

	private String dni;
	private int cod;

	public PerteneceId() {
	}

	public PerteneceId(String dni, int cod) {
		this.dni = dni;
		this.cod = cod;
	}

	@Column(name = "dni", nullable = false, length = 10)
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Column(name = "cod", nullable = false)
	public int getCod() {
		return this.cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PerteneceId))
			return false;
		PerteneceId castOther = (PerteneceId) other;

		return ((this.getDni() == castOther.getDni())
				|| (this.getDni() != null && castOther.getDni() != null && this.getDni().equals(castOther.getDni())))
				&& (this.getCod() == castOther.getCod());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getDni() == null ? 0 : this.getDni().hashCode());
		result = 37 * result + this.getCod();
		return result;
	}

}
