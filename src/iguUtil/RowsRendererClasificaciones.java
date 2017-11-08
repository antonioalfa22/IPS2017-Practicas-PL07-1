package iguUtil;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RowsRendererClasificaciones extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	private int columna;

	public RowsRendererClasificaciones(int Colpatron) {
		this.columna = Colpatron;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean selected, boolean focused, int row, int column) {
		setBackground(Color.white);
		table.setForeground(Color.black);
		super.getTableCellRendererComponent(table, value, selected, focused,
				row, column);
		if (table.getValueAt(row, columna).toString().equals("1")) {
			this.setBackground(Color.yellow);
		} else if (table.getValueAt(row, columna).toString().equals("2")) {
			this.setBackground(Color.LIGHT_GRAY);
		} else if (table.getValueAt(row, columna).toString().equals("3")) {
			this.setBackground(Color.ORANGE);
		}
		return this;
	}
}
