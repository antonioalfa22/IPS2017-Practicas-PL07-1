/**
 * 
 */
package logic;

/**
 * @author Antonio
 *
 */
public class FechaInscripcion implements Comparable<FechaInscripcion>{
	private String fechaInicio,fechaFin;
	private double precio;
	private int diaInicio,mesInicio,yearInicio,diaFin,mesFin,yearFin;

	/**
	 * 
	 */
	public FechaInscripcion(String fecha,String fechaFin,double precio) {
		setFecha(fecha);
		setFechaFin(fechaFin);
		setPrecio(precio);
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
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
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
		return fechaInicio+"-"+fechaFin+" Precio: "+precio+"euros";
	}

	@Override
	public int compareTo(FechaInscripcion o) {
		Date f1 = new Date(this.fechaInicio);
		Date f2 = new Date(o.fechaInicio);
		return f1.compareTo(f2);
	}
	
	
	

}
