package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import com.toedter.calendar.JDateChooser;

import conexion.Conexion;

import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ListadoIVACompras extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private Double totalNeto0=0.0;
	private Double totalNeto21=0.0;
	private Double totalNeto27=0.0;
	private Double totalNeto10=0.0;
	private Double totalIVA21=0.0;
	private Double totalIVA27=0.0;
	private Double totalIVA10=0.0;
	private Double totalIVA0=0.0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoIVACompras frame = new ListadoIVACompras();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListadoIVACompras() {
		setTitle("Lista de IVA Compras");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoIVACompras.class.getResource("/logos/logo4.png")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Object[] encabezado = new Object[9];
		encabezado[0] = "Fecha";
		encabezado[1] = "N° Comprobante";
		encabezado[2] = "Tipo";
		encabezado[3] = "Razon Social";
		encabezado[4] = "CUIT";
		encabezado[5] = "Alicuota IVA";
		encabezado[6] = "Importe Neto (sin Iva)";
		encabezado[7] = "Iva Debito Fiscal";
		encabezado[8] = "Importe Total";
		
		
		DefaultTableModel tablaModelo= new DefaultTableModel(encabezado, 0);
		
		JLabel lblSeleccioneElPerodo = new JLabel("Periodo :");
		lblSeleccioneElPerodo.setBounds(10, 11, 59, 14);
		contentPane.add(lblSeleccioneElPerodo);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(68, 11, 43, 14);
		contentPane.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(264, 11, 49, 14);
		contentPane.add(lblHasta);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(111, 5, 100, 20);
		contentPane.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(323, 5, 93, 20);
		contentPane.add(dateChooser_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(708, 7, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 842, 326);
		contentPane.add(scrollPane);
		
		table = new JTable(tablaModelo);
		scrollPane.setViewportView(table);
		
		JLabel lblAlicuota = new JLabel("21%");
		lblAlicuota.setBounds(32, 401, 37, 14);
		contentPane.add(lblAlicuota);
		
		JLabel lblAlicuota_1 = new JLabel("27%");
		lblAlicuota_1.setBounds(32, 426, 28, 14);
		contentPane.add(lblAlicuota_1);
		
		JLabel lblAlicuota_2 = new JLabel("10.5%");
		lblAlicuota_2.setBounds(32, 451, 37, 14);
		contentPane.add(lblAlicuota_2);
		
		JLabel lblNewLabel = new JLabel("NETO");
		lblNewLabel.setBounds(68, 401, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(214, 401, 49, 14);
		contentPane.add(lblIva);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(332, 426, 49, 14);
		contentPane.add(lblTotal);
		
		JLabel lblNeto21 = new JLabel("New label");
		lblNeto21.setBounds(130, 401, 74, 14);
		contentPane.add(lblNeto21);
		
		JLabel lblNeto27 = new JLabel("New label");
		lblNeto27.setBounds(130, 426, 74, 14);
		contentPane.add(lblNeto27);
		
		JLabel lblNeto10 = new JLabel("New label");
		lblNeto10.setBounds(130, 451, 74, 14);
		contentPane.add(lblNeto10);
		
		JLabel lblIVA21 = new JLabel("New label");
		lblIVA21.setBounds(264, 401, 58, 14);
		contentPane.add(lblIVA21);
		
		JLabel lblIVA27 = new JLabel("New label");
		lblIVA27.setBounds(264, 426, 58, 14);
		contentPane.add(lblIVA27);
		
		JLabel lblIVA10 = new JLabel("New label");
		lblIVA10.setBounds(264, 451, 58, 14);
		contentPane.add(lblIVA10);
		
		JLabel lblTotal21 = new JLabel("New label");
		lblTotal21.setBounds(396, 401, 74, 14);
		contentPane.add(lblTotal21);
		
		JLabel lblTotal27 = new JLabel("New label");
		lblTotal27.setBounds(396, 426, 74, 14);
		contentPane.add(lblTotal27);
		
		JLabel lblTotal10 = new JLabel("New label");
		lblTotal10.setBounds(396, 451, 74, 14);
		contentPane.add(lblTotal10);
		
		JLabel label = new JLabel("NETO");
		label.setBounds(68, 426, 49, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("NETO");
		label_1.setBounds(68, 451, 49, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("IVA");
		label_2.setBounds(214, 426, 49, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("IVA");
		label_3.setBounds(214, 451, 49, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("TOTAL");
		label_4.setBounds(332, 401, 49, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("TOTAL");
		label_5.setBounds(332, 451, 49, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("0%");
		label_6.setBounds(32, 376, 37, 14);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("NETO");
		label_7.setBounds(68, 378, 49, 14);
		contentPane.add(label_7);
		
		JLabel lblNeto0 = new JLabel("New label");
		lblNeto0.setBounds(130, 378, 74, 14);
		contentPane.add(lblNeto0);
		
		JLabel lblIva0 = new JLabel("New label");
		lblIva0.setBounds(264, 378, 58, 14);
		contentPane.add(lblIva0);
		
		JLabel lblTotal0 = new JLabel("New label");
		lblTotal0.setBounds(396, 378, 74, 14);
		contentPane.add(lblTotal0);
		
		JLabel label_8 = new JLabel("IVA");
		label_8.setBounds(214, 378, 49, 14);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("TOTAL");
		label_9.setBounds(332, 378, 49, 14);
		contentPane.add(label_9);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Conexion nc = new Conexion();
					Connection conn = nc.conectar();
					Statement instruccion;
					instruccion = conn.createStatement();
					ResultSet resultado = instruccion.executeQuery("select * from remito R join proveedor P on P.id_proveedor= R.id_proveedor");
					while(resultado.next()) {
						if(resultado.getString("tipo").equals("B") || resultado.getString("tipo").equals("C")) {
							Object[] fila = new Object[9];
							fila[0] = resultado.getString("fecha"); //"Fecha";
							fila[1] = resultado.getInt("numero_factura"); //"N° Comprobante";
							fila[2] = resultado.getString("tipo"); // tipo de factura
							fila[3] = resultado.getString("nombre");  //"Razon Social";
							fila[4] = resultado.getString("cuilcuit"); //"CUIT";
							fila[5] = "--"; //"Alicuota IVA";
							fila[6] = resultado.getDouble("importe_total");//"Importe Neto (sin Iva)";
							fila[7] = 0.0; //"Iva Debito Fiscal";
							fila[8] = resultado.getDouble("importe_total");//"Importe Total";
							tablaModelo.addRow(fila);
							totalNeto0=totalNeto0+(Double) fila[8];
							
						} else {
							if(resultado.getDouble("iva_21")!=0.0) {
								Object[] fila = new Object[9];
								fila[0] = resultado.getString("fecha"); //"Fecha";
								fila[1] = resultado.getInt("numero_factura"); //"N° Comprobante";
								fila[2] = resultado.getString("tipo"); // tipo de factura
								fila[3] = resultado.getString("nombre");  //"Razon Social";
								fila[4] = resultado.getString("cuilcuit"); //"CUIT";
								fila[5] = "21%"; //"Alicuota IVA";
								fila[6] = resultado.getDouble("importe_neto21");//"Importe Neto (sin IVA)";
								fila[7] = resultado.getDouble("iva_21"); //"Iva Debito Fiscal";
								fila[8] = (Double) fila[6] + (Double) fila [7];//"Importe Total";
								tablaModelo.addRow(fila);
								totalNeto21=totalNeto21+(Double) fila[6];
								totalIVA21=totalIVA21+(Double) fila[7];
							}
							if(resultado.getDouble("iva_27")!=0.0) {
								Object[] fila = new Object[9];
								fila[0] = resultado.getString("fecha"); //"Fecha";
								fila[1] = resultado.getInt("numero_factura"); //"N° Comprobante";
								fila[2] = resultado.getString("tipo"); // tipo de factura
								fila[3] = resultado.getString("nombre");  //"Razon Social";
								fila[4] = resultado.getString("cuilcuit"); //"CUIT";
								fila[5] = "27%"; //"Alicuota IVA";
								fila[6] = resultado.getDouble("importe_neto27");//"Importe Neto (sin IVA)";
								fila[7] = resultado.getDouble("iva_27"); //"Iva Debito Fiscal";
								fila[8] = (Double) fila[6] + (Double) fila [7];//"Importe Total";
								tablaModelo.addRow(fila);
								totalNeto27=totalNeto27+(Double) fila[6];
								totalIVA27=totalIVA27+(Double) fila[7];
							}
							if(resultado.getDouble("iva_10")!=0.0) {
								Object[] fila = new Object[9];
								fila[0] = resultado.getString("fecha"); //"Fecha";
								fila[1] = resultado.getInt("numero_factura"); //"N° Comprobante";
								fila[2] = resultado.getString("tipo"); // tipo de factura
								fila[3] = resultado.getString("nombre");  //"Razon Social";
								fila[4] = resultado.getString("cuilcuit"); //"CUIT";
								fila[5] = "10.5%"; //"Alicuota IVA";
								fila[6] = resultado.getDouble("importe_neto10");//"Importe Neto (sin IVA)";
								fila[7] = resultado.getDouble("iva_10"); //"Iva Debito Fiscal";
								fila[8] = (Double) fila[6] + (Double) fila [7];//"Importe Total";
								tablaModelo.addRow(fila);
								totalNeto10=totalNeto10+(Double) fila[6];
								totalIVA10=totalIVA10+(Double) fila[7];
							}
							
						}
						
						
					}
					
					lblIVA21.setText(String.valueOf(totalIVA21));
					lblIVA27.setText(String.valueOf(totalIVA27));
					lblIVA10.setText(String.valueOf(totalIVA10));
					lblNeto21.setText(String.valueOf(totalNeto21));
					lblNeto27.setText(String.valueOf(totalNeto27));
					lblNeto10.setText(String.valueOf(totalNeto10));
					lblTotal21.setText(String.valueOf(totalIVA21+totalNeto21));
					lblTotal27.setText(String.valueOf(totalIVA27+totalNeto27));
					lblTotal10.setText(String.valueOf(totalIVA10+totalNeto10));
					lblIva0.setText(String.valueOf(totalIVA0));
					lblNeto0.setText(String.valueOf(totalNeto0));
					lblTotal0.setText(String.valueOf(totalNeto0));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
