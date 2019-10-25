package ventana;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ElegirProveedor extends JDialog {
	private JTextField textField;
	private JTable table;
	private Integer provElegido;
	private String nombreProveedor;
	private Integer condFiscal;
	ArrayList<Integer> condiciones= new ArrayList<Integer>();
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 10, 101, 21);
		getContentPane().add(comboBox);
		comboBox.addItem("Razon Social");
		comboBox.addItem("CUIT");
		
		Object[] fila = new Object[3];
		fila[0]= "ID Prov";
		fila[1]= "Nombre";
		fila[2]= "CUIT";
		
		DefaultTableModel tablaModelo = new DefaultTableModel(fila, 0);
		
		
		textField = new JTextField();
		textField.setBounds(154, 11, 96, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = comboBox.getSelectedItem().toString();
				String condicion = textField.getText();
				
				if(seleccion.equals("Razon Social")) {
					// llamar a la funcion de buscar por nombre del proveedor
					Conexion nc = new Conexion();
					Connection conec = nc.conectar();
					Statement instruccion;
					try {
						instruccion = conec.createStatement();
						ResultSet resultado = instruccion.executeQuery("Select * from proveedor where nombre like '%"+condicion+"%'");
						
						// antes de cargar los resultado borramos todos los datos de la tabla menos la primer fial que tiene el encabezado
						condiciones.clear();
						while(tablaModelo.getRowCount()>0) {
							tablaModelo.removeRow(tablaModelo.getRowCount()-1);
						}
						
						while(resultado.next()) {
							Object[] linea = new Object[3];
							linea[0]= resultado.getInt("id_proveedor");
							linea[1]= resultado.getString("nombre");
							linea[2]= resultado.getString("cuilcuit");
							tablaModelo.addRow(linea);
							condiciones.add(resultado.getInt("id_condicion_fiscal"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
						
					
					nc.desconectar();
					
					
					
				} else if(seleccion.equals("CUIT")) {
					// llamar a la fucnion de buscar por CUIT.
					
					Conexion nc = new Conexion();
					Connection conec = nc.conectar();
					Statement instruccion;
					try {
						instruccion = conec.createStatement();
						ResultSet resultado = instruccion.executeQuery("Select * from proveedor where cuilcuit = '"+condicion+"'");
						
						// antes de cargar los resultado borramos todos los datos de la tabla menos la primer fial que tiene el encabezado
						condiciones.clear();
						while(tablaModelo.getRowCount()>0) {
							tablaModelo.removeRow(tablaModelo.getRowCount()-1);
						}
						
						while(resultado.next()) {
							Object[] linea = new Object[3];
							linea[0]= resultado.getInt("id_proveedor");
							linea[1]= resultado.getString("nombre");
							linea[2]= resultado.getString("cuilcuit");
							tablaModelo.addRow(linea);
							condiciones.add(resultado.getInt("id_condicion_fiscal"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
						
					
					nc.desconectar();
					
					
				}
				
			
				
			}
		});
		btnBuscar.setBounds(306, 10, 89, 23);
		getContentPane().add(btnBuscar);
		
		
		
		ArrayList<Integer> condiciones= new ArrayList<Integer>();
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<0) {
					return;
				}
				Integer seleccion = (Integer) tablaModelo.getValueAt(table.getSelectedRow(), 0);
				String selecNombre = (String) tablaModelo.getValueAt(table.getSelectedRow(), 1);
				//provElegido=textField.getText().toString();
				Integer condicion = condiciones.get(table.getSelectedRow());
				provElegido=seleccion;
				nombreProveedor=selecNombre;
				condFiscal=condicion;
				setVisible(false);
			}
		});
		btnSeleccionar.setBounds(382, 242, 89, 23);
		getContentPane().add(btnSeleccionar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 42, 439, 193);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tablaModelo);
		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from proveedor");
		
		while(resultado.next()) {
			Object[] linea = new Object[3];
			linea[0]= resultado.getInt("id_proveedor");
			linea[1]= resultado.getString("nombre");
			linea[2]= resultado.getString("cuilcuit");
			tablaModelo.addRow(linea);
			condiciones.add(resultado.getInt("id_condicion_fiscal"));
			
		}
		nc.desconectar();

	}
	
	
	public Integer getProvElegido() {
			return provElegido;
	}
	public void setProv(Integer provElegido) {
		this.provElegido = provElegido;
	}
	
	public String getNombreProovedor() {
		return nombreProveedor;
	}
	
	public Integer getCondFiscal() {
		return condFiscal;
	}
}
