package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DefinirPeriodoDeTrabajo extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefinirPeriodoDeTrabajo dialog = new DefinirPeriodoDeTrabajo();
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
	public DefinirPeriodoDeTrabajo() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblSeleccioneElMes = new JLabel("Seleccione el Mes del Per\u00EDodo de Trabajo");
		lblSeleccioneElMes.setBounds(120, 43, 202, 14);
		getContentPane().add(lblSeleccioneElMes);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(148, 83, 133, 22);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(77, 185, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(253, 185, 89, 23);
		getContentPane().add(btnNewButton_1);

	}

}
