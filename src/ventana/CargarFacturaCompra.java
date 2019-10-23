package ventana;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import conexion.Conexion;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Toolkit;

public class CargarFacturaCompra extends JFrame {
	private JTextField textField;
	private JTextField textFieldNumFactura;
	private JTextField textFieldImporteFacturado;
	private JPanel contentPane;
	JButton btnCalcularIVA = new JButton("Calcular Importe de IVA");
	JLabel lblesteEsEl = new JLabel("(Monto)");
	JLabel lblIvaFiscal = new JLabel("IVA FISCAL");
	JLabel lblMoneda = new JLabel("$");
	private JTextField textFieldNeto21;
	private JTextField textFieldIVA21;
	private JTextField textFieldNeto10;
	private JTextField textFieldIVA10;
	private JTextField textFieldNeto27;
	private JTextField textFieldIVA27;
	private Integer idProveedor;
	private String tipoFactura;
	private String conceptoCompra;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(CargarFacturaCompra.class.getResource("/logos/logo4.png")));
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cargar Facturas de Compras");
		setBounds(100, 100, 530, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JRadioButton rdbtna = new JRadioButton("\"A\"");
		rdbtna.setBounds(113, 68, 77, 23);
		contentPane.add(rdbtna);

		JRadioButton rdbtnb = new JRadioButton("\"B\"");
		rdbtnb.setBounds(209, 68, 75, 23);
		contentPane.add(rdbtnb);

		JRadioButton rdbtnc = new JRadioButton("\"C\"");
		rdbtnc.setBounds(297, 68, 75, 23);
		contentPane.add(rdbtnc);
		
		ButtonGroup tipo = new ButtonGroup();
		tipo.add(rdbtna);
		tipo.add(rdbtnb);
		tipo.add(rdbtnc);
		
		rdbtnc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					tipoFactura="C";
					btnCalcularIVA.setVisible(false);
					lblesteEsEl.setVisible(false);
					lblIvaFiscal.setVisible(false);
					lblMoneda.setVisible(false);
				
							
			}
		});
		
		rdbtnb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tipoFactura="B";
				btnCalcularIVA.setVisible(true);
				lblesteEsEl.setVisible(true);
				lblIvaFiscal.setVisible(true);
				lblMoneda.setVisible(true);
			}
		});
		
		rdbtna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tipoFactura="A";
				btnCalcularIVA.setVisible(true);
				lblesteEsEl.setVisible(true);
				lblIvaFiscal.setVisible(true);	
				lblMoneda.setVisible(true);
			}
		});
		
		JLabel lblNombreProveedor = new JLabel("Nombre Proveedor");
		lblNombreProveedor.setBounds(253, 37, 169, 14);
		contentPane.add(lblNombreProveedor);

		JLabel lblElegirProveedor = new JLabel("Elegir Proveedor");
		lblElegirProveedor.setBounds(10, 37, 103, 14);
		contentPane.add(lblElegirProveedor);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(135, 33, 89, 23);
		contentPane.add(btnNewButton);

		

		JLabel lblTipoDeFactura = new JLabel("Tipo de Factura");
		lblTipoDeFactura.setBounds(10, 72, 103, 14);
		contentPane.add(lblTipoDeFactura);

		
		JLabel lblPuntoDeVenta = new JLabel("Punto de Venta");
		lblPuntoDeVenta.setBounds(10, 112, 103, 14);
		contentPane.add(lblPuntoDeVenta);

		textField = new JTextField();
		textField.setBounds(101, 109, 54, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
				String contenido = textField.getText();
				if(contenido.length()>=4) {
					e.consume();
				}
			}
		});
		

		JLabel lblNumeroDeFactura = new JLabel("Numero de Factura");
		lblNumeroDeFactura.setBounds(180, 112, 125, 14);
		contentPane.add(lblNumeroDeFactura);

		textFieldNumFactura = new JTextField();
		textFieldNumFactura.setBounds(307, 109, 96, 20);
		contentPane.add(textFieldNumFactura);
		textFieldNumFactura.setColumns(10);
		textFieldNumFactura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
				String contenido = textFieldNumFactura.getText();
				if(contenido.length()>=8) {
					e.consume();
				}
			}
		});

		JLabel lblFechaDeEmisin = new JLabel("Fecha de Emisi\u00F3n");
		lblFechaDeEmisin.setBounds(10, 144, 103, 14);
		contentPane.add(lblFechaDeEmisin);

		JLabel lblConceptoDeCompra = new JLabel("Concepto de Compra");
		lblConceptoDeCompra.setBounds(10, 174, 125, 14);
		contentPane.add(lblConceptoDeCompra);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(135, 170, 137, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Compra de Bienes");
		comboBox.addItem("Servicios");
		comboBox.addItem("Bienes de Uso");

		JLabel lblAlcuotaDeIva = new JLabel("Al\u00EDcuota de IVA");
		lblAlcuotaDeIva.setBounds(10, 371, 103, 14);
		contentPane.add(lblAlcuotaDeIva);

		JComboBox comboBoxIva = new JComboBox();
		comboBoxIva.setBounds(135, 367, 89, 22);
		contentPane.add(comboBoxIva);
		comboBoxIva.addItem("21%");
		comboBoxIva.addItem("10,5%");
		comboBoxIva.addItem("27%");
		
		JLabel lblImporteNetoTotal = new JLabel("Importe TOTAL FACTURADO");
		lblImporteNetoTotal.setBounds(10, 405, 180, 14);
		contentPane.add(lblImporteNetoTotal);

		textFieldImporteFacturado = new JTextField();
		textFieldImporteFacturado.setBounds(219, 402, 77, 20);
		contentPane.add(textFieldImporteFacturado);
		textFieldImporteFacturado.setColumns(10);

		
		lblIvaFiscal.setBounds(29, 441, 77, 14);
		contentPane.add(lblIvaFiscal);
		
		lblesteEsEl.setHorizontalAlignment(SwingConstants.LEFT);
		

		
		lblesteEsEl.setBounds(155, 441, 125, 14);
		contentPane.add(lblesteEsEl);

		JButton btnCargarFactura = new JButton("Cargar Factura");
		btnCargarFactura.setBounds(180, 500, 137, 23);
		contentPane.add(btnCargarFactura);
		btnCargarFactura.setEnabled(false);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(113, 143, 96, 20);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		
		
		
		btnCalcularIVA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldImporteFacturado.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "COmplete el importe");
					return;
				}
				Double importe = Double.valueOf(textFieldImporteFacturado.getText());
				if(comboBoxIva.getSelectedItem().equals("21%")) {
					Double alicuota = importe*210/1210;
					BigDecimal ali = new BigDecimal(alicuota);
					ali = ali.setScale(2, RoundingMode.HALF_UP);
					lblesteEsEl.setText(String.valueOf(ali));
				} else if(comboBoxIva.getSelectedItem().equals("10,5%")) {
					Double alicuota = importe*105/1105;
					BigDecimal ali = new BigDecimal(alicuota);
					ali = ali.setScale(2, RoundingMode.HALF_UP);
					lblesteEsEl.setText(String.valueOf(ali));
				} else {
					Double alicuota = importe*270/1270;
					BigDecimal ali = new BigDecimal(alicuota);
					ali = ali.setScale(2, RoundingMode.HALF_UP);
					lblesteEsEl.setText(String.valueOf(ali));
				}
				btnCargarFactura.setEnabled(true);
				
			}
		});
		btnCalcularIVA.setBounds(320, 401, 169, 23);
		contentPane.add(btnCalcularIVA);
		
		JRadioButton rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setBounds(145, 199, 54, 23);
		contentPane.add(rdbtnSi);
		rdbtnSi.setEnabled(false);
		
		JRadioButton radioButtonNo = new JRadioButton("No");
		radioButtonNo.setBounds(209, 199, 54, 23);
		contentPane.add(radioButtonNo);
		radioButtonNo.setSelected(true);
		
		ButtonGroup stock = new ButtonGroup();
		stock.add(rdbtnSi);
		stock.add(radioButtonNo);
		
		
		JLabel lblDeseaGenerarStock = new JLabel("Desea generar Stock?");
		lblDeseaGenerarStock.setBounds(10, 203, 203, 14);
		contentPane.add(lblDeseaGenerarStock);
		
		JLabel lblCondicinoDeVenta = new JLabel("Condicion de Venta");
		lblCondicinoDeVenta.setBounds(10, 231, 145, 26);
		contentPane.add(lblCondicinoDeVenta);
		
		JRadioButton rdbtnContado = new JRadioButton("Contado");
		rdbtnContado.setBounds(164, 234, 111, 23);
		contentPane.add(rdbtnContado);
		
		JRadioButton radioButtonCuentaCorriente = new JRadioButton("Cuenta Corriente");
		radioButtonCuentaCorriente.setBounds(276, 233, 161, 23);
		contentPane.add(radioButtonCuentaCorriente);
		
		ButtonGroup groupCondVenta = new ButtonGroup();
		groupCondVenta.add(rdbtnContado);
		groupCondVenta.add(radioButtonCuentaCorriente);
		
		
		lblMoneda.setBounds(126, 441, 24, 14);
		contentPane.add(lblMoneda);
		
		JLabel label = new JLabel("$");
		label.setBounds(197, 405, 12, 14);
		contentPane.add(label);
		
		JLabel labelIVAPregunta = new JLabel("Desea ingresar el IVA de forma manual?");
		labelIVAPregunta.setBounds(10, 256, 250, 26);
		contentPane.add(labelIVAPregunta);
		
		JRadioButton radioButtonManual = new JRadioButton("Si");
		radioButtonManual.setBounds(253, 260, 61, 23);
		contentPane.add(radioButtonManual);
		radioButtonManual.setSelected(true);
		
		JRadioButton radioButtonAutomatico = new JRadioButton("No");
		radioButtonAutomatico.setBounds(320, 258, 68, 23);
		contentPane.add(radioButtonAutomatico);
		
		ButtonGroup grupoCalculoIVA = new ButtonGroup();
		grupoCalculoIVA.add(radioButtonManual);
		grupoCalculoIVA.add(radioButtonAutomatico);
		
		JLabel lblIva = new JLabel("IVA 21% ");
		lblIva.setBounds(10, 332, 77, 14);
		contentPane.add(lblIva);
		
		JLabel lblIva_1 = new JLabel("IVA 10.5% ");
		lblIva_1.setBounds(170, 332, 77, 14);
		contentPane.add(lblIva_1);
		
		JLabel lblIva_2 = new JLabel("IVA 27% ");
		lblIva_2.setBounds(341, 335, 77, 14);
		contentPane.add(lblIva_2);
		
		JLabel lblImporteNeto = new JLabel("Importe Neto 21%");
		lblImporteNeto.setBounds(10, 293, 103, 14);
		contentPane.add(lblImporteNeto);
		
		textFieldNeto21 = new JTextField();
		textFieldNeto21.setBounds(113, 287, 54, 20);
		contentPane.add(textFieldNeto21);
		textFieldNeto21.setColumns(10);
		
		textFieldIVA21 = new JTextField();
		textFieldIVA21.setColumns(10);
		textFieldIVA21.setBounds(113, 329, 54, 20);
		contentPane.add(textFieldIVA21);
		
		JLabel lblImporteNeto_1 = new JLabel("Importe Neto 10.5%");
		lblImporteNeto_1.setBounds(169, 293, 103, 14);
		contentPane.add(lblImporteNeto_1);
		
		JLabel lblImporteNeto_2 = new JLabel("Importe Neto 27%");
		lblImporteNeto_2.setBounds(341, 296, 103, 14);
		contentPane.add(lblImporteNeto_2);
		
		textFieldNeto10 = new JTextField();
		textFieldNeto10.setColumns(10);
		textFieldNeto10.setBounds(276, 287, 54, 20);
		contentPane.add(textFieldNeto10);
		
		textFieldIVA10 = new JTextField();
		textFieldIVA10.setColumns(10);
		textFieldIVA10.setBounds(276, 329, 54, 20);
		contentPane.add(textFieldIVA10);
		
		textFieldNeto27 = new JTextField();
		textFieldNeto27.setColumns(10);
		textFieldNeto27.setBounds(435, 287, 54, 20);
		contentPane.add(textFieldNeto27);
		
		textFieldIVA27 = new JTextField();
		textFieldIVA27.setColumns(10);
		textFieldIVA27.setBounds(435, 329, 54, 20);
		contentPane.add(textFieldIVA27);
		
		JLabel lblMontoTotal = new JLabel("Monto Total $");
		lblMontoTotal.setBounds(297, 441, 89, 14);
		contentPane.add(lblMontoTotal);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(396, 441, 49, 14);
		contentPane.add(lblMonto);
		
		JButton btnCalcularImporteTotal = new JButton("Calcular Monto Total");
		btnCalcularImporteTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double total=0.0;
				if(!textFieldNeto10.getText().isEmpty()) {
					total=total+Double.valueOf(textFieldNeto10.getText());
				}
				if(!textFieldNeto21.getText().isEmpty()) {
					total=total+Double.valueOf(textFieldNeto21.getText());
				}
				if(!textFieldNeto27.getText().isEmpty()) {
					total=total+Double.valueOf(textFieldNeto27.getText());
				}
				if(!textFieldIVA10.getText().isEmpty()) {
					total=total+Double.valueOf(textFieldIVA10.getText());
				}
				if(!textFieldIVA21.getText().isEmpty()) {
					total=total+Double.valueOf(textFieldIVA21.getText());
				}
				if(!textFieldIVA27.getText().isEmpty()) {
					total=total+Double.valueOf(textFieldIVA27.getText());
				}
				lblMonto.setText(String.valueOf(total));
				
				btnCargarFactura.setEnabled(true);
				
			}
		});
		btnCalcularImporteTotal.setBounds(265, 367, 182, 23);
		contentPane.add(btnCalcularImporteTotal);

		
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
				lblMonto.setVisible(false);
				lblMontoTotal.setVisible(false);
				btnCalcularImporteTotal.setVisible(false);
				
				lblIvaFiscal.setVisible(true);
				lblMoneda.setVisible(true);
				lblImporteNetoTotal.setVisible(true);
				label.setVisible(true);
				textFieldImporteFacturado.setVisible(true);
				lblAlcuotaDeIva.setVisible(true);
				comboBoxIva.setVisible(true);
				lblesteEsEl.setVisible(true);
				btnCalcularIVA.setVisible(true);
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
				lblMonto.setVisible(true);
				lblMontoTotal.setVisible(true);
				btnCalcularImporteTotal.setVisible(true);
				
				lblIvaFiscal.setVisible(false);
				lblMoneda.setVisible(false);
				lblImporteNetoTotal.setVisible(false);
				label.setVisible(false);
				textFieldImporteFacturado.setVisible(false);
				lblAlcuotaDeIva.setVisible(false);
				comboBoxIva.setVisible(false);
				lblesteEsEl.setVisible(false);
				btnCalcularIVA.setVisible(false);
				
			}
		});
		
		btnCargarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numerofac=textField.getText()+textFieldNumFactura.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fecha = sdf.format(dateChooser.getDate());
				String puntoDeVenta=textField.getText();
				Double total=0.0;
				conceptoCompra = (String) comboBox.getSelectedItem();
				if(tipoFactura.equals("A")) {
					if(radioButtonManual.isSelected()) {
						Double neto21 = 0.0;
						Double neto10 = 0.0;
						Double neto27 = 0.0;
						Double iva10 = 0.0;
						Double iva21 = 0.0;
						Double iva27 = 0.0;
						total = Double.valueOf(lblMonto.getText());
						if(!textFieldNeto10.getText().isEmpty()) {
							neto10 = Double.valueOf(textFieldNeto10.getText());
						}
						if(!textFieldNeto21.getText().isEmpty()) {
							neto21 = Double.valueOf(textFieldNeto21.getText());
						}
						if(!textFieldNeto27.getText().isEmpty()) {
							neto27 = Double.valueOf(textFieldNeto27.getText());
						}
						if(!textFieldIVA10.getText().isEmpty()) {
							iva10 = Double.valueOf(textFieldIVA10.getText());
						}
						if(!textFieldIVA21.getText().isEmpty()) {
							iva21 = Double.valueOf(textFieldIVA21.getText());
						}
						if(!textFieldIVA27.getText().isEmpty()) {
							iva27 = Double.valueOf(textFieldIVA27.getText());
						}
						String sql="INSERT INTO remito (id_empresa, numero_factura, fecha, id_proveedor, punto_venta, importe_neto21, importe_neto10, importe_neto27, iva_21, iva_10, iva_27,importe_total, concepto, tipo) VALUES (1, "+numerofac+", '"+fecha+"', "+idProveedor+", "+puntoDeVenta+", "+neto21+", "+neto10+", "+neto27+", "+iva21+", "+iva10+", "+iva27+", "+total+", '"+conceptoCompra+"', '"+tipoFactura+"')";
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
						String query="";
						if(comboBoxIva.getSelectedItem().equals("21%")) {
							Double iva21 = Double.valueOf(lblesteEsEl.getText());
							total = Double.valueOf(textFieldImporteFacturado.getText());
							Double neto21 = total-iva21;
							query="INSERT INTO remito (id_empresa, numero_factura, fecha, id_proveedor, punto_venta, importe_neto21, importe_neto10, importe_neto27, iva_21, iva_10, iva_27,importe_total, concepto, tipo) VALUES (1, "+numerofac+", '"+fecha+"', "+idProveedor+", "+puntoDeVenta+", "+neto21+", 0.0, 0.0, "+iva21+", 0.0, 0.0, "+total+", '"+conceptoCompra+"', '"+tipoFactura+"')";
						}
						if(comboBoxIva.getSelectedItem().equals("10.5")) {
							Double iva10 = Double.valueOf(lblesteEsEl.getText());
							total = Double.valueOf(textFieldImporteFacturado.getText());
							Double neto10 = total-iva10;
							query="INSERT INTO remito (id_empresa, numero_factura, fecha, id_proveedor, punto_venta, importe_neto21, importe_neto10, importe_neto27, iva_21, iva_10, iva_27,importe_total, concepto, tipo) VALUES (1, "+numerofac+", '"+fecha+"', "+idProveedor+", "+puntoDeVenta+", 0.0, "+neto10+", 0.0, 0.0, "+iva10+", 0.0, "+total+", '"+conceptoCompra+"', '"+tipoFactura+"')";
						}
						if(comboBoxIva.getSelectedItem().equals("27%")) {
							Double iva27 = Double.valueOf(lblesteEsEl.getText());
							total = Double.valueOf(textFieldImporteFacturado.getText());
							Double neto27 = total-iva27;
							query="INSERT INTO remito (id_empresa, numero_factura, fecha, id_proveedor, punto_venta, importe_neto21, importe_neto10, importe_neto27, iva_21, iva_10, iva_27,importe_total, concepto, tipo) VALUES (1, "+numerofac+", '"+fecha+"', "+idProveedor+", "+puntoDeVenta+", 0.0, 0.0, "+neto27+", 0.0, 0.0, "+iva27+", "+total+", '"+conceptoCompra+"', '"+tipoFactura+"')";
						}
						try {
							Conexion nc = new Conexion();
							Connection conn = nc.conectar();
							Statement instruccion;
							instruccion = conn.createStatement();
							instruccion.executeUpdate(query);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}				
						
					}
				} else {
					total = Double.valueOf(textFieldImporteFacturado.getText());
					String sql="INSERT INTO remito (id_empresa, numero_factura, fecha, id_proveedor, punto_venta, importe_neto21, importe_neto10, importe_neto27, iva_21, iva_10, iva_27,importe_total, concepto, tipo) VALUES (1, "+numerofac+", '"+fecha+"', "+idProveedor+", "+puntoDeVenta+", "+0.0+", "+0.0+", "+0.0+", "+0.0+", "+0.0+", "+0.0+", "+total+", '"+conceptoCompra+"', '"+tipoFactura+"')";
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
				
				}
				if(radioButtonCuentaCorriente.isSelected()) {
					try {
						Conexion nc = new Conexion();
						Connection conn = nc.conectar();
						Statement instruccion;
						instruccion = conn.createStatement();
						ResultSet resultado = instruccion.executeQuery("Select * from cuenta_proveedor where id_proveedor="+idProveedor);
						if(resultado.next()) {
							System.out.println("Ya tiene cuenta, hay que insertar el item directamente y luego actualizamos el saldo de la cuenta proveedor mediante la suma del valor de este comporbante");
							String sql="Insert into itemcuentaproveedor (id_cuenta, saldo, fecha, comprobante, numerocomprobante) values ((select id_cuenta from cuenta_proveedor where id_proveedor="+idProveedor+"), "+total+", '"+fecha+"', 'factura', "+numerofac+")";
							// aca insertamos
							instruccion.executeUpdate("Insert into itemcuentaproveedor (id_cuenta, saldo, fecha, comprobante, numerocomprobante) values ((select id_cuenta from cuenta_proveedor where id_proveedor="+idProveedor+"), "+total+", '"+fecha+"', 'factura', "+numerofac+")");
							// aca actualizamos el salod de cuenta proveedor
							
							instruccion.executeUpdate("Update cuenta_proveedor set saldo = saldo + "+total+" where id_proveedor = "+idProveedor);
						
						} else {
							// creamos la cuenta
							instruccion.executeUpdate("Insert into cuenta_proveedor (id_empresa, id_proveedor, saldo, limite_saldo, fecha_alta) values (1, "+idProveedor+", "+total+", 0,'"+fecha+"')");
							
							String sql="Insert into itemcuentaproveedor (id_cuenta, saldo, fecha, comprobante, numerocomprobante) values ((select id_cuenta from cuenta_proveedor where id_proveedor="+idProveedor+"), "+total+", '"+fecha+"', 'factura', "+numerofac+")";
							// insertamos el registro ( no es necesario actualizar el saldo de la cuenta porque recien lo creamos)
							instruccion.executeUpdate("Insert into itemcuentaproveedor (id_cuenta, saldo, fecha, comprobante, numerocomprobante) values ((select id_cuenta from cuenta_proveedor where id_proveedor="+idProveedor+"), "+total+", '"+fecha+"', 'factura', "+numerofac+")");
							
							System.out.println("No teien cuenta hay que crearla");
						}
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ElegirProveedor ep = new ElegirProveedor(new java.awt.Frame(), true);
					ep.setVisible(true);
					lblNombreProveedor.setText(ep.getNombreProovedor());
					idProveedor = ep.getProvElegido();
					
					if(ep.getCondFiscal()==1) {
						rdbtna.setSelected(true);
						tipoFactura="A";
						btnCalcularIVA.setVisible(true);
						lblesteEsEl.setVisible(true);
						lblIvaFiscal.setVisible(true);	
						lblMoneda.setVisible(true);
					} else {
						rdbtnc.setSelected(true);
						tipoFactura="C";
						btnCalcularIVA.setVisible(false);
						lblesteEsEl.setVisible(false);
						lblIvaFiscal.setVisible(false);
						lblMoneda.setVisible(false);
						lblImporteNetoTotal.setVisible(true);
						textFieldImporteFacturado.setVisible(true);
						btnCargarFactura.setEnabled(true);
						
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
						lblMonto.setVisible(false);
						lblMontoTotal.setVisible(false);
						btnCalcularImporteTotal.setVisible(false);
						labelIVAPregunta.setVisible(false);
						radioButtonNo.setVisible(false);
						rdbtnSi.setVisible(false);
						radioButtonManual.setVisible(false);
						radioButtonAutomatico.setVisible(false);
					}
					
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		});
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
		lblMonto.setVisible(true);
		lblMontoTotal.setVisible(true);
		btnCalcularImporteTotal.setVisible(true);
		
		lblIvaFiscal.setVisible(false);
		lblMoneda.setVisible(false);
		lblImporteNetoTotal.setVisible(false);
		label.setVisible(false);
		textFieldImporteFacturado.setVisible(false);
		lblAlcuotaDeIva.setVisible(false);
		comboBoxIva.setVisible(false);
		lblesteEsEl.setVisible(false);
		btnCalcularIVA.setVisible(false);
		
	}
}
