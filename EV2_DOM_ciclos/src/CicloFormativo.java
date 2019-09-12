
public class CicloFormativo {
	private String nombre;
	private String familia;
	private int grado;
	private int horas;
	
	public CicloFormativo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CicloFormativo(String nombre, String familia, int grado, int horas) {
		super();
		this.nombre = nombre;
		this.familia = familia;
		this.grado = grado;
		this.horas = horas;
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
	 * @return the familia
	 */
	public String getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(String familia) {
		this.familia = familia;
	}

	/**
	 * @return the grado
	 */
	public int getGrado() {
		return grado;
	}

	/**
	 * @param grado the grado to set
	 */
	public void setGrado(int grado) {
		this.grado = grado;
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
	
	
}
