package igu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.joda.time.DateTime;

import entities.Carrera;
import entities.Corredor;
import entities.Usuario;
import gestorBBDD.GestorDB;
import logic.GestorApp;

/**
 * Ventana que muestra toda la informacion de las carreras a las que esta
 * inscrito o preinscrito cada usuario
 * 
 * @author Pablo Menendez y Antonio Paya
 *
 */
public class VentanaUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnInfoPersonal;
	private JPanel pnInfo;
	private JLabel lbInfo;
	private JPanel pnSubInfo;
	private JLabel lblNombre;
	private JLabel lblDNI;
	private JPanel pnCard;
	private JPanel pnClasificaciones;
	private JPanel pnSubClasis;
	private JLabel lblClasificacion;
	private JPanel pnCarreras;
	private JPanel pnSubCarreras;
	private JPanel pnSelectCarrera;
	private JPanel pnEstadoInscripcion;
	private JPanel pnAccederClasificacion;
	private JPanel pnLabelsCarreras;
	private JLabel lblCarrerasDisponibles;
	private JLabel lblEstadoInscripcion;
	private JLabel lblAccesoClasificacin;
	private JLabel lbDatosNombre;
	private JLabel lbDatosDNI;
	private JLabel lblFecha;
	private JLabel lbDatosFecha;
	private JLabel lblDireccion;
	private JLabel lbDatosDireccion;
	private JLabel lblCodigoPostal;
	private JLabel lbDatosCodigo;
	private JLabel lblCorreo;
	private JLabel lbDatosCorreo;
	private JLabel lbDatosEdad;
	private JLabel lblLocalidad;
	private JLabel lbDatosLocalidad;
	private JLabel lblEdad;
	private Usuario user;
	private JPanel pnAtras;
	private JButton btnAtrs;
	private JPanel pnResultados;
	private JLabel lbTiempo;
	private JLabel lbDatosTiempo;
	private JLabel lbPosAbsoluta;
	private JLabel lbDatosPosAbsoluta;
	private JLabel lbPosCategoria;
	private JLabel lbDatosPosCategoria;
	private JPanel panelBusqueda;
	private JRadioButton rbDNI;
	private JRadioButton rbNombre;
	private JRadioButton rdbtnCorreo;
	private JLabel lblBuscarPor;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtDatos;
	private JList<Usuario> listaUsuarios;
	private DefaultListModel<Usuario> modeloLista;
	private GestorApp gestor;
	private final static int DNI = 1, NOMBRE = 2, CORREO = 3;
	private int rbSeleccionado;
	private JScrollPane scrollPane;
	private String txtmemoria;
	private String estadoInscripcion;

	/**
	 * Create the frame.
	 */
	public VentanaUsuario(GestorApp g) {
		rbSeleccionado = DNI;
		this.gestor = g;
		setTitle("Ventana Usuario");
		setBounds(100, 100, 1017, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelBusqueda(), BorderLayout.SOUTH);
		contentPane.add(getPnInfoPersonal(), BorderLayout.WEST);
		contentPane.add(getPanel_2(), BorderLayout.CENTER);
		if (user != null) {
			updateInfoUsuario();
			updateInfoCarreras();
		}
	}

	// ==========================================================================================
	// COMPONENTES IGU:
	// ==========================================================================================

	private JPanel getPnInfoPersonal() {
		if (pnInfoPersonal == null) {
			pnInfoPersonal = new JPanel();
			pnInfoPersonal.setBackground(SystemColor.activeCaption);
			pnInfoPersonal.setLayout(new GridLayout(1, 1, 0, 0));
			pnInfoPersonal.add(getPnInfo());
		}
		return pnInfoPersonal;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBackground(new Color(210, 180, 140));
			pnInfo.setBorder(new MatteBorder(3, 3, 2, 2, (Color) new Color(0, 0, 0)));
			pnInfo.setLayout(new BorderLayout(0, 0));
			pnInfo.add(getLbInfo(), BorderLayout.NORTH);
			pnInfo.add(getPnSubInfo(), BorderLayout.CENTER);
		}
		return pnInfo;
	}

	private JLabel getLbInfo() {
		if (lbInfo == null) {
			lbInfo = new JLabel("Informacion personal");
			lbInfo.setBackground(SystemColor.desktop);
			lbInfo.setFont(new Font("Tahoma", Font.BOLD, 19));
		}
		return lbInfo;
	}

	private JPanel getPnSubInfo() {
		if (pnSubInfo == null) {
			pnSubInfo = new JPanel();
			pnSubInfo.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
			pnSubInfo.setBackground(SystemColor.info);
			pnSubInfo.setLayout(new GridLayout(0, 2, 0, 0));
			pnSubInfo.add(getLblNombre());
			pnSubInfo.add(getLbDatosNombre());
			pnSubInfo.add(getLblDNI());
			pnSubInfo.add(getLbDatosDNI());
			pnSubInfo.add(getLbEdad());
			pnSubInfo.add(getLbDatosEdad());
			pnSubInfo.add(getLblFecha());
			pnSubInfo.add(getLbDatosFecha());
			pnSubInfo.add(getLblDireccion());
			pnSubInfo.add(getLbDatosDireccion());
			pnSubInfo.add(getLblLocalidad());
			pnSubInfo.add(getLbDatosLocalidad());
			pnSubInfo.add(getLblCodigoPostal());
			pnSubInfo.add(getLbDatosCodigo());
			pnSubInfo.add(getLblCorreo());
			pnSubInfo.add(getLbDatosCorreo());
		}
		return pnSubInfo;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("  Nombre:");
			lblNombre.setForeground(new Color(255, 0, 0));
			lblNombre.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblNombre;
	}

	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("  DNI:");
			lblDNI.setForeground(new Color(255, 0, 0));
			lblDNI.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblDNI;
	}

	private JLabel getLbEdad() {
		if (lblEdad == null) {
			lblEdad = new JLabel("  Edad:");
			lblEdad.setForeground(Color.RED);
			lblEdad.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblEdad;
	}

	private JPanel getPanel_2() {
		if (pnCard == null) {
			pnCard = new JPanel();
			pnCard.setBorder(new MatteBorder(3, 2, 2, 3, (Color) new Color(0, 0, 0)));
			pnCard.setLayout(new CardLayout(0, 0));
			pnCard.add(getPanel_1_1(), "carreras");
			pnCard.add(getPnClasificaciones(), "clasificacion");
		}
		return pnCard;
	}

	private JPanel getPnClasificaciones() {
		if (pnClasificaciones == null) {
			pnClasificaciones = new JPanel();
			pnClasificaciones.setBackground(new Color(210, 180, 140));
			pnClasificaciones.setLayout(new BorderLayout(0, 0));
			pnClasificaciones.add(getPnSubClasis(), BorderLayout.CENTER);
			pnClasificaciones.add(getLblClasificacion(), BorderLayout.NORTH);
		}
		return pnClasificaciones;
	}

	private JLabel getLbDatosEdad() {
		if (lbDatosEdad == null) {
			lbDatosEdad = new JLabel("");
			lbDatosEdad.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDatosEdad;
	}

	private JPanel getPnSubClasis() {
		if (pnSubClasis == null) {
			pnSubClasis = new JPanel();
			pnSubClasis.setLayout(new BorderLayout(0, 0));
			pnSubClasis.add(getPnAtras(), BorderLayout.EAST);
			pnSubClasis.add(getPanel_1(), BorderLayout.CENTER);
		}
		return pnSubClasis;
	}

	private JLabel getLblClasificacion() {
		if (lblClasificacion == null) {
			lblClasificacion = new JLabel("<dynamic>");
			lblClasificacion.setFont(new Font("Tahoma", Font.PLAIN, 19));
		}
		return lblClasificacion;
	}

	private JPanel getPanel_1_1() {
		if (pnCarreras == null) {
			pnCarreras = new JPanel();
			pnCarreras.setLayout(new BorderLayout(0, 0));
			pnCarreras.add(getPanel_1_2(), BorderLayout.CENTER);
			pnCarreras.add(getPnLabelsCarreras(), BorderLayout.NORTH);
		}
		return pnCarreras;
	}

	private JPanel getPanel_1_2() {
		if (pnSubCarreras == null) {
			pnSubCarreras = new JPanel();
			pnSubCarreras.setLayout(new GridLayout(0, 3, 0, 0));
			pnSubCarreras.add(getPanel_1_3());
			pnSubCarreras.add(getPanel_1_4());
			pnSubCarreras.add(getPanel_1_5());
		}
		return pnSubCarreras;
	}

	private JPanel getPanel_1_3() {
		if (pnSelectCarrera == null) {
			pnSelectCarrera = new JPanel();
			pnSelectCarrera.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
			pnSelectCarrera.setBackground(SystemColor.info);
			pnSelectCarrera.setLayout(new GridLayout(2, 0, 0, 0));
		}
		return pnSelectCarrera;
	}

	private JPanel getPanel_1_4() {
		if (pnEstadoInscripcion == null) {
			pnEstadoInscripcion = new JPanel();
			pnEstadoInscripcion.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 0, 0)));
			pnEstadoInscripcion.setBackground(SystemColor.info);
			pnEstadoInscripcion.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return pnEstadoInscripcion;
	}

	private JPanel getPanel_1_5() {
		if (pnAccederClasificacion == null) {
			pnAccederClasificacion = new JPanel();
			pnAccederClasificacion.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 0, 0)));
			pnAccederClasificacion.setBackground(SystemColor.info);
			pnAccederClasificacion.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return pnAccederClasificacion;
	}

	private JPanel getPnLabelsCarreras() {
		if (pnLabelsCarreras == null) {
			pnLabelsCarreras = new JPanel();
			pnLabelsCarreras.setBackground(new Color(210, 180, 140));
			pnLabelsCarreras.setLayout(new GridLayout(0, 3, 0, 0));
			pnLabelsCarreras.add(getLblCarrerasInscritas());
			pnLabelsCarreras.add(getLblEstadoInscripcion());
			pnLabelsCarreras.add(getLblAccesoClasificacin());
		}
		return pnLabelsCarreras;
	}

	private JLabel getLblCarrerasInscritas() {
		if (lblCarrerasDisponibles == null) {
			lblCarrerasDisponibles = new JLabel("Carreras inscritas");
			lblCarrerasDisponibles.setFont(new Font("Tahoma", Font.BOLD, 19));
		}
		return lblCarrerasDisponibles;
	}

	private JLabel getLblEstadoInscripcion() {
		if (lblEstadoInscripcion == null) {
			lblEstadoInscripcion = new JLabel(" Estado inscripcion");
			lblEstadoInscripcion.setFont(new Font("Tahoma", Font.BOLD, 19));
		}
		return lblEstadoInscripcion;
	}

	private JLabel getLblAccesoClasificacin() {
		if (lblAccesoClasificacin == null) {
			lblAccesoClasificacin = new JLabel("Acceso clasificacion");
			lblAccesoClasificacin.setFont(new Font("Tahoma", Font.BOLD, 19));
		}
		return lblAccesoClasificacin;
	}

	private JLabel getLbDatosNombre() {
		if (lbDatosNombre == null) {
			lbDatosNombre = new JLabel("");
			lbDatosNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDatosNombre;
	}

	private JLabel getLbDatosDNI() {
		if (lbDatosDNI == null) {
			lbDatosDNI = new JLabel("");
			lbDatosDNI.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDatosDNI;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("  Fecha de nacimiento:");
			lblFecha.setForeground(new Color(255, 0, 0));
			lblFecha.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblFecha;
	}

	private JLabel getLbDatosFecha() {
		if (lbDatosFecha == null) {
			lbDatosFecha = new JLabel("");
			lbDatosFecha.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDatosFecha;
	}

	private JLabel getLblDireccion() {
		if (lblDireccion == null) {
			lblDireccion = new JLabel("  Direcci\u00F3n:");
			lblDireccion.setForeground(new Color(255, 0, 0));
			lblDireccion.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblDireccion;
	}

	private JLabel getLbDatosDireccion() {
		if (lbDatosDireccion == null) {
			lbDatosDireccion = new JLabel("");
			lbDatosDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDatosDireccion;
	}

	private JLabel getLblCodigoPostal() {
		if (lblCodigoPostal == null) {
			lblCodigoPostal = new JLabel("  Codigo Postal:");
			lblCodigoPostal.setForeground(new Color(255, 0, 0));
			lblCodigoPostal.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblCodigoPostal;
	}

	private JLabel getLbDatosCodigo() {
		if (lbDatosCodigo == null) {
			lbDatosCodigo = new JLabel("");
			lbDatosCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDatosCodigo;
	}

	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("  Correo:");
			lblCorreo.setForeground(new Color(255, 0, 0));
			lblCorreo.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblCorreo;
	}

	private JLabel getLbDatosCorreo() {
		if (lbDatosCorreo == null) {
			lbDatosCorreo = new JLabel("");
			lbDatosCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDatosCorreo;
	}

	private JLabel getLblLocalidad() {
		if (lblLocalidad == null) {
			lblLocalidad = new JLabel("  Localidad");
			lblLocalidad.setForeground(new Color(255, 0, 0));
			lblLocalidad.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblLocalidad;
	}

	private JLabel getLbDatosLocalidad() {
		if (lbDatosLocalidad == null) {
			lbDatosLocalidad = new JLabel("");
			lbDatosLocalidad.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDatosLocalidad;
	}

	private JPanel getPnAtras() {
		if (pnAtras == null) {
			pnAtras = new JPanel();
			pnAtras.setBackground(SystemColor.info);
			pnAtras.setLayout(new BorderLayout(0, 0));
			pnAtras.add(getBtnAtrs(), BorderLayout.SOUTH);
		}
		return pnAtras;
	}

	private JButton getBtnAtrs() {
		if (btnAtrs == null) {
			btnAtrs = new JButton("Atr\u00E1s");
			btnAtrs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CardLayout cl = (CardLayout) pnCard.getLayout();
					cl.show(pnCard, "carreras");
				}
			});
		}
		return btnAtrs;
	}

	private JPanel getPanel_1() {
		if (pnResultados == null) {
			pnResultados = new JPanel();
			pnResultados.setBackground(SystemColor.info);
			pnResultados.setLayout(new GridLayout(3, 3, 0, 0));
			pnResultados.add(getLbTiempo());
			pnResultados.add(getLbDatosTiempo());
			pnResultados.add(getLbPosAbsoluta());
			pnResultados.add(getLbDatosPosAbsoluta());
			pnResultados.add(getLbPosCategoria());
			pnResultados.add(getLbDatosPosCategoria());
		}
		return pnResultados;
	}

	private JLabel getLbTiempo() {
		if (lbTiempo == null) {
			lbTiempo = new JLabel("  Tiempo:");
			lbTiempo.setForeground(new Color(255, 0, 0));
			lbTiempo.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lbTiempo;
	}

	private JLabel getLbDatosTiempo() {
		if (lbDatosTiempo == null) {
			lbDatosTiempo = new JLabel("");
		}
		return lbDatosTiempo;
	}

	private JLabel getLbPosAbsoluta() {
		if (lbPosAbsoluta == null) {
			lbPosAbsoluta = new JLabel("  Posicion Absoluta:");
			lbPosAbsoluta.setForeground(new Color(255, 0, 0));
			lbPosAbsoluta.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lbPosAbsoluta;
	}

	private JLabel getLbDatosPosAbsoluta() {
		if (lbDatosPosAbsoluta == null) {
			lbDatosPosAbsoluta = new JLabel("");
		}
		return lbDatosPosAbsoluta;
	}

	private JLabel getLbPosCategoria() {
		if (lbPosCategoria == null) {
			lbPosCategoria = new JLabel("  Posicion en Categoria:");
			lbPosCategoria.setForeground(new Color(255, 0, 0));
			lbPosCategoria.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lbPosCategoria;
	}

	private JLabel getLbDatosPosCategoria() {
		if (lbDatosPosCategoria == null) {
			lbDatosPosCategoria = new JLabel("");
		}
		return lbDatosPosCategoria;
	}

	private JPanel getPanelBusqueda() {
		if (panelBusqueda == null) {
			panelBusqueda = new JPanel();
			panelBusqueda.setForeground(new Color(210, 180, 140));
			panelBusqueda.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(0, 0, 0)));
			panelBusqueda.setBackground(new Color(210, 180, 140));
			GridBagLayout gbl_panelBusqueda = new GridBagLayout();
			gbl_panelBusqueda.columnWidths = new int[] { 137, 86, 114, 136, 0, 0 };
			gbl_panelBusqueda.rowHeights = new int[] { 24, 17, 31, 0, 0 };
			gbl_panelBusqueda.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
			gbl_panelBusqueda.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
			panelBusqueda.setLayout(gbl_panelBusqueda);
			GridBagConstraints gbc_lblBuscarPor = new GridBagConstraints();
			gbc_lblBuscarPor.insets = new Insets(0, 0, 5, 5);
			gbc_lblBuscarPor.gridx = 0;
			gbc_lblBuscarPor.gridy = 1;
			panelBusqueda.add(getLblBuscarPor(), gbc_lblBuscarPor);
			GridBagConstraints gbc_rbDNI = new GridBagConstraints();
			gbc_rbDNI.insets = new Insets(0, 0, 5, 5);
			gbc_rbDNI.gridx = 1;
			gbc_rbDNI.gridy = 1;
			panelBusqueda.add(getRbDNI(), gbc_rbDNI);
			GridBagConstraints gbc_rbNombre = new GridBagConstraints();
			gbc_rbNombre.insets = new Insets(0, 0, 5, 5);
			gbc_rbNombre.gridx = 2;
			gbc_rbNombre.gridy = 1;
			panelBusqueda.add(getRbNombre(), gbc_rbNombre);
			GridBagConstraints gbc_rdbtnCorreo = new GridBagConstraints();
			gbc_rdbtnCorreo.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnCorreo.gridx = 3;
			gbc_rdbtnCorreo.gridy = 1;
			panelBusqueda.add(getRdbtnCorreo(), gbc_rdbtnCorreo);
			GridBagConstraints gbc_txtDatos = new GridBagConstraints();
			gbc_txtDatos.gridwidth = 3;
			gbc_txtDatos.insets = new Insets(0, 0, 5, 5);
			gbc_txtDatos.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDatos.gridx = 1;
			gbc_txtDatos.gridy = 2;
			panelBusqueda.add(getTxtDatos(), gbc_txtDatos);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 3;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 3;
			panelBusqueda.add(getScrollPane(), gbc_scrollPane);
		}
		return panelBusqueda;
	}

	private JRadioButton getRbDNI() {
		if (rbDNI == null) {
			rbDNI = new JRadioButton("DNI");
			rbDNI.setBackground(SystemColor.info);
			buttonGroup.add(rbDNI);
			rbDNI.setFont(new Font("Tahoma", Font.ITALIC, 13));
			rbDNI.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rbSeleccionado = DNI;
						// txtDatos.setText("");
					}
				}
			});
		}
		return rbDNI;
	}

	private JRadioButton getRbNombre() {
		if (rbNombre == null) {
			rbNombre = new JRadioButton("Nombre");
			rbNombre.setBackground(SystemColor.info);
			buttonGroup.add(rbNombre);
			rbNombre.setFont(new Font("Tahoma", Font.ITALIC, 13));
			rbDNI.setSelected(true);
			rbNombre.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rbSeleccionado = NOMBRE;
						txtDatos.setText("");
					}
				}
			});

		}
		return rbNombre;
	}

	private JRadioButton getRdbtnCorreo() {
		if (rdbtnCorreo == null) {
			rdbtnCorreo = new JRadioButton("Correo");
			rdbtnCorreo.setBackground(SystemColor.info);
			buttonGroup.add(rdbtnCorreo);
			rdbtnCorreo.setFont(new Font("Tahoma", Font.ITALIC, 13));
			rdbtnCorreo.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						rbSeleccionado = CORREO;
						txtDatos.setText("");
					}
				}
			});

		}
		return rdbtnCorreo;
	}

	private JLabel getLblBuscarPor() {
		if (lblBuscarPor == null) {
			lblBuscarPor = new JLabel("Buscar por:");
			lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 13));

		}
		return lblBuscarPor;
	}

	private JList<Usuario> getListaUsuarios() {
		if (listaUsuarios == null) {
			listaUsuarios = new JList<Usuario>();
			listaUsuarios.setBackground(SystemColor.info);
			modelList();
			listaUsuarios.setModel(modeloLista);
			listaUsuarios.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent lse) {
					if (lse.getValueIsAdjusting()) {
						muestraPnCarreras();
						user = listaUsuarios.getSelectedValue();
						updateInfoCarreras();
						updateInfoUsuario();
						contentPane.updateUI();
					}
				}
			});
		}
		return listaUsuarios;
	}

	private void muestraPnCarreras() {
		CardLayout cl = (CardLayout) pnCard.getLayout();
		cl.show(pnCard, "carreras");
	}

	private DefaultListModel<Usuario> modelList() {
		modeloLista = new DefaultListModel<>();
		ArrayList<Usuario> usuarios = gestor.getUsuarios();
		for (Usuario usuario : usuarios) {
			modeloLista.addElement(usuario);
		}
		return modeloLista;
	}

	private JTextField getTxtDatos() {
		if (txtDatos == null) {
			txtDatos = new JTextField();
			txtDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!txtmemoria.toLowerCase().equals(txtDatos.getText().toLowerCase())) {
						txtmemoria = txtDatos.getText();
						actualizarLista();
					}
				}
			});
			txtDatos.setColumns(10);
			txtmemoria = "";

		}
		return txtDatos;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getListaUsuarios());

		}
		return scrollPane;

	}

	// ==========================================================================================
	// 											LOGICA:
	// ==========================================================================================

	/**
	 * Actualiza el layout
	 * 
	 * @param filas
	 */
	private void updateLayout(int filas) {
		pnSelectCarrera.setLayout(new GridLayout(filas, 1, 1, 1));
		pnEstadoInscripcion.setLayout(new GridLayout(filas, 1, 1, 1));
		pnAccederClasificacion.setLayout(new GridLayout(filas, 1, 1, 1));

	}

	/**
	 * Actualiza la lista de usuarios
	 */
	private void actualizarLista() {
		modeloLista = new DefaultListModel<>();
		ArrayList<Usuario> usuarios = gestor.getUsuarios();
		for (Usuario usuario : usuarios) {
			if (rbSeleccionado == DNI) {
				if (usuario.getDni().contains(txtmemoria))
					modeloLista.addElement(usuario);
			} else if (rbSeleccionado == NOMBRE) {
				if (usuario.getNombre().contains(txtmemoria))
					modeloLista.addElement(usuario);
			} else if (rbSeleccionado == CORREO) {
				if (usuario.getCorreo().contains(txtmemoria))
					modeloLista.addElement(usuario);
			}

		}
		listaUsuarios.setModel(modeloLista);
	}

	/**
	 * Actualiza la informacion del usuario
	 */
	private void updateInfoUsuario() {
		lbDatosNombre.setText(user.getNombre());
		lbDatosDNI.setText(user.getDni());
		lbDatosCodigo.setText(user.getCodigo_postal());
		lbDatosCorreo.setText(user.getCorreo());
		lbDatosFecha.setText(user.getFecha_nacimiento());
		lbDatosDireccion.setText(user.getDireccion());
		lbDatosLocalidad.setText(user.getLocalidad());
		lbDatosEdad.setText(user.getEdad() + "");
	}

	/**
	 * Muestra la clasificacion del usuario de la carrera pasada por parametro
	 * 
	 * @param c,
	 *            Carrera
	 */
	private void mostrarClasificacion(Carrera c) {
		CardLayout cl = (CardLayout) pnCard.getLayout();
		cl.show(pnCard, "clasificacion");
		lblClasificacion.setText("Clasificación " + c.getNombre() + ":");
		if (user != null) {
			Corredor corredor = user.getCorredor(c);
			lbDatosTiempo.setText(corredor.getTiempos().get(corredor.getTiempos().size() - 1).toString());
			lbDatosPosAbsoluta.setText(corredor.getPosicionAbsoluta());
			lbDatosPosCategoria.setText(corredor.getPosicionCategoria());

		}
	}

	/**
	 * Actualiza la información relativa a las carreras
	 */
	private void updateInfoCarreras() {
		pnSelectCarrera.removeAll();
		pnEstadoInscripcion.removeAll();
		pnAccederClasificacion.removeAll();

		ArrayList<Carrera> carreras = user.getCarreras();
		int filas = carreras.size();
		updateLayout(filas);
		for (Carrera c : carreras) {
			pnSelectCarrera.add(new JLabel(" " + c.getNombre()));
			pnSelectCarrera.doLayout();
			if (user.isInscrito(c)) {
				try {
					estadoInscripcion = null;
					estadoInscripcion = GestorDB.getNotasPagoInscrito(user.getDni(), c);
					JButton btn = new JButton("Ver Estado");
					btn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							EstadoCompeticion dialog;
							dialog = new EstadoCompeticion(estadoInscripcion,c,gestor,user);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dialog.setLocationRelativeTo(null);
							dialog.setResizable(false);
						}
					});
					pnEstadoInscripcion.add(btn);
					
				} catch (SQLException ex) {
					GestorDB.handleSQLException(ex);
				}
			} else {
				comprobarCarreraUpdate(c);
			}
			JButton btnClas = new JButton("Ver clasificacion");
			btnClas.setEnabled(false);
