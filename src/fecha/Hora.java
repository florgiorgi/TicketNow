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
	public String toString() {
		return this.horas.toString() + ":" + this.minutos.toString();
	}
}
