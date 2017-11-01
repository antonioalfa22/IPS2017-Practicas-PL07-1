package igu;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import entities.Carrera;
import entities.Corredor;
import entities.Preinscrito;
import entities.Usuario;
import logic.GestorApp;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Ventana que realiza el registro de un usuario en una carrera
 * @author Antonio Payá
 *
 */
public class Register extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel panelTitulo;
	private JLabel lblRegistrate;
	private JLabel lblNombreYApellidos;
	private JTextField txtNombre;
	private JLabel lblFechaNacimiento;
	private JComboBox<Integer> cbDia;
	private JComboBox<String> cbMes;
	private JComboBox<Integer> cbYear;
	private JLabel lbDia;
	private JLabel lbMes;
	private JLabel lbAno;
	private JLabel lbDir;
	private JTextField txtDir;
	private JLabel lbTelefono;
	private JTextField txtTelefono;
	private JLabel lbLocalidad;
	private JTextField txtLocalidad;
	private JLabel lbCp;
	private JTextField txtCP;
	private JLabel lbCorreo;
	private JTextField txtCorreo;
	private JLabel lbContra;
	private JPasswordField txtContra;
	private JButton btRegistrar;
	private JButton btCancelar;
	private int dia,mes,year;
	private JLabel lbGenero;
	private JComboBox<String> cbGenero;
	private JLabel lbDni;
	private JTextField txtDni;
	private Carrera c;
	private String dni;
	private String nombre;
	private String fecha;
	private String dir;
	private int tel;
	private String loc;
	private String cp;
	private String correo;
	private String contra;
	private int genero;
	private GestorApp gestor;

	/**
	 * Create the dialog.
	 */
	public Register(Carrera c,GestorApp g) {
		this.c = c;
		this.gestor = g;
		dia = 1;mes = 1; year = 1999;
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 509, 511);
		getContentPane().setLayout(null);
		getContentPane().add(getPanelTitulo());
		getContentPane().add(getLblNombreYApellidos());
		getContentPane().add(getTxtNombre());
		getContentPane().add(getLblFechaNacimiento());
		getContentPane().add(getCbDia());
		getContentPane().add(getCbMes());
		getContentPane().add(getCbYear());
		getContentPane().add(getLbDia());
		getContentPane().add(getLbMes());
		getContentPane().add(getLbAno());
		getContentPane().add(getLbDir());
		getContentPane().add(getTxtDir());
		getContentPane().add(getLbTelefono());
		getContentPane().add(getTxtTelefono());
		getContentPane().add(getLbLocalidad());
		getContentPane().add(getTxtLocalidad());
		getContentPane().add(getLbCp());
		getContentPane().add(getTxtCP());
		getContentPane().add(getLbCorreo());
		getContentPane().add(getTxtCorreo());
		getContentPane().add(getLbContra());
		getContentPane().add(getTxtContra());
		getContentPane().add(getBtRegistrar());
		getContentPane().add(getBtCancelar());
		getContentPane().add(getLbGenero());
		getContentPane().add(getCbGenero());
		getContentPane().add(getLbDni());
		getContentPane().add(getTxtDni());

	}
	
	//==========================================================================================
	//										CAMPOS: 
	//==========================================================================================
	
	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
			panelTitulo.setBackground(Color.DARK_GRAY);
			panelTitulo.setBounds(0, 0, 493, 62);
			panelTitulo.setLayout(null);
			panelTitulo.add(getLblRegistrate());
		}
		return panelTitulo;
	}
	private JLabel getLblRegistrate() {
		if (lblRegistrate == null) {
			lblRegistrate = new JLabel("REGISTRATE");
			lblRegistrate.setForeground(Color.LIGHT_GRAY);
			lblRegistrate.setFont(new Font("Source Sans Pro Semibold", Font.BOLD | Font.ITALIC, 30));
			lblRegistrate.setBounds(151, 11, 196, 46);
		}
		return lblRegistrate;
	}
	private JLabel getLblNombreYApellidos() {
		if (lblNombreYApellidos == null) {
			lblNombreYApellidos = new JLabel("Nombre y Apellidos(*)");
			lblNombreYApellidos.setForeground(Color.BLACK);
			lblNombreYApellidos.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lblNombreYApellidos.setBounds(30, 101, 125, 24);
		}
		return lblNombreYApellidos;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			txtNombre.setBackground(SystemColor.controlHighlight);
			txtNombre.setBounds(180, 102, 288, 23);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JLabel getLblFechaNacimiento() {
		if (lblFechaNacimiento == null) {
			lblFechaNacimiento = new JLabel("Fecha Nacimiento(*)");
			lblFechaNacimiento.setForeground(Color.BLACK);
			lblFechaNacimiento.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lblFechaNacimiento.setBounds(30, 147, 125, 24);
		}
		return lblFechaNacimiento;
	}
	private JComboBox<Integer> getCbDia() {
		if (cbDia == null) {
			cbDia = new JComboBox<Integer>();
			cbDia.setBounds(180, 146, 58, 24);
			cbDia.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					dia = cbDia.getSelectedIndex()+1;
				}
			});
			calcularDias();
		}
		return cbDia;
	}
	private void calcularDias() {
		cbDia.removeAll();
		if(mes == 1|| mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
			for(int i = 1;i <=31 ; i++) cbDia.addItem(i);
		else if(mes == 2)
			for(int i = 1;i <=28 ; i++) cbDia.addItem(i);
		else
			for(int i = 1;i <=30 ; i++) cbDia.addItem(i);
	}

	private JComboBox<String> getCbMes() {
		if (cbMes == null) {
			cbMes = new JComboBox<String>();
			cbMes.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					mes = cbMes.getSelectedIndex()+1;
					calcularDias();
					cbDia.updateUI();
				}
			});
			cbMes.setModel(new DefaultComboBoxModel<String>
				(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", 
					"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
			cbMes.setBounds(248, 146, 125, 24);
		}
		return cbMes;
	}
	private JComboBox<Integer> getCbYear() {
		if (cbYear == null) {
			cbYear = new JComboBox<Integer>();
			cbYear.setBounds(383, 146, 85, 24);
			int[] years = new int[150];
			for(int i=0; i<118 ;i++) { years[i] = 2017-i;cbYear.addItem(years[i]);}
			cbYear.setSelectedItem(1999);
			cbYear.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					year = (Integer)cbYear.getSelectedItem();
				}
			});
		}
		return cbYear;
	}
	private JLabel getLbDia() {
		if (lbDia == null) {
			lbDia = new JLabel("D\u00EDa");
			lbDia.setHorizontalAlignment(SwingConstants.CENTER);
			lbDia.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 11));
			lbDia.setBounds(180, 125, 58, 18);
		}
		return lbDia;
	}
	private JLabel getLbMes() {
		if (lbMes == null) {
			lbMes = new JLabel("Mes");
			lbMes.setHorizontalAlignment(SwingConstants.CENTER);
			lbMes.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 11));
			lbMes.setBounds(280, 125, 58, 18);
		}
		return lbMes;
	}
	private JLabel getLbAno() {
		if (lbAno == null) {
			lbAno = new JLabel("A\u00F1o");
			lbAno.setHorizontalAlignment(SwingConstants.CENTER);
			lbAno.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 11));
			lbAno.setBounds(394, 125, 58, 18);
		}
		return lbAno;
	}
	private JLabel getLbDir() {
		if (lbDir == null) {
			lbDir = new JLabel("Direccion");
			lbDir.setForeground(Color.BLACK);
			lbDir.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbDir.setBounds(30, 182, 125, 24);
		}
		return lbDir;
	}
	private JTextField getTxtDir() {
		if (txtDir == null) {
			txtDir = new JTextField();
			txtDir.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			txtDir.setColumns(10);
			txtDir.setBackground(SystemColor.controlHighlight);
			txtDir.setBounds(180, 181, 288, 23);
		}
		return txtDir;
	}
	private JLabel getLbTelefono() {
		if (lbTelefono == null) {
			lbTelefono = new JLabel("Telefono(*)");
			lbTelefono.setForeground(Color.BLACK);
			lbTelefono.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbTelefono.setBounds(30, 217, 125, 24);
		}
		return lbTelefono;
	}
	private JTextField getTxtTelefono() {
		if (txtTelefono == null) {
			txtTelefono = new JTextField();
			txtTelefono.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			txtTelefono.setColumns(10);
			txtTelefono.setBackground(SystemColor.controlHighlight);
			txtTelefono.setBounds(180, 215, 288, 23);
		}
		return txtTelefono;
	}
	private JLabel getLbLocalidad() {
		if (lbLocalidad == null) {
			lbLocalidad = new JLabel("Localidad");
			lbLocalidad.setForeground(Color.BLACK);
			lbLocalidad.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbLocalidad.setBounds(30, 252, 125, 24);
		}
		return lbLocalidad;
	}
	private JTextField getTxtLocalidad() {
		if (txtLocalidad == null) {
			txtLocalidad = new JTextField();
			txtLocalidad.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			txtLocalidad.setColumns(10);
			txtLocalidad.setBackground(SystemColor.controlHighlight);
			txtLocalidad.setBounds(180, 249, 288, 23);
		}
		return txtLocalidad;
	}
	private JLabel getLbCp() {
		if (lbCp == null) {
			lbCp = new JLabel("Codigo Postal");
			lbCp.setForeground(Color.BLACK);
			lbCp.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbCp.setBounds(30, 287, 125, 24);
		}
		return lbCp;
	}
	private JTextField getTxtCP() {
		if (txtCP == null) {
			txtCP = new JTextField();
			txtCP.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			txtCP.setColumns(10);
			txtCP.setBackground(SystemColor.controlHighlight);
			txtCP.setBounds(180, 283, 288, 23);
		}
		return txtCP;
	}
	private JLabel getLbCorreo() {
		if (lbCorreo == null) {
			lbCorreo = new JLabel("Correo(*)");
			lbCorreo.setForeground(Color.BLACK);
			lbCorreo.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbCorreo.setBounds(30, 322, 125, 24);
		}
		return lbCorreo;
	}
	private JTextField getTxtCorreo() {
		if (txtCorreo == null) {
			txtCorreo = new JTextField();
			txtCorreo.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			txtCorreo.setColumns(10);
			txtCorreo.setBackground(SystemColor.controlHighlight);
			txtCorreo.setBounds(180, 317, 288, 23);
		}
		return txtCorreo;
	}
	private JLabel getLbContra() {
		if (lbContra == null) {
			lbContra = new JLabel("Contrase\u00F1a");
			lbContra.setForeground(Color.BLACK);
			lbContra.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbContra.setBounds(30, 352, 125, 24);
		}
		return lbContra;
	}
	private JPasswordField getTxtContra() {
		if (txtContra == null) {
			txtContra = new JPasswordField();
			txtContra.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			txtContra.setBackground(SystemColor.controlHighlight);
			txtContra.setBounds(180, 351, 288, 24);
		}
		return txtContra;
	}
	private JButton getBtRegistrar() {
		if (btRegistrar == null) {
			btRegistrar = new JButton("Registrar");
			btRegistrar.setBackground(Color.DARK_GRAY);
			btRegistrar.setForeground(new Color(72, 209, 204));
			btRegistrar.setFont(new Font("Source Sans Pro Semibold", Font.BOLD | Font.ITALIC, 12));
			btRegistrar.setBounds(280, 430, 93, 31);
			btRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					registrar();
				}
			});
		}
		return btRegistrar;
	}
	
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btCancelar.setForeground(new Color(72, 209, 204));
			btCancelar.setFont(new Font("Source Sans Pro Semibold", Font.BOLD | Font.ITALIC, 12));
			btCancelar.setBackground(Color.DARK_GRAY);
			btCancelar.setBounds(375, 430, 93, 31);
		}
		return btCancelar;
	}
	private JLabel getLbGenero() {
		if (lbGenero == null) {
			lbGenero = new JLabel("Genero(*)");
			lbGenero.setForeground(Color.BLACK);
			lbGenero.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbGenero.setBounds(30, 387, 125, 24);
		}
		return lbGenero;
	}
	private JComboBox<String> getCbGenero() {
		if (cbGenero == null) {
			cbGenero = new JComboBox<String>();
			cbGenero.setModel(new DefaultComboBoxModel<String>(new String[] {"Masculino", "Femenino"}));
			cbGenero.setBounds(180, 388, 288, 24);
		}
		return cbGenero;
	}
	private JLabel getLbDni() {
		if (lbDni == null) {
			lbDni = new JLabel("DNI(*)");
			lbDni.setForeground(Color.BLACK);
			lbDni.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lbDni.setBounds(30, 73, 125, 24);
		}
		return lbDni;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					dni = txtDni.getText();
					comprobarUsuarioRegistrado();
				}
			});
			txtDni.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			txtDni.setColumns(10);
			txtDni.setBackground(SystemColor.controlHighlight);
			txtDni.setBounds(180, 73, 288, 23);
		}
		return txtDni;
	}
	
	//==========================================================================================
	//										LOGICA: 
	//==========================================================================================
	

	/**
	 * Mete los datos en la base de datos
	 * @return true si los mete correctamente, false en caso contrario
	 */
	private boolean meterDatos() {
		Usuario u = new Usuario(dni,nombre,fecha,dir,tel,loc,cp,correo,contra,genero);
		Calendar f = new GregorianCalendar();
		String fecha_insc = f.get(Calendar.DAY_OF_MONTH)+"/"+(f.get(Calendar.MONTH)+1)+"/"+f.get(Calendar.YEAR);
		try {
			VentanaPrincipal.gestorCarreras.addUsuario(u);
			VentanaPrincipal.gestorCarreras.setUsuarioActivo(u);
			VentanaPrincipal.gestorCarreras.addPreeinscrito(u, c, fecha_insc);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Metodo que comprueba cada uno de los campos
	 * @return true si esta todo correcto
	 */
	private boolean comprobarDatos() {
		addCamposToVariables();
		
		//COMPRUEBA QUE TENGA MAS EDAD QUE LA MINIMA
		if(2017-year < c.getEdad_minima()) {
			JOptionPane.showMessageDialog(null, "No tienes la edad suficiente para participar en la carrera",
					"Edad menor que la mínima especificada", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//COMPRUEBA QUE EL TELEFONO SEAN NUMEROS
		try {
			tel = Integer.parseInt(txtTelefono.getText());
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El telefono debe ser un numero",
					"Telefono incorrecto", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//SI HAY ALGUN CAMPO NO OBLIGATORIO VACIO, LO INICIALIZA
		if(dir.length()<=1) dir = "";
		if(loc.length()<=1) loc = "";
		if(cp.length()<=1) cp = "";
		if(contra == null) contra ="";
		
		//COMPRUEBA QUE LOS CAMPOS OBLIGATORIOS ESTAN RELLENADOS
		if(dni.length()>=1 && nombre.length()>=1 && fecha.length()>=1  && tel > 0 && (genero==1 || genero==0)
				&& correo != null ) {
			//COMPRUEBA QUE EL USUARIO CON ESE DNI NO ESTE INSCRITO EN ESA CARRERA YA(PREINSCRITO O INSCRITO)
			List<Preinscrito> preinscritos = VentanaPrincipal.gestorCarreras.getTodosLosPreinscritos(c);
			List<Corredor> corredores = VentanaPrincipal.gestorCarreras.getTodosLosCorredores(c);
			for (Preinscrito preinscrito : preinscritos) {
				if(preinscrito.getDni().equals(dni)) {
					JOptionPane.showMessageDialog(null, "Ya hay un participante con ese DNI",
							"Atleta ya registrado", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			for (Corredor corredor : corredores) {
				if(corredor.getDni().equals(dni)) {
					JOptionPane.showMessageDialog(null, "Ya hay un participante con ese DNI",
							"Atleta ya registrado", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			return true;
		}
		
		JOptionPane.showMessageDialog(null, "Tienes algun campo mal rellenado",
				  "Campos vacíos o erroneos", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	/**
	 * Comprueba si ese DNI ya está en uso y en caso afirmativo da la opción al
	 * usuario de registrarse con los datos de dicho DNI
	 */
	private void comprobarUsuarioRegistrado() {
		for (Usuario p : gestor.getUsuarios()) {
			if (p.getDni().equals(dni)) {
				int seleccion = JOptionPane.showOptionDialog(null,
						"Ese DNI ya está registrado en la aplicación,"
								+ "¿Deseas usar sus datos?",
						"DNI Ya registrado", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, new Object[] {
								"Usar los datos de ese DNI", "Cancelar" },
						"opcion 1");

				if (seleccion == 0) {
					ArrayList<Usuario> users = gestor.getUsuarios();
					Usuario u = users.stream()
							.filter(x -> x.getDni().equals(p.getDni()))
							.findFirst().get();
					txtDni.setText(u.getDni());
					txtNombre.setText(u.getNombre());
					cbDia.setSelectedItem(31 - Integer.parseInt(u
							.getFecha_nacimiento().split("/")[0]));
					cbMes.setSelectedIndex(12 - Integer.parseInt(u
							.getFecha_nacimiento().split("/")[1]));
					cbYear.setSelectedItem(2017 - Integer.parseInt(u
							.getFecha_nacimiento().split("/")[2]));
					txtDir.setText(u.getDireccion());
					txtTelefono.setText(String.valueOf(u.getTelefono()));
					txtLocalidad.setText(u.getLocalidad());
					txtCP.setText(u.getCodigo_postal());
					txtCorreo.setText(u.getCorreo());
					txtContra.setText(u.getContra());
					int gen = u.getGenero();
					if (gen == 0) {
						cbGenero.setSelectedItem("Masculino");
					} else {
						cbGenero.setSelectedItem("Femenino");
					}

				}
			}
		}
	}
	
	/**
	 * Metodo que añade a las variables cada uno de los campos
	 */
	private void addCamposToVariables() {
		dni = txtDni.getText();
		nombre = txtNombre.getText();
		fecha = dia+"/"+mes+"/"+year;
		dir = txtDir.getText();
		loc = txtLocalidad.getText();
		cp = txtCP.getText();
		correo = txtCorreo.getText();
		contra = txtContra.getPassword().toString();
		genero = ((String) cbGenero.getSelectedItem()).equals("Masculino") ? 1 : 0;
	}
	
	/**
	 * Metodo que registra el usuario en la base de datos
	 */
	private void registrar() {
		if(comprobarDatos())
			if(meterDatos()) {
				int seleccion = JOptionPane.showOptionDialog(null,"Registrado con Exito", "Registrado con Exito",
						   JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,
						   new Object[] { "Obtener justificante", "Cerrar"},
						   "opcion 1");

				if (seleccion == 0) {
					try {
						generarJustificante();
					} catch (IOException e) {
						System.out.println("Error al generar el justificante");
						e.printStackTrace();
					}
				}
				dispose();
			}
	}
	
	/**
	 * Metodo que genera un .txt con el justificante
	 * @throws IOException
	 */
	private void generarJustificante() throws IOException {
		String ruta = "justificantes/"+c.getNombre()+"_"+dni+".txt";
		File archivo = new File(ruta);
		BufferedWriter bw;
		bw = new BufferedWriter(new FileWriter(archivo));
		bw.write("============================================\n");
		bw.write("TIKET RUN\n");
		bw.write("============================================\n\n");
		bw.write("Nombre Carrera: "+c.getNombre()+"\n");
		bw.write("Fecha Carrera: "+c.getFecha()+"\n");
		bw.write("Lugar Carrera: "+c.getLugar()+"\n");
		bw.write("Tipo Carrera: "+c.getTipo()+"\n");
		bw.write("Número Kilometros: "+c.getDistancia()+"\n");
		bw.write("Precio: "+c.getPrecio()+"\n");
		bw.write("--------------->\n");
		bw.write("DNI: "+dni+"\n");
		bw.write("Correo: "+correo+"\n");
		bw.write("Nombre: "+nombre+"\n");
		bw.write("----------Datos para la trasferencia bancaria----------\n");
		bw.write("Nº Cuenta: "+c.getNum_cuenta()+"\n");
		bw.close();
	}
}
