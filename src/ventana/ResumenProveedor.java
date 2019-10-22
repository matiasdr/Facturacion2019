package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Toolkit;


public class ResumenProveedor extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	JLabel lblNombreDelCliente = new JLabel("NOMBRE DEL Proveedor");
	private JTable table;
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
		
		
		
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSeleccioneElProveedor = new JLabel("Seleccione el Proveedor");
			lblSeleccioneElProveedor.setBounds(10, 11, 142, 14);
			contentPanel.add(lblSeleccioneElProveedor);
		}
		{
			lblNombreDelCliente.setBounds(311, 11, 142, 14);
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

					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			});
			btnBuscarProveedor.setBounds(164, 7, 89, 23);
			contentPanel.add(btnBuscarProveedor);
		}
		
		{
			JLabel lblSeleccioneElPeriodo = new JLabel("Seleccione el periodo");
			lblSeleccioneElPeriodo.setBounds(10, 44, 142, 14);
			contentPanel.add(lblSeleccioneElPeriodo);
		}
		{
			JLabel lblDesde = new JLabel("Desde: ");
			lblDesde.setBounds(151, 44, 49, 14);
			contentPanel.add(lblDesde);
		}
		{
			JLabel lblHasta = new JLabel("Hasta: ");
			lblHasta.setBounds(303, 44, 49, 14);
			contentPanel.add(lblHasta);
		}
		JDateChooser fechaDesde = new JDateChooser();
		fechaDesde.setBounds(202, 38, 89, 20);
		contentPanel.add(fechaDesde);
		fechaDesde.setDateFormatString("dd-MM-yy");
		
		JDateChooser fechaHasta = new JDateChooser();
		fechaHasta.setBounds(364, 38, 89, 20);
		contentPanel.add(fechaHasta);
		fechaHasta.setDateFormatString("dd-MM-yy");
		{
			JButton btnListar = new JButton("Listar");
			btnListar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String prov = lblNombreDelCliente.getText();
					Date desde = fechaDesde.getDate();
					Date hasta = fechaHasta.getDate();
					// aca llamariamos al store procedure y le pasariamos como parametros el nomrbe del proveedor y las fechas
				}
			});
			btnListar.setBounds(30, 89, 89, 23);
			contentPanel.add(btnListar);
		}
		{
			JButton btnImprimir = new JButton("Imprimir");
			btnImprimir.setBounds(265, 89, 89, 23);
			contentPanel.add(btnImprimir);
		}
		
		table = new JTable();
		table.setBounds(10, 125, 443, 109);
		contentPanel.add(table);
		
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			contentPane.add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
