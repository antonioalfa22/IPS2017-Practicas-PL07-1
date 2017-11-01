package igu;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.GestorApp;
import entities.Corredor;
import entities.Preinscrito;

/**
 * 
 * Ventana que muestra una tabla con los inscritos en una carrera y el estado de dicha inscripcion
 * 
 * @author Sara Grimaldos
 *
 */
public class VentanaInscritos extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPnInscritos;
	private JTable tablaInscritos;
	private DefaultTableModel modeloTabla;
	
	private VentanaSeleccionCarreraInscritos ventanaPrincipal;
	private GestorApp g;


	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public VentanaInscritos(VentanaSeleccionCarreraInscritos ventanaPrincipal, Integer idCarrera) throws SQLException {
		g = new GestorApp();
		setBounds(100, 100, 731, 441);
		this.setVentanaPrincipal(ventanaPrincipal);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblInscritos = new JLabel("Inscritos:");
			lblInscritos.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblInscritos.setBounds(26, 11, 81, 33);
			contentPanel.add(lblInscritos);
		}
		contentPanel.add(getScrollPnInscritos(idCarrera));
	}
	private JScrollPane getScrollPnInscritos(Integer idCarrera) throws SQLException {
		if (scrollPnInscritos == null) {
			scrollPnInscritos = new JScrollPane();
			scrollPnInscritos.setBounds(26, 55, 637, 306);
			scrollPnInscritos.setViewportView(getTablaInscritos(idCarrera));
		}
		return scrollPnInscritos;
	}
	private JTable getTablaInscritos(Integer idCarrera) throws SQLException {
		if (tablaInscritos == null) {
			String[] nombreColumnas = {"DNI", "Nombre", "Categoria", "Fecha Inscripci\u00F3n", "Pagado"};
			modeloTabla = new DefaultTableModel(nombreColumnas, 0);
			tablaInscritos = new JTable(modeloTabla);
			tablaInscritos.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(20); //modifica ancho de columna 0
			tablaInscritos.getTableHeader().setReorderingAllowed(false);  

			a�adirFilas(idCarrera);
		}
		return tablaInscritos;
	}

	
	private void a�adirFilas(Integer idCarrera) throws SQLException{
		Object[] nuevaFila = new Object[5];
		List<Preinscrito> preinscritos = g.getPreinscritosOrderByFecha(idCarrera);
		List<Corredor> corredores = g.getCorredoresOrderByFecha(idCarrera);
		for (int i=0; i<preinscritos.size();i++){
			nuevaFila[0] = preinscritos.get(i).getDni();
			nuevaFila[1] = preinscritos.get(i).getNombre();
			nuevaFila[2] = preinscritos.get(i).getCategoria();
			nuevaFila[3] = preinscritos.get(i).getFechaInscripcion();
			nuevaFila[4] = "No";
			modeloTabla.addRow(nuevaFila);
		}
		for (int i=0; i<corredores.size();i++){
			nuevaFila[0] = corredores.get(i).getDni();
			nuevaFila[1] = corredores.get(i).getNombre();
			nuevaFila[2] = corredores.get(i).getCategoria();
			nuevaFila[3] = corredores.get(i).getFechaInscripcion();
			nuevaFila[4] = "Si";
			modeloTabla.addRow(nuevaFila);
		}
	}
	/**
	 * @return the ventanaPrincipal
	 */
	public VentanaSeleccionCarreraInscritos getVentanaPrincipal() {
		return ventanaPrincipal;
	}
	/**
	 * @param ventanaPrincipal the ventanaPrincipal to set
	 */
	public void setVentanaPrincipal(VentanaSeleccionCarreraInscritos ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
	
}
