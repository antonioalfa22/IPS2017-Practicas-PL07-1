package iguRegistros;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Carrera;
import gestorBBDD.GestorDB;
import logic.Categoria;
import logic.Date;
import logic.FechaInscripcion;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * 
 * @author Antonio Paya Gonzalez
 *
 */
public class RegistroCarrera extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTitulo;
	private JLabel lblCrearCarrera;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lbLugar;
	private JTextField txtLugar;
	private JLabel lbDNI;
	private JTextField txtDNI;
	private JLabel lbParticipantes;
	private JSpinner spParticipantes;
	private JLabel lbDistancia;
	private JSpinner spDistancia;
	private JLabel lbDureza;
	private JComboBox<String> cbDureza;
	private JLabel lbEdadMinima;
	private JSpinner spEdadMinima;
	private JLabel lbTipo;
	private JComboBox<String> cbTipo;
	private JScrollPane scrollCategorias;
	private JList<Categoria> listaCategorias;
	private JButton btAddCategoria;
	private JButton btnEliminarCategoria;
	private JSpinner spCatMin;
	private JSpinner spCatMax;
	private JLabel lbEdadMinimaCategoria;
	private JLabel lblLmiteSuperior;
	private JTextField txtNombreCategoria;
	private JScrollPane scrollFechas;
	private JList<FechaInscripcion> listaFechasInscripcion;
	private JComboBox<Integer> cbDia;
	private JComboBox<String> cbMes;
	private JComboBox<Integer> cbYear;
	private int dia,mes,year;
	private JButton btEliminarFecha;
	private JButton btAddFecha;
	private DefaultListModel<Categoria> modeloCategoria;
	private DefaultListModel<FechaInscripcion> modeloFechas;
	private JSpinner spPrecio;
	private JComboBox<Integer> cbDiaFin;
	private JComboBox<String> cbMesFin;
	private JComboBox<Integer> cbYearFin;
	private int mesFin,diaFin,yearFin;
	private int mesFecha,diaFecha,yearFecha;
	private JLabel lblFechaInicioInscripcin;
	private JLabel lblFechaFinInscripcion;
	private JLabel lblPrecioeuros;
	private JLabel lblCategorias;
	private JButton btAddCarrera;
	private JLabel lbFecha;
	private JComboBox<Integer> cbDiaFecha;
	private JComboBox<String> cbMesFecha;
	private JComboBox<Integer> cbYearFecha;
	private JLabel lbCuenta;
	private JTextField txtCuenta;

	/**
	 * Create the frame.
	 */
	public RegistroCarrera() {
		Calendar fecha = new GregorianCalendar();
		dia = fecha.get(Calendar.DAY_OF_MONTH);mes = fecha.get(Calendar.MONTH); year = fecha.get(Calendar.YEAR);
		diaFin = fecha.get(Calendar.DAY_OF_MONTH);mesFin = fecha.get(Calendar.MONTH); yearFin = fecha.get(Calendar.YEAR);
		diaFecha = fecha.get(Calendar.DAY_OF_MONTH);mesFecha = fecha.get(Calendar.MONTH); yearFecha = fecha.get(Calendar.YEAR);
		setTitle("Crear una Carrera");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelTitulo());
		contentPane.add(getLblNombre());
		contentPane.add(getTxtNombre());
		contentPane.add(getLbLugar());
		contentPane.add(getTxtLugar());
		contentPane.add(getLbDNI());
		contentPane.add(getTxtDNI());
		contentPane.add(getLbParticipantes());
		contentPane.add(getSpParticipantes());
		contentPane.add(getLbDistancia());
		contentPane.add(getSpDistancia());
		contentPane.add(getLbDureza());
		contentPane.add(getCbDureza());
		contentPane.add(getLbEdadMinima());
		contentPane.add(getSpEdadMinima());
		contentPane.add(getLbTipo());
		contentPane.add(getCbTipo());
		contentPane.add(getScrollCategorias());
		contentPane.add(getBtAddCategoria());
		contentPane.add(getBtnEliminarCategoria());
		contentPane.add(getSpCatMin());
		contentPane.add(getSpCatMax());
		contentPane.add(getLbEdadMinimaCategoria());
		contentPane.add(getLblLmiteSuperior());
		contentPane.add(getTxtNombreCategoria());
		contentPane.add(getScrollFechas());
		contentPane.add(getCbDia());
		contentPane.add(getCbMes());
		contentPane.add(getCbYear());
		contentPane.add(getBtEliminarFecha());
		contentPane.add(getBtAddFecha());
		contentPane.add(getSpPrecio());
		contentPane.add(getCbDiaFin());
		contentPane.add(getCbMesFin());
		contentPane.add(getCbYearFin());
		contentPane.add(getLblFechaInicioInscripcin());
		contentPane.add(getLblFechaFinInscripcion());
		contentPane.add(getLblPrecioeuros());
		contentPane.add(getLblCategorias());
		contentPane.add(getBtAddCarrera());
		contentPane.add(getLbFecha());
		contentPane.add(getCbDiaFecha());
		contentPane.add(getCbMesFecha());
		contentPane.add(getCbYearFecha());
		contentPane.add(getLbCuenta());
		contentPane.add(getTxtCuenta());
	}
	
	//==========================================================================================
	//										COMPONENTES IGU: 
	//==========================================================================================
		

	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
			panelTitulo.setBackground(Color.DARK_GRAY);
			panelTitulo.setBounds(0, 0, 729, 80);
			panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
			panelTitulo.add(getLblCrearCarrera());
		}
		return panelTitulo;
	}
	private JLabel getLblCrearCarrera() {
		if (lblCrearCarrera == null) {
			lblCrearCarrera = new JLabel("CREAR CARRERA");
			lblCrearCarrera.setForeground(Color.LIGHT_GRAY);
			lblCrearCarrera.setFont(new Font("Source Sans Pro Semibold", Font.BOLD | Font.ITALIC, 40));
		}
		return lblCrearCarrera;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setForeground(Color.DARK_GRAY);
			lblNombre.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lblNombre.setBounds(25, 100, 78, 28);
		}
		return lblNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			txtNombre.setBounds(162, 100, 202, 28);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JLabel getLbLugar() {
		if (lbLugar == null) {
			lbLugar = new JLabel("Lugar:");
			lbLugar.setForeground(Color.DARK_GRAY);
			lbLugar.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lbLugar.setBounds(25, 141, 78, 28);
		}
		return lbLugar;
	}
	private JTextField getTxtLugar() {
		if (txtLugar == null) {
			txtLugar = new JTextField();
			txtLugar.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			txtLugar.setColumns(10);
			txtLugar.setBounds(162, 139, 202, 28);
		}
		return txtLugar;
	}
	private JLabel getLbDNI() {
		if (lbDNI == null) {
			lbDNI = new JLabel("DNI del Organizador:");
			lbDNI.setForeground(Color.DARK_GRAY);
			lbDNI.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lbDNI.setBounds(25, 180, 130, 28);
		}
		return lbDNI;
	}
	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			txtDNI.setColumns(10);
			txtDNI.setBounds(162, 178, 202, 28);
		}
		return txtDNI;
	}
	private JLabel getLbParticipantes() {
		if (lbParticipantes == null) {
			lbParticipantes = new JLabel("N\u00BA M\u00E1ximo participantes");
			lbParticipantes.setForeground(Color.DARK_GRAY);
			lbParticipantes.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lbParticipantes.setBounds(375, 100, 153, 28);
		}
		return lbParticipantes;
	}
	private JSpinner getSpParticipantes() {
		if (spParticipantes == null) {
			spParticipantes = new JSpinner();
			spParticipantes.setModel(new SpinnerNumberModel(new Integer(100), new Integer(1), null, new Integer(1)));
			spParticipantes.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 13));
			spParticipantes.setBounds(542, 100, 83, 28);
		}
		return spParticipantes;
	}
	private JLabel getLbDistancia() {
		if (lbDistancia == null) {
			lbDistancia = new JLabel("Distancia(Km):");
			lbDistancia.setForeground(Color.DARK_GRAY);
			lbDistancia.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lbDistancia.setBounds(374, 141, 153, 28);
		}
		return lbDistancia;
	}
	private JSpinner getSpDistancia() {
		if (spDistancia == null) {
			spDistancia = new JSpinner();
			spDistancia.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1)));
			spDistancia.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 13));
			spDistancia.setBounds(542, 141, 83, 28);
		}
		return spDistancia;
	}
	private JLabel getLbDureza() {
		if (lbDureza == null) {
			lbDureza = new JLabel("Dureza:");
			lbDureza.setForeground(Color.DARK_GRAY);
			lbDureza.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lbDureza.setBounds(25, 219, 130, 28);
		}
		return lbDureza;
	}
	private JComboBox<String> getCbDureza() {
		if (cbDureza == null) {
			cbDureza = new JComboBox<String>();
			cbDureza.setModel(new DefaultComboBoxModel<String>(new String[] {"Suave", "Media", "Dura", "Extrema"}));
			cbDureza.setBounds(162, 217, 202, 30);
		}
		return cbDureza;
	}
	private JLabel getLbEdadMinima() {
		if (lbEdadMinima == null) {
			lbEdadMinima = new JLabel("Edad M\u00EDnima:");
			lbEdadMinima.setForeground(Color.DARK_GRAY);
			lbEdadMinima.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lbEdadMinima.setBounds(374, 178, 153, 28);
		}
		return lbEdadMinima;
	}
	private JSpinner getSpEdadMinima() {
		if (spEdadMinima == null) {
			spEdadMinima = new JSpinner();
			spEdadMinima.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					int ed = (int)spEdadMinima.getValue();
					spCatMin.setModel(new SpinnerNumberModel(new Integer(ed), new Integer(ed), null, new Integer(ed)));
					spCatMax.setModel(new SpinnerNumberModel(new Integer(ed), new Integer(ed), null, new Integer(ed)));
					//spCatMax.updateUI();
					//spCatMin.updateUI();
				}
			});
			
			spEdadMinima.setModel(new SpinnerNumberModel(18, 6, 140, 1));
			spEdadMinima.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 13));
			spEdadMinima.setBounds(542, 180, 83, 28);
		}
		return spEdadMinima;
	}
	private JLabel getLbTipo() {
		if (lbTipo == null) {
			lbTipo = new JLabel("Tipo:");
			lbTipo.setForeground(Color.DARK_GRAY);
			lbTipo.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lbTipo.setBounds(375, 219, 130, 28);
		}
		return lbTipo;
	}
	private JComboBox<String> getCbTipo() {
		if (cbTipo == null) {
			cbTipo = new JComboBox<String>();
			cbTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Urbana", "Monta\u00F1a"}));
			cbTipo.setBounds(542, 217, 187, 30);
		}
		return cbTipo;
	}
	private JScrollPane getScrollCategorias() {
		if (scrollCategorias == null) {
			scrollCategorias = new JScrollPane();
			scrollCategorias.setBounds(25, 369, 175, 158);
			scrollCategorias.setViewportView(getListaCategorias());
		}
		return scrollCategorias;
	}
	private JList<Categoria> getListaCategorias() {
		if (listaCategorias == null) {
			listaCategorias = new JList<Categoria>();
			listaCategorias.setModel(getModeloCategorias());
		}
		return listaCategorias;
	}
	private ListModel<Categoria> getModeloCategorias() {
		if(modeloCategoria==null) {
			modeloCategoria = new DefaultListModel<Categoria>();
		}
		return modeloCategoria;
	}

	private JButton getBtAddCategoria() {
		if (btAddCategoria == null) {
			btAddCategoria = new JButton("A\u00F1adir nueva categoria");
			btAddCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addCategoria();
				}
			});
			btAddCategoria.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btAddCategoria.setBounds(210, 461, 154, 28);
		}
		return btAddCategoria;
	}
	private JButton getBtnEliminarCategoria() {
		if (btnEliminarCategoria == null) {
			btnEliminarCategoria = new JButton("Eliminar categoria");
			btnEliminarCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarCategoria();
				}
			});
			btnEliminarCategoria.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btnEliminarCategoria.setBounds(210, 504, 154, 23);
		}
		return btnEliminarCategoria;
	}
	private JSpinner getSpCatMin() {
		if (spCatMin == null) {
			spCatMin = new JSpinner();
			spCatMin.setModel(new SpinnerNumberModel(new Integer(18), new Integer(18), null, new Integer(1)));
			spCatMin.setBounds(210, 399, 46, 20);
		}
		return spCatMin;
	}
	private JSpinner getSpCatMax() {
		if (spCatMax == null) {
			spCatMax = new JSpinner();
			spCatMax.setModel(new SpinnerNumberModel(new Integer(18), new Integer(18), null, new Integer(1)));
			spCatMax.setBounds(210, 430, 46, 20);
		}
		return spCatMax;
	}
	private JLabel getLbEdadMinimaCategoria() {
		if (lbEdadMinimaCategoria == null) {
			lbEdadMinimaCategoria = new JLabel("L\u00EDmite inferior");
			lbEdadMinimaCategoria.setForeground(Color.DARK_GRAY);
			lbEdadMinimaCategoria.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lbEdadMinimaCategoria.setBounds(265, 396, 99, 28);
		}
		return lbEdadMinimaCategoria;
	}
	private JLabel getLblLmiteSuperior() {
		if (lblLmiteSuperior == null) {
			lblLmiteSuperior = new JLabel("L\u00EDmite superior");
			lblLmiteSuperior.setForeground(Color.DARK_GRAY);
			lblLmiteSuperior.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lblLmiteSuperior.setBounds(265, 427, 99, 28);
		}
		return lblLmiteSuperior;
	}
	private JTextField getTxtNombreCategoria() {
		if (txtNombreCategoria == null) {
			txtNombreCategoria = new JTextField();
			txtNombreCategoria.setText("Nombre");
			txtNombreCategoria.setName("");
			txtNombreCategoria.setBounds(210, 368, 154, 20);
			txtNombreCategoria.setColumns(10);
		}
		return txtNombreCategoria;
	}
	private JScrollPane getScrollFechas() {
		if (scrollFechas == null) {
			scrollFechas = new JScrollPane();
			scrollFechas.setBounds(375, 369, 354, 65);
			scrollFechas.setViewportView(getListaFechasInscripcion());
		}
		return scrollFechas;
	}
	private JList<FechaInscripcion> getListaFechasInscripcion() {
		if (listaFechasInscripcion == null) {
			listaFechasInscripcion = new JList<FechaInscripcion>();
			listaFechasInscripcion.setModel(getModeloFechas());
		}
		return listaFechasInscripcion;
	}
	private ListModel<FechaInscripcion> getModeloFechas() {
		if(modeloFechas==null) {
			modeloFechas = new DefaultListModel<FechaInscripcion>();
		}
		return modeloFechas;
	}
	private JComboBox<Integer> getCbDia() {
		if (cbDia == null) {
			cbDia = new JComboBox<Integer>();
			cbDia.setBounds(432, 304, 58, 24);
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
			cbMes.setBounds(500, 304, 125, 24);
		}
		return cbMes;
	}
	private JComboBox<Integer> getCbYear() {
		if (cbYear == null) {
			cbYear = new JComboBox<Integer>();
			cbYear.setBounds(644, 304, 85, 24);
			int[] years = new int[150];
			for(int i=0; i<150 ;i++) { years[i] = year+i;cbYear.addItem(years[i]);}
			cbYear.setSelectedItem(1999);
			cbYear.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					year = (Integer)cbYear.getSelectedItem();
				}
			});
		}
		return cbYear;
	}
	
	private JComboBox<Integer> getCbDiaFin() {
		if (cbDiaFin == null) {
			cbDiaFin = new JComboBox<Integer>();
			cbDiaFin.setBounds(432, 339, 58, 24);
			cbDiaFin.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					diaFin = cbDiaFin.getSelectedIndex()+1;
				}
			});
			calcularDiasFin();
		}
		return cbDiaFin;
	}
	private JComboBox<String> getCbMesFin() {
		if (cbMesFin == null) {
			cbMesFin = new JComboBox<String>();
			cbMesFin.setBounds(500, 339, 125, 24);
			cbMesFin.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					mesFin = cbMesFin.getSelectedIndex()+1;
					calcularDias();
					cbDiaFin.updateUI();
				}
			});
			cbMesFin.setModel(new DefaultComboBoxModel<String>
			(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", 
				"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		}
		return cbMesFin;
	}
	private JComboBox<Integer> getCbYearFin() {
		if (cbYearFin == null) {
			cbYearFin = new JComboBox<Integer>();
			cbYearFin.setBounds(644, 339, 85, 24);
			int[] years = new int[150];
			for(int i=0; i<150 ;i++) { years[i] = yearFin+i;cbYearFin.addItem(years[i]);}
			cbYearFin.setSelectedItem(1999);
			cbYearFin.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					yearFin = (Integer)cbYearFin.getSelectedItem();
				}
			});
		}
		return cbYearFin;
	}
	private void calcularDiasFin() {
		cbDiaFin.removeAll();
		if(mesFin == 1|| mesFin == 3 || mesFin == 5 || mesFin == 7 || mesFin == 8 || mesFin == 10 || mesFin == 12)
			for(int i = 1;i <=31 ; i++) cbDiaFin.addItem(i);
		else if(mesFin == 2)
			for(int i = 1;i <=28 ; i++) cbDiaFin.addItem(i);
		else
			for(int i = 1;i <=30 ; i++) cbDiaFin.addItem(i);
	}
	private void calcularDiasFecha() {
		cbDiaFecha.removeAll();
		if(mesFecha == 1|| mesFecha == 3 || mesFecha == 5 || mesFecha == 7 || mesFecha == 8 || mesFecha == 10 || mesFecha == 12)
			for(int i = 1;i <=31 ; i++) cbDiaFecha.addItem(i);
		else if(mesFecha == 2)
			for(int i = 1;i <=28 ; i++) cbDiaFecha.addItem(i);
		else
			for(int i = 1;i <=30 ; i++) cbDiaFecha.addItem(i);
	}
	
	private JComboBox<Integer> getCbDiaFecha() {
		if (cbDiaFecha == null) {
			cbDiaFecha = new JComboBox<Integer>();
			cbDiaFecha.setBounds(97, 258, 58, 24);
			cbDiaFecha.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					diaFecha = cbDiaFecha.getSelectedIndex()+1;
				}
			});
			calcularDiasFecha();
		}
		return cbDiaFecha;
	}
	private JComboBox<String> getCbMesFecha() {
		if (cbMesFecha == null) {
			cbMesFecha = new JComboBox<String>();
			cbMesFecha.setBounds(162, 259, 115, 24);
			cbMesFecha.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					mesFecha = cbMesFecha.getSelectedIndex()+1;
					calcularDias();
					cbDiaFecha.updateUI();
				}
			});
			cbMesFecha.setModel(new DefaultComboBoxModel<String>
			(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", 
				"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		}
		return cbMesFecha;
	}
	private JComboBox<Integer> getCbYearFecha() {
		if (cbYearFecha == null) {
			cbYearFecha = new JComboBox<Integer>();
			cbYearFecha.setBounds(285, 258, 78, 24);
			int[] years = new int[150];
			for(int i=0; i<150 ;i++) { years[i] = yearFecha+i;cbYearFecha.addItem(years[i]);}
			cbYearFecha.setSelectedItem(1999);
			cbYearFecha.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					yearFecha = (Integer)cbYearFecha.getSelectedItem();
				}
			});
		}
		return cbYearFecha;
	}
	
	private JSpinner getSpPrecio() {
		if (spPrecio == null) {
			spPrecio = new JSpinner();
			spPrecio.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spPrecio.setBounds(482, 437, 46, 23);
		}
		return spPrecio;
	}
	
	private JLabel getLblFechaInicioInscripcin() {
		if (lblFechaInicioInscripcin == null) {
			lblFechaInicioInscripcin = new JLabel("Fecha Inicio Inscripci\u00F3n:");
			lblFechaInicioInscripcin.setForeground(Color.DARK_GRAY);
			lblFechaInicioInscripcin.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lblFechaInicioInscripcin.setBounds(277, 303, 145, 28);
		}
		return lblFechaInicioInscripcin;
	}
	private JLabel getLblFechaFinInscripcion() {
		if (lblFechaFinInscripcion == null) {
			lblFechaFinInscripcion = new JLabel("Fecha Fin Inscripci\u00F3n:");
			lblFechaFinInscripcion.setForeground(Color.DARK_GRAY);
			lblFechaFinInscripcion.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lblFechaFinInscripcion.setBounds(292, 338, 130, 28);
		}
		return lblFechaFinInscripcion;
	}
	private JLabel getLblPrecioeuros() {
		if (lblPrecioeuros == null) {
			lblPrecioeuros = new JLabel("Precio(Euros)");
			lblPrecioeuros.setForeground(Color.DARK_GRAY);
			lblPrecioeuros.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lblPrecioeuros.setBounds(373, 435, 99, 28);
		}
		return lblPrecioeuros;
	}
	private JLabel getLblCategorias() {
		if (lblCategorias == null) {
			lblCategorias = new JLabel("CATEGORIAS:");
			lblCategorias.setForeground(Color.DARK_GRAY);
			lblCategorias.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lblCategorias.setBounds(25, 330, 145, 28);
		}
		return lblCategorias;
	}
	
	private JButton getBtEliminarFecha() {
		if (btEliminarFecha == null) {
			btEliminarFecha = new JButton("Eliminar fecha");
			btEliminarFecha.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btEliminarFecha.setBounds(575, 463, 154, 28);
		}
		return btEliminarFecha;
	}
	private JButton getBtAddFecha() {
		if (btAddFecha == null) {
			btAddFecha = new JButton("A\u00F1adir nueva fecha");
			btAddFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addFecha();
				}
			});
			btAddFecha.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btAddFecha.setBounds(575, 434, 154, 28);
		}
		return btAddFecha;
	}
	
	private JLabel getLbFecha() {
		if (lbFecha == null) {
			lbFecha = new JLabel("Fecha:");
			lbFecha.setForeground(Color.DARK_GRAY);
			lbFecha.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lbFecha.setBounds(25, 258, 130, 28);
		}
		return lbFecha;
	}
	
	private JLabel getLbCuenta() {
		if (lbCuenta == null) {
			lbCuenta = new JLabel("Numero cuenta:");
			lbCuenta.setForeground(Color.DARK_GRAY);
			lbCuenta.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			lbCuenta.setBounds(375, 254, 130, 28);
		}
		return lbCuenta;
	}
	private JTextField getTxtCuenta() {
		if (txtCuenta == null) {
			txtCuenta = new JTextField();
			txtCuenta.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 13));
			txtCuenta.setColumns(10);
			txtCuenta.setBounds(542, 254, 187, 28);
		}
		return txtCuenta;
	}
	private JButton getBtAddCarrera() {
		if (btAddCarrera == null) {
			btAddCarrera = new JButton("A\u00F1adir Carrera");
			btAddCarrera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addCarrera();
				}
			});
			btAddCarrera.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btAddCarrera.setBounds(432, 553, 154, 28);
		}
		return btAddCarrera;
	}
	
	//==========================================================================================
	//										LOGICA: 
	//==========================================================================================
		
	
	/**
	 * Añade una fecha de inscripcion a la carrera
	 */
	protected void addFecha() {
		dia = (int)cbDia.getSelectedItem();mes = (int) cbMes.getSelectedIndex()+1;year = (int) cbYear.getSelectedItem();
		FechaInscripcion fecha = new FechaInscripcion(dia+"/"+mes+"/"+year,diaFin+"/"+mesFin+"/"+yearFin,(int)spPrecio.getValue());
		ArrayList<FechaInscripcion> fechas = new ArrayList<FechaInscripcion>();
		for(int i=0; i<modeloFechas.getSize();i++) {fechas.add(modeloFechas.get(i));}
		for (FechaInscripcion fi : fechas) {
			Date fi1 = new Date(fi.getFecha());
			Date fi2 = new Date(fecha.getFecha());
			Date ff1 = new Date(fi.getFechaFin());
			Date ff2 = new Date(fecha.getFechaFin());
			if(((fi1.compareTo(fi2) > 0 && fi1.compareTo(ff2) > 0) || (fi1.compareTo(fi2) < 0 && ff1.compareTo(fi2) < 0))
					&& fi2.compareTo(ff2) < 0) {
				modeloFechas.addElement(fecha);
				listaFechasInscripcion.setModel(modeloFechas);
				return;
			}
		}
		Date fi2 = new Date(fecha.getFecha());
		Date ff2 = new Date(fecha.getFechaFin());
		if(fechas.size()==0 && fi2.compareTo(ff2) < 0) {
			modeloFechas.addElement(fecha);
			listaFechasInscripcion.setModel(modeloFechas);
		}
		else {
			JOptionPane.showMessageDialog(null, "La fecha de inscripción no es correcta, comprueba que no exista otra con el"
					+ "mismo periodo de tiempo o que la fecha de final sea mayor que la de inicio.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	/**
	 * Añade una categoria a la carrera
	 */
	private void addCategoria() {
		String nombreCat = txtNombreCategoria.getText();
		int emin = (int) spCatMin.getValue(),emax = (int) spCatMax.getValue();
		if(emin>emax) {
			JOptionPane.showMessageDialog(null, "La edad máxima es menor que la edad minima",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(nombreCat != null && nombreCat != "" ) {
			Categoria cat = new Categoria(emin,emax,nombreCat);	
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			for(int i=0; i<modeloCategoria.getSize();i++) {
				if((cat.getEdadMin()>modeloCategoria.get(i).getEdadMin() && cat.getEdadMin()<modeloCategoria.get(i).getEdadMax())
						||(cat.getEdadMax()>modeloCategoria.get(i).getEdadMin() && cat.getEdadMax()<modeloCategoria.get(i).getEdadMax())) {
					JOptionPane.showMessageDialog(null, "Ya hay una categoría en ese rango de edades",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(cat.getEdadMin()==modeloCategoria.get(i).getEdadMin() && cat.getEdadMax()==modeloCategoria.get(i).getEdadMax()) {
					JOptionPane.showMessageDialog(null, "Ya hay una categoría en ese rango de edades",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			modeloCategoria.addElement(cat);
			for(int i=0; i<modeloCategoria.getSize();i++) {categorias.add(modeloCategoria.get(i));}
			Collections.sort(categorias);
			modeloCategoria.clear();
			for (Categoria categoria : categorias) {
				modeloCategoria.addElement(categoria);
			}
			listaCategorias.setModel(modeloCategoria);
			int minimo = categorias.get(0).getEdadMin()>(int)spEdadMinima.getValue()?(int)spEdadMinima.getValue():categorias.get(0).getEdadMax();
			boolean temp = false;
			for (Categoria categoria : categorias) {
				if(categoria.getEdadMin()>minimo) {
					spCatMin.setModel(new SpinnerNumberModel(new Integer(minimo), new Integer(minimo), null, new Integer(1)));
					spCatMax.setModel(new SpinnerNumberModel(new Integer(categoria.getEdadMin()), new Integer(categoria.getEdadMin())
							, null, new Integer(1)));
					temp = true;
					return;
				}
				minimo = categoria.getEdadMax();
			}
			if(!temp) {
				int max = categorias.get(categorias.size()-1).getEdadMax();
				spCatMin.setModel(new SpinnerNumberModel(new Integer(max), new Integer(max), null, new Integer(1)));
				spCatMax.setModel(new SpinnerNumberModel(new Integer(max), new Integer(max), null, new Integer(1)));
			}
		}
	}
	
	/**
	 * Elimina una categoria de la carrera
	 */
	private void eliminarCategoria() {
		if(listaCategorias.getSelectedIndex()!=-1) {
			modeloCategoria.removeElementAt(listaCategorias.getSelectedIndex());
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			for(int i=0; i<modeloCategoria.getSize();i++) {categorias.add(modeloCategoria.get(i));}
			Collections.sort(categorias);
			modeloCategoria.clear();
			for (Categoria categoria : categorias) {
				modeloCategoria.addElement(categoria);
			}
			listaCategorias.setModel(modeloCategoria);
			int minimo = categorias.get(0).getEdadMin()>(int)spEdadMinima.getValue()?(int)spEdadMinima.getValue():categorias.get(0).getEdadMax();
			boolean temp = false;
			for (Categoria categoria : categorias) {
				if(categoria.getEdadMin()>minimo) {
					spCatMin.setModel(new SpinnerNumberModel(new Integer(minimo), new Integer(minimo), null, new Integer(1)));
					spCatMax.setModel(new SpinnerNumberModel(new Integer(categoria.getEdadMin()), new Integer(categoria.getEdadMin())
							, null, new Integer(1)));
					temp = true;
					return;
				}
				minimo = categoria.getEdadMax();
			}
			if(!temp) {
				int max = categorias.get(categorias.size()-1).getEdadMax();
				spCatMin.setModel(new SpinnerNumberModel(new Integer(max), new Integer(max), null, new Integer(1)));
				spCatMax.setModel(new SpinnerNumberModel(new Integer(max), new Integer(max), null, new Integer(1)));
			}
		}
	}
	
	/**
	 * Añade una carrera a la base de datos
	 */
	protected void addCarrera() {
		Carrera c = createCarrera();
		if(c!= null) {
			try {
				GestorDB.addCarrera(c);
			} catch (SQLException e) {
				System.out.println("Error al añadir la carrera");
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Se ha creado la carrera",
					"Creada con exito", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	

	/**
	 * Crea el objeto carrera y lo devuelve
	 * @return Carrera
	 */
	private Carrera createCarrera() {
		String nombre = txtNombre.getText();
		String lugar = txtLugar.getText();
		String fecha = diaFecha+"/"+mesFecha+"/"+yearFecha;
		int participantes = (int) spParticipantes.getValue();
		int distancia = (int) spDistancia.getValue();
		String dureza = (String) cbDureza.getSelectedItem();
		int edad = (int) spEdadMinima.getValue();
		String tipo = (String) cbTipo.getSelectedItem();
		String numCuenta = txtCuenta.getText();
		String dni = txtDNI.getText();
		ArrayList<FechaInscripcion> fechas = new ArrayList<FechaInscripcion>();
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		int id = -1;
		for(int i=0; i<modeloFechas.getSize();i++) {
			fechas.add(modeloFechas.getElementAt(i));
		}
		for(int i=0; i<modeloCategoria.getSize();i++) {
			categorias.add(modeloCategoria.getElementAt(i));
		}
		try {
			id = GestorDB.sacarTodasLasCarreras().size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Carrera c = null;
		if(!nombre.equals("") && !lugar.equals("") && fecha != null && dureza != null && tipo!= null && !numCuenta.equals("") && !dni.equals("")
				&& fechas.size()!=0 && categorias.size()!=0) {
			c = new Carrera(id+1,nombre,lugar,fecha,participantes,distancia,dureza,edad,tipo,numCuenta,dni,fechas,categorias);
		}
		else {
			JOptionPane.showMessageDialog(null, "Tienes algun campo sin rellenar o mal rellenado",
					"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return c;
	}
	
}