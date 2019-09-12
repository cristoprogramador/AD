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
	@Table(name="premios")

	public class Premios  implements Serializable{
		//Esto es el campo clave primaria
		@Id
		Integer codigo;
		String descripci�n;
		Integer cantidad;
		
		@ManyToMany(fetch=FetchType.LAZY, mappedBy="premios")
		private Set<Ciclista>ciclistas =new HashSet<>();
		
		
		public Premios(Integer codigo, String descripci�n, Integer cantidad) {
			super();
			this.codigo = codigo;
			this.descripci�n = descripci�n;
			this.cantidad = cantidad;
		}


		public Premios() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Integer getCodigo() {
			return codigo;
		}


		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}


		public String getDescripci�n() {
			return descripci�n;
		}


		public void setDescripci�n(String descripci�n) {
			this.descripci�n = descripci�n;
		}


		public Integer getCantidad() {
			return cantidad;
		}


		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}


		public Set<Ciclista> getCiclistas() {
			return ciclistas;
		}


		public void setCiclistas(Set<Ciclista> ciclistas) {
			this.ciclistas = ciclistas;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
			result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
			result = prime * result + ((descripci�n == null) ? 0 : descripci�n.hashCode());
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
			Premios other = (Premios) obj;
			if (cantidad == null) {
				if (other.cantidad != null)
					return false;
			} else if (!cantidad.equals(other.cantidad))
				return false;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			if (descripci�n == null) {
				if (other.descripci�n != null)
					return false;
			} else if (!descripci�n.equals(other.descripci�n))
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "Premio [codigo=" + codigo + ", descripci�n=" + descripci�n + ", cantidad=" + cantidad + "]";
		} 
		 
}

