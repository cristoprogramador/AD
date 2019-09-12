package pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="puerto")
public class Puerto implements Serializable{		
		//en la bd la tabla es con minuscula pero en Java la clase empieza por mayuscula
		//cambiaremos la referencia mas adelante
		
		@Id
		private String nompuerto;
		private Integer altura;
		private String categoria;
		private Double pendiente;

		
		//las claves ajenas no se ponen, se indican a continuacion como relaciones
		@ManyToOne(fetch=FetchType.LAZY) 	//solo se recuperan los datos cuando se accede a ellos
		@JoinColumn(name="dorsal")
		private Ciclista ciclista;		//Este lo mapeamos en la clase Equipo
		
		@ManyToOne(fetch=FetchType.LAZY) //solo se recuperan los datos cuando se accede a ellos
		@JoinColumn(name="netapa") //netapa hace referencia al nombre de la columna de la BD
		private Etapa obj_etapa; //Este lo mapeamos en la clase Equipo
		
		public Puerto(){
			
		}

		public Puerto(String nompuerto, Integer altura, String categoria, Double pendiente) {
			super();
			this.nompuerto = nompuerto;
			this.altura = altura;
			this.categoria = categoria;
			this.pendiente = pendiente;
		}

		public Etapa getEtapa() {
			return obj_etapa;
		}

		public void setEtapa(Etapa etapa) {
			this.obj_etapa = etapa;
		}

		/**
		 * @return the nompuerto
		 */
		public String getNompuerto() {
			return nompuerto;
		}

		/**
		 * @param nompuerto the nompuerto to set
		 */
		public void setNompuerto(String nompuerto) {
			this.nompuerto = nompuerto;
		}

		/**
		 * @return the altura
		 */
		public Integer getAltura() {
			return altura;
		}

		/**
		 * @param altura the altura to set
		 */
		public void setAltura(Integer altura) {
			this.altura = altura;
		}

		/**
		 * @return the categoria
		 */
		public String getCategoria() {
			return categoria;
		}

		/**
		 * @param categoria the categoria to set
		 */
		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		/**
		 * @return the pendiente
		 */
		public Double getPendiente() {
			return pendiente;
		}

		/**
		 * @param pendiente the pendiente to set
		 */
		public void setPendiente(Double pendiente) {
			this.pendiente = pendiente;
		}

		/**
		 * @return the ciclista
		 */
		public Ciclista getCiclista() {
			return ciclista;
		}

		/**
		 * @param ciclista the ciclista to set
		 */
		public void setCiclista(Ciclista ciclista) {
			this.ciclista = ciclista;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((altura == null) ? 0 : altura.hashCode());
			result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
			result = prime * result + ((ciclista == null) ? 0 : ciclista.hashCode());
			result = prime * result + ((nompuerto == null) ? 0 : nompuerto.hashCode());
			result = prime * result + ((pendiente == null) ? 0 : pendiente.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Puerto other = (Puerto) obj;
			if (altura == null) {
				if (other.altura != null)
					return false;
			} else if (!altura.equals(other.altura))
				return false;
			if (categoria == null) {
				if (other.categoria != null)
					return false;
			} else if (!categoria.equals(other.categoria))
				return false;
			if (ciclista == null) {
				if (other.ciclista != null)
					return false;
			} else if (!ciclista.equals(other.ciclista))
				return false;
			if (nompuerto == null) {
				if (other.nompuerto != null)
					return false;
			} else if (!nompuerto.equals(other.nompuerto))
				return false;
			if (pendiente == null) {
				if (other.pendiente != null)
					return false;
			} else if (!pendiente.equals(other.pendiente))
				return false;
			return true;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Puerto [nompuerto=" + nompuerto + ", altura=" + altura + ", categoria=" + categoria + ", pendiente="
					+ pendiente + ", ciclista=" + ciclista + "]";
		}
		
}

