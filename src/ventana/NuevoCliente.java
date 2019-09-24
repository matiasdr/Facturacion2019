package ventana;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NuevoCliente extends JFrame {
	private JTextField cli_RazSocial;
	private JTextField cli_Cuil;
	private JTextField cli_Domicilio;
	private JTextField cli_Telefono;
	private JTextField cli_Categ;
	private JTextField cli_PersResp;
	private JTextField cli_Contacto;
	private JPanel contentPane;
	private JTextField cli_SelecIva;
	
	private boolean flag;
	
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoCliente frame = new NuevoCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	
	
	
	
	/**
	 * Create the dialog.
	 */
	public NuevoCliente() {
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		JLabel lblNombreORazn = new JLabel("Nombre o Raz\u00F3n Social :");
		lblNombreORazn.setBounds(10, 11, 149, 14);
		contentPane.add(lblNombreORazn);

		JLabel lblCuitOCuil = new JLabel("CUIT o CUIL :");
		lblCuitOCuil.setBounds(10, 36, 91, 14);
		contentPane.add(lblCuitOCuil);

		JLabel lblNewLabel = new JLabel("Domicilio :");
		lblNewLabel.setBounds(10, 61, 71, 14);
		contentPane.add(lblNewLabel);

		JLabel lblTelefono = new JLabel("Telefono :");
		lblTelefono.setBounds(10, 86, 71, 14);
		contentPane.add(lblTelefono);

		JLabel lblCondicionAnteEl = new JLabel("Condicion ante el IVA :");
		lblCondicionAnteEl.setBounds(10, 111, 138, 14);
		contentPane.add(lblCondicionAnteEl);

		JLabel lblCategora = new JLabel("Categor\u00EDa :");
		lblCategora.setBounds(10, 136, 71, 14);
		contentPane.add(lblCategora);

		JLabel lblPersonaResponsable = new JLabel("Persona Responsable :");
		lblPersonaResponsable.setBounds(10, 161, 138, 14);
		contentPane.add(lblPersonaResponsable);

		JLabel lblContactoresponsable = new JLabel("Contacto (Responsable) :");
		lblContactoresponsable.setBounds(10, 186, 149, 14);
		contentPane.add(lblContactoresponsable);

		cli_RazSocial = new JTextField();
		cli_RazSocial.setBounds(171, 8, 167, 20);
		contentPane.add(cli_RazSocial);
		cli_RazSocial.setColumns(10);

		cli_Cuil = new JTextField();
		cli_Cuil.setBounds(171, 33, 125, 20);
		contentPane.add(cli_Cuil);
		cli_Cuil.setColumns(10);

		cli_Domicilio = new JTextField();
		cli_Domicilio.setBounds(171, 58, 167, 20);
		contentPane.add(cli_Domicilio);
		cli_Domicilio.setColumns(10);

		cli_Telefono = new JTextField();
		cli_Telefono.setBounds(171, 83, 125, 20);
		contentPane.add(cli_Telefono);
		cli_Telefono.setColumns(10);

		cli_Categ = new JTextField();
		cli_Categ.setBounds(171, 133, 125, 20);
		contentPane.add(cli_Categ);
		cli_Categ.setColumns(10);

		cli_PersResp = new JTextField();
		cli_PersResp.setBounds(171, 158, 167, 20);
		contentPane.add(cli_PersResp);
		cli_PersResp.setColumns(10);

		cli_Contacto = new JTextField();
		cli_Contacto.setBounds(171, 183, 125, 20);
		contentPane.add(cli_Contacto);
		cli_Contacto.setColumns(10);
		
		cli_SelecIva = new JTextField();
		cli_SelecIva.setBounds(314, 107, 167, 22);
		contentPane.add(cli_SelecIva);
		cli_SelecIva.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Resp. Inscripto");
		comboBox.addItem("Cons. Final");
		comboBox.addItem("Monotributo");
		comboBox.addItem("Iva Exento");
		
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cli_SelecIva.setText(comboBox.getSelectedItem().toString());
			}
		});
		
		comboBox.setBounds(171, 107, 125, 22);
		contentPane.add(comboBox);
		
		JLabel cli_error = new JLabel("Dato invalido/Campo obligatorio");
		cli_error.setForeground(Color.RED);
		cli_error.setBackground(Color.WHITE);
		cli_error.setVisible(false);
		cli_error.setBounds(350, 10, 191, 16);
		contentPane.add(cli_error);
		
		JLabel cli_error1 = new JLabel("Dato invalido/Campo obligatorio");
		cli_error1.setForeground(Color.RED);
		cli_error1.setVisible(false);
		cli_error1.setBounds(350, 35, 191, 16);
		contentPane.add(cli_error1);
		
		JLabel cli_error2 = new JLabel("Dato invalido/Campo obligatorio");
		cli_error2.setForeground(Color.RED);
		cli_error2.setVisible(false);
		cli_error2.setBounds(350, 60, 191, 16);
		contentPane.add(cli_error2);
		
