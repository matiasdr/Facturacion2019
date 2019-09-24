package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ListadoIVAVentas extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoIVAVentas frame = new ListadoIVAVentas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListadoIVAVentas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneElPerodo = new JLabel("Seleccione el Per\u00EDodo");
		lblSeleccioneElPerodo.setBounds(10, 11, 119, 14);
		contentPane.add(lblSeleccioneElPerodo);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(119, 11, 49, 14);
		contentPane.add(lblDesde);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(154, 7, 42, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(206, 11, 49, 14);
		contentPane.add(lblHasta);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(238, 7, 49, 23);
		contentPane.add(btnNewButton_1);
		
		JList list = new JList();
		list.setBounds(27, 47, 397, 204);
		contentPane.add(list);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(308, 7, 89, 23);
		contentPane.add(btnImprimir);
	}

}
