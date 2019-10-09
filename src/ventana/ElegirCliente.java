package ventana;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ElegirCliente extends JDialog {
	private JTextField textField;
	private JTable table;
	private Integer clienElegido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElegirCliente dialog = new ElegirCliente(new java.awt.Frame(), true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public ElegirCliente(java.awt.Frame parent, boolean modal) throws SQLException {
		super(parent, modal);
		setTitle("Busqueda de Clientes");
		setBounds(100, 100, 581, 386);
		getContentPane().setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(187, 13, 142, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(394, 12, 135, 23);
		getContentPane().add(btnBuscar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 13, 135, 22);
		getContentPane().add(comboBox);
		
		DefaultTableModel tablaModelo = new DefaultTableModel(0, 3);
		Object[] fila = new Object[3];
		fila[0]= "ID Cliente";
		fila[1]= "Nombre";
		fila[2]= "CUIT";
		tablaModelo.addRow(fila);
		
		
		table = new JTable();
		table.setModel(tablaModelo);
		table.setBounds(12, 48, 517, 242);
		getContentPane().add(table);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Integer seleccion = (Integer) tablaModelo.getValueAt(table.getSelectedRow(), 0);
				//provElegido=textField.getText().toString();
				clienElegido=seleccion;
				setVisible(false);
			}
		});
		
		btnSeleccionar.setBounds(361, 303, 148, 25);
		getContentPane().add(btnSeleccionar);

		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		nc.listarClientes();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from cliente");
		
		while(resultado.next()) {
			Object[] linea = new Object[3];
			linea[0]= resultado.getInt("id_cliente");
			linea[1]= resultado.getString("nombre");
			linea[2]= resultado.getString("cuilcuit");
			tablaModelo.addRow(linea);
			
		}
		nc.desconectar();
		
	}
	public Integer getClienElegido() {
		return clienElegido;
	}
	public void setClien(Integer clienElegido) {
		this.clienElegido = clienElegido;
	}
}
