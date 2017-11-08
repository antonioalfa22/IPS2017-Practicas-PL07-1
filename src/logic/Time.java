package logic;

/**
 * Clase de utilidad que permite hacer comparaciones entre dos tiempos
 * 
 * @author Sara Grimaldos Rodríguez
 *
 */

public class Time implements Comparable<Time>{

	public int hour, minute, second;
	/**
	 * Constructor de la clase dandole como parametros el horas, minutos y segundos
	 */
	public Time(int hour,int minute,int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	/**
	 * Constructor de la clase que la crea dandole por parametro
	 * un String en formato hh:mm:ss
	 * @param date
	 */
	public Time(String date) {
		String[] datos = date.split("/");
		this.hour = Integer.parseInt(datos[2]);
		this.minute = Integer.parseInt(datos[1]);
		this.second = Integer.parseInt(datos[0]);
	}
	
	/**
	 * Devuelve > 0 si el tiempo metido por parametro es menor
	 */
	@Override
	public int compareTo(Time o) {
		if(o.hour > hour) return -1;
		if(o.hour == hour) {
			if(o.minute > minute) return -1;
			if(o.minute == minute) {
				if(o.second > second) return -1;
				if(o.second == second) return 0;
			}
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return hour+":"+minute+":"+second;
	}
	
}
