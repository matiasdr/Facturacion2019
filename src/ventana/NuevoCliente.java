package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ItemEvent;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class NuevoCliente extends JFrame {
	private JTextField cli_RazSocial;
	private JTextField cli_Cuil;
	private JTextField cli_Domicilio;
	private JTextField cli_Telefono;
	private JTextField cli_Categ;
	private JTextField cli_PersResp;
	private JTextField cli_Contacto;
	private JPanel contentPane;
	private int Categoria;
	
	private boolean flag;
	
	private JComboBox<String> comboBox;
	private JTextField tFEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoCliente frame = new NuevoCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public NuevoCliente() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoCliente.class.getResource("/logos/logo4.png")));
		setTitle("Alta de Cliente");
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		

		JLabel lblNombreORazn = new JLabel("Nombre o Raz\u00F3n Social :");
		lblNombreORazn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNombreORazn.setBounds(10, 65, 163, 14);
		contentPane.add(lblNombreORazn);

		JLabel lblCuitOCuil = new JLabel("CUIT o CUIL :");
		lblCuitOCuil.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblCuitOCuil.setBounds(59, 92, 106, 14);
		contentPane.add(lblCuitOCuil);

		JLabel lblDomicilio = new JLabel("Domicilio :");
		lblDomicilio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblDomicilio.setBounds(88, 119, 71, 14);
		contentPane.add(lblDomicilio);

		JLabel lblTelefono = new JLabel("Telefono :");
		lblTelefono.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblTelefono.setBounds(88, 146, 71, 14);
		contentPane.add(lblTelefono);

		JLabel lblCondicionAnteEl = new JLabel("Condicion ante el IVA :");
		lblCondicionAnteEl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblCondicionAnteEl.setBounds(10, 173, 155, 14);
		contentPane.add(lblCondicionAnteEl);

		JLabel lblCategoria = new JLabel("Categor\u00EDa :");
		lblCategoria.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblCategoria.setBounds(88, 212, 85, 20);
		contentPane.add(lblCategoria);

		JLabel lblPersonaResponsable = new JLabel("Persona Responsable :");
		lblPersonaResponsable.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblPersonaResponsable.setBounds(10, 245, 149, 14);
		contentPane.add(lblPersonaResponsable);

		JLabel lblContactoresponsable = new JLabel("Contacto Responsable :");
		lblContactoresponsable.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblContactoresponsable.setBounds(10, 275, 168, 14);
		contentPane.add(lblContactoresponsable);
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEmail.setBounds(103, 304, 56, 16);
		contentPane.add(lblEmail);
		
		tFEmail = new JTextField();
		tFEmail.setBounds(186, 298, 191, 22);
		contentPane.add(tFEmail);
		tFEmail.setColumns(10);

		cli_RazSocial = new JTextField();
		cli_RazSocial.setBounds(185, 62, 167, 20);
		contentPane.add(cli_RazSocial);
		cli_RazSocial.setColumns(10);

		cli_Cuil = new JTextField();
		cli_Cuil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
				String contenido = cli_Cuil.getText();
				if(contenido.length()>=11) {
					e.consume();
				}
			}
		});
		cli_Cuil.setBounds(185, 89, 125, 20);
		contentPane.add(cli_Cuil);
		cli_Cuil.setColumns(10);

		cli_Domicilio = new JTextField();
		cli_Domicilio.setBounds(185, 116, 167, 20);
		contentPane.add(cli_Domicilio);
		cli_Domicilio.setColumns(10);

		cli_Telefono = new JTextField();
		cli_Telefono.setBounds(185, 143, 125, 20);
		contentPane.add(cli_Telefono);
		cli_Telefono.setColumns(10);

		cli_Categ = new JTextField();
		cli_Categ.setBounds(185, 203, 42, 20);
		contentPane.add(cli_Categ);
		cli_Categ.setColumns(10);

		cli_PersResp = new JTextField();
		cli_PersResp.setBounds(185, 236, 167, 20);
		contentPane.add(cli_PersResp);
		cli_PersResp.setColumns(10);

		cli_Contacto = new JTextField();
		cli_Contacto.setBounds(185, 269, 125, 20);
		contentPane.add(cli_Contacto);
		cli_Contacto.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(185, 169, 281, 22);
		contentPane.add(comboBox);
		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from condicion_fiscal");
		while(resultado.next()) {
			comboBox.addItem(resultado.getString("descripcion"));
		}
		nc.desconectar();
		
		
		JLabel cli_error = new JLabel("Dato invalido/Campo obligatorio");
		cli_error.setForeground(Color.RED);
		cli_error.setBackground(Color.WHITE);
		cli_error.setVisible(false);
		cli_error.setBounds(364, 64, 191, 16);
		contentPane.add(cli_error);
		
		JLabel cli_error1 = new JLabel("Dato invalido/Campo obligatorio");
		cli_error1.setForeground(Color.RED);
		cli_error1.setVisible(false);
		cli_error1.setBounds(364, 91, 191, 16);
		contentPane.add(cli_error1);
		
		JLabel cli_error2 = new JLabel("Dato invalido/Campo obligatorio");
		cli_error2.setForeground(Color.RED);
		cli_error2.setVisible(false);
		cli_error2.setBounds(364, 118, 191, 16);
		contentPane.add(cli_error2);
				
		JLabel cli_error4 = new JLabel("Dato invalido/Campo obligatorio");
		cli_error4.setForeground(Color.RED);
		cli_error4.setVisible(false);		cli_error4.setBounds(347, 205, 191, 16);
		contentPane.add(cli_error4);
		
		JButton btnGuardarCliente = new JButton("Guardar Cliente");
		btnGuardarCliente.addActionListener(new ActionListener() {
			@SuppressWarnings("unlikely-arg-type")
			public void actionPerformed(ActionEvent arg0) {
				
				String Raz_Social= null;
				String Cuil = null;
				String Domicilio = null;
				int Categoria = 0;
				String Responsable = null;
				String Contacto = null; 
				String TipoIva = null;
				String Telefono = null;
				Integer condicion = null;
				String email = null;
				
				
				if(cli_RazSocial.getText().isEmpty())
				 {
				   cli_error.setVisible(true);
				   flag = true;
				 }else {
					 Raz_Social=cli_RazSocial.getText();
				 }
						         
				 if(cli_Cuil.getText().isEmpty() || cli_Cuil.getText().length() != 11)
				 {
					 cli_error1.setVisible(true);
					 flag = true;
				 } else {
					 Cuil = cli_Cuil.getText();
				 }
												     
                 if(cli_Domicilio.getText().isEmpty())
                 {
                	 cli_error2.setVisible(true);
                	 flag = true;
                 } else {
                	 Domicilio = cli_Domicilio.getText();
                 }
                 
                 Telefono = cli_Telefono.getText();
                                                 
				 if(cli_Categ.getText().equals(0))
				 {
				 	cli_error4.setVisible(true);
				 	 flag = true;
				 } else {
					int Categoria1 = parceInt(cli_Categ.getText());
				 }
				 
				 condicion = comboBox.getSelectedIndex()+1;				 
				 Responsable = cli_PersResp.getText();
				 Contacto = cli_Contacto.getText();
				 if(!tFEmail.getText().isEmpty()) email = tFEmail.getText();
				 
				 StringBuilder objetivo = new StringBuilder(Cuil);
				 objetivo = objetivo.insert(2,"-");
				 objetivo = objetivo.insert(objetivo.length()-1, "-");
				 Cuil=objetivo.toString();
				 
				 
				 if(flag) {
					 JOptionPane.showMessageDialog(null, "Faltan Ingresar Datos");
					 flag = false;
				 }
				 else 
				 {
						try {
							Conexion nc = new Conexion();
							Connection conec = nc.conectar();
							
							System.out.println(Raz_Social);
							System.out.println(Cuil);
							System.out.println(Domicilio);
							System.out.println(Telefono);
							System.out.println(condicion);
							System.out.println(Categoria);
							System.out.println(Responsable);
							System.out.println(Contacto);	
							System.out.println(email);
							
							Statement instruccion = conec.createStatement();
							instruccion.execute("spnuevocliente '"+Raz_Social+"', '"+Cuil+"', '"+Domicilio+"', "+Telefono+", "+condicion+", "+Categoria+", "+Responsable+", "+Contacto+", "+email+", 0");
							JOptionPane.showMessageDialog(null, "Los Datos fueron Guardados Satisfactoriamente");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						nc.desconectar();
				 }
			}

			private Integer parceInt(String text) {
				// TODO Auto-generated method stub
				return null;
			}
		});

		
		
		btnGuardarCliente.setBounds(214, 345, 138, 23);
		contentPane.add(btnGuardarCliente);
		
		JLabel lblTitulo = new JLabel("Alta de Clientes");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblTitulo.setBounds(177, 13, 125, 16);
		contentPane.add(lblTitulo);
		
		
		
			

	}
}
