package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Toolkit;

public class NuevoProveedor extends JFrame {
	private JTextField textFieldRazonSocial;
	private JTextField textFieldCuit;
	private JTextField textFieldDomicilio;
	private JTextField textFieldTelefono;
	private JTextField textFieldCategoria;
	private JTextField textFieldPersResponsable;
	private JTextField textFieldContacto;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		
		
		public void run() {
			try {
				NuevoProveedor frame = new NuevoProveedor();
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
	public NuevoProveedor() throws SQLException {
		setTitle("Alta de Proveedores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoProveedor.class.getResource("/logos/logo4.png")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel labelRazon = new JLabel("Campo Inv\u00E1lido");
		labelRazon.setBounds(309, 51, 125, 14);
		contentPane.add(labelRazon);
		labelRazon.setVisible(false);
		
		JLabel labelCuit = new JLabel("Campo Inv\u00E1lido");
		labelCuit.setBounds(309, 78, 125, 14);
		contentPane.add(labelCuit);
		labelCuit.setVisible(false);
		
		JLabel labelCondicion = new JLabel("Campo Inv\u00E1lido");
		labelCondicion.setBounds(438, 137, 125, 22);
		contentPane.add(labelCondicion);
		labelCondicion.setVisible(false);
		
		JLabel labelCategoria = new JLabel("Campo Inv\u00E1lido");
		labelCategoria.setBounds(281, 208, 125, 14);
		contentPane.add(labelCategoria);
		labelCategoria.setVisible(false);
		
		contentPane.setLayout(null);
		JLabel lblNombreORazn = new JLabel("Nombre o Raz\u00F3n Social");
		lblNombreORazn.setBounds(10, 51, 173, 14);
		contentPane.add(lblNombreORazn);

		JLabel lblCuitOCuil = new JLabel("CUIT o CUIL");
		lblCuitOCuil.setBounds(10, 78, 173, 14);
		contentPane.add(lblCuitOCuil);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 105, 173, 14);
		contentPane.add(lblDomicilio);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 145, 173, 14);
		contentPane.add(lblTelefono);

		JLabel lblCondicionAnteEl = new JLabel("Condicion ante el IVA");
		lblCondicionAnteEl.setBounds(10, 181, 173, 14);
		contentPane.add(lblCondicionAnteEl);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 205, 173, 20);
		contentPane.add(lblCategora);

		JLabel lblPersonaResponsable = new JLabel("Persona Responsable");
		lblPersonaResponsable.setBounds(10, 241, 173, 14);
		contentPane.add(lblPersonaResponsable);

		JLabel lblContactoresponsable = new JLabel("Contacto Responsable");
		lblContactoresponsable.setBounds(10, 274, 173, 14);
		contentPane.add(lblContactoresponsable);

		textFieldRazonSocial = new JTextField();
		textFieldRazonSocial.setBounds(195, 48, 96, 20);
		contentPane.add(textFieldRazonSocial);
		textFieldRazonSocial.setColumns(10);

		textFieldCuit = new JTextField();
		textFieldCuit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
				String contenido = textFieldCuit.getText();
				if(contenido.length()>=11) {
					e.consume();
				}
			}
		});
		textFieldCuit.setBounds(195, 75, 96, 20);
		contentPane.add(textFieldCuit);
		textFieldCuit.setColumns(10);

		textFieldDomicilio = new JTextField();
		textFieldDomicilio.setBounds(195, 102, 140, 20);
		contentPane.add(textFieldDomicilio);
		textFieldDomicilio.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(193, 138, 156, 20);
		contentPane.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		textFieldCategoria = new JTextField();
		textFieldCategoria.setBounds(193, 205, 52, 20);
		contentPane.add(textFieldCategoria);
		textFieldCategoria.setColumns(10);

		textFieldPersResponsable = new JTextField();
		textFieldPersResponsable.setBounds(195, 238, 191, 20);
		contentPane.add(textFieldPersResponsable);
		textFieldPersResponsable.setColumns(10);

		textFieldContacto = new JTextField();
		textFieldContacto.setBounds(194, 271, 173, 20);
		contentPane.add(textFieldContacto);
		textFieldContacto.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(193, 173, 231, 22);
		contentPane.add(comboBox);

		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from condicion_fiscal");
		while(resultado.next()) {
			comboBox.addItem(resultado.getString("descripcion"));
		}
		nc.desconectar();
		
		JButton btnGuardarProveedor = new JButton("Guardar Proveedor");
		btnGuardarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre=null;
				String cuitProv=null;
				String domicilio=null;
				String telefono=null;
				String categoria=null;
				String personaResponsable=null;
				String contacto=null;
				Integer condicion=null;
				Boolean error=false;
				
				if(textFieldRazonSocial.getText().isEmpty()) {
					error=true;
					labelRazon.setVisible(true);
				} else {
					nombre = textFieldRazonSocial.getText();
					labelRazon.setVisible(false);
				}
				if(textFieldCuit.getText().isEmpty() || textFieldCuit.getText().length()!=11) {
					error=true;
					labelCuit.setVisible(true);
				} else {
					cuitProv = textFieldCuit.getText();
					labelCuit.setVisible(false);
				}
				if(textFieldCategoria.getText().isEmpty()) {
					error=true;
					labelCategoria.setVisible(true);
				} else {
					categoria = textFieldCategoria.getText();
					labelCategoria.setVisible(false);
				}
				if(comboBox.getSelectedItem().equals("")) {
					error=true;
					labelRazon.setVisible(true);
				} else {
					condicion = comboBox.getSelectedIndex()+1;
					labelRazon.setVisible(false);
				}
				
				if(!textFieldDomicilio.getText().isEmpty()) domicilio = textFieldDomicilio.getText();
				if(!textFieldTelefono.getText().isEmpty()) telefono = textFieldTelefono.getText();
				if(!textFieldPersResponsable.getText().isEmpty()) personaResponsable = textFieldPersResponsable.getText();
				if(!textFieldContacto.getText().isEmpty()) contacto = textFieldContacto.getText();
				StringBuilder objetivo = new StringBuilder(cuitProv);
				objetivo = objetivo.insert(2,"-");
				objetivo = objetivo.insert(objetivo.length()-1, "-");
				cuitProv=objetivo.toString();
				
				
				if(error) {
					JOptionPane.showMessageDialog(null, "Error en algun campo");
				} else {
					try {
						Conexion nc = new Conexion();
						Connection conn = nc.conectar();
						Statement instruccion;
						instruccion = conn.createStatement();
						instruccion.execute("spnuevoproveedor '"+nombre+"', '"+cuitProv+"', '"+domicilio+"', '"+telefono+"', "+condicion+", "+categoria+", '"+personaResponsable+"', '"+contacto+"', NULL, 0");
						JOptionPane.showMessageDialog(null, "Proveedor Agregado Correctamente");
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				
				try {
					dispose();
					NuevoProveedor frame;
					frame = new NuevoProveedor();
					frame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnGuardarProveedor.setBounds(182, 304, 167, 23);
		contentPane.add(btnGuardarProveedor);
		
		JLabel lblNewLabel = new JLabel("Alta de Proveedores");
		lblNewLabel.setBounds(189, 13, 125, 16);
		contentPane.add(lblNewLabel);

		


	}
}
