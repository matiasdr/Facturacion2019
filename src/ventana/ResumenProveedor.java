package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ResumenProveedor extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ResumenProveedor dialog = new ResumenProveedor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResumenProveedor() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSeleccioneElCliente = new JLabel("Seleccione el Cliente");
			lblSeleccioneElCliente.setBounds(10, 11, 101, 14);
			contentPanel.add(lblSeleccioneElCliente);
		}
		{
			JButton btnBuscarCliente = new JButton("Buscar Cliente");
			btnBuscarCliente.setBounds(121, 7, 113, 23);
			contentPanel.add(btnBuscarCliente);
		}
		{
			JLabel lblNombreDelCliente = new JLabel("NOMBRE DEL Proveedor");
			lblNombreDelCliente.setBounds(244, 11, 142, 14);
			contentPanel.add(lblNombreDelCliente);
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
			JButton button = new JButton("New button");
			button.setBounds(163, 32, 59, 23);
			contentPanel.add(button);
		}
		{
			JLabel lblHasta = new JLabel("Hasta: ");
			lblHasta.setBounds(235, 36, 49, 14);
			contentPanel.add(lblHasta);
		}
		{
			JButton btnNewButton = new JButton("New button");
			btnNewButton.setBounds(275, 32, 59, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnListar = new JButton("Listar");
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
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
