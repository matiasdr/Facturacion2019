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
import java.awt.Toolkit;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.HeadlessException;

import com.toedter.calendar.JDayChooser;

import conexion.Conexion;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

public class ResumenCliente extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTable table;
	
	private Date fHasta;
	private Date fDesde;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ResumenCliente frame = new ResumenCliente();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResumenCliente() {
		setTitle("Resumen de Cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ResumenCliente.class.getResource("/logos/logo4.png")));
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		DefaultTableModel tablaModelo = new DefaultTableModel(0,3);
		Object[] fila = new Object[3];
		fila[0]= "Tipo Comprobante";
		fila[1]= "Numero";
		fila[2]= "Saldo";
		tablaModelo.addRow(fila);
		
		table = new JTable(tablaModelo);
		table.setFont(new Font("Times New Roman", Font.BOLD, 14));
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setBounds(10, 113, 568, 179);
		contentPanel.add(table);
		
		JDateChooser desdeDia = new JDateChooser();
		desdeDia.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		desdeDia.setBounds(247, 47, 114, 22);
		contentPanel.add(desdeDia);
		desdeDia.setDateFormatString("yyyy-MM-dd");
		
		JDateChooser hastaDia = new JDateChooser();
		hastaDia.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		hastaDia.setBounds(451, 47, 122, 22);
		contentPanel.add(hastaDia);
		hastaDia.setDateFormatString("yyyy-MM-dd");
				
		{
			JLabel lblNombreClie = new JLabel("Seleccione el Cliente :");
			lblNombreClie.setFont(new Font("Times New Roman", Font.BOLD, 15));
			lblNombreClie.setBounds(34, 82, 152, 23);
			contentPanel.add(lblNombreClie);
		}
		
		JLabel lblSeleccionCliente = new JLabel("");
		lblSeleccionCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblSeleccionCliente.setBounds(333, 76, 240, 29);
		contentPanel.add(lblSeleccionCliente);
		
		{
			JButton btnBuscarCliente = new JButton("Buscar Cliente");
			btnBuscarCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ElegirCliente ec;
					try {
						ec = new ElegirCliente(new java.awt.Frame(), true);
						ec.setVisible(true);
						lblSeleccionCliente.setText(ec.getNombreCliente());
						Integer id_seleccion = ec.getClienElegido();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String fDesde = sdf.format(desdeDia.getDate());
						String fHasta = sdf.format(hastaDia.getDate());
						
						
						System.out.println(fDesde);
						System.out.println(fHasta);
						
															
						try {
							Conexion nc = new Conexion();
							Connection conec = nc.conectar();
							Statement instruccion = conec.createStatement();
							String sql = "Select it.comprobante, it.numerocomprobante, it.saldo from itemcuentacliente it "
									+ "inner join cuenta_cliente cc on it.id_cuenta=cc.id_cuenta "
									+ "inner join cliente c on c.id_cliente= cc.id_cliente where it.fecha >= cast('"+ fDesde + "'as date) and it.fecha <= cast('" + fHasta+ "'as date) and c.id_cliente =" + id_seleccion ;
							
							System.out.print(sql);				
							ResultSet resultado = instruccion.executeQuery(sql); 
							 
		
							while(resultado.next()) {
								Object[] linea = new Object[3];
								linea[0]= resultado.getString("comprobante");
								linea[1]= resultado.getInt("numerocomprobante");
								linea[2]= resultado.getDouble("saldo");
								tablaModelo.addRow(linea);
							}
					 								
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
			});
			btnBuscarCliente.setFont(new Font("Times New Roman", Font.BOLD, 15));
			btnBuscarCliente.setBounds(189, 82, 132, 23);
			contentPanel.add(btnBuscarCliente);
		}
		
		
		
		{
			JLabel lblSeleccioneElPeriodo = new JLabel("Seleccione el periodo :");
			lblSeleccioneElPeriodo.setFont(new Font("Times New Roman", Font.BOLD, 15));
			lblSeleccioneElPeriodo.setBounds(34, 51, 152, 18);
			contentPanel.add(lblSeleccioneElPeriodo);
		}
		
		{
			JLabel lblDesde = new JLabel("Desde: ");
			lblDesde.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblDesde.setBounds(198, 55, 49, 14);
			contentPanel.add(lblDesde);
		}
		
		{
			JLabel lblHasta = new JLabel("Hasta: ");
			lblHasta.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblHasta.setBounds(397, 55, 49, 14);
			contentPanel.add(lblHasta);
		}
		
		
		JLabel lblTitulo = new JLabel("Resumen de Cliente");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		lblTitulo.setBounds(201, 0, 168, 29);
		contentPanel.add(lblTitulo);
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			contentPane.add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
					}
				});
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
