package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ListadoClieTodos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoClieTodos dialog = new ListadoClieTodos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoClieTodos() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 53, 376, 165);
		contentPanel.add(scrollPane);
		{
			JButton btnImprimir = new JButton("Imprimir");
			btnImprimir.setBounds(28, 19, 89, 23);
			contentPanel.add(btnImprimir);
		}
		{
			JButton btnBuscarPor = new JButton("Buscar");
			btnBuscarPor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnBuscarPor.setBounds(315, 19, 89, 23);
			contentPanel.add(btnBuscarPor);
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(177, 20, 28, 22);
		contentPanel.add(comboBox);
		{
			JLabel lblBuscarPor = new JLabel("Buscar por");
			lblBuscarPor.setBounds(122, 23, 73, 14);
			contentPanel.add(lblBuscarPor);
		}
		
		textField = new JTextField();
		textField.setBounds(215, 20, 96, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
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
