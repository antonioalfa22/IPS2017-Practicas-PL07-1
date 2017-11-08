/**
 * 
 */
package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.Carrera;
import entities.Corredor;
import entities.Preinscrito;
import entities.Usuario;
import gestorBBDD.GestorDB;

/**
 * Clase que hace de interfaz para hacer modificaciones en la base de datos
 * 
 * @author Antonio Paya Gonzalez
 *
 */
public class GestorApp {

	public ArrayList<Carrera> carreras;
	public ArrayList<Usuario> usuarios;
	public ArrayList<Corredor> corredores;
	public ArrayList<Preinscrito> preinscritos;
	public ArrayList<Inscrito> inscritos;
	public Carrera carrera;
	private Usuario usuarioActivo;

	/**
	 * Constructor de la clase
	 */
	public GestorApp() {
		try {
			carreras = GestorDB.sacarTodasLasCarreras();
			Collections.sort(carreras);
		} catch (SQLException e) {
			System.out
					.println("Error al sacar las carreras de la base de datos");
			e.printStackTrace();
		}
		try {
			usuarios = GestorDB.sacaTodosLosUsuarios();

		} catch (SQLException e) {
			System.out
					.println("Error al sacar los usuarios de la base de datos");
			e.printStackTrace();
		}
		usuarioActivo = null;
	}

	/**
	 * @return the usuarioActivo
	 */
	public Usuario getUsuarioActivo() {
		return usuarioActivo;
	}

