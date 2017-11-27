package iguRegistros;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.ListModel;
import javax.swing.SpinnerNumberModel;
import logic.PuntoControl;
import javax.swing.JSeparator;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CarreraPuntosControl extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lbTiempoMax;
	private JSpinner spHoras;
	private JSpinner spMinutos;
	private JLabel lblHoras;
	private JLabel lblMinutos;
	private JSeparator separator;
	private JLabel lblKm;
	private JSpinner spKm;
	private JLabel lblTiempoMaximo;
	private JSpinner spHorasPunto;
	private JLabel lbHorasKm;
	private JSpinner spMinutosPunto;
	private JLabel lbMinutosPunto;
	private JButton btAdd;
	private JScrollPane scrollPane;
	private JList<PuntoControl> lista;
	private JButton btAceptar;
	private DefaultListModel<PuntoControl> modeloPuntos;
	private JButton btEliminarPunto;
	private int distancia;


	/**
	 * Create the dialog.
	 */
	public CarreraPuntosControl(int distancia) {
		this.distancia = distancia;
		setTitle("Puntos de Control");
		setBounds(100, 100, 654, 362);
		getContentPane().setLayout(null);
		getContentPane().add(getLbTiempoMax());
		getContentPane().add(getSpHoras());
		getContentPane().add(getSpMinutos());
		getContentPane().add(getLblHoras());
		getContentPane().add(getLblMinutos());
		getContentPane().add(getSeparator());
		getContentPane().add(getLblKm());
		getContentPane().add(getSpKm());
		getContentPane().add(getLblTiempoMaximo());
		getContentPane().add(getSpHorasPunto());
		getContentPane().add(getLbHorasKm());
		getContentPane().add(getSpMinutosPunto());
		getContentPane().add(getLbMinutosPunto());
		getContentPane().add(getBtAdd());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtAceptar());
		getContentPane().add(getBtEliminarPunto());
	}

	private JLabel getLbTiempoMax() {
		if (lbTiempoMax == null) {
			lbTiempoMax = new JLabel("Tiempo maximo para finalizar la competicion:");
			lbTiempoMax.setBounds(10, 38, 319, 29);
			lbTiempoMax.setForeground(Color.DARK_GRAY);
			lbTiempoMax.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return lbTiempoMax;
	}
	private JSpinner getSpHoras() {
		if (spHoras == null) {
			spHoras = new JSpinner();
			spHoras.setModel(new SpinnerNumberModel(23, 0, 23, 1));
			spHoras.setBounds(355, 42, 81, 23);
		}
		return spHoras;
	}
	private JSpinner getSpMinutos() {
		if (spMinutos == null) {
			spMinutos = new JSpinner();
			spMinutos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
			spMinutos.setBounds(446, 42, 81, 23);
		}
		return spMinutos;
	}
	private JLabel getLblHoras() {
		if (lblHoras == null) {
			lblHoras = new JLabel("Horas");
			lblHoras.setBounds(355, 24, 46, 14);
		}
		return lblHoras;
	}
	private JLabel getLblMinutos() {
		if (lblMinutos == null) {
			lblMinutos = new JLabel("Minutos");
			lblMinutos.setBounds(446, 24, 46, 14);
		}
		return lblMinutos;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(10, 93, 621, 2);
		}
		return separator;
	}
	private JLabel getLblKm() {
		if (lblKm == null) {
			lblKm = new JLabel("Km");
			lblKm.setForeground(Color.DARK_GRAY);
			lblKm.setFont(new Font("Dialog", Font.BOLD, 13));
			lblKm.setBounds(10, 117, 46, 28);
		}
		return lblKm;
	}
	private JSpinner getSpKm() {
		if (spKm == null) {
			spKm = new JSpinner();
			spKm.setModel(new SpinnerNumberModel(0, null, distancia-1, 1));
			spKm.setBounds(56, 121, 81, 23);
		}
		return spKm;
	}
	private JLabel getLblTiempoMaximo() {
		if (lblTiempoMaximo == null) {
			lblTiempoMaximo = new JLabel("Tiempo maximo:");
			lblTiempoMaximo.setForeground(Color.DARK_GRAY);
			lblTiempoMaximo.setFont(new Font("Dialog", Font.BOLD, 13));
			lblTiempoMaximo.setBounds(147, 117, 125, 28);
		}
		return lblTiempoMaximo;
	}
	private JSpinner getSpHorasPunto() {
		if (spHorasPunto == null) {
			spHorasPunto = new JSpinner();
			spHorasPunto.setModel(new SpinnerNumberModel(12, 0, 23, 1));
			spHorasPunto.setBounds(270, 121, 81, 23);
		}
		return spHorasPunto;
	}
	private JLabel getLbHorasKm() {
		if (lbHorasKm == null) {
			lbHorasKm = new JLabel("Horas");
			lbHorasKm.setBounds(270, 106, 46, 14);
		}
		return lbHorasKm;
	}
	private JSpinner getSpMinutosPunto() {
		if (spMinutosPunto == null) {
			spMinutosPunto = new JSpinner();
			spMinutosPunto.setModel(new SpinnerNumberModel(30, 0, 59, 1));
			spMinutosPunto.setBounds(361, 121, 81, 23);
		}
		return spMinutosPunto;
	}
	private JLabel getLbMinutosPunto() {
		if (lbMinutosPunto == null) {
			lbMinutosPunto = new JLabel("Minutos");
			lbMinutosPunto.setBounds(361, 106, 46, 14);
		}
		return lbMinutosPunto;
	}
	private JButton getBtAdd() {
		if (btAdd == null) {
			btAdd = new JButton("A\u00F1adir punto de control");
			btAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					addPunto();
				}
			});
			btAdd.setBounds(467, 102, 164, 23);
		}
		return btAdd;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(13, 175, 618, 102);
			scrollPane.setViewportView(getLista());
		}
		return scrollPane;
	}
	private JList<PuntoControl> getLista() {
		if (lista == null) {
			lista = new JList<PuntoControl>();
			lista.setModel(getModeloFechas());
		}
		return lista;
	}
	private ListModel<PuntoControl> getModeloFechas() {
		if(modeloPuntos==null) {
			modeloPuntos = new DefaultListModel<PuntoControl>();
		}
		return modeloPuntos;
	}
	
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enviar();
				}
			});
			btAceptar.setBounds(258, 283, 176, 29);
		}
		return btAceptar;
	}
	
	private JButton getBtEliminarPunto() {
		if (btEliminarPunto == null) {
			btEliminarPunto = new JButton("Eliminar punto de control");
			btEliminarPunto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deletePunto();
				}
			});
			btEliminarPunto.setBounds(467, 136, 164, 23);
		}
		return btEliminarPunto;
	}
	
	//==========================================================================================
	//										LOGICA: 
	//==========================================================================================
	
	public void addPunto() {
		int km = (int)spKm.getValue();
		int horas = (int) spHorasPunto.getValue();
		int min = (int) spMinutosPunto.getValue();
		boolean flag = true;
		for (int i = 0; i < modeloPuntos.size(); i++) {
			if(modeloPuntos.getElementAt(i).getKm() == km) {
				JOptionPane.showMessageDialog(null, "Ya existe un punto de control en ese Km, por favor ingrese otro diferente",
						"Error", JOptionPane.ERROR_MESSAGE);
				flag = false;
			}
		}
		if(flag) {
			modeloPuntos.addElement(new PuntoControl(km,horas,min));
			lista.setModel(modeloPuntos);
		}
	}
	
	public void deletePunto() {
		if(modeloPuntos.size() != 0 && lista.getSelectedIndex()!=-1) {
			modeloPuntos.remove(lista.getSelectedIndex());
			lista.setModel(modeloPuntos);
		}
	}
	
	public void enviar() {
		ArrayList<PuntoControl> puntos = new ArrayList<PuntoControl>();
		puntos.add(new PuntoControl(distancia,(int)spHoras.getValue(),(int)spMinutos.getValue()));
		for (int i = 0; i < modeloPuntos.size(); i++) {
			puntos.add(modeloPuntos.getElementAt(i));
		}
		RegistroCarrera.puntos_control = puntos;
		dispose();
	}
	
}
