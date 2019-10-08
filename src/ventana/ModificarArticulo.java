package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ModificarArticulo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDescripcion;
	private JTextField textFieldEAN;
	private JTextField textFieldStock;
	private JTextField textFieldPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarArticulo frame = new ModificarArticulo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ModificarArticulo() throws SQLException {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreODescripcion = new JLabel("Nombre o Descripcion del art\u00EDculo");
		lblNombreODescripcion.setBounds(74, 59, 167, 14);
		contentPane.add(lblNombreODescripcion);
		
		JLabel lblEan = new JLabel("EAN");
		lblEan.setBounds(74, 84, 49, 14);
		contentPane.add(lblEan);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(74, 109, 49, 14);
		contentPane.add(lblStock);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(74, 134, 49, 14);
		contentPane.add(lblPrecio);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(249, 56, 96, 20);
		contentPane.add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldEAN = new JTextField();
		textFieldEAN.setBounds(249, 81, 96, 20);
		contentPane.add(textFieldEAN);
		textFieldEAN.setColumns(10);
		
		textFieldStock = new JTextField();
		textFieldStock.setBounds(249, 106, 96, 20);
		contentPane.add(textFieldStock);
		textFieldStock.setColumns(10);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(249, 131, 96, 20);
		contentPane.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(249, 162, 96, 20);
		contentPane.add(comboBox);
		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from proveedor");
		while(resultado.next()) {
			comboBox.addItem(resultado.getString("nombre"));
		}
		
		JButton btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.setBounds(144, 199, 148, 23);
		contentPane.add(btnGuardarCambios);
		
		JButton btnBuscarArtculo = new JButton("Buscar art\u00EDculo");
		btnBuscarArtculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id_art=null;
				
				try {
					ListadoArticulos la = new ListadoArticulos(new java.awt.Frame(), true);
					la.setVisible(true);
					id_art= la.getIdArt();
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Conexion nc = new Conexion();
				Connection conec = nc.conectar();
				try {
					Statement instruccion = conec.createStatement();
					ResultSet resultado = instruccion.executeQuery("Select * from articulo where id_articulo = "+id_art);
					// ahora rellenamos todos los campos con los datos de la consulta
					while(resultado.next()) {
					textFieldDescripcion.setText(resultado.getString("descripcion"));
					textFieldEAN.setText(resultado.getString("ean"));
					textFieldStock.setText(resultado.getString("cantidad"));
					textFieldPrecio.setText(resultado.getString("precio_venta"));
					int seleccion = resultado.getInt("id_proveedor");
					comboBox.setSelectedIndex(seleccion-1);
					
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				nc.desconectar();
				
			}
		});
		btnBuscarArtculo.setBounds(144, 11, 134, 23);
		contentPane.add(btnBuscarArtculo);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(74, 159, 49, 14);
		contentPane.add(lblProveedor);
		
	
	}
}
