package logic;

import java.util.Calendar;

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

	
	public Time suma(Time tiempo){
		int h=tiempo.hour;
		int m=tiempo.minute;
		Time time = new Time(0,0,0);
		
		if(this.minute+m >59) {
			int suma=(this.minute+m)-60;
			time.minute=suma;
			time.hour++;
			time.hour+=tiempo.hour;
		}else {
			time.minute=this.minute+tiempo.minute;
			time.hour=this.hour+tiempo.hour;
		}
		return time;
		
	}
}
