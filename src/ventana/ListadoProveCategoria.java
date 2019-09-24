package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ListadoProveCategoria extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoProveCategoria frame = new ListadoProveCategoria();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoProveCategoria() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		DefaultListModel listModel = new DefaultListModel<String>(); // En las siguientes l�neas cargamos la lista de las categorias
		JList list = new JList();
		list.setBounds(324, 11, 110, 207);
		list.setModel(listModel);
		contentPanel.add(list);
		listModel.addElement("Categoria 1");
		listModel.addElement("Categoria 2");
		listModel.addElement("Categoria 3");		

		JLabel lblSeleccioneLaCategora = new JLabel("Seleccione la categor\u00EDa");
		lblSeleccioneLaCategora.setBounds(56, 11, 155, 25);
		contentPanel.add(lblSeleccioneLaCategora);

		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(196, 204, 89, 23);
		contentPanel.add(btnImprimir);
		
		DefaultTableModel tablaProv = new DefaultTableModel(0, 2); // Definimos el encabezado de la lsita de proveedores
		Object[] encabezado = new Object[2];
		encabezado[0]="C U I T";
		encabezado[1]="Razon Social";
		tablaProv.addRow(encabezado);
		
		table = new JTable();
		table.setBounds(28, 50, 286, 143);
		table.setModel(tablaProv);
		contentPanel.add(table);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// validamos que haya seleccionada al menos una categor�a
				ArrayList<String> categorias = new ArrayList<String>();
				if(list.getSelectedValuesList().isEmpty()) {
					// agregar ventana de error
					return;
				} else {
					categorias = (ArrayList<String>) list.getSelectedValuesList();
				}
				
				
				// Llamar al store procedure pasando como parametro el arreglo de categorias
				
				// Luego deber�amos llenar la tabla con la lista que nos devielva
				
				// for each result as elem => tablaProv.addElement(elem)
				
				
			}
		});
		btnFiltrar.setBounds(221, 12, 89, 23);
		contentPanel.add(btnFiltrar);

		
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
}
