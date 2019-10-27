package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Login extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private String permisoUsuario;
	private String nombreUsuario;
	public String ipServidor;
	private JTextField textFieldipServidor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(new java.awt.Frame(), true);
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
	public Login(java.awt.Frame parent, boolean modal) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super(parent, modal);
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/logos/logo4.png")));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNombreDeUsuario.setBounds(41, 117, 164, 14);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblClave.setBounds(41, 162, 55, 14);
		contentPane.add(lblClave);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		textFieldUsuario.setBounds(198, 115, 96, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		passwordField.setBounds(198, 160, 96, 20);
		contentPane.add(passwordField);
		
		JLabel lblCredencialesInvalidas = new JLabel("Credenciales Invalidas");
		lblCredencialesInvalidas.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCredencialesInvalidas.setForeground(Color.RED);
		lblCredencialesInvalidas.setBounds(122, 191, 155, 14);
		lblCredencialesInvalidas.setVisible(false);
		contentPane.add(lblCredencialesInvalidas);
		
	
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user = textFieldUsuario.getText();
				char[] pass = passwordField.getPassword();
				char[] correct=null;
				Integer cargo=0;
				Conexion nc = new Conexion();
				Connection connec = nc.conectar();
				try {
					Statement instruccion = connec.createStatement();
					ResultSet resultado = instruccion.executeQuery("Select * from usuario where mail = '"+user+"'");
					while(resultado.next()) {
						String aux = resultado.getString("clave").trim();
						correct = aux.toCharArray();
						cargo = resultado.getInt("id_cargo");
						nombreUsuario= resultado.getString("nombre").trim();
					}
					nc.desconectar();
					if(Arrays.equals(pass, correct)) {
						JOptionPane.showMessageDialog(null, "Credenciales Correctas! Bienvenid@ "+nombreUsuario);
					} else {
					//	JOptionPane.showMessageDialog(null, "Credenciales Incorrectas");
						lblCredencialesInvalidas.setVisible(true);
						cargo=0;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "UsuarioIncorrecto");
			//		e1.printStackTrace();
				}
				if(cargo == 0) {
					passwordField.selectAll();
					passwordField.setText("");
				} else {
					switch(cargo) {
					case 1: permisoUsuario="administrador";
							break;
					case 2:	permisoUsuario="cajero";
							break;
					case 3: break;
					case 4:	permisoUsuario="secretaria";
							break;
					default:break;
					}
					setVisible(false);
				}
				
				
			}
		});
		btnIngresar.setBounds(164, 228, 89, 23);
		contentPane.add(btnIngresar);
		
		JLabel lblNewLabel = new JLabel("Ingrese sus Credenciales");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(112, 13, 188, 30);
		contentPane.add(lblNewLabel);
		
		
		
	}
	
	public String getPermisoUsuario() {
		return permisoUsuario;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public String getIpServidor() {
		return ipServidor;
	}
}
