package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JRadioButton;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

public class GenerarReciboCliente extends JFrame {
	public GenerarReciboCliente() {
		setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerarReciboCliente.class.getResource("/logos/logo4.png")));
		setTitle("Recibos para Clientes ");
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().setLayout(null);
		
		JLabel LblTitulo = new JLabel("Generar Recibo de Cobro");
		LblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		LblTitulo.setBounds(274, 13, 188, 25);
		getContentPane().add(LblTitulo);
		
		JLabel lblSelecCliente = new JLabel("Selecione el Cliente :");
		lblSelecCliente.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSelecCliente.setBounds(30, 75, 145, 16);
		getContentPane().add(lblSelecCliente);
		
		JComboBox cBCliente = new JComboBox();
		cBCliente.setBounds(170, 172, 188, 22);
		getContentPane().add(cBCliente);
		
		JButton btnBusCliente = new JButton("Iniciar Busqueda");
		btnBusCliente.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnBusCliente.setBounds(176, 71, 144, 25);
		getContentPane().add(btnBusCliente);
		
		JLabel lblDatos = new JLabel("Datos del Comprobantes :");
		lblDatos.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDatos.setBounds(30, 116, 171, 25);
		getContentPane().add(lblDatos);
		
		tFdatos = new JTextField();
		tFdatos.setBounds(202, 117, 188, 22);
		getContentPane().add(tFdatos);
		tFdatos.setColumns(10);
		
		JLabel lblMediodePago = new JLabel("Medios de Pago :");
		lblMediodePago.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMediodePago.setBounds(30, 175, 145, 16);
		getContentPane().add(lblMediodePago);
		
		JLabel lblComprobantes = new JLabel("Facturas Pendientes");
		lblComprobantes.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblComprobantes.setBounds(481, 143, 133, 16);
		getContentPane().add(lblComprobantes);
		
		JList listFactPend = new JList();
		listFactPend.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listFactPend.setFont(new Font("Times New Roman", Font.BOLD, 12));
		listFactPend.setBounds(405, 172, 290, 138);
		getContentPane().add(listFactPend);
		
		JLabel lblPagoParcial = new JLabel("\u00BFRealiza Pago Parcial?");
		lblPagoParcial.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPagoParcial.setBounds(30, 217, 156, 16);
		getContentPane().add(lblPagoParcial);
		
		JRadioButton rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setFont(new Font("Times New Roman", Font.BOLD, 13));
		rdbtnSi.setBounds(181, 213, 46, 25);
		getContentPane().add(rdbtnSi);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		rdbtnNo.setBounds(229, 213, 46, 25);
		getContentPane().add(rdbtnNo);
		
		JLabel lblImporteParcial = new JLabel("Ingrese Importe Parcial");
		lblImporteParcial.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblImporteParcial.setBounds(30, 262, 156, 16);
		getContentPane().add(lblImporteParcial);
		
		textField = new JTextField();
		textField.setBounds(192, 259, 145, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblResumen = new JLabel("Resumen de Operacion");
		lblResumen.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblResumen.setBounds(331, 334, 162, 16);
		getContentPane().add(lblResumen);
		
		JTabbedPane JtableResumen = new JTabbedPane(JTabbedPane.TOP);
		JtableResumen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		JtableResumen.setBounds(157, 365, 538, 71);
		getContentPane().add(JtableResumen);
		
		JButton btnCalcular = new JButton("Calcular Importe");
		btnCalcular.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnCalcular.setBounds(157, 449, 136, 25);
		getContentPane().add(btnCalcular);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnConfirmar.setBounds(396, 449, 97, 25);
		getContentPane().add(btnConfirmar);
		
		JButton btnEmitirRecibos = new JButton("Emitir Recibo");
		btnEmitirRecibos.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnEmitirRecibos.setBounds(582, 449, 113, 25);
		getContentPane().add(btnEmitirRecibos);
	}
	private JTextField textField_2;
	private JTextField importeParcial;
	private JPanel contentPane;
	private JTextField tFdatos;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarReciboCliente frame = new GenerarReciboCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}