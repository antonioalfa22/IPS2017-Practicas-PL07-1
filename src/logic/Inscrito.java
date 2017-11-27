package logic;

public class Inscrito {
	
	private String DNI;
	private String nombre;
	private String categoria;
	private String fechaInscripcion;
	private String pagado;
	
	
	/**
	 * @param dNI
	 * @param nombre
	 * @param categoria
	 * @param fechaInscripcion
	 * @param pagado
	 */
	public Inscrito(String dNI, String nombre, String categoria,
			String fechaInscripcion, String pagado) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.categoria = categoria;
		this.fechaInscripcion = fechaInscripcion;
		this.pagado = pagado;
	}


	/**
	 * @return the dNI
	 */
	public String getDNI() {
		return DNI;
	}


	/**
	 * @param dNI the dNI to set
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
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
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}


	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	/**
	 * @return the fechaInscripcion
	 */
	public String getFechaInscripcion() {
		return fechaInscripcion;
	}


	/**
	 * @param fechaInscripcion the fechaInscripcion to set
	 */
	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}


	/**
	 * @return the pagado
	 */
	public String getPagado() {
		return pagado;
	}


	/**
	 * @param pagado the pagado to set
	 */
	public void setPagado(String pagado) {
		this.pagado = pagado;
	}
	
	
	
	

}
