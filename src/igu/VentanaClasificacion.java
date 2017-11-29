package igu;

import gestorBBDD.LectorCSV;
import iguUtil.ModeloNoEditable;
import iguUtil.RowsRendererClasificaciones;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Categoria;
import logic.GestorApp;
import entities.Carrera;
import entities.Corredor;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaClasificacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCarreras;
	private JComboBox<Carrera> cbCarreras;
	private GestorApp g;
	private JLabel lblSexo;
	private JComboBox<String> cbSexo;
	private JLabel lblCategoria;
	private JComboBox<String> cbCategoria;
	private JButton btnBuscar;
	private JPanel pnClasificaciones;
	private JScrollPane scrollPaneClasificaciones;
	private JTable tableClasificaciones;
	private ModeloNoEditable modeloTabla;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public VentanaClasificacion() throws SQLException {
		g = new GestorApp();
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				VentanaClasificacion.class
						.getResource("/img/icons8-Running Filled-50.png")));
		setTitle("Clasificacion de carreras");
		setBounds(100, 100, 805, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblCarreras());
		contentPane.add(getCbCarreras());
		contentPane.add(getLblSexo());
		contentPane.add(getCbSexo());
		contentPane.add(getLblCategoria());
		contentPane.add(getCbCategoria());
		contentPane.add(getBtnBuscar());
		contentPane.add(getPnClasificaciones());
	}
	
	//==========================================================================================
	//										IGU: 
	//==========================================================================================

	private JLabel getLblCarreras() {
		if (lblCarreras == null) {
			lblCarreras = new JLabel("Carrera:");
			lblCarreras.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCarreras.setBounds(43, 13, 79, 28);
		}
		return lblCarreras;
	}

	private JComboBox<Carrera> getCbCarreras() throws SQLException {
		if (cbCarreras == null) {
			cbCarreras = new JComboBox<Carrera>();
			for (Carrera carrera : g.getCarrerasOrderByFecha()) {
				cbCarreras.addItem(carrera);
			}
			cbCarreras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Carrera carrera = (Carrera) cbCarreras.getSelectedItem();
					inicializarModeloCategorias(carrera);
					LectorCSV.actualizarTiempos(carrera.getNombre() + ".csv",
							carrera); // Actualizamos tiempos de corredores
					btnBuscar.setEnabled(true);
				}
			});
			cbCarreras.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbCarreras.setBounds(43, 54, 243, 20);
		}
		return cbCarreras;
	}
	private void inicializarModeloCategorias(Carrera carrera) {
		cbCategoria.removeAllItems();
		cbCategoria.addItem("Todas");
		cbCategoria.addItem("Absoluta");
		for (Categoria c : carrera.getCategorias()) {
			cbCategoria.addItem(c.getNombre().toString());
		}
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblSexo.setBounds(310, 13, 79, 28);
		}
		return lblSexo;
	}
	private JComboBox<String> getCbSexo() {
		if (cbSexo == null) {
			cbSexo = new JComboBox<String>();
			cbSexo.addItem("Todos");
			cbSexo.addItem("Femenino");
			cbSexo.addItem("Masculino");
			cbSexo.setBounds(310, 53, 102, 22);
		}
		return cbSexo;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Categor\u00EDa:");
			lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCategoria.setBounds(455, 13, 79, 28);
		}
		return lblCategoria;
	}
	private JComboBox<String> getCbCategoria() {
		if (cbCategoria == null) {
			cbCategoria = new JComboBox<String>();
			cbCategoria.setBounds(455, 53, 132, 22);
		}
		return cbCategoria;
	}
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.setEnabled(false);
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscar();	
				}
			});
			btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnBuscar.setBounds(638, 51, 97, 25);
		}
		return btnBuscar;
	}
	private void ClearTabla() {
		for (int i = 0; i < tableClasificaciones.getRowCount(); i++) {
			modeloTabla.removeRow(i);
			i -= 1;
		}
	}
	private JPanel getPnClasificaciones() {
		if (pnClasificaciones == null) {
			pnClasificaciones = new JPanel();
			pnClasificaciones.setBounds(43, 120, 692, 367);
			pnClasificaciones.setLayout(new BorderLayout(0, 0));
			pnClasificaciones.add(getScrollPaneClasificaciones(),
					BorderLayout.CENTER);
		}
		return pnClasificaciones;
	}
	private JScrollPane getScrollPaneClasificaciones() {
		if (scrollPaneClasificaciones == null) {
			scrollPaneClasificaciones = new JScrollPane();
			scrollPaneClasificaciones
					.setViewportView(getTableClasificaciones());
		}
		return scrollPaneClasificaciones;
	}
	private JTable getTableClasificaciones() {
		if (tableClasificaciones == null) {
			String[] nombreColumnas = { "Categoría", "Posición", "Dorsal",
					"Nombre", "Club", "Tiempo" };
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			tableClasificaciones = new JTable(modeloTabla);
			RowsRendererClasificaciones rr = new RowsRendererClasificaciones(1);
			tableClasificaciones.setDefaultRenderer(Object.class, rr);
		}
		return tableClasificaciones;
	}
	
	//==========================================================================================
	//										LOGICA: 
	//==========================================================================================

	/**
	 * Se ejecuta cuando se pulsa en buscar
	 * Muestra los corredores de la carrera y categoria seleccionadas
	 */
	private void buscar() {
		Carrera carrera = (Carrera) cbCarreras.getSelectedItem();
		String genero = (String) cbSexo.getSelectedItem();
		String categoria = (String) cbCategoria.getSelectedItem();
		ClearTabla();
		
		if (carrera.isFinalizada() && !(g.getCorredores(carrera.getId()).isEmpty())){
			if (categoria.equals("Todas")) {
				añadirFilasCategoriasTodas(carrera.getId(), genero,
						categoria);
			} else {
				añadirFilas(carrera.getId(), genero, categoria);
			}
			if (modeloTabla.getRowCount()==0){
				JOptionPane.showMessageDialog(null, "No hay corredores de la carrera\n"
						+ carrera.getNombre()+" de la categoría "+ categoria);
			}
		}else{
			JOptionPane.showMessageDialog(null, "No hay corredores de la carrera o no se ha disputado aun\n"
					+ carrera.getNombre());
		}
	}
	
	/**
	 * Añade filas
	 * @param idCarrera
	 * @param genero
	 * @param categoria
	 */
	private void añadirFilasCategoriasTodas(Integer idCarrera, String genero,
			String categoria) {
		Object[] nuevaFila = new Object[6];
		List<Corredor> corredores = escogerListaCorredores(idCarrera, genero,
				categoria);
		String currentCategoria;
		int pos = 1;
		currentCategoria = corredores.get(0).getCategoria();
		for (int i = 0; i < corredores.size(); i++) {
			if (!(currentCategoria.equals(corredores.get(i).getCategoria()))) {
				pos = 1;
				currentCategoria = corredores.get(i).getCategoria();
				nuevaFila[0] = "";
				nuevaFila[1] = "";
				nuevaFila[2] = "";
				nuevaFila[3] = "";
				nuevaFila[4] = "";
				nuevaFila[5] = "";
				modeloTabla.addRow(nuevaFila);
			}
			
			if (!(corredores.get(i).getTiempo().equals("-1"))){
				nuevaFila[0] = corredores.get(i).getCategoria();
				nuevaFila[1] = pos++;
				nuevaFila[2] = corredores.get(i).getDorsal();
				nuevaFila[3] = corredores.get(i).getNombre();
				nuevaFila[4] = corredores.get(i).getClub();
				nuevaFila[5] = corredores.get(i).getTiempo();
				
				if (nuevaFila[5].equals("DNF")){
					nuevaFila[1] = "DNF";
					nuevaFila[5]= "---";
				}else if(nuevaFila[5].equals("DNS")){
					nuevaFila[1] = "DNS";
					nuevaFila[5]= "---";
				}
				
				modeloTabla.addRow(nuevaFila);
			}
		}
	}

	/**
	 * Añade filas
	 * @param idCarrera
	 * @param genero
	 * @param categoria
	 */
	private void añadirFilas(Integer idCarrera, String genero, String categoria) {
		Object[] nuevaFila = new Object[6];
		List<Corredor> corredores = escogerListaCorredores(idCarrera, genero,
				categoria);

		int pos = 1;
		for (int i = 0; i < corredores.size(); i++) {
			if (!(corredores.get(i).getTiempo().equals("DNF"))
					&& !(corredores.get(i).getTiempo().equals("DNS")) && !(corredores.get(i).getTiempo().equals("-1"))) {
					nuevaFila[0] = corredores.get(i).getCategoria();
					nuevaFila[1] = pos++;
					nuevaFila[2] = corredores.get(i).getDorsal();
					nuevaFila[3] = corredores.get(i).getNombre();
					nuevaFila[4] = corredores.get(i).getClub();
					nuevaFila[5] = corredores.get(i).getTiempo();
					modeloTabla.addRow(nuevaFila);
			}
		}
		for (int i = 0; i < corredores.size(); i++) {
			if (corredores.get(i).getTiempo().equals("DNF")) {
				nuevaFila[0] = corredores.get(i).getCategoria();
				nuevaFila[1] = "DNF";
				nuevaFila[2] = corredores.get(i).getDorsal();
				nuevaFila[3] = corredores.get(i).getNombre();
				nuevaFila[4] = corredores.get(i).getClub();
				nuevaFila[5] = "---";
				modeloTabla.addRow(nuevaFila);
			} else if (corredores.get(i).getTiempo().equals("DNS")) {
				nuevaFila[0] = corredores.get(i).getCategoria();
				nuevaFila[1] = "DNS";
				nuevaFila[2] = corredores.get(i).getDorsal();
				nuevaFila[3] = corredores.get(i).getNombre();
				nuevaFila[4] = corredores.get(i).getClub();
				nuevaFila[5] = "---";
				modeloTabla.addRow(nuevaFila);
			}
		}
	}

	/**
	 * Escoge la lista de corredores
	 * @param idCarrera
	 * @param genero
	 * @param categoria
	 * @return
	 */
	private List<Corredor> escogerListaCorredores(Integer idCarrera,
			String genero, String categoria) {
		if (genero.equals("Todos") && categoria.equals("Absoluta")) {
			return g.getCorredores(idCarrera);
		} else if ((genero.equals("Femenino") || genero.equals("Masculino"))
				&& categoria.equals("Absoluta")) {
			return g.getCorredoresByGenero(idCarrera, genero);
		} else if (genero.equals("Todos") && categoria.equals("Todas")) {
			return g.getCorredoresTodasCategorias(idCarrera);
		} else if ((genero.equals("Femenino") || genero.equals("Masculino"))
				&& categoria.equals("Todas")) {
			return g.getCorredoresTodasCategoriasByGenero(idCarrera, genero);
		} else if (genero.equals("Todos") && categoria.equals(categoria)) {
			return g.getCorredoresTodasCategoriasGeneroT(idCarrera, categoria);
		} else {
			return g.getCorredoresByGeneroByCategoria(idCarrera, genero,
					categoria);
		}
	}

}
