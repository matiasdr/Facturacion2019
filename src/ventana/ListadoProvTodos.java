package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import conexion.Conexion;

import javax.swing.JTable;
import java.awt.Toolkit;

public class ListadoProvTodos extends JFrame {
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCondicion;
	private JPanel contentPane;
	private JTable table;
	private String provSeleccionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoProvTodos frame = new ListadoProvTodos();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public ListadoProvTodos() throws SQLException {
		setTitle("Listados de Proveedores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoProvTodos.class.getResource("/logos/logo4.png")));
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 591, 358);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		Object[] fila = new Object[3];
		fila[0]= "ID Prov";
		fila[1]= "Nombre";
		fila[2]= "CUIT";
		DefaultTableModel tablaModelo = new DefaultTableModel(fila, 0);
		

		JComboBox<String> comboBox = new JComboBox<String>();

		

		comboBox.setBounds(185, 19, 106, 22);

		contentPanel.add(comboBox);
		{
			JLabel lblBuscarPor = new JLabel("Buscar por");
			lblBuscarPor.setBounds(101, 24, 73, 14);
			contentPanel.add(lblBuscarPor);
		}
		comboBox.addItem("Razon Social");
		comboBox.addItem("CUIT");
		

		textFieldCondicion = new JTextField();

		textFieldCondicion.setBounds(325, 21, 106, 20);
		contentPanel.add(textFieldCondicion);
		textFieldCondicion.setColumns(10);
		{
			JButton btnImprimir = new JButton("Imprimir");
			btnImprimir.setBounds(0, 20, 89, 23);
			contentPanel.add(btnImprimir);
		}
		{
			JButton btnBuscarPor = new JButton("Buscar");
			btnBuscarPor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String seleccion = comboBox.getSelectedItem().toString();
					String condicion = textFieldCondicion.getText();
					
					if(seleccion.equals("Razon Social")) {
						// llamar a la funcion de buscar por nombre del proveedor
						Conexion nc = new Conexion();
						Connection conec = nc.conectar();
						Statement instruccion;
						try {
							instruccion = conec.createStatement();
							ResultSet resultado = instruccion.executeQuery("Select * from proveedor where nombre like '%"+condicion+"%'");
							
							// antes de cargar los resultado borramos todos los datos de la tabla menos la primer fial que tiene el encabezado
							
							while(tablaModelo.getRowCount()>0) {
								tablaModelo.removeRow(tablaModelo.getRowCount()-1);
							}
							
							while(resultado.next()) {
								Object[] linea = new Object[3];
								linea[0]= resultado.getInt("id_proveedor");
								linea[1]= resultado.getString("nombre");
								linea[2]= resultado.getString("cuilcuit");
								tablaModelo.addRow(linea);
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
							
							while(tablaModelo.getRowCount()>0) {
								tablaModelo.removeRow(tablaModelo.getRowCount()-1);
							}
							
							while(resultado.next()) {
								Object[] linea = new Object[3];
								linea[0]= resultado.getInt("id_proveedor");
								linea[1]= resultado.getString("nombre");
								linea[2]= resultado.getString("cuilcuit");
								tablaModelo.addRow(linea);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
							
						
						nc.desconectar();
						
						
					}
					
					// resultado... debe ser acomodado en el JTable 
				}
				
			});

			btnBuscarPor.setBounds(441, 20, 89, 23);
			contentPanel.add(btnBuscarPor);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 67, 515, 185);
		contentPanel.add(scrollPane);
		{
			table = new JTable();
			scrollPane.setViewportView(table);

			table.setModel(tablaModelo);
			
		}
		
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
			
		}
		nc.desconectar();
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			contentPane.add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public String getProv() {
		
		return provSeleccionado;
	}
}


