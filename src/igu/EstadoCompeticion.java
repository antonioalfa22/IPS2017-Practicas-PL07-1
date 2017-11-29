package igu;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import entities.Carrera;
import entities.Corredor;
import entities.Preinscrito;
import entities.Usuario;
import gestorBBDD.GestorDB;
import logic.GestorApp;

public class EstadoCompeticion extends JDialog {

	private static final long serialVersionUID = 1L;
	private String estado;
	private JLabel lbEstadoCompeticion;
	private JTextField txtEstado;
	private JButton btCancelarInscripcion;
	private JButton btCederDorsal;
	private JPanel panelCeder;
	private JLabel lbDNI;
	private JTextField txtDni;
	private JLabel lbNombre;
	private JTextField txtNombre;
	private JLabel label;
	private JLabel lbDia;
	private JComboBox<Integer> cbDia;
	private JLabel lbMes;
	private JComboBox<String> cbMes;
	private JLabel lbYear;
	private JComboBox<Integer> cbYear;
	private JLabel lbTel;
	private JTextField txtTelefono;
	private JLabel lbCorreo;
	private JTextField txtCorreo;
	private JLabel lbGenero;
	private JComboBox<String> cbGenero;
	private JButton btAceptarCeder;
	private int dia, mes, year;
	private Carrera c;
	private String dni;
	private String nombre;
	private String fecha;
	private int tel;
	private String correo;
	private int genero;
	private GestorApp gestor;
	private Usuario usuario;

	/**
	 * Create the dialog.
	 */
	public EstadoCompeticion(String estado, Carrera c, GestorApp g, Usuario usuario) {
		this.usuario = usuario;
		this.c = c;
		this.gestor = g;
		dia = 1;
		mes = 1;
		year = 1999;
		setTitle("Estado Inscripcion de " + this.usuario.getNombre());
		this.estado = estado;
		setBounds(100, 100, 600, 469);
		getContentPane().setLayout(null);
		getContentPane().add(getLbEstadoCompeticion());
		getContentPane().add(getTxtEstado());
		getContentPane().add(getBtCancelarInscripcion());
		getContentPane().add(getBtCederDorsal());
		getContentPane().add(getPanelCeder());
		this.toFront();
	}

	private JLabel getLbEstadoCompeticion() {
		if (lbEstadoCompeticion == null) {
			lbEstadoCompeticion = new JLabel("Estado de la Inscripcion: ");
			lbEstadoCompeticion.setForeground(Color.DARK_GRAY);
			lbEstadoCompeticion.setFont(new Font("Dialog", Font.BOLD, 13));
			lbEstadoCompeticion.setBounds(10, 11, 205, 39);
		}
		return lbEstadoCompeticion;
	}

	private JTextField getTxtEstado() {
		if (txtEstado == null) {
			txtEstado = new JTextField();
			txtEstado.setEnabled(false);
			txtEstado.setText(estado);
			txtEstado.setEditable(false);
			txtEstado.setFont(new Font("Dialog", Font.BOLD, 13));
			txtEstado.setBounds(228, 11, 346, 39);
			txtEstado.setColumns(10);
		}
		return txtEstado;
	}

