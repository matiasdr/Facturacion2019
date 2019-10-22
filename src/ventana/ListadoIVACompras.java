package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import java.awt.Toolkit;

public class ListadoIVACompras extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoIVACompras frame = new ListadoIVACompras();
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
	public ListadoIVACompras() {
		setTitle("Lista de IVA Compras");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoIVACompras.class.getResource("/logos/logo4.png")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneElPerodo = new JLabel("Periodo :");
		lblSeleccioneElPerodo.setBounds(10, 11, 59, 14);
		contentPane.add(lblSeleccioneElPerodo);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(68, 11, 43, 14);
		contentPane.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(223, 11, 49, 14);
		contentPane.add(lblHasta);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(462, 7, 89, 23);
		contentPane.add(btnImprimir);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(111, 5, 100, 20);
		contentPane.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(262, 5, 93, 20);
		contentPane.add(dateChooser_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(367, 7, 89, 23);
		contentPane.add(btnBuscar);
		
		table = new JTable();
		table.setBounds(42, 53, 509, 388);
		contentPane.add(table);
	}
}
