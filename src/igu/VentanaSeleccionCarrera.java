package igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;

import entities.Carrera;
import logic.GestorApp;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JButton;

/**
 * Ventana con un ComboBox que permite elegir una carrera para ver el estado de la 
 * inscripcion
 * 
 * @author Sara Grimaldos
 *
 */
public class VentanaSeleccionCarrera extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCarreras;
	private JComboBox<Carrera> cbCarreras;
	private JButton btnAceptar;
	

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
					VentanaSeleccionCarrera frame = new VentanaSeleccionCarrera();
					frame.setVisible(true);
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
	public VentanaSeleccionCarrera() throws SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblCarreras());
		contentPane.add(getCbCarreras());
		contentPane.add(getBtnAceptar());
	}

	private JLabel getLblCarreras() {
		if (lblCarreras == null) {
			lblCarreras = new JLabel("Carreras Populares:");
			lblCarreras.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCarreras.setBounds(43, 39, 139, 28);
		}
		return lblCarreras;
	}
	private JComboBox<Carrera> getCbCarreras() throws SQLException {
		if (cbCarreras == null) {
			cbCarreras = new JComboBox<Carrera>();
			GestorApp ga = new GestorApp();
			for (Carrera carrera : ga.getCarreras()){
				cbCarreras.addItem(carrera);
			}
			cbCarreras.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbCarreras.setBounds(43, 85, 292, 20);
		}
		return cbCarreras;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setEnabled(true);
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Carrera carrera = (Carrera) cbCarreras.getSelectedItem();
					try {
						mostrarVentanaInscritos(carrera.getId());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnAceptar.setBounds(291, 248, 89, 23);
			
		}
		return btnAceptar;
	}
	
	private void mostrarVentanaInscritos(Integer idCarrera) throws SQLException {
		VentanaInscritos vInscritos = new VentanaInscritos(this,idCarrera);
		vInscritos.setLocationRelativeTo(this);
		vInscritos.setModal(true);
		vInscritos.setVisible(true);
	}
	
}
