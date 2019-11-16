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
import javax.swing.SwingConstants;

public class ResumenCliente extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTable tablectacte;
	
	private Date fHasta;
	private Date fDesde;
	private JTable tablecdo;
	private String tipoComp;
	
	
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
		setBounds(100, 100, 819, 724);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblSaldo = new JLabel("Saldo :   $  ");
		lblSaldo.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSaldo.setBounds(622, 382, 86, 16);
		contentPanel.add(lblSaldo);
			
		
		DefaultTableModel tablaModeloctacte = new DefaultTableModel(0,6);
		Object[] fila = new Object[6];
		fila[0]= "Fecha";
		fila[1]= "Tipo Comprobante";
		fila[2]= "Numero";
		fila[3]= "Debe";
		fila[4]= "Haber";
		fila[5]= "Saldo";	
		tablaModeloctacte.addRow(fila);
		
		tablectacte = new JTable(tablaModeloctacte);
		tablectacte.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tablectacte.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablectacte.setBounds(12, 160, 767, 209);
		contentPanel.add(tablectacte);
				
		JLabel lblTitulo = new JLabel("Resumen de Cliente");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		lblTitulo.setBounds(201, 0, 168, 29);
		contentPanel.add(lblTitulo);
		
		JLabel lblTituloctacte = new JLabel("Movimientos en Cuenta Corriente");
		lblTituloctacte.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblTituloctacte.setBounds(260, 137, 240, 16);
		contentPanel.add(lblTituloctacte);
		
		JLabel lblTituloContado = new JLabel("Movimientos en Cuenta de Contado");
		lblTituloContado.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblTituloContado.setBounds(260, 435, 261, 16);
		contentPanel.add(lblTituloContado);
		
		DefaultTableModel tablaModelocdo = new DefaultTableModel(0,3);
		Object[] fila1= new Object[3];
		fila1[0]= "Tipo Comprobante";
		fila1[1]= "Numero";
		fila1[2]= "Fecha";		
		tablaModelocdo.addRow(fila1);
		
		tablecdo = new JTable(tablaModelocdo);
		tablecdo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablecdo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		tablecdo.setBounds(61, 464, 669, 153);
		contentPanel.add(tablecdo);
		
		JLabel lblSaldoCli = new JLabel("0000,00");
		lblSaldoCli.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldoCli.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblSaldoCli.setBounds(695, 376, 84, 29);
		contentPanel.add(lblSaldoCli);
		
			
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
			lblNombreClie.setBounds(34, 99, 152, 23);
			contentPanel.add(lblNombreClie);
		}
		
		JLabel lblSeleccionCliente = new JLabel("");
		lblSeleccionCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblSeleccionCliente.setBounds(331, 95, 312, 29);
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
						int saldocli = 0;
															
						try {
							Conexion nc = new Conexion();
							Connection conec = nc.conectar();
							Statement instruccion = conec.createStatement();
							String sql = "Select it.comprobante, it.numerocomprobante, it.saldo, it.debe_haber, it.fecha from itemcuentacliente it "
									+ "inner join cuenta_cliente cc on it.id_cuenta=cc.id_cuenta "
									+ "inner join cliente c on c.id_cliente= cc.id_cliente where it.fecha >= cast('"+ fDesde + "'as date) and it.fecha <= cast('" + fHasta+ "'as date) and c.id_cliente =" + id_seleccion ;
							ResultSet resultado = instruccion.executeQuery(sql);
							
							Statement instruccion1 = conec.createStatement();
							String sql1 = "select numero, tipo, fecha from factura where fecha >= cast('"+fDesde+"' as date) and fecha <= cast('"+fHasta+"' as date)  and condicion_venta = 'Contado' and id_cliente =" + id_seleccion;
							ResultSet resultado1 = instruccion1.executeQuery(sql1); 
		
							while(resultado.next()) {
								Object[] linea = new Object[6];
								linea[0]= resultado.getString("fecha");
								linea[1]= resultado.getString("comprobante");
								linea[2]= resultado.getInt("numerocomprobante");
								tipoComp = resultado.getString("debe_haber").trim();
								
								if(tipoComp.toString().equals("D"))
								{
									linea[3]= resultado.getInt("saldo");
									linea[4]= 0;
									saldocli = saldocli+resultado.getInt("saldo"); 
								}
								else
									if(tipoComp.toString().equals("H"))
									{
										linea[3]= 0;
										linea[4]= resultado.getInt("saldo");
										saldocli = saldocli-resultado.getInt("saldo");
									}
								linea[5]= saldocli;
								lblSaldoCli.setText(String.valueOf(saldocli));								
								tablaModeloctacte.addRow(linea);
								
							}
							
							while(resultado1.next()) {
								Object[] linea1 = new Object[3];
								linea1[0]= resultado1.getString("tipo");
								linea1[1]= resultado1.getInt("numero");
								linea1[2]= resultado1.getDate("fecha");
								tablaModelocdo.addRow(linea1);
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
			btnBuscarCliente.setBounds(187, 99, 132, 23);
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
