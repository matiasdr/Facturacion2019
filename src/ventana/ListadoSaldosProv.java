package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;

public class ListadoSaldosProv extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoSaldosProv frame = new ListadoSaldosProv();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoSaldosProv() {

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(185, 5, 102, 20);
		contentPanel.add(dateChooser);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		Date today = Calendar.getInstance().getTime();
		dateChooser.setDate(today);
		
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSeleccioneLaFecha = new JLabel("Seleccione la fecha");

			lblSeleccioneLaFecha.setBounds(30, 11, 94, 14);
			contentPanel.add(lblSeleccioneLaFecha);
		}
		{
			JLabel lblHasta = new JLabel("Hasta: ");

			lblHasta.setBounds(134, 11, 49, 14);
			contentPanel.add(lblHasta);
		}
		{
			DefaultTableModel tablaSaldos= new DefaultTableModel(0, 4);
			Object[] encabezado = new Object[4];
			encabezado[0]="CUIT";
			encabezado[1]="Razon Social";
			encabezado[2]="Fecha Ultimo Movimiento";
			encabezado[3]="Saldo";
			tablaSaldos.addRow(encabezado);
			table = new JTable();
			table.setBounds(10, 41, 404, 142);
			table.setModel(tablaSaldos);
			contentPanel.add(table);

		}
		{
			JButton btnNewButton_2 = new JButton("Buscar");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// obtenemos la fecha elegida en el jcalendar y la guardadmo en una variable para pasaral al sstoreprecedure
					
					Date fechaElegida = dateChooser.getDate();
					
					// hacemos un foreach para cada result y lo metemos en el tablaSaldos
					
					// tablaSaldos.addRow(elemento)
					
				}
			});
			btnNewButton_2.setBounds(375, 7, 89, 23);
			contentPanel.add(btnNewButton_2);
		}
		{
			JButton btnImprimir = new JButton("Imprimir");
			btnImprimir.setBounds(166, 194, 89, 23);
			contentPanel.add(btnImprimir);
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
