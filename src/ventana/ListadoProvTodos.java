package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ListadoProvTodos extends JFrame {
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCondicion;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoProvTodos frame = new ListadoProvTodos();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoProvTodos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(177, 20, 28, 22);
		contentPanel.add(comboBox);
		{
			JLabel lblBuscarPor = new JLabel("Buscar por");
			lblBuscarPor.setBounds(122, 23, 73, 14);
			contentPanel.add(lblBuscarPor);
		}
		comboBox.addItem("Razon Social");
		comboBox.addItem("CUIT");
		

		textFieldCondicion = new JTextField();
		textFieldCondicion.setBounds(215, 20, 96, 20);
		contentPanel.add(textFieldCondicion);
		textFieldCondicion.setColumns(10);
		
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
					String seleccion = comboBox.getSelectedItem().toString();
					String condicion = textFieldCondicion.getText();
					
					if(seleccion.equals("Razon Social")) {
						// llamar a la funcion de buscar por nombre del proveedor
						
					} else if(seleccion.equals("CUIT")) {
						// llamar a la fucnion de buscar por CUIT.
					}
				}
				
			});
			btnBuscarPor.setBounds(315, 19, 89, 23);
			contentPanel.add(btnBuscarPor);
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
