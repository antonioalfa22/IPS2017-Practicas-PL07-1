package entities;

/**
 * Clase que representa a un atleta que ya ha pagado su inscripcion
 * y va a disputar la carrera.
 * 
 * @author Pablo Menendez y Sara Grimaldos
 *
 */
public class Corredor implements Comparable<Corredor>{

	private String dni;
	private String nombre;
	private String categoria;
	private String fechaInscripcion;
	private String genero;
	private String club;
	private String tiempo;
	private Integer dorsal,idCarrera;
	//Posiciones 
	private String posicionAbsoluta;
	private String posicionCategoria;
	
	
	/**
	 * @param dni
	 * @param idCarrera
	 * @param tiempo
	 * @param dorsal
	 * @param categoria
	 * @param genero
	 * @param nombre
	 * @param fechaInscripcion
	 */
	public Corredor(String dni, Integer idCarrera, String tiempo,
			Integer dorsal, String categoria, String genero, String nombre,
			String fechaInscripcion) {
		super();
		this.dni = dni;
		this.idCarrera = idCarrera;
		this.tiempo = tiempo;
		this.dorsal = dorsal;
		this.categoria = categoria;
		this.genero = genero;
		this.nombre = nombre;
		this.fechaInscripcion = fechaInscripcion;
		posicionAbsoluta="-";
		posicionCategoria="-";
	}

	public Corredor(String dni, Integer idCarrera, String tiempo,
			Integer dorsal, String categoria, String genero, String nombre,
			String fechaInscripcion,String club) {
		super();
		this.dni = dni;
		this.idCarrera = idCarrera;
		this.tiempo = tiempo;
		this.dorsal = dorsal;
		this.categoria = categoria;
		this.genero = genero;
		this.nombre = nombre;
		this.fechaInscripcion = fechaInscripcion;
		this.club=club;
		posicionAbsoluta="-";
		posicionCategoria="-";
	}
	
	


	/**
	 * @return the posicionAbsoluta
	 */
	public String getPosicionAbsoluta() {
		return posicionAbsoluta;
	}

	/**
	 * @param posicionAbsoluta set the posicionAbsoluta
	 */
	public void setPosicionAbsoluta(String posicionAbsoluta) {
		this.posicionAbsoluta = posicionAbsoluta;
	}

	/**
	 * @return the posicionCategoria
	 */
	public String getPosicionCategoria() {
		return posicionCategoria;
	}

	/**
	 * @param posicionCategoria set the posicionCategoria
	 */
	public void setPosicionCategoria(String posicionCategoria) {
		this.posicionCategoria = posicionCategoria;
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
	 * @return the tiempo
	 */
	public String getTiempo() {
		return tiempo;
	}
	/**
	 * @param tiempo the tiempo to set
	 */
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getFechaInscripcion() {
		return fechaInscripcion;
	}


	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	

	public Integer getDorsal() {
		return dorsal;
	}


	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}
	
	/**
	 * @return the club
	 */
	public String getClub() {
		return club;
	}


	/**
	 * @param club the club to set
	 */
	public void setClub(String club) {
		this.club = club;
	}
	
	
	
	public String toString() {
		return dni+" "+nombre+" "+categoria+" "+fechaInscripcion+" "+dorsal;
	}
	

	
	@Override
	/**
	 * Criterio de comparación
	 */
	public int compareTo(Corredor arg0) {
		Corredor a2 = arg0;
		
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
