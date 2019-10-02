package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class CargarFacturaCompra extends JFrame {
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
					CargarFacturaCompra frame = new CargarFacturaCompra();
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
	public CargarFacturaCompra() {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JLabel lblCargarFacturaDe = new JLabel("Cargar Factura de Compra");
		lblCargarFacturaDe.setBounds(168, 11, 137, 14);
		contentPane.add(lblCargarFacturaDe);

		JLabel lblElegirProveedor = new JLabel("Elegir Proveedor");
		lblElegirProveedor.setBounds(10, 37, 103, 14);
		contentPane.add(lblElegirProveedor);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(135, 33, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNombreProveedor = new JLabel("Nombre Proveedor");
		lblNombreProveedor.setBounds(253, 37, 169, 14);
		contentPane.add(lblNombreProveedor);

		JLabel lblTipoDeFactura = new JLabel("Tipo de Factura");
		lblTipoDeFactura.setBounds(10, 72, 103, 14);
		contentPane.add(lblTipoDeFactura);

		JRadioButton rdbtna = new JRadioButton("\"A\"");
		rdbtna.setBounds(113, 68, 43, 23);
		contentPane.add(rdbtna);

		JRadioButton rdbtnb = new JRadioButton("\"B\"");
		rdbtnb.setBounds(170, 68, 43, 23);
		contentPane.add(rdbtnb);

		JRadioButton rdbtnc = new JRadioButton("\"C\"");
		rdbtnc.setBounds(228, 68, 43, 23);
		contentPane.add(rdbtnc);

		JLabel lblPuntoDeVenta = new JLabel("Punto de Venta");
		lblPuntoDeVenta.setBounds(10, 112, 103, 14);
		contentPane.add(lblPuntoDeVenta);

		textField = new JTextField();
		textField.setBounds(101, 109, 54, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNumeroDeFactura = new JLabel("Numero de Factura");
		lblNumeroDeFactura.setBounds(168, 112, 125, 14);
		contentPane.add(lblNumeroDeFactura);

		textField_1 = new JTextField();
		textField_1.setBounds(276, 109, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblFechaDeEmisin = new JLabel("Fecha de Emisi\u00F3n");
		lblFechaDeEmisin.setBounds(10, 144, 103, 14);
		contentPane.add(lblFechaDeEmisin);

		JButton btnestoDebeSer = new JButton("(Esto debe ser un jcalenda)");
		btnestoDebeSer.setBounds(113, 140, 89, 23);
		contentPane.add(btnestoDebeSer);

		JLabel lblConceptoDeCompra = new JLabel("Concepto de Compra");
		lblConceptoDeCompra.setBounds(10, 174, 125, 14);
		contentPane.add(lblConceptoDeCompra);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(135, 170, 28, 22);
		contentPane.add(comboBox);

		JButton btnDetallar = new JButton("Detallar");
		btnDetallar.setBounds(101, 207, 89, 23);
		contentPane.add(btnDetallar);

		textField_2 = new JTextField();
		textField_2.setBounds(216, 171, 96, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblAlcuotaDeIva = new JLabel("Al\u00EDcuota de IVA");
		lblAlcuotaDeIva.setBounds(10, 240, 103, 14);
		contentPane.add(lblAlcuotaDeIva);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(113, 236, 28, 22);
		contentPane.add(comboBox_1);

		JLabel lblImporteNetoTotal = new JLabel("Importe NETO TOTAL FACTURADO");
		lblImporteNetoTotal.setBounds(10, 275, 180, 14);
		contentPane.add(lblImporteNetoTotal);

		textField_3 = new JTextField();
		textField_3.setBounds(209, 272, 96, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblIvaFiscal = new JLabel("IVA FISCAL");
		lblIvaFiscal.setBounds(10, 304, 77, 14);
		contentPane.add(lblIvaFiscal);

		JLabel lblesteEsEl = new JLabel("(ESTE ES EL RESULTADO DE IVA)");
		lblesteEsEl.setBounds(130, 304, 193, 14);
		contentPane.add(lblesteEsEl);

		JButton btnCargarFactura = new JButton("Cargar Factura");
		btnCargarFactura.setBounds(113, 378, 137, 23);
		contentPane.add(btnCargarFactura);

	}

}
