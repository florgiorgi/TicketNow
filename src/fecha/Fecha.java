package fecha;

public class Fecha {

	private Integer dia;
	private Integer mes;
	private Integer a�o;
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

	
	public Fecha(Integer dia, Integer mes, Integer a�o) {
		if(dia == null || mes == null || a�o == null)
		if(dia < 0 || mes < 0 || mes > 12 || a�o < 2017)
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
		this.a�o = a�o;
	}
	
	public int getDia() {
		return this.dia;
	}
	
	public int getMes() {
		return this.mes;
	}
	
	public int getA�o() {
		return this.a�o;
	}
	
	@Override
	public String toString() {
		return this.dia.toString() + "/" + this.mes.toString() + "/" + this.a�o.toString();
	}
	
}
