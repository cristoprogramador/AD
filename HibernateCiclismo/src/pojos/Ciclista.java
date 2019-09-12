package pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ciclista")

public class Ciclista implements Serializable{
	
	//en la bd la tabla es con minuscula pero en Java la clase empieza por mayuscula
	//cambiaremos la referencia mas adelante
	
	@Id //Es clave primaria
	@GeneratedValue(strategy=GenerationType.AUTO) //cuando la clave primaria es autoincrementado hay que indicarlo asi
	private Integer dorsal;
	private String nombre;
	private Date nacimiento; 	//import java.util.Date
	
	//especificamos tabla 
	//primero especificamos las clabes ajena y luego las privadas de esta clase y luego la otra que tendrá el mappedby
	@ManyToMany (fetch=FetchType.LAZY)
	@JoinTable(
			name ="gana", 
			joinColumns={@JoinColumn(name ="dorsal", referencedColumnName="dorsal")},
			inverseJoinColumns={@JoinColumn(name="premio", referencedColumnName="codigo")})
	private Set<Premios>premios =new HashSet<>();
	
	//las claves ajenas no se ponen, se indican a continuacion como relaciones
	
	@ManyToOne(fetch=FetchType.LAZY) 	//solo se recuperan los datos cuando se accede a ellos
	@JoinColumn(name="nomeq")
	private Equipo equipo; 		//Este lo mapeamos en la clase Equipo
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ciclista")
	private Set<Puerto> puertos = new HashSet<>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ciclista_ganador")
	private Set<Etapa>etapas_ganadas =new HashSet<>();
	
	public Ciclista(Integer dorsal, String nombre, Date nacimiento) {
		super();
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.nacimiento = nacimiento;
	}
	
	public Ciclista(){}

	public Integer getDorsal() {
		return dorsal;
	}

	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public Set<Premios> getPremios() {
		return premios;
	}

	public void setPremios(Set<Premios> premios) {
		this.premios = premios;
	}
	
	public Set<Etapa> getEtapas_ganadas() {
		return etapas_ganadas;
	}

	public void setEtapas_ganadas(Set<Etapa> etapas_ganadas) {
		this.etapas_ganadas = etapas_ganadas;
	}

	public Set<Puerto> getPuertos() {
		return puertos;
	}

	public void setPuertos(Set<Puerto> puertos) {
		this.puertos = puertos;
	}
	
	@Override
	public String toString() {
		return "Ciclista [dorsal=" + dorsal + ", nombre=" + nombre + ", nacimiento=" + nacimiento + "]";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dorsal == null) ? 0 : dorsal.hashCode());
		result = prime * result + ((nacimiento == null) ? 0 : nacimiento.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Ciclista other = (Ciclista) obj;
		if (dorsal == null) {
			if (other.dorsal != null)
				return false;
		} else if (!dorsal.equals(other.dorsal))
			return false;
		if (nacimiento == null) {
			if (other.nacimiento != null)
				return false;
		} else if (!nacimiento.equals(other.nacimiento))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	

}
