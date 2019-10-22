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
	private JTable table;

	private Integer id_seleccion;
	private Double saldoC;
	private String tipoComp;
	private int idcta;
	
	
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
		setBounds(100, 100, 842, 407);
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
		tFfecha.setBounds(703, 65, 79, 22);
		contentPane.add(tFfecha);
		tFfecha.setColumns(10);
		String FechaAct = sdf.format(fecha);
		
		JLabel lblTitulo = new JLabel("Generar Recibo de Cobro ");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblTitulo.setBounds(293, 13, 196, 16);
		contentPane.add(lblTitulo);
		
		JLabel lblSelecCli = new JLabel("Seleccione el Cliente :");
		lblSelecCli.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSelecCli.setBounds(26, 68, 153, 16);
		contentPane.add(lblSelecCli);
		
		JLabel lblNombreClie = new JLabel("");
		lblNombreClie.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNombreClie.setBounds(340, 59, 276, 25);
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
		btnIniBus.setBounds(191, 64, 137, 25);
		contentPane.add(btnIniBus);
		
		

		JLabel lblDatos = new JLabel("Datos del Comprobante :");
		lblDatos.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDatos.setBounds(26, 124, 171, 16);
		contentPane.add(lblDatos);
		
		tFDatos = new JTextField();
		tFDatos.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tFDatos.setColumns(10);
		tFDatos.setBounds(196, 121, 211, 22);
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
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@SuppressWarnings("unlikely-arg-type")
			public void actionPerformed(ActionEvent e) {
				   
				String nro_recibo = null;
				double imporRec = 0;
				
				if(!tFDatos.getText().isEmpty()) nro_recibo = tFDatos.getText();
				
				 				
				 if(!tFImporte.getText().isEmpty()) {
					 
					   imporRec = Double.parseDouble(tFImporte.getText());					    
					    tipoComp = "H";
					    String comprobante = "Recibo";;
					    					  
					    try {
							Conexion nc = new Conexion();
							System.out.println();
							System.out.println(idcta);
							System.out.println(imporRec);
							System.out.println(FechaAct);
							System.out.println(tipoComp);
							System.out.println(comprobante);
							System.out.println(nro_recibo);
									
							Connection conec = nc.conectar();
							Statement instruccion = conec.createStatement();
							instruccion.execute("spnuevoitemcuentacliente '"+idcta+"', '"+imporRec+"', '"+FechaAct+"', '"+tipoComp+"', '"+comprobante+"', '"+nro_recibo+"', 0");
							
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
		btnEmitirRec.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnEmitirRec.setBounds(420, 320, 123, 25);
		contentPane.add(btnEmitirRec);
		
		ButtonGroup importe = new ButtonGroup();
		
		JLabel lblMedioPago = new JLabel("Medio de Pago :");
		lblMedioPago.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMedioPago.setBounds(26, 177, 116, 25);
		contentPane.add(lblMedioPago);
		
		JComboBox comboBoxMedPag = new JComboBox();
		comboBoxMedPag.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboBoxMedPag.setBounds(148, 178, 211, 22);
		contentPane.add(comboBoxMedPag);
		comboBoxMedPag.addItem("Efectivo");
		comboBoxMedPag.addItem("Tarjeta de Credito/Debito");
		comboBoxMedPag.addItem("Transferencia");
		comboBoxMedPag.addItem("Cheque");
		
		
		
		table = new JTable();
		table.setBounds(756, 440, -581, -95);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("Fecha :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(644, 68, 64, 16);
		contentPane.add(lblNewLabel);
	}

}
