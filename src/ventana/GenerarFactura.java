package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GenerarFactura extends JFrame {
	private JTextField textField;
	private JTextField textFieldArticulo;
	private JTextField textFieldCantidad;
	private JPanel contentPane;
	private JTable table;
	
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
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// funcion para buscar yseleccionar un Cliente, luego será guardado en el lblNombreDelCLiente
			}
		});
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
		
		
		DefaultTableModel modelTabla = new DefaultTableModel(0, 5);
		Object[] fila=new Object[5];
		fila[0]="EAN";
		fila[1]="Articulo";
		fila[2]="Cantidad";
		fila[3]="Precio Unitario";
		fila[4]="Precio Total";
		modelTabla.addRow(fila);
		

		table = new JTable();
		table.setModel(modelTabla);
		table.setBounds(10, 260, 273, 50);
		contentPane.add(table);
		
		JLabel lblBuscarArtculos = new JLabel("Buscar Art\u00EDculos");
		lblBuscarArtculos.setBounds(10, 116, 93, 14);
		contentPane.add(lblBuscarArtculos);

		JLabel lblPorNombre = new JLabel("Por Nombre:");
		lblPorNombre.setBounds(108, 118, 82, 14);
		contentPane.add(lblPorNombre);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setBounds(304, 181, 60, 20);
		contentPane.add(textFieldCantidad);
		textFieldCantidad.setColumns(10);

		textFieldArticulo = new JTextField();
		textFieldArticulo.setBounds(174, 113, 96, 20);
		contentPane.add(textFieldArticulo);
		textFieldArticulo.setColumns(10);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String art = textFieldArticulo.getText();
				// llamar a la funcion buscar articulo por nombre y colocar los resultados en el Jlist
			}
		});
		btnFiltrar.setBounds(281, 112, 89, 23);
		contentPane.add(btnFiltrar);

		DefaultListModel listModel = new DefaultListModel<String>();
		
		JList list = new JList();
		list.setBounds(10, 145, 274, 90);
		list.setModel(listModel);
		contentPane.add(list);
		listModel.addElement("Hola MUndo");
		
		JLabel totalFactura = new JLabel("00,00");
		totalFactura.setBounds(233, 319, 49, 14);
		contentPane.add(totalFactura);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtenemos loa valores de los controles de la ventana
				Integer cant = Integer.valueOf(textFieldCantidad.getText());
				double total = 10.50*cant;
				
				// creamos un Arreglo de 5 y le pasamos los valores
				
				Object[] nuevaFila = new Object[5];
				nuevaFila[0]="NUMERO EAN"; //aca debemos traer de la base de datos el correspondiente
				nuevaFila[1]= list.getSelectedValue().toString(); // este es el nombre del articulo
				nuevaFila[2]= cant; // la cantidad del artiuclo;
				nuevaFila[3]= 10.50; // este es el precio Unitario de nuestro articulo
				nuevaFila[4]= total; // el total de ese articulo
				// hacemos un addROw para agregar a la vista un item
			
				modelTabla.addRow(nuevaFila);
				
				
				double todos=0;
				for(int i = 1; i<table.getRowCount();i++ ) {
					todos=todos + (double) table.getValueAt(i, 4);
				}
				String var=String.valueOf(todos);
				totalFactura.setText(var);
			}
		});
		btnAgregar.setBounds(291, 212, 89, 23);
		contentPane.add(btnAgregar);

		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelTabla.removeRow(table.getSelectedRow());
				
				double todos=0;
				for(int i = 1; i<table.getRowCount();i++ ) {
					todos=todos + (double) table.getValueAt(i, 4);
				}
				String var=String.valueOf(todos);
				totalFactura.setText(var);
			}
		});
		btnQuitar.setBounds(291, 287, 89, 23);
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

		
		JLabel lblTotalDeLa = new JLabel("Total de la Factura");
		lblTotalDeLa.setBounds(91, 319, 111, 14);
		contentPane.add(lblTotalDeLa);
		

		
		

	}
}
