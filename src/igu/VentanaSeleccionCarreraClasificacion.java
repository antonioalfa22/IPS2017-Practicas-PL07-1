package igu;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Carrera;
import logic.GestorApp;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;

/**
 * Ventana con un ComboBox que permite elegir una carrera para ver las clasificaciones
 * femeninas y masculinas
 * 
 * @author Sara Grimaldos
 *
 */
public class VentanaSeleccionCarreraClasificacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCarreras;
	private JComboBox<Carrera> cbCarreras;
	private JButton btnAceptar;
	private JRadioButton rdbtnClasificacionFemenina;
	private JRadioButton rdbtnClasificacionMasculina;
	
	private final ButtonGroup bgCategoria = new ButtonGroup();

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VentanaSeleccionCarreraClasificacion() throws SQLException {
		setBounds(100, 100, 432, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblCarreras());
		contentPane.add(getCbCarreras());
		contentPane.add(getBtnAceptar());
		contentPane.add(getRdbtnClasificacionFemenina());
		contentPane.add(getRdbtnClasificacionMasculina());
	}

	private JLabel getLblCarreras() {
		if (lblCarreras == null) {
			lblCarreras = new JLabel("Clasificaci\u00F3n de carreras:");
			lblCarreras.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCarreras.setBounds(43, 39, 178, 28);
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
						if (rdbtnClasificacionFemenina.isSelected()){
							mostrarVentanaInscritos(carrera.getId(),"Femenino");
						}else{
							mostrarVentanaInscritos(carrera.getId(),"Masculino");
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnAceptar.setBounds(291, 248, 89, 23);
			
		}
		return btnAceptar;
	}
	
	private void mostrarVentanaInscritos(Integer idCarrera, String genero) throws SQLException {
		VentanaClasificaciones vInscritos = new VentanaClasificaciones(this,idCarrera, genero);
		vInscritos.setLocationRelativeTo(this);
		vInscritos.setModal(true);
		vInscritos.setVisible(true);
	}
	private JRadioButton getRdbtnClasificacionFemenina() {
		if (rdbtnClasificacionFemenina == null) {
			rdbtnClasificacionFemenina = new JRadioButton("Clasificaci\u00F3n femenina");
			rdbtnClasificacionFemenina.setSelected(true);
			bgCategoria.add(rdbtnClasificacionFemenina);
			rdbtnClasificacionFemenina.setFont(new Font("Tahoma", Font.PLAIN, 11));
			rdbtnClasificacionFemenina.setBounds(43, 155, 155, 29);
		}
		return rdbtnClasificacionFemenina;
	}
	private JRadioButton getRdbtnClasificacionMasculina() {
		if (rdbtnClasificacionMasculina == null) {
			rdbtnClasificacionMasculina = new JRadioButton("Clasificaci\u00F3n masculina");
			bgCategoria.add(rdbtnClasificacionMasculina);
			rdbtnClasificacionMasculina.setFont(new Font("Tahoma", Font.PLAIN, 11));
			rdbtnClasificacionMasculina.setBounds(43, 192, 155, 29);
		}
		return rdbtnClasificacionMasculina;
	}
}
