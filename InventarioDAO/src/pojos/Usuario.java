package pojos;

public class Usuario {
	
	

	Integer idusuario;
	String username;
	String password;
	Integer tipo;
	Integer rol;
	String grupo;
	Integer departamento;
	String nombre;
	String apellido1;
	String apellido2;
	String domicilio;
	String poblacion;
	String codpostal;
	String email;
	String telefono;
	

	public Usuario() {
		
	}

	
	
	public Usuario(Integer idusuario, String username, String password, Integer tipo, Integer rol, String grupo,
			Integer departamento, String nombre, String apellido1, String apellido2, String domicilio, String poblacion,
			String codpostal, String email, String telefono) {
		
		
		this.idusuario = idusuario;
		this.username = username;
		this.password = password;
		this.tipo = tipo;
		this.rol = rol;
		this.grupo = grupo;
		this.departamento = departamento;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.domicilio = domicilio;
		this.poblacion = poblacion;
		this.codpostal = codpostal;
		this.email = email;
		this.telefono = telefono;
	}





	public Integer getIdusuario() {
		return idusuario;
	}



	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Integer getTipo() {
		return tipo;
	}



	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}



	public Integer getRol() {
		return rol;
	}



	public void setRol(Integer rol) {
		this.rol = rol;
	}



	public String getGrupo() {
		return grupo;
	}



	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}



	public Integer getDepartamento() {
		return departamento;
	}



	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido1() {
		return apellido1;
	}



	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}



	public String getApellido2() {
		return apellido2;
	}



	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}



	public String getDomicilio() {
		return domicilio;
	}



	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}



	public String getPoblacion() {
		return poblacion;
	}



	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}



	public String getCodpostal() {
		return codpostal;
	}



	public void setCodpostal(String codpostal) {
		this.codpostal = codpostal;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Usuario other = (Usuario) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Usuario=" + idusuario + ", tipo=" + tipo + ", rol=" + rol + ", departamento=" + departamento
				+ ", username=" + username + ", password=" + password + ", grupo=" + grupo + ", nombre=" + nombre
				+ ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", domicilio=" + domicilio + ", poblacion="
				+ poblacion + ", codpostal=" + codpostal + ", email=" + email + ", telefono=" + telefono;
	}
	
	
	
	
}
