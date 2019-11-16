package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.*;

import conexion.Conexion;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.Color;


public class ResumenProveedor extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	JLabel lblNombreDelCliente = new JLabel("--");
	private JTable table;
	private Integer idProveedor=0;
	private JTable tableContado;
	
	private String tipocomp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ResumenProveedor frame = new ResumenProveedor();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResumenProveedor() {
		setTitle("Resumen de Cuenta Proveedores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ResumenProveedor.class.getResource("/logos/logo4.png")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 761);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(10, 165, 691, 239);
		contentPanel.add(scrollPane);
		
		JLabel label = new JLabel("Seleccione el periodo :");
		label.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label.setBounds(10, 15, 152, 18);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("Desde: ");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_1.setBounds(174, 19, 49, 14);
		contentPanel.add(label_1);
		
		JDateChooser dateChooserDesde = new JDateChooser();
		dateChooserDesde.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dateChooserDesde.setBounds(223, 11, 114, 22);
		contentPanel.add(dateChooserDesde);
		dateChooserDesde.setDateFormatString("yyyy-MM-dd");
		dateChooserDesde.setDate(new Date());
		
		JLabel label_2 = new JLabel("Hasta: ");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_2.setBounds(373, 19, 49, 14);
		contentPanel.add(label_2);
		
		JDateChooser dateChooserHasta = new JDateChooser();
		dateChooserHasta.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dateChooserHasta.setBounds(427, 11, 122, 22);
		contentPanel.add(dateChooserHasta);
		dateChooserHasta.setDateFormatString("yyyy-MM-dd");
		dateChooserHasta.setDate(new Date());
		
		
		
		JLabel label_3 = new JLabel("Movimientos en Cuenta Corriente");
		label_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		label_3.setBounds(220, 137, 248, 16);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("Movimientos en Cuenta de Contado");
		label_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		label_4.setBounds(222, 475, 261, 16);
		contentPanel.add(label_4);
		
		JScrollPane scrollPaneContado = new JScrollPane();
		scrollPaneContado.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneContado.setBounds(36, 504, 636, 176);
		contentPanel.add(scrollPaneContado);
		
		Object[] encabezado = new Object[6];
		encabezado[0] = "Fecha";
		encabezado[1] = "Tipo Comprobante";
		encabezado[2] = "Numero Comprobante";
		encabezado[3] = "Debe";
		encabezado[4] = "Haber";
		encabezado[5] = "Importe";

		
		Object[] encabezadocdo = new Object[3];
		encabezadocdo[0] = "Tipo Comprobante";
		encabezadocdo[1] = "Numero Comprobante";
		encabezadocdo[2] = "Importe";

		DefaultTableModel tablaModeloContado = new DefaultTableModel(encabezadocdo, 0);
		
		tableContado = new JTable(tablaModeloContado);
		scrollPaneContado.setViewportView(tableContado);

		DefaultTableModel modeloTabla = new DefaultTableModel(encabezado, 0);

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);
		
		JLabel lblSaldo = new JLabel("Saldo :  $");
		lblSaldo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblSaldo.setBounds(542, 417, 78, 16);
		contentPanel.add(lblSaldo);
		
		JLabel lblSaldopro = new JLabel("New label");
		lblSaldopro.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldopro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblSaldopro.setBounds(613, 414, 86, 23);
		contentPanel.add(lblSaldopro);



		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSeleccioneElProveedor = new JLabel("Seleccione el Proveedor :");
			lblSeleccioneElProveedor.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblSeleccioneElProveedor.setBounds(10, 78, 166, 23);
			contentPanel.add(lblSeleccioneElProveedor);
		}
		{
			lblNombreDelCliente.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNombreDelCliente.setBounds(335, 78, 285, 22);
			contentPanel.add(lblNombreDelCliente);
		}
		{
			JButton btnBuscarProveedor = new JButton("Buscar Proveedor");
			btnBuscarProveedor.setFont(new Font("Times New Roman", Font.BOLD, 12));
			btnBuscarProveedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ElegirProveedor ep;
					try {
						ep = new ElegirProveedor(new java.awt.Frame(), true);
						ep.setVisible(true);
						lblNombreDelCliente.setText(ep.getNombreProovedor());
						idProveedor = ep.getProvElegido();
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}
		});
			btnBuscarProveedor.setBounds(167, 79, 142, 23);
			contentPanel.add(btnBuscarProveedor);
		}
		
		JButton btnListarMovimientos = new JButton("Listar Movimientos");
		btnListarMovimientos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnListarMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fechaDesde;
				String fechaHasta;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				fechaDesde = sdf.format(dateChooserDesde.getDate());
				fechaHasta = sdf.format(dateChooserHasta.getDate());
			    int saldopro = 0;
				
				// Borramos lo que tenga la tabla de cuenta corriente y luego insertamos la busqueda
				while(modeloTabla.getRowCount()>0) {
					modeloTabla.removeRow(modeloTabla.getRowCount()-1);
				}
				
				try {
					Conexion nc = new Conexion();
					Connection conn = nc.conectar();
					Statement instruccion = conn.createStatement();
					ResultSet resultado = instruccion.executeQuery("select * from itemcuentaproveedor join cuenta_proveedor on cuenta_proveedor.id_cuenta = itemcuentaproveedor.id_cuenta where fecha>=cast('"+fechaDesde+"' as date) and fecha<=cast('"+fechaHasta+"' as date) and id_proveedor = "+idProveedor);

					while(resultado.next()) {
						Object[] fila = new Object[6];
						fila[0] = resultado.getDate("fecha");
						fila[1] = resultado.getString("comprobante");
						fila[2] = resultado.getString("numerocomprobante");
						tipocomp = resultado.getString("comprobante").trim();
						
						if(tipocomp.toString().equals("factura"))
						{
							fila[3]=resultado.getInt("saldo");
							fila[4]= 0.00;
							saldopro = saldopro+resultado.getInt("saldo");
						}
						else
						{
							fila[3]= 0.00;
							fila[4]= resultado.getInt("saldo");
							saldopro = saldopro-resultado.getInt("saldo");
						}
						fila[5]= saldopro;
						lblSaldopro.setText(String.valueOf(saldopro));								
						modeloTabla.addRow(fila);
					}
					
					while(tablaModeloContado.getRowCount()>0) {
						tablaModeloContado.removeRow(tablaModeloContado.getRowCount()-1);
					}
					
					String sql = "Select * from remito where id_proveedor = "+idProveedor+" and condicion_compra like 'contado'";
		//			System.out.println(sql);
					resultado = instruccion.executeQuery(sql);
					
					while(resultado.next()) {
						Object[] fila = new Object[3];
						fila[0] = "Factura"; //resultado.getString("numero_factura");
						fila[1] = resultado.getString("numero_factura");
						fila[2] = resultado.getDouble("importe_total");
						tablaModeloContado.addRow(fila);
					}
					

					nc.desconectar();
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnListarMovimientos.setBounds(515, 114, 157, 23);
		contentPanel.add(btnListarMovimientos);
		
	

	}
}
