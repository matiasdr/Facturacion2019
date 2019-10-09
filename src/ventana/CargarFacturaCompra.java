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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;

public class CargarFacturaCompra extends JFrame {
	private JTextField textField;
	private JTextField textFieldNumFactura;
	private JTextField textFieldImporteFacturado;
	private JPanel contentPane;
	JButton btnCalcular = new JButton("Calcular");
	JLabel lblesteEsEl = new JLabel("(Monto)");
	JLabel lblIvaFiscal = new JLabel("IVA FISCAL");
	JLabel lblMoneda = new JLabel("$");

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
		setTitle("Cargar Facturas de Compras");
		setBounds(100, 100, 492, 538);
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
					btnCalcular.setVisible(false);
					lblesteEsEl.setVisible(false);
					lblIvaFiscal.setVisible(false);
					lblMoneda.setVisible(false);
				
							
			}
		});
		
		rdbtnb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnCalcular.setVisible(true);
				lblesteEsEl.setVisible(true);
				lblIvaFiscal.setVisible(true);
				lblMoneda.setVisible(true);
			}
		});
		
		rdbtna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnCalcular.setVisible(true);
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ElegirProveedor ep = new ElegirProveedor(new java.awt.Frame(), true);
					ep.setVisible(true);
					lblNombreProveedor.setText(ep.getNombreProovedor());
					
					if(ep.getCondFiscal()==1) {
						rdbtna.setSelected(true);
						btnCalcular.setVisible(true);
						lblesteEsEl.setVisible(true);
						lblIvaFiscal.setVisible(true);	
						lblMoneda.setVisible(true);
					} else {
						rdbtnc.setSelected(true);
						btnCalcular.setVisible(false);
						lblesteEsEl.setVisible(false);
						lblIvaFiscal.setVisible(false);
						lblMoneda.setVisible(false);
					}
					
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
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
		comboBox.setBounds(135, 170, 85, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Mercaderķa");
		comboBox.addItem("Gastos");
		comboBox.addItem("Bienes de Uso");

		JLabel lblAlcuotaDeIva = new JLabel("Al\u00EDcuota de IVA");
		lblAlcuotaDeIva.setBounds(10, 268, 103, 14);
		contentPane.add(lblAlcuotaDeIva);

		JComboBox comboBoxIva = new JComboBox();
		comboBoxIva.setBounds(135, 264, 89, 22);
		contentPane.add(comboBoxIva);
		comboBoxIva.addItem("21%");
		comboBoxIva.addItem("10,5%");
		comboBoxIva.addItem("27%");
		
		JLabel lblImporteNetoTotal = new JLabel("Importe TOTAL FACTURADO");
		lblImporteNetoTotal.setBounds(10, 311, 180, 14);
		contentPane.add(lblImporteNetoTotal);

		textFieldImporteFacturado = new JTextField();
		textFieldImporteFacturado.setBounds(219, 308, 77, 20);
		contentPane.add(textFieldImporteFacturado);
		textFieldImporteFacturado.setColumns(10);

		
		lblIvaFiscal.setBounds(27, 336, 77, 14);
		contentPane.add(lblIvaFiscal);
		
		lblesteEsEl.setHorizontalAlignment(SwingConstants.LEFT);
		

		
		lblesteEsEl.setBounds(150, 336, 125, 14);
		contentPane.add(lblesteEsEl);

		JButton btnCargarFactura = new JButton("Cargar Factura");
		btnCargarFactura.setBounds(113, 378, 137, 23);
		contentPane.add(btnCargarFactura);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(113, 143, 96, 20);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd-MM-yy");
		
		
		
		btnCalcular.addActionListener(new ActionListener() {
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
			}
		});
		btnCalcular.setBounds(314, 307, 89, 23);
		contentPane.add(btnCalcular);
		
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
		
		
		lblMoneda.setBounds(131, 336, 24, 14);
		contentPane.add(lblMoneda);
		
		JLabel label = new JLabel("$");
		label.setBounds(200, 311, 12, 14);
		contentPane.add(label);

	}
}
