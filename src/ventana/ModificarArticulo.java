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
	private Integer idArticuloModificado;

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
		nc.desconectar();
		
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(74, 159, 49, 14);
		contentPane.add(lblProveedor);
		
		JLabel labelIVA = new JLabel("IVA del Articulo");
		labelIVA.setBounds(74, 190, 134, 14);
		contentPane.add(labelIVA);
		
		double iva21=21;
		double iva10=10.5;
		double iva27=27;		
		JComboBox comboBoxIVA = new JComboBox();
		comboBoxIVA.setBounds(249, 193, 96, 20);
		contentPane.add(comboBoxIVA);
		comboBoxIVA.addItem(iva21);
		comboBoxIVA.addItem(iva10);
		comboBoxIVA.addItem(iva27);
		
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
					textFieldPrecio.setText(resultado.getString("pvp"));
					int seleccion = resultado.getInt("id_proveedor");
					comboBox.setSelectedIndex(seleccion-1);
					double ivas = resultado.getDouble("ivaporcent");
					comboBoxIVA.setSelectedItem(ivas);
					idArticuloModificado=id_art;
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
		
		JButton btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ean=textFieldEAN.getText();
				String nombre= textFieldDescripcion.getText();
				String precio=textFieldPrecio.getText();
				String stock=textFieldStock.getText();
				Integer proveedor = comboBox.getSelectedIndex()+1;
				double iva = (double) comboBoxIVA.getSelectedItem();
				try {
					Conexion nc = new Conexion();
					Connection conn = nc.conectar();
					Statement instruccion = conn.createStatement();
					instruccion.executeUpdate("UPDATE articulo SET ean = '"+ean+"',descripcion = '"+nombre+"',pvp = "+precio+",ivaporcent = "+iva+",cantidad = "+stock+",id_proveedor = "+proveedor+" WHERE id_articulo="+idArticuloModificado);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnGuardarCambios.setBounds(144, 228, 148, 23);
		contentPane.add(btnGuardarCambios);
		
	
	}
}
