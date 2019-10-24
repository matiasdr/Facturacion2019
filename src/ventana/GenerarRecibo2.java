package ventana;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;

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
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import java.awt.Color;
import com.toedter.calendar.JDayChooser;

public class GenerarRecibo2 extends JFrame {
	private JPanel contentPane;
	private JTextField tFDatos;
	private JTextField tFImporte;
	private JTextField tFfecha;
	private JTable tablaFactPend;
	private JTextField tFnro_Rec;
	private JTable table;

	private Integer id_seleccion;
	private Double saldoC;
	private String tipoComp;
	private int idcta;
	private int nro_rec;
	private String forma_pago;
	
	
	
	
		/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarRecibo2 frame = new GenerarRecibo2();
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
	public GenerarRecibo2() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerarRecibo2.class.getResource("/logos/logo4.png")));
		setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		setTitle("Generar Recibo de Cobro a Cliente");
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tFfecha = new JTextField();
		tFfecha.setBackground(new Color(255, 255, 204));
		tFfecha.setForeground(new Color(0, 51, 0));
		
		Date fecha = new Date(); //fecha y hora actual
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //formatear la fecha en una cadena
		tFfecha.setText(sdf.format(fecha)); //setear la representacion en cadena de la fecha
		tFfecha.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tFfecha.setBounds(740, 34, 79, 22);
		contentPane.add(tFfecha);
		tFfecha.setColumns(10);
		String FechaAct = sdf.format(fecha); 
		
		JLabel lblFechaRec = new JLabel("Fecha :");
		lblFechaRec.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblFechaRec.setBounds(672, 36, 64, 16);
		contentPane.add(lblFechaRec);
		
		JLabel lblnro_rec = new JLabel("Recibo Numero :");
		lblnro_rec.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblnro_rec.setBounds(621, 68, 106, 16);
		contentPane.add(lblnro_rec);
		
		tFnro_Rec = new JTextField();
		tFnro_Rec.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tFnro_Rec.setBounds(740, 65, 91, 22);
		contentPane.add(tFnro_Rec);
		tFnro_Rec.setColumns(10);
		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion;
		try {
			instruccion = conec.createStatement();
	    	ResultSet resultado = instruccion.executeQuery("select top 1 numerocomprobante from itemcuentacliente where debe_haber = 'H' order by numerocomprobante desc");
			
	    	while(resultado.next()) {	    	
	    	nro_rec = resultado.getInt("numerocomprobante") +1;
			tFnro_Rec.setText(String.valueOf(nro_rec));
	    	}
	    	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		nc.desconectar();
		
		
		JLabel lblTitulo = new JLabel("Generar Recibo de Cobro ");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblTitulo.setBounds(293, 13, 196, 16);
		contentPane.add(lblTitulo);
		
		JLabel lblSelecCli = new JLabel("Seleccione el Cliente :");
		lblSelecCli.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSelecCli.setBounds(12, 68, 153, 16);
		contentPane.add(lblSelecCli);
		
		JLabel lblNombreClie = new JLabel("");
		lblNombreClie.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNombreClie.setBounds(333, 59, 276, 25);
		contentPane.add(lblNombreClie);
		
		JLabel lblimporteSaldo = new JLabel("");
		lblimporteSaldo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		lblimporteSaldo.setBounds(734, 302, 85, 25);
		contentPane.add(lblimporteSaldo);
		
		JLabel lblTipsaldo = new JLabel("");
		lblTipsaldo.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTipsaldo.setBounds(590, 302, 137, 25);
		contentPane.add(lblTipsaldo);
		
		DefaultTableModel tablaModelo = new DefaultTableModel(0,3);
		Object[] fila = new Object[3];
		fila[0]= "Tipo Comprobante";
		fila[1]= "Numero";
		fila[2]= "Saldo";
		
		tablaModelo.addRow(fila);
		
		tablaFactPend = new JTable(tablaModelo);
		tablaFactPend.setBackground(new Color(245, 245, 220));
		tablaFactPend.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tablaFactPend.setFont(new Font("Times New Roman", Font.BOLD, 13));
		tablaFactPend.setBounds(439, 139, 372, 150);
		contentPane.add(tablaFactPend);
		
		
		
		JButton btnIniBus = new JButton("Iniciar Busqueda");
		btnIniBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirCliente ec;
				try {
					ec = new ElegirCliente(new java.awt.Frame(), true);
					ec.setVisible(true);
					lblNombreClie.setText(ec.getNombreCliente());
					id_seleccion= ec.getClienElegido();
          //		lblNombreClie.setText(String.valueOf(id_seleccion));
					
					
					Double saldoC = 0.00;
					
					Conexion nc = new Conexion();
					Connection conec = nc.conectar();
					Statement instruccion;
					try {
						instruccion = conec.createStatement();
						ResultSet resultado = instruccion.executeQuery("Select it.comprobante, it.numerocomprobante, it.saldo, it.debe_haber, it.id_cuenta from itemcuentacliente it \r\n" + 
								"inner join cuenta_cliente cc on it.id_cuenta=cc.id_cuenta \r\n" + 
								"inner join cliente c on c.id_cliente= cc.id_cliente where c.id_cliente =" + id_seleccion);
						
						
						
						while(resultado.next()) {
							Object[] linea = new Object[3];
							linea[0]= resultado.getString("comprobante");
							linea[1]= resultado.getInt("numerocomprobante");
							linea[2]= resultado.getDouble("saldo");
							tipoComp = resultado.getString("debe_haber").trim();
	                        idcta = resultado.getInt("id_cuenta");
							 
							tablaModelo.addRow(linea);
						
							
							if(tipoComp.toString().equals("H")) {
							
								saldoC -= resultado.getDouble("saldo");
							}
							else
								if(tipoComp.toString().equals("D"))
							{
								saldoC += resultado.getDouble("saldo");
							}
							
							
						}								
							if(saldoC >= 1) {
								lblimporteSaldo.setText(String.valueOf(saldoC));
								lblTipsaldo.setText("Saldo Pendiente :");
								lblimporteSaldo.setForeground(Color.RED);
							}
							else
							{
								if(saldoC < 0) {
									lblimporteSaldo.setText(String.valueOf(saldoC*-1));
									lblTipsaldo.setText("Saldo a Favor :");
									lblimporteSaldo.setForeground(Color.BLACK);
							    }
							}
        				
											
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					nc.desconectar();
					
					
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private int parceInt(JLabel lblimporteSaldo) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		btnIniBus.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnIniBus.setBounds(172, 64, 137, 25);
		contentPane.add(btnIniBus);
		
		

		JLabel lblDatos = new JLabel("Detalle del Comprobante :");
		lblDatos.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDatos.setBounds(26, 195, 178, 16);
		contentPane.add(lblDatos);
		
		tFDatos = new JTextField();
		tFDatos.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tFDatos.setColumns(10);
		tFDatos.setBounds(204, 190, 211, 22);
		contentPane.add(tFDatos);
		
		JLabel lblFacPend = new JLabel("Listado de Movimientos");
		lblFacPend.setForeground(Color.BLACK);
		lblFacPend.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblFacPend.setBounds(537, 110, 171, 16);
		contentPane.add(lblFacPend);
		
		JLabel lblImpoParc = new JLabel("Importe de Pago :   $");
		lblImpoParc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblImpoParc.setBounds(26, 239, 143, 16);
		contentPane.add(lblImpoParc);
		
		tFImporte = new JTextField();
		tFImporte.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tFImporte.setColumns(10);
		tFImporte.setBounds(172, 236, 116, 22);
		contentPane.add(tFImporte);
		
		JComboBox comboBoxMedPag = new JComboBox();
		comboBoxMedPag.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboBoxMedPag.setBounds(148, 134, 211, 22);
		contentPane.add(comboBoxMedPag);
		comboBoxMedPag.addItem("Efectivo");
		comboBoxMedPag.addItem("Tarjeta de Credito/Debito");
		comboBoxMedPag.addItem("Transferencia");
		comboBoxMedPag.addItem("Cheque");
		
		forma_pago = (String) comboBoxMedPag.getSelectedItem();
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@SuppressWarnings("unlikely-arg-type")
			public void actionPerformed(ActionEvent e) {
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now(); 
				String fecha = dtf.format(now);
				   
				String detalle = null;
				double imporRec = 0;
				
				if(!tFDatos.getText().isEmpty()) detalle = tFDatos.getText();
				
				 				
				 if(!tFImporte.getText().isEmpty()) {
					 
					   imporRec = Double.parseDouble(tFImporte.getText());					    
					    tipoComp = "H";
					    String comprobante = "Recibo";;
					    					  
					    try {
							Conexion nc = new Conexion();
							Connection conec = nc.conectar();
							Statement instruccion = conec.createStatement();
							String sql = "spnuevoitemcuentacliente '"+idcta+"', '"+imporRec+"', '"+fecha+"', '"+tipoComp+"', '"+comprobante+"', '"+nro_rec+"', '"+detalle+"', '"+forma_pago+"', 0";
							System.out.println(sql);
							instruccion.execute(sql);
							
							instruccion.executeUpdate("Update cuenta_cliente set saldo = saldo + "+imporRec+" where id_cliente = "+id_seleccion);
							
							while(tablaModelo.getRowCount()>0) // LIMPIA EL JTABLE ANTES DE CARGARLO DE NUEVO
							{
								tablaModelo.removeRow(tablaModelo.getRowCount()-1);
							}
							// volver a cargar el JTable despues de confirmar el Recibo...
							saldoC = 0.00;
							Conexion cr = new Conexion();
							Connection conecRec = nc.conectar();
							Statement instruccion1;
							try {
								instruccion1 = conec.createStatement();
								ResultSet resultado = instruccion1.executeQuery("Select it.comprobante, it.numerocomprobante, it.saldo, it.debe_haber, it.id_cuenta from itemcuentacliente it \r\n" + 
										"inner join cuenta_cliente cc on it.id_cuenta=cc.id_cuenta \r\n" + 
										"inner join cliente c on c.id_cliente= cc.id_cliente where c.id_cliente =" + id_seleccion);
															
								while(resultado.next()) {
									Object[] linea = new Object[3];
									linea[0]= resultado.getString("comprobante");
									linea[1]= resultado.getInt("numerocomprobante");
									linea[2]= resultado.getDouble("saldo");
									tipoComp = resultado.getString("debe_haber").trim();
			                        idcta = resultado.getInt("id_cuenta");
									 
									tablaModelo.addRow(linea);
																	
									if(tipoComp.toString().equals("H")) {
									
										saldoC -= resultado.getDouble("saldo");
									}
									else
										if(tipoComp.toString().equals("D"))
									{
										saldoC += resultado.getDouble("saldo");
									}
																	
								}								
									if(saldoC >= 1) {
										lblimporteSaldo.setText(String.valueOf(saldoC));
										lblTipsaldo.setText("Saldo Pendiente :");
										lblimporteSaldo.setForeground(Color.RED);
									}
									else
									{
										if(saldoC < 0) {
											lblimporteSaldo.setText(String.valueOf(saldoC*-1));
											lblTipsaldo.setText("Saldo a Favor :");
											lblimporteSaldo.setForeground(Color.BLACK);
									    }
									}
		        				
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							cr.desconectar();
							
							JOptionPane.showMessageDialog(null, "Los Datos fueron Guardados Satisfactoriamente");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
				 }
				 else 
				 {		
					 JOptionPane.showMessageDialog(null, "Faltan Ingresar el Importe al comprobante");				 
				 }
			}

			
		});
		btnConfirmar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnConfirmar.setBounds(262, 320, 97, 25);
		contentPane.add(btnConfirmar);
		
		JButton btnEmitirRec = new JButton("Cancelar");
		btnEmitirRec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		btnEmitirRec.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnEmitirRec.setBounds(420, 320, 123, 25);
		contentPane.add(btnEmitirRec);
		
		ButtonGroup importe = new ButtonGroup();
		
		JLabel lblMedioPago = new JLabel("Medio de Pago :");
		lblMedioPago.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMedioPago.setBounds(26, 139, 116, 25);
		contentPane.add(lblMedioPago);
		
		
		
		
		
		
		table = new JTable();
		table.setBounds(756, 440, -581, -95);
		contentPane.add(table);
		
		
		
		
		
		
	}

}
