package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entities.Carrera;
import entities.Usuario;
import gestorBBDD.GestorDB;

public class VentanaPago extends JDialog {

	private static final long serialVersionUID = 1L;
	private Carrera carrera;
	private Usuario usuario;

	private JPanel contentPane;
	private JPanel pnNorte;
	private JPanel pnSur;
	private JButton btnAceptar;
	private JPanel pnCentro;
	private JLabel lblSeleccionFormaPago;
	private JRadioButton rdbtnTransferenciaBancaria;

	/**
	 * Create the frame.
	 */
	public VentanaPago(Carrera carrera, Usuario usuario) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPnSur(), BorderLayout.SOUTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
		setResizable(false);
		this.carrera = carrera;
		this.usuario = usuario;
	}

	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setBorder(new EmptyBorder(5, 5, 5, 5));
			pnNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnNorte.add(getLblSeleccionFormaPago());
		}
		return pnNorte;
	}

	private JPanel getPnSur() {
		if (pnSur == null) {
			pnSur = new JPanel();
			pnSur.setLayout(new FlowLayout(FlowLayout.RIGHT));
			pnSur.add(getBtnAceptar());
		}
		return pnSur;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarInfoPago();
				}
			});
			btnAceptar.setEnabled(false);
			btnAceptar.setActionCommand("OK");
		}
		return btnAceptar;
	}

	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new BorderLayout(0, 0));
			pnCentro.add(getRdbtnTransferenciaBancaria(), BorderLayout.CENTER);
		}
		return pnCentro;
	}

	private JLabel getLblSeleccionFormaPago() {
		if (lblSeleccionFormaPago == null) {
			lblSeleccionFormaPago = new JLabel("Selecciona una forma de pago:");
			lblSeleccionFormaPago.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSeleccionFormaPago;
	}

	private JRadioButton getRdbtnTransferenciaBancaria() {
		if (rdbtnTransferenciaBancaria == null) {
			rdbtnTransferenciaBancaria = new JRadioButton("Transferencia Bancaria");
			rdbtnTransferenciaBancaria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (btnAceptar.isEnabled())
						btnAceptar.setEnabled(false);
					else
						btnAceptar.setEnabled(true);
				}
			});
			rdbtnTransferenciaBancaria.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return rdbtnTransferenciaBancaria;
	}

	/**
	 * Método que muestra en un diálogo el número de cuenta donde hay que depositar
	 * la transferencia y el montante de la operación para que el usuario confirme
	 * el pago el pago
	 */
	private void mostrarInfoPago() {
		int option = JOptionPane.showConfirmDialog(this,
				"Número de cuenta: " + carrera.getNum_cuenta() + "\nCantidad a pagar: " + carrera.getPrecio()
						+" €\n¿Está conforme con el pago?",
				"Información de pago", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			try {
				GestorDB.pagoPorTransferencia(carrera.getId(), usuario.getDni());
				JOptionPane.showMessageDialog(this, "¡El pago por transferencia se ha realizado con éxito!",
						"Transferencia completada", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} catch (SQLException ex) {
				GestorDB.handleSQLException(ex);
			}
		}
	}
}