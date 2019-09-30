package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.*;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;


public class ResumenProveedor extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	JLabel lblNombreDelCliente = new JLabel("NOMBRE DEL Proveedor");
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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
			lblNombreDelCliente.setBounds(244, 11, 142, 14);
			contentPanel.add(lblNombreDelCliente);
		}
		{
			JButton btnBuscarProveedor = new JButton("Buscar Proveedor");
			btnBuscarProveedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ElegirProveedor ep = new ElegirProveedor(new java.awt.Frame(), true);
					ep.setVisible(true);
					
					lblNombreDelCliente.setText(ep.getProvElegido());
				}
			});
			btnBuscarProveedor.setBounds(153, 7, 81, 23);
			contentPanel.add(btnBuscarProveedor);
		}
		
		{
			JLabel lblSeleccioneElPeriodo = new JLabel("Seleccione el periodo");
			lblSeleccioneElPeriodo.setBounds(10, 36, 113, 14);
			contentPanel.add(lblSeleccioneElPeriodo);
		}
		{
			JLabel lblDesde = new JLabel("Desde: ");
			lblDesde.setBounds(121, 36, 49, 14);
			contentPanel.add(lblDesde);
		}
		{
			JLabel lblHasta = new JLabel("Hasta: ");
			lblHasta.setBounds(269, 36, 49, 14);
			contentPanel.add(lblHasta);
		}
		JDateChooser fechaDesde = new JDateChooser();
		fechaDesde.setBounds(164, 30, 89, 20);
		contentPanel.add(fechaDesde);
		fechaDesde.setDateFormatString("dd-MM-yy");
		
		JDateChooser fechaHasta = new JDateChooser();
		fechaHasta.setBounds(316, 30, 89, 20);
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
			btnListar.setBounds(10, 61, 89, 23);
			contentPanel.add(btnListar);
		}
		{
			JList list = new JList();
			list.setBounds(10, 96, 402, 122);
			contentPanel.add(list);
		}
		{
			JButton btnImprimir = new JButton("Imprimir");
			btnImprimir.setBounds(198, 61, 89, 23);
			contentPanel.add(btnImprimir);
		}
		
	
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
