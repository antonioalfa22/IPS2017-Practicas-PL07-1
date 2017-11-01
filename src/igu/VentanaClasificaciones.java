package igu;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import entities.Carrera;
import entities.Corredor;

import java.awt.Toolkit;

import logic.GestorApp;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * 
 * Ventana que muestra una tabla con las clasificaciones ordenada por puestos
 * 
 * @author Sara Grimaldos
 *
 */
public class VentanaClasificaciones extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPnInscritos;
	private JTable tablaInscritos;
	private ModeloNoEditable modeloTabla;
	private GestorApp g;
	
	private VentanaSeleccionCarreraClasificacion ventanaPrincipal;
	private JLabel lblNombreCarrera;
	private JLabel lblNombreCategoria;


	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public VentanaClasificaciones(VentanaSeleccionCarreraClasificacion ventanaPrincipal, Integer idCarrera, String genero, String categoria) throws SQLException {
		g = new GestorApp();
		setTitle("Clasificaciones");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaClasificaciones.class.getResource("/img/icons8-Running Filled-50.png")));
		setBounds(100, 100, 731, 441);
		this.setVentanaPrincipal(ventanaPrincipal);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getScrollPnInscritos(idCarrera, genero, categoria));
		contentPanel.add(getLblNombreCarrera(idCarrera));
		contentPanel.add(getLblNombreCategoria(categoria));
	}
	private JScrollPane getScrollPnInscritos(Integer idCarrera, String genero, String categoria) throws SQLException {
		if (scrollPnInscritos == null) {
			scrollPnInscritos = new JScrollPane();
			scrollPnInscritos.setBounds(26, 55, 637, 306);
			scrollPnInscritos.setViewportView(getTablaInscritos(idCarrera, genero, categoria));
		}
		return scrollPnInscritos;
	}
	private JTable getTablaInscritos(Integer idCarrera, String genero, String categoria) throws SQLException {
		if (tablaInscritos == null) {
			String[] nombreColumnas = {"Posición","Dorsal","Nombre","Club","Tiempo"};
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			tablaInscritos = new JTable(modeloTabla);
			tablaInscritos.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(20);
			tablaInscritos.getTableHeader().setReorderingAllowed(false);  

			añadirFilas(idCarrera, genero, categoria);
		}
		return tablaInscritos;
	}
	
	private void añadirFilas(Integer idCarrera, String genero, String categoria) throws SQLException {
		Object[] nuevaFila = new Object[5];
		List<Corredor> corredores = escogerListaCorredores(idCarrera, genero, categoria);
		int pos = 1;
		for (int i = 0; i < corredores.size(); i++) {
			if (!(corredores.get(i).getTiempo().equals("DNF")) && !(corredores.get(i).getTiempo().equals("DNS"))) {
				nuevaFila[0] = pos++;
				nuevaFila[1] = corredores.get(i).getDorsal();
				nuevaFila[2] = corredores.get(i).getNombre();
				nuevaFila[3] = corredores.get(i).getClub();
				nuevaFila[4] = corredores.get(i).getTiempo();
				modeloTabla.addRow(nuevaFila);
			}
		}
		for (int i = 0; i < corredores.size(); i++) {
			if (corredores.get(i).getTiempo().equals("DNF")) {
				nuevaFila[0] = "DNF";
				nuevaFila[1] = corredores.get(i).getDorsal();
				nuevaFila[2] = corredores.get(i).getNombre();
				nuevaFila[3] = corredores.get(i).getClub();
				nuevaFila[4] = "---";
				modeloTabla.addRow(nuevaFila);
			}else if (corredores.get(i).getTiempo().equals("DNS")){
				nuevaFila[0] = "DNS";
				nuevaFila[1] = corredores.get(i).getDorsal();
				nuevaFila[2] = corredores.get(i).getNombre();
				nuevaFila[3] = corredores.get(i).getClub();
				nuevaFila[4] = "---";
				modeloTabla.addRow(nuevaFila);
			}
		}

	}
	
	private List<Corredor> escogerListaCorredores(Integer idCarrera, String genero, String categoria){
		if (genero.equals("") && categoria.equals("Clasificación Absoluta")){
			return g.getCorredores(idCarrera);
		}else if ((genero.equals("Femenino")|| genero.equals("Masculino")) 
				&& categoria.equals("Clasificación Femenina") && categoria.equals("Clasificación Masculina")){
			return g.getCorredoresByGenero(idCarrera, genero);
		}else{
			return g.getCorredoresByGeneroByCategoria(idCarrera, genero, categoria);
		}
	}

	/**
	 * @return the ventanaPrincipal
	 */
	public VentanaSeleccionCarreraClasificacion getVentanaPrincipal() {
		return ventanaPrincipal;
	}
	/**
	 * @param ventanaPrincipal the ventanaPrincipal to set
	 */
	public void setVentanaPrincipal(VentanaSeleccionCarreraClasificacion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private JLabel getLblNombreCarrera(Integer idCarrera) {
		Carrera c = g.getCarreraById(idCarrera);
		if (lblNombreCarrera == null) {
			lblNombreCarrera = new JLabel(c.getNombre());
			lblNombreCarrera.setVerticalAlignment(SwingConstants.TOP);
			lblNombreCarrera.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNombreCarrera.setHorizontalAlignment(SwingConstants.LEFT);
			lblNombreCarrera.setBounds(28, 25, 177, 39);
		}
		return lblNombreCarrera;
	}

	private JLabel getLblNombreCategoria(String categoria) {
		if (lblNombreCategoria == null) {
			lblNombreCategoria = new JLabel(categoria);
			lblNombreCategoria.setVerticalAlignment(SwingConstants.TOP);
			lblNombreCategoria.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNombreCategoria.setBounds(202, 25, 280, 32);
		}
		return lblNombreCategoria;
	}
}
