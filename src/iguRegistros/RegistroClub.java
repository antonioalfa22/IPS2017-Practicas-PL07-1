package iguRegistros;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import entities.Carrera;
import entities.Club;
import entities.Usuario;
import gestorBBDD.LectorCSV;
import igu.VentanaPrincipal;
import logic.GestorApp;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class RegistroClub extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTitulo;
	private JLabel lbTitulo;
	private JButton btRegistrar;
	private JButton btCancelar;
	private JLabel lbNombre;
	private JTextField txtNombre;
	private JLabel lbIntegrantes;
	private JButton btnCargarCsvCon;
	private JDialog ventana;
	private JLabel lbDireccion;
	private JTextField txtDireccion;
	private Carrera carrera;
	private GestorApp gestor;
	private List<Usuario> users;


	/**
	 * Create the frame.
	 */
	public RegistroClub(Carrera c,GestorApp g) {
		this.carrera = c; this.gestor=g;
		users = null;
		ventana = this;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 521, 395);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelTitulo());
		contentPane.add(getBtRegistrar());
		contentPane.add(getBtCancelar());
		contentPane.add(getLbNombre());
		contentPane.add(getTxtNombre());
		contentPane.add(getLbIntegrantes());
		contentPane.add(getBtnCargarCsvCon());
		contentPane.add(getLbDireccion());
		contentPane.add(getTxtDireccion());
	}
	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
			panelTitulo.setBackground(Color.DARK_GRAY);
			panelTitulo.setBounds(0, 0, 509, 68);
			panelTitulo.setLayout(null);
			panelTitulo.add(getLbTitulo());
		}
		return panelTitulo;
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("REGISTRATE COMO CLUB");
			lbTitulo.setBounds(86, 11, 349, 46);
			lbTitulo.setForeground(Color.LIGHT_GRAY);
			lbTitulo.setFont(new Font("Source Sans Pro Semibold", Font.BOLD | Font.ITALIC, 30));
		}
		return lbTitulo;
	}
	private JButton getBtRegistrar() {
		if (btRegistrar == null) {
			btRegistrar = new JButton("Registrar");
			btRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					registrar();
				}
			});
			btRegistrar.setForeground(new Color(72, 209, 204));
			btRegistrar.setFont(new Font("Source Sans Pro Semibold", Font.BOLD | Font.ITALIC, 12));
			btRegistrar.setBackground(Color.DARK_GRAY);
			btRegistrar.setBounds(311, 320, 93, 31);
		}
		return btRegistrar;
	}
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setForeground(new Color(72, 209, 204));
			btCancelar.setFont(new Font("Source Sans Pro Semibold", Font.BOLD | Font.ITALIC, 12));
			btCancelar.setBackground(Color.DARK_GRAY);
			btCancelar.setBounds(405, 320, 93, 31);
		}
		return btCancelar;
	}
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre del club(*)");
			lbNombre.setForeground(Color.BLACK);
			lbNombre.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbNombre.setBounds(48, 94, 125, 24);
		}
		return lbNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			txtNombre.setColumns(10);
			txtNombre.setBackground(SystemColor.controlHighlight);
			txtNombre.setBounds(192, 95, 288, 23);
		}
		return txtNombre;
	}
	private JLabel getLbIntegrantes() {
		if (lbIntegrantes == null) {
			lbIntegrantes = new JLabel("Integrantes del club(*)");
			lbIntegrantes.setToolTipText("El formato del archivo csv debe ser de un Integrante por linea con estilo: DNI;Nombre;Fecha Nacimiento,Telefono,Correo y G\u00E9nero");
			lbIntegrantes.setForeground(Color.BLACK);
			lbIntegrantes.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbIntegrantes.setBounds(48, 142, 125, 24);
		}
		return lbIntegrantes;
	}
	private JButton getBtnCargarCsvCon() {
		if (btnCargarCsvCon == null) {
			btnCargarCsvCon = new JButton("Cargar csv con los integrantes...");
			btnCargarCsvCon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				   JFileChooser file=new JFileChooser();
				   FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
				   file.setFileFilter(filter);
				   file.showOpenDialog(ventana);
				   try{String abre=file.getSelectedFile().getPath(); users = LectorCSV.leerUsuarios(abre);
				   btnCargarCsvCon.setText(abre);}
				   catch(Exception ex) {};
				  
				}
			});
			btnCargarCsvCon.setToolTipText("");
			btnCargarCsvCon.setBackground(SystemColor.controlHighlight);
			btnCargarCsvCon.setBounds(192, 142, 288, 23);
		}
		return btnCargarCsvCon;
	}
	private JLabel getLbDireccion() {
		if (lbDireccion == null) {
			lbDireccion = new JLabel("Direccion");
			lbDireccion.setToolTipText("El formato del archivo csv debe ser de un Integrante por linea con estilo: DNI;Nombre;Fecha Nacimiento,Telefono,Correo y G\u00E9nero");
			lbDireccion.setForeground(Color.BLACK);
			lbDireccion.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbDireccion.setBounds(48, 190, 125, 24);
		}
		return lbDireccion;
	}
	private JTextField getTxtDireccion() {
		if (txtDireccion == null) {
			txtDireccion = new JTextField();
			txtDireccion.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			txtDireccion.setColumns(10);
			txtDireccion.setBackground(SystemColor.controlHighlight);
			txtDireccion.setBounds(192, 191, 288, 23);
		}
		return txtDireccion;
	}
	
	private void registrar() {
		boolean ya_registrado = gestor.clubs.stream().map(x -> x.getNombre()).collect(Collectors.toList()).contains(txtNombre.getText());
		if(!(users == null) && !txtNombre.getText().equals("") && !ya_registrado) {
			ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
			for(Usuario u:users) {
				if(gestor.getUsuarios().stream().map(x -> x.getDni()).collect(Collectors.toList()).contains(u.getDni())) {
					int seleccion = JOptionPane.showOptionDialog(null, "El DNI: "+u.getDni()+" ya está registrado."
							+ "¿Deseas usar sus datos?","DNI Ya registrado",
							   JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,
							   new Object[] { "Usar los datos de ese DNI", "Cancelar"},
							   "opcion 1");

					if (seleccion == 0) {
						listaUsuarios.add(gestor.getUsuarios().stream().filter(x -> x.getDni().equals(u.getDni())).findFirst().get());
					}			
				}else {
					listaUsuarios.add(u);
					VentanaPrincipal.gestorCarreras.addUsuario(u);
				}
			}
			int id = gestor.getClubs().size()+1;
			Club club = new Club(id,txtNombre.getText(),txtDireccion.getText()!=null?txtDireccion.getText():"");
			VentanaPrincipal.gestorCarreras.addClub(club);
			listaUsuarios.forEach(x -> VentanaPrincipal.gestorCarreras.addPertenece_a_Club(x,club));
			Calendar f = new GregorianCalendar();
			String fecha_insc = f.get(Calendar.DAY_OF_MONTH)+"/"+(f.get(Calendar.MONTH)+1)+"/"+f.get(Calendar.YEAR);
			
			try {
				//Guardar en BBDD los usuarios y preinscritos
				gestor.getTodosLosPreinscritos(carrera).stream().forEach(x -> {
					if(listaUsuarios.stream().map(y -> y.getDni()).collect(Collectors.toList()).contains(x.getDni()))
						listaUsuarios.removeIf(z -> z.getDni().equals(x.getDni()));
				});
				GestorApp.getTodosLosCorredores(carrera).stream().forEach(x -> {
					if(listaUsuarios.stream().map(y -> y.getDni()).collect(Collectors.toList()).contains(x.getDni()))
						listaUsuarios.removeIf(z -> z.getDni().equals(x.getDni()));
				});
				listaUsuarios.forEach(x -> 
				{
					if(x.getEdad() >= carrera.getEdad_minima())
						VentanaPrincipal.gestorCarreras.addPreeinscrito(x, carrera, fecha_insc);
					else
						JOptionPane.showMessageDialog(null, x.getNombre()+" no tiene la edad suficiente para participar.\n"
								+ "No se le va a inscribir en la carrera",
								"No tiene edad suficiente", JOptionPane.PLAIN_MESSAGE);
				});
				
				//Mensaje registro completado
				JOptionPane.showMessageDialog(null, "Club registrado con exito",
						"Registro completado", JOptionPane.PLAIN_MESSAGE);
				dispose();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if(ya_registrado)
			JOptionPane.showMessageDialog(null, "Ya existe un club con ese nombre",
					"Error al registrar Club", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "El nombre del club está vacío o no hay integrarntes en él.",
					"Error al registrar Club", JOptionPane.ERROR_MESSAGE);
	}
}
