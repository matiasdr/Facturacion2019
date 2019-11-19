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
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private JTable tableServicios;
	private JTable tableBienesUso;
	private JTable tableCompra;
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
		setBounds(100, 100, 878, 615);
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
		encabezado[7] = "Iva Credito Fiscal";
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
		dateChooser.setDate(new Date());
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(323, 5, 93, 20);
		contentPane.add(dateChooser_1);
		dateChooser_1.setDate(new Date());
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(708, 7, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 842, 326);
		contentPane.add(scrollPane);
		
		table = new JTable(tablaModelo);
		scrollPane.setViewportView(table);
		
		Object[] encabServ = new Object[4];
		encabServ[0] = "Alícuota";
		encabServ[1] = "IVA";
		encabServ[2] = "NETO";
		encabServ[3] = "TOTAL";
		
		DefaultTableModel modelServicio = new DefaultTableModel(encabServ, 0);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(607, 405, 245, 110);
		contentPane.add(scrollPane_1);
		
		tableServicios = new JTable(modelServicio);
		scrollPane_1.setViewportView(tableServicios);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(289, 405, 262, 110);
		contentPane.add(scrollPane_2);
		
		DefaultTableModel modelBienesUso = new DefaultTableModel(encabServ, 0);
		
		tableBienesUso = new JTable(modelBienesUso);
		scrollPane_2.setViewportView(tableBienesUso);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 405, 239, 110);
		contentPane.add(scrollPane_3);
		
		DefaultTableModel modelCompra = new DefaultTableModel(encabServ, 0);
		
		tableCompra = new JTable(modelCompra);
		scrollPane_3.setViewportView(tableCompra);
		
		JLabel lblTotalIvaCredito = new JLabel("Total IVA Credito Fiscal");
		lblTotalIvaCredito.setBounds(289, 538, 146, 14);
		contentPane.add(lblTotalIvaCredito);
		
		JLabel lblIVATOTALTOTAL = new JLabel("--");
		lblIVATOTALTOTAL.setBounds(458, 538, 93, 14);
		contentPane.add(lblIVATOTALTOTAL);
		
		JLabel lblCompraDeBienes = new JLabel("Compra de Bienes");
		lblCompraDeBienes.setBounds(85, 378, 113, 14);
		contentPane.add(lblCompraDeBienes);
		
		JLabel lblBienesDeUso = new JLabel("Bienes de Uso");
		lblBienesDeUso.setBounds(367, 378, 121, 14);
		contentPane.add(lblBienesDeUso);
		
		JLabel lblServicios = new JLabel("Servicios");
		lblServicios.setBounds(676, 378, 77, 14);
		contentPane.add(lblServicios);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while(tablaModelo.getRowCount()>0) {
					tablaModelo.removeRow(tablaModelo.getRowCount()-1);
				}
				while(modelServicio.getRowCount()>0) {
					modelServicio.removeRow(modelServicio.getRowCount()-1);
				}
				while(modelBienesUso.getRowCount()>0) {
					modelBienesUso.removeRow(modelBienesUso.getRowCount()-1);
				}
				while(modelCompra.getRowCount()>0) {
					modelCompra.removeRow(modelCompra.getRowCount()-1);
				}
				totalNeto0=0.0;
				totalNeto21=0.0;
				totalNeto27=0.0;
				totalNeto10=0.0;
				totalIVA21=0.0;
				totalIVA27=0.0;
				totalIVA10=0.0;
				totalIVA0=0.0;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fechaDesde = sdf.format(dateChooser.getDate());
				String fechaHasta = sdf.format(dateChooser_1.getDate());
				
				try {
					Conexion nc = new Conexion();
					Connection conn = nc.conectar();
					Statement instruccion;
					instruccion = conn.createStatement();
					ResultSet resultado = instruccion.executeQuery("select * from remito R join proveedor P on P.id_proveedor= R.id_proveedor where fecha>=cast('"+fechaDesde+"' as date) and fecha<=cast('"+fechaHasta+"' as date)");
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
				/*	
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
					lblTotal0.setText(String.valueOf(totalNeto0));*/
					
					resultado = instruccion.executeQuery("select sum(iva_21) as 'iva21', sum(iva_10) as 'iva10', sum(iva_27) as 'iva27', sum(importe_neto21) as 'neto21', sum(importe_neto10) as 'neto10', sum(importe_neto27) as 'neto27' from remito where concepto = 'Servicios' and fecha>=cast('"+fechaDesde+"' as date) and fecha<=cast('"+fechaHasta+"' as date)");
					while(resultado.next()) {
						Object[] fila = new Object[4];
						fila[0]="21%";
						fila[1]=resultado.getDouble("iva21");
						fila[2]=resultado.getDouble("neto21");
						fila[3]=(Double)fila[1]+(Double)fila[2];
						modelServicio.addRow(fila);
						
						fila[0]="10%";
						fila[1]=resultado.getDouble("iva10");
						fila[2]=resultado.getDouble("neto10");
						fila[3]=(Double)fila[1]+(Double)fila[2];
						modelServicio.addRow(fila);
						
						fila[0]="27%";
						fila[1]=resultado.getDouble("iva27");
						fila[2]=resultado.getDouble("neto27");
						fila[3]=(Double)fila[1]+(Double)fila[2];
						modelServicio.addRow(fila);
						
						fila[0]= "TOTALES";
						fila[1]=(Double)tableServicios.getValueAt(0, 1) + (Double)tableServicios.getValueAt(1, 1)+ (Double)tableServicios.getValueAt(2, 1);
						fila[2]=(Double)tableServicios.getValueAt(0, 2) + (Double)tableServicios.getValueAt(1, 2)+ (Double)tableServicios.getValueAt(2, 2);
						fila[3]=(Double)tableServicios.getValueAt(0, 3) + (Double)tableServicios.getValueAt(1, 3)+ (Double)tableServicios.getValueAt(2, 3);
						modelServicio.addRow(fila);
					}
					
					
					resultado = instruccion.executeQuery("select sum(iva_21) as 'iva21', sum(iva_10) as 'iva10', sum(iva_27) as 'iva27', sum(importe_neto21) as 'neto21', sum(importe_neto10) as 'neto10', sum(importe_neto27) as 'neto27' from remito where concepto = 'Bienes de Uso' and fecha>=cast('"+fechaDesde+"' as date) and fecha<=cast('"+fechaHasta+"' as date)");
					while(resultado.next()) {
						Object[] fila = new Object[4];
						fila[0]="21%";
						fila[1]=resultado.getDouble("iva21");
						fila[2]=resultado.getDouble("neto21");
						fila[3]=(Double)fila[1]+(Double)fila[2];
						modelBienesUso.addRow(fila);
						
						fila[0]="10%";
						fila[1]=resultado.getDouble("iva10");
						fila[2]=resultado.getDouble("neto10");
						fila[3]=(Double)fila[1]+(Double)fila[2];
						modelBienesUso.addRow(fila);
						
						fila[0]="27%";
						fila[1]=resultado.getDouble("iva27");
						fila[2]=resultado.getDouble("neto27");
						fila[3]=(Double)fila[1]+(Double)fila[2];
						modelBienesUso.addRow(fila);
						
						fila[0]= "TOTALES";
						fila[1]=(Double)tableBienesUso.getValueAt(0, 1) + (Double)tableBienesUso.getValueAt(1, 1)+ (Double)tableBienesUso.getValueAt(2, 1);
						fila[2]=(Double)tableBienesUso.getValueAt(0, 2) + (Double)tableBienesUso.getValueAt(1, 2)+ (Double)tableBienesUso.getValueAt(2, 2);
						fila[3]=(Double)tableBienesUso.getValueAt(0, 3) + (Double)tableBienesUso.getValueAt(1, 3)+ (Double)tableBienesUso.getValueAt(2, 3);
						modelBienesUso.addRow(fila);
					}
					
					resultado = instruccion.executeQuery("select sum(iva_21) as 'iva21', sum(iva_10) as 'iva10', sum(iva_27) as 'iva27', sum(importe_neto21) as 'neto21', sum(importe_neto10) as 'neto10', sum(importe_neto27) as 'neto27' from remito where concepto = 'Compra de Bienes' and fecha>=cast('"+fechaDesde+"' as date) and fecha<=cast('"+fechaHasta+"' as date)");
					while(resultado.next()) {
						Object[] fila = new Object[4];
						fila[0]="21%";
						fila[1]=resultado.getDouble("iva21");
						fila[2]=resultado.getDouble("neto21");
						fila[3]=(Double)fila[1]+(Double)fila[2];
						modelCompra.addRow(fila);
						
						fila[0]="10%";
						fila[1]=resultado.getDouble("iva10");
						fila[2]=resultado.getDouble("neto10");
						fila[3]=(Double)fila[1]+(Double)fila[2];
						modelCompra.addRow(fila);
						
						fila[0]="27%";
						fila[1]=resultado.getDouble("iva27");
						fila[2]=resultado.getDouble("neto27");
						fila[3]=(Double)fila[1]+(Double)fila[2];
						modelCompra.addRow(fila);
						
						fila[0]= "TOTALES";
						fila[1]=(Double)tableCompra.getValueAt(0, 1) + (Double)tableCompra.getValueAt(1, 1)+ (Double)tableCompra.getValueAt(2, 1);
						fila[2]=(Double)tableCompra.getValueAt(0, 2) + (Double)tableCompra.getValueAt(1, 2)+ (Double)tableCompra.getValueAt(2, 2);
						fila[3]=(Double)tableCompra.getValueAt(0, 3) + (Double)tableCompra.getValueAt(1, 3)+ (Double)tableCompra.getValueAt(2, 3);
						modelCompra.addRow(fila);
					}
					Double totaliva=(Double)tableCompra.getValueAt(3, 1)+(Double)tableBienesUso.getValueAt(3, 1)+(Double)tableServicios.getValueAt(3, 1);
					lblIVATOTALTOTAL.setText(String.valueOf(totaliva));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
