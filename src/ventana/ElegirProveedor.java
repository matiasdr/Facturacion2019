package ventana;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ElegirProveedor extends JDialog {
	private JTextField textField;
	private JTable table;
	private String provElegido;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElegirProveedor dialog = new ElegirProveedor(new java.awt.Frame(), true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ElegirProveedor(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		setTitle("Busqueda de Proveedores");
		setBounds(100, 100, 527, 337);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(154, 11, 96, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(306, 10, 89, 23);
		getContentPane().add(btnBuscar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 10, 101, 21);
		getContentPane().add(comboBox);
		
		table = new JTable();
		table.setBounds(20, 42, 382, 179);
		getContentPane().add(table);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				provElegido=textField.getText().toString();
				setVisible(false);
			}
		});
		btnSeleccionar.setBounds(295, 228, 89, 23);
		getContentPane().add(btnSeleccionar);

	}
	public String getProvElegido() {
		
		return provElegido;
	}
	public void setProv(String provElegido) {
		this.provElegido = provElegido;
	}
}
