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
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class ResumenProveedor extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	JLabel lblNombreDelCliente = new JLabel("NOMBRE DEL Proveedor");
	private JTable table;
	private Integer idProveedor=0;
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
		setBounds(100, 100, 487, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 404, 172);
		contentPanel.add(scrollPane);

		Object[] encabezado = new Object[3];
		encabezado[0] = "Tipo Comprobante";
		encabezado[1] = "Numero Comprobante";
		encabezado[2] = "Importe";

		DefaultTableModel modeloTabla = new DefaultTableModel(encabezado, 0);

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);



		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSeleccioneElProveedor = new JLabel("Seleccione el Proveedor");
			lblSeleccioneElProveedor.setBounds(10, 16, 142, 14);
			contentPanel.add(lblSeleccioneElProveedor);
		}
		{
			lblNombreDelCliente.setBounds(253, 16, 142, 14);
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

					while(modeloTabla.getRowCount()>0) {
						modeloTabla.removeRow(modeloTabla.getRowCount()-1);
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
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
			btnBuscarProveedor.setBounds(153, 12, 81, 23);
			contentPanel.add(btnBuscarProveedor);
		}


	}
}
