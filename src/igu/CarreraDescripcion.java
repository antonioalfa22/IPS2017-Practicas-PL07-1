package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Carrera;
import logic.GestorApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

/**
 * Ventana que representa la descripcion de una carrera.
 * 
 * @author Antonio Payá González
 *
 */
public class CarreraDescripcion extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbTitulo;
	private JLabel lbIconoLugar;
	private Carrera c;
	/**
	 * Create the dialog.
	 */
	public CarreraDescripcion(Carrera c,GestorApp gestor) {
		this.c = c;
		setTitle(c.getNombre());
		setBounds(100, 100, 611, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLbTitulo());
		contentPanel.add(getLbIconoLugar());
		{
			JLabel lblLugar = new JLabel("Lugar: "+c.getLugar());
			lblLugar.setForeground(Color.DARK_GRAY);
			lblLugar.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			lblLugar.setBounds(68, 50, 166, 27);
			contentPanel.add(lblLugar);
		}
		{
			JLabel label = new JLabel("");
			setImagenAdaptada(label,"/img/calendar1.png");
			label.setBounds(270, 50, 31, 28);
			setImagenAdaptada(label,"/img/calendar1.png");
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Fecha: "+c.getFecha());
			label.setForeground(Color.DARK_GRAY);
			label.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			label.setBounds(311, 50, 166, 27);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			setImagenAdaptada(label,"/img/icons8-People Filled-50.png");
			label.setBounds(20, 105, 31, 28);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Nº Máximo Participantes: "+c.getNum_max_part());
			label.setForeground(Color.DARK_GRAY);
			label.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			label.setBounds(68, 106, 166, 27);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			setImagenAdaptada(label,"/img/icons8-Date From-26.png");
			label.setBounds(270, 105, 31, 28);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Fecha inscripción: De"+c.getFechaInscripcionActual().getFecha()+" a "+c.getFechaInscripcionActual().getFechaFin());
			label.setForeground(Color.DARK_GRAY);
			label.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			label.setBounds(311, 105, 274, 27);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			setImagenAdaptada(label,"/img/icons8-Speed Filled-50.png");
			label.setBounds(20, 164, 31, 28);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Distancia: "+c.getDistancia()+" km");
			label.setForeground(Color.DARK_GRAY);
			label.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			label.setBounds(68, 165, 166, 27);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			setImagenAdaptada(label,"/img/icons8-Running Filled-50.png");
			label.setBounds(270, 164, 31, 28);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Dureza: "+c.getDureza());
			label.setForeground(Color.DARK_GRAY);
			label.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			label.setBounds(311, 165, 166, 27);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			setImagenAdaptada(label,"/img/icons8-Baby Face Filled-50.png");
			label.setBounds(20, 220, 31, 28);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Edad Mínima: "+c.getEdad_minima());
			label.setForeground(Color.DARK_GRAY);
			label.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			label.setBounds(68, 221, 166, 27);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			setImagenAdaptada(label,"/img/icons8-Treasure Map Filled-50.png");
			label.setBounds(270, 220, 31, 28);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Tipo: "+c.getTipo());
			label.setForeground(Color.DARK_GRAY);
			label.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			label.setBounds(311, 221, 166, 27);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			setImagenAdaptada(label,"/img/icons8-Money Bag-26.png");
			label.setBounds(203, 259, 31, 28);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Precio: "+c.getPrecio()+" €");
			label.setForeground(Color.DARK_GRAY);
			label.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 12));
			label.setBounds(244, 259, 166, 27);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btInscribirme = new JButton(" Inscribirme como Usuario");
				btInscribirme.setForeground(new Color(255, 255, 255));
				btInscribirme.setFont(new Font("Century Gothic", Font.BOLD, 11));
				btInscribirme.setBackground(new Color(60, 179, 113));
				if(VentanaPrincipal.gestorCarreras.getNumPreinscritos(c) < c.getNum_max_part()) {
					btInscribirme.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Register dialog = new Register(c,gestor);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dialog.setLocationRelativeTo(null);
							dialog.setResizable(false);
							
						}
					});
				}
				else {
					JOptionPane.showMessageDialog(null, "Ya se ha llenado el numero maximo de participantes",
							"Carrera completa", JOptionPane.ERROR_MESSAGE);
					btInscribirme.setEnabled(false);
				}
				{
					JButton btnInscribirUnEquipo = new JButton(" Inscribir un Equipo");
					btnInscribirUnEquipo.setForeground(Color.WHITE);
					btnInscribirUnEquipo.setFont(new Font("Century Gothic", Font.BOLD, 11));
					btnInscribirUnEquipo.setBackground(new Color(60, 179, 113));
					btnInscribirUnEquipo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
//							Register dialog = new Register(c,gestor);
//							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//							dialog.setVisible(true);
//							dialog.setLocationRelativeTo(null);
//							dialog.setResizable(false);
							
						}
					});
					buttonPane.add(btnInscribirUnEquipo);
				}
				
				buttonPane.add(btInscribirme);
			}
			{
				JButton btCancelar = new JButton("Cancelar");
				btCancelar.setForeground(new Color(255, 255, 255));
				btCancelar.setFont(new Font("Century Gothic", Font.BOLD, 11));
				btCancelar.setBackground(new Color(60, 179, 113));
				btCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(btCancelar);
			}
		}
	}
	
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel(c.getNombre());
			lbTitulo.setForeground(Color.DARK_GRAY);
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 26));
			lbTitulo.setBounds(10, 11, 491, 28);
		}
		return lbTitulo;
	}
	private JLabel getLbIconoLugar() {
		if (lbIconoLugar == null) {
			lbIconoLugar = new JLabel("");
			setImagenAdaptada(lbIconoLugar,"/img/icons8-Map Pinpoint Filled-50.png");
			lbIconoLugar.setBounds(20, 49, 31, 28);
		}
		return lbIconoLugar;
	}
	/**
	 * Metodo que pone una imagen a una Label
	 * @param lb
	 * @param ruta
	 */
	private void setImagenAdaptada(JLabel lb, String ruta){
		Image imgOriginal = new ImageIcon(getClass().getResource(ruta)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(30,30, Image.SCALE_FAST);
		lb.setIcon(new ImageIcon(imgEscalada));
		lb.setDisabledIcon(new ImageIcon(imgEscalada));
	}
}
