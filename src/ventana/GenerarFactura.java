package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JList;

public class GenerarFactura extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarFactura dialog = new GenerarFactura();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
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
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(null);
		
		JLabel lblEmitirComprobanteFactura = new JLabel("Emitir Comprobante Factura");
		lblEmitirComprobanteFactura.setBounds(140, 11, 156, 14);
		getContentPane().add(lblEmitirComprobanteFactura);
		
		JLabel lblSeleccionarCliente = new JLabel("Seleccionar Cliente");
		lblSeleccionarCliente.setBounds(10, 33, 93, 14);
		getContentPane().add(lblSeleccionarCliente);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(113, 29, 89, 23);
		getContentPane().add(btnBuscar);
		
		JLabel lblNombreDelCliente = new JLabel("Nombre del CLiente");
		lblNombreDelCliente.setBounds(212, 33, 132, 14);
		getContentPane().add(lblNombreDelCliente);
		
		JRadioButton rdbtnContado = new JRadioButton("Contado");
		rdbtnContado.setBounds(113, 66, 111, 23);
		getContentPane().add(rdbtnContado);
		
		JRadioButton rdbtnCuentaCorriente = new JRadioButton("Cuenta Corriente");
		rdbtnCuentaCorriente.setBounds(233, 66, 111, 23);
		getContentPane().add(rdbtnCuentaCorriente);
		
		JLabel lblCondicionDeVenta = new JLabel("Condicion de venta:");
		lblCondicionDeVenta.setBounds(10, 70, 97, 14);
		getContentPane().add(lblCondicionDeVenta);
		
		JLabel lblDasDeVencimiento = new JLabel("D\u00EDas de Vencimiento:");
		lblDasDeVencimiento.setBounds(113, 93, 111, 14);
		getContentPane().add(lblDasDeVencimiento);
		
		textField = new JTextField();
		textField.setBounds(233, 90, 96, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblBuscarArtculos = new JLabel("Buscar Art\u00EDculos");
		lblBuscarArtculos.setBounds(10, 116, 93, 14);
		getContentPane().add(lblBuscarArtculos);
		
		JLabel lblPorNombre = new JLabel("Por Nombre:");
		lblPorNombre.setBounds(108, 118, 82, 14);
		getContentPane().add(lblPorNombre);
		
		textField_1 = new JTextField();
		textField_1.setBounds(174, 113, 96, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(281, 112, 89, 23);
		getContentPane().add(btnFiltrar);
		
		JList list = new JList();
		list.setBounds(10, 145, 274, 90);
		getContentPane().add(list);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(291, 212, 89, 23);
		getContentPane().add(btnAgregar);
		
		JList list_1 = new JList();
		list_1.setBounds(10, 272, 274, 64);
		getContentPane().add(list_1);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(291, 313, 89, 23);
		getContentPane().add(btnQuitar);
		
		JButton btnGenerarComprobante = new JButton("Generar Comprobante");
		btnGenerarComprobante.setBounds(55, 367, 147, 23);
		getContentPane().add(btnGenerarComprobante);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(255, 367, 89, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblElegirCantidad = new JLabel("Elegir Cantidad");
		lblElegirCantidad.setBounds(294, 160, 76, 14);
		getContentPane().add(lblElegirCantidad);
		
		textField_2 = new JTextField();
		textField_2.setBounds(304, 181, 60, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

	}
}
