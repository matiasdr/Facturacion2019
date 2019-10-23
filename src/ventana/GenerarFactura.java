package ventana;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import conexion.Conexion;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Toolkit;

public class GenerarFactura extends JFrame {
	private JTextField textFieldVencimiento;
	private JTextField textFieldArticulo;
	private JTextField textFieldCantidad;
	private JPanel contentPane;
	private JTable table;
	private JTable tablaProductos;
	private String nombreCliente;
	private Integer condicionFiscalCliente;
	private Integer idCliente;
	
	
	
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
		setTitle("Generar Factura...");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerarFactura.class.getResource("/logos/logo4.png")));
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 898, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		JLabel lblNombreDelCliente = new JLabel("Nombre del CLiente");

		JLabel lblSeleccionarCliente = new JLabel("Seleccionar Cliente");
		lblSeleccionarCliente.setBounds(78, 22, 119, 14);
		contentPane.add(lblSeleccionarCliente);

		JButton btnBuscar = new JButton("Buscar Cliente");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// funcion para buscar yseleccionar un Cliente, luego será guardado en el lblNombreDelCLiente
				try {
					ElegirCliente ec = new ElegirCliente(new java.awt.Frame(), true);
					ec.setVisible(true);
					idCliente=ec.getClienElegido();
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Conexion nc = new Conexion();
				Connection connec = nc.conectar();
				try {
					Statement instruccion = connec.createStatement();
					ResultSet resultado = instruccion.executeQuery("Select * from cliente where id_cliente = "+idCliente);
					while(resultado.next()) {
						nombreCliente = resultado.getString("nombre");
						condicionFiscalCliente = resultado.getInt("id_condicion_fiscal");
					}
					lblNombreDelCliente.setText(nombreCliente);

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				
				
			}
		});
		btnBuscar.setBounds(209, 18, 183, 23);
		contentPane.add(btnBuscar);
		
		textFieldVencimiento = new JTextField();
		textFieldVencimiento.setBounds(248, 86, 96, 20);
		contentPane.add(textFieldVencimiento);
		textFieldVencimiento.setColumns(10);

		
		lblNombreDelCliente.setBounds(434, 22, 132, 14);
		contentPane.add(lblNombreDelCliente);
		
		JRadioButton rdbtnContado = new JRadioButton("Contado");
		rdbtnContado.setBounds(220, 55, 111, 23);
		contentPane.add(rdbtnContado);

		JRadioButton rdbtnCuentaCorriente = new JRadioButton("Cuenta Corriente");
		rdbtnCuentaCorriente.setBounds(343, 55, 111, 23);
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
		lblCondicionDeVenta.setBounds(78, 59, 119, 14);
		contentPane.add(lblCondicionDeVenta);

		JLabel lblDasDeVencimiento = new JLabel("D\u00EDas de Vencimiento:");
		lblDasDeVencimiento.setBounds(88, 89, 132, 14);
		contentPane.add(lblDasDeVencimiento);		
		
		Object[] fila=new Object[7];
		fila[0]="EAN";
		fila[1]="Articulo";
		fila[2]="Cantidad";
		fila[3]="Precio Unitario";
		fila[4]="Precio Total";
		fila[5]="ID";
		fila[6]="IVA %";
		
		DefaultTableModel modelTabla = new DefaultTableModel(fila, 0);
		
		
		
		Object[] encabe=new Object[6];
		encabe[0]="EAN";
		encabe[1]="Descripcion";
		encabe[2]="Stock";
		encabe[3]="Precio Unitario";
		encabe[4]="ID";
		encabe[5]="IVA %";
		
		DefaultTableModel modelTablaProductos = new DefaultTableModel(encabe, 0);
		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from articulo");
		
		while(resultado.next()) {
			Object[] linea = new Object[6];
			linea[0]= resultado.getString("ean");
			linea[1]= resultado.getString("descripcion");
			linea[2]= resultado.getInt("cantidad");
			linea[3]= resultado.getDouble("pvp");
			linea[4]= resultado.getInt("id_articulo");
			linea[5]= resultado.getDouble("ivaporcent");
			modelTablaProductos.addRow(linea);
			
		}
		nc.desconectar();
		
		JLabel lblBuscarArtculos = new JLabel("Buscar Art\u00EDculos");
		lblBuscarArtculos.setBounds(375, 89, 93, 14);
		contentPane.add(lblBuscarArtculos);

		JLabel lblPorNombre = new JLabel("Por Nombre:");
		lblPorNombre.setBounds(487, 89, 82, 14);
		contentPane.add(lblPorNombre);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setBounds(753, 228, 60, 20);
		contentPane.add(textFieldCantidad);
		textFieldCantidad.setColumns(10);

		textFieldArticulo = new JTextField();
		textFieldArticulo.setBounds(574, 86, 96, 20);
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
						Object[] linea = new Object[6];
						linea[0]= resultado1.getString("ean");
						linea[1]= resultado1.getString("descripcion");
						linea[2]= resultado1.getInt("cantidad");
						linea[3]= resultado1.getDouble("pvp");
						linea[4]= resultado1.getInt("id_articulo");
						linea[5]= resultado.getDouble("ivaporcent");
						modelTablaProductos.addRow(linea);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nc1.desconectar();
				
			}
		});
		btnFiltrar.setBounds(708, 85, 89, 23);
		contentPane.add(btnFiltrar);

		JLabel totalFactura = new JLabel("00,00");
		totalFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
		totalFactura.setBounds(530, 576, 140, 14);
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
				Integer idArtic= (Integer)modelTablaProductos.getValueAt(tablaProductos.getSelectedRow(), 4);
				Double porc = (Double)modelTablaProductos.getValueAt(tablaProductos.getSelectedRow(), 5);
				// verificamos que no se intente obtener una cantidad mayor a la que hay en existencia
				
				if(cant>stock || cant<=0) {
					JOptionPane.showMessageDialog(null, "Cantidad elegida No Valida");
					return;
				}
				
				Double total = precio*cant;
				
				// creamos un Arreglo de 5 y le pasamos los valores
				
				Object[] nuevaFila = new Object[7];
				nuevaFila[0]=ean; //aca debemos traer de la base de datos el correspondiente
				nuevaFila[1]= descripcion; // este es el nombre del articulo
				nuevaFila[2]= cant; // la cantidad del artiuclo;
				nuevaFila[3]= precio; // este es el precio Unitario de nuestro articulo
				nuevaFila[4]= total; // el total de ese articulo
				nuevaFila[5]= idArtic;
				nuevaFila[6]= porc;
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
		btnAgregar.setBounds(712, 261, 160, 42);
		contentPane.add(btnAgregar);

		JButton btnQuitar = new JButton("Quitar Articulo");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "No Seleccionó el Item a eliminar");
					return;
				}
				modelTabla.removeRow(table.getSelectedRow());
				
				double todos=0;
				for(int i = 0; i<table.getRowCount();i++ ) {
					todos=todos + (double) table.getValueAt(i, 4);
				}
				String var=String.valueOf(todos);
				totalFactura.setText(var);
			}
		});
		btnQuitar.setBounds(712, 499, 160, 42);
		contentPane.add(btnQuitar);

		JButton btnGenerarComprobante = new JButton("Emitir Factura");
		btnGenerarComprobante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipoComprobante=null;
				Integer numFactura=null;
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now(); 
				String fecha = dtf.format(now);
				String condicion=null;
				Integer idFacturaGenerada=null;
				String numeroFinal="";
				Double neto21=0.0;
				Double neto27=0.0;
				Double neto10=0.0;
				Double iva21=0.0;
				Double iva27=0.0;
				Double iva10=0.0;
				if(rdbtnContado.isSelected()) {
					condicion="Contado";
				} else {
					condicion="Cuenta";
				}
				Double importeTotal = Double.valueOf(totalFactura.getText());
				if(condicionFiscalCliente==1) {
					tipoComprobante="A";
				} else {
					tipoComprobante="B";
				}
				for(int i = 0; i<table.getRowCount();i++ ) {
					if((double)table.getValueAt(i, 6)==21.00) {
						Double aux = ((double) table.getValueAt(i, 4))/1.21;
						neto21 = neto21 + aux;
						iva21 = aux*0.21;
					//	System.out.println(String.valueOf(neto21+" "+iva21));
					} else 
					if((double)table.getValueAt(i, 6)==27.00) {
						Double aux = ((double) table.getValueAt(i, 4))/1.27;
						neto27 = neto27 + aux;
						iva27 = aux*0.27;
					} else 
					if((double)table.getValueAt(i, 6)==10.50) {
						Double aux = ((double) table.getValueAt(i, 4))/1.105;
						neto10 = neto10 + aux;
						iva10 = aux*0.105;
					}
				}
				
				
				try {
					Conexion nc = new Conexion();
					Connection conn = nc.conectar();
					Statement instruccion;
					instruccion = conn.createStatement();
					ResultSet resultado = instruccion.executeQuery("select top 1 numero from factura order by numero desc");
					while(resultado.next()) {
						numFactura=Integer.valueOf(resultado.getString("numero"))+1;
					}
					numeroFinal="000"+numFactura;
					instruccion = conn.createStatement();
					String sql="insert into factura(numero, tipo, fecha, id_cliente, id_empresa, condicion_venta, importe_total, importe_neto21, importe_neto10, importe_neto27, iva_21, iva_10, iva_27) VALUES ('"+numeroFinal+"', '"+tipoComprobante+"', '"+fecha+"', "+idCliente+", 1, '"+condicion+"', "+importeTotal+", "+neto21+", "+neto10+", "+neto27+", "+iva21+", "+iva10+", "+iva27+")";
					System.out.println(sql);
					instruccion.executeUpdate(sql);
					ResultSet resul = instruccion.executeQuery("select top 1 id_factura from factura order by id_factura desc");
					while(resul.next()) {
						idFacturaGenerada = resul.getInt("id_factura");
					}
					nc.desconectar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println(tipoComprobante+ " " +numFactura+ " " +fecha+ " " +condicion+ " " +importeTotal);
				
				// ahora hacemos toda la secuencia de actualizar el stock, de agregar la factura a la cuenta del cliente y agregar los registros de cada articulo_factura correspondiente
				try {
					Conexion nc = new Conexion();
					Connection conn = nc.conectar();
					Statement instruccion = conn.createStatement();
					for(int i = 0; i<table.getRowCount();i++ ) {
						int idarticulo=(int) table.getValueAt(i, 5);
						int cantidadarticulo=(int) table.getValueAt(i, 2);
						instruccion.execute("spnuevoarticulofactura null, "+cantidadarticulo+", "+idFacturaGenerada+", "+idarticulo+", null");
						instruccion.executeUpdate("update articulo set cantidad=cantidad-"+cantidadarticulo+" where id_articulo = "+idarticulo);
					}
					
					// preguntamos si la venta es en cuenta corriente... en ese caso debemos insertar la venta en la cuenta del cliente
					if(rdbtnCuentaCorriente.isSelected()) {
						
						ResultSet res = instruccion.executeQuery("select id_cuenta from cuenta_cliente where id_cliente="+idCliente);
						if(res.next()) {
							System.out.println("tiene cuenta");
							// isnertamos el item en la cuenta del cliente
							String sql="Insert into itemcuentacliente (id_cuenta, saldo, fecha, debe_haber, comprobante, numerocomprobante) values ((select id_cuenta from cuenta_cliente where id_cliente="+idCliente+"), "+importeTotal+", '"+fecha+"', 'D','factura', "+numeroFinal+")";
							System.out.println(sql);
							instruccion.executeUpdate(sql);
							
							// ahora actualizamos el saldo en la tabla cuenta_cliente
							
							instruccion.executeUpdate("Update cuenta_cliente set saldo = saldo + "+importeTotal+" where id_cliente = "+idCliente);
							
							
						} else {
							System.out.println("No tiene cuenta");
							// creamos la cuenta
							instruccion.executeUpdate("Insert into cuenta_cliente (id_empresa, id_cliente, saldo, limite_saldo, fecha_alta) values (1, "+idCliente+", "+importeTotal+", 0,'"+fecha+"')");
							
							// ahora insertamos la venta como un itemcluentalciente.
							String sql="Insert into itemcuentacliente (id_cuenta, saldo, fecha, debe_haber, comprobante, numerocomprobante) values ((select id_cuenta from cuenta_cliente where id_cliente="+idCliente+"), "+importeTotal+", '"+fecha+"', 'D','factura', "+numeroFinal+")";
							System.out.println(sql);
							instruccion.executeUpdate(sql);
							
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// Ahora recargamos la página para que se actualice el stock
				
				dispose();
				try {
					GenerarFactura frame;
					frame = new GenerarFactura();
					frame.setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnGenerarComprobante.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnGenerarComprobante.setBounds(30, 558, 219, 44);
		contentPane.add(btnGenerarComprobante);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(712, 575, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblElegirCantidad = new JLabel("Ingresar Cantidad");
		lblElegirCantidad.setBounds(714, 191, 119, 14);
		contentPane.add(lblElegirCantidad);

		
		JLabel lblTotalDeLa = new JLabel("Total de la Factura");
		lblTotalDeLa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalDeLa.setBounds(259, 576, 224, 14);
		contentPane.add(lblTotalDeLa);
		
		tablaProductos = new JTable();
		tablaProductos.setModel(modelTablaProductos);
		
		JScrollPane scrollPane = new JScrollPane(tablaProductos);
		scrollPane.setBounds(30, 116, 672, 236);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 363, 672, 184);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(modelTabla);
		//scrollPane.setViewportView(tablaProductos);
		

		
		

	}
}
