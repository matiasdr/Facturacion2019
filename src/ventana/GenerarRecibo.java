package ventana;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GenerarRecibo extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField importeParcial;
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
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGeneracinDeRecibos = new JLabel("Generaci\u00F3n de Recibos");
		lblGeneracinDeRecibos.setBounds(329, 11, 142, 14);
		contentPane.add(lblGeneracinDeRecibos);

		JLabel lblTipoDeRecibo = new JLabel("Tipo de Recibo");
		lblTipoDeRecibo.setBounds(10, 30, 73, 14);
		contentPane.add(lblTipoDeRecibo);

		JRadioButton rdbtnPagoRecibido = new JRadioButton("Cobro a Clientes");
		rdbtnPagoRecibido.setBounds(112, 26, 111, 23);
		contentPane.add(rdbtnPagoRecibido);

		JRadioButton rdbtnPorPagoEfectuado = new JRadioButton("Por Pago a Proveedores");
		rdbtnPorPagoEfectuado.setBounds(408, 26, 209, 23);
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

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(127, 99, 28, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Efectivo");
		comboBox.addItem("Tarjeta de Credito");
		comboBox.addItem("Transferencia");
		comboBox.addItem("Cheque");
		

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
		lblSeleccionarProveedor.setBounds(409, 78, 147, 14);
		contentPane.add(lblSeleccionarProveedor);
		
		JLabel lblNombreProveedor = new JLabel("Nombre del Proveedor");
		lblNombreProveedor.setBounds(650, 78, 142, 14);
		contentPane.add(lblNombreProveedor);

		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirProveedor ep;
				try {
					ep = new ElegirProveedor(new java.awt.Frame(), true);
					ep.setVisible(true);
					lblNombreProveedor.setText(String.valueOf(ep.getProvElegido()));
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				// aca deberiasmo llamar a un store procedure para cargar el listado de las facturas de ese proveedor
			}
		});
		button.setBounds(555, 74, 89, 23);
		contentPane.add(button);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(386, 50, 2, 495);
		contentPane.add(separator);

		JLabel label = new JLabel("Datos del Comprobante.");
		label.setBounds(408, 103, 142, 14);
		contentPane.add(label);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(555, 100, 142, 20);
		contentPane.add(textField_2);

		JLabel label_2 = new JLabel("Medio de Pago");
		label_2.setBounds(408, 128, 125, 14);
		contentPane.add(label_2);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(555, 124, 142, 22);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("Efectivo");
		comboBox_1.addItem("Tarjeta de Credito");
		comboBox_1.addItem("Transferencia");
		comboBox_1.addItem("Cheque");
		
		JLabel label_3 = new JLabel("\u00BFEs un importe parcial?");
		label_3.setBounds(408, 159, 145, 14);
		contentPane.add(label_3);
		
		importeParcial = new JTextField();
		importeParcial.setColumns(10);
		importeParcial.setBounds(555, 181, 142, 20);
		contentPane.add(importeParcial);

		JRadioButton radioButton = new JRadioButton("Si");
		radioButton.setBounds(619, 155, 48, 23);
		contentPane.add(radioButton);
		radioButton.setSelected(true);

		JRadioButton radioButton_1 = new JRadioButton("No");
		radioButton_1.setBounds(555, 155, 62, 23);
		contentPane.add(radioButton_1);

		ButtonGroup importe = new ButtonGroup();
		importe.add(radioButton);
		importe.add(radioButton_1);
		radioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(radioButton.isSelected()) {
					importeParcial.setEnabled(true);
				}
				
			}
		});
		
		radioButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(radioButton_1.isSelected()) {
					importeParcial.setEnabled(false);
				}
				
			}
		});
		
		JLabel label_4 = new JLabel("Importe:");
		label_4.setBounds(408, 189, 49, 14);
		contentPane.add(label_4);

		

		JLabel label_5 = new JLabel("Seleccionar Factura");
		label_5.setBounds(405, 216, 125, 14);
		contentPane.add(label_5);

		JList list_1 = new JList();
		list_1.setBounds(555, 222, 142, 77);
		contentPane.add(list_1);

		JLabel label_6 = new JLabel("Importe total Seleccionado");
		label_6.setBounds(504, 322, 163, 14);
		contentPane.add(label_6);

		JButton button_1 = new JButton("Emitir Recibo");
		button_1.setBounds(502, 388, 142, 23);
		contentPane.add(button_1);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(528, 498, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton button_2 = new JButton("Cancelar");
		button_2.setBounds(145, 498, 89, 23);
		contentPane.add(button_2);

	}
}
