package ventana;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class GenerarRecibo extends JFrame {
	private JTextField textField_2;
	private JTextField importeParcial;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarRecibo frame = new GenerarRecibo();
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
	public GenerarRecibo() {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGeneracinDeRecibos = new JLabel("Generaci\u00F3n de Recibos Por Pagos Hechos a Proveedores");
		lblGeneracinDeRecibos.setBounds(241, 11, 376, 14);
		contentPane.add(lblGeneracinDeRecibos);
		
		ButtonGroup elec = new ButtonGroup();

		JLabel lblSeleccionarProveedor = new JLabel("Seleccionar Proveedor");
		lblSeleccionarProveedor.setBounds(126, 62, 147, 14);
		contentPane.add(lblSeleccionarProveedor);
		
		JLabel lblNombreProveedor = new JLabel("Nombre del Proveedor");
		lblNombreProveedor.setBounds(375, 62, 142, 14);
		contentPane.add(lblNombreProveedor);

		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirProveedor ep;
				try {
					ep = new ElegirProveedor(new java.awt.Frame(), true);
					ep.setVisible(true);
					lblNombreProveedor.setText(ep.getNombreProovedor());
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				// aca deberiasmo llamar a un store procedure para cargar el listado de las facturas de ese proveedor
			}
		});
		button.setBounds(263, 58, 89, 23);
		contentPane.add(button);

		JLabel label = new JLabel("Datos del Comprobante.");
		label.setBounds(126, 103, 142, 14);
		contentPane.add(label);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(261, 100, 142, 20);
		contentPane.add(textField_2);

		JLabel label_2 = new JLabel("Medio de Pago");
		label_2.setBounds(136, 138, 125, 14);
		contentPane.add(label_2);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(263, 134, 142, 22);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("Efectivo");
		comboBox_1.addItem("Tarjeta de Credito");
		comboBox_1.addItem("Transferencia");
		comboBox_1.addItem("Cheque");
		
		JLabel label_3 = new JLabel("\u00BFEs un importe parcial?");
		label_3.setBounds(123, 184, 145, 14);
		contentPane.add(label_3);
		
		importeParcial = new JTextField();
		importeParcial.setColumns(10);
		importeParcial.setBounds(261, 235, 142, 20);
		contentPane.add(importeParcial);

		JRadioButton radioButton = new JRadioButton("Si");
		radioButton.setBounds(327, 180, 48, 23);
		contentPane.add(radioButton);
		radioButton.setSelected(true);

		JRadioButton radioButton_1 = new JRadioButton("No");
		radioButton_1.setBounds(263, 180, 62, 23);
		contentPane.add(radioButton_1);

		ButtonGroup importe = new ButtonGroup();
		importe.add(radioButton);
		importe.add(radioButton_1);
		radioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(radioButton.isSelected()) {
					importeParcial.setEnabled(true);
				}
				
			}
		});
		
		radioButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(radioButton_1.isSelected()) {
					importeParcial.setEnabled(false);
				}
				
			}
		});
		
		JLabel lblIngreseAquiEl = new JLabel("Ingrese aqui el importe Parcial a abonar");
		lblIngreseAquiEl.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseAquiEl.setBounds(38, 223, 197, 45);
		contentPane.add(lblIngreseAquiEl);

		

		JLabel lblSeleccionarFacturaA = new JLabel("Seleccionar Factura a Abonar");
		lblSeleccionarFacturaA.setBounds(471, 106, 209, 14);
		contentPane.add(lblSeleccionarFacturaA);

		JList list_1 = new JList();
		list_1.setBounds(471, 137, 209, 118);
		contentPane.add(list_1);

		JButton button_1 = new JButton("Emitir Recibo");
		button_1.setBounds(497, 397, 142, 23);
		contentPane.add(button_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(241, 299, 416, 73);
		contentPane.add(tabbedPane);
		
		JLabel lblVistaResumidaDe = new JLabel("Vista resumida de la operacion");
		lblVistaResumidaDe.setBounds(271, 274, 346, 14);
		contentPane.add(lblVistaResumidaDe);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(241, 397, 89, 23);
		contentPane.add(btnCalcular);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(375, 397, 89, 23);
		contentPane.add(btnConfirmar);

	}
}
