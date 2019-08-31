package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ListadoSaldosProv extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoSaldosProv dialog = new ListadoSaldosProv();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoSaldosProv() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSeleccioneLaFecha = new JLabel("Seleccione la fecha");
			lblSeleccioneLaFecha.setBounds(10, 11, 94, 14);
			contentPanel.add(lblSeleccioneLaFecha);
		}
		{
			JLabel lblHasta = new JLabel("Hasta: ");
			lblHasta.setBounds(206, 11, 49, 14);
			contentPanel.add(lblHasta);
		}
		{
			JButton btnNewButton_1 = new JButton("New button");
			btnNewButton_1.setBounds(240, 7, 89, 23);
			contentPanel.add(btnNewButton_1);
		}
		{
			JButton btnNewButton_2 = new JButton("Buscar");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton_2.setBounds(335, 7, 89, 23);
			contentPanel.add(btnNewButton_2);
		}
		{
			JList list = new JList();
			list.setBounds(30, 37, 377, 146);
			contentPanel.add(list);
		}
		{
			JButton btnImprimir = new JButton("Imprimir");
			btnImprimir.setBounds(166, 194, 89, 23);
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
