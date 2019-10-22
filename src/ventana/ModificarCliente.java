package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ModificarCliente extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField mod_cliRazSoc;
	private JTextField mod_cliCuit;
	private JTextField mod_cliDom;
	private JTextField mod_cliTelef;
	private JTextField mod_cliCateg;
	private JTextField mod_cliResp;
	private JTextField mod_cliContacto;
	
	private int idcliente;
	private JPanel contentPane;
	private JTextField mod_Email;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarCliente frame = new ModificarCliente();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarCliente() throws SQLException {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JLabel lblerrorRazSoc = new JLabel("Campo Invalido");
		lblerrorRazSoc.setForeground(Color.RED);
		lblerrorRazSoc.setBackground(Color.WHITE);
		lblerrorRazSoc.setBounds(418, 58, 91, 15);
		contentPane.add(lblerrorRazSoc);
		lblerrorRazSoc.setVisible(false);
		
		JLabel lblerrorCuil = new JLabel("Campo Invalido");
		lblerrorCuil.setForeground(Color.RED);
		lblerrorCuil.setBackground(Color.WHITE);
		lblerrorCuil.setBounds(418, 83, 91, 15);
		contentPane.add(lblerrorCuil);
		lblerrorCuil.setVisible(false);
		
		JLabel lblerrorDom = new JLabel("Campo Invalido");
		lblerrorDom.setForeground(Color.RED);
		lblerrorDom.setBackground(Color.WHITE);
		lblerrorDom.setBounds(418, 109, 91, 15);
		contentPane.add(lblerrorDom);
		lblerrorDom.setVisible(false);
		
		JLabel lblerrorCateg = new JLabel("Dato Invalido");
		lblerrorCateg.setForeground(Color.RED);
		lblerrorCateg.setBounds(429, 184, 80, 15);
		contentPane.add(lblerrorCateg);
		lblerrorCateg.setVisible(false);

		JLabel lblNombreORazn = new JLabel("Nombre o Raz\u00F3n Social :");
		lblNombreORazn.setBounds(42, 59, 155, 14);
		contentPane.add(lblNombreORazn);

		JLabel lblCuitOCuil = new JLabel("CUIT o CUIL :");
		lblCuitOCuil.setBounds(42, 84, 91, 14);
		contentPane.add(lblCuitOCuil);

		JLabel lblNewLabel = new JLabel("Domicilio :");
		lblNewLabel.setBounds(42, 109, 91, 14);
		contentPane.add(lblNewLabel);

		JLabel lblTelefono = new JLabel("Telefono :");
		lblTelefono.setBounds(42, 134, 109, 14);
		contentPane.add(lblTelefono);

		JLabel lblCondicionAnteEl = new JLabel("Condicion ante el IVA :");
		lblCondicionAnteEl.setBounds(42, 159, 144, 14);
		contentPane.add(lblCondicionAnteEl);

		JLabel lblCategora = new JLabel("Categor\u00EDa :");
		lblCategora.setBounds(42, 184, 91, 14);
		contentPane.add(lblCategora);

		JLabel lblPersonaResponsable = new JLabel("Persona Responsable :");
		lblPersonaResponsable.setBounds(42, 209, 144, 14);
		contentPane.add(lblPersonaResponsable);

		JLabel lblContactoresponsable = new JLabel("Contacto (Responsable) :");
		lblContactoresponsable.setBounds(42, 234, 171, 14);
		contentPane.add(lblContactoresponsable);
		
		JLabel lblEmail = new JLabel("E-mail Responsable :");
		lblEmail.setBounds(42, 261, 126, 16);
		contentPane.add(lblEmail);

		mod_cliRazSoc = new JTextField();
		mod_cliRazSoc.setBounds(195, 56, 185, 20);
		contentPane.add(mod_cliRazSoc);
		mod_cliRazSoc.setColumns(10);

		mod_cliCuit = new JTextField();
		mod_cliCuit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
				String contenido = mod_cliCuit.getText();
				if(contenido.length()>=11) {
					e.consume();
				}
			}
		});
		
		mod_cliCuit.setBounds(195, 81, 136, 20);
		contentPane.add(mod_cliCuit);
		mod_cliCuit.setColumns(10);

		mod_cliDom = new JTextField();
		mod_cliDom.setBounds(195, 106, 185, 20);
		contentPane.add(mod_cliDom);
		mod_cliDom.setColumns(10);

		mod_cliTelef = new JTextField();
		mod_cliTelef.setBounds(195, 131, 136, 20);
		contentPane.add(mod_cliTelef);
		mod_cliTelef.setColumns(10);

		mod_cliCateg = new JTextField();
		mod_cliCateg.setBounds(195, 181, 136, 20);
		contentPane.add(mod_cliCateg);
		mod_cliCateg.setColumns(10);

		mod_cliResp = new JTextField();
		mod_cliResp.setBounds(195, 206, 167, 20);
		contentPane.add(mod_cliResp);
		mod_cliResp.setColumns(10);

		mod_cliContacto = new JTextField();
		mod_cliContacto.setBounds(195, 231, 136, 20);
		contentPane.add(mod_cliContacto);
		mod_cliContacto.setColumns(10);
		
		mod_Email = new JTextField();
		mod_Email.setBounds(195, 255, 167, 22);
		contentPane.add(mod_Email);
		mod_Email.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(198, 155, 243, 22);
		contentPane.add(comboBox);
		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from condicion_fiscal");
		while(resultado.next()) {
			comboBox.addItem(resultado.getString("descripcion"));
		}

		JLabel lblSeleccioneElCliente = new JLabel("Seleccione el Cliente");
		lblSeleccioneElCliente.setBounds(42, 11, 144, 14);
		contentPane.add(lblSeleccioneElCliente);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id_cli=null;
				try {
					ElegirCliente ec;
					ec = new ElegirCliente(new java.awt.Frame(), true);
					ec.setVisible(true);
					id_cli=ec.getClienElegido();
					idcliente = id_cli;
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Conexion nc = new Conexion();
				Connection conec = nc.conectar();
				try {
					Statement instruccion = conec.createStatement();
					ResultSet resultado = instruccion.executeQuery("Select * from cliente where id_cliente = "+id_cli);
					
					
					// ahora rellenamos todos los campos con los datos de la consulta
					while(resultado.next()) {
					mod_cliRazSoc.setText(resultado.getString("nombre"));
					mod_cliCateg.setText(resultado.getString("categoria"));
					mod_cliDom.setText(resultado.getString("domicilio"));
					mod_cliResp.setText(resultado.getString("nombrecontacto"));
					mod_cliContacto.setText(resultado.getString("telefonocontacto"));
					mod_cliTelef.setText(resultado.getString("telefono"));
					int seleccion = resultado.getInt("id_condicion_fiscal");
					comboBox.setSelectedIndex(seleccion-1);
					String cadena = resultado.getString("cuilcuit");
					cadena = cadena.replace("-", "");
					mod_cliCuit.setText(cadena);
					mod_Email.setText(resultado.getString("email"));
					
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				nc.desconectar();
						
			}
		});
		btnBuscar.setBounds(225, 11, 89, 23);
		contentPane.add(btnBuscar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre=null;
				String cuitClie=null;
				String domicilio=null;
				String telefono=null;
				String categoria=null;
				String personaResponsable=null;
				String contacto=null;
				String c_email=null;
				
				int condicion = 0;;
				boolean error = false;
				
				
				if(mod_cliRazSoc.getText().isEmpty()) {
					error=true;
					mod_cliRazSoc.setVisible(true);
				} else {
					nombre = mod_cliRazSoc.getText();
					lblerrorRazSoc.setVisible(false);
				}
				
				if(mod_cliCuit.getText().isEmpty() || mod_cliCuit.getText().length()!=11) {
					error=true;
					lblerrorCuil.setVisible(true);
				} else {
					cuitClie= mod_cliCuit.getText();
					lblerrorCuil.setVisible(false);
				}
				
				if(mod_cliCateg.getText().isEmpty()) {
					error = true;
					lblerrorCateg.setVisible(true);
				} else {
					categoria = mod_cliCateg.getText();
					lblerrorCateg.setVisible(false);
				}
				
				if(comboBox.getSelectedItem().equals("")) {
					error = true;
					lblerrorCateg.setVisible(true);
				} else {
					condicion = comboBox.getSelectedIndex()+1;
					lblerrorCateg.setVisible(false);
				}
				
//				condicion = comboBox.getSelectedIndex()+1;
				
				if(!mod_cliDom.getText().isEmpty()) domicilio = mod_cliDom.getText();
				if(!mod_cliTelef.getText().isEmpty()) telefono = mod_cliTelef.getText();
				if(!mod_cliResp.getText().isEmpty()) personaResponsable = mod_cliResp.getText();
				if(!mod_cliContacto.getText().isEmpty()) contacto = mod_cliContacto.getText();
				if(!mod_Email.getText().isEmpty())  c_email = mod_Email.getText();
				
				if(error) {
					JOptionPane.showMessageDialog(null, "Error en algun campo");
				} else {
					
					try {
						Conexion nc = new Conexion();
						Connection conn= nc.conectar();
						Statement instruccion = conn.createStatement();
						instruccion.executeUpdate("UPDATE cliente SET nombre = '"+nombre+"', cuilcuit = '"+cuitClie+"',domicilio = '"+domicilio+"', telefono = '"+telefono+"', id_condicion_fiscal = '"+condicion+"', categoria = "+categoria+", nombrecontacto = '"+personaResponsable+"', telefonocontacto = '"+contacto+"', email = '"+c_email+"' WHERE id_cliente ="+idcliente);
						JOptionPane.showMessageDialog(null, "Modificacion realizada con exito");
						nc.desconectar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Todos los campos completados correctamente");
				}
			}
		});
		btnGuardar.setBounds(273, 294, 89, 23);
		contentPane.add(btnGuardar);
		
		
		
		
		
	}
}
