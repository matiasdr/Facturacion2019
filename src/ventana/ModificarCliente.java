package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ModificarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarCliente dialog = new ModificarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarCliente() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNombreORazn = new JLabel("Nombre o Raz\u00F3n Social");
		lblNombreORazn.setBounds(42, 59, 125, 14);
		getContentPane().add(lblNombreORazn);
		
		JLabel lblCuitOCuil = new JLabel("CUIT o CUIL");
		lblCuitOCuil.setBounds(42, 84, 91, 14);
		getContentPane().add(lblCuitOCuil);
		
		JLabel lblNewLabel = new JLabel("Domicilio");
		lblNewLabel.setBounds(42, 109, 49, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(42, 134, 49, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblCondicionAnteEl = new JLabel("Condicion ante el IVA");
		lblCondicionAnteEl.setBounds(42, 159, 106, 14);
		getContentPane().add(lblCondicionAnteEl);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(42, 184, 49, 14);
		getContentPane().add(lblCategora);
		
		JLabel lblPersonaResponsable = new JLabel("Persona Responsable");
		lblPersonaResponsable.setBounds(42, 209, 125, 14);
		getContentPane().add(lblPersonaResponsable);
		
		JLabel lblContactoresponsable = new JLabel("Contacto (Responsable)");
		lblContactoresponsable.setBounds(42, 234, 125, 14);
		getContentPane().add(lblContactoresponsable);
		
		textField = new JTextField();
		textField.setBounds(225, 56, 96, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(225, 81, 96, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(225, 106, 96, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(225, 131, 96, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(225, 181, 96, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(225, 206, 96, 20);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(225, 231, 96, 20);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(225, 155, 96, 22);
		getContentPane().add(comboBox);
		
		JLabel lblSeleccioneElCliente = new JLabel("Seleccione el Cliente");
		lblSeleccioneElCliente.setBounds(42, 11, 144, 14);
		getContentPane().add(lblSeleccioneElCliente);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(225, 11, 89, 23);
		getContentPane().add(btnBuscar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(331, 230, 89, 23);
		getContentPane().add(btnGuardar);

	}
}
