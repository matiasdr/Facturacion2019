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
import javax.swing.JLabel;

public class NuevoProveedor extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
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
	 */
	public NuevoProveedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		JLabel lblNombreORazn = new JLabel("Nombre o Raz\u00F3n Social");
		lblNombreORazn.setBounds(10, 11, 125, 14);
		contentPane.add(lblNombreORazn);

		JLabel lblCuitOCuil = new JLabel("CUIT o CUIL");
		lblCuitOCuil.setBounds(10, 36, 91, 14);
		contentPane.add(lblCuitOCuil);

		JLabel lblNewLabel = new JLabel("Domicilio");
		lblNewLabel.setBounds(10, 61, 49, 14);
		contentPane.add(lblNewLabel);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 86, 49, 14);
		contentPane.add(lblTelefono);

		JLabel lblCondicionAnteEl = new JLabel("Condicion ante el IVA");
		lblCondicionAnteEl.setBounds(10, 111, 106, 14);
		contentPane.add(lblCondicionAnteEl);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 136, 49, 14);
		contentPane.add(lblCategora);

		JLabel lblPersonaResponsable = new JLabel("Persona Responsable");
		lblPersonaResponsable.setBounds(10, 161, 125, 14);
		contentPane.add(lblPersonaResponsable);

		JLabel lblContactoresponsable = new JLabel("Contacto (Responsable)");
		lblContactoresponsable.setBounds(10, 186, 125, 14);
		contentPane.add(lblContactoresponsable);

		textField = new JTextField();
		textField.setBounds(193, 8, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(193, 33, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(193, 58, 96, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(193, 83, 96, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(193, 133, 96, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(193, 158, 96, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(193, 183, 96, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(193, 107, 96, 22);
		contentPane.add(comboBox);

		JButton btnGuardarProveedor = new JButton("Guardar Proveedor");
		btnGuardarProveedor.setBounds(140, 214, 138, 23);
		contentPane.add(btnGuardarProveedor);
	}

}
