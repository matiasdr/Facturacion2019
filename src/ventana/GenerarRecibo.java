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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;

public class GenerarRecibo extends JFrame {
	private JTextField textField_2;
	private JTextField importeParcial;
	private JPanel contentPane;
	private JTable table;
	private Integer idProveedor=0;
	private Integer idCuenta=0;

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
		setBounds(100, 100, 785, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGeneracinDeRecibos = new JLabel("Generaci\u00F3n de Recibos Por Pagos Hechos a Proveedores");
		lblGeneracinDeRecibos.setBounds(207, 13, 376, 23);
		contentPane.add(lblGeneracinDeRecibos);
		
		ButtonGroup elec = new ButtonGroup();

		JLabel lblSeleccionarProveedor = new JLabel("Seleccionar Proveedor");
		lblSeleccionarProveedor.setBounds(39, 62, 147, 14);
		contentPane.add(lblSeleccionarProveedor);
		
		JLabel lblNombreProveedor = new JLabel("Nombre del Proveedor");
		lblNombreProveedor.setBounds(320, 62, 142, 14);
		contentPane.add(lblNombreProveedor);

		JButton button = new JButton("Buscar");
		button.setBounds(177, 58, 89, 23);
		contentPane.add(button);

		JLabel label = new JLabel("Datos del Comprobante.");
		label.setBounds(39, 103, 142, 14);
		contentPane.add(label);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(177, 100, 142, 20);
		contentPane.add(textField_2);

		JLabel label_2 = new JLabel("Medio de Pago");
		label_2.setBounds(39, 143, 125, 18);
		contentPane.add(label_2);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(177, 141, 142, 22);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("Efectivo");
		comboBox_1.addItem("Tarjeta de Credito");
		comboBox_1.addItem("Transferencia");
		comboBox_1.addItem("Cheque");
		
		importeParcial = new JTextField();
		importeParcial.setColumns(10);
		importeParcial.setBounds(177, 184, 142, 20);
		contentPane.add(importeParcial);
		
		JLabel lblIngreseAquiEl = new JLabel("Importe a abonar");
		lblIngreseAquiEl.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseAquiEl.setBounds(11, 172, 153, 45);
		contentPane.add(lblIngreseAquiEl);

		JLabel lblSeleccionarFacturaA = new JLabel("Resumen de ultimos movimientos");
		lblSeleccionarFacturaA.setBounds(420, 103, 266, 14);
		contentPane.add(lblSeleccionarFacturaA);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 138, 394, 179);
		contentPane.add(scrollPane);
		
		Object[] encabezado = new Object[3];
		encabezado[0] = "Tipo de Comprobante";
		encabezado[1] = "Numero";
		encabezado[2] = "Importe";
		
	    DefaultTableModel modeloTabla = new DefaultTableModel(encabezado, 0);
		
		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);
		
		JButton btnImprimiRecibo = new JButton("Imprimir Recibo");
		btnImprimiRecibo.setBounds(166, 306, 153, 45);
		contentPane.add(btnImprimiRecibo);
		btnImprimiRecibo.setEnabled(false);
		
		JLabel lblSaldoAPagar = new JLabel("Saldo a Pagar: $");
		lblSaldoAPagar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSaldoAPagar.setBounds(340, 328, 131, 23);
		contentPane.add(lblSaldoAPagar);
		
		JLabel labelSaldo = new JLabel("0.0");
		labelSaldo.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelSaldo.setBounds(478, 328, 89, 23);
		contentPane.add(labelSaldo);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirProveedor ep;
				try {
					ep = new ElegirProveedor(new java.awt.Frame(), true);
					ep.setVisible(true);
					lblNombreProveedor.setText(ep.getNombreProovedor());
					idProveedor=ep.getProvElegido();
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// aca deberiasmo llamar a un store procedure para cargar el listado de las facturas de ese proveedor
				
				try {
					Conexion nc = new Conexion();
					Connection conn = nc.conectar();
					Statement instruccion = conn.createStatement();
					ResultSet resultado = instruccion.executeQuery("select * from itemcuentaproveedor join cuenta_proveedor on cuenta_proveedor.id_cuenta = itemcuentaproveedor.id_cuenta where id_proveedor = "+idProveedor);
					
					while(resultado.next()) {
						Object[] fila = new Object[3];
						fila[0] = resultado.getString("comprobante");
						fila[1] = resultado.getString("numerocomprobante");
						fila[2] = resultado.getDouble("saldo");
						modeloTabla.addRow(fila);
					}
					
					nc.desconectar();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					Conexion nc = new Conexion();
					Connection conn = nc.conectar();
					Statement instruccion = conn.createStatement();
					ResultSet resultado = instruccion.executeQuery("Select * from cuenta_proveedor where id_proveedor ="+idProveedor);
					
					while(resultado.next()) {
						labelSaldo.setText(String.valueOf(resultado.getDouble("saldo")));
						idCuenta= resultado.getInt("id_cuenta");
					}
					
					nc.desconectar();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnGenerarRecibo = new JButton("Generar Recibo");
		btnGenerarRecibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idProveedor.equals(0)) {
					JOptionPane.showMessageDialog(null, "No eligio el proveedor");
					return;
				}
				if(importeParcial.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No ingreso el importe del recibo");
					return;
				}
				
				Double importeTotal = Double.valueOf(importeParcial.getText());
				String datoRecibo = textField_2.getText();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now(); 
				String fecha = dtf.format(now);
				
				try {
					Conexion nc = new Conexion();
					Connection conn = nc.conectar();
					Statement instruccion;
					instruccion = conn.createStatement();
					instruccion.executeUpdate("Insert into itemcuentaproveedor (id_cuenta, saldo, fecha, comprobante, numerocomprobante) values ("+idCuenta+", "+importeTotal+", '"+fecha+"', 'recibo', '"+datoRecibo+"')");
					// aca actualizamos el salod de cuenta proveedor
						
					instruccion.executeUpdate("Update cuenta_proveedor set saldo = saldo - "+importeTotal+" where id_proveedor = "+idProveedor);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnGenerarRecibo.setBounds(156, 235, 163, 45);
		contentPane.add(btnGenerarRecibo);

	}
}
