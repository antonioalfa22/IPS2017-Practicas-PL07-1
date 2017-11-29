/**
 * 
 */
package gestorBBDD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entities.Carrera;
import entities.Usuario;
import logic.GestorApp;
import logic.PuntoControl;
import logic.Time;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class LectorCSV {

	public static final String SEPARADOR = "\t";

	public static List<Usuario> leerUsuarios(String fichero) {
		BufferedReader bufferLectura = null;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			bufferLectura = new BufferedReader(new FileReader(fichero));
			String linea = bufferLectura.readLine();
			while (linea != null) {
				String[] campos = linea.split(SEPARADOR);
				linea = bufferLectura.readLine();
				try {
					usuarios.add(new Usuario(campos[0], campos[1], campos[2], Integer.parseInt(campos[3]), campos[4],
							campos[5]));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"El csv debe tener un integrante por fila con formato DNI Nombre Fecha Telefono"
									+ " Correo Genero \nSeparados por tabuladores",
							"CSV mal formado", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferLectura != null) {
				try {
					bufferLectura.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return usuarios;
	}

	/**
	 * Se le pasa un fichero con tiempos,la carrera a la que corresponde y actualiza
	 * los tiempos en la base de datos
	 * 
	 * @param ruta,
	 *            ruta del fichero
	 * @param carrera
	 *            carr
	 */
	public static void actualizarTiempos(String ruta, Carrera carrera) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		boolean asignado = false;

		GestorApp g = new GestorApp();
		g.eliminaTiempos(carrera);

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;

			// Puntos de control
			ArrayList<PuntoControl> puntoControl = carrera.getPuntos_control();
			Time tiempoAcumulado;

			int dorsal = -1;
			int tInicio = -1;
			String tFin = null;
			while ((linea = br.readLine()) != null) {
				tiempoAcumulado = new Time(puntoControl.get(0).getHoras(), puntoControl.get(0).getMin(), 0);
				asignado = false;
				String[] line = linea.split(",");
				if (line.length == puntoControl.size() + 2) {
					// Comprobación del dorsal, si no es correcto el valor se queda en -1
					if (!line[0].equals("")) {
						if (!line[0].matches(".*[a-zA-Z].*")) {
							if (Integer.parseInt(line[0]) > 0) {
								dorsal = Integer.parseInt(line[0]);
							}
						}
					}
					// Comprabomos si el atleta ha empezado la carrera, si no el valor se queda en
					// -1
					if (!line[1].equals("")) {
						if (!line[1].matches(".*[a-zA-Z].*")) {
							if (Integer.parseInt(line[1]) == 0) {
								tInicio = Integer.parseInt(line[1]);
							}
						}
					}
					int cont = 1;
					boolean descalificado = false;
					for (int i = 2; i < 2 + puntoControl.size(); i++) {
						String[] crono = line[i].split(":");
						if (crono.length == 3) {
							int horas = -1;
							int minutos = -1;
							int segundos = -1;
							if (!crono[0].equals("")) {
								if (!crono[0].matches(".*[a-zA-Z].*")) {
									if (Integer.parseInt(crono[0]) >= 0) {
										horas = Integer.parseInt(crono[0]);
									}
								}
							}
							if (!crono[1].equals("")) {
								if (!crono[1].matches(".*[a-zA-Z].*")) {
									if (Integer.parseInt(crono[1]) >= 0 && Integer.parseInt(crono[1]) < 60) {
										minutos = Integer.parseInt(crono[1]);
									}
								}
							}
							if (!crono[2].equals("")) {
								if (!crono[2].matches(".*[a-zA-Z].*")) {
									if (Integer.parseInt(crono[2]) >= 0 && Integer.parseInt(crono[2]) < 60) {
										segundos = Integer.parseInt(crono[2]);
									}
								}
							}
							if (horas != -1 && minutos != -1 && segundos != -1) {
								if (!descalificado) {
									Time time = new Time(horas, minutos, segundos);
									if (time.compareTo(tiempoAcumulado) < 0) {
										tFin = horas + ":" + minutos + ":" + segundos;
										g.asignaTiempo(carrera, dorsal, puntoControl.get(i - 2).getKm(), tFin);
										if (cont < puntoControl.size()) {
											tiempoAcumulado = tiempoAcumulado
													.suma(new Time(puntoControl.get(cont).getHoras(),
															puntoControl.get(cont).getMin(), 0));
											cont++;
										}
									} else {
										tFin = horas + ":" + minutos + ":" + segundos;
										g.asignaTiempo(carrera, dorsal, puntoControl.get(i - 2).getKm(), tFin);
										descalificado = true;
									}
								} else {
									tFin = "97:97:97";
									g.asignaTiempo(carrera, dorsal, puntoControl.get(i - 2).getKm(), tFin);
								}
							}
						}
					}

					if (dorsal != -1 && tInicio != -1 && tFin != null) {
						asignado = true;
					}

				} else if (line.length == 2) {
					if (!line[0].equals("")) {
						if (!line[0].matches(".*[a-zA-Z].*")) {
							if (Integer.parseInt(line[0]) > 0) {
								dorsal = Integer.parseInt(line[0]);
							}
						}
					}
					if (!line[1].equals("")) {
						if (!line[1].matches(".*[a-zA-Z].*")) {
							if (Integer.parseInt(line[1]) == 0) {
								tInicio = Integer.parseInt(line[1]);
							} else {
								tInicio = -1;
							}
						}
					}

					if (dorsal != -1 && tInicio == 0) {
						for (PuntoControl p : puntoControl) {
							tFin = "98:98:98";
							g.asignaTiempo(carrera, dorsal, p.getKm(), tFin);
						}
						asignado = true;
					}

				} else if (line.length == 1) {
					if (!line[0].equals("")) {
						if (!line[0].matches(".*[a-zA-Z].*")) {
							if (Integer.parseInt(line[0]) > 0) {
								dorsal = Integer.parseInt(line[0]);
							} else {
								dorsal = -1;
							}
						}
					} else {
						dorsal = -1;
					}

					if (dorsal != -1) {
						for (PuntoControl p : puntoControl) {
							tFin = "99:99:99";
							g.asignaTiempo(carrera, dorsal, p.getKm(), tFin);
						}
						asignado = true;
					}
				}

				if (!asignado) {
					g.asignaTiempo(carrera, dorsal, null, "-3:-3:-3");
				}
			}

		} catch (Exception e) {
			System.out.println("Archico .csv no encontrado");
			// e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

}
