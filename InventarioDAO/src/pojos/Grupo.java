package pojos;

public class Grupo {
	private String idgrupo;
	private String nombre;
	
	public Grupo(String idgrupo, String nombre) {
		this.idgrupo = idgrupo;
		this.nombre = nombre;
	}

	public String getIdgrupo() {
		return idgrupo;
	}

	public void setIdgrupo(String idgrupo) {
		this.idgrupo = idgrupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	@Override
	public String toString() {
		return "grupo idgrupo=" + idgrupo + ", nombre=" + nombre;
	}
	
	
	
	
	

}
