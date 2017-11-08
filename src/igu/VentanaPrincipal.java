package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.toedter.calendar.JCalendar;
import entities.Carrera;
import logic.Date;
import logic.FechaInscripcion;
import logic.GestorApp;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JCheckBox;


/**
 * Ventana que representa todas las carreras en las que un usuario puede inscribirse
 * 
 * @author Antonio Payá González
 *
 */
public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperior;
	private JLabel lbTitulo;
	private JMenuBar menuSuperior;
	private JMenu mnAyuda;
	private JPanel panelCentro;
	private JPanel panelFiltros;
	private JLayeredPane panelCarreras;
	private JLabel lbFiltros;
	public static GestorApp gestorCarreras;
	private int numPaneles;
	private int numCarreras;
	private JCalendar calendar;
	private ArrayList<Carrera> carreras;
	private JMenu mnOrganizador;
	private JMenuItem mntmGestionarcarreras;
	private JMenu mnUsuario;
	private JMenuItem mntmVerCarreras;
	private JMenuItem mntmClasificaciones;
	private JLabel lbNombreCarreraFiltro;
	private JTextField txtNombreCarrera;
	private JLabel lbTipoCarreraFiltro;
	private JCheckBox chckbxUrbana;
	private JCheckBox chcbxMontain;
	private String txtmemoria;
	private JMenuItem mntmCrearCarrera;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Properties props = new Properties();
					props.put("logoString", "");
					AluminiumLookAndFeel.setCurrentTheme(props);
					UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
					
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VentanaPrincipal() throws SQLException {
		gestorCarreras = new GestorApp();
		this.carreras = gestorCarreras.carreras;
		setBackground(new Color(153, 153, 204));
		setFont(new Font("Century Gothic", Font.PLAIN, 15));
		setTitle("TicketRun");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1128, 643);
		setJMenuBar(getMenuSuperior());
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelSuperior(), BorderLayout.NORTH);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
		actualizaDorsales();
	}
	
	//==========================================================================================
	//										MENUS: 
	//==========================================================================================
	
	private JMenuBar getMenuSuperior() {
		if (menuSuperior == null) {
			menuSuperior = new JMenuBar();
			menuSuperior.add(getMnUsuario());
			menuSuperior.add(getMnOrganizador());
			menuSuperior.add(getMnAyuda());
		}
		return menuSuperior;
	}
	
	//**************MENU ORGANIZADOR************
	private JMenu getMnOrganizador() {
		if (mnOrganizador == null) {
			mnOrganizador = new JMenu("Organizador");
			mnOrganizador.add(getMntmCrearCarrera());
			mnOrganizador.add(getMntmGestionarcarreras());
			mnOrganizador.add(getMntmClasificaciones());
		}
		return mnOrganizador;
	}
	
	private JMenuItem getMntmCrearCarrera() {
		if (mntmCrearCarrera == null) {
			mntmCrearCarrera = new JMenuItem("Crear Carrera");
			mntmCrearCarrera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RegistroCarrera dialog;
					dialog = new RegistroCarrera();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setLocationRelativeTo(null);
					dialog.setResizable(false);		
				}
			});
			mntmCrearCarrera.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		}
		return mntmCrearCarrera;
	}
	
	private JMenuItem getMntmGestionarcarreras() {
		if (mntmGestionarcarreras == null) {
			mntmGestionarcarreras = new JMenuItem("GestionarCarreras");
			mntmGestionarcarreras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
			mntmGestionarcarreras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaInscritos dialog;
					try {
						dialog = new VentanaInscritos();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						dialog.setLocationRelativeTo(null);
						dialog.setResizable(false);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}			
				}
			});
		}
		return mntmGestionarcarreras;
	}
	
	private JMenuItem getMntmClasificaciones() {
		if (mntmClasificaciones == null) {
			mntmClasificaciones = new JMenuItem("Clasificaciones");
			mntmClasificaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaClasificacion dialog;
					try {
						dialog = new VentanaClasificacion();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						dialog.setLocationRelativeTo(null);
						dialog.setResizable(false);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}			
				}
			});
		}
		return mntmClasificaciones;
	}
	
	//**************MENU USUARIO************
	
	private JMenu getMnUsuario() {
		if (mnUsuario == null) {
			mnUsuario = new JMenu("Usuario");
			mnUsuario.add(getMntmVerCarreras());
		}
		return mnUsuario;
	}
	private JMenuItem getMntmVerCarreras() {
		if (mntmVerCarreras == null) {
			mntmVerCarreras = new JMenuItem("Ver Carreras");
			mntmVerCarreras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaUsuario dialog = new VentanaUsuario(gestorCarreras);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setLocationRelativeTo(null);
					dialog.setResizable(true);
				}
			});
			mntmVerCarreras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		}
		return mntmVerCarreras;
	}
	
		//**************MENU AYUDA************
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('a');
		}
		return mnAyuda;
	}
	
	//==========================================================================================
	//										PANEL SUPERIOR: 
	//==========================================================================================
	private JPanel getPanelSuperior() {
		if (panelSuperior == null) {
			panelSuperior = new JPanel();
			panelSuperior.setBackground(Color.DARK_GRAY);
			panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelSuperior.add(getLbTitulo());
		}
		return panelSuperior;
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("TICKET RUN");
			lbTitulo.setForeground(new Color(135, 206, 250));
			lbTitulo.setFont(new Font("Showcard Gothic", Font.BOLD, 60));
		}
		return lbTitulo;
	}
	
	//==========================================================================================
	//										PANEL CENTRAL: 
	//==========================================================================================
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBackground(UIManager.getColor("Button.light"));
			GridBagLayout gbl_panelCentro = new GridBagLayout();
			gbl_panelCentro.columnWidths = new int[] {376, 506, 0};
			gbl_panelCentro.rowHeights = new int[]{0, 0};
			gbl_panelCentro.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panelCentro.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panelCentro.setLayout(gbl_panelCentro);
			getPanelFiltros().setLayout(null);
			GridBagConstraints gbc_panelFiltros = new GridBagConstraints();
			gbc_panelFiltros.insets = new Insets(0, 0, 0, 5);
			gbc_panelFiltros.fill = GridBagConstraints.BOTH;
			gbc_panelFiltros.gridx = 0;
			gbc_panelFiltros.gridy = 0;
			panelCentro.add(getPanelFiltros(), gbc_panelFiltros);
			GridBagConstraints gbc_panelCarreras = new GridBagConstraints();
			gbc_panelCarreras.fill = GridBagConstraints.BOTH;
			gbc_panelCarreras.gridx = 1;
			gbc_panelCarreras.gridy = 0;
			panelCentro.add(getPanelCarreras(), gbc_panelCarreras);
		}
		return panelCentro;
	}
	
	//==========================================================================================
	//										PANEL FILTROS: 
	//==========================================================================================
	private JPanel getPanelFiltros() {
		if (panelFiltros == null) {
			panelFiltros = new JPanel();
			panelFiltros.setBackground(SystemColor.textInactiveText);
			panelFiltros.add(getLbFiltros());
			calendar = new JCalendar();
			calendar.setBounds(10, 224, 351, 264);
			calendar.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent e) {
					Date fecha = new Date(calendar.getYearChooser().getYear(),calendar.getMonthChooser().getMonth()+1,calendar.getDayChooser().getDay());
					if(fecha.month==9)
						fecha.month = 9;
					carreras = new ArrayList<Carrera>();
					for (Carrera c : gestorCarreras.carreras) {
						Date cf = c.getFechaFormateada();
						boolean temp = false;
						if(cf.compareTo(fecha) >= 0) {
							for(int i=0;!carreras.isEmpty() && i<carreras.size(); i++) {
								if(carreras.get(i).getFechaFormateada().compareTo(c.getFechaFormateada()) >= 0) {
									carreras.add(i,c);
									temp = true;
									break;
								}
									
							}
							if(!temp)carreras.add(carreras.size(),c);
							
						}
					}
					actualizarCarreras();
				}
			});
			panelFiltros.add(getLbNombreCarreraFiltro());
			panelFiltros.add(getLbTipoCarreraFiltro());
			panelFiltros.add(calendar);
			panelFiltros.add(getTxtNombreCarrera());
			panelFiltros.add(getChckbxUrbana());
			panelFiltros.add(getChcbxMontain());
		}
		return panelFiltros;
	}
	
	private JLabel getLbFiltros() {
		if (lbFiltros == null) {
			lbFiltros = new JLabel("FILTROS:");
			lbFiltros.setForeground(UIManager.getColor("Button.light"));
			lbFiltros.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 26));
			lbFiltros.setBounds(10, 11, 140, 41);
		}
		return lbFiltros;
	}
	
	private JLabel getLbNombreCarreraFiltro() {
		if (lbNombreCarreraFiltro == null) {
			lbNombreCarreraFiltro = new JLabel("Nombre Carrera:");
			lbNombreCarreraFiltro.setForeground(SystemColor.controlHighlight);
			lbNombreCarreraFiltro.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbNombreCarreraFiltro.setBounds(10, 57, 110, 19);
		}
		return lbNombreCarreraFiltro;
	}
	private JTextField getTxtNombreCarrera() {
		if (txtNombreCarrera == null) {
			txtNombreCarrera = new JTextField();
			txtmemoria = "";
			txtNombreCarrera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!txtmemoria.equals(txtNombreCarrera.getText())) {
						txtmemoria = txtNombreCarrera.getText();
						gestorCarreras = new GestorApp();
						carreras = gestorCarreras.carreras;
						ArrayList<Carrera> aeliminar = new ArrayList<Carrera>();
						for (Carrera carrera : carreras) {
							if(!carrera.getNombre().toLowerCase().contains(txtmemoria.toLowerCase()))aeliminar.add(carrera);
						}
						for (Carrera carrera : aeliminar) {
							carreras.remove(carrera);
						}
						actualizarCarreras();
					}
				}
			});
			txtNombreCarrera.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 11));
			txtNombreCarrera.setBounds(122, 51, 239, 20);
			txtNombreCarrera.setColumns(10);
		}
		return txtNombreCarrera;
	}
	private JLabel getLbTipoCarreraFiltro() {
		if (lbTipoCarreraFiltro == null) {
			lbTipoCarreraFiltro = new JLabel("Tipo Carrera:");
			lbTipoCarreraFiltro.setForeground(SystemColor.controlHighlight);
			lbTipoCarreraFiltro.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbTipoCarreraFiltro.setBounds(10, 88, 110, 19);
		}
		return lbTipoCarreraFiltro;
	}
	private JCheckBox getChckbxUrbana() {
		if (chckbxUrbana == null) {
			chckbxUrbana = new JCheckBox("Urbana");
			chckbxUrbana.setForeground(Color.DARK_GRAY);
			chckbxUrbana.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 12));
			chckbxUrbana.setBounds(10, 116, 153, 23);
			chckbxUrbana.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarCHC();		
				}
			});
		}
		return chckbxUrbana;
	}

	private JCheckBox getChcbxMontain() {
		if (chcbxMontain == null) {
			chcbxMontain = new JCheckBox("Monta\u00F1a");
			chcbxMontain.setForeground(Color.DARK_GRAY);
			chcbxMontain.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 12));
			chcbxMontain.setBounds(196, 115, 165, 24);
			chcbxMontain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarCHC();			
				}
			});
		}
		return chcbxMontain;
	}
	
	//==========================================================================================
	//										PANEL CARRERAS: 
	//==========================================================================================
	
	private JLayeredPane getPanelCarreras() {
		if (panelCarreras == null) {
			panelCarreras = new JLayeredPane();
			panelCarreras.setLayout(new CardLayout(0, 0));
			if(carreras.size() % 4 == 0)
				numPaneles =  carreras.size()/4;
			else
				numPaneles =  1+ carreras.size()/4;
			numCarreras = 0;
			for(int i=1; i<=numPaneles; i++){
				panelCarreras.add(getPlantillaPanel(null,i), ""+i);
				
			}
		}
		return panelCarreras;
	}
	private JPanel getPlantillaPanel(JPanel panel,int num) {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(new Color(211, 211, 211));
			GridBagLayout gbl_panelCarreras = new GridBagLayout();
			gbl_panelCarreras.columnWidths = new int[]{33, 55, 101, 112, 64, 48, 0, 0, 0};
			gbl_panelCarreras.rowHeights = new int[]{27, 0, 0, 0, 36, 0, 0, 0, 36, 0, 0, 0, 36, 0, 0, 0,36};
			gbl_panelCarreras.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panelCarreras.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panelCarreras);
			//GridBag
			int fila = 0;
			for(int i=0; i<4;i++) {
				fila++;
				GridBagConstraints gbc_lbCarrera = new GridBagConstraints();
				gbc_lbCarrera.anchor = GridBagConstraints.WEST;
				gbc_lbCarrera.insets = new Insets(0, 0, 5, 5);
				gbc_lbCarrera.gridwidth = 3;
				gbc_lbCarrera.gridx = 1;
				gbc_lbCarrera.gridy = fila;
				if(numCarreras < carreras.size())
					panel.add(getLbCarrera(null,carreras.get(numCarreras).getNombre()), gbc_lbCarrera);
				GridBagConstraints gbc_lblFecha = new GridBagConstraints();
				gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
				gbc_lblFecha.gridx = 4;
				gbc_lblFecha.gridy = fila;
				if(numCarreras < carreras.size())
					panel.add(getLblFecha(null,carreras.get(numCarreras).getFecha()), gbc_lblFecha);
				GridBagConstraints gbc_btInscribirme = new GridBagConstraints();
				gbc_btInscribirme.insets = new Insets(0, 0, 5, 5);
				gbc_btInscribirme.gridx = 6;
				gbc_btInscribirme.gridy = fila;
				if(numCarreras < carreras.size())
					panel.add(getBtInscribirme(null,carreras.get(numCarreras)), gbc_btInscribirme);
				GridBagConstraints gbc_lbTipoKm = new GridBagConstraints();
				gbc_lbTipoKm.anchor = GridBagConstraints.WEST;
				gbc_lbTipoKm.insets = new Insets(0, 0, 5, 5);
				gbc_lbTipoKm.gridwidth = 3;
				gbc_lbTipoKm.gridx = 2;
				gbc_lbTipoKm.gridy = ++fila;
				if(numCarreras < carreras.size())
					panel.add(getLbTipoKm(null,carreras.get(numCarreras).getTipo(),
							carreras.get(numCarreras).getDistancia(),carreras.get(numCarreras).getLugar()), gbc_lbTipoKm);
				GridBagConstraints gbc_lbPrecio = new GridBagConstraints();
				gbc_lbPrecio.insets = new Insets(0, 0, 5, 5);
				gbc_lbPrecio.gridx = 6;
				gbc_lbPrecio.gridy = fila;
				if(numCarreras < carreras.size())
					panel.add(getLbPrecio(null,carreras.get(numCarreras).getPrecio()), gbc_lbPrecio);
				GridBagConstraints gbc_lblFechaLimiteInscripcion = new GridBagConstraints();
				gbc_lblFechaLimiteInscripcion.anchor = GridBagConstraints.WEST;
				gbc_lblFechaLimiteInscripcion.gridwidth = 2;
				gbc_lblFechaLimiteInscripcion.insets = new Insets(0, 0, 5, 5);
				gbc_lblFechaLimiteInscripcion.gridx = 3;
				gbc_lblFechaLimiteInscripcion.gridy = ++fila;
				if(numCarreras < carreras.size())
					panel.add(getLblFechaLimiteInscripcion(null,carreras.get(numCarreras).getFechaInscripcionActual()), 
							gbc_lblFechaLimiteInscripcion);
				fila++;
				numCarreras++;
			}
			
			GridBagConstraints gbc_panelSiguiente = new GridBagConstraints();
			gbc_panelSiguiente.fill = GridBagConstraints.BOTH;
			gbc_panelSiguiente.gridwidth = 7;
			gbc_panelSiguiente.gridx = 1;
			gbc_panelSiguiente.gridy = 17;
			
			if(num == 1 && num==numPaneles)
				panel.add(getPanelSiguiente(null,false,false,num), gbc_panelSiguiente);  //Los booleanos son anterior y siguiente, en dicho orden, si esta a true
			else if(num==numPaneles)													//tiene anterior o siguiente
				panel.add(getPanelSiguiente(null,true,false,num), gbc_panelSiguiente);
			else if(num == 1)
				panel.add(getPanelSiguiente(null,false,true,num), gbc_panelSiguiente);
			else
				panel.add(getPanelSiguiente(null,true,true,num), gbc_panelSiguiente);
		}
		return panel;
	}
	
	private JPanel getPanelSiguiente(JPanel panelSiguiente,boolean ant,boolean sig,int index) {
		if (panelSiguiente == null) {
			panelSiguiente = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelSiguiente.getLayout();
			panelSiguiente.setBackground(new Color(211, 211, 211));
			flowLayout.setVgap(20);
			flowLayout.setHgap(10);
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelSiguiente.add(getBtnPginaAnterior(null,ant,index));
			panelSiguiente.add(getBtnSiguientePgina(null,sig,index));
		}
		return panelSiguiente;
	}
	
	private JButton getBtnSiguientePgina(JButton btnSiguientePgina,boolean sig,int index) {
		if (btnSiguientePgina == null) {
			btnSiguientePgina = new JButton("Pagina siguiente");
			btnSiguientePgina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					 CardLayout cl = (CardLayout)(panelCarreras.getLayout());
				     cl.show(panelCarreras, ""+(index+1));
				}
			});
			btnSiguientePgina.setMnemonic('s');
			btnSiguientePgina.setForeground(new Color(255, 255, 255));
			btnSiguientePgina.setFont(new Font("Century Gothic", Font.BOLD, 11));
			btnSiguientePgina.setBackground(new Color(60, 179, 113));
			if(!sig) btnSiguientePgina.setEnabled(false);
			else  btnSiguientePgina.setEnabled(true);
		}
		return btnSiguientePgina;
	}
	
	private JButton getBtnPginaAnterior(JButton btnPginaAnterior,boolean ant,int index) {
		if (btnPginaAnterior == null) {
			btnPginaAnterior = new JButton("Pagina anterior");
			btnPginaAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					 CardLayout cl = (CardLayout)(panelCarreras.getLayout());
				     cl.show(panelCarreras, ""+(index-1));
				}
			});
			btnPginaAnterior.setMnemonic('a');
			btnPginaAnterior.setForeground(new Color(255, 255, 255));
			btnPginaAnterior.setFont(new Font("Century Gothic", Font.BOLD, 11));
			btnPginaAnterior.setBackground(new Color(60, 179, 113));
			if(!ant) btnPginaAnterior.setEnabled(false);
			else btnPginaAnterior.setEnabled(true);
		}
		return btnPginaAnterior;
	}
	
	private JLabel getLbCarrera(JLabel lbCarrera,String name) {
		if (lbCarrera == null) {
			lbCarrera = new JLabel(name);
			lbCarrera.setForeground(Color.DARK_GRAY);
			lbCarrera.setFont(new Font("Segoe WP Black", Font.BOLD | Font.ITALIC, 18));
		}
		return lbCarrera;
	}
	
	private JLabel getLbTipoKm(JLabel lbtipo,String tipo,int km,String localidad) {
		if (lbtipo == null) {
			lbtipo = new JLabel("Tipo: "+tipo+" - "+km +"km"+" - Lugar: "+localidad);
			lbtipo.setForeground(new Color(47, 79, 79));
			lbtipo.setFont(new Font("Segoe WP Semibold", Font.PLAIN, 12));
		}
		return lbtipo;
	}
	private JLabel getLblFecha(JLabel lblFecha,String fecha) {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha: "+fecha);
			lblFecha.setForeground(Color.DARK_GRAY);
			lblFecha.setFont(new Font("Segoe WP Semibold", Font.PLAIN, 15));
		}
		return lblFecha;
	}
	private JButton getBtInscribirme(JButton btInscribirme,Carrera c) {
		if (btInscribirme == null) {
			btInscribirme = new JButton("Inscribirme");
			btInscribirme.setForeground(new Color(135, 206, 250));
			btInscribirme.setBackground(Color.DARK_GRAY);
			btInscribirme.setFont(new Font("Segoe WP Semibold", Font.BOLD, 15));
			Calendar fecha = new GregorianCalendar();
			String fecha_actual = fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
			if(new Date(c.getFechaInscripcionActual().getFechaFin()).compareTo(new Date(fecha_actual)) < 0)
					btInscribirme.setEnabled(false);
			else {
				btInscribirme.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						CarreraDescripcion dialog = new CarreraDescripcion(c,gestorCarreras);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						dialog.setLocationRelativeTo(null);
						dialog.setResizable(false);
							
					}
				});
			}	
		}
		return btInscribirme;
	}
	
	private JLabel getLblFechaLimiteInscripcion(JLabel lblFechaLimiteInscripcion,FechaInscripcion fecha) {
		if (lblFechaLimiteInscripcion == null) {
			lblFechaLimiteInscripcion = new JLabel("Inscripción-> De: "+fecha.getFecha()+" a "+fecha.getFechaFin());
			lblFechaLimiteInscripcion.setForeground(new Color(47, 79, 79));
			lblFechaLimiteInscripcion.setFont(new Font("Segoe WP Semibold", Font.PLAIN, 11));
		}
		return lblFechaLimiteInscripcion;
	}
	private JLabel getLbPrecio(JLabel lbPrecio,double precio) {
		if (lbPrecio == null) {
			lbPrecio = new JLabel(precio+ "€");
			lbPrecio.setForeground(Color.DARK_GRAY);
			lbPrecio.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 16));
		}
		return lbPrecio;
	}
	
	//==========================================================================================
	//										LOGICA: 
	//==========================================================================================
	
	
	/**
	 * Metodo que actualiza la ventana de carreras
	 */
	private void actualizarCarreras(){
		panelCentro.remove(panelCarreras);
		panelCarreras = null;
		GridBagConstraints gbc_panelCarreras = new GridBagConstraints();
		gbc_panelCarreras.fill = GridBagConstraints.BOTH;
		gbc_panelCarreras.gridx = 1;
		gbc_panelCarreras.gridy = 0;
		panelCentro.add(getPanelCarreras(), gbc_panelCarreras);
		panelCentro.updateUI();
		panelCarreras.updateUI();
	}
	
	protected void comprobarCHC() {
		gestorCarreras = new GestorApp();
		carreras = new ArrayList<Carrera>();
		if(chcbxMontain.isSelected() && chckbxUrbana.isSelected()) {
			carreras = gestorCarreras.carreras;
		}else if(chcbxMontain.isSelected() && !chckbxUrbana.isSelected()) {
			for (Carrera carrera : gestorCarreras.carreras) {
				if(carrera.getTipo().equals("Montaña"))carreras.add(carrera);
			}
		}else if(!chcbxMontain.isSelected() && chckbxUrbana.isSelected()) {
			for (Carrera carrera : gestorCarreras.carreras) {
				if(carrera.getTipo().equals("Urbana"))carreras.add(carrera);
			}
		}
		else {
			carreras = gestorCarreras.carreras;
		}
		actualizarCarreras();		
	}
	
	/**
	 * Comprueba si la fecha de inscripción de las carreras ya ha cerrrado hace 2 días(por si queda alguno preinscrito),
	 * si es así asigna dorsales a los corredores
	 * @throws SQLException 
	 */
	private void actualizaDorsales() throws SQLException {
		Calendar fecha = new GregorianCalendar();
		String ffaa = fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
		Date fActual = new Date(ffaa);
		for(Carrera c:carreras) {
			ArrayList<FechaInscripcion> fechas = c.getFechas_inscripcion();
			Date d = new Date(fechas.get(fechas.size()-1).getFechaFin());//Coge la fecha fin de laúltima fecha de inscripción posible
			d.setDay(d.getDay()+2);;//Le suma dos días por posibles preinscritos
			if(fActual.compareTo(d)>0) {
				c.actualizaAtletas();
				c.asignaDorsales();
				
			}
		}
	}
	
	
}
