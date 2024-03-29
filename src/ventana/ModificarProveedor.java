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
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Toolkit;

public class ModificarProveedor extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldRazon;
	private JTextField textFieldCuit;
	private JTextField textFieldDomicilio;
	private JTextField textFieldTelefono;
	private JTextField textFieldCategoria;
	private JTextField textFieldPersResp;
	private JTextField textFieldContacto;
	private JPanel contentPane;
	private Integer idProvElegido;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarProveedor frame = new ModificarProveedor();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public ModificarProveedor() throws SQLException {
		setTitle("Modificar Datos del  Proveedor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarProveedor.class.getResource("/logos/logo4.png")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);


		
		JLabel lblErrorRazon = new JLabel("Campo Invalido");
		lblErrorRazon.setBounds(394, 59, 103, 14);
		contentPane.add(lblErrorRazon);
		lblErrorRazon.setVisible(false);
		
		JLabel labelErrorCuit = new JLabel("Campo Invalido");
		labelErrorCuit.setBounds(394, 84, 103, 14);
		contentPane.add(labelErrorCuit);
		labelErrorCuit.setVisible(false);
		
		JLabel labelErrorCondicion = new JLabel("Campo Invalido");
		labelErrorCondicion.setBounds(394, 159, 103, 14);
		contentPane.add(labelErrorCondicion);
		labelErrorCondicion.setVisible(false);
		
		JLabel labelErrorCategoria = new JLabel("Campo Invalido");
		labelErrorCategoria.setBounds(394, 184, 103, 14);
		contentPane.add(labelErrorCategoria);
		labelErrorCategoria.setVisible(false);
		
		JLabel lblNombreORazn = new JLabel("Nombre o Raz\u00F3n Social");
		lblNombreORazn.setBounds(42, 59, 170, 14);
		contentPane.add(lblNombreORazn);

		JLabel lblCuitOCuil = new JLabel("CUIT o CUIL");
		lblCuitOCuil.setBounds(42, 84, 170, 14);
		contentPane.add(lblCuitOCuil);

		JLabel lblNewLabel = new JLabel("Domicilio");
		lblNewLabel.setBounds(42, 109, 170, 14);
		contentPane.add(lblNewLabel);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(42, 134, 170, 14);
		contentPane.add(lblTelefono);

		JLabel lblCondicionAnteEl = new JLabel("Condicion ante el IVA");
		lblCondicionAnteEl.setBounds(42, 159, 170, 14);
		contentPane.add(lblCondicionAnteEl);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(42, 184, 170, 14);
		contentPane.add(lblCategora);

		JLabel lblPersonaResponsable = new JLabel("Persona Responsable");
		lblPersonaResponsable.setBounds(42, 209, 170, 14);
		contentPane.add(lblPersonaResponsable);

		JLabel lblContactoresponsable = new JLabel("Contacto (Responsable)");
		lblContactoresponsable.setBounds(42, 234, 170, 14);
		contentPane.add(lblContactoresponsable);

		textFieldRazon = new JTextField();
		textFieldRazon.setBounds(192, 56, 190, 20);
		contentPane.add(textFieldRazon);
		textFieldRazon.setColumns(10);
		
		textFieldCuit = new JTextField();
		textFieldCuit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
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
		textFieldCuit.setBounds(192, 81, 129, 20);
		contentPane.add(textFieldCuit);
		textFieldCuit.setColumns(10);

		textFieldDomicilio = new JTextField();
		textFieldDomicilio.setBounds(192, 106, 156, 20);
		contentPane.add(textFieldDomicilio);
		textFieldDomicilio.setColumns(10);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(192, 131, 129, 20);
		contentPane.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		textFieldCategoria = new JTextField();
		textFieldCategoria.setBounds(192, 181, 44, 20);
		contentPane.add(textFieldCategoria);
		textFieldCategoria.setColumns(10);

		textFieldPersResp = new JTextField();
		textFieldPersResp.setBounds(192, 206, 156, 20);
		contentPane.add(textFieldPersResp);
		textFieldPersResp.setColumns(10);

		textFieldContacto = new JTextField();
		textFieldContacto.setBounds(192, 231, 156, 20);
		contentPane.add(textFieldContacto);
		textFieldContacto.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(192, 155, 156, 22);
		contentPane.add(comboBox);

		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from condicion_fiscal");
		while(resultado.next()) {
			comboBox.addItem(resultado.getString("descripcion"));
		}
		JLabel lblSeleccioneElCliente = new JLabel("Seleccione el Proveedor");
		lblSeleccioneElCliente.setBounds(42, 11, 170, 14);
		contentPane.add(lblSeleccioneElCliente);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id_prov=null;
				try {
					ElegirProveedor ep;
					ep = new ElegirProveedor(new java.awt.Frame(), true);
					ep.setVisible(true);
					id_prov=ep.getProvElegido();
					idProvElegido=id_prov;
					//textFieldRazon.setText(ep.getProvElegido());
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Conexion nc = new Conexion();
				Connection conec = nc.conectar();
				try {
					Statement instruccion = conec.createStatement();
					ResultSet resultado = instruccion.executeQuery("Select * from proveedor where id_proveedor = "+id_prov);
					// ahora rellenamos todos los campos con los datos de la consulta
					while(resultado.next()) {
					textFieldRazon.setText(resultado.getString("nombre"));
					textFieldCategoria.setText(resultado.getString("categoria"));
					textFieldDomicilio.setText(resultado.getString("domicilio"));
					textFieldPersResp.setText(resultado.getString("nombrecontacto"));
					textFieldContacto.setText(resultado.getString("telefonocontacto"));
					textFieldTelefono.setText(resultado.getString("telefono"));
					int seleccion = resultado.getInt("id_condicion_fiscal");
					comboBox.setSelectedIndex(seleccion-1);
					String cadena = resultado.getString("cuilcuit");
					cadena = cadena.replace("-", "");
					textFieldCuit.setText(cadena);
					
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
				String cuitProv=null;
				String domicilio=null;
				String telefono=null;
				String categoria=null;
				String personaResponsable=null;
				String contacto=null;
				Integer condicion=null;
				Boolean error=false;
				
				if(textFieldRazon.getText().isEmpty()) {
					error=true;
					lblErrorRazon.setVisible(true);
				} else {
					nombre = textFieldRazon.getText();
					lblErrorRazon.setVisible(false);
				}
				
				if(textFieldCuit.getText().isEmpty() || textFieldCuit.getText().length()!=11) {
					error=true;
					labelErrorCuit.setVisible(true);
				} else {
					cuitProv= textFieldCuit.getText();
					labelErrorCuit.setVisible(false);
				}
				
				if(textFieldCategoria.getText().isEmpty()) {
					error = true;
					labelErrorCategoria.setVisible(true);
				} else {
					categoria = textFieldCategoria.getText();
					labelErrorCategoria.setVisible(false);
				}
				
				if(comboBox.getSelectedItem().equals("")) {
					error = true;
					labelErrorCondicion.setVisible(true);
				} else {
					condicion = comboBox.getSelectedIndex()+1;
					labelErrorCondicion.setVisible(false);
				}
				
				if(!textFieldDomicilio.getText().isEmpty()) domicilio = textFieldDomicilio.getText();
				if(!textFieldTelefono.getText().isEmpty()) telefono = textFieldTelefono.getText();
				if(!textFieldPersResp.getText().isEmpty()) personaResponsable = textFieldPersResp.getText();
				if(!textFieldContacto.getText().isEmpty()) contacto = textFieldContacto.getText();
				
				
				if(error) {
					JOptionPane.showMessageDialog(null, "Error en algun campo");
				} else {
					StringBuilder objetivo = new StringBuilder(cuitProv);
					objetivo = objetivo.insert(2,"-");
					objetivo = objetivo.insert(objetivo.length()-1, "-");
					cuitProv=objetivo.toString();
					
					try {
						Conexion nc = new Conexion();
						Connection conn= nc.conectar();
						Statement instruccion = conn.createStatement();
						instruccion.executeUpdate("UPDATE proveedor SET nombre = '"+nombre+"',cuilcuit = '"+cuitProv+"',domicilio = '"+domicilio+"',telefono = '"+telefono+"',id_condicion_fiscal = "+condicion+",categoria = "+categoria+",nombrecontacto = '"+personaResponsable+"',telefonocontacto = '"+contacto+"',email = 'NULL' WHERE id_proveedor ="+idProvElegido);
						JOptionPane.showMessageDialog(null, "Modificacion realizada con exito");
						nc.desconectar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						dispose();
						ModificarProveedor frame;
						frame = new ModificarProveedor();
						frame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnGuardar.setBounds(179, 261, 89, 23);
		contentPane.add(btnGuardar);
		
	}
	
}
