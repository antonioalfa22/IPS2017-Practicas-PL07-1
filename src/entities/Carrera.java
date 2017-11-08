/**
 * 
 */
package entities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import gestorBBDD.GestorDB;
import logic.Categoria;
import logic.Date;
import logic.FechaInscripcion;
import logic.GestorApp;

/**
 * Clase que representa una carrera
 * @author Antonio Paya Gonzalez
 *
 */
public class Carrera implements Comparable<Carrera>{
	
	private String nombre,lugar,dureza,tipo,num_cuenta,fecha,dni_creador;
	private int id,num_max_part,distancia,edad_minima;
	private ArrayList<Corredor> atletas;
	private ArrayList<FechaInscripcion> fechas_inscripcion;
	private ArrayList<Categoria> categorias;
	
	/**
	 * Constructor con parametros de la clase
	 * @param nombre
	 * @param lugar
	 * @param fecha
	 * @param num_max_part
	 * @param precio
	 * @param fecha_inscripcion
	 * @param distancia
	 * @param dureza
	 * @param edad_minima
	 * @param tipo
	 * @param num_cuenta
	 * @param dni_creador
	 */
	public Carrera(int id,String nombre,String lugar,String fecha,int num_max_part,
			int distancia,String dureza,int edad_minima,String tipo,String num_cuenta,String dni_creador,
			ArrayList<FechaInscripcion> fechas,ArrayList<Categoria> cats) {
		setId(id);
		setNombre(nombre);
		setLugar(lugar);
		setFecha(fecha);
		setNum_max_part(num_max_part);
		setDistancia(distancia);
		setDureza(dureza);
		setEdad_minima(edad_minima);
		setTipo(tipo);
		setNum_cuenta(num_cuenta);
		setDni_creador(dni_creador);
		setFechas_inscripcion(fechas);
		setCategorias(cats);
		atletas = new ArrayList<Corredor>();
	}
	
	
	
	
	/**
	 * @return the categorias
	 */
	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}




	/**
	 * @param categorias the categorias to set
	 */
	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}




	/**
	 * @return the fechas_inscripcion
	 */
	public ArrayList<FechaInscripcion> getFechas_inscripcion() {
		return fechas_inscripcion;
	}



	/**
	 * @param fechas_inscripcion the fechas_inscripcion to set
	 */
	public void setFechas_inscripcion(ArrayList<FechaInscripcion> fechas_inscripcion) {
		this.fechas_inscripcion = fechas_inscripcion;
	}



	/**
	 * 
	 * @return
	 */
	public boolean isFinalizada() {
		Calendar f = new GregorianCalendar();
		String fecha_a = f.get(Calendar.DAY_OF_MONTH) + "/" + (f.get(Calendar.MONTH) + 1) + "/"
				+ f.get(Calendar.YEAR);
		Date fecha_actual = new Date(fecha_a);
		Date fecha_fin = new Date(fecha);
		fecha_fin.day = fecha_fin.day+1;
		return fecha_actual.compareTo(fecha_fin) > 0 ? true: false;
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
	 * @return the lugar
	 */
	public String getLugar() {
		return lugar;
	}

	/**
	 * @param lugar the lugar to set
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	/**
	 * @return the dureza
	 */
	public String getDureza() {
		return dureza;
	}

	/**
	 * @param dureza the dureza to set
	 */
	public void setDureza(String dureza) {
		this.dureza = dureza;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the num_cuenta
	 */
	public String getNum_cuenta() {
		return num_cuenta;
	}

	/**
	 * @param num_cuenta the num_cuenta to set
	 */
	public void setNum_cuenta(String num_cuenta) {
		this.num_cuenta = num_cuenta;
	}

	/**
	 * @return the num_max_part
	 */
	public int getNum_max_part() {
		return num_max_part;
	}

	/**
	 * @param num_max_part the num_max_part to set
	 */
	public void setNum_max_part(int num_max_part) {
		this.num_max_part = num_max_part;
	}

	/**
	 * @return the distancia
	 */
	public int getDistancia() {
		return distancia;
	}

	/**
	 * @param distancia the distancia to set
	 */
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	/**
	 * @return the edad_minima
	 */
	public int getEdad_minima() {
		return edad_minima;
	}

	/**
	 * @param edad_minima the edad_minima to set
	 */
	public void setEdad_minima(int edad_minima) {
		this.edad_minima = edad_minima;
	}

	/**
	 * @return the dni_creador
	 */
	public String getDni_creador() {
		return dni_creador;
	}

	/**
	 * @param dni_creador the dni_creador to set
	 */
	public void setDni_creador(String dni_creador) {
		this.dni_creador = dni_creador;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public Date getFechaFormateada() {
		return new Date(fecha);
	}
	
	/**
	 * Ordena a los atletas por fecha de inscripción. 
	 */
	public void ordenarAtletas() {
		Collections.sort(atletas);
	}

	/**
	 * Asigna un dorsal a cada atleta en función de la fecha de inscripción.Esta 
	 * asignación de número de dorsal se realiza en orden creciente respecto a la fecha de inscripción, 
	 * y se dejan los diez primeros números sin asignar para atender a posibles compromisos.
	 */
	public void asignaDorsal() {
		ordenarAtletas();
		int cont = 10;
		for (Corredor a : atletas) {
			a.setDorsal(cont);
			cont++;
		}
	}

	public void muestraAtletas() {
		for (Corredor a : atletas) {
			System.out.println(a.toString());
		}
	}
	
	public double getPrecio() {
		return getFechaInscripcionActual().getPrecio();
	}
	
	public FechaInscripcion getFechaInscripcionActual() {
		Calendar fecha = new GregorianCalendar();
		String ffaa = fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
		Date fActual = new Date(ffaa);
		Collections.sort(fechas_inscripcion);
		for (FechaInscripcion f : fechas_inscripcion) {
			Date fPrueba = new Date(f.getFecha());
			Date fPruebaFinal = new Date(f.getFechaFin());
			if(fActual.compareTo(fPrueba) >= 0 && fActual.compareTo(fPruebaFinal) <= 0)
				return f;
		}
		return fechas_inscripcion.get(fechas_inscripcion.size()-1);
	}
	
	public String getCategoriaParaUsuario(int edad) {
		for (Categoria cat : categorias) {
			if(edad >= cat.getEdadMin() && edad < cat.getEdadMax()) {
				return cat.getNombre();
			}
		}
		return "Sin categoria";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombre+"  Lugar:"+lugar;
	}

	@Override
	public int compareTo(Carrera a2) {
		int dia = Integer.parseInt(this.getFecha().split("/")[0]);
		int mes = Integer.parseInt(this.getFecha().split("/")[1]);
		int año = Integer.parseInt(this.getFecha().split("/")[2]);
		
		int diaA2 = Integer.parseInt(a2.getFecha().split("/")[0]);
		int mesA2 = Integer.parseInt(a2.getFecha().split("/")[1]);
		int añoA2 = Integer.parseInt(a2.getFecha().split("/")[2]);
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
	
	public void actualizaAtletas() {
		atletas = (ArrayList<Corredor>) GestorApp.getTodosLosCorredores(this);
	}
	/**
	 * Asigna un dorsal a cada atleta en función de la fecha de inscripción.Esta 
	 * asignación de número de dorsal se realiza en orden creciente respecto a la fecha de inscripción, 
	 * y se dejan los diez primeros números sin asignar para atender a posibles compromisos.
	 * @throws SQLException 
	 */
	public void asignaDorsales() throws SQLException {
		ordenarAtletas();
		int cont = 10;
		for (Corredor c : atletas) {
			c.setDorsal(cont);
			GestorDB.updateDorsal(c,cont);
			cont++;
		}
		
	}
	

}
