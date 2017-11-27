package iguRegistros;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.ListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import logic.Date;
import logic.FechaCancelacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarreraConfigurarCancelacion extends JDialog {
	private static final long serialVersionUID = 1L;
	private JLabel lblFechaInicioPeriodo;
	private JComboBox<Integer> cbDiaInicio;
	private JComboBox<String> cbMesInicio;
	private JComboBox<Integer> cbYearInicio;
	private JLabel lbFechaFinPeriodo;
	private JComboBox<Integer> cbDiaFin;
	private JComboBox<String> cbMesFin;
	private JComboBox<Integer> cbYearFin;
	private JLabel lbDevolver;
	private JSpinner spDevolver;
	private JButton btAdd;
	private JButton btEliminar;
	private JScrollPane scrollPane;
	private JList<FechaCancelacion> lista;
	private int mesFin,mesInicio,diaFin,diaInicio,yearFin,yearInicio;
	private DefaultListModel<FechaCancelacion> modeloFechas;
	private JButton btAceptar;
	
	/**
	 * Create the dialog.
	 */
	public CarreraConfigurarCancelacion() {
		Calendar fecha = new GregorianCalendar();
		diaInicio = fecha.get(Calendar.DAY_OF_MONTH);mesInicio = fecha.get(Calendar.MONTH); yearInicio = fecha.get(Calendar.YEAR);
		diaFin = fecha.get(Calendar.DAY_OF_MONTH);mesFin = fecha.get(Calendar.MONTH); yearFin = fecha.get(Calendar.YEAR);
		setTitle("Fechas Cancelacion");
		setBounds(100, 100, 626, 360);
		getContentPane().setLayout(null);
		getContentPane().add(getLblFechaInicioPeriodo());
		getContentPane().add(getCbDiaInicio());
		getContentPane().add(getCbMesInicio());
		getContentPane().add(getCbYearInicio());
		getContentPane().add(getLbFechaFinPeriodo());
		getContentPane().add(getCbDiaFin());
		getContentPane().add(getCbMesFin());
		getContentPane().add(getCbYearFin());
		getContentPane().add(getLbDevolver());
		getContentPane().add(getSpDevolver());
		getContentPane().add(getBtAdd());
		getContentPane().add(getBtEliminar());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtAceptar());

	}

	private JLabel getLblFechaInicioPeriodo() {
		if (lblFechaInicioPeriodo == null) {
			lblFechaInicioPeriodo = new JLabel("Fecha Inicio Periodo Cancelacion:");
			lblFechaInicioPeriodo.setBounds(10, 26, 242, 39);
			lblFechaInicioPeriodo.setForeground(Color.DARK_GRAY);
			lblFechaInicioPeriodo.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lblFechaInicioPeriodo;
	}
	private JComboBox<Integer> getCbDiaInicio() {
		if (cbDiaInicio == null) {
			cbDiaInicio = new JComboBox<Integer>();
			cbDiaInicio.setBounds(273, 34, 58, 24);
			cbDiaInicio.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					diaInicio = cbDiaInicio.getSelectedIndex()+1;
				}
			});
			calcularDiasInicio();
		}
		return cbDiaInicio;
	}
	private JComboBox<String> getCbMesInicio() {
		if (cbMesInicio == null) {
			cbMesInicio = new JComboBox<String>();
			cbMesInicio.setBounds(341, 34, 125, 24);
			cbMesInicio.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					mesInicio = cbMesInicio.getSelectedIndex()+1;
					calcularDiasInicio();
					cbDiaInicio.updateUI();
				}
			});
			cbMesInicio.setModel(new DefaultComboBoxModel<String>
				(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", 
					"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		}
		return cbMesInicio;
	}
	private JComboBox<Integer> getCbYearInicio() {
		if (cbYearInicio == null) {
			cbYearInicio = new JComboBox<Integer>();
			cbYearInicio.setBounds(476, 34, 85, 24);
			int[] years = new int[150];
			for(int i=0; i<150 ;i++) { years[i] = yearInicio+i;cbYearInicio.addItem(years[i]);}
			cbYearInicio.setSelectedItem(1999);
			cbYearInicio.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					yearInicio = (Integer)cbYearInicio.getSelectedItem();
				}
			});
		}
		return cbYearInicio;
	}
	private JLabel getLbFechaFinPeriodo() {
		if (lbFechaFinPeriodo == null) {
			lbFechaFinPeriodo = new JLabel("Fecha Fin Periodo Cancelacion:");
			lbFechaFinPeriodo.setForeground(Color.DARK_GRAY);
			lbFechaFinPeriodo.setFont(new Font("Dialog", Font.BOLD, 13));
			lbFechaFinPeriodo.setBounds(10, 78, 242, 39);
		}
		return lbFechaFinPeriodo;
	}
	private JComboBox<Integer> getCbDiaFin() {
		if (cbDiaFin == null) {
			cbDiaFin = new JComboBox<Integer>();
			cbDiaFin.setBounds(273, 86, 58, 24);
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
			cbMesFin.setBounds(341, 86, 125, 24);
			cbMesFin.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					mesFin = cbMesFin.getSelectedIndex()+1;
					calcularDiasInicio();
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
			cbYearFin.setBounds(476, 86, 85, 24);
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
	private JLabel getLbDevolver() {
		if (lbDevolver == null) {
			lbDevolver = new JLabel("% A devolver:");
			lbDevolver.setForeground(Color.DARK_GRAY);
			lbDevolver.setFont(new Font("Dialog", Font.BOLD, 13));
			lbDevolver.setBounds(10, 128, 99, 28);
		}
		return lbDevolver;
	}
	private JSpinner getSpDevolver() {
		if (spDevolver == null) {
			spDevolver = new JSpinner();
			spDevolver.setModel(new SpinnerNumberModel(0, null, 100, 1));
			spDevolver.setBounds(119, 133, 99, 23);
		}
		return spDevolver;
	}
	private JButton getBtAdd() {
		if (btAdd == null) {
			btAdd = new JButton("A\u00F1adir nueva fecha");
			btAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addFecha();
				}
			});
			btAdd.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btAdd.setBounds(273, 129, 154, 28);
		}
		return btAdd;
	}
	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar fecha");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					deleteFecha();
				}
			});
			btEliminar.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btEliminar.setBounds(434, 129, 154, 28);
		}
		return btEliminar;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 168, 590, 102);
			scrollPane.setViewportView(getLista());
		}
		return scrollPane;
	}
	private JList<FechaCancelacion> getLista() {
		if (lista == null) {
			lista = new JList<FechaCancelacion>();
			lista.setModel(getModeloFechas());
		}
		return lista;
	}
	private ListModel<FechaCancelacion> getModeloFechas() {
		if(modeloFechas==null) {
			modeloFechas = new DefaultListModel<FechaCancelacion>();
		}
		return modeloFechas;
	}
	
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					enviarDatos();
				}
			});
			btAceptar.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btAceptar.setBounds(228, 282, 154, 28);
		}
		return btAceptar;
	}
	
	//==========================================================================================
	//										LOGICA: 
	//==========================================================================================
	
	private void calcularDiasInicio() {
		cbDiaInicio.removeAll();
		if(mesInicio == 1|| mesInicio == 3 || mesInicio == 5 || mesInicio == 7 || mesInicio == 8 || mesInicio == 10 || mesInicio == 12)
			for(int i = 1;i <=31 ; i++) cbDiaInicio.addItem(i);
		else if(mesInicio == 2)
			for(int i = 1;i <=28 ; i++) cbDiaInicio.addItem(i);
		else
			for(int i = 1;i <=30 ; i++) cbDiaInicio.addItem(i);
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
	
	/**
	 * Añade una fecha de cancelacion a la carrera
	 */
	protected void addFecha() {
		diaInicio = (int)cbDiaInicio.getSelectedItem();
		mesInicio = (int) cbMesInicio.getSelectedIndex()+1;
		yearInicio = (int) cbYearInicio.getSelectedItem();
		FechaCancelacion fecha = new FechaCancelacion(diaInicio+"/"+mesInicio+"/"+yearInicio,diaFin+"/"+mesFin+"/"+yearFin,
				(int)spDevolver.getValue());
		ArrayList<FechaCancelacion> fechas = new ArrayList<FechaCancelacion>();
		for(int i=0; i<modeloFechas.getSize();i++) {fechas.add(modeloFechas.get(i));}
		for (FechaCancelacion fi : fechas) {
			Date fi1 = new Date(fi.getFecha());
			Date fi2 = new Date(fecha.getFecha());
			Date ff1 = new Date(fi.getFechaFin());
			Date ff2 = new Date(fecha.getFechaFin());
			if(((fi1.compareTo(fi2) > 0 && fi1.compareTo(ff2) > 0) || (fi1.compareTo(fi2) < 0 && ff1.compareTo(fi2) < 0))
					&& fi2.compareTo(ff2) < 0) {
				modeloFechas.addElement(fecha);
				lista.setModel(modeloFechas);
				return;
			}
		}
		Date fi2 = new Date(fecha.getFecha());
		Date ff2 = new Date(fecha.getFechaFin());
		if(fechas.size()==0 && fi2.compareTo(ff2) < 0) {
			modeloFechas.addElement(fecha);
			lista.setModel(modeloFechas);
		}
		else {
			JOptionPane.showMessageDialog(null, "La fecha de inscripción no es correcta, comprueba que no exista otra con el"
					+ "mismo periodo de tiempo o que la fecha de final sea mayor que la de inicio.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Metodo que elimina una fecha de cancelacion
	 */
	public void deleteFecha() {
		if(modeloFechas.size() != 0 && lista.getSelectedIndex()!=-1) {
			modeloFechas.remove(lista.getSelectedIndex());
			lista.setModel(modeloFechas);
		}
	}
	
	/**
	 * Metodo que envia a el registro Carrera
	 * las fechas de cancelacion de la misma
	 */
	public void enviarDatos() {
		ArrayList<FechaCancelacion> fechas = new ArrayList<FechaCancelacion>();
		for (int i = 0; i < modeloFechas.size(); i++) {
			fechas.add(modeloFechas.getElementAt(i));
		}
		RegistroCarrera.fechas_cancelacion = fechas;
		dispose();
	}
	
}
