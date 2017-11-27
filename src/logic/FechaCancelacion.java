/**
 * 
 */
package logic;

/**
 * @author Antonio
 *
 */
public class FechaCancelacion implements Comparable<FechaCancelacion>{
	private String fechaInicio,fechaFin;
	private double aDevolver;
	private int diaInicio,mesInicio,yearInicio,diaFin,mesFin,yearFin;

	/**
	 * 
	 */
	public FechaCancelacion(String fecha,String fechaFin,double aDevolver) {
		setFecha(fecha);
		setFechaFin(fechaFin);
		setADevolver(aDevolver);
	}

	/**
	 * @return the diaFin
	 */
	public int getDiaFin() {
		return diaFin;
	}

	/**
	 * @return the mesFin
	 */
	public int getMesFin() {
		return mesFin;
	}

	/**
	 * @return the yearFin
	 */
	public int getYearFin() {
		return yearFin;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		String[] fechas = fechaFin.split("/");
		diaFin = Integer.parseInt(fechas[0]);
		mesFin = Integer.parseInt(fechas[1]);
		yearFin = Integer.parseInt(fechas[2]);
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fechaInicio;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		String[] fechas = fecha.split("/");
		diaInicio = Integer.parseInt(fechas[0]);
		mesInicio = Integer.parseInt(fechas[1]);
		yearInicio = Integer.parseInt(fechas[2]);
		this.fechaInicio = fecha;
	}

	/**
	 * @return the precio
	 */
	public double getADevolver() {
		return aDevolver;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setADevolver(double precio) {
		this.aDevolver = precio;
	}

	/**
	 * @return the dia
	 */
	public int getDia() {
		return diaInicio;
	}
	/**
	 * @return the mes
	 */
	public int getMes() {
		return mesInicio;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return yearInicio;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return fechaInicio+"-"+fechaFin+" A devolver: "+aDevolver+"%";
	}

	@Override
	public int compareTo(FechaCancelacion o) {
		Date f1 = new Date(this.fechaInicio);
		Date f2 = new Date(o.fechaInicio);
		return f1.compareTo(f2);
	}
	
	
	

}