	/**
	 * @param usuarioActivo
	 *            the usuarioActivo to set
	 */
	public void setUsuarioActivo(Usuario usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

	/**
	 * @return the carreras
	 */
	public ArrayList<Carrera> getCarreras() {
		return carreras;
	}

	/**
	 * @param carreras
	 *            the carreras to set
	 */
	public void setCarreras(ArrayList<Carrera> carreras) {
		this.carreras = carreras;
	}

	/**
	 * @return the usuarios
	 */
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios
	 *            the usuarios to set
	 */
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * @param c
	 */
	public void addCarrera(Carrera c) {
		try {
			GestorDB.addCarrera(c);
		} catch (SQLException e) {
			System.out.println("Error meter una carrera en la base de datos");
			e.printStackTrace();
		}
		carreras.add(c);
	}

	/**
	 * @param c
	 */
	public void addUsuario(Usuario u) {
		try {
			GestorDB.addUsuario(u);
		} catch (SQLException e) {
			System.out.println("Error meter un usuario en la base de datos");
			e.printStackTrace();
		}
		usuarios.add(u);
	}

	/**
	 * Metodo que a�ade un usuario a una carrera
	 * 
	 * @param u
	 * @param c
	 */
	public void addPreeinscrito(Usuario u, Carrera c, String fecha) {
		try {
			GestorDB.addPreeinscrito(u, c, fecha);
		} catch (SQLException e) {
			System.out
					.println("Error meter un preeinscrito en la base de datos");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que devuelve true si un usuarios esta preinscrito en una carrera
	 * 
	 * @param u
	 * @param c
	 * @return
	 */
	public boolean isPreeinscrito(String d, Carrera c) {
		boolean preinscrito = false;
		try {
			ArrayList<String> dnis = GestorDB
					.sacaTodosLosDNIPreinscritosEnCarrera(c);
			for (String dni : dnis) {
				if (dni.equals(d))
					preinscrito = true;
			}
		} catch (SQLException e) {
			System.out
					.println("Error meter un preeinscrito en la base de datos");
			e.printStackTrace();
		}
		return preinscrito;
	}

	/**
	 * Metodo que devuelve el numero de preinscritos en una carrera
	 * 
	 * @param c
	 * @return
	 */
	public int getNumPreinscritos(Carrera c) {
		int num = 0;
		try {
			num = GestorDB.sacaTodosLosDNIPreinscritosEnCarrera(c).size();
		} catch (SQLException e) {
			System.out
					.println("Error al sacar los un preeinscritos des la base de datos");
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * Devuelve true si el usuario esta inscrito o preinscrito en la carrera
	 * 
	 * @param u
	 * @param c
	 * @return
	 */
	public boolean isUsuarioRegistradoInCarrera(Usuario u, Carrera c) {
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		try {
			carreras = GestorDB.findCarrerasDeUnUsuario(u);
		} catch (SQLException e) {
			System.out.println("Error al sacar las carreras de un usuario");
			e.printStackTrace();
		}
		for (Carrera carrera : carreras) {
			if (carrera.getId() == c.getId())
				return true;
		}
		return false;
	}

	/**
	 * Metodo que devuelve todos los preinscritos en una carrera
	 * 
	 * @param c
	 * @return
	 */
	public List<Preinscrito> getTodosLosPreinscritos(Carrera c) {
		try {
			return GestorDB.findInscritosByIdCarrera(c.getId());
		} catch (SQLException e) {
			System.out.println("Error al sacar los preinscritos");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo que devuelve todos los corredores de una carrera
	 * 
	 * @param c
	 * @return
	 */
	public static List<Corredor> getTodosLosCorredores(Carrera c) {
		try {

			return GestorDB.findCorredoresByIdCarrera(c.getId());

		} catch (SQLException e) {
			System.out.println("Error al sacar los preinscritos");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteCarrera(int id) {
		for (int i = 0; i < carreras.size(); i++) {
			if (carreras.get(i).getId() == id) {
				try {
					GestorDB.deleteCarrera(id);
				} catch (SQLException e) {
					System.out
							.println("Error al borrar una carrera de la base de datos");
					e.printStackTrace();
				}
				carreras.remove(i);
			}
		}
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteUsuario(String dni) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getDni() == dni) {
				try {
					GestorDB.deleteUsuario(dni);
				} catch (SQLException e) {
					System.out
							.println("Error al borrar un usuario de la base de datos");
					e.printStackTrace();
				}
				carreras.remove(i);
			}
		}
	}

	/**
	 * Actualiza el dato tiempos en el corredor que tenga el dorsal que se le
	 * pasa por par�metro
	 * 
	 * @param carrera
	 * @param dorsal
	 * @param tiempo
	 * @throws SQLException
	 */
	public void asignaTiempo(Carrera carrera, int dorsal, String tiempo)
			throws SQLException {
		List<Corredor> corredores;

		corredores = getTodosLosCorredores(carrera);
		for (Corredor c : corredores) {
			if (c.getDorsal() == dorsal) {
				GestorDB.updateTiempo(c, tiempo);
			}
		}

	}

	/**
	 * Devuelve si existe un  usuario en la DB con el mismo dni
	 * @param user
	 * @return
	 */
	public boolean existeUsuario(String dni) {
		for(Usuario u:usuarios) {
			if(u.getDni().equals(dni)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Devuelve el usuario que tiene como dni el pasado por par�metro
	 * @param dni
	 * @return
	 */
	public Usuario getUsuario(String dni) {
		for(Usuario u:usuarios) {
			if(u.getDni().equals(dni)){
				return u;
			}
		}
		return null;
		
	}

	// ---------------------------------------------------------

	/**
	 * @return the carreras ordenados por fecha de inscripcion
	 * @throws SQLException
	 */
	public ArrayList<Carrera> getCarrerasOrderByFecha() throws SQLException {
		carreras = GestorDB.sacarTodasLasCarreras();
		Collections.sort(carreras);
		return carreras;
	}

	/**
	 * @param idCarrera
	 * @return corredores de una carrera ordenados por tiempo
	 */
	public ArrayList<Corredor> getCorredores(Integer idCarrera) {
		try {
			corredores = GestorDB
					.findCorredoresByIdCarreraOrderByTiempo(idCarrera);

		} catch (SQLException e) {
			System.out
					.println("Error al sacar los corredores de la base de datos");
			e.printStackTrace();
		}

		return corredores;
	}

	/**
	 * @param idCarrera
	 * @param genero
	 * @return corredores de una carrera, de un genero pasado por parametro
	 *         ordenados por tiempo
	 */
	public ArrayList<Corredor> getCorredoresByGenero(Integer idCarrera,
			String genero) {
		try {
			corredores = GestorDB
					.findCorredoresByIdCarreraOrderByTiempoByGenero(idCarrera,
							genero);

		} catch (SQLException e) {
			System.out
					.println("Error al sacar los corredores de la base de datos");
			e.printStackTrace();
		}

		return corredores;
	}

	/**
	 * @param idCarrera
	 * @param genero
	 * @param categoria
	 * @return corredores de la carrera, del genero y de la categoria pasada por
	 *         parametro ordenados por tiempo.
	 */
	public ArrayList<Corredor> getCorredoresByGeneroByCategoria(
			Integer idCarrera, String genero, String categoria) {
		try {
			corredores = GestorDB
					.findCorredoresByIdCarreraOrderByTiempoByGeneroByCategoria(
							idCarrera, genero, categoria);

		} catch (SQLException e) {
			System.out
					.println("Error al sacar los corredores de la base de datos");
			e.printStackTrace();
		}

		return corredores;
	}

	/**
	 * @param idCarrera
	 * @return corredores de una carrera ordenados por categor�a y por tiempo.
	 */
	public ArrayList<Corredor> getCorredoresTodasCategorias(Integer idCarrera) {
		try {
			corredores = GestorDB
					.findCorredoresByIdCarreraOrderByTiempoByCategoria(idCarrera);

		} catch (SQLException e) {
			System.out
					.println("Error al sacar los corredores de la base de datos");
			e.printStackTrace();
		}

		return corredores;
	}

	/**
	 * @param idCarrera
	 * @param genero
	 * @return corredores de una carrera con un genero pasado por parametro
	 *         ordenados por categor�a y por tiempo.
	 */
	public ArrayList<Corredor> getCorredoresTodasCategoriasByGenero(
			Integer idCarrera, String genero) {
		try {
			corredores = GestorDB
					.findCorredoresByIdCarreraOrderByTiempoByCategoriaByGenero(
							idCarrera, genero);

		} catch (SQLException e) {
			System.out
					.println("Error al sacar los corredores de la base de datos");
			e.printStackTrace();
		}

		return corredores;
	}

	/**
	 * @param idCarrera
	 * @param categoria
	 * @return corredores de la carrera y de la categoria pasada por parametro
	 *         ordenados por tiempo.
	 */
	public ArrayList<Corredor> getCorredoresTodasCategoriasGeneroT(
			Integer idCarrera, String categoria) {
		try {
			corredores = GestorDB
					.findCorredoresByIdCarreraOrderByTiempoByCategoria(
							idCarrera, categoria);

		} catch (SQLException e) {
			System.out
					.println("Error al sacar los corredores de la base de datos");
			e.printStackTrace();
		}

		return corredores;
	}

	/**
	 * @param idCarrera
	 * @return inscritos de una carrera, es decir, los que ya han pagado y los
	 *         que a�n no han pagado.
	 */
	public ArrayList<Inscrito> getInscritosByIdCarrera(Integer idCarrera) {
		try {
			inscritos = GestorDB.findInscritosByIdCarrera(idCarrera, idCarrera);

		} catch (SQLException e) {
			System.out
					.println("Error al sacar los inscritos de la base de datos");
			e.printStackTrace();
		}

		return inscritos;
	}

}