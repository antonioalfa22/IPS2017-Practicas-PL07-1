package igu;


import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;








import logic.GestorApp;
import logic.Inscrito;
import entities.Carrera;
import iguUtil.ModeloNoEditable;
import iguUtil.RowsRendererInscripciones;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import java.awt.Color;

public class VentanaInscritos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCarreras;
	private JComboBox<Carrera> cbCarreras;
	private GestorApp g;
	private JPanel pnClasificaciones;
	private JScrollPane scrollPaneClasificaciones;
	private JTable tableClasificaciones;

	private ModeloNoEditable modeloTabla;
	private JLabel lblOrdenar;
	private JPanel pnOrdenar;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public VentanaInscritos() throws SQLException {
		g = new GestorApp();

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				VentanaInscritos.class
						.getResource("/img/icons8-Running Filled-50.png")));
		setTitle("Inscritos de carreras");
		setBounds(100, 100, 805, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblCarreras());
		contentPane.add(getCbCarreras());
		contentPane.add(getPnClasificaciones());
		contentPane.add(getPnOrdenar());
	}
	
	//==========================================================================================
	//										COMPONENTES IGU: 
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
					ClearTabla();
					Carrera carrera = (Carrera) cbCarreras.getSelectedItem();
					if ( !(g.getInscritosByIdCarrera(carrera.getId()).isEmpty())){
						try {
							añadirFilas(carrera.getId());
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, "No hay inscritos para la carrera\n"+ carrera.getNombre());
					}
				}
			});
			cbCarreras.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbCarreras.setBounds(43, 54, 243, 20);
		}
		return cbCarreras;
	}
	private JPanel getPnClasificaciones() {
		if (pnClasificaciones == null) {
			pnClasificaciones = new JPanel();
			pnClasificaciones.setBounds(43, 159, 692, 328);
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
			String[] nombreColumnas = { "DNI", "Nombre", "Categoría",
					"Fecha de inscripción", "Pagado"};
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			tableClasificaciones = new JTable(modeloTabla);
			tableClasificaciones.setAutoCreateRowSorter(true);
			RowsRendererInscripciones rr = new RowsRendererInscripciones(4);
			tableClasificaciones.setDefaultRenderer(Object.class, rr);
		}
		return tableClasificaciones;
	}
	private JLabel getLblOrdenar() {
		if (lblOrdenar == null) {
			lblOrdenar = new JLabel("Haz click sobre una columna para ordenar los inscritos por el criterio que desees");
			lblOrdenar.setBounds(15, 16, 561, 21);
			lblOrdenar.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblOrdenar;
	}
	private JPanel getPnOrdenar() {
		if (pnOrdenar == null) {
			pnOrdenar = new JPanel();
			pnOrdenar.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnOrdenar.setBounds(43, 90, 591, 53);
			pnOrdenar.setLayout(null);
			pnOrdenar.add(getLblOrdenar());
		}
		return pnOrdenar;
	}
	
	//==========================================================================================
	//										LOGICA: 
	//==========================================================================================
	
	/**
	 * Crea la tabla de clasificaciones
	 */
	private void ClearTabla() {
		for (int i = 0; i < tableClasificaciones.getRowCount(); i++) {
			modeloTabla.removeRow(i);
			i -= 1;
		}
	}
	
	/**
	 * Añade las filas a la tabla
	 * @param idCarrera
	 * @throws ParseException
	 */
	private void añadirFilas(Integer idCarrera) throws ParseException {
		Object[] nuevaFila = new Object[5];
		List<Inscrito> inscritos = g.getInscritosByIdCarrera(idCarrera);

		for (int i = 0; i < inscritos.size(); i++) {
			String date = formatearFecha(inscritos.get(i).getFechaInscripcion());
			
			nuevaFila[0] = inscritos.get(i).getDNI();
			nuevaFila[1] = inscritos.get(i).getNombre();
			nuevaFila[2] = inscritos.get(i).getCategoria();
			nuevaFila[3] = date;
			nuevaFila[4] = inscritos.get(i).getPagado();
			modeloTabla.addRow(nuevaFila);
		}
	}
	
	/**
	 * Formatea la fecha
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	private String formatearFecha(String fecha) throws ParseException{
		SimpleDateFormat parseador = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
		Date date = parseador.parse(fecha);
		return formateador.format(date);
	}
	
}
