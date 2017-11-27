/**
 * 
 */
package logic;

/**
 * Clase que representa un punto de control de la carrera
 * @author Antonio
 *
 */
public class PuntoControl {
	
	private int km;
	private int horas;
	private int min;
	
	/**
	 * @param km
	 * @param horas
	 * @param min
	 */
	public PuntoControl(int km, int horas, int min) {
		this.km = km;
		this.horas = horas;
		this.min = min;
	}
	/**
	 * @return the km
	 */
	public int getKm() {
		return km;
	}
	/**
	 * @param km the km to set
	 */
	public void setKm(int km) {
		this.km = km;
	}
	/**
	 * @return the horas
	 */
	public int getHoras() {
		return horas;
	}
	/**
	 * @param horas the horas to set
	 */
	public void setHoras(int horas) {
		this.horas = horas;
	}
	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PuntoControl [km=" + km + ", Tiempo maximo=" + horas + ":" + min + "]";
	}
	

}
