package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	 */
	public ModificarProveedor() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);


		
		JLabel lblErrorRazon = new JLabel("Campo Invalido");
		lblErrorRazon.setBounds(331, 59, 103, 14);
		contentPane.add(lblErrorRazon);
		lblErrorRazon.setVisible(false);
		
		JLabel labelErrorCuit = new JLabel("Campo Invalido");
		labelErrorCuit.setBounds(331, 84, 103, 14);
		contentPane.add(labelErrorCuit);
		labelErrorCuit.setVisible(false);
		
		JLabel labelErrorCondicion = new JLabel("Campo Invalido");
		labelErrorCondicion.setBounds(331, 159, 103, 14);
		contentPane.add(labelErrorCondicion);
		labelErrorCondicion.setVisible(false);
		
		JLabel labelErrorCategoria = new JLabel("Campo Invalido");
		labelErrorCategoria.setBounds(331, 184, 103, 14);
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
		textFieldRazon.setBounds(225, 56, 96, 20);
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
		textFieldCuit.setBounds(225, 81, 96, 20);
		contentPane.add(textFieldCuit);
		textFieldCuit.setColumns(10);

		textFieldDomicilio = new JTextField();
		textFieldDomicilio.setBounds(225, 106, 96, 20);
		contentPane.add(textFieldDomicilio);
		textFieldDomicilio.setColumns(10);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(225, 131, 96, 20);
		contentPane.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		textFieldCategoria = new JTextField();
		textFieldCategoria.setBounds(225, 181, 96, 20);
		contentPane.add(textFieldCategoria);
		textFieldCategoria.setColumns(10);

		textFieldPersResp = new JTextField();
		textFieldPersResp.setBounds(225, 206, 96, 20);
		contentPane.add(textFieldPersResp);
		textFieldPersResp.setColumns(10);

		textFieldContacto = new JTextField();
		textFieldContacto.setBounds(225, 231, 96, 20);
		contentPane.add(textFieldContacto);
		textFieldContacto.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(225, 155, 96, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Resp Inscripto");
		comboBox.addItem("Consumidor final");
		comboBox.addItem("Monotributista");
		comboBox.addItem("Exento");
		comboBox.addItem("");

		JLabel lblSeleccioneElProov = new JLabel("Seleccione el Proveedor");
		lblSeleccioneElProov.setBounds(42, 11, 170, 14);
		contentPane.add(lblSeleccioneElProov);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirProveedor ep= new ElegirProveedor(new java.awt.Frame(), true);
				ep.setVisible(true);
				
				textFieldRazon.setText(ep.getProvElegido());
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
				String condicion=null;
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
					condicion = comboBox.getSelectedItem().toString();
					labelErrorCondicion.setVisible(false);
				}
				
				if(!textFieldDomicilio.getText().isEmpty()) domicilio = textFieldDomicilio.getText();
				if(!textFieldTelefono.getText().isEmpty()) telefono = textFieldTelefono.getText();
				if(!textFieldPersResp.getText().isEmpty()) personaResponsable = textFieldPersResp.getText();
				if(!textFieldContacto.getText().isEmpty()) contacto = textFieldContacto.getText();
				
				if(error) {
					JOptionPane.showMessageDialog(null, "Error en algun campo");
				} else {
					JOptionPane.showMessageDialog(null, "Todos los campos completados correctamente");
				}
				
			}
		});
		btnGuardar.setBounds(331, 230, 89, 23);
		contentPane.add(btnGuardar);
		
	}
	
}
