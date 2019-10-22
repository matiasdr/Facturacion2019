package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Toolkit;

public class CrearEmpresa extends JFrame {
	private JTextField RazonSocial;
	private JTextField NomFantacia;
	private JTextField Cuit;
	private JTextField Domicilio;
	private JTextField Telefono;
	private JTextField NumIngBrutos;
	private JTextField FecInAct;
	private JTextField CantPuntVtas;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearEmpresa frame = new CrearEmpresa();
					frame.setVisible(true);
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
		setTitle("Crear Empresa");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CrearEmpresa.class.getResource("/logos/logo4.png")));
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombreDe = new JLabel("Raz\u00F3n Social");
		lblNombreDe.setBounds(10, 11, 75, 14);
		contentPane.add(lblNombreDe);

		JLabel lblNombreDeFantasa = new JLabel("Nombre de Fantas\u00EDa");
		lblNombreDeFantasa.setBounds(10, 36, 135, 14);
		contentPane.add(lblNombreDeFantasa);

		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 61, 49, 14);
		contentPane.add(lblCuit);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 86, 75, 14);
		contentPane.add(lblDomicilio);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 111, 75, 14);
		contentPane.add(lblTelefono);

		JLabel lblNewLabel = new JLabel("Condicion ante el IVA");
		lblNewLabel.setBounds(10, 136, 135, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNumeroDeIngresos = new JLabel("Numero de Ingresos Brutos");
		lblNumeroDeIngresos.setBounds(10, 161, 167, 14);
		contentPane.add(lblNumeroDeIngresos);

		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio de Actividades");
		lblFechaDeInicio.setBounds(10, 186, 185, 14);
		contentPane.add(lblFechaDeInicio);

		JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos de venta");
		lblCantidadDePuntos.setBounds(10, 211, 167, 14);
		contentPane.add(lblCantidadDePuntos);

		JLabel lblMesDeInicio = new JLabel("Mes de Inicio de Actividades");
		lblMesDeInicio.setBounds(10, 236, 167, 14);
		contentPane.add(lblMesDeInicio);

		RazonSocial = new JTextField();
		RazonSocial.setBounds(141, 8, 173, 20);
		contentPane.add(RazonSocial);
		RazonSocial.setColumns(10);

		NomFantacia = new JTextField();
		NomFantacia.setBounds(157, 33, 157, 20);
		contentPane.add(NomFantacia);
		NomFantacia.setColumns(10);

		Cuit = new JTextField();
		Cuit.setBounds(157, 58, 157, 20);
		contentPane.add(Cuit);
		Cuit.setColumns(10);

		Domicilio = new JTextField();
		Domicilio.setBounds(157, 83, 157, 20);
		contentPane.add(Domicilio);
		Domicilio.setColumns(10);

		Telefono = new JTextField();
		Telefono.setBounds(157, 108, 157, 20);
		contentPane.add(Telefono);
		Telefono.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(167, 132, 147, 22);
		contentPane.add(comboBox);

		NumIngBrutos = new JTextField();
		NumIngBrutos.setBounds(177, 158, 137, 20);
		contentPane.add(NumIngBrutos);
		NumIngBrutos.setColumns(10);

		FecInAct = new JTextField();
		FecInAct.setBounds(187, 183, 127, 20);
		contentPane.add(FecInAct);
		FecInAct.setColumns(10);

		CantPuntVtas = new JTextField();
		CantPuntVtas.setBounds(189, 208, 125, 20);
		contentPane.add(CantPuntVtas);
		CantPuntVtas.setColumns(10);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(189, 232, 125, 22);
		contentPane.add(comboBox_1);

		JButton btnCrearEmpresa = new JButton("Crear Empresa");
		btnCrearEmpresa.setBounds(42, 299, 124, 23);
		contentPane.add(btnCrearEmpresa);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(247, 299, 107, 23);
		contentPane.add(btnCerrar);

	}
}