//			if (user.getCorredor(c) != null && user.getCorredor(c).getTiempos().get(user.getCorredor(c).getTiempos().size() - 1) != null) {
//				btnClas.setEnabled(true);
//			}
			pnAccederClasificacion.add(btnClas);
			if (c.isFinalizada()) {
				pnAccederClasificacion.setEnabled(true);
				btnClas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarClasificacion(c);
					}
				});
			} else {
				btnClas.setEnabled(false);
			}
		}
	}


	/**
	 * Hace las comprobaciones correspondientes y actualiza la informacion relativa
	 * a la carrera pasada por parametro
	 * 
	 * @param c,
	 *            Carrera
	 */
	private void comprobarCarreraUpdate(Carrera c) {
		String[] fecha_pagado = null; // Fecha de pago REAL (csv)
		DateTime oldDateTime = new DateTime();

		// Cargar fecha de pago del csv (más antigua)
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(VentanaPrincipal.FICHERO_EXTRACTOS));
			String linea = reader.readLine();
			while (linea != null) {
				String[] datos = linea.split(";");
				DateTime newDateTime = new DateTime(
						datos[3].split("/")[2] + "-" + datos[3].split("/")[1] + "-" + datos[3].split("/")[0]);
				if (user.getDni().equals(datos[1]) && newDateTime.isBefore(oldDateTime)) {
					fecha_pagado = datos[3].split("/");
					oldDateTime = newDateTime;
				}
				linea = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (fecha_pagado != null) {
			String[] fecha_pago = null; // Fecha en la que se formalizo la inscripcion con transferencia
			try {
				String aux = GestorDB.getFechaPago(user.getDni());
				if (aux != null)
					fecha_pago = aux.split("/");
			} catch (SQLException ex) {
				GestorDB.handleSQLException(ex);
			}

			if (fecha_pago != null) {
				GregorianCalendar auxDate = new GregorianCalendar(Integer.parseInt(fecha_pago[0]),
						Integer.parseInt(fecha_pago[1]), Integer.parseInt(fecha_pago[2])); // Fecha auxiliar

				// CASOS: Año actual mayor que el de pago / Mismo año, y mes actual al menos 2
				// unidades mayor que el de pago / Mismo año y mes, y día actual más de 2
				// unidades mayor que el de pago / Mismo año, y mes actual posterior al de pago,
				// y día actual más de 2 unidades mayor que el de pago
				if (Integer.parseInt(fecha_pagado[2]) > Integer.parseInt(fecha_pago[2])
						|| (Integer.parseInt(fecha_pagado[2]) == Integer.parseInt(fecha_pago[2])
								&& Integer.parseInt(fecha_pagado[1]) - Integer.parseInt(fecha_pago[1]) >= 2)
						|| (Integer.parseInt(fecha_pagado[2]) == Integer.parseInt(fecha_pago[2])
								&& Integer.parseInt(fecha_pagado[1]) == Integer.parseInt(fecha_pago[1])
								&& Integer.parseInt(fecha_pagado[0]) - Integer.parseInt(fecha_pago[0]) > 2)
						|| (Integer.parseInt(fecha_pagado[2]) == Integer.parseInt(fecha_pago[2])
								&& Integer.parseInt(fecha_pagado[1]) - Integer.parseInt(fecha_pago[1]) == 1
								&& Integer.parseInt(fecha_pagado[0])
										+ (auxDate.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)
												- Integer.parseInt(fecha_pago[0])) > 2)) {
					JButton btn = new JButton("Ver Estado");
					btn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							EstadoCompeticion dialog;
							dialog = new EstadoCompeticion("Cancelada - Limite de 48h superado",c,gestor,user);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dialog.setLocationRelativeTo(null);
							dialog.setResizable(false);
						}
					});
					pnEstadoInscripcion.add(btn);
					try {
						GestorDB.setNotasPago("Cancelada - Limite de 48h superado", user.getDni(), c);
					} catch (SQLException ex) {
						GestorDB.handleSQLException(ex);
					}
				} else {
					try {
						String state = GestorDB.getNotasPagoPreinscrito(user.getDni(), c);
						JButton btn = new JButton("Ver estado");
						btn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								EstadoCompeticion dialog;
								dialog = new EstadoCompeticion(state,c,gestor,user);
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
								dialog.setLocationRelativeTo(null);
								dialog.setResizable(false);
							}
						});
						pnEstadoInscripcion.add(btn);
					} catch (SQLException ex) {
						GestorDB.handleSQLException(ex);
					}
				}

			}

			String str = null;
			try {
				str = GestorDB.getNotasPagoPreinscrito(user.getDni(), c);
			} catch (SQLException ex) {
				GestorDB.handleSQLException(ex);
			}
			if (!user.isInscrito(c) && str != null && !str.toLowerCase().contains("cancela")) {
				JButton btn = new JButton("Ver Estado");
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EstadoCompeticion dialog;
						dialog = new EstadoCompeticion("Pendiente de confirmacion",c,gestor,user);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						dialog.setLocationRelativeTo(null);
						dialog.setResizable(false);
					}
				});
				pnEstadoInscripcion.add(btn);
				try {
					GestorDB.setNotasPago("Pendiente de confirmacion", user.getDni(), c);
				} catch (SQLException ex) {
					GestorDB.handleSQLException(ex);
				}
			}
		} else {
			try {
				JButton btn = new JButton("Ver Estado");
				String state = GestorDB.getNotasPagoPreinscrito(user.getDni(), c);
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EstadoCompeticion dialog;
						dialog = new EstadoCompeticion(state,c,gestor,user);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						dialog.setLocationRelativeTo(null);
						dialog.setResizable(false);
					}
				});
				pnEstadoInscripcion.add(btn);
			} catch (SQLException ex) {
				GestorDB.handleSQLException(ex);
			}
		}

		
	}
}
