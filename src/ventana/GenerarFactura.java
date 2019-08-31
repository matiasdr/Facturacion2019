package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JPanel;

public class GenerarFactura extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarFactura frame = new GenerarFactura();
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
	public GenerarFactura() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		JLabel lblEmitirComprobanteFactura = new JLabel("Emitir Comprobante Factura");
		lblEmitirComprobanteFactura.setBounds(140, 11, 156, 14);
		contentPane.add(lblEmitirComprobanteFactura);

		JLabel lblSeleccionarCliente = new JLabel("Seleccionar Cliente");
		lblSeleccionarCliente.setBounds(10, 33, 93, 14);
		contentPane.add(lblSeleccionarCliente);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(113, 29, 89, 23);
		contentPane.add(btnBuscar);

		JLabel lblNombreDelCliente = new JLabel("Nombre del CLiente");
		lblNombreDelCliente.setBounds(212, 33, 132, 14);
		contentPane.add(lblNombreDelCliente);

		JRadioButton rdbtnContado = new JRadioButton("Contado");
		rdbtnContado.setBounds(113, 66, 111, 23);
		contentPane.add(rdbtnContado);

		JRadioButton rdbtnCuentaCorriente = new JRadioButton("Cuenta Corriente");
		rdbtnCuentaCorriente.setBounds(233, 66, 111, 23);
		contentPane.add(rdbtnCuentaCorriente);

		JLabel lblCondicionDeVenta = new JLabel("Condicion de venta:");
		lblCondicionDeVenta.setBounds(10, 70, 97, 14);
		contentPane.add(lblCondicionDeVenta);

		JLabel lblDasDeVencimiento = new JLabel("D\u00EDas de Vencimiento:");
		lblDasDeVencimiento.setBounds(113, 93, 111, 14);
		contentPane.add(lblDasDeVencimiento);

		textField = new JTextField();
		textField.setBounds(233, 90, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblBuscarArtculos = new JLabel("Buscar Art\u00EDculos");
		lblBuscarArtculos.setBounds(10, 116, 93, 14);
		contentPane.add(lblBuscarArtculos);

		JLabel lblPorNombre = new JLabel("Por Nombre:");
		lblPorNombre.setBounds(108, 118, 82, 14);
		contentPane.add(lblPorNombre);

		textField_1 = new JTextField();
		textField_1.setBounds(174, 113, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(281, 112, 89, 23);
		contentPane.add(btnFiltrar);

		JList list = new JList();
		list.setBounds(10, 145, 274, 90);
		contentPane.add(list);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(291, 212, 89, 23);
		contentPane.add(btnAgregar);

		JList list_1 = new JList();
		list_1.setBounds(10, 272, 274, 64);
		contentPane.add(list_1);

		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(291, 313, 89, 23);
		contentPane.add(btnQuitar);

		JButton btnGenerarComprobante = new JButton("Generar Comprobante");
		btnGenerarComprobante.setBounds(55, 367, 147, 23);
		contentPane.add(btnGenerarComprobante);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(255, 367, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblElegirCantidad = new JLabel("Elegir Cantidad");
		lblElegirCantidad.setBounds(294, 160, 76, 14);
		contentPane.add(lblElegirCantidad);

		textField_2 = new JTextField();
		textField_2.setBounds(304, 181, 60, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

	}
}
