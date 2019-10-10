package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;

public class ListadoClieTodos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfBusqueda;
	private JPanel contentPane;
	private JTable table;
	private String clieSeleccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoClieTodos frame = new ListadoClieTodos();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoClieTodos() {
		setTitle("Listado de Cliente");
		setBounds(100, 100, 548, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnImprimir = new JButton("Imprimir");
			btnImprimir.setBounds(12, 19, 89, 23);
			contentPanel.add(btnImprimir);
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(179, 19, 128, 22);
		contentPanel.add(comboBox);
		{
			JLabel lblBuscarPor = new JLabel("Buscar por");
			lblBuscarPor.setBounds(113, 23, 73, 14);
			contentPanel.add(lblBuscarPor);
		}
		comboBox.addItem("Razon Social");
		comboBox.addItem("Cuil/Cuit");
		
		
		 
			Object[] fila = new Object[3];
			fila[0]= "ID Cliente";
			fila[1]= "Nombre";
			fila[2]= "CUIT";
			
			DefaultTableModel tablaModelo = new DefaultTableModel(fila, 0);
	
		{
			JButton btnBuscarPor = new JButton("Buscar");
			btnBuscarPor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				 String itemcombo = comboBox.getSelectedItem().toString();
				 String busqueda = tfBusqueda.getText();
				 
				 if(itemcombo.equals("Razon Social"))
				 {
					 //iniciar busqueda por nombre
						Conexion ltc = new Conexion();
						Connection conec = ltc.conectar();
						ltc.listarClientes();
						Statement instruccion;
						try {
							instruccion = conec.createStatement();
							ResultSet resultado = instruccion.executeQuery("Select * from cliente where nombre like '%"+busqueda+"%'");
							
							while(tablaModelo.getRowCount()>0) // LIMPIA EL JTABLE ANTES DE CARGARLO DE NUEVO
							{
								tablaModelo.removeRow(tablaModelo.getRowCount()-1);
							}
							
							while(resultado.next()) {
								Object[] linea = new Object[3];
								linea[0]= resultado.getInt("id_cliente");
								linea[1]= resultado.getString("nombre");
								linea[2]= resultado.getString("cuilcuit");
						    	tablaModelo.addRow(linea);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						ltc.desconectar();			
					}
								
				 if(itemcombo.equals("Cuil/Cuit"))
		    	{
				   // iniciar busqueda por cuit o cuil
						Conexion ltc = new Conexion();
						Connection conec = ltc.conectar();
						ltc.listarClientes();
						Statement instruccion;
						try {
							instruccion = conec.createStatement();
							ResultSet resultado = instruccion.executeQuery("Select * from cliente where cuilcuit like '%"+busqueda+"%'");
							
							while(tablaModelo.getRowCount()>0) {
								tablaModelo.removeRow(tablaModelo.getRowCount()-1);
							}
							
							while(resultado.next()) {
								Object[] linea = new Object[3];
								linea[0]= resultado.getInt("id_cliente");
								linea[1]= resultado.getString("nombre");
								linea[2]= resultado.getString("cuilcuit");
						    	tablaModelo.addRow(linea);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						ltc.desconectar();			 
					 
			    }
					
				}
				
			});
			
			
			btnBuscarPor.setBounds(427, 19, 89, 23);
			contentPanel.add(btnBuscarPor);
		

		}

		tfBusqueda = new JTextField();
		tfBusqueda.setBounds(319, 20, 96, 20);
		contentPanel.add(tfBusqueda);
		tfBusqueda.setColumns(10);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 55, 504, 192);
			contentPanel.add(scrollPane);
			
		    table = new JTable();
		    scrollPane.setViewportView(table);
		    table.setModel(tablaModelo);
		}
		
		
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
		
		return clieSeleccion;
	}
}
