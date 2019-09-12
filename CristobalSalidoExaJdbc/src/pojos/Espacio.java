package pojos;

public class Espacio {

	private int idespacio;
	private String nombre;
	private String descripcion;
	private Integer padre;
	
	public Espacio(){
		
	}

	public Espacio(int idespacio, String nombre, String descripcion, int padre) {
		super();
		this.idespacio = idespacio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.padre = padre;
	}

	public int getIdespacio() {
		return idespacio;
	}

	public void setIdespacio(int idespacio) {
		this.idespacio = idespacio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPadre() {
		return padre;
	}

	public void setPadre(int padre) {
		this.padre = padre;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + idespacio;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + padre;
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
		Espacio other = (Espacio) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idespacio != other.idespacio)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (padre != other.padre)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Espacio [idespacio=" + idespacio + ", nombre=" + nombre + ", descripcion=" + descripcion + ", padre="
				+ padre + "]";
	}
	
}
