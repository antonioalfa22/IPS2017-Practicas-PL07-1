package igu;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entities.Carrera;
import entities.Corredor;
import entities.Usuario;
import logic.GestorApp;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JList;

/**
 * 
 * @author UO252406
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
	private JLabel lblCategoria;
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
	private JLabel lbDatosCategoria;
	private JLabel lblFecha;
	private JLabel lbDatosFecha;
	private JLabel lblDireccion;
	private JLabel lbDatosDireccion;
	private JLabel lblCodigoPostal;
	private JLabel lbDatosCodigo;
	private JLabel lblCorreo;
	private JLabel lbDatosCorreo;
	private JLabel lblLocalidad;
	private JLabel lbDatosLocalidad;
	
	
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
	
	
	/**
	 * Create the frame.
	 */
	public VentanaUsuario(GestorApp g) {
		this.gestor = g;
		setTitle("Ventana Usuario");
		setBounds(100, 100, 1017, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelBusqueda(), BorderLayout.NORTH);
		contentPane.add(getPnInfoPersonal(), BorderLayout.WEST);
		contentPane.add(getPanel_2(), BorderLayout.CENTER);
		if(user!=null) {
			updateInfoUsuario();
			updateInfoCarreras();
		}
	}


	private JPanel getPnInfoPersonal() {
		if (pnInfoPersonal == null) {
			pnInfoPersonal = new JPanel();
			pnInfoPersonal.setLayout(new GridLayout(1, 1, 0, 0));
			pnInfoPersonal.add(getPnInfo());
		}
		return pnInfoPersonal;
	}
	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			pnInfo.setLayout(new BorderLayout(0, 0));
			pnInfo.add(getLbInfo(), BorderLayout.NORTH);
			pnInfo.add(getPnSubInfo(), BorderLayout.CENTER);
		}
		return pnInfo;
	}
	private JLabel getLbInfo() {
		if (lbInfo == null) {
			lbInfo = new JLabel("Informaci\u00F3n personal");
			lbInfo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		}
		return lbInfo;
	}
	private JPanel getPnSubInfo() {
		if (pnSubInfo == null) {
			pnSubInfo = new JPanel();
			pnSubInfo.setLayout(new GridLayout(8, 2, 0, 0));
			pnSubInfo.add(getLblNombre());
			pnSubInfo.add(getLbDatosNombre());
			pnSubInfo.add(getLblDNI());
			pnSubInfo.add(getLbDatosDNI());
			pnSubInfo.add(getLblCategoria());
			pnSubInfo.add(getLbDatosCategoria());
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
			lblNombre.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblNombre;
	}
	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("  DNI:");
			lblDNI.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblDNI;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("  Categoria:");
			lblCategoria.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblCategoria;
	}
	private JPanel getPanel_2() {
		if (pnCard == null) {
			pnCard = new JPanel();
			pnCard.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			pnCard.setLayout(new CardLayout(0, 0));
			pnCard.add(getPanel_1_1(), "carreras");
			pnCard.add(getPnClasificaciones(), "clasificacion");
		}
		return pnCard;
	}
	private JPanel getPnClasificaciones() {
		if (pnClasificaciones == null) {
			pnClasificaciones = new JPanel();
			pnClasificaciones.setLayout(new BorderLayout(0, 0));
			pnClasificaciones.add(getPnSubClasis(), BorderLayout.CENTER);
			pnClasificaciones.add(getLblClasificacion(), BorderLayout.NORTH);
		}
		return pnClasificaciones;
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
			pnSelectCarrera.setLayout(new GridLayout(2,0,0,0));
		}
		return pnSelectCarrera;
	}
	private JPanel getPanel_1_4() {
		if (pnEstadoInscripcion == null) {
			pnEstadoInscripcion = new JPanel();
			pnEstadoInscripcion.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return pnEstadoInscripcion;
	}
	private JPanel getPanel_1_5() {
		if (pnAccederClasificacion == null) {
			pnAccederClasificacion = new JPanel();
			pnAccederClasificacion.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return pnAccederClasificacion;
	}
	private JPanel getPnLabelsCarreras() {
		if (pnLabelsCarreras == null) {
			pnLabelsCarreras = new JPanel();
			pnLabelsCarreras.setLayout(new GridLayout(0, 3, 0, 0));
			pnLabelsCarreras.add(getLblCarrerasDisponibles());
			pnLabelsCarreras.add(getLblEstadoInscripcion());
			pnLabelsCarreras.add(getLblAccesoClasificacin());
		}
		return pnLabelsCarreras;
	}
	private JLabel getLblCarrerasDisponibles() {
		if (lblCarrerasDisponibles == null) {
			lblCarrerasDisponibles = new JLabel("Carreras disponibles");
			lblCarrerasDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 19));
		}
		return lblCarrerasDisponibles;
	}
	private JLabel getLblEstadoInscripcion() {
		if (lblEstadoInscripcion == null) {
			lblEstadoInscripcion = new JLabel(" Estado inscripci\u00F3n");
			lblEstadoInscripcion.setFont(new Font("Tahoma", Font.PLAIN, 19));
		}
		return lblEstadoInscripcion;
	}
	private JLabel getLblAccesoClasificacin() {
		if (lblAccesoClasificacin == null) {
			lblAccesoClasificacin = new JLabel("Acceso clasificaci\u00F3n");
			lblAccesoClasificacin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		}
		return lblAccesoClasificacin;
	}
	private JLabel getLbDatosNombre() {
		if (lbDatosNombre == null) {
			lbDatosNombre = new JLabel("");
		}
		return lbDatosNombre;
	}
	private JLabel getLbDatosDNI() {
		if (lbDatosDNI == null) {
			lbDatosDNI = new JLabel("");
		}
		return lbDatosDNI;
	}
	private JLabel getLbDatosCategoria() {
		if (lbDatosCategoria == null) {
			lbDatosCategoria = new JLabel("");
		}
		return lbDatosCategoria;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("  Fecha de nacimiento:");
			lblFecha.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblFecha;
	}
	private JLabel getLbDatosFecha() {
		if (lbDatosFecha == null) {
			lbDatosFecha = new JLabel("");
		}
		return lbDatosFecha;
	}
	private JLabel getLblDireccion() {
		if (lblDireccion == null) {
			lblDireccion = new JLabel("  Direcci\u00F3n:");
			lblDireccion.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblDireccion;
	}
	private JLabel getLbDatosDireccion() {
		if (lbDatosDireccion == null) {
			lbDatosDireccion = new JLabel("");
		}
		return lbDatosDireccion;
	}
	private JLabel getLblCodigoPostal() {
		if (lblCodigoPostal == null) {
			lblCodigoPostal = new JLabel("  Codigo Postal:");
			lblCodigoPostal.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblCodigoPostal;
	}
	private JLabel getLbDatosCodigo() {
		if (lbDatosCodigo == null) {
			lbDatosCodigo = new JLabel("");
		}
		return lbDatosCodigo;
	}
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("  Correo:");
			lblCorreo.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblCorreo;
	}
	private JLabel getLbDatosCorreo() {
		if (lbDatosCorreo == null) {
			lbDatosCorreo = new JLabel("");
		}
		return lbDatosCorreo;
	}
	private JLabel getLblLocalidad() {
		if (lblLocalidad == null) {
			lblLocalidad = new JLabel("  Localidad");
			lblLocalidad.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblLocalidad;
	}
	private JLabel getLbDatosLocalidad() {
		if (lbDatosLocalidad == null) {
			lbDatosLocalidad = new JLabel("");
		}
		return lbDatosLocalidad;
	}
	
	
	private JPanel getPnAtras() {
		if (pnAtras == null) {
			pnAtras = new JPanel();
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
					cl.show(pnCard,"carreras");
				}
			});
		}
		return btnAtrs;
	}
	private JPanel getPanel_1() {
		if (pnResultados == null) {
			pnResultados = new JPanel();
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
			lbPosCategoria = new JLabel("  Posicion en Categor\u00EDa:");
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
		for(Carrera c:carreras) {
			c.setFinalizada(true);
			pnSelectCarrera.add(new JLabel(" "+c.getNombre()));
			pnSelectCarrera.doLayout();
			if(user.isInscrito(c)) {
				pnEstadoInscripcion.add(new JLabel("Pagada"));
				pnEstadoInscripcion.doLayout();
				
			}else {
				JLabel pago = new JLabel("Pendiente de pago");
				JButton btn = new JButton("Pagar");
				for (Corredor u : gestor.getTodosLosCorredores(c)) {
					if(u.getDni().equals(user.getDni())) {
						btn.setEnabled(false);
						pago.setText("Pagada");
					}
						
				}
				pnEstadoInscripcion.add(btn);
				pnEstadoInscripcion.add(pago);
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VentanaPago vp = new VentanaPago(c,user);
						vp.setVisible(true);
						dispose();
					}
				});
			}
			JButton btn = new JButton("Ver clasificación");
			pnAccederClasificacion.add(btn);
			if(c.isFinalizada()) {
				pnAccederClasificacion.setEnabled(true);
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarClasificacion(c);
					}
				});
			}else {
				btn.setEnabled(false);
			}
		}
	}
	
	
	private void updateLayout(int filas) {
		pnSelectCarrera.setLayout(new GridLayout(filas, 1, 1, 1));
		pnEstadoInscripcion.setLayout(new GridLayout(filas, 1, 1, 1));
		pnAccederClasificacion.setLayout(new GridLayout(filas, 1, 1, 1));

	}


	private void mostrarClasificacion(Carrera c) {
		CardLayout cl = (CardLayout) pnCard.getLayout();
		cl.show(pnCard, "clasificacion");
		lblClasificacion.setText("Clasificación " + c.getNombre() + ":");
		if(user!=null) {
			Corredor corredor = user.getCorredor(c);
			lbDatosTiempo.setText(String.valueOf(corredor.getTiempo()));
			lbDatosPosAbsoluta.setText(corredor.getPosicionAbsoluta());
			lbDatosPosCategoria.setText(corredor.getPosicionCategoria());
		}

	}
	
	private void updateInfoUsuario() {
		lbDatosNombre.setText(user.getNombre());
		lbDatosDNI.setText(user.getDni());
		lbDatosCategoria.setText(user.getCategoria());
		lbDatosCodigo.setText(user.getCodigo_postal());
		lbDatosCorreo.setText(user.getCorreo());
		lbDatosFecha.setText(user.getFecha_nacimiento());
		lbDatosDireccion.setText(user.getDireccion());
		lbDatosLocalidad.setText(user.getLocalidad());
	}
	
	
	private JPanel getPanelBusqueda() {
		if (panelBusqueda == null) {
			panelBusqueda = new JPanel();
			GridBagLayout gbl_panelBusqueda = new GridBagLayout();
			gbl_panelBusqueda.columnWidths = new int[]{137, 86, 114, 136, 0, 0};
			gbl_panelBusqueda.rowHeights = new int[]{24, 17, 31, 0, 0};
			gbl_panelBusqueda.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panelBusqueda.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
			GridBagConstraints gbc_listaUsuarios = new GridBagConstraints();
			gbc_listaUsuarios.gridwidth = 3;
			gbc_listaUsuarios.insets = new Insets(0, 0, 0, 5);
			gbc_listaUsuarios.fill = GridBagConstraints.BOTH;
			gbc_listaUsuarios.gridx = 1;
			gbc_listaUsuarios.gridy = 3;
			panelBusqueda.add(getListaUsuarios(), gbc_listaUsuarios);
		}
		return panelBusqueda;
	}
	private JRadioButton getRbDNI() {
		if (rbDNI == null) {
			rbDNI = new JRadioButton("DNI");
			buttonGroup.add(rbDNI);
			rbDNI.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return rbDNI;
	}
	private JRadioButton getRbNombre() {
		if (rbNombre == null) {
			rbNombre = new JRadioButton("Nombre");
			buttonGroup.add(rbNombre);
			rbNombre.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return rbNombre;
	}
	private JRadioButton getRdbtnCorreo() {
		if (rdbtnCorreo == null) {
			rdbtnCorreo = new JRadioButton("Correo");
			buttonGroup.add(rdbtnCorreo);
			rdbtnCorreo.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return rdbtnCorreo;
	}
	private JLabel getLblBuscarPor() {
		if (lblBuscarPor == null) {
			lblBuscarPor = new JLabel("Buscar por:");
			lblBuscarPor.setFont(new Font("Tahoma", Font.ITALIC, 13));
		}
		return lblBuscarPor;
	}
	private JTextField getTxtDatos() {
		if (txtDatos == null) {
			txtDatos = new JTextField();
			txtDatos.setColumns(10);
		}
		return txtDatos;
	}
	private JList<Usuario> getListaUsuarios() {
		if (listaUsuarios == null) {
			listaUsuarios = new JList<Usuario>();
			modelList();
			listaUsuarios.setModel(modeloLista);
		    listaUsuarios.addListSelectionListener(new ListSelectionListener() {
		        public void valueChanged(ListSelectionEvent lse) {
		            if (lse.getValueIsAdjusting()) {
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
	
	private DefaultListModel<Usuario> modelList(){
		   modeloLista = new DefaultListModel<>();
		   ArrayList<Usuario> usuarios = gestor.getUsuarios();
		   for (Usuario usuario : usuarios) {
			   modeloLista.addElement(usuario);
		   }
		   return modeloLista;
	}
}
