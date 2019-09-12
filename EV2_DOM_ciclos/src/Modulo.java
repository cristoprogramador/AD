
public class Modulo {
	 private String nombre;
	 private int horas;
	 private int curso;
	public Modulo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Modulo(String nombre, int horas, int curso) {
		super();
		this.nombre = nombre;
		this.horas = horas;
		this.curso = curso;
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
	 * @return the horas
	 */
	public int getHoras() {
		return horas;
	}
	/**
	 * @param horas the horas to set
	 */
	public void setHoras(int horas) {
		this.horas = horas;
	}
	/**
	 * @return the curso
	 */
	public int getCurso() {
		return curso;
	}
	/**
	 * @param curso the curso to set
	 */
	public void setCurso(int curso) {
		this.curso = curso;
	}

}
