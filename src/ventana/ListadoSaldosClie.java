package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import conexion.Conexion;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;

public class ListadoSaldosClie extends JFrame {
	
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoSaldosClie frame = new ListadoSaldosClie();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoSaldosClie() {
//		setOpacity(0.0f);
		setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		setTitle("Listado de Saldos de Clientes...");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 218, 513, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			contentPane.add(buttonPane);
			
			{
				JButton cancelButton = new JButton("Cerrar");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
			}
		}
		
		
			Object[] titulo = new Object[3];
			titulo[0]=" CUIT";
			titulo[1]="  Razon Social";
			titulo[2]="  Saldo";
			
			
			 DefaultTableModel tablaSaldoClie = new DefaultTableModel(titulo, 0);
			
			
			Conexion ltc = new Conexion();
			Connection conec = ltc.conectar();
//			ltc.listarClientes();
			Statement instruccion;
			try {
				instruccion = conec.createStatement();
				ResultSet resultado = instruccion.executeQuery("select c.cuilcuit, c.nombre, cc.saldo from cliente c "
						+ "inner join cuenta_cliente cc on c.id_cliente = cc.id_cliente");
							
				while(resultado.next()) {
					Object[] linea = new Object[3];
					linea[0]= resultado.getString("cuilcuit");
					linea[1]= resultado.getString("nombre");
					linea[2]= resultado.getInt("saldo");
			    	tablaSaldoClie.addRow(linea);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			ltc.desconectar();			
		
		
/*		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(113);
		table.getColumnModel().getColumn(1).setPreferredWidth(213);
		table.getColumnModel().getColumn(2).setPreferredWidth(88);
		table.setBounds(5, 31, 598, 182);
		contentPane.add(table);
*/		
		JLabel lblFechaSaldo = new JLabel("Saldos al : ");
		lblFechaSaldo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblFechaSaldo.setBounds(165, 13, 97, 16);
		contentPane.add(lblFechaSaldo);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(274, 7, 95, 22);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		Date today = Calendar.getInstance().getTime();
		dateChooser.setDate(today);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 501, 173);
		contentPane.add(scrollPane);
		
		    table = new JTable();
		    scrollPane.setViewportView(table);
		    table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		    table.setModel(tablaSaldoClie);
	}
}
