package pojos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "etapa")
public class Etapa implements Serializable{
	
	@Id
	private Integer netapa;
	private Integer km;
	private String salida;
	private String llegada;
	
	//etapa hace referencia al atributo del pojo con el que hacemos la relación
	//guardamos la lista de objetos puerto, mapeado con etapa
	@OneToMany(fetch=FetchType.LAZY, mappedBy="obj_etapa")
	private Set<Puerto> puertos = new HashSet<>();
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "dorsal")
	private Ciclista ciclista_ganador;
	
	 @ManyToMany(fetch=FetchType.LAZY)
	 @JoinTable(
	 name="llevar",
	 joinColumns={@JoinColumn(name="netapa", referencedColumnName="netapa")},
	 inverseJoinColumns={@JoinColumn(name="codigo", referencedColumnName="codigo")})
	private Set<Maillot>maillots =new HashSet<>();
	
	public Etapa(){
		
	}

	public Etapa(Integer netapa, Integer km, String salida, String llegada) {
		super();
		this.netapa = netapa;
		this.km = km;
		this.salida = salida;
		this.llegada = llegada;
	}

	public Integer getNetapa() {
		return netapa;
	}

	public void setNetapa(Integer netapa) {
		this.netapa = netapa;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getLlegada() {
		return llegada;
	}

	public void setLlegada(String llegada) {
		this.llegada = llegada;
	}

	public Set<Puerto> getPuertos() {
		return puertos;
	}

	public void setPuertos(Set<Puerto> puertos) {
		this.puertos = puertos;
	}

	public Ciclista getCiclista_ganador() {
		return ciclista_ganador;
	}

	public void setCiclista_ganador(Ciclista ciclista_ganador) {
		this.ciclista_ganador = ciclista_ganador;
	}
	
	
	public Set<Maillot> getMaillots() {
		return maillots;
	}

	public void setMaillots(Set<Maillot> maillots) {
		this.maillots = maillots;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((km == null) ? 0 : km.hashCode());
		result = prime * result + ((llegada == null) ? 0 : llegada.hashCode());
		result = prime * result + ((netapa == null) ? 0 : netapa.hashCode());
		result = prime * result + ((salida == null) ? 0 : salida.hashCode());
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
		Etapa other = (Etapa) obj;
		if (km == null) {
			if (other.km != null)
				return false;
		} else if (!km.equals(other.km))
			return false;
		if (llegada == null) {
			if (other.llegada != null)
				return false;
		} else if (!llegada.equals(other.llegada))
			return false;
		if (netapa == null) {
			if (other.netapa != null)
				return false;
		} else if (!netapa.equals(other.netapa))
			return false;
		if (salida == null) {
			if (other.salida != null)
				return false;
		} else if (!salida.equals(other.salida))
			return false;
		return true;
	}

	
}
