/**
 * 
 */
package entities;

import java.util.ArrayList;
import java.util.Collections;
import logic.Date;

/**
 * Clase que representa una carrera
 * @author Antonio Paya Gonzalez , Pablo Menendez y Sara Grimaldos
 *
 */
public class Carrera implements Comparable<Carrera>{
	
	private String nombre,lugar,dureza,tipo,num_cuenta,fecha,fecha_inscripcion;
	private int id,num_max_part,precio,distancia,edad_minima,dni_creador;
	private ArrayList<Corredor> atletas;
	//Para saber si la carrera está finalizada o no
	private boolean finalizada;
	
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
	public Carrera(int id,String nombre,String lugar,String fecha,int num_max_part,int precio,String fecha_inscripcion,
			int distancia,String dureza,int edad_minima,String tipo,String num_cuenta,int dni_creador) {
		setId(id);
		setNombre(nombre);
		setLugar(lugar);
		setFecha(fecha);
		setNum_max_part(num_max_part);
		setPrecio(precio);
		setFecha_inscripcion(fecha_inscripcion);
		setDistancia(distancia);
		setDureza(dureza);
		setEdad_minima(edad_minima);
		setTipo(tipo);
		setNum_cuenta(num_cuenta);
		setDni_creador(dni_creador);
		atletas = new ArrayList<Corredor>();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isFinalizada() {
		return finalizada;
	}

	/**
	 * 
	 * @param finalizada
	 */
	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
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
	 * @return the precio
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(int precio) {
		this.precio = precio;
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
	public int getDni_creador() {
		return dni_creador;
	}

	/**
	 * @param dni_creador the dni_creador to set
	 */
	public void setDni_creador(int dni_creador) {
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

	/**
	 * @return the fecha_inscripcion
	 */
	public String getFecha_inscripcion() {
		return fecha_inscripcion;
	}

	/**
	 * @param fecha_inscripcion the fecha_inscripcion to set
	 */
	public void setFecha_inscripcion(String fecha_inscripcion) {
		this.fecha_inscripcion = fecha_inscripcion;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombre+" - "+lugar;
	}

	@Override
	public int compareTo(Carrera o) {
		Carrera a2 = o;
		
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
	
	
	

}
