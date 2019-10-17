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
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import java.awt.Color;

public class GenerarRecibo2 extends JFrame {
	private JPanel contentPane;
//	private Jtable tableFac;
	private JTextField tFDatos;
	private JTextField tFImporte;
	
	private Integer id_seleccion;
	private JTable tablaFactPend;
	private JTable table;
	private JTable tablaResumen;
	private int saldoC;
	private String tipoComp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarRecibo2 frame = new GenerarRecibo2();
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
	public GenerarRecibo2() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerarRecibo2.class.getResource("/logos/logo4.png")));
		setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		setTitle("Generar Recibo de Cobro a Cliente");
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Generar Recibo de Cobro ");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblTitulo.setBounds(293, 13, 196, 16);
		contentPane.add(lblTitulo);
		
		JLabel lblSelecCli = new JLabel("Seleccione el Cliente :");
		lblSelecCli.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSelecCli.setBounds(26, 68, 153, 16);
		contentPane.add(lblSelecCli);
		
		JLabel lblNombreClie = new JLabel("");
		lblNombreClie.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNombreClie.setBounds(378, 59, 276, 25);
		contentPane.add(lblNombreClie);
		
		JLabel lblSaldo = new JLabel("Saldo :");
		lblSaldo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSaldo.setBounds(658, 311, 56, 16);
		contentPane.add(lblSaldo);
		
		JLabel lblimporteSaldo = new JLabel("");
		lblimporteSaldo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblimporteSaldo.setBounds(726, 302, 85, 25);
		contentPane.add(lblimporteSaldo);
		
		DefaultTableModel tablaModelo = new DefaultTableModel(0,4);
		Object[] fila = new Object[4];
		fila[0]= "Tipo Comprobante";
		fila[1]= "Numero";
		fila[2]= "Saldo";
		fila[3]= "Importe";
		tablaModelo.addRow(fila);
		
		tablaFactPend = new JTable(tablaModelo);
		tablaFactPend.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tablaFactPend.setFont(new Font("Times New Roman", Font.BOLD, 13));
		tablaFactPend.setBounds(439, 139, 372, 150);
		contentPane.add(tablaFactPend);
		
		
		
		JButton btnIniBus = new JButton("Iniciar Busqueda");
		btnIniBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirCliente ec;
				try {
					ec = new ElegirCliente(new java.awt.Frame(), true);
					ec.setVisible(true);
					lblNombreClie.setText(ec.getNombreCliente());
					id_seleccion= ec.getClienElegido();
          //		lblNombreClie.setText(String.valueOf(id_seleccion));
					
					
					
					Conexion nc = new Conexion();
					Connection conec = nc.conectar();
					Statement instruccion;
					try {
						instruccion = conec.createStatement();
						ResultSet resultado = instruccion.executeQuery("Select it.comprobante, it.numerocomprobante, it.saldo, it.debe_haber from itemcuentacliente it \r\n" + 
								"inner join cuenta_cliente cc on it.id_cuenta=cc.id_cuenta \r\n" + 
								"inner join cliente c on c.id_cliente= cc.id_cliente where c.id_cliente =" + id_seleccion);
						
						while(resultado.next()) {
							Object[] linea = new Object[4];
							linea[0]= resultado.getString("comprobante");
							linea[1]= resultado.getInt("numerocomprobante");
							linea[2]= resultado.getInt("saldo");
							linea[3] = resultado.getString("debe_haber").trim();
							tablaModelo.addRow(linea);
						
							
							if(linea[3].toString().equals("H")) {
							
								saldoC -= resultado.getInt("saldo");
							}
							else
								if(linea[3].toString().equals("D"))
							{
								saldoC += resultado.getInt("saldo");
							}
							
							
						}								
							if(saldoC >= 0) {
								lblimporteSaldo.setText(String.valueOf(saldoC));
								lblimporteSaldo.setForeground(Color.BLACK);
							}
							else
							{
								if(saldoC < 0) {
									lblimporteSaldo.setText(String.valueOf(saldoC));
									lblimporteSaldo.setForeground(Color.RED);
							    }
							}
        				
											
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					nc.desconectar();
					
					
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private int parceInt(JLabel lblimporteSaldo) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		btnIniBus.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnIniBus.setBounds(191, 64, 137, 25);
		contentPane.add(btnIniBus);
		
			
		
// = new JTable();
//		table.setModel(tablaModelo);
//		table.setBounds(12, 48, 517, 242);
//		getContentPane().add(table);
		
		
		
	
		
		
		
		JLabel lblDatos = new JLabel("Datos del Comprobante :");
		lblDatos.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDatos.setBounds(26, 124, 171, 16);
		contentPane.add(lblDatos);
		
		tFDatos = new JTextField();
		tFDatos.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tFDatos.setColumns(10);
		tFDatos.setBounds(196, 121, 211, 22);
		contentPane.add(tFDatos);
		
		JLabel lblFacPend = new JLabel("Listado de Movimientos");
		lblFacPend.setForeground(Color.BLACK);
		lblFacPend.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblFacPend.setBounds(537, 110, 171, 16);
		contentPane.add(lblFacPend);
		
		JLabel lblResumen = new JLabel("Resumen de Operacion");
		lblResumen.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblResumen.setBounds(378, 328, 164, 16);
		contentPane.add(lblResumen);
		
		JLabel lblImpoParc = new JLabel("Importe de Pago Parcial :");
		lblImpoParc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblImpoParc.setBounds(26, 273, 171, 16);
		contentPane.add(lblImpoParc);
		
		tFImporte = new JTextField();
		tFImporte.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tFImporte.setColumns(10);
		tFImporte.setBounds(196, 270, 116, 22);
		contentPane.add(tFImporte);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCalcular.setBounds(174, 453, 97, 25);
		contentPane.add(btnCalcular);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnConfirmar.setBounds(427, 453, 97, 25);
		contentPane.add(btnConfirmar);
		
		JButton btnEmitirRec = new JButton("Emitir Recibo");
		btnEmitirRec.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnEmitirRec.setBounds(645, 453, 123, 25);
		contentPane.add(btnEmitirRec);
		
		JLabel lblPagoParc = new JLabel("\u00BFRealiza Pago Parcial :");
		lblPagoParc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPagoParc.setBounds(26, 230, 153, 16);
		contentPane.add(lblPagoParc);
		
		JRadioButton rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setFont(new Font("Times New Roman", Font.BOLD, 13));
		rdbtnSi.setBounds(191, 227, 41, 25);
		contentPane.add(rdbtnSi);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		rdbtnNo.setBounds(236, 227, 50, 25);
		contentPane.add(rdbtnNo);
		
		ButtonGroup importe = new ButtonGroup();
		importe.add(rdbtnSi);
		importe.add(rdbtnNo);
		rdbtnSi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnSi.isSelected()) {
					tFImporte.setEnabled(true);
				}
			}
		});
		
		rdbtnNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNo.isSelected()) {
					tFImporte.setEnabled(false);
				}
			}
		});
		
		JLabel lblMedioPago = new JLabel("Medio de Pago :");
		lblMedioPago.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMedioPago.setBounds(26, 177, 116, 25);
		contentPane.add(lblMedioPago);
		
		JComboBox comboBoxMedPag = new JComboBox();
		comboBoxMedPag.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboBoxMedPag.setBounds(148, 178, 211, 22);
		contentPane.add(comboBoxMedPag);
		comboBoxMedPag.addItem("Efectivo");
		comboBoxMedPag.addItem("Tarjeta de Credito/Debito");
		comboBoxMedPag.addItem("Transferencia");
		comboBoxMedPag.addItem("Cheque");
		
		
		
		table = new JTable();
		table.setBounds(756, 440, -581, -95);
		contentPane.add(table);
		
		tablaResumen = new JTable();
		tablaResumen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tablaResumen.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tablaResumen.setBounds(178, 346, 590, 94);
		contentPane.add(tablaResumen);
		
		
		
	
	
	}
}
