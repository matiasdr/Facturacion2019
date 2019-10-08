package ventana;

import java.awt.BorderLayout;
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
		try {
			NuevoProveedor frame = new NuevoProveedor();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public NuevoProveedor() throws SQLException {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel labelRazon = new JLabel("Campo Inv\u00E1lido");
		labelRazon.setBounds(299, 11, 125, 14);
		contentPane.add(labelRazon);
		labelRazon.setVisible(false);
		
		JLabel labelCuit = new JLabel("Campo Inv\u00E1lido");
		labelCuit.setBounds(299, 36, 125, 14);
		contentPane.add(labelCuit);
		labelCuit.setVisible(false);
		
		JLabel labelCondicion = new JLabel("Campo Inv\u00E1lido");
		labelCondicion.setBounds(299, 111, 125, 14);
		contentPane.add(labelCondicion);
		labelCondicion.setVisible(false);
		
		JLabel labelCategoria = new JLabel("Campo Inv\u00E1lido");
		labelCategoria.setBounds(299, 136, 125, 14);
		contentPane.add(labelCategoria);
		labelCategoria.setVisible(false);
		
		contentPane.setLayout(null);
		JLabel lblNombreORazn = new JLabel("Nombre o Raz\u00F3n Social");
		lblNombreORazn.setBounds(10, 11, 173, 14);
		contentPane.add(lblNombreORazn);

		JLabel lblCuitOCuil = new JLabel("CUIT o CUIL");
		lblCuitOCuil.setBounds(10, 36, 173, 14);
		contentPane.add(lblCuitOCuil);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 61, 173, 14);
		contentPane.add(lblDomicilio);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 86, 173, 14);
		contentPane.add(lblTelefono);

		JLabel lblCondicionAnteEl = new JLabel("Condicion ante el IVA");
		lblCondicionAnteEl.setBounds(10, 111, 173, 14);
		contentPane.add(lblCondicionAnteEl);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 136, 173, 14);
		contentPane.add(lblCategora);

		JLabel lblPersonaResponsable = new JLabel("Persona Responsable");
		lblPersonaResponsable.setBounds(10, 161, 173, 14);
		contentPane.add(lblPersonaResponsable);

		JLabel lblContactoresponsable = new JLabel("Contacto (Responsable)");
		lblContactoresponsable.setBounds(10, 186, 173, 14);
		contentPane.add(lblContactoresponsable);

		textFieldRazonSocial = new JTextField();
		textFieldRazonSocial.setBounds(193, 8, 96, 20);
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
		textFieldCuit.setBounds(193, 33, 96, 20);
		contentPane.add(textFieldCuit);
		textFieldCuit.setColumns(10);

		textFieldDomicilio = new JTextField();
		textFieldDomicilio.setBounds(193, 58, 96, 20);
		contentPane.add(textFieldDomicilio);
		textFieldDomicilio.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(193, 83, 96, 20);
		contentPane.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		textFieldCategoria = new JTextField();
		textFieldCategoria.setBounds(193, 133, 96, 20);
		contentPane.add(textFieldCategoria);
		textFieldCategoria.setColumns(10);

		textFieldPersResponsable = new JTextField();
		textFieldPersResponsable.setBounds(193, 158, 96, 20);
		contentPane.add(textFieldPersResponsable);
		textFieldPersResponsable.setColumns(10);

		textFieldContacto = new JTextField();
		textFieldContacto.setBounds(193, 183, 96, 20);
		contentPane.add(textFieldContacto);
		textFieldContacto.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(193, 107, 96, 22);
		contentPane.add(comboBox);

		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from condicion_fiscal");
		while(resultado.next()) {
			comboBox.addItem(resultado.getString("descripcion"));
		}
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
				String condicion=null;
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
					condicion = comboBox.getSelectedItem().toString();
					labelRazon.setVisible(false);
				}
				
				if(!textFieldDomicilio.getText().isEmpty()) domicilio = textFieldDomicilio.getText();
				if(!textFieldTelefono.getText().isEmpty()) telefono = textFieldTelefono.getText();
				if(!textFieldPersResponsable.getText().isEmpty()) personaResponsable = textFieldPersResponsable.getText();
				if(!textFieldContacto.getText().isEmpty()) contacto = textFieldContacto.getText();
				
				if(error) {
					JOptionPane.showMessageDialog(null, "Error en algun campo");
				} else {
					JOptionPane.showMessageDialog(null, "Todos los campos completados correctamente");
				}
				
				
			}
		});
		btnGuardarProveedor.setBounds(140, 214, 167, 23);
		contentPane.add(btnGuardarProveedor);

		


	}
}
