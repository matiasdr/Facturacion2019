package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoArticulos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private Integer idArt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoArticulos dialog = new ListadoArticulos(new java.awt.Frame(), true);
					dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
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
	public ListadoArticulos(java.awt.Frame parent, boolean modal) throws SQLException {
		super(parent, modal);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		Object[] encabezado = new Object[6];
		encabezado[0]="ID";
		encabezado[1]="EAN";
		encabezado[2]="Descripcion";
		encabezado[3]="Stock";
		encabezado[4]="Precio";
		encabezado[5]="Proveedor";
		
		DefaultTableModel tablaModelo = new DefaultTableModel(encabezado, 0);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(251, 21, 83, 23);
		contentPanel.add(comboBox);
		comboBox.addItem("EAN");
		comboBox.addItem("Descripcion");
		comboBox.addItem("Proveedor");
		
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnImprimir_1 = new JButton("Imprimir");
		btnImprimir_1.setBounds(38, 21, 89, 23);
		contentPanel.add(btnImprimir_1);
		
		JLabel lblBuscarPor = new JLabel("Buscar Por");
		lblBuscarPor.setBounds(152, 25, 89, 14);
		contentPanel.add(lblBuscarPor);
		
		textField = new JTextField();
		textField.setBounds(382, 24, 96, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selecCombo= comboBox.getSelectedItem().toString();
				String condicion = textField.getText().toString();
				if(textField.getText().isEmpty()) {
					// avisar que ttiene que poner algo
				} else {
					switch(selecCombo) {
					case "EAN":
						Conexion nc = new Conexion();
						Connection conec = nc.conectar();
						Statement instruccion;
						try {
							instruccion = conec.createStatement();
							ResultSet resultado = instruccion.executeQuery("Select * from articulo join proveedor ON proveedor.id_proveedor = articulo.id_proveedor where ean like '%"+condicion+"%'");
							
							// antes de cargar los resultado borramos todos los datos de la tabla menos la primer fial que tiene el encabezado
							
							while(tablaModelo.getRowCount()>1) {
								tablaModelo.removeRow(tablaModelo.getRowCount()-1);
							}
							
							while(resultado.next()) {
								Object[] linea = new Object[6];
								linea[0]= resultado.getInt("id_articulo");
								linea[1]= resultado.getString("ean");
								linea[2]= resultado.getString("descripcion");
								linea[3]= resultado.getDouble("cantidad");
								linea[4]= resultado.getDouble("pvp");
								linea[5]= resultado.getString("nombre");
								tablaModelo.addRow(linea);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
							
						
						nc.desconectar();
						
						break;
					case "Descripcion":
						Conexion nc1 = new Conexion();
						Connection conec1 = nc1.conectar();
						Statement instruccion1;
						try {
							instruccion1 = conec1.createStatement();
							ResultSet resultado1 = instruccion1.executeQuery("Select * from articulo join proveedor ON proveedor.id_proveedor = articulo.id_proveedor where descripcion like '%"+condicion+"%'");
							
							// antes de cargar los resultado borramos todos los datos de la tabla menos la primer fial que tiene el encabezado
							
							while(tablaModelo.getRowCount()>1) {
								tablaModelo.removeRow(tablaModelo.getRowCount()-1);
							}
							
							while(resultado1.next()) {
								Object[] linea = new Object[6];
								linea[0]= resultado1.getInt("id_articulo");
								linea[1]= resultado1.getString("ean");
								linea[2]= resultado1.getString("descripcion");
								linea[3]= resultado1.getDouble("cantidad");
								linea[4]= resultado1.getDouble("pvp");
								linea[5]= resultado1.getString("nombre");
								tablaModelo.addRow(linea);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						nc1.desconectar();

						break;
					case "Proveedor":
						Conexion nc2 = new Conexion();
						Connection conec2 = nc2.conectar();
						Statement instruccion2;
						try {
							instruccion2 = conec2.createStatement();
							ResultSet resultado2 = instruccion2.executeQuery("Select * from articulo join proveedor ON proveedor.id_proveedor = articulo.id_proveedor where nombre like '%"+condicion+"%'");
							
							// antes de cargar los resultado borramos todos los datos de la tabla menos la primer fial que tiene el encabezado
							
							while(tablaModelo.getRowCount()>1) {
								tablaModelo.removeRow(tablaModelo.getRowCount()-1);
							}
							
							while(resultado2.next()) {
								Object[] linea = new Object[6];
								linea[0]= resultado2.getInt("id_articulo");
								linea[1]= resultado2.getString("ean");
								linea[2]= resultado2.getString("descripcion");
								linea[3]= resultado2.getDouble("cantidad");
								linea[4]= resultado2.getDouble("pvp");
								linea[5]= resultado2.getString("nombre");
								tablaModelo.addRow(linea);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						nc2.desconectar();
						break;
					default: break;
					}
				}
					
			}
		});
		btnBuscar.setBounds(497, 21, 89, 23);
		contentPanel.add(btnBuscar);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<0) {
					return;
				}
				Integer selec =(Integer) tablaModelo.getValueAt(table.getSelectedRow(), 0);
				idArt = selec;
				setVisible(false);
			}
		});
		btnSeleccionar.setBounds(461, 314, 89, 23);
		contentPanel.add(btnSeleccionar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 576, 242);
		contentPanel.add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tablaModelo);
		
		Conexion nc = new Conexion();
		Connection conec = nc.conectar();
		Statement instruccion = conec.createStatement();
		ResultSet resultado = instruccion.executeQuery("Select * from articulo join proveedor ON proveedor.id_proveedor = articulo.id_proveedor");
		
		while(resultado.next()) {
			Object[] linea = new Object[6];
			linea[0]= resultado.getInt("id_articulo");
			linea[1]= resultado.getString("ean");
			linea[2]= resultado.getString("descripcion");
			linea[3]= resultado.getDouble("cantidad");
			linea[4]= resultado.getDouble("pvp");
			linea[5]= resultado.getString("nombre");
			tablaModelo.addRow(linea);
			
		}
		nc.desconectar();
	
	}
	
	public Integer getIdArt() {
		return idArt;
	}
	
}
