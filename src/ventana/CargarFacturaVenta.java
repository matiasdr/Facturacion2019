package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.Toolkit;

public class CargarFacturaVenta extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargarFacturaVenta frame = new CargarFacturaVenta();
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
	public CargarFacturaVenta() {
		setTitle("Cargar Facturas de Venta");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CargarFacturaVenta.class.getResource("/logos/logo4.png")));
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCargarFacturaDe = new JLabel("Cargar Factura de Venta");
		lblCargarFacturaDe.setBounds(159, 13, 156, 23);
		contentPane.add(lblCargarFacturaDe);

		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setBounds(12, 72, 49, 14);
		contentPane.add(lblNewLabel);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(70, 63, 89, 23);
		contentPane.add(btnBuscar);

		JLabel lblnombreDelCliente = new JLabel("(nombre del cliente)");
		lblnombreDelCliente.setBounds(183, 67, 156, 14);
		contentPane.add(lblnombreDelCliente);

		JLabel lblPuntoDeVenta = new JLabel("Punto de Venta");
		lblPuntoDeVenta.setBounds(12, 99, 96, 14);
		contentPane.add(lblPuntoDeVenta);

		textField = new JTextField();
		textField.setBounds(118, 96, 41, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNumeroDeFactura = new JLabel("Numero de Factura");
		lblNumeroDeFactura.setBounds(180, 99, 115, 14);
		contentPane.add(lblNumeroDeFactura);

		textField_1 = new JTextField();
		textField_1.setBounds(319, 96, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblCondicionDeVenta = new JLabel("Condicion de Venta");
		lblCondicionDeVenta.setBounds(10, 130, 121, 14);
		contentPane.add(lblCondicionDeVenta);

		JRadioButton rdbtnContado = new JRadioButton("Contado");
		rdbtnContado.setBounds(159, 126, 80, 23);
		contentPane.add(rdbtnContado);

		JRadioButton rdbtnCuentaCorriente = new JRadioButton("Cuenta Corriente");
		rdbtnCuentaCorriente.setBounds(267, 126, 148, 23);
		contentPane.add(rdbtnCuentaCorriente);

	}
}
