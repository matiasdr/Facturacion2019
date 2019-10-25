package ventana;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.awt.event.ActionEvent;

public class CargarFacturaVenta extends JFrame {
	private JPanel contentPane;
	
	private JTextField textFieldNeto21;
	private JTextField textFieldIVA21;
	private JTextField textFieldNeto10;
	private JTextField textFieldIVA10;
	private JTextField textFieldNeto27;
	private JTextField textFieldIVA27;
	private Integer idProveedor;
	private String tipoFactura;
	private String conceptoCompra;
	private JTextField textFieldTotalFacturado;
	private JTextField textFieldPuntodeVenta;
	private JTextField textFieldNumeroFactura;
	private Integer idCliente;
	private String nombreCliente;
	private Integer condicionFiscalCliente;
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
		radioButtonManual.setSelected(true);
		radioButtonManual.setBounds(265, 206, 61, 23);
		contentPane.add(radioButtonManual);
		
		JRadioButton radioButtonAutomatico = new JRadioButton("No");
		radioButtonAutomatico.setBounds(332, 204, 68, 23);
		contentPane.add(radioButtonAutomatico);
		
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
		dateChooser.setBounds(123, 138, 70, 20);
		contentPane.add(dateChooser);
		
		textFieldPuntodeVenta = new JTextField();
		textFieldPuntodeVenta.setBounds(123, 103, 96, 20);
		contentPane.add(textFieldPuntodeVenta);
		textFieldPuntodeVenta.setColumns(10);
		
		textFieldNumeroFactura = new JTextField();
		textFieldNumeroFactura.setBounds(367, 103, 96, 20);
		contentPane.add(textFieldNumeroFactura);
		textFieldNumeroFactura.setColumns(10);
		textFieldNumeroFactura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				if(Character.isLetter(validar)) {
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
		lblNumeroDeFactura.setBounds(247, 106, 110, 14);
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
		
		JLabel lblNombreCliente = new JLabel("--");
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

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
}
