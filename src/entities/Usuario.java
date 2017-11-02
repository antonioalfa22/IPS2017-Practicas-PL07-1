package entities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import gestorBBDD.GestorDB;

/**
 * Clase que representa un usuario que se registra en la aplicacion
 * 
 * @author Antonio Payá y Mirza Ojeda
 *
 */
public class Usuario{

	// Atributos del atleta
	private String dni,nombre,fecha_nacimiento,direccion,localidad,codigo_postal,correo,contra;
	private String genero;
	private int telefono;

	
	public Usuario(String dni,String nombre,String fecha,String dir,int telefono,String localidad,String cp,
			String correo,String contra,String genero) {
		setDni(dni);
		setNombre(nombre);
		setFecha_nacimiento(fecha);
		setDireccion(dir);
		setLocalidad(localidad);
		setCodigo_postal(cp);
		setTelefono(telefono);
		setCorreo(correo);
		setContra(contra);
		setGenero(genero);
	}
	
	public Usuario(String dni,String nombre,String fecha,int telefono,String correo,String genero) {
		setDni(dni);
		setNombre(nombre);
		setFecha_nacimiento(fecha);
		setDireccion("");
		setLocalidad("");
		setCodigo_postal("");
		setTelefono(telefono);
		setCorreo(correo);
		setContra("");
		setGenero(genero);
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
	 * @return the fecha_nacimiento
	 */
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}



	/**
	 * @param fecha_nacimiento the fecha_nacimiento to set
	 */
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}



	/**
	 * @return the direrccion
	 */
	public String getDireccion() {
		return direccion;
	}



	/**
	 * @param direrccion the direrccion to set
	 */
	public void setDireccion(String direrccion) {
		this.direccion = direrccion;
	}



	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}



	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}



	/**
	 * @return the codigo_postal
	 */
	public String getCodigo_postal() {
		return codigo_postal;
	}



	/**
	 * @param codigo_postal the codigo_postal to set
	 */
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}



	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}



	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}



	/**
	 * @return the contra
	 */
	public String getContra() {
		return contra;
	}



	/**
	 * @param contra the contra to set
	 */
	public void setContra(String contra) {
		this.contra = contra;
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
	 * @return the telefono
	 */
	public int getTelefono() {
		return telefono;
	}



	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	/**
	 * Método privado auxiliar destinado al cálculo de la edad del atleta en función
	 * de la fecha actual y su fecha de nacimiento. La fecha actual es un objeto
	 * Calendar y la fecha de nacimiento del atleta es un String con formato
	 * dd/mm/aaaa
	 * 
	 * @return edad del atleta
	 */
	public int getEdad() {
		// Fecha actual
		Calendar fecha_actual = Calendar.getInstance();
		// Fecha de nacimiento del atleta, en un array con formato ["dd","mm","aaaa"]
		String[] fecha = fecha_nacimiento.split("/");

		return fecha_actual.get(Calendar.YEAR) - Integer.parseInt(fecha[2]);
	}
	
	
	/**
	 * @return the carreras
	 */
	public ArrayList<Carrera> getCarreras() {
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		try {
			carreras = GestorDB.findCarrerasDeUnUsuario(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carreras;
	}

	
	/**
	 * Comprueba que el usuario esté inscrito o preinscrito en una determinada carrera
	 * @param c carrera
	 * @return true si está inscrito,false si está preinscrito
	 */
	public boolean isInscrito(Carrera c) {
		ArrayList<Corredor> corredores = new ArrayList<Corredor>();
		try {
			corredores = GestorDB.findCorredoresByIdCarrera(c.getId());
		} catch (SQLException e) {
			GestorDB.handleSQLException(e);
		}
		for(Corredor co:corredores) {
			if(this.getDni()==co.getDni()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Devuelve el corredor de una carrera determinada
	 * @param carrera
	 * @return corredor
	 */
	public Corredor getCorredor(Carrera c) {
		Corredor corredor = null;
		ArrayList<Corredor> corredores = new ArrayList<Corredor>();
		try {
			corredores = GestorDB.findCorredoresByIdCarrera(c.getId());
		} catch (SQLException e) {
			GestorDB.handleSQLException(e);
		}
		for(Corredor co:corredores) {
			if(this.getDni().equals(co.getDni())) {
				corredor=co;
			}
		}
		return corredor;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dni=" + dni + ", Nombre=" + nombre;
	}
	
	
	
	
}