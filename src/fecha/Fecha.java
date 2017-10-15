package fecha;

public class Fecha {

	private Integer dia;
	private Integer mes;
	private Integer año;
	private static final int ENERO = 1;
	private static final int FEBRERO = 2;
	private static final int MARZO = 3;
//	private static final int ABRIL = 4;
	private static final int MAYO = 5;
//	private static final int JUNIO = 6;
	private static final int JULIO = 7;
	private static final int AGOSTO = 8;
//	private static final int SEPTIEMBRE = 9;
	private static final int OCTUBRE = 10;
//	private static final int NOVIEMBRE = 11;
	private static final int DICIEMBRE = 12;

	
	public Fecha(Integer dia, Integer mes, Integer año) {
		if(dia == null || mes == null || año == null)
		if(dia < 0 || mes < 0 || mes > 12 || año < 1930)
			throw new IllegalArgumentException();
		if(mes == ENERO || mes == MARZO || mes == MAYO || mes == JULIO || mes == AGOSTO || mes == OCTUBRE || mes == DICIEMBRE) {
			if(dia > 31)
				throw new IllegalArgumentException();
		} else if(mes == FEBRERO) {
			if(dia > 28)
				throw new IllegalArgumentException();
		} else {
			if(dia > 30)
				throw new IllegalArgumentException();
		}
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}
	
	public int getDia() {
		return this.dia;
	}
	
	public int getMes() {
		return this.mes;
	}
	
	public int getAño() {
		return this.año;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((año == null) ? 0 : año.hashCode());
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
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
		Fecha other = (Fecha) obj;
		if (año == null) {
			if (other.año != null)
				return false;
		} else if (!año.equals(other.año))
			return false;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.dia.toString() + "/" + this.mes.toString() + "/" + this.año.toString();
	}
	
}