/*		JLabel cli_error3 = new JLabel("Dato invalido/Campo obligatorio");
		cli_error3.setForeground(Color.RED);
		cli_error3.setVisible(false);
		cli_error3.setBounds(350, 85, 191, 16);
		contentPane.add(cli_error3);
*/		
		JLabel cli_error4 = new JLabel("Dato invalido/Campo obligatorio");
		cli_error4.setForeground(Color.RED);
		cli_error4.setVisible(false);
		cli_error4.setBounds(350, 135, 191, 16);
		contentPane.add(cli_error4);
		
/*		JLabel cli_error5 = new JLabel("Dato invalido/Campo obligatorio");
		cli_error5.setForeground(Color.RED);
		cli_error5.setVisible(false);
		cli_error5.setBounds(350, 160, 191, 16);
		contentPane.add(cli_error5);
		
		JLabel cli_error6 = new JLabel("Dato invalido/Campo obligatorio");
		cli_error6.setForeground(Color.RED);
		cli_error6.setVisible(false);
		cli_error6.setBounds(350, 185, 191, 16);
		contentPane.add(cli_error6);
*/		
		
	

		
		JButton btnGuardarCliente = new JButton("Guardar Cliente");
		btnGuardarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String Raz_Social= null;
				String Cuil= null;
				String Domicilio = null;
				String Categoria = null;
				String Responsable = null;
				String Contacto = null; 
				String TipoIva = null;
				String Telefono = null;
				
				if(cli_RazSocial.getText().isEmpty())
				 {
				   cli_error.setVisible(true);
				   flag = true;
				 }else {
					 Raz_Social=cli_RazSocial.getText();
				 }
				  
		         
				 if(cli_Cuil.getText().length()< 11 || cli_Cuil.getText().length() > 12)
				 {
					 cli_error1.setVisible(true);
					 flag = true;
				 } else {
					 Cuil = cli_Cuil.getText();
				 }
								     
                 if(cli_Domicilio.getText().isEmpty())
                 {
                	 cli_error2.setVisible(true);
                	 flag = true;
                 } else {
                	 Domicilio = cli_Domicilio.getText();
                 }
                 
                 Telefono = cli_Telefono.getText();
                                    
/*				 if(cli_Telefono.getText().isEmpty())
				 {
					cli_error3.setVisible(true);
					 flag = true;
				 }
*/				    
				    
				 if(cli_Categ.getText().isEmpty())
				 {
				 	cli_error4.setVisible(true);
				 	 flag = true;
				 } else {
					 Categoria = cli_Categ.getText();
				 }
				 
				 Responsable = cli_PersResp.getText();
				 Contacto = cli_Contacto.getText();
				 
				 
/*				 if(cli_PersResp.getText().isEmpty())
				 {
					cli_error5.setVisible(true);
					 flag = true;
				 }
				  

				 if(cli_Contacto.getText().isEmpty())
 				 {
					cli_error6.setVisible(true);
					 flag = true;
				 }
*/				
				 
				 if(flag) {
					 JOptionPane.showMessageDialog(null, "Faltan Ingresar Datos");
					 flag = false;
				 }
				 else 
				 {
					 JOptionPane.showMessageDialog(null, "Los Datos fueron Guardados Satisfactoriamente");
				 }
			}
		});
		
		
		
		btnGuardarCliente.setBounds(140, 214, 138, 23);
		contentPane.add(btnGuardarCliente);
		
			

	}
}
