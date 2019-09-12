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
@Table(name="maillot")
public class Maillot implements Serializable{
	@Id
	private String codigo;
	private String tipo;
	private String color;
	private Integer premio;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="maillots")
	private Set<Etapa>etapas =new HashSet<>();

	public Maillot(String codigo, String tipo, String color, Integer premio) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
		this.color = color;
		this.premio = premio;
	}
	
	public Maillot(){
		
	}
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getPremio() {
		return premio;
	}

	public void setPremio(Integer premio) {
		this.premio = premio;
	}

	public Set<Etapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(Set<Etapa> etapas) {
		this.etapas = etapas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((premio == null) ? 0 : premio.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Maillot other = (Maillot) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (premio == null) {
			if (other.premio != null)
				return false;
		} else if (!premio.equals(other.premio))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Maillot [codigo=" + codigo + ", tipo=" + tipo + ", color=" + color + ", premio=" + premio + "]";
	}

}
