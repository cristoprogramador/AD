package pojos;

public class TipoUsuario {

	private Integer idtipousuario;
	private String nombre;
	
	public TipoUsuario(){
		
	}
	public TipoUsuario(Integer idtipousuario, String nombre) {
		super();
		this.idtipousuario = idtipousuario;
		this.nombre = nombre;
	}
	/**
	 * @return the idtipousuario
	 */
	public Integer getIdtipousuario() {
		return idtipousuario;
	}
	/**
	 * @param idtipousuario the idtipousuario to set
	 */
	public void setIdtipousuario(Integer idtipousuario) {
		this.idtipousuario = idtipousuario;
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
		return "TipoUsuario [idtipousuario=" + idtipousuario + ", nombre=" + nombre + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idtipousuario == null) ? 0 : idtipousuario.hashCode());
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
		TipoUsuario other = (TipoUsuario) obj;
		if (idtipousuario == null) {
			if (other.idtipousuario != null)
				return false;
		} else if (!idtipousuario.equals(other.idtipousuario))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
