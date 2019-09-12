import java.util.List;

public class GrupoAlumnos {
	private String codigo;
	private String nombre;
	private int maxAlumnos;
	private List<String> nombreAlumnos;
	
	public GrupoAlumnos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GrupoAlumnos(String codigo, String nombre, int maxAlumnos, List<String> nombreAlumnos) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.maxAlumnos = maxAlumnos;
		this.nombreAlumnos = nombreAlumnos;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * @return the maxAlumnos
	 */
	public int getMaxAlumnos() {
		return maxAlumnos;
	}

	/**
	 * @param maxAlumnos the maxAlumnos to set
	 */
	public void setMaxAlumnos(int maxAlumnos) {
		this.maxAlumnos = maxAlumnos;
	}

	/**
	 * @return the nombreAlumnos
	 */
	public List<String> getNombreAlumnos() {
		return nombreAlumnos;
	}

	/**
	 * @param nombreAlumnos the nombreAlumnos to set
	 */
	public void setNombreAlumnos(List<String> nombreAlumnos) {
		this.nombreAlumnos = nombreAlumnos;
	}
	
	
}
