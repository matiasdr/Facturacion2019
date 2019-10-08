package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.ws.handler.MessageContext;

import conexion.Conexion;
import javax.swing.JScrollPane;

public class GenerarFactura extends JFrame {
	private JTextField textFieldVencimiento;
	private JTextField textFieldArticulo;
	private JTextField textFieldCantidad;
	private JPanel contentPane;
	private JTable table;
	private JTable tablaProductos;
	
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
	 * @throws SQLException 
	 */
	public GenerarFactura() throws SQLException {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		textFieldVencimiento = new JTextField();
		textFieldVencimiento.setBounds(233, 90, 96, 20);
		contentPane.add(textFieldVencimiento);
		textFieldVencimiento.setColumns(10);

		JLabel lblNombreDelCliente = new JLabel("Nombre del CLiente");
		lblNombreDelCliente.setBounds(212, 33, 132, 14);
		contentPane.add(lblNombreDelCliente);
		
		JRadioButton rdbtnContado = new JRadioButton("Contado");
		rdbtnContado.setBounds(113, 66, 111, 23);
		contentPane.add(rdbtnContado);

		JRadioButton rdbtnCuentaCorriente = new JRadioButton("Cuenta Corriente");
		rdbtnCuentaCorriente.setBounds(233, 66, 111, 23);
		contentPane.add(rdbtnCuentaCorriente);
		rdbtnCuentaCorriente.setSelected(true);
		
		ButtonGroup condicionVenta = new ButtonGroup();
		condicionVenta.add(rdbtnCuentaCorriente);
		condicionVenta.add(rdbtnContado);
		rdbtnContado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnContado.isSelected()) {
					textFieldVencimiento.setEnabled(false);
				}
			}
		});
		
		rdbtnCuentaCorriente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCuentaCorriente.isSelected()) {
					textFieldVencimiento.setEnabled(true);
				}
			}
		});

		JLabel lblCondicionDeVenta = new JLabel("Condicion de venta:");
		lblCondicionDeVenta.setBounds(10, 70, 97, 14);
		contentPane.add(lblCondicionDeVenta);

		JLabel lblDasDeVencimiento = new JLabel("D\u00EDas de Vencimiento:");
		lblDasDeVencimiento.setBounds(113, 93, 111, 14);
		contentPane.add(lblDasDeVencimiento);		
		
		Object[] fila=new Object[5];
		fila[0]="EAN";
		fila[1]="Articulo";
		fila[2]="Cantidad";
		fila[3]="Precio Unitario";
		fila[4]="Precio Total";
		
		DefaultTableModel modelTabla = new DefaultTableModel(fila, 0);
		
		
		
		Object[] encabe=new Object[4];
		encabe[0]="EAN";
		encabe[1]="Descripcion";
		encabe[2]="Stock";
		encabe[3]="Precio Unitario";		
		
		
		DefaultTableModel modelTablaProductos = new DefaultTableModel(encabe, 0);
		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from articulo");
		
		while(resultado.next()) {
			Object[] linea = new Object[4];
			linea[0]= resultado.getString("ean");
			linea[1]= resultado.getString("descripcion");
			linea[2]= resultado.getInt("cantidad");
			linea[3]= resultado.getDouble("precio_venta");
			modelTablaProductos.addRow(linea);
			
		}
		nc.desconectar();
		
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
				
				// llamar a la funcion buscar articulo por nombre y colocar los resultados en el JTable
				
				Conexion nc1 = new Conexion();
				Connection conec1 = nc1.conectar();
				Statement instruccion1;
				try {
					instruccion1 = conec1.createStatement();
					ResultSet resultado1 = instruccion1.executeQuery("Select * from articulo join proveedor ON proveedor.id_proveedor = articulo.id_proveedor where descripcion like '%"+art+"%'");
					
					// antes de cargar los resultado borramos todos los datos de la tabla menos la primer fial que tiene el encabezado
					
					while(modelTablaProductos.getRowCount()>0) {
						modelTablaProductos.removeRow(modelTablaProductos.getRowCount()-1);
					}
					
					while(resultado1.next()) {
						Object[] linea = new Object[4];
						linea[0]= resultado1.getString("ean");
						linea[1]= resultado1.getString("descripcion");
						linea[2]= resultado1.getInt("cantidad");
						linea[3]= resultado1.getDouble("precio_venta");
						modelTablaProductos.addRow(linea);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nc1.desconectar();
				
			}
		});
		btnFiltrar.setBounds(281, 112, 89, 23);
		contentPane.add(btnFiltrar);

		JLabel totalFactura = new JLabel("00,00");
		totalFactura.setBounds(233, 319, 49, 14);
		contentPane.add(totalFactura);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtenemos loa valores de los controles de la ventana
				if(tablaProductos.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "No Seleccionó el Producto");
					return;
				}
				if(textFieldCantidad.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No completó la cantidad");
					return;
				}
				Integer cant = Integer.valueOf(textFieldCantidad.getText());
				Double precio = (Double) modelTablaProductos.getValueAt(tablaProductos.getSelectedRow(), 3);
				String ean =(String) modelTablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0);
				String descripcion = (String) modelTablaProductos.getValueAt(tablaProductos.getSelectedRow(), 1);
				Integer stock = (Integer)modelTablaProductos.getValueAt(tablaProductos.getSelectedRow(), 2);
				// verificamos que no se intente obtener una cantidad mayor a la que hay en existencia
				
				if(cant>stock || cant<=0) {
					JOptionPane.showMessageDialog(null, "Cantidad elegida No Valida");
					return;
				}
				
				Double total = precio*cant;
				
				// creamos un Arreglo de 5 y le pasamos los valores
				
				Object[] nuevaFila = new Object[5];
				nuevaFila[0]=ean; //aca debemos traer de la base de datos el correspondiente
				nuevaFila[1]= descripcion; // este es el nombre del articulo
				nuevaFila[2]= cant; // la cantidad del artiuclo;
				nuevaFila[3]= precio; // este es el precio Unitario de nuestro articulo
				nuevaFila[4]= total; // el total de ese articulo
				// hacemos un addROw para agregar a la vista un item
			
				modelTabla.addRow(nuevaFila);
				
				
				double todos=0;
				for(int i = 0; i<table.getRowCount();i++ ) {
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
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "No Seleccionó el Item a eliminar");
					return;
				}
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
		
		tablaProductos = new JTable();
		tablaProductos.setModel(modelTablaProductos);
		
		JScrollPane scrollPane = new JScrollPane(tablaProductos);
		scrollPane.setBounds(20, 141, 263, 100);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 248, 272, 62);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(modelTabla);
		//scrollPane.setViewportView(tablaProductos);
		

		
		

	}
}
