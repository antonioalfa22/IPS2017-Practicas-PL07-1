/**
 * 
 */
package logic;

/**
 * Clase de utilidad que permite hacer comparaciones entre dos fechas
 * 
 * @author Antonio Payá González
 *
 */
public class Date implements Comparable<Date>{

	public int year,month,day;
	/**
	 * Constructor de la clase dandole como parametros el año,mes y dia
	 */
	public Date(int year,int month,int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	/**
	 * Constructor de la clase que la crea dandole por parametro
	 * un String en formato dd/mm/yyyy
	 * @param date
	 */
	public Date(String date) {
		String[] datos = date.split("/");
		this.year = Integer.parseInt(datos[2]);
		this.month = Integer.parseInt(datos[1]);
		this.day = Integer.parseInt(datos[0]);
	}
	
	/**
	 * Devuelve > 0 si la fecha metida por parametro es menor
	 */
	@Override
	public int compareTo(Date o) {
		if(o.year > year) return -1;
		if(o.year == year) {
			if(o.month > month) return -1;
			if(o.month == month) {
				if(o.day > day) return -1;
				if(o.day == day) return 0;
			}
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return day+"/"+month+"/"+year;
	}
	
	

}
