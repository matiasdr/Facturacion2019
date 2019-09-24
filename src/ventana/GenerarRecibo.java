package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GenerarRecibo extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarRecibo frame = new GenerarRecibo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public GenerarRecibo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGeneracinDeRecibos = new JLabel("Generaci\u00F3n de Recibos");
		lblGeneracinDeRecibos.setBounds(224, 11, 142, 14);
		contentPane.add(lblGeneracinDeRecibos);

		JLabel lblTipoDeRecibo = new JLabel("Tipo de Recibo");
		lblTipoDeRecibo.setBounds(10, 30, 73, 14);
		contentPane.add(lblTipoDeRecibo);

		JRadioButton rdbtnPagoRecibido = new JRadioButton("Cobro a Clientes");
		rdbtnPagoRecibido.setBounds(112, 26, 111, 23);
		contentPane.add(rdbtnPagoRecibido);

		JRadioButton rdbtnPorPagoEfectuado = new JRadioButton("Por Pago a Proveedores");
		rdbtnPorPagoEfectuado.setBounds(383, 26, 148, 23);
		contentPane.add(rdbtnPorPagoEfectuado);

		JLabel lblSeleccionarCliente = new JLabel("Seleccionar Cliente");
		lblSeleccionarCliente.setBounds(10, 55, 99, 14);
		contentPane.add(lblSeleccionarCliente);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(103, 51, 89, 23);
		contentPane.add(btnBuscar);

		JLabel lblNombreCliente = new JLabel("Nombre cliente");
		lblNombreCliente.setBounds(202, 56, 99, 14);
		contentPane.add(lblNombreCliente);

		JLabel lblConcepto = new JLabel("Datos del Comprobante.");
		lblConcepto.setBounds(10, 78, 111, 14);
		contentPane.add(lblConcepto);

		textField = new JTextField();
		textField.setBounds(127, 75, 142, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblMedioDePago = new JLabel("Medio de Pago");
		lblMedioDePago.setBounds(10, 103, 99, 14);
		contentPane.add(lblMedioDePago);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(127, 99, 28, 22);
		contentPane.add(comboBox);

		JLabel lblesUnImporte = new JLabel("\u00BFEs un importe parcial?");
		lblesUnImporte.setBounds(10, 128, 145, 14);
		contentPane.add(lblesUnImporte);

		JRadioButton rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setBounds(127, 124, 48, 23);
		contentPane.add(rdbtnSi);

		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(173, 124, 73, 23);
		contentPane.add(rdbtnNo);

		JLabel lblImporte = new JLabel("Importe:");
		lblImporte.setBounds(10, 153, 49, 14);
		contentPane.add(lblImporte);

		textField_1 = new JTextField();
		textField_1.setBounds(127, 153, 142, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblSeleccionarFactura = new JLabel("Seleccionar Factura");
		lblSeleccionarFactura.setBounds(10, 184, 125, 14);
		contentPane.add(lblSeleccionarFactura);

		JList list = new JList();
		list.setBounds(127, 184, 142, 77);
		contentPane.add(list);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(20, 222, 89, 23);
		contentPane.add(btnSeleccionar);

		JLabel lblImporteTotalSeleccionado = new JLabel("Importe total Seleccionado");
		lblImporteTotalSeleccionado.setBounds(60, 273, 163, 14);
		contentPane.add(lblImporteTotalSeleccionado);

		JButton btnEmitirRecibo = new JButton("Emitir Recibo");
		btnEmitirRecibo.setBounds(66, 318, 142, 23);
		contentPane.add(btnEmitirRecibo);

		JLabel lblSeleccionarProveedor = new JLabel("Seleccionar Proveedor");
		lblSeleccionarProveedor.setBounds(283, 50, 99, 14);
		contentPane.add(lblSeleccionarProveedor);

		JButton button = new JButton("Buscar");
		button.setBounds(376, 46, 89, 23);
		contentPane.add(button);

		JLabel label_1 = new JLabel("Nombre cliente");
		label_1.setBounds(475, 51, 99, 14);
		contentPane.add(label_1);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(279, 55, 1, 312);
		contentPane.add(separator);

		JLabel label = new JLabel("Datos del Comprobante.");
		label.setBounds(293, 78, 111, 14);
		contentPane.add(label);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(410, 75, 142, 20);
		contentPane.add(textField_2);

		JLabel label_2 = new JLabel("Medio de Pago");
		label_2.setBounds(294, 103, 99, 14);
		contentPane.add(label_2);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(411, 103, 28, 22);
		contentPane.add(comboBox_1);

		JLabel label_3 = new JLabel("\u00BFEs un importe parcial?");
		label_3.setBounds(293, 132, 145, 14);
		contentPane.add(label_3);

		JRadioButton radioButton = new JRadioButton("Si");
		radioButton.setBounds(410, 128, 48, 23);
		contentPane.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("No");
		radioButton_1.setBounds(456, 128, 73, 23);
		contentPane.add(radioButton_1);

		JLabel label_4 = new JLabel("Importe:");
		label_4.setBounds(293, 157, 49, 14);
		contentPane.add(label_4);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(410, 157, 142, 20);
		contentPane.add(textField_3);

		JLabel label_5 = new JLabel("Seleccionar Factura");
		label_5.setBounds(290, 184, 125, 14);
		contentPane.add(label_5);

		JList list_1 = new JList();
		list_1.setBounds(410, 183, 142, 77);
		contentPane.add(list_1);

		JLabel label_6 = new JLabel("Importe total Seleccionado");
		label_6.setBounds(350, 273, 163, 14);
		contentPane.add(label_6);

		JButton button_1 = new JButton("Emitir Recibo");
		button_1.setBounds(350, 318, 142, 23);
		contentPane.add(button_1);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(239, 378, 89, 23);
		contentPane.add(btnCancelar);

	}
}