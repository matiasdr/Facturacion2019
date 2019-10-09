package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ListadoIVAVentas extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Object[] encabezado = new Object[8];
		encabezado[0] = "Fecha";
		encabezado[1] = "N° Comprobante";
		encabezado[2] = "Razon Social";
		encabezado[3] = "CUIT";
		encabezado[4] = "Alicuota IVA";
		encabezado[5] = "Importe Neto (sin Iva)";
		encabezado[6] = "Iva Debito Fiscal";
		encabezado[7] = "Importe Total";
		
		
		DefaultTableModel tablaModelo= new DefaultTableModel(encabezado, 0);
		
		JLabel lblSeleccioneElPerodo = new JLabel("Periodo");
		lblSeleccioneElPerodo.setBounds(10, 11, 61, 14);
		contentPane.add(lblSeleccioneElPerodo);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(121, 11, 49, 14);
		contentPane.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(385, 11, 49, 14);
		contentPane.add(lblHasta);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(835, 7, 89, 23);
		contentPane.add(btnImprimir);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(176, 11, 98, 20);
		contentPane.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(472, 11, 98, 20);
		contentPane.add(dateChooser_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(683, 7, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 56, 952, 321);
		contentPane.add(scrollPane);
		
		table = new JTable(tablaModelo);
		scrollPane.setViewportView(table);
	}
}
