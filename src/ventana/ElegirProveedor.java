package ventana;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ElegirProveedor extends JDialog {
	private JTextField textField;
	private JTable table;
	private Integer provElegido;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElegirProveedor dialog = new ElegirProveedor(new java.awt.Frame(), true);
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
	 * @throws SQLException 
	 */
	public ElegirProveedor(java.awt.Frame parent, boolean modal) throws SQLException {
		super(parent, modal);
		setTitle("Busqueda de Proveedores");
		setBounds(100, 100, 527, 337);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(154, 11, 96, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(306, 10, 89, 23);
		getContentPane().add(btnBuscar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 10, 101, 21);
		getContentPane().add(comboBox);
		
		DefaultTableModel tablaModelo = new DefaultTableModel(0, 3);
		Object[] fila = new Object[3];
		fila[0]= "ID Prov";
		fila[1]= "Nombre";
		fila[2]= "CUIT";
		tablaModelo.addRow(fila);
		
		table = new JTable();
		table.setModel(tablaModelo);
		table.setBounds(20, 42, 382, 179);
		getContentPane().add(table);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer seleccion = (Integer) tablaModelo.getValueAt(table.getSelectedRow(), 0);
				//provElegido=textField.getText().toString();
				provElegido=seleccion;
				setVisible(false);
			}
		});
		btnSeleccionar.setBounds(295, 228, 89, 23);
		getContentPane().add(btnSeleccionar);
		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		nc.listarClientes();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from proveedor");
		
		while(resultado.next()) {
			Object[] linea = new Object[3];
			linea[0]= resultado.getInt("id_proveedor");
			linea[1]= resultado.getString("nombre");
			linea[2]= resultado.getString("cuilcuit");
			tablaModelo.addRow(linea);
			
		}
		nc.desconectar();

	}
	public Integer getProvElegido() {
		
		return provElegido;
	}
	public void setProv(Integer provElegido) {
		this.provElegido = provElegido;
	}
}
