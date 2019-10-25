package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import conexion.Conexion;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

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
		setTitle("Listado de Saldo de Proveedores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoSaldosProv.class.getResource("/logos/logo4.png")));

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(201, 5, 102, 20);
		contentPanel.add(dateChooser);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		Date today = Calendar.getInstance().getTime();
		dateChooser.setDate(today);
		
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSeleccioneLaFecha = new JLabel("Seleccione la fecha ");

			lblSeleccioneLaFecha.setBounds(30, 11, 114, 14);
			contentPanel.add(lblSeleccioneLaFecha);
		}
		{
			JLabel lblHasta = new JLabel("Hasta: ");

			lblHasta.setBounds(154, 11, 49, 14);
			contentPanel.add(lblHasta);
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
			btnImprimir.setBounds(198, 208, 89, 23);
			contentPanel.add(btnImprimir);
		}
		Object[] encabezado = new Object[3];
		encabezado[0]="CUIT";
		encabezado[1]="Razon Social";
		encabezado[2]="Saldo";			
		DefaultTableModel tablaSaldos= new DefaultTableModel(encabezado, 0);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 54, 427, 143);
		contentPanel.add(scrollPane);
		table = new JTable(tablaSaldos);
		scrollPane.setViewportView(table);
		
		Conexion ltc = new Conexion();
		Connection conec = ltc.conectar();
		Statement instruccion;
		try {
			instruccion = conec.createStatement();
			ResultSet resultado = instruccion.executeQuery("select c.cuilcuit, c.nombre, cc.saldo from proveedor c "
					+ "inner join cuenta_proveedor cc on c.id_proveedor = cc.id_proveedor");
						
			while(resultado.next()) {
				Object[] linea = new Object[3];
				linea[0]= resultado.getString("cuilcuit");
				linea[1]= resultado.getString("nombre");
				linea[2]= resultado.getInt("saldo");
		    	tablaSaldos.addRow(linea);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		ltc.desconectar();	
		
	}
}
