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
	private JPanel contentPane;
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
	public ModificarCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 348);
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

		mod_cliRazSoc = new JTextField();
		mod_cliRazSoc.setBounds(195, 56, 185, 20);
		contentPane.add(mod_cliRazSoc);
		mod_cliRazSoc.setColumns(10);

		mod_cliCuit = new JTextField();
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

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(198, 155, 96, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Resp Inscripto");
		comboBox.addItem("Consumidor final");
		comboBox.addItem("Monotributista");
		comboBox.addItem("Exento");
		comboBox.addItem("");

		JLabel lblSeleccioneElCliente = new JLabel("Seleccione el Cliente");
		lblSeleccioneElCliente.setBounds(42, 11, 144, 14);
		contentPane.add(lblSeleccioneElCliente);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ElegirCliente ep= new ElegirCliente(new java.awt.Frame(), true);
				ep.setVisible(true);
				
				mod_cliRazSoc.setText(ep.getClienElegido());
				
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
					cuitProv= mod_cliCuit.getText();
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
					condicion = comboBox.getSelectedItem().toString();
					lblerrorCateg.setVisible(false);
				}
				
				if(!mod_cliDom.getText().isEmpty()) domicilio = mod_cliDom.getText();
				if(!mod_cliTelef.getText().isEmpty()) telefono = mod_cliTelef.getText();
				if(!mod_cliResp.getText().isEmpty()) personaResponsable = mod_cliResp.getText();
				if(!mod_cliContacto.getText().isEmpty()) contacto = mod_cliContacto.getText();
				
				if(error) {
					JOptionPane.showMessageDialog(null, "Error en algun campo");
				} else {
					JOptionPane.showMessageDialog(null, "Todos los campos completados correctamente");
				}
			}
		});
		btnGuardar.setBounds(273, 264, 89, 23);
		contentPane.add(btnGuardar);
		
		
		
		
		
	}
}
