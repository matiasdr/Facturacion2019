package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ListadoProveCategoria extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoProveCategoria frame = new ListadoProveCategoria();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoProveCategoria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JList list = new JList();
		list.setBounds(324, 11, 110, 207);
		contentPanel.add(list);

		JLabel lblSeleccioneLaCategora = new JLabel("Seleccione la categor\u00EDa");
		lblSeleccioneLaCategora.setBounds(56, 11, 155, 25);
		contentPanel.add(lblSeleccioneLaCategora);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(221, 12, 89, 23);
		contentPanel.add(btnFiltrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 55, 272, 138);
		contentPanel.add(scrollPane);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(196, 204, 89, 23);
		contentPanel.add(btnImprimir);
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
