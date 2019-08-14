package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JButton;

public class CrearEmpresa extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearEmpresa dialog = new CrearEmpresa();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public CrearEmpresa() {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		
		JLabel lblNombreDe = new JLabel("Raz�n Social");
		lblNombreDe.setBounds(10, 11, 75, 14);
		getContentPane().add(lblNombreDe);
		
		JLabel lblNombreDeFantasa = new JLabel("Nombre de Fantas\u00EDa");
		lblNombreDeFantasa.setBounds(10, 36, 107, 14);
		getContentPane().add(lblNombreDeFantasa);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 61, 49, 14);
		getContentPane().add(lblCuit);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 86, 49, 14);
		getContentPane().add(lblDomicilio);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 111, 49, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblNewLabel = new JLabel("Condicion ante el IVA");
		lblNewLabel.setBounds(10, 136, 107, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNumeroDeIngresos = new JLabel("Numero de Ingresos Brutos");
		lblNumeroDeIngresos.setBounds(10, 161, 135, 14);
		getContentPane().add(lblNumeroDeIngresos);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio de Actividades");
		lblFechaDeInicio.setBounds(10, 186, 150, 14);
		getContentPane().add(lblFechaDeInicio);
		
		JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos de venta");
		lblCantidadDePuntos.setBounds(10, 211, 150, 14);
		getContentPane().add(lblCantidadDePuntos);
		
		JLabel lblMesDeInicio = new JLabel("Mes de Inicio de Actividades");
		lblMesDeInicio.setBounds(10, 236, 150, 14);
		getContentPane().add(lblMesDeInicio);
		
		textField = new JTextField();
		textField.setBounds(180, 11, 96, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 33, 96, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(180, 58, 96, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(180, 83, 96, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(180, 108, 96, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(180, 132, 96, 22);
		getContentPane().add(comboBox);
		
		textField_5 = new JTextField();
		textField_5.setBounds(180, 158, 96, 20);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(180, 183, 96, 20);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(180, 208, 96, 20);
		getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(180, 232, 96, 22);
		getContentPane().add(comboBox_1);
		
		JButton btnCrearEmpresa = new JButton("Crear Empresa");
		btnCrearEmpresa.setBounds(42, 299, 124, 23);
		getContentPane().add(btnCrearEmpresa);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(247, 299, 107, 23);
		getContentPane().add(btnCerrar);

	}
}