	private JButton getBtCancelarInscripcion() {
		if (btCancelarInscripcion == null) {
			btCancelarInscripcion = new JButton("Cancelar Inscripcion");
			btCancelarInscripcion.setEnabled(false);

			try {
				String aux = null;
				// Fecha actual
				DateTime fecha_actual = new DateTime();
				// Fecha de inicio
				aux = GestorDB.getFechaInicioCancelacion(c.getId());
				if (aux != null) {
					String[] inicio = aux.split("/");
					DateTime fecha_inicio = new DateTime(inicio[2] + "-" + inicio[1] + "-" + inicio[0]);
					// Fecha de fin
					String[] fin = GestorDB.getFechaFinCancelacion(c.getId()).split("/");
					DateTime fecha_fin = new DateTime(fin[2] + "-" + fin[1] + "-" + fin[0]);

					Interval interval = new Interval(fecha_inicio, fecha_fin);
					if (interval.contains(fecha_actual))
						btCancelarInscripcion.setEnabled(true);
					if(!estado.contains("Pagado"))
						btCancelarInscripcion.setEnabled(false);
				}
			} catch (SQLException ex) {
				GestorDB.handleSQLException(ex);
			}

			btCancelarInscripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cancelarInscripcion();
				}
			});
			btCancelarInscripcion.setBounds(71, 71, 205, 30);
		}
		return btCancelarInscripcion;
	}

	private JButton getBtCederDorsal() {
		if (btCederDorsal == null) {
			btCederDorsal = new JButton("Ceder mi dorsal");
			btCederDorsal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cederDorsal();
				}
			});
			btCederDorsal.setBounds(286, 71, 205, 30);
			btCederDorsal.setEnabled(false);
			if (estado.equals("Pagado"))
				btCederDorsal.setEnabled(true);
		}
		return btCederDorsal;
	}

	private JPanel getPanelCeder() {
		if (panelCeder == null) {
			panelCeder = new JPanel();
			panelCeder.setBounds(10, 120, 564, 299);
			panelCeder.setLayout(null);
			panelCeder.add(getLbDNI());
			panelCeder.add(getTxtDni());
			panelCeder.add(getLbNombre());
			panelCeder.add(getTxtNombre());
			panelCeder.add(getLabel());
			panelCeder.add(getLbDia());
			panelCeder.add(getCbDia());
			panelCeder.add(getLbMes());
			panelCeder.add(getCbMes());
			panelCeder.add(getLbYear());
			panelCeder.add(getCbYear());
			panelCeder.add(getLbTel());
			panelCeder.add(getTxtTelefono());
			panelCeder.add(getLbCorreo());
			panelCeder.add(getTxtCorreo());
			panelCeder.add(getLbGenero());
			panelCeder.add(getCbGenero());
			panelCeder.add(getBtAceptarCeder());
			panelCeder.setVisible(false);
		}
		return panelCeder;
	}

	private JLabel getLbDNI() {
		if (lbDNI == null) {
			lbDNI = new JLabel("DNI(*)");
			lbDNI.setForeground(Color.BLACK);
			lbDNI.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbDNI.setBounds(10, 11, 125, 24);
		}
		return lbDNI;
	}

	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtDni.setColumns(10);
			txtDni.setBackground(SystemColor.controlHighlight);
			txtDni.setBounds(189, 12, 288, 23);
			txtDni.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					dni = txtDni.getText();
					comprobarUsuarioRegistrado();
				}
			});
		}
		return txtDni;
	}

	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre y Apellidos(*)");
			lbNombre.setForeground(Color.BLACK);
			lbNombre.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbNombre.setBounds(10, 46, 125, 24);
		}
		return lbNombre;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtNombre.setColumns(10);
			txtNombre.setBackground(SystemColor.controlHighlight);
			txtNombre.setBounds(189, 49, 288, 23);
		}
		return txtNombre;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Fecha Nacimiento(*)");
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Dialog", Font.PLAIN, 12));
			label.setBounds(10, 108, 125, 24);
		}
		return label;
	}

	private JLabel getLbDia() {
		if (lbDia == null) {
			lbDia = new JLabel("D\u00EDa");
			lbDia.setHorizontalAlignment(SwingConstants.CENTER);
			lbDia.setFont(new Font("Dialog", Font.PLAIN, 11));
			lbDia.setBounds(189, 83, 58, 18);
		}
		return lbDia;
	}

	private JComboBox<Integer> getCbDia() {
		if (cbDia == null) {
			cbDia = new JComboBox<Integer>();
			cbDia.setBounds(189, 108, 58, 24);
			cbDia.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					dia = cbDia.getSelectedIndex() + 1;
				}
			});
			calcularDias();
		}
		return cbDia;
	}

	private JLabel getLbMes() {
		if (lbMes == null) {
			lbMes = new JLabel("Mes");
			lbMes.setHorizontalAlignment(SwingConstants.CENTER);
			lbMes.setFont(new Font("Dialog", Font.PLAIN, 11));
			lbMes.setBounds(270, 83, 58, 18);
		}
		return lbMes;
	}

	private JComboBox<String> getCbMes() {
		if (cbMes == null) {
			cbMes = new JComboBox<String>();
			cbMes.setBounds(263, 108, 125, 24);
			cbMes.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					mes = cbMes.getSelectedIndex() + 1;
					calcularDias();
					cbDia.updateUI();
				}
			});
			cbMes.setModel(new DefaultComboBoxModel<String>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
					"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
		}
		return cbMes;
	}

	private JLabel getLbYear() {
		if (lbYear == null) {
			lbYear = new JLabel("A\u00F1o");
			lbYear.setHorizontalAlignment(SwingConstants.CENTER);
			lbYear.setFont(new Font("Dialog", Font.PLAIN, 11));
			lbYear.setBounds(404, 83, 58, 18);
		}
		return lbYear;
	}

	private JComboBox<Integer> getCbYear() {
		if (cbYear == null) {
			cbYear = new JComboBox<Integer>();
			cbYear.setBounds(398, 108, 85, 24);
			int[] years = new int[150];
			for (int i = 0; i < 150; i++) {
				years[i] = 2017 - i;
				cbYear.addItem(years[i]);
			}
			cbYear.setSelectedItem(1999);
			cbYear.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					year = (Integer) cbYear.getSelectedItem();
				}
			});
		}
		return cbYear;
	}

	private JLabel getLbTel() {
		if (lbTel == null) {
			lbTel = new JLabel("Telefono(*)");
			lbTel.setForeground(Color.BLACK);
			lbTel.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbTel.setBounds(10, 146, 125, 24);
		}
		return lbTel;
	}

	private JTextField getTxtTelefono() {
		if (txtTelefono == null) {
			txtTelefono = new JTextField();
			txtTelefono.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtTelefono.setColumns(10);
			txtTelefono.setBackground(SystemColor.controlHighlight);
			txtTelefono.setBounds(189, 147, 288, 23);
		}
		return txtTelefono;
	}

	private JLabel getLbCorreo() {
		if (lbCorreo == null) {
			lbCorreo = new JLabel("Correo(*)");
			lbCorreo.setForeground(Color.BLACK);
			lbCorreo.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbCorreo.setBounds(10, 181, 125, 24);
		}
		return lbCorreo;
	}

	private JTextField getTxtCorreo() {
		if (txtCorreo == null) {
			txtCorreo = new JTextField();
			txtCorreo.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtCorreo.setColumns(10);
			txtCorreo.setBackground(SystemColor.controlHighlight);
			txtCorreo.setBounds(189, 184, 288, 23);
		}
		return txtCorreo;
	}

	private JLabel getLbGenero() {
		if (lbGenero == null) {
			lbGenero = new JLabel("Genero(*)");
			lbGenero.setForeground(Color.BLACK);
			lbGenero.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbGenero.setBounds(10, 216, 125, 24);
		}
		return lbGenero;
	}

	private JComboBox<String> getCbGenero() {
		if (cbGenero == null) {
			cbGenero = new JComboBox<String>();
			cbGenero.setModel(new DefaultComboBoxModel<String>(new String[] { "Masculino", "Femenino" }));
			cbGenero.setBounds(189, 219, 288, 24);
		}
		return cbGenero;
	}

	private JButton getBtAceptarCeder() {
		if (btAceptarCeder == null) {
			btAceptarCeder = new JButton("Ceder dorsal a la persona con estos datos");
			btAceptarCeder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aceptar();
				}
			});
			btAceptarCeder.setBounds(209, 254, 345, 34);
		}
		return btAceptarCeder;
	}

	// ==========================================================================================
	// LOGICA:
	// ==========================================================================================

	protected void cederDorsal() {
		panelCeder.setVisible(true);

	}

	protected void cancelarInscripcion() {
		double devolucion = 0;
		try {
			devolucion = (double)(GestorDB.getDevolucion(c.getId()) * GestorDB.getCantidadPagadaCorredor(usuario.getDni())) / 100d;
		} catch (SQLException ex) {
			GestorDB.handleSQLException(ex);
		}
		int option = JOptionPane.showConfirmDialog(this,
				"¿Está seguro de que quiere cancelar su inscripción?\nEn caso afirmativo se le reenvolsarán "
						+ devolucion + "€",
				"Confirmar Cancelación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			try {
				GestorDB.cancelar(usuario.getDni(), c);
				devolucion = (double)(GestorDB.getDevolucion(c.getId()) * GestorDB.getCantidadPagada(usuario.getDni())) / 100d;
				GestorDB.setNotasPago("Cancelado - Se devolverán " + devolucion + "€", usuario.getDni(), c);
				getBtCancelarInscripcion().setEnabled(false);
				dispose();
			} catch (SQLException ex) {
				GestorDB.handleSQLException(ex);
			}
			JOptionPane.showMessageDialog(this, "Cancelación completada con éxito", "Cancelación Completada",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void aceptar() {
		if (comprobarDatos())
			if (meterDatos()) {
				JOptionPane.showMessageDialog(null, "Se ha traspasado el dorsal correctamente",
						"Se ha traspasado el dorsal", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
	}

	private boolean meterDatos() {
		try {
			if (!gestor.existeUsuario(dni)) {
				Usuario u = new Usuario(dni, nombre, fecha, "", tel, "", "", correo, "",
						genero == 1 ? "Masculino" : "Femenino");
				VentanaPrincipal.gestorCarreras.addUsuario(u);
			}
			Usuario u = new Usuario(dni, nombre, fecha, "", tel, "", "", correo, "",
					genero == 1 ? "Masculino" : "Femenino");
			boolean flag = false;
			for (Carrera carr : usuario.getCarreras()) {
				if (carr.getId() == c.getId())
					flag = true;
			}
			if (flag) {
				Corredor corredor = null;
				for (Corredor corr : gestor.corredores)
					if (corr.getDni().equals(usuario.getDni()))
						corredor = corr;
				VentanaPrincipal.gestorCarreras.addPreeinscrito(usuario, c, corredor.getFechaInscripcion());
				GestorDB.setNotasPago("Cancelada-Dorsal Cedido", usuario.getDni(), c);
				VentanaPrincipal.gestorCarreras.addCorredor(u, c, corredor.getFechaInscripcion(),
						GestorDB.getNotasPagoInscrito(corredor.getDni(), c),
						GestorDB.getCantidadPagadaCorredor(corredor.getDni()), corredor.getDorsal());
				VentanaPrincipal.gestorCarreras.deleteCorredor(corredor.getDni());
			} else {

			}

			// TODO: Acabar de hacer
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error, prueba a recargar la aplicación para actualizar posibles cambios hechos"
					+ "anteriormente", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private boolean comprobarDatos() {
		addCamposToVariables();

		// COMPRUEBA QUE TENGA MAS EDAD QUE LA MINIMA
		if (2017 - year < c.getEdad_minima()) {
			JOptionPane.showMessageDialog(null, "No tienes la edad suficiente para participar en la carrera",
					"Edad menor que la mínima especificada", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (dni.equals(usuario.getDni())) {
			JOptionPane.showMessageDialog(null, "No puedes traspasarte el dorsal a ti mismo",
					"Error al traspasar el dorsal", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		GregorianCalendar cal = new GregorianCalendar();
		GregorianCalendar cal2 = new GregorianCalendar(Integer.parseInt(c.getFecha().split("/")[2]),
				Integer.parseInt(c.getFecha().split("/")[1]) - 1, Integer.parseInt(c.getFecha().split("/")[0]));
		if (cal.compareTo(cal2) > 0) {
			JOptionPane.showMessageDialog(null, "No puedes traspasar el dorsal de una carrera ya finalizada",
					"Error al traspasar el dorsal", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// COMPRUEBA QUE EL TELEFONO SEAN NUMEROS
		try {
			tel = Integer.parseInt(txtTelefono.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El telefono debe ser un numero", "Telefono incorrecto",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// COMPRUEBA QUE LOS CAMPOS OBLIGATORIOS ESTAN RELLENADOS
		if (dni.length() >= 1 && nombre.length() >= 1 && fecha.length() >= 1 && tel > 0 && (genero == 1 || genero == 0)
				&& correo != null) {
			// COMPRUEBA QUE EL USUARIO CON ESE DNI NO ESTE INSCRITO EN ESA CARRERA
			// YA(PREINSCRITO O INSCRITO)
			List<Preinscrito> preinscritos = VentanaPrincipal.gestorCarreras.getTodosLosPreinscritos(c);
			List<Corredor> corredores = GestorApp.getTodosLosCorredores(c);
			for (Preinscrito preinscrito : preinscritos) {
				if (preinscrito.getDni().equals(dni)) {
					JOptionPane.showMessageDialog(null, "Ya hay un participante con ese DNI", "Atleta ya registrado",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			for (Corredor corredor : corredores) {
				if (corredor.getDni().equals(dni)) {
					JOptionPane.showMessageDialog(null, "Ya hay un participante con ese DNI", "Atleta ya registrado",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			return true;
		}

		JOptionPane.showMessageDialog(null, "Tienes algun campo mal rellenado", "Campos vacíos o erroneos",
				JOptionPane.ERROR_MESSAGE);
		return false;
	}

	private void addCamposToVariables() {
		dni = txtDni.getText();
		nombre = txtNombre.getText();
		fecha = dia + "/" + mes + "/" + year;
		correo = txtCorreo.getText();
		genero = ((String) cbGenero.getSelectedItem()).equals("Masculino") ? 1 : 0;
	}

	protected void comprobarUsuarioRegistrado() {
		for (Usuario p : gestor.getUsuarios()) {
			if (p.getDni().equals(dni)) {
				int seleccion = JOptionPane.showOptionDialog(null,
						"Ese DNI ya está registrado en la aplicación," + "¿Deseas usar sus datos?", "DNI Ya registrado",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						new Object[] { "Usar los datos de ese DNI", "Cancelar" }, "opcion 1");

				if (seleccion == 0) {
					ArrayList<Usuario> users = gestor.getUsuarios();
					Usuario u = users.stream().filter(x -> x.getDni().equals(p.getDni())).findFirst().get();
					txtDni.setText(u.getDni());
					txtNombre.setText(u.getNombre());
					cbDia.setSelectedItem(Integer.parseInt(u.getFecha_nacimiento().split("/")[0]));
					cbMes.setSelectedIndex(Integer.parseInt(u.getFecha_nacimiento().split("/")[1]) - 1);
					cbYear.setSelectedItem(Integer.parseInt(u.getFecha_nacimiento().split("/")[2]));
					txtTelefono.setText(String.valueOf(u.getTelefono()));
					txtCorreo.setText(u.getCorreo());
					String gen = u.getGenero();
					if (gen.equals("Masculino")) {
						cbGenero.setSelectedItem("Masculino");
					} else {
						cbGenero.setSelectedItem("Femenino");
					}

				}
			}
		}
	}

	/**
	 * Calcula los dias del comboBox
	 */
	private void calcularDias() {
		cbDia.removeAll();
		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
			for (int i = 1; i <= 31; i++)
				cbDia.addItem(i);
		else if (mes == 2)
			for (int i = 1; i <= 28; i++)
				cbDia.addItem(i);
		else
			for (int i = 1; i <= 30; i++)
				cbDia.addItem(i);
	}
}
