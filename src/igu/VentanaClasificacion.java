package igu;

import gestorBBDD.LectorCSV;
import iguUtil.ModeloNoEditable;
import iguUtil.RowsRendererClasificaciones;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Categoria;
import logic.GestorApp;
import logic.PuntoControl;
import logic.Time;
import entities.Carrera;
import entities.Corredor;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class VentanaClasificacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCarreras;
	private JComboBox<Carrera> cbCarreras;
	private GestorApp g;
	private JLabel lblSexo;
	private JComboBox<String> cbSexo;
	private JLabel lblCategoria;
	private JComboBox<String> cbCategoria;
	private JButton btnBuscar;
	private JPanel pnClasificaciones;
	private JScrollPane scrollPaneClasificaciones;
	private JTable tableClasificaciones;
	private ModeloNoEditable modeloTabla;
	private JCheckBox chckbxClub;
	private JCheckBox chckbxTiemposIntermedios;
	private JCheckBox chckbxDiferenciaTiempos;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public VentanaClasificacion() throws SQLException {
		g = new GestorApp();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaClasificacion.class.getResource("/img/icons8-Running Filled-50.png")));
		setTitle("Clasificacion de carreras");
		setBounds(100, 100, 852, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblCarreras());
		contentPane.add(getCbCarreras());
		contentPane.add(getLblSexo());
		contentPane.add(getCbSexo());
		contentPane.add(getLblCategoria());
		contentPane.add(getCbCategoria());
		contentPane.add(getBtnBuscar());
		contentPane.add(getPnClasificaciones());

		JLabel labelDatosMostrar = new JLabel("Selecciona los campos a mostrar:");
		labelDatosMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelDatosMostrar.setBounds(43, 83, 198, 28);

		contentPane.add(labelDatosMostrar);
		contentPane.add(getChckbxClub());
		contentPane.add(getChckbxTiemposIntermedios());
		contentPane.add(getChckbxDiferenciaTiempos());
	}

	// ==========================================================================================
	// IGU:
	// ==========================================================================================

	private JLabel getLblCarreras() {
		if (lblCarreras == null) {
			lblCarreras = new JLabel("Carrera:");
			lblCarreras.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCarreras.setBounds(43, 13, 79, 28);
		}
		return lblCarreras;
	}

	private JComboBox<Carrera> getCbCarreras() throws SQLException {
		if (cbCarreras == null) {
			cbCarreras = new JComboBox<Carrera>();
			for (Carrera carrera : g.getCarrerasOrderByFecha()) {
				cbCarreras.addItem(carrera);
			}
			cbCarreras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Carrera carrera = (Carrera) cbCarreras.getSelectedItem();
					inicializarModeloCategorias(carrera);
					modeloTabla = new ModeloNoEditable(asignarColumnas(carrera), 0);
					tableClasificaciones.setModel(modeloTabla);
					modeloTabla.fireTableDataChanged();

					
					try {
						LectorCSV.actualizarTiempos(carrera.getNombre() + ".csv", carrera);
						btnBuscar.setEnabled(true);
					} catch (Exception e) {
						btnBuscar.setEnabled(false);
						JOptionPane.showMessageDialog(null,
								"No hay corredores que hayan disputado la carrera\n" + carrera.getNombre());
						
					} //
					// Actualizamos tiempos de
					// corredores
					

				}
			});
			cbCarreras.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbCarreras.setBounds(43, 54, 243, 20);
		}
		return cbCarreras;
	}

	private void inicializarModeloCategorias(Carrera carrera) {
		cbCategoria.removeAllItems();
		cbCategoria.addItem("Todas");
		cbCategoria.addItem("Absoluta");
		for (Categoria c : carrera.getCategorias()) {
			cbCategoria.addItem(c.getNombre().toString());
		}
	}

	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblSexo.setBounds(310, 13, 79, 28);
		}
		return lblSexo;
	}

	private JComboBox<String> getCbSexo() {
		if (cbSexo == null) {
			cbSexo = new JComboBox<String>();
			cbSexo.addItem("Todos");
			cbSexo.addItem("Femenino");
			cbSexo.addItem("Masculino");
			cbSexo.setBounds(310, 53, 102, 22);
		}
		return cbSexo;
	}

	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Categor\u00EDa:");
			lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCategoria.setBounds(455, 13, 79, 28);
		}
		return lblCategoria;
	}

	private JComboBox<String> getCbCategoria() {
		if (cbCategoria == null) {
			cbCategoria = new JComboBox<String>();
			cbCategoria.setBounds(455, 53, 132, 22);
		}
		return cbCategoria;
	}

	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.setEnabled(false);
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscar();

					chckbxClub.setEnabled(true);
					chckbxTiemposIntermedios.setEnabled(true);
					chckbxDiferenciaTiempos.setEnabled(true);
				}
			});
			btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnBuscar.setBounds(638, 51, 97, 25);
		}
		return btnBuscar;
	}

	private void ClearTabla() {
		for (int i = 0; i < tableClasificaciones.getRowCount(); i++) {
			modeloTabla.removeRow(i);
			i -= 1;
		}
	}

	private JPanel getPnClasificaciones() {
		if (pnClasificaciones == null) {
			pnClasificaciones = new JPanel();
			pnClasificaciones.setBounds(43, 168, 748, 367);
			pnClasificaciones.setLayout(new BorderLayout(0, 0));
			pnClasificaciones.add(getScrollPaneClasificaciones(), BorderLayout.CENTER);
		}
		return pnClasificaciones;
	}

	private JScrollPane getScrollPaneClasificaciones() {
		if (scrollPaneClasificaciones == null) {
			scrollPaneClasificaciones = new JScrollPane();
			scrollPaneClasificaciones.setViewportView(getTableClasificaciones());
		}
		return scrollPaneClasificaciones;
	}

	private JTable getTableClasificaciones() {
		if (tableClasificaciones == null) {
			List<String> list = new ArrayList<String>();
			list.add("Categoría");
			list.add("Posición");
			list.add("Dorsal");
			list.add("Nombre");
			list.add("Tiempo Final");

			String[] nombreColumnas = new String[list.size()];
			nombreColumnas = list.toArray(nombreColumnas);

			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			tableClasificaciones = new JTable(modeloTabla);
			RowsRendererClasificaciones rr = new RowsRendererClasificaciones(1);
			tableClasificaciones.setDefaultRenderer(Object.class, rr);
		}
		return tableClasificaciones;
	}

	private JCheckBox getChckbxClub() {
		if (chckbxClub == null) {
			chckbxClub = new JCheckBox("Club");
			chckbxClub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Carrera carrera = (Carrera) cbCarreras.getSelectedItem();
					modeloTabla = new ModeloNoEditable(putoMono(carrera), 0);
					tableClasificaciones.setModel(modeloTabla);
					modeloTabla.fireTableDataChanged();
					buscar();
				}
			});
			chckbxClub.setBounds(43, 122, 65, 23);
			chckbxClub.setEnabled(false);
		}
		return chckbxClub;
	}

	private JCheckBox getChckbxTiemposIntermedios() {
		if (chckbxTiemposIntermedios == null) {
			chckbxTiemposIntermedios = new JCheckBox("Tiempos intermedios");
			chckbxTiemposIntermedios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					Carrera carrera = (Carrera) cbCarreras.getSelectedItem();
					modeloTabla = new ModeloNoEditable(putoMono(carrera), 0);
					tableClasificaciones.setModel(modeloTabla);
					modeloTabla.fireTableDataChanged();
					buscar();
				}
			});
			chckbxTiemposIntermedios.setBounds(110, 122, 161, 23);
			chckbxTiemposIntermedios.setEnabled(false);
		}
		return chckbxTiemposIntermedios;
	}

	private JCheckBox getChckbxDiferenciaTiempos() {
		if (chckbxDiferenciaTiempos == null) {
			chckbxDiferenciaTiempos = new JCheckBox("Diferencia de tiempos");
			chckbxDiferenciaTiempos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Carrera carrera = (Carrera) cbCarreras.getSelectedItem();
					modeloTabla = new ModeloNoEditable(putoMono(carrera), 0);
					tableClasificaciones.setModel(modeloTabla);
					modeloTabla.fireTableDataChanged();
					buscar();
				}
			});
			chckbxDiferenciaTiempos.setBounds(273, 122, 155, 23);
			chckbxDiferenciaTiempos.setEnabled(false);
		}
		return chckbxDiferenciaTiempos;
	}

	// ==========================================================================================
	// LOGICA:
	// ==========================================================================================

	private String[] putoMono(Carrera carrera) {
		List<String> list = new ArrayList<String>();
		list.add("Categoría");
		list.add("Posición");
		list.add("Dorsal");
		list.add("Nombre");
		list.add("Tiempo Final");
		list.add("Club");
		for (PuntoControl p : carrera.getPuntos_control()) {
			list.add("Tiempo Km " + p.getKm());
		}
		list.add("Diferencia tiempo");

		if (!(this.chckbxClub.isSelected())) {
			list.remove("Club");
		}
		if (!(this.chckbxTiemposIntermedios.isSelected())) {
			for (PuntoControl p : carrera.getPuntos_control()) {
				list.remove("Tiempo Km " + p.getKm());
			}
		}
		if (!(this.chckbxDiferenciaTiempos.isSelected())) {
			list.remove("Diferencia tiempo");
		}

		String[] nombreColumnas = new String[list.size()];
		nombreColumnas = list.toArray(nombreColumnas);

		return nombreColumnas;
	}

	/**
	 * Devuelve el array de nombres de columnas de la tabla en funcion de la carrera
	 * 
	 * @param carrera
	 * @return Array de nombres de columna
	 */
	private String[] asignarColumnas(Carrera carrera) {
		List<String> list = new ArrayList<String>();
		list.add("Categoría");
		list.add("Posición");
		list.add("Dorsal");
		list.add("Nombre");
		list.add("Tiempo Final");

		String[] nombreColumnas = new String[list.size()];
		nombreColumnas = list.toArray(nombreColumnas);

		return nombreColumnas;
	}

	/**
	 * Se ejecuta cuando se pulsa en buscar Muestra los corredores de la carrera y
	 * categoria seleccionadas
	 */
	private void buscar() {
		Carrera carrera = (Carrera) cbCarreras.getSelectedItem();
		String genero = (String) cbSexo.getSelectedItem();
		String categoria = (String) cbCategoria.getSelectedItem();
		ClearTabla();

		if (carrera.isFinalizada() && !(g.getCorredores(carrera.getId()).isEmpty())) {
			if (categoria.equals("Todas")) {
				añadirFilasCategoriasTodas(carrera, genero, categoria);
			} else {
				añadirFilas(carrera, genero, categoria);
			}
			if (modeloTabla.getRowCount() == 0) {
				chckbxClub.setSelected(false);
				chckbxTiemposIntermedios.setSelected(false);
				chckbxDiferenciaTiempos.setSelected(false);
				JOptionPane.showMessageDialog(null,
						"No hay corredores de la carrera\n" + carrera.getNombre() + " de la categoría " + categoria);
			}
		} else {
			chckbxClub.setSelected(false);
			chckbxTiemposIntermedios.setSelected(false);
			chckbxDiferenciaTiempos.setSelected(false);
			JOptionPane.showMessageDialog(null,
					"No hay corredores de la carrera o no se ha disputado aun\n" + carrera.getNombre());
		}
	}

	/**
	 * Resta desp a ant
	 * 
	 * @param desp
	 * @param ant
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Time resta(Time desp, Time ant) {

		java.sql.Time tDesp = new java.sql.Time(desp.hour, desp.minute, desp.second);
		java.sql.Time tAnt = new java.sql.Time(ant.hour, ant.minute, ant.second);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tDesp);
		calendar.add(Calendar.HOUR, -tAnt.getHours());
		calendar.add(Calendar.MINUTE, -tAnt.getMinutes());
		calendar.add(Calendar.SECOND, -tAnt.getSeconds());

		return new Time(calendar.getTime().getHours(), calendar.getTime().getMinutes(),
				calendar.getTime().getSeconds());

	}

	/**
	 * Añade filas
	 * 
	 * @param idCarrera
	 * @param genero
	 * @param categoria
	 */
	private void añadirFilas(Carrera carrera, String genero, String categoria) {

		List<Corredor> corredores = escogerListaCorredores(carrera, genero, categoria);
		List<Object> list;
		Object[] nuevaFila;

		int pos = 1;

		for (int i = 0; i < corredores.size(); i++) {
			boolean descalificado = false;
			boolean dns = false;
			boolean dnf = false;
			Time tiempoInf = corredores.get(0).getTiempos().get(corredores.get(0).getTiempos().size() - 1);
			list = new ArrayList<Object>();

			// Realiza el control de tiempos para saber si el corredor ha sido descalificado
			List<PuntoControl> puntosControl = carrera.getPuntos_control();
			int cont = 1;
			Time tiempoAcumulado = new Time(puntosControl.get(0).getHoras(), puntosControl.get(0).getMin(), 0);
			for (Time time : corredores.get(i).getTiempos()) {
				if (time.toString().equals("99:99:99")) {
					dns = true;
				}

				if (time.toString().equals("98:98:98")) {
					dnf = true;
				}

				if (time.compareTo(tiempoAcumulado) > 0) {
					descalificado = true;
				}

				if (!descalificado && cont < puntosControl.size()) {
					tiempoAcumulado = tiempoAcumulado
							.suma(new Time(puntosControl.get(cont).getHoras(), puntosControl.get(cont).getMin(), 0));
					cont++;
				}

			}

			if (corredores.get(i).getTiempos().size() != 0) {
				if (!(corredores.get(i).getTiempos().get(corredores.get(i).getTiempos().size() - 1).toString()
						.equals("-1"))) {
					list.add(corredores.get(i).getCategoria());
					if (!descalificado && !dns && !dnf) {
						list.add(pos++);
					} else {

						if (dns || dnf) {
							if (dns) {
								list.add("DNS");
							}
							if (dnf) {
								list.add("DNF");
							}
						} else {

							if (descalificado) {
								list.add("Fuera de control");
							}
						}

					}
					list.add(corredores.get(i).getDorsal());
					list.add(corredores.get(i).getNombre());
					if (!descalificado && !dns && !dnf) {
						list.add(corredores.get(i).getTiempos().get(corredores.get(i).getTiempos().size() - 1)
								.toString());
					} else {
						list.add("----");
					}

					if (this.chckbxClub.isSelected()) {
						list.add(corredores.get(i).getClub());
					} else {
						list.remove(corredores.get(i).getClub());
					}

					if (this.chckbxTiemposIntermedios.isSelected()) {
						for (Time time : corredores.get(i).getTiempos()) {
							if (time.toString().equals("97:97:97") || time.toString().equals("99:99:99")
									|| time.toString().equals("98:98:98")) {
								if (time.toString().equals("98:98:98") || time.toString().equals("99:99:99")) {
									for (int x = 0; x < puntosControl.size(); x++) {
										list.add("----");
									}
								} else {
									list.add("----");
								}

							} else {
								list.add(time);
							}

						}
					} else {
						for (Time time : corredores.get(i).getTiempos()) {
							list.remove(time);
						}
					}

					if (this.chckbxDiferenciaTiempos.isSelected()) {
						if (!(tiempoInf.toString().equals(corredores.get(i).getTiempos()
								.get(corredores.get(i).getTiempos().size() - 1).toString()))) {
							if (!descalificado && !dns && !dnf) {
								list.add(resta(
										corredores.get(i).getTiempos().get(corredores.get(i).getTiempos().size() - 1),
										tiempoInf));
							} else {
								list.add("----");
							}
						}else {
							list.add("----");
						}
					}else {
							list.remove(
									resta(corredores.get(i).getTiempos().get(corredores.get(i).getTiempos().size() - 1),
											tiempoInf));
						}

					nuevaFila = new Object[list.size()];
					nuevaFila = list.toArray(nuevaFila);

					modeloTabla.addRow(nuevaFila);
				}
			}
		}
	}

	/**
	 * Añade filas
	 * 
	 * @param idCarrera
	 * @param genero
	 * @param categoria
	 */
	private void añadirFilasCategoriasTodas(Carrera carrera, String genero, String categoria) {
		List<Corredor> corredores = escogerListaCorredores(carrera, genero, categoria);

		List<Object> list;
		Object[] nuevaFila;

		String currentCategoria;
		int pos = 1;
		currentCategoria = corredores.get(0).getCategoria();

		Time tiempoInf = corredores.get(0).getTiempos().get(corredores.get(0).getTiempos().size() - 1);
		for (int i = 0; i < corredores.size(); i++) {
			boolean descalificado = false;
			boolean dns = false;
			boolean dnf = false;

			list = new ArrayList<Object>();
			if (corredores.get(i).getTiempos().size() != 0) {
				if (!(currentCategoria.equals(corredores.get(i).getCategoria()))) {
					pos = 1;
					currentCategoria = corredores.get(i).getCategoria();
					tiempoInf = corredores.get(i).getTiempos().get(corredores.get(i).getTiempos().size() - 1);
					for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
						list.add("");
					}
					nuevaFila = new Object[list.size()];
					nuevaFila = list.toArray(nuevaFila);

					modeloTabla.addRow(nuevaFila);
					list = new ArrayList<Object>();
				}

				// Realiza el control de tiempos para saber si el corredor ha sido descalificado
				List<PuntoControl> puntosControl = carrera.getPuntos_control();
				int cont = 1;
				Time tiempoAcumulado = new Time(puntosControl.get(0).getHoras(), puntosControl.get(0).getMin(), 0);
				for (Time time : corredores.get(i).getTiempos()) {
					if (time.toString().equals("99:99:99")) {
						dns = true;
					}

					if (time.toString().equals("98:98:98")) {
						dnf = true;
					}

					if (time.compareTo(tiempoAcumulado) > 0) {
						descalificado = true;
					}

					if (!descalificado && cont < puntosControl.size()) {
						tiempoAcumulado = tiempoAcumulado.suma(
								new Time(puntosControl.get(cont).getHoras(), puntosControl.get(cont).getMin(), 0));
						cont++;
					}

				}

				if (!(corredores.get(i).getTiempos().get(corredores.get(i).getTiempos().size() - 1).toString()
						.equals("-1"))) {
					list.add(corredores.get(i).getCategoria());
					if (!descalificado && !dns && !dnf) {
						list.add(pos++);
					} else {
						if (dns || dnf) {
							if (dns) {
								list.add("DNS");
							}
							if (dnf) {
								list.add("DNF");
							}
						} else {

							if (descalificado) {
								list.add("Fuera de control");
							}
						}
					}

					list.add(corredores.get(i).getDorsal());
					list.add(corredores.get(i).getNombre());
					if (!descalificado && !dns && !dnf) {
						list.add(corredores.get(i).getTiempos().get(corredores.get(i).getTiempos().size() - 1)
								.toString());
					} else {
						list.add("----");
					}

					if (this.chckbxClub.isSelected()) {
						list.add(corredores.get(i).getClub());
					} else {
						list.remove(corredores.get(i).getClub());
					}

					if (this.chckbxTiemposIntermedios.isSelected()) {
						for (Time time : corredores.get(i).getTiempos()) {
							if (time.toString().equals("97:97:97") || time.toString().equals("99:99:99")
									|| time.toString().equals("98:98:98")) {
								if (time.toString().equals("98:98:98") || time.toString().equals("99:99:99")) {
									for (int x = 0; x < puntosControl.size(); x++) {
										list.add("----");
									}
								} else {
									list.add("----");
								}

							} else {
								list.add(time);
							}

						}
					} else {
						for (Time time : corredores.get(i).getTiempos()) {
							list.remove(time);
						}
					}

					
						if (this.chckbxDiferenciaTiempos.isSelected()) {
							if (!(tiempoInf.toString().equals(corredores.get(i).getTiempos()
									.get(corredores.get(i).getTiempos().size() - 1).toString()))) {
								if (!descalificado && !dns && !dnf) {
									list.add(resta(
											corredores.get(i).getTiempos().get(corredores.get(i).getTiempos().size() - 1),
											tiempoInf));
								} else {
									list.add("----");
								}
							}else {
								list.add("----");
							}
						}else {
								list.remove(
										resta(corredores.get(i).getTiempos().get(corredores.get(i).getTiempos().size() - 1),
												tiempoInf));
							}
						

					nuevaFila = new Object[list.size()];
					nuevaFila = list.toArray(nuevaFila);
					modeloTabla.addRow(nuevaFila);
				}
			}
		}
	}

	/**
	 * Escoge la lista de corredores
	 * 
	 * @param idCarrera
	 * @param genero
	 * @param categoria
	 * @return
	 */
	private List<Corredor> escogerListaCorredores(Carrera carrera, String genero, String categoria) {

		Integer n_km = carrera.getPuntos_control().get(carrera.getPuntos_control().size() - 1).getKm();

		if (genero.equals("Todos") && categoria.equals("Absoluta")) {
			return g.getCorredores(carrera.getId());
		} else if ((genero.equals("Femenino") || genero.equals("Masculino")) && categoria.equals("Absoluta")) {
			return g.getCorredoresByGenero(carrera.getId(), genero);
		} else if (genero.equals("Todos") && categoria.equals("Todas")) {
			return g.getCorredoresTodasCategorias(carrera.getId(), n_km);
		} else if ((genero.equals("Femenino") || genero.equals("Masculino")) && categoria.equals("Todas")) {
			return g.getCorredoresTodasCategoriasByGenero(carrera.getId(), genero, n_km);
		} else if (genero.equals("Todos") && categoria.equals(categoria)) {
			return g.getCorredoresTodasCategoriasGeneroT(carrera.getId(), categoria);
		} else {
			return g.getCorredoresByGeneroByCategoria(carrera.getId(), genero, categoria);
		}
	}

}
