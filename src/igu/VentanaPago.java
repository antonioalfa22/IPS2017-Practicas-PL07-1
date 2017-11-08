package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.ButtonGroup;
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
	private JRadioButton rdbtnTarjetaDeCredito;
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
					if (rdbtnTarjetaDeCredito.isSelected()) {
						mostrarVentanaTarjeta();
					} else {
						mostrarInfoPagoPorTransferencia();
					}
				}
			});
			btnAceptar.setEnabled(true);
			btnAceptar.setActionCommand("OK");
		}
		return btnAceptar;
	}

	/**
	 * Método privado auxiliar que muestra la ventana para rellenar los datos de la
	 * tarjeta de crédito
	 */
	private void mostrarVentanaTarjeta() {
		VentanaTarjeta vt = new VentanaTarjeta(usuario, carrera, this);
		vt.setVisible(true);
	}

	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new GridLayout(0, 1, 0, 0));
			pnCentro.add(getRdbtnTransferenciaBancaria());
			pnCentro.add(getRdbtnTarjetaDeCredito());
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
			rdbtnTransferenciaBancaria.setSelected(true);
			buttonGroup.add(rdbtnTransferenciaBancaria);
			rdbtnTransferenciaBancaria.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return rdbtnTransferenciaBancaria;
	}

	/**
	 * Método que muestra un mensaje con la información de pago al usuario para que
	 * confirme el pago por transferencia
	 */
	private void mostrarInfoPagoPorTransferencia() {
		int option = JOptionPane.showConfirmDialog(this,
				"Número de cuenta: " + carrera.getNum_cuenta() + "\nCantidad a pagar: " + carrera.getPrecio()
						+ " €\n¿Está conforme con el pago?",
				"Información de pago", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			try {
				Calendar date = Calendar.getInstance();
				String fecha_actual = date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1) + "/"
						+ date.get(Calendar.YEAR);
				GestorDB.setFechaPago(usuario.getDni(), fecha_actual);
				GestorDB.setNotasPago("Pendiente de confirmación", usuario.getDni(),carrera);
				JOptionPane.showMessageDialog(this,
						"Pago por transferencia escogido.\nDispone de 48 horas para confirmar la transferencia",
						"Pago pendiente de confirmación", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} catch (SQLException ex) {
				GestorDB.handleSQLException(ex);
			}
		}
	}

	private JRadioButton getRdbtnTarjetaDeCredito() {
		if (rdbtnTarjetaDeCredito == null) {
			rdbtnTarjetaDeCredito = new JRadioButton("Tarjeta de Cr\u00E9dito");
			buttonGroup.add(rdbtnTarjetaDeCredito);
			rdbtnTarjetaDeCredito.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return rdbtnTarjetaDeCredito;
	}

}