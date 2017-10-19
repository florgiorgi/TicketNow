package fecha;

public class Hora {

	private Integer horas;
	private Integer minutos;
	
	public Hora(Integer horas, Integer minutos) {
		if(horas == null || minutos == null)
			throw new IllegalArgumentException();
		if(horas < 0 || horas > 24 || minutos < 0 || minutos > 59)
			throw new IllegalArgumentException();
		this.horas = horas;
		this.minutos = minutos;
	}
	
	public Integer getHoras() {
		return this.horas;
	}
	
	public Integer getMinutos() {
		return this.minutos;
	}
	
	public Integer getHora() {
		return this.horas*100 + this.minutos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horas == null) ? 0 : horas.hashCode());
		result = prime * result + ((minutos == null) ? 0 : minutos.hashCode());
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
		Hora other = (Hora) obj;
		if (horas == null) {
			if (other.horas != null)
				return false;
		} else if (!horas.equals(other.horas))
			return false;
		if (minutos == null) {
			if (other.minutos != null)
				return false;
		} else if (!minutos.equals(other.minutos))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.horas.toString() + ":" + this.minutos.toString();
	}
}
