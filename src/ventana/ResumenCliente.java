package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Toolkit;

public class ResumenCliente extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ResumenCliente frame = new ResumenCliente();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResumenCliente() {
		setTitle("Resumen de Cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ResumenCliente.class.getResource("/logos/logo4.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSeleccioneElCliente = new JLabel("Seleccione el Cliente");
			lblSeleccioneElCliente.setBounds(10, 11, 152, 14);
			contentPanel.add(lblSeleccioneElCliente);
		}
		{
			JButton btnBuscarCliente = new JButton("Buscar Cliente");
			btnBuscarCliente.setBounds(221, 7, 113, 23);
			contentPanel.add(btnBuscarCliente);
		}
		{
			JLabel lblNombreDelCliente = new JLabel("NOMBRE DEL CLIENTE");
			lblNombreDelCliente.setBounds(363, 11, 142, 14);
			contentPanel.add(lblNombreDelCliente);
		}
		{
			JLabel lblSeleccioneElPeriodo = new JLabel("Seleccione el periodo");
			lblSeleccioneElPeriodo.setBounds(10, 36, 126, 14);
			contentPanel.add(lblSeleccioneElPeriodo);
		}
		{
			JLabel lblDesde = new JLabel("Desde: ");
			lblDesde.setBounds(148, 38, 49, 14);
			contentPanel.add(lblDesde);
		}
		{
			JButton button = new JButton("New button");
			button.setBounds(209, 32, 110, 23);
			contentPanel.add(button);
		}
		{
			JLabel lblHasta = new JLabel("Hasta: ");
			lblHasta.setBounds(331, 36, 49, 14);
			contentPanel.add(lblHasta);
		}
		{
			JButton btnNewButton = new JButton("New button");
			btnNewButton.setBounds(392, 32, 113, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnListar = new JButton("Listar");
			btnListar.setBounds(24, 249, 89, 23);
			contentPanel.add(btnListar);
		}
		{
			JList list = new JList();
			list.setBounds(10, 68, 495, 150);
			contentPanel.add(list);
		}
		{
			JButton btnImprimir = new JButton("Imprimir");
			btnImprimir.setBounds(221, 249, 89, 23);
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
