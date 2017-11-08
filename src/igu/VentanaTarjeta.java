package igu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entities.Carrera;
import entities.Usuario;
import gestorBBDD.GestorDB;

public class VentanaTarjeta extends JDialog {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Carrera carrera;
	private VentanaPago vp;

	private JPanel contentPane;
	private JLabel labelNumeroTarjeta;
	private JLabel labelFechaVencimiento;
	private JTextField textFieldNumeroTarjeta;
	private JTextField textFieldFechaVencimiento;
	private JButton btnAceptar;

	private boolean inscrito = false;

	public boolean isInscrito() {
		return inscrito;
	}

	/**
	 * Create the frame.
	 */
	public VentanaTarjeta(Usuario usuario, Carrera carrera, VentanaPago vp) {
		this.usuario = usuario;
		this.carrera = carrera;
		this.vp = vp;
		setLocationRelativeTo(vp);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 385, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLabelNumeroTarjeta());
		contentPane.add(getLabelFechaVencimiento());
		contentPane.add(getTextFieldNumeroTarjeta());
		contentPane.add(getTextFieldFechaVencimiento());
		contentPane.add(getBtnAceptar());
	}

	private JLabel getLabelNumeroTarjeta() {
		if (labelNumeroTarjeta == null) {
			labelNumeroTarjeta = new JLabel("N\u00FAmero de Tarjeta:");
			labelNumeroTarjeta.setBounds(10, 16, 160, 30);
		}
		return labelNumeroTarjeta;
	}

	private JLabel getLabelFechaVencimiento() {
		if (labelFechaVencimiento == null) {
			labelFechaVencimiento = new JLabel("Fecha de vencimiento:");
			labelFechaVencimiento.setBounds(10, 57, 160, 30);
		}
		return labelFechaVencimiento;
	}

	private JTextField getTextFieldNumeroTarjeta() {
		if (textFieldNumeroTarjeta == null) {
			textFieldNumeroTarjeta = new JTextField();
			textFieldNumeroTarjeta.setBounds(180, 16, 179, 30);
			textFieldNumeroTarjeta.setColumns(10);
		}
		return textFieldNumeroTarjeta;
	}

	private JTextField getTextFieldFechaVencimiento() {
		if (textFieldFechaVencimiento == null) {
			textFieldFechaVencimiento = new JTextField();
			textFieldFechaVencimiento.setBounds(180, 57, 179, 30);
			textFieldFechaVencimiento.setColumns(10);
		}
		return textFieldFechaVencimiento;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (validarCamposVacios())
						mostrarInfoPagoPorTarjeta();
					else
						mostarMensajeCamposVacios();
				}
			});
			btnAceptar.setBounds(270, 117, 89, 23);
		}
		return btnAceptar;
	}

	/**
	 * M�todo privado auxiliar que muestra un mensaje de error para indicar que
	 * faltan campos por completar
	 */
	private void mostarMensajeCamposVacios() {
		JOptionPane.showMessageDialog(this, "Por favor, rellene todos los campos", "Campos Vac�os",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * M�todo privado auxiliar que comprueba que los campos a rellenar no est�n
	 * vac�os
	 * 
	 * @return true si todos los campos est�n completos, false en caso contrario
	 */
	private boolean validarCamposVacios() {
		if (textFieldFechaVencimiento.getText().isEmpty() || textFieldNumeroTarjeta.getText().isEmpty())
			return false;
		return true;
	}

	/**
	 * M�todo que muestra un mensaje con la informaci�n de pago al usuario para que
	 * confirme el pago con tarjeta
	 */
	private void mostrarInfoPagoPorTarjeta() {
		int option = JOptionPane.showConfirmDialog(this,
				"Cantidad a pagar: " + carrera.getPrecio() + " �\n�Est� conforme con el pago?", "Informaci�n de pago",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			try {
				GestorDB.pagar(carrera.getId(), usuario.getDni());
				GestorDB.setNotasPago("Pagado", usuario.getDni(),carrera);
				JOptionPane.showMessageDialog(this, "�El pago por tarjeta se ha realizado con �xito!",
						"Pago completado", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				vp.dispose();
			} catch (SQLException ex) {
				GestorDB.handleSQLException(ex);
			}
		}
	}
}
