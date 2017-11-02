/**
 * 
 */
package logic;

/**
 * Clase que simula una categoria de una competicion
 * @author Antonio Paya Gonzalez
 *
 */
public class Categoria implements Comparable<Categoria>{
	
	private int edadMin,edadMax;
	private String nombre;

	/**
	 * 
	 */
	public Categoria(int emin,int emax,String nombre) {
		setEdadMin(emin);
		setEdadMax(emax);
		setNombre(nombre);
	}
	
	

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the edadMin
	 */
	public int getEdadMin() {
		return edadMin;
	}

	/**
	 * @param edadMin the edadMin to set
	 */
	public void setEdadMin(int edadMin) {
		this.edadMin = edadMin;
	}

	/**
	 * @return the edadMax
	 */
	public int getEdadMax() {
		return edadMax;
	}

	/**
	 * @param edadMax the edadMax to set
	 */
	public void setEdadMax(int edadMax) {
		this.edadMax = edadMax;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombre + "["+edadMin+","+edadMax+")";
	}



	@Override
	public int compareTo(Categoria o) {
		return this.edadMin - o.edadMin;
	}
	
	
	

}
