package ventana;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ElegirCliente extends JDialog {
	private JTextField textField;
	private JTable table;
	private String clienElegido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElegirCliente dialog = new ElegirCliente(new java.awt.Frame(), true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ElegirCliente(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		setTitle("Busqueda de Clientes");
		setBounds(100, 100, 581, 386);
		getContentPane().setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(187, 13, 142, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(394, 12, 135, 23);
		getContentPane().add(btnBuscar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 13, 135, 22);
		getContentPane().add(comboBox);
		
		table = new JTable();
		table.setBounds(12, 48, 517, 242);
		getContentPane().add(table);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clienElegido=textField.getText().toString();
				setVisible(false);
			}
		});
		
		btnSeleccionar.setBounds(361, 303, 148, 25);
		getContentPane().add(btnSeleccionar);

	}
	public String getClienElegido() {
		return clienElegido;
	}
	public void setClien(String clienElegido) {
		this.clienElegido = clienElegido;
	}
}
