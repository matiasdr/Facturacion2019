package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevoArticulo extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEAN;
	private JTextField txtStock;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoArticulo frame = new NuevoArticulo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NuevoArticulo() {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreODescripcion = new JLabel("Nombre o Descripcion del art\u00EDculo");
		lblNombreODescripcion.setBounds(10, 11, 238, 14);
		contentPane.add(lblNombreODescripcion);
		
		JLabel lblEan = new JLabel("EAN");
		lblEan.setBounds(10, 36, 49, 14);
		contentPane.add(lblEan);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 61, 49, 14);
		contentPane.add(lblStock);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 86, 49, 14);
		contentPane.add(lblPrecio);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(301, 8, 96, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtEAN = new JTextField();
		txtEAN.setBounds(301, 33, 96, 20);
		contentPane.add(txtEAN);
		txtEAN.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(301, 58, 96, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(301, 83, 96, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre=null;
				String ean=null;
				String stock=null;
				String precio=null;
				Boolean error=false;
				if(txtNombre.getText().isEmpty()) {
					error=true;
				} else {
					nombre= txtNombre.getText();
				}
				
				if(txtEAN.getText().isEmpty()) {
					error=true;
				} else {
					ean = txtEAN.getText();
				}
				
				if(!txtStock.getText().isEmpty()) stock = txtStock.getText();
				if(!txtPrecio.getText().isEmpty()) precio = txtPrecio.getText();
				if(error) {
					JOptionPane.showMessageDialog(null, "Error en algun campo");
				} else {
					JOptionPane.showMessageDialog(null, "Todos los campos completados correctamente");
				}
			}
		});
		btnNewButton.setBounds(96, 166, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(293, 166, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
