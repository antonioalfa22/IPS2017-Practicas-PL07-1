/**
 * 
 */
package entities;

/**
 * Clase que representa un club de atletismo
 * @author Antonio Paya Gonzalez
 *
 */
public class Club {
	
	private int id;
	private String nombre,dir;

	/**
	 * Constructor de la clase club
	 * @param id
	 * @param nombre
	 * @param dir
	 */
	public Club(int id,String nombre,String dir) {
		setId(id);
		setNombre(nombre);
		setDir(dir);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Club: " + nombre + ", Direccion: " + dir;
	}

	
}
