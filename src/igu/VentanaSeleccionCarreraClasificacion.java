package igu;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Carrera;
import entities.Corredor;
import logic.GestorApp;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.Toolkit;

import javax.swing.border.TitledBorder;

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
	private JPanel pnClasificaciones;
	private JRadioButton rdbtnClasificacionGeneral;
	private JLabel lblGenero;
	private JLabel lblCategoria;
	private JRadioButton rdbtnSeniorM;
	private JRadioButton rdbtnVeteranoAM;
	private JRadioButton rdbtnVeteranoBM;
	private JRadioButton rdbtnVeteranoCM;
	private JRadioButton rdbtnVeteranoDM;
	private JRadioButton rdbtnVeteranoEM;
	private JRadioButton rdbtnVeteranoFM;
	private JRadioButton rdbtnAbsoluta;
	private JRadioButton rdbtnSeniorF;
	private JRadioButton rdbtnVeteranoAF;
	private JRadioButton rdbtnVeteranoBF;
	private JRadioButton rdbtnVeteranoCF;
	private JRadioButton rdbtnVeteranoDF;
	private JRadioButton rdbtnVeteranoEF;
	private JRadioButton rdbtnVeteranoFF;
	
	private GestorApp g;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VentanaSeleccionCarreraClasificacion() throws SQLException {
		g = new GestorApp();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaSeleccionCarreraClasificacion.class.getResource("/img/icons8-Running Filled-50.png")));
		setTitle("Clasificaci\u00F3n de carreras");
		setBounds(100, 100, 722, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblCarreras());
		contentPane.add(getCbCarreras());
		contentPane.add(getBtnAceptar());
		contentPane.add(getPnClasificaciones());
	}

	private JLabel getLblCarreras() {
		if (lblCarreras == null) {
			lblCarreras = new JLabel("Carreras:");
			lblCarreras.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCarreras.setBounds(43, 39, 178, 28);
		}
		return lblCarreras;
	}
	private JComboBox<Carrera> getCbCarreras() throws SQLException {
		if (cbCarreras == null) {
			cbCarreras = new JComboBox<Carrera>();
			for (Carrera carrera : g.getCarrerasOrderByFecha()){
				cbCarreras.addItem(carrera);
			}
			cbCarreras.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbCarreras.setBounds(43, 85, 337, 20);
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
						if (hayCorredoresCarrera(carrera.getId())){
							if (rdbtnClasificacionFemenina.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Femenino", "Clasificación Femenina");
							}else if (rdbtnClasificacionMasculina.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Masculino","Clasificación Masculina");
							} else if (rdbtnAbsoluta.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"","Clasificación Absoluta");
							} else if (rdbtnSeniorM.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Masculino","Senior - Masculino");
							}else if (rdbtnVeteranoAM.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Masculino","Veterano A - Masculino");
							}else if (rdbtnVeteranoBM.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Masculino","Veterano B - Masculino");
							}else if (rdbtnVeteranoCM.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Masculino","Veterano C - Masculino");
							}else if (rdbtnVeteranoDM.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Masculino","Veterano D - Masculino");
							}else if (rdbtnVeteranoEM.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Masculino","Veterano E - Masculino");
							}else if (rdbtnVeteranoFM.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Masculino","Veterano F - Masculino");
							}else if (rdbtnSeniorF.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Femenino","Senior - Femenino");
							}else if (rdbtnVeteranoAF.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Femenino","Veterano A - Femenino");
							}else if (rdbtnVeteranoBF.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Femenino","Veterano B - Femenino");
							}else if (rdbtnVeteranoCF.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Femenino","Veterano C - Femenino");
							}else if (rdbtnVeteranoDF.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Femenino","Veterano D - Femenino");
							}else if (rdbtnVeteranoEF.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Femenino","Veterano E - Femenino");
							}else if (rdbtnVeteranoFF.isSelected()){
								mostrarVentanaInscritos(carrera.getId(),"Femenino","Veterano F - Femenino");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Error: No hay corredores");
						}

						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnAceptar.setBounds(550, 518, 89, 23);
			
		}
		return btnAceptar;
	}
	
	private boolean hayCorredoresCarrera(Integer idCarrera){
		ArrayList<Corredor> list = g.getCorredores(idCarrera);
		if (list.isEmpty()){
			return false;
		}
		return true;
	}
	
	private void mostrarVentanaInscritos(Integer idCarrera, String genero, String categoria) throws SQLException {  
		VentanaClasificaciones vInscritos = new VentanaClasificaciones(this,idCarrera, genero, categoria);
		vInscritos.setLocationRelativeTo(this);
		vInscritos.setModal(true);
		vInscritos.setVisible(true);
	}
	private JRadioButton getRdbtnClasificacionFemenina() {
		if (rdbtnClasificacionFemenina == null) {
			rdbtnClasificacionFemenina = new JRadioButton("Clasificaci\u00F3n femenina");
			rdbtnClasificacionFemenina.setBounds(97, 93, 168, 23);
			bgCategoria.add(rdbtnClasificacionFemenina);
			rdbtnClasificacionFemenina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		}
		return rdbtnClasificacionFemenina;
	}
	private JRadioButton getRdbtnClasificacionMasculina() {
		if (rdbtnClasificacionMasculina == null) {
			rdbtnClasificacionMasculina = new JRadioButton("Clasificaci\u00F3n masculina");
			rdbtnClasificacionMasculina.setBounds(95, 67, 170, 23);
			bgCategoria.add(rdbtnClasificacionMasculina);
			rdbtnClasificacionMasculina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		}
		return rdbtnClasificacionMasculina;
	}
	private JPanel getPnClasificaciones() {
		if (pnClasificaciones == null) {
			pnClasificaciones = new JPanel();
			pnClasificaciones.setBorder(new TitledBorder(null, "Clasificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnClasificaciones.setBounds(43, 144, 596, 363);
			pnClasificaciones.setLayout(null);
			pnClasificaciones.add(getRdbtnClasificacionFemenina());
			pnClasificaciones.add(getRdbtnClasificacionMasculina());
			pnClasificaciones.add(getRdbtnClasificacionGeneral());
			pnClasificaciones.add(getLblGenero());
			pnClasificaciones.add(getLblCategoria());
			pnClasificaciones.add(getRdbtnSeniorM());
			pnClasificaciones.add(getRdbtnVeteranoAM());
			pnClasificaciones.add(getRdbtnVeteranoBM());
			pnClasificaciones.add(getRdbtnVeteranoCM());
			pnClasificaciones.add(getRdbtnVeteranoDM());
			pnClasificaciones.add(getRdbtnVeteranoEM());
			pnClasificaciones.add(getRdbtnVeteranoFM());
			pnClasificaciones.add(getRdbtnAbsoluta());
			pnClasificaciones.add(getRdbtnSeniorF());
			pnClasificaciones.add(getRdbtnVeteranoAF());
			pnClasificaciones.add(getRdbtnVeteranoBF());
			pnClasificaciones.add(getRdbtnVeteranoCF());
			pnClasificaciones.add(getRdbtnVeteranoDF());
			pnClasificaciones.add(getRdbtnVeteranoEF());
			pnClasificaciones.add(getRdbtnVeteranoFF());
		}
		return pnClasificaciones;
	}
	private JRadioButton getRdbtnClasificacionGeneral() {
		if (rdbtnClasificacionGeneral == null) {
			rdbtnClasificacionGeneral = new JRadioButton("Clasificaci\u00F3n general");
			rdbtnClasificacionGeneral.setFont(new Font("Tahoma", Font.PLAIN, 11));
			rdbtnClasificacionGeneral.setSelected(true);
			bgCategoria.add(rdbtnClasificacionGeneral);
			rdbtnClasificacionGeneral.setBounds(31, 32, 131, 32);
		}
		return rdbtnClasificacionGeneral;
	}
	private JLabel getLblGenero() {
		if (lblGenero == null) {
			lblGenero = new JLabel("G\u00E9nero:");
			lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblGenero.setBounds(31, 70, 46, 14);
		}
		return lblGenero;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Categor\u00EDas:");
			lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblCategoria.setBounds(31, 129, 70, 23);
		}
		return lblCategoria;
	}
	private JRadioButton getRdbtnSeniorM() {
		if (rdbtnSeniorM == null) {
			rdbtnSeniorM = new JRadioButton("Clasificaci\u00F3n S\u00E9nior Masculina");
			rdbtnSeniorM.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnSeniorM);
			rdbtnSeniorM.setBounds(97, 130, 179, 23);
		}
		return rdbtnSeniorM;
	}
	private JRadioButton getRdbtnVeteranoAM() {
		if (rdbtnVeteranoAM == null) {
			rdbtnVeteranoAM = new JRadioButton("Clasificaci\u00F3n Veterano A Masculina");
			rdbtnVeteranoAM.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoAM);
			rdbtnVeteranoAM.setBounds(97, 159, 197, 23);
		}
		return rdbtnVeteranoAM;
	}
	private JRadioButton getRdbtnVeteranoBM() {
		if (rdbtnVeteranoBM == null) {
			rdbtnVeteranoBM = new JRadioButton("Clasificaci\u00F3n Veterano B Masculina");
			rdbtnVeteranoBM.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoBM);
			rdbtnVeteranoBM.setBounds(97, 185, 222, 23);
		}
		return rdbtnVeteranoBM;
	}
	private JRadioButton getRdbtnVeteranoCM() {
		if (rdbtnVeteranoCM == null) {
			rdbtnVeteranoCM = new JRadioButton("Clasificaci\u00F3n Veterano C Masculina");
			rdbtnVeteranoCM.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoCM);
			rdbtnVeteranoCM.setBounds(97, 211, 197, 23);
		}
		return rdbtnVeteranoCM;
	}
	private JRadioButton getRdbtnVeteranoDM() {
		if (rdbtnVeteranoDM == null) {
			rdbtnVeteranoDM = new JRadioButton("Clasificaci\u00F3n Veterano D Masculina");
			rdbtnVeteranoDM.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoDM);
			rdbtnVeteranoDM.setBounds(97, 237, 197, 23);
		}
		return rdbtnVeteranoDM;
	}
	private JRadioButton getRdbtnVeteranoEM() {
		if (rdbtnVeteranoEM == null) {
			rdbtnVeteranoEM = new JRadioButton("Clasificaci\u00F3n Veterano E Masculina");
			rdbtnVeteranoEM.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoEM);
			rdbtnVeteranoEM.setBounds(97, 263, 197, 23);
		}
		return rdbtnVeteranoEM;
	}
	private JRadioButton getRdbtnVeteranoFM() {
		if (rdbtnVeteranoFM == null) {
			rdbtnVeteranoFM = new JRadioButton("Clasificaci\u00F3n Veterano F Masculina");
			rdbtnVeteranoFM.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoFM);
			rdbtnVeteranoFM.setBounds(97, 289, 197, 23);
		}
		return rdbtnVeteranoFM;
	}
	private JRadioButton getRdbtnAbsoluta() {
		if (rdbtnAbsoluta == null) {
			rdbtnAbsoluta = new JRadioButton("Clasificaci\u00F3n Absoluta");
			rdbtnAbsoluta.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnAbsoluta);
			rdbtnAbsoluta.setBounds(31, 315, 147, 23);
		}
		return rdbtnAbsoluta;
	}
	private JRadioButton getRdbtnSeniorF() {
		if (rdbtnSeniorF == null) {
			rdbtnSeniorF = new JRadioButton("Clasificaci\u00F3n S\u00E9nior Femenina");
			rdbtnSeniorF.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnSeniorF);
			rdbtnSeniorF.setBounds(326, 130, 222, 23);
		}
		return rdbtnSeniorF;
	}
	private JRadioButton getRdbtnVeteranoAF() {
		if (rdbtnVeteranoAF == null) {
			rdbtnVeteranoAF = new JRadioButton("Clasificaci\u00F3n Veterano A Femenina");
			rdbtnVeteranoAF.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoAF);
			rdbtnVeteranoAF.setBounds(326, 159, 222, 23);
		}
		return rdbtnVeteranoAF;
	}
	private JRadioButton getRdbtnVeteranoBF() {
		if (rdbtnVeteranoBF == null) {
			rdbtnVeteranoBF = new JRadioButton("Clasificaci\u00F3n Veterano B Femenina");
			rdbtnVeteranoBF.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoBF);
			rdbtnVeteranoBF.setBounds(326, 185, 237, 23);
		}
		return rdbtnVeteranoBF;
	}
	private JRadioButton getRdbtnVeteranoCF() {
		if (rdbtnVeteranoCF == null) {
			rdbtnVeteranoCF = new JRadioButton("Clasificaci\u00F3n Veterano C Femenina");
			rdbtnVeteranoCF.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoCF);
			rdbtnVeteranoCF.setBounds(326, 211, 237, 23);
		}
		return rdbtnVeteranoCF;
	}
	private JRadioButton getRdbtnVeteranoDF() {
		if (rdbtnVeteranoDF == null) {
			rdbtnVeteranoDF = new JRadioButton("Clasificaci\u00F3n Veterano D Femenina");
			rdbtnVeteranoDF.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoDF);
			rdbtnVeteranoDF.setBounds(326, 237, 245, 23);
		}
		return rdbtnVeteranoDF;
	}
	private JRadioButton getRdbtnVeteranoEF() {
		if (rdbtnVeteranoEF == null) {
			rdbtnVeteranoEF = new JRadioButton("Clasificaci\u00F3n Veterano E Femenina");
			rdbtnVeteranoEF.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoEF);
			rdbtnVeteranoEF.setBounds(326, 263, 229, 23);
		}
		return rdbtnVeteranoEF;
	}
	private JRadioButton getRdbtnVeteranoFF() {
		if (rdbtnVeteranoFF == null) {
			rdbtnVeteranoFF = new JRadioButton("Clasificaci\u00F3n Veterano F Femenina");
			rdbtnVeteranoFF.setFont(new Font("Tahoma", Font.PLAIN, 11));
			bgCategoria.add(rdbtnVeteranoFF);
			rdbtnVeteranoFF.setBounds(326, 289, 237, 23);
		}
		return rdbtnVeteranoFF;
	}
}
