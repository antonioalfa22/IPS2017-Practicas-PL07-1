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

import entities.Corredor;
import gestorBBDD.GestorDB;

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
	
	private VentanaSeleccionCarreraClasificacion ventanaPrincipal;


	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public VentanaClasificaciones(VentanaSeleccionCarreraClasificacion ventanaPrincipal, Integer idCarrera, String genero) throws SQLException {
		setBounds(100, 100, 731, 441);
		this.setVentanaPrincipal(ventanaPrincipal);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblClasificacion = new JLabel("Clasificaci\u00F3n absoluta:");
			lblClasificacion.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblClasificacion.setBounds(26, 11, 186, 33);
			contentPanel.add(lblClasificacion);
		}
		contentPanel.add(getScrollPnInscritos(idCarrera, genero));
	}
	private JScrollPane getScrollPnInscritos(Integer idCarrera, String genero) throws SQLException {
		if (scrollPnInscritos == null) {
			scrollPnInscritos = new JScrollPane();
			scrollPnInscritos.setBounds(26, 55, 637, 306);
			scrollPnInscritos.setViewportView(getTablaInscritos(idCarrera, genero));
		}
		return scrollPnInscritos;
	}
	private JTable getTablaInscritos(Integer idCarrera, String genero) throws SQLException {
		if (tablaInscritos == null) {
			String[] nombreColumnas = {"Posición", "Nombre", "Categoría", "Tiempo(min)"};
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			tablaInscritos = new JTable(modeloTabla);
			tablaInscritos.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(20);
			tablaInscritos.getTableHeader().setReorderingAllowed(false);  

			añadirFilas(idCarrera, genero);
		}
		return tablaInscritos;
	}

	
	private void añadirFilas(Integer idCarrera, String genero) throws SQLException{
		Object[] nuevaFila = new Object[5];
		List<Corredor> inscritos = GestorDB.findCorredoresByIdCarreraOrderByTiempoByGenero(idCarrera, genero);
		int pos = 1;
		for (int i=0; i<inscritos.size();i++){
			if (inscritos.get(i).getTiempo()!=-1){
				nuevaFila[0] = pos++;
				nuevaFila[1] = inscritos.get(i).getNombre();
				nuevaFila[2] = inscritos.get(i).getCategoria();
				nuevaFila[3] = inscritos.get(i).getTiempo();
				modeloTabla.addRow(nuevaFila);
				
			}
		}
		for (int i=0; i<inscritos.size();i++){
			if (inscritos.get(i).getTiempo()==-1){
				nuevaFila[0] = "NF";
				nuevaFila[1] = inscritos.get(i).getNombre();
				nuevaFila[2] = inscritos.get(i).getCategoria();
				nuevaFila[3] = "---";
				modeloTabla.addRow(nuevaFila);

			}
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
	
}
