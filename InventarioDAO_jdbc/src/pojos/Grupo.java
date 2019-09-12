package pojos;

public class Grupo {

	private String idgrupo;
	private String nombre;
	
	public Grupo(){
		
	}
	public Grupo(String idgrupo, String nombre) {
		super();
		this.idgrupo = idgrupo;
		this.nombre = nombre;
	}
	/**
	 * @return the idgrupo
	 */
	public String getIdgrupo() {
		return idgrupo;
	}
	/**
	 * @param idgrupo the idgrupo to set
	 */
	public void setIdgrupo(String idgrupo) {
		this.idgrupo = idgrupo;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Grupo [idgrupo=" + idgrupo + ", nombre=" + nombre + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idgrupo == null) ? 0 : idgrupo.hashCode());
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
		Grupo other = (Grupo) obj;
		if (idgrupo == null) {
			if (other.idgrupo != null)
				return false;
		} else if (!idgrupo.equals(other.idgrupo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
