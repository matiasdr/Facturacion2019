package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class NuevoArticulo extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEAN;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private Integer idProv;

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
	 * @throws SQLException 
	 */
	public NuevoArticulo() throws SQLException {
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
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(294, 190, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(301, 114, 96, 20);
		contentPane.add(comboBox);
		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from proveedor");
		while(resultado.next()) {
			comboBox.addItem(resultado.getString("nombre"));
		}
		nc.desconectar();
		
		JLabel label = new JLabel("Proveedor");
		label.setBounds(10, 117, 118, 14);
		contentPane.add(label);
		
		JLabel labelIVA = new JLabel("IVA del Articulo");
		labelIVA.setBounds(10, 142, 118, 14);
		contentPane.add(labelIVA);
		double iva21=21;
		double iva10=10.5;
		double iva27=27;
		JComboBox comboBoIVA = new JComboBox();
		comboBoIVA.setBounds(301, 145, 96, 20);
		contentPane.add(comboBoIVA);
		comboBoIVA.addItem(iva21);
		comboBoIVA.addItem(iva10);
		comboBoIVA.addItem(iva27);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre=null;
				String ean=null;
				String stock=null;
				String precio=null;
				double iva= (double) comboBoIVA.getSelectedItem();
				String proveedor = (String) comboBox.getSelectedItem();
				
				try {
					Conexion cn = new Conexion();
					Connection con = cn.conectar();
					Statement instrucion = con.createStatement();
					ResultSet resulta = instrucion.executeQuery("Select * from proveedor where nombre = '"+proveedor+"'");
					while(resulta.next()) {
						idProv = resulta.getInt("id_proveedor");
					}
					cn.desconectar();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
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
					
					
					try {
						Conexion nc = new Conexion();
						Connection conec = nc.conectar();
						Statement instruccion = conec.createStatement();
						instruccion.execute("spnuevoarticulo '"+ean+"', '"+nombre+"', "+precio+", "+iva+", "+stock+", "+idProv+", 0");
						JOptionPane.showMessageDialog(null, "Articulo creado correctamente");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(101, 190, 89, 23);
		contentPane.add(btnNewButton);
	}
}
