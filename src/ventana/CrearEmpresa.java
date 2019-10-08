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
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombreDe = new JLabel("Raz�n Social");
		lblNombreDe.setBounds(10, 11, 75, 14);
		contentPane.add(lblNombreDe);

		JLabel lblNombreDeFantasa = new JLabel("Nombre de Fantas\u00EDa");
		lblNombreDeFantasa.setBounds(10, 36, 107, 14);
		contentPane.add(lblNombreDeFantasa);

		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 61, 49, 14);
		contentPane.add(lblCuit);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 86, 49, 14);
		contentPane.add(lblDomicilio);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 111, 49, 14);
		contentPane.add(lblTelefono);

		JLabel lblNewLabel = new JLabel("Condicion ante el IVA");
		lblNewLabel.setBounds(10, 136, 107, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNumeroDeIngresos = new JLabel("Numero de Ingresos Brutos");
		lblNumeroDeIngresos.setBounds(10, 161, 135, 14);
		contentPane.add(lblNumeroDeIngresos);

		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio de Actividades");
		lblFechaDeInicio.setBounds(10, 186, 150, 14);
		contentPane.add(lblFechaDeInicio);

		JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos de venta");
		lblCantidadDePuntos.setBounds(10, 211, 150, 14);
		contentPane.add(lblCantidadDePuntos);

		JLabel lblMesDeInicio = new JLabel("Mes de Inicio de Actividades");
		lblMesDeInicio.setBounds(10, 236, 150, 14);
		contentPane.add(lblMesDeInicio);

		RazonSocial = new JTextField();
		RazonSocial.setBounds(180, 11, 96, 20);
		contentPane.add(RazonSocial);
		RazonSocial.setColumns(10);

		NomFantacia = new JTextField();
		NomFantacia.setBounds(180, 33, 96, 20);
		contentPane.add(NomFantacia);
		NomFantacia.setColumns(10);

		Cuit = new JTextField();
		Cuit.setBounds(180, 58, 96, 20);
		contentPane.add(Cuit);
		Cuit.setColumns(10);

		Domicilio = new JTextField();
		Domicilio.setBounds(180, 83, 96, 20);
		contentPane.add(Domicilio);
		Domicilio.setColumns(10);

		Telefono = new JTextField();
		Telefono.setBounds(180, 108, 96, 20);
		contentPane.add(Telefono);
		Telefono.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(180, 132, 96, 22);
		contentPane.add(comboBox);

		NumIngBrutos = new JTextField();
		NumIngBrutos.setBounds(180, 158, 96, 20);
		contentPane.add(NumIngBrutos);
		NumIngBrutos.setColumns(10);

		FecInAct = new JTextField();
		FecInAct.setBounds(180, 183, 96, 20);
		contentPane.add(FecInAct);
		FecInAct.setColumns(10);

		CantPuntVtas = new JTextField();
		CantPuntVtas.setBounds(180, 208, 96, 20);
		contentPane.add(CantPuntVtas);
		CantPuntVtas.setColumns(10);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(180, 232, 96, 22);
		contentPane.add(comboBox_1);

		JButton btnCrearEmpresa = new JButton("Crear Empresa");
		btnCrearEmpresa.setBounds(42, 299, 124, 23);
		contentPane.add(btnCrearEmpresa);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(247, 299, 107, 23);
		contentPane.add(btnCerrar);

	}
}
