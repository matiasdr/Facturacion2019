package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DefinirPeriodoDeTrabajo extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefinirPeriodoDeTrabajo frame = new DefinirPeriodoDeTrabajo();
					frame.setVisible(true);
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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneElMes = new JLabel("Seleccione el Mes del Per\u00EDodo de Trabajo");
		lblSeleccioneElMes.setBounds(120, 43, 202, 14);
		contentPane.add(lblSeleccioneElMes);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(148, 83, 133, 22);
		contentPane.add(comboBox);
		comboBox.addItem("[Seleccione el Periodo]");
		comboBox.addItem("Enero");
		comboBox.addItem("Febrero");
		comboBox.addItem("Marzo");
		comboBox.addItem("Abril");
		comboBox.addItem("Mayo");
		comboBox.addItem("Junio");
		comboBox.addItem("Julio");
		comboBox.addItem("Agosto");
		comboBox.addItem("Septiembre");
		comboBox.addItem("Octubre");
		comboBox.addItem("Noviembre");
		comboBox.addItem("Diciembre");
		comboBox.setSelectedIndex(0);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(77, 185, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(253, 185, 89, 23);
		contentPane.add(btnNewButton_1);

	}

}
