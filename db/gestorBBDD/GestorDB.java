package gestorBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Carrera;
import entities.Corredor;
import entities.Preinscrito;
import entities.Usuario;
import logic.Categoria;
import logic.FechaInscripcion;

/**
 * Clase que accede a la base de datos
 * y tiene métodos para sacar y añadir datos
 * 
 * @author Antonio Paya Gonzalez
 *
 */
public class GestorDB {
	public static Connection conexion;
	
	/**
	 * Metodo que establece una conexion con la base
	 * de datos.
	 */
	private static void conectar() {
		try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexión de la base de datos");
        }
	}
	
	/**
	 * Metodo que cierra la base de datos
	 * (Debe de estar abierta)
	 * =======CERRAR SIEMPRE=======
	 */
	private static void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la base de datos");
		}
	}
	
	/**
	 * Metodo que devuelve un ArrayList con todas las carreras
	 * de la base de datos ordenadas por la fecha en la que se realizan
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Carrera> sacarTodasLasCarreras() throws SQLException {
		conectar();
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		PreparedStatement st = conexion.prepareStatement("SELECT * FROM Carrera order by Fecha ASC");
		ResultSet rs = st.executeQuery();
		while (rs.next()){
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			ArrayList<FechaInscripcion> fechas = new ArrayList<FechaInscripcion>();
			int id = rs.getInt("Id_Carrera");
			String nombre = rs.getString("Nombre");
			String lugar = rs.getString("Lugar");
			String fecha = rs.getString("Fecha");
			int num_max = rs.getInt("Num_max_part");
			int km = rs.getInt("Numero_km");
			String dureza = rs.getString("Dureza");
			int edad = rs.getInt("Edad_minima");
			String tipo = rs.getString("Tipo");
			String ncuenta = rs.getString("Numero_cuenta");
			String dni = rs.getString("DNI");
			PreparedStatement st2 = conexion.prepareStatement("SELECT * FROM CATEGORIA WHERE Id_Carrera = ?");
			st2.setInt(1, id);
			ResultSet rs2 = st2.executeQuery();
			while(rs2.next()) {
				String ncat = rs2.getString("Nombre");
				int nemin = rs2.getInt("Edad_minima");
				int nemax = rs2.getInt("Edad_maxima");
				categorias.add(new Categoria(nemin,nemax,ncat));
			}
			PreparedStatement st3 = conexion.prepareStatement("SELECT * FROM FECHA_INSCRIPCION WHERE Id_Carrera = ?");
			st3.setInt(1, id);
			ResultSet rs3 = st3.executeQuery();
			while(rs3.next()) {
				String fi = rs3.getString("Fecha_inicio");
				String ff = rs3.getString("Fecha_fin");
				int precio = rs3.getInt("Precio");
				fechas.add(new FechaInscripcion(fi,ff,precio));
			}
			Carrera c = new Carrera(id,nombre,lugar,fecha,num_max,km,dureza,edad,tipo,ncuenta,dni,fechas,categorias);
			carreras.add(c);
			rs2.close();
			rs3.close();
			st2.close();
			st3.close();
		}
		rs.close();
		st.close();
		cerrar();
		return carreras;
	}
	
	/**
	 * Metodo que devuelve un ArrayList con todos los usuarios
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Usuario> sacaTodosLosUsuarios() throws SQLException{
		conectar();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Statement st = conexion.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Usuario");
		while (rs.next()){
			String dni = rs.getString("DNI");
			String nombre = rs.getString("Nombre");
			String fecha_nacimiento = rs.getString("Fecha_nacimiento");
			String dir = rs.getString("Direccion");
			int tel = rs.getInt("Telefono");
			String localidad = rs.getString("Localidad");
			String cp = rs.getString("Codigo_postal");
			String correo = rs.getString("Correo");
			String contra = rs.getString("Contraseña");
			String genero = rs.getString("Genero");
			Usuario u = new Usuario(dni,nombre,fecha_nacimiento,dir,tel,localidad,cp,correo,contra,genero);
			usuarios.add(u);
		}
		rs.close();
		st.close();
		cerrar();
		return usuarios;
	}
	
	/**
	 * Metodo que saca todos los dni inscritos en una carrera
	 * @param c
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> sacaTodosLosDNIPreinscritosEnCarrera(Carrera c) throws SQLException{
		conectar();
		ArrayList<String> dnis = new ArrayList<String>();
		PreparedStatement addCarrera = conexion.prepareStatement("Select * From Preinscritos p "
				+ "Where p.Id_carrera = ?");
		addCarrera.setInt(1,c.getId());
		ResultSet rs = addCarrera.executeQuery();
		while (rs.next()){
			String dni = rs.getString("DNI");
			dnis.add(dni);
		}
		addCarrera.close();
		cerrar();
		return dnis;
	}
	
	/**
	 * Metodo que saca un ArrayList de los Corredores(con dorsal, es decir ya pagaron)
	 * de una carrera 
	 * @param idCarrera
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Corredor> findCorredoresByIdCarrera(Integer idCarrera) throws SQLException{
			ArrayList<Corredor> corredores = new ArrayList<Corredor>();
		
			conectar();
			PreparedStatement pst = conexion.prepareStatement("SELECT * FROM Corredores WHERE Id_Carrera = ? ");
			pst.setInt(1, idCarrera);
			ResultSet rs=pst.executeQuery();
			while (rs.next()){
				corredores.add(new Corredor(rs.getString("DNI"), rs.getInt("Id_Carrera"), rs.getInt("Tiempo"),
						rs.getInt("Dorsal"), rs.getString("Categoria"), rs.getString("Genero"), rs.getString("Nombre"),
						rs.getString("Fecha_Inscripcion")));
			}
			rs.close();
			pst.close();
			cerrar();
			return corredores;
		}
	
	
	/**
	 * Metodo que saca un ArrayList de los Corredores del genero pasado 
	 * por parametro ordenados por tiempo(con dorsal, es decir ya pagaron)
	 * de una carrera 
	 * @param idCarrera, genero
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Corredor> findCorredoresByIdCarreraOrderByTiempoByGenero(Integer idCarrera, String genero) throws SQLException{
			ArrayList<Corredor> corredores = new ArrayList<Corredor>();
		
			conectar();
			PreparedStatement pst = conexion.prepareStatement("SELECT * FROM Corredores WHERE Id_Carrera = ? and Genero = ? ORDER BY Tiempo ASC ");
			pst.setInt(1, idCarrera);
			pst.setString(2, genero);
			ResultSet rs=pst.executeQuery();
			while (rs.next()){
				corredores.add(new Corredor(rs.getString("DNI"), rs.getInt("Id_Carrera"), rs.getInt("Tiempo"),
						rs.getInt("Dorsal"), rs.getString("Categoria"), rs.getString("Genero"), rs.getString("Nombre"),
						rs.getString("Fecha_Inscripcion")));
			}
			rs.close();
			pst.close();
			cerrar();
			return corredores;
		}
	
	
	/**
	 * Metodo que devuelve un ArrayList de Preinscritos(no pagaron aun) de una carrera
	 * @param idCarrera
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Preinscrito> findInscritosByIdCarrera(Integer idCarrera) throws SQLException {
		ArrayList<Preinscrito> preinscritos = new ArrayList<Preinscrito>();
	
		conectar();
		PreparedStatement pst = conexion.prepareStatement("SELECT * FROM Preinscritos WHERE Id_Carrera = ? ORDER BY Fecha_Inscripcion ASC");
		pst.setInt(1, idCarrera);
		ResultSet rs=pst.executeQuery();
		
		while (rs.next()){
			preinscritos.add(new Preinscrito(rs.getString("DNI"),
					rs.getInt("Id_Carrera"),rs.getString("Categoria"),
					rs.getString("Genero"),rs.getString("Pagado"),rs.getString("Fecha_Inscripcion"),rs.getString("Nombre")));
		}
		
		rs.close();
		pst.close();
		cerrar();
		return preinscritos;
	}
	
	/**
	 * Metodo que devuelve un ArrayList con todas las carreras en las que
	 * un usuario esta inscrito o preinscrito
	 * @param u
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Carrera> findCarrerasDeUnUsuario(Usuario u) throws SQLException{
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		ArrayList<Integer> ids_carreras = new ArrayList<Integer>();
		conectar();
		PreparedStatement pst = conexion.prepareStatement("SELECT p.Id_Carrera FROM Preinscritos p WHERE p.DNI = ?" + 
				"union " + 
				"select c.Id_Carrera FROM Corredores c WHERE c.DNI = ?");
		pst.setString(1, u.getDni());
		pst.setString(2, u.getDni());
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			ids_carreras.add(rs.getInt("Id_Carrera"));	
			rs.close();
			pst.close();
		}
		rs.close();
		pst.close();
		cerrar();
		ArrayList<Carrera> todas = sacarTodasLasCarreras();
		for(Carrera crr:todas)if(ids_carreras.contains(crr.getId()))carreras.add(crr);
		return carreras;
	}
	
	
	/**
	 * Metodo que añade una carrera a la base de datos
	 * @param c
	 * @throws SQLException
	 */
	public static void addCarrera(Carrera c) throws SQLException {
		conectar();
		PreparedStatement addCarrera = conexion.prepareStatement("INSERT INTO CARRERA "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		addCarrera.setInt(1,c.getId());
		addCarrera.setString(2, c.getNombre());
		addCarrera.setString(3, c.getLugar());
		addCarrera.setString(4, c.getFecha());
		addCarrera.setInt(5, c.getNum_max_part());
		addCarrera.setInt(6, c.getDistancia());
		addCarrera.setString(7, c.getDureza());
		addCarrera.setInt(8, c.getEdad_minima());
		addCarrera.setString(9, c.getTipo());
		addCarrera.setString(10, c.getNum_cuenta());
		addCarrera.setString(11, c.getDni_creador());
		addCarrera.executeUpdate();
		addCarrera.close();
		for (Categoria cat : c.getCategorias()) {
			PreparedStatement addCategorias = conexion.prepareStatement("INSERT INTO CATEGORIA "
					+ "VALUES (?,?,?,?)");
			addCategorias.setString(1,cat.getNombre());
			addCategorias.setInt(2, cat.getEdadMin());
			addCategorias.setInt(3, cat.getEdadMax());
			addCategorias.setInt(4, c.getId());
			addCategorias.executeUpdate();
			addCategorias.close();
		}
		for (FechaInscripcion fi : c.getFechas_inscripcion()) {
			PreparedStatement addFechas = conexion.prepareStatement("INSERT INTO FECHA_INSCRIPCION "
					+ "VALUES (?,?,?,?)");
			addFechas.setString(1,fi.getFecha());
			addFechas.setString(2, fi.getFechaFin());
			addFechas.setDouble(3, fi.getPrecio());
			addFechas.setInt(4, c.getId());
			addFechas.executeUpdate();
			addFechas.close();
		}
		cerrar();
	}
	
	/**
	 * 
	 * Metodo que añade un Usuario a la base de datos
	 * @param U, Usuario
	 * @throws SQLException
	 */
	public static void addUsuario(Usuario u) throws SQLException {
		conectar();
		PreparedStatement addUsuario = conexion.prepareStatement("INSERT INTO Usuario "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)");
		addUsuario.setString(1, u.getDni());
		addUsuario.setString(2, u.getNombre());
		addUsuario.setString(3, u.getFecha_nacimiento());
		addUsuario.setString(4, u.getDireccion());
		addUsuario.setInt(5, u.getTelefono());
		addUsuario.setString(6, u.getLocalidad());
		addUsuario.setString(7, u.getCodigo_postal());
		addUsuario.setString(8, u.getCorreo());
		addUsuario.setString(9, u.getContra());
		addUsuario.setString(10, u.getGenero());
		addUsuario.executeUpdate();
		addUsuario.close();
		cerrar();
	}

	/**
	 * Metodo que borra una carrera de la base de datos
	 * @param c
	 * @throws SQLException
	 */
	public static void deleteCarrera(int id) throws SQLException {
		conectar();
		PreparedStatement deleteCarrera = conexion.prepareStatement("DELETE FROM CARRERA WHERE id=?");
		deleteCarrera.setInt(1, id);
		deleteCarrera.executeUpdate();
		deleteCarrera.close();
		cerrar();
	}
	
	/**
	 * Metodo que preeinscribe a un usuario en una carrera
	 * @param u
	 * @param c
	 * @throws SQLException 
	 */
	public static void addPreeinscrito(Usuario u,Carrera c,String fecha) throws SQLException {
		conectar();
		PreparedStatement addPreinscrito = conexion.prepareStatement("INSERT INTO Preinscritos "
				+ "VALUES (?,?,'No',?,?,?,?,?)");
		addPreinscrito.setString(1, u.getDni());
		addPreinscrito.setInt(2, c.getId());
		addPreinscrito.setString(3, c.getCategoriaParaUsuario(u.getEdad()));
		addPreinscrito.setString(4, u.getGenero());
		addPreinscrito.setString(5, u.getNombre());
		addPreinscrito.setString(6, fecha);
		addPreinscrito.setString(7, fecha);
		addPreinscrito.executeUpdate();
		addPreinscrito.close();
		cerrar();
	}
	
	
	/**
	 * Metodo que borra un usuario de la base de datos
	 * @param dni
	 * @throws SQLException
	 */
	public static void deleteUsuario(String dni) throws SQLException {
		conectar();
		PreparedStatement deleteUsuario = conexion.prepareStatement("DELETE FROM Usuario WHERE dni=?");
		deleteUsuario.setString(1, dni);
		deleteUsuario.executeUpdate();
		deleteUsuario.close();
		cerrar();
	}
	
	/**
	 * Método que marca como pagada la inscripcion a la carrera cuyo id se recibe
	 * como parámetro para el usuario de DNI también recibido como parámetro. Este
	 * método debería invocarse tras realizar la inscripción empleando el método de
	 * pago por transferencia
	 * 
	 * @param idCarrera
	 * @param DNI
	 * @throws SQLException
	 */
	public static void pagoPorTarjeta(int idCarrera, String DNI) throws SQLException {
		conectar();
		PreparedStatement ps = conexion
				.prepareStatement("UPDATE Preinscritos SET Pagado = ? WHERE Id_carrera = ? AND DNI = ?");
		ps.setString(1, "Si");
		ps.setInt(2, idCarrera);
		ps.setString(3, DNI);
		ps.executeUpdate();
		ps.close();
		cerrar();
	}

	/**
	 * Método auxiliar que maneja las excepciones de tipo SQLException en las
	 * consultas a la base de datos
	 * 
	 * @param ex
	 */
	public static void handleSQLException(SQLException ex) {
		System.out.println(" SQLException recogida: ");
		while (ex != null) {
			System.out.println("Mensaje: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("ErrorCode: " + ex.getErrorCode());
			ex = ex.getNextException();
			System.out.println(" ");
		}
	}
	
	
	/**
	 * Método que guarda la fecha en la que un determinado usuario escogió pagar por
	 * transferencia, de manera que disponga de un máximo de 48 horas para confirmar
	 * dicho pago
	 * 
	 * @param DNI
	 *            dni del usuario
	 * @param fecha
	 *            fecha de pago
	 * @throws SQLException
	 */
	public static void setFechaPago(String DNI, String fecha) throws SQLException {
		conectar();
		PreparedStatement ps = conexion.prepareStatement("UPDATE Preinscritos SET Fecha_pago = ? WHERE DNI = ?");
		ps.setString(1, fecha);
		ps.setString(2, DNI);
		ps.executeUpdate();
		ps.close();
		cerrar();
	}

	/**
	 * Método que devuelve la fecha en la que un determinado usuario escogió pagar
	 * por transferencia, de manera que si se ha superado el límite de 48 horas su
	 * inscripción quede cancelada
	 * 
	 * @param DNI
	 *            dni del usuario
	 * @return fecha de pago
	 * @throws SQLException
	 */
	public static String getFechaPago(String DNI) throws SQLException {
		String fecha = "";
		conectar();
		PreparedStatement ps = conexion.prepareStatement("SELECT Fecha_pago FROM Preinscritos WHERE DNI = ?");
		ps.setString(1, DNI);
		ResultSet rs = ps.executeQuery();
		fecha = rs.getString("Fecha_pago");
		ps.close();
		rs.close();
		cerrar();
		return fecha;
	}
}
