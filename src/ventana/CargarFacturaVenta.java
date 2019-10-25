package ventana;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.Toolkit;
import com.toedter.calendar.JDateChooser;

import conexion.Conexion;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CargarFacturaVenta extends JFrame {
	private JPanel contentPane;
	
	private JTextField textFieldNeto21;
	private JTextField textFieldIVA21;
	private JTextField textFieldNeto10;
	private JTextField textFieldIVA10;
	private JTextField textFieldNeto27;
	private JTextField textFieldIVA27;
	private JTextField textFieldTotalFacturado;
	private JTextField textFieldPuntodeVenta;
	private JTextField textFieldNumeroFactura;
	private Integer idCliente=0;
	private String nombreCliente="";
	private Integer condicionFiscalCliente=0;
	private Integer idEmpresa = 1;
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
		setBounds(100, 100, 516, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblElegirCliente = new JLabel("Elegir Cliente");
		lblElegirCliente.setBounds(10, 27, 103, 14);
		contentPane.add(lblElegirCliente);
		
		JLabel label_1 = new JLabel("Tipo de Factura");
		label_1.setBounds(10, 66, 103, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Punto de Venta");
		label_2.setBounds(10, 106, 103, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Fecha de Emisi\u00F3n");
		label_3.setBounds(10, 138, 103, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Condicion de Venta");
		label_4.setBounds(10, 167, 145, 26);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Desea ingresar el IVA de forma manual?");
		label_5.setBounds(10, 204, 250, 26);
		contentPane.add(label_5);
		
		JRadioButton radioButtonManual = new JRadioButton("Si");
		radioButtonManual.setBounds(265, 206, 61, 23);
		contentPane.add(radioButtonManual);
		radioButtonManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JRadioButton radioButtonAutomatico = new JRadioButton("No");
		radioButtonAutomatico.setBounds(332, 204, 68, 23);
		contentPane.add(radioButtonAutomatico);
		radioButtonAutomatico.setSelected(true);
		
		ButtonGroup grupoManAut = new ButtonGroup();
		grupoManAut.add(radioButtonAutomatico);
		grupoManAut.add(radioButtonManual);
		
		JLabel lblIva = new JLabel("IVA 21% ");
		lblIva.setBounds(10, 286, 77, 14);
		contentPane.add(lblIva);
		lblIva.setVisible(false);
		
		JLabel lblIva_1 = new JLabel("IVA 10.5% ");
		lblIva_1.setBounds(170, 286, 77, 14);
		contentPane.add(lblIva_1);
		lblIva_1.setVisible(false);
		
		JLabel lblIva_2 = new JLabel("IVA 27% ");
		lblIva_2.setBounds(341, 289, 77, 14);
		contentPane.add(lblIva_2);
		lblIva_2.setVisible(false);
		
		JLabel lblImporteNeto = new JLabel("Importe Neto 21%");
		lblImporteNeto.setBounds(10, 247, 103, 14);
		contentPane.add(lblImporteNeto);
		lblImporteNeto.setVisible(false);
		
		textFieldNeto21 = new JTextField();
		textFieldNeto21.setBounds(113, 241, 54, 20);
		contentPane.add(textFieldNeto21);
		textFieldNeto21.setColumns(10);
		textFieldNeto21.setVisible(false);
		
		textFieldIVA21 = new JTextField();
		textFieldIVA21.setColumns(10);
		textFieldIVA21.setBounds(113, 283, 54, 20);
		contentPane.add(textFieldIVA21);
		textFieldIVA21.setVisible(false);
		
		JLabel lblImporteNeto_1 = new JLabel("Importe Neto 10.5%");
		lblImporteNeto_1.setBounds(169, 247, 103, 14);
		contentPane.add(lblImporteNeto_1);
		lblImporteNeto_1.setVisible(false);
		
		JLabel lblImporteNeto_2 = new JLabel("Importe Neto 27%");
		lblImporteNeto_2.setBounds(343, 247, 93, 14);
		contentPane.add(lblImporteNeto_2);
		lblImporteNeto_2.setVisible(false);
		
		textFieldNeto10 = new JTextField();
		textFieldNeto10.setColumns(10);
		textFieldNeto10.setBounds(276, 241, 54, 20);
		contentPane.add(textFieldNeto10);
		textFieldNeto10.setVisible(false);
		
		textFieldIVA10 = new JTextField();
		textFieldIVA10.setColumns(10);
		textFieldIVA10.setBounds(276, 283, 54, 20);
		contentPane.add(textFieldIVA10);
		textFieldIVA10.setVisible(false);
		
		textFieldNeto27 = new JTextField();
		textFieldNeto27.setColumns(10);
		textFieldNeto27.setBounds(435, 241, 54, 20);
		contentPane.add(textFieldNeto27);
		textFieldNeto27.setVisible(false);
		
		textFieldIVA27 = new JTextField();
		textFieldIVA27.setColumns(10);
		textFieldIVA27.setBounds(435, 283, 54, 20);
		contentPane.add(textFieldIVA27);
		textFieldIVA27.setVisible(false);
		
		JLabel lblAlcuotaDeIva = new JLabel("Al\u00EDcuota de IVA");
		lblAlcuotaDeIva.setBounds(10, 318, 103, 14);
		contentPane.add(lblAlcuotaDeIva);

		JComboBox comboBoxIva = new JComboBox();
		comboBoxIva.setBounds(135, 314, 89, 22);
		contentPane.add(comboBoxIva);
		comboBoxIva.addItem("21%");
		comboBoxIva.addItem("10,5%");
		comboBoxIva.addItem("27%");
		
		JLabel lblImporteTotalFacturado = new JLabel("Importe Total Facturado");
		lblImporteTotalFacturado.setBounds(10, 350, 134, 14);
		contentPane.add(lblImporteTotalFacturado);
		
		textFieldTotalFacturado = new JTextField();
		textFieldTotalFacturado.setBounds(164, 347, 96, 20);
		contentPane.add(textFieldTotalFacturado);
		textFieldTotalFacturado.setColumns(10);
		
		JButton btnCargarFactura = new JButton("Cargar Factura");
		btnCargarFactura.setBounds(318, 331, 145, 38);
		contentPane.add(btnCargarFactura);
		
		JRadioButton rdbtnContado = new JRadioButton("Contado");
		rdbtnContado.setBounds(135, 169, 111, 23);
		contentPane.add(rdbtnContado);
		rdbtnContado.setSelected(true);
		
		JRadioButton rdbtnCuentaCorriente = new JRadioButton("Cuenta Corriente");
		rdbtnCuentaCorriente.setBounds(265, 169, 153, 23);
		contentPane.add(rdbtnCuentaCorriente);
		
		ButtonGroup grupoCondicionVenta = new ButtonGroup();
		grupoCondicionVenta.add(rdbtnContado);
		grupoCondicionVenta.add(rdbtnCuentaCorriente);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(123, 138, 111, 20);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setDate(new Date());
		
		textFieldPuntodeVenta = new JTextField();
		textFieldPuntodeVenta.setBounds(123, 103, 61, 20);
		contentPane.add(textFieldPuntodeVenta);
		textFieldPuntodeVenta.setColumns(10);
		textFieldPuntodeVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				if(!Character.isDigit(validar)) {
					getToolkit().beep();
					e.consume();
				}
				String contenido = textFieldPuntodeVenta.getText();
				if(contenido.length()>=4) {
					e.consume();
				}
			}
		});
		
		textFieldNumeroFactura = new JTextField();
		textFieldNumeroFactura.setBounds(367, 103, 96, 20);
		contentPane.add(textFieldNumeroFactura);
		textFieldNumeroFactura.setColumns(10);
		textFieldNumeroFactura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				if(!Character.isDigit(validar)) {
					getToolkit().beep();
					e.consume();
				}
				String contenido = textFieldNumeroFactura.getText();
				if(contenido.length()>=8) {
					e.consume();
				}
			}
		});
		
		JLabel lblNumeroDeFactura = new JLabel("Numero de Factura");
		lblNumeroDeFactura.setBounds(204, 106, 153, 14);
		contentPane.add(lblNumeroDeFactura);
		
		JRadioButton rdbtnA = new JRadioButton("A");
		rdbtnA.setBounds(119, 62, 68, 23);
		contentPane.add(rdbtnA);
		
		JRadioButton rdbtnB = new JRadioButton("B");
		rdbtnB.setBounds(189, 62, 61, 23);
		contentPane.add(rdbtnB);
		rdbtnB.setSelected(true);
		
		ButtonGroup grupoTipoAB = new ButtonGroup();
		grupoTipoAB.add(rdbtnA);
		grupoTipoAB.add(rdbtnB);
		
		JButton btnBuscarCliente = new JButton("Buscar Cliente");

		btnBuscarCliente.setBounds(113, 23, 134, 23);
		contentPane.add(btnBuscarCliente);
		
		JLabel lblNombreCliente = new JLabel("");
		lblNombreCliente.setBounds(276, 27, 160, 14);
		contentPane.add(lblNombreCliente);
		
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					lblNombreCliente.setText(nombreCliente);
					if(condicionFiscalCliente==1) {
						rdbtnA.setSelected(true);
					} else {
						rdbtnB.setSelected(true);
					}

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		radioButtonAutomatico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIva.setVisible(false);
				lblIva_1.setVisible(false);
				lblIva_2.setVisible(false);
				lblImporteNeto.setVisible(false);
				lblImporteNeto_1.setVisible(false);
				lblImporteNeto_2.setVisible(false);
				textFieldIVA10.setVisible(false);
				textFieldIVA21.setVisible(false);
				textFieldIVA27.setVisible(false);
				textFieldNeto10.setVisible(false);
				textFieldNeto21.setVisible(false);
				textFieldNeto27.setVisible(false);
				
				lblAlcuotaDeIva.setVisible(true);
				comboBoxIva.setVisible(true);
				
			}
		});
		
		radioButtonManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIva.setVisible(true);
				lblIva_1.setVisible(true);
				lblIva_2.setVisible(true);
				lblImporteNeto.setVisible(true);
				lblImporteNeto_1.setVisible(true);
				lblImporteNeto_2.setVisible(true);
				textFieldIVA10.setVisible(true);
				textFieldIVA21.setVisible(true);
				textFieldIVA27.setVisible(true);
				textFieldNeto10.setVisible(true);
				textFieldNeto21.setVisible(true);
				textFieldNeto27.setVisible(true);
				
				lblAlcuotaDeIva.setVisible(false);
				comboBoxIva.setVisible(false);
			}
		});
		
		btnCargarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idCliente==0) {
					JOptionPane.showMessageDialog(null, "Debe Seleccionar el Cliente");
					return;
				}
				if(textFieldPuntodeVenta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete el punto de venta");
					return;
				}
				if(textFieldNumeroFactura.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete el Numero de factura");
					return;
				}
				String puntoDeVenta = textFieldPuntodeVenta.getText();
				String numeroFactura = textFieldNumeroFactura.getText();
				String numeroFinal = puntoDeVenta + numeroFactura;
				String tipoComprobante ="";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fecha = sdf.format(dateChooser.getDate());
				String condicionVenta ="";	
				Double neto21 = 0.0;
				Double neto10 = 0.0;
				Double neto27 = 0.0;
				Double iva10 = 0.0;
				Double iva21 = 0.0;
				Double iva27 = 0.0;
				Double total = 0.0;
				if(rdbtnContado.isSelected()) {
					condicionVenta ="Contado";
				} else {
					condicionVenta ="Cuenta";
				}
				if(rdbtnA.isSelected()) {
					tipoComprobante="A";
				} else {
					tipoComprobante="B";
				}
				if(radioButtonManual.isSelected()) {
					if(!textFieldIVA10.getText().isEmpty()) {
						iva10=Double.valueOf(textFieldIVA10.getText());
					}
					if(!textFieldIVA21.getText().isEmpty()) {
						iva21=Double.valueOf(textFieldIVA21.getText());
					}
					if(!textFieldIVA27.getText().isEmpty()) {
						iva27=Double.valueOf(textFieldIVA27.getText());
					}
					if(!textFieldNeto10.getText().isEmpty()) {
						neto10=Double.valueOf(textFieldNeto10.getText());
					}
					if(!textFieldNeto21.getText().isEmpty()) {
						neto21=Double.valueOf(textFieldNeto21.getText());
					}
					if(!textFieldNeto27.getText().isEmpty()) {
						neto27=Double.valueOf(textFieldNeto27.getText());
					}
				
					total=neto21+neto10+neto27+iva10+iva21+iva27;
					if(total==0.0) {
						JOptionPane.showMessageDialog(null, "Ingrese al menos una discriminacion manual del IVA");
						return;
					}
					String sql = "insert into factura (numero, tipo, fecha, id_cliente, id_empresa, condicion_venta, importe_total, importe_neto21, importe_neto10, importe_neto27, iva_21, iva_10, iva_27) VALUES ('"+numeroFinal+"', '"+tipoComprobante+"', '"+fecha+"', "+idCliente+", "+idEmpresa+", '"+condicionVenta+"', "+total+", "+neto21+", "+neto10+", "+neto27+", "+iva21+", "+iva10+", "+iva27+")";
					System.out.println(sql);
					
					try {
						Conexion nc = new Conexion();
						Connection conn = nc.conectar();
						Statement instruccion;
						instruccion = conn.createStatement();
						instruccion.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				} else {
					if(textFieldTotalFacturado.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Debe ingresar el importe total facturado");
						return;
					}
					total=Double.valueOf(textFieldTotalFacturado.getText());
					if(comboBoxIva.getSelectedItem().equals("21%")) {
						neto21 = total/1.21;
						iva21 = neto21*0.21;
					}
					if(comboBoxIva.getSelectedItem().equals("27%")) {
						neto27 = total/1.27;
						iva27 = neto27*0.27;
					}
					if(comboBoxIva.getSelectedItem().equals("10,5%")) {
						neto10 = total/1.105;
						iva10 = neto10*0.105;
					}
					String sql = "Insert into factura (numero, tipo, fecha, id_cliente, id_empresa, condicion_venta, importe_total, importe_neto21, importe_neto10, importe_neto27, iva_21, iva_10, iva_27) VALUES ('"+numeroFinal+"', '"+tipoComprobante+"', '"+fecha+"', "+idCliente+", "+idEmpresa+", '"+condicionVenta+"', "+total+", "+neto21+", "+neto10+", "+neto27+", "+iva21+", "+iva10+", "+iva27+")";
					
					try {
						Conexion nc = new Conexion();
						Connection conn = nc.conectar();
						Statement instruccion;
						instruccion = conn.createStatement();
						instruccion.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(rdbtnCuentaCorriente.isSelected()) {
					try {
					Conexion nc = new Conexion();
					Connection conn = nc.conectar();
					Statement instruccion = conn.createStatement();
					ResultSet res = instruccion.executeQuery("select id_cuenta from cuenta_cliente where id_cliente="+idCliente);
					if(res.next()) {
						System.out.println("tiene cuenta");
						// isnertamos el item en la cuenta del cliente
						String sql="Insert into itemcuentacliente (id_cuenta, saldo, fecha, debe_haber, comprobante, numerocomprobante) values ((select id_cuenta from cuenta_cliente where id_cliente="+idCliente+"), "+total+", '"+fecha+"', 'D','factura', "+numeroFinal+")";
						System.out.println(sql);
						instruccion.executeUpdate(sql);
						
						// ahora actualizamos el saldo en la tabla cuenta_cliente
						
						instruccion.executeUpdate("Update cuenta_cliente set saldo = saldo + "+total+" where id_cliente = "+idCliente);
						
						
					} else {
						System.out.println("No tiene cuenta");
						// creamos la cuenta
						instruccion.executeUpdate("Insert into cuenta_cliente (id_empresa, id_cliente, saldo, limite_saldo, fecha_alta) values (1, "+idCliente+", "+total+", 0,'"+fecha+"')");
						
						// ahora insertamos la venta como un itemcluentalciente.
						String sql="Insert into itemcuentacliente (id_cuenta, saldo, fecha, debe_haber, comprobante, numerocomprobante) values ((select id_cuenta from cuenta_cliente where id_cliente="+idCliente+"), "+total+", '"+fecha+"', 'D','factura', "+numeroFinal+")";
						System.out.println(sql);
						instruccion.executeUpdate(sql);
						
					}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				JOptionPane.showMessageDialog(null, "Factura Cargada con Éxito");
				dispose();
				CargarFacturaVenta frame = new CargarFacturaVenta();
				frame.setVisible(true);
				
			}
		});

	}
}
