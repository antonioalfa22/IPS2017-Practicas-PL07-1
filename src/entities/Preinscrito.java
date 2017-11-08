package entities;

/**
 * Clase que representa a un usuario que se ha inscrito en una carrera
 * pero que aun no ha pagado la cuota de inscripcion
 * 
 * @author Sara Grimaldos
 *
 */
public class Preinscrito implements Comparable<Preinscrito>{
	
	private String dni;
	private Integer idCarrera;
	private String categoria;
	private String genero;
	private String pagado;
	private String fechaInscripcion;
	private String nombre;
	/**
	 * 
	 */
	public Preinscrito() {
		super();
	}
	/**
	 * @param dni
	 * @param idCarrera
	 * @param categoria
	 * @param genero
	 * @param pagado
	 * @param fechaInscripcion
	 * @param nombre
	 */
	public Preinscrito(String dni, Integer idCarrera, String categoria,
			String genero, String pagado, String fechaInscripcion, String nombre) {
		super();
		this.dni = dni;
		this.idCarrera = idCarrera;
		this.categoria = categoria;
		this.genero = genero;
		this.pagado = pagado;
		this.fechaInscripcion = fechaInscripcion;
		this.nombre = nombre;
	}
	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * @return the idCarrera
	 */
	public Integer getIdCarrera() {
		return idCarrera;
	}
	/**
	 * @param idCarrera the idCarrera to set
	 */
	public void setIdCarrera(Integer idCarrera) {
		this.idCarrera = idCarrera;
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
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}
	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
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
	
	
	@Override
	public String toString() {
		return "Preinscrito [dni=" + dni + ", idCarrera=" + idCarrera
				+ ", categoria=" + categoria + ", genero=" + genero
				+ ", pagado=" + pagado + "]";
	}
	@Override
	public int compareTo(Preinscrito arg0) {
Preinscrito a2 = arg0;
		
		int dia = Integer.parseInt(this.getFechaInscripcion().split("/")[0]);
		int mes = Integer.parseInt(this.getFechaInscripcion().split("/")[1]);
		int año = Integer.parseInt(this.getFechaInscripcion().split("/")[2]);
		
		int diaA2 = Integer.parseInt(a2.getFechaInscripcion().split("/")[0]);
		int mesA2 = Integer.parseInt(a2.getFechaInscripcion().split("/")[1]);
		int añoA2 = Integer.parseInt(a2.getFechaInscripcion().split("/")[2]);
		
		if (año < añoA2) {
			return -1;
		} else if (año > añoA2) {
			return 1;
		} else {
			if (mes < mesA2) {
				return -1;
			} else if (mes > mesA2) {
				return 1;
			} else {
				if (dia < diaA2) {
					return -1;
				} else {
					return 1;
				}
			}
		}
	}


	
	

}
