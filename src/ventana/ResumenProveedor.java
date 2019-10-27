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


public class ResumenProveedor extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	JLabel lblNombreDelCliente = new JLabel("--");
	private JTable table;
	private Integer idProveedor=0;
	private JTable tableContado;
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
		setBounds(100, 100, 604, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 102, 542, 239);
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
		dateChooserDesde.setBounds(223, 11, 114, 22);
		contentPanel.add(dateChooserDesde);
		dateChooserDesde.setDateFormatString("yyyy-MM-dd");
		dateChooserDesde.setDate(new Date());
		
		JLabel label_2 = new JLabel("Hasta: ");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_2.setBounds(373, 19, 49, 14);
		contentPanel.add(label_2);
		
		JDateChooser dateChooserHasta = new JDateChooser();
		dateChooserHasta.setBounds(427, 11, 122, 22);
		contentPanel.add(dateChooserHasta);
		dateChooserHasta.setDateFormatString("yyyy-MM-dd");
		dateChooserHasta.setDate(new Date());
		
		
		
		JLabel label_3 = new JLabel("Movimientos en Cuenta Corriente");
		label_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		label_3.setBounds(128, 80, 339, 16);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("Movimientos en Cuenta de Contado");
		label_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		label_4.setBounds(135, 354, 261, 16);
		contentPanel.add(label_4);
		
		JScrollPane scrollPaneContado = new JScrollPane();
		scrollPaneContado.setBounds(10, 385, 542, 176);
		contentPanel.add(scrollPaneContado);
		
		Object[] encabezado = new Object[3];
		encabezado[0] = "Tipo Comprobante";
		encabezado[1] = "Numero Comprobante";
		encabezado[2] = "Importe";

		DefaultTableModel tablaModeloContado = new DefaultTableModel(encabezado, 0);
		
		tableContado = new JTable(tablaModeloContado);
		scrollPaneContado.setViewportView(tableContado);

		DefaultTableModel modeloTabla = new DefaultTableModel(encabezado, 0);

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);



		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSeleccioneElProveedor = new JLabel("Seleccione el Proveedor");
			lblSeleccioneElProveedor.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblSeleccioneElProveedor.setBounds(10, 55, 142, 14);
			contentPanel.add(lblSeleccioneElProveedor);
		}
		{
			lblNombreDelCliente.setBounds(341, 55, 142, 14);
			contentPanel.add(lblNombreDelCliente);
		}
		{
			JButton btnBuscarProveedor = new JButton("Buscar Proveedor");
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
			btnBuscarProveedor.setBounds(162, 51, 142, 23);
			contentPanel.add(btnBuscarProveedor);
		}
		
		JButton btnListarMovimientos = new JButton("Listar Movimientos");
		btnListarMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fechaDesde;
				String fechaHasta;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				fechaDesde = sdf.format(dateChooserDesde.getDate());
				fechaHasta = sdf.format(dateChooserHasta.getDate());
				
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
						Object[] fila = new Object[3];
						fila[0] = resultado.getString("comprobante");
						fila[1] = resultado.getString("numerocomprobante");
						fila[2] = resultado.getDouble("saldo");
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
		btnListarMovimientos.setBounds(390, 68, 157, 23);
		contentPanel.add(btnListarMovimientos);

	}
}
