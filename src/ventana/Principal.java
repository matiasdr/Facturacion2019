package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import conexion.Conexion;

import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class Principal {


	private JFrame frmSistemaDeGestion;
	private String permisos;
	private String nombreUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();

					window.frmSistemaDeGestion.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Principal() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frmSistemaDeGestion = new JFrame();
		frmSistemaDeGestion.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/logos/logo4.png")));
		frmSistemaDeGestion.setForeground(Color.WHITE);
		frmSistemaDeGestion.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 22));
		frmSistemaDeGestion.setTitle("Sistema de Gestion");
		frmSistemaDeGestion.setBounds(100, 100, 823, 526);
		frmSistemaDeGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//javax.swing.UIManager.setLookAndFeel("Windows");
		
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}  catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(frmSistemaDeGestion);
        
		//updateComponentTreeUI(frame);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frmSistemaDeGestion.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmCrearEmpresa = new JMenuItem("Crear Empresa");
		mntmCrearEmpresa.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmCrearEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearEmpresa ce;
				ce = new CrearEmpresa();
				ce.setVisible(true);
			}
		});
		mntmCrearEmpresa.setEnabled(false);
		mntmCrearEmpresa.setVisible(false);
		
		
		JMenuItem mntmIngresar = new JMenuItem("Ingresar");
		mntmIngresar.setFont(new Font("Verdana", Font.BOLD, 13));
		
		mnArchivo.add(mntmIngresar);
		mnArchivo.add(mntmCrearEmpresa);
		
		JMenuItem mntmDefinirPerodoDe = new JMenuItem("Definir Per\u00EDodo de Trabajo");
		mntmDefinirPerodoDe.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmDefinirPerodoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefinirPeriodoDeTrabajo dp;
				dp = new DefinirPeriodoDeTrabajo();
				dp.setVisible(true);
			}
		});
		mnArchivo.add(mntmDefinirPerodoDe);
		mntmDefinirPerodoDe.setEnabled(false);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		menuBar.add(mnClientes);
		mnClientes.setEnabled(false);
		
		JMenuItem mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
		mntmNuevoCliente.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoCliente nc;
				try {
					nc = new NuevoCliente();
					nc.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnClientes.add(mntmNuevoCliente);
		
		
		JMenuItem mntmModificarCliente = new JMenuItem("Modificar Cliente");
		mntmModificarCliente.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmModificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				 ModificarCliente mc;
				try {
					mc = new ModificarCliente();
					mc.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 				
			}
		});
		mnClientes.add(mntmModificarCliente);
		
		
		JMenu mnListadoDeClientes = new JMenu("Listado de Clientes");
		mnListadoDeClientes.setFont(new Font("Verdana", Font.BOLD, 13));
		mnClientes.add(mnListadoDeClientes);
		
		
		JMenuItem mntmTodos = new JMenuItem("Todos");
		mntmTodos.setFont(new Font("Verdana", Font.BOLD, 15));
		mntmTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListadoClieTodos ltc = new ListadoClieTodos(); 
			    ltc.setVisible(true);
				
			}
		});
		mnListadoDeClientes.add(mntmTodos);
		
		JMenuItem mntmListadoDeSaldos = new JMenuItem("Listado de Saldos");
		mntmListadoDeSaldos.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmListadoDeSaldos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 ListadoSaldosClie lsc;
				lsc = new ListadoSaldosClie();
				lsc.setVisible(true);
				 				
			}
		});
		mnClientes.add(mntmListadoDeSaldos);
		
		JMenuItem mntmResumenDeCliente = new JMenuItem("Resumen de Cliente");
		mntmResumenDeCliente.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmResumenDeCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ResumenCliente rsc;
				rsc = new ResumenCliente();
				rsc.setVisible(true);
			}
		});
		mnClientes.add(mntmResumenDeCliente);
		
		JMenu mnProveedores = new JMenu("Proveedores");
		mnProveedores.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		menuBar.add(mnProveedores);
		mnProveedores.setEnabled(false);
		
		JMenuItem mntmNuevoProveedor = new JMenuItem("Nuevo Proveedor");
		mntmNuevoProveedor.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmNuevoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					NuevoProveedor np;
					np = new NuevoProveedor();
					np.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnProveedores.add(mntmNuevoProveedor);
		
		JMenuItem mntmModificarProveedor = new JMenuItem("Modificar Proveedor");
		mntmModificarProveedor.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmModificarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarProveedor mp;
				try {
					mp = new ModificarProveedor();
					mp.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnProveedores.add(mntmModificarProveedor);
		
		JMenuItem mntmListadoDeSaldos_1 = new JMenuItem("Listado de Saldos");
		mntmListadoDeSaldos_1.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmListadoDeSaldos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoSaldosProv lsp = new ListadoSaldosProv();
				lsp.setVisible(true);
			}
		});
		
		JMenuItem mntmTodos_1 = new JMenuItem("Listado de Proveedores");
		mntmTodos_1.setFont(new Font("Verdana", Font.BOLD, 13));
		mnProveedores.add(mntmTodos_1);
		mntmTodos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListadoProvTodos lp = new ListadoProvTodos();
					lp.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		mnProveedores.add(mntmListadoDeSaldos_1);
		
		JMenuItem mntmResumenDeProveedor = new JMenuItem("Resumen de Proveedor");
		mntmResumenDeProveedor.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmResumenDeProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResumenProveedor rp = new ResumenProveedor();
				rp.setVisible(true);
			}
		});
		mnProveedores.add(mntmResumenDeProveedor);
		
		JMenu mnGenerar = new JMenu("Generar");
		mnGenerar.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		menuBar.add(mnGenerar);
		mnGenerar.setEnabled(false);
		
		JMenuItem mntmFactura = new JMenuItem("Factura");
		mntmFactura.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					GenerarFactura facturar;
					facturar = new GenerarFactura();
					facturar.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnGenerar.add(mntmFactura);
		
		JMenu mnRecibo = new JMenu("Recibo");
		mnRecibo.setFont(new Font("Verdana", Font.BOLD, 13));
		mnGenerar.add(mnRecibo);
		
		JMenuItem mntmPagosAProveedores = new JMenuItem("Pagos a Proveedores");
		mntmPagosAProveedores.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmPagosAProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarRecibo gr = new GenerarRecibo();
				gr.setVisible(true);
			}
		});
		mnRecibo.add(mntmPagosAProveedores);
		
		JMenuItem mntmCobrosAClientes = new JMenuItem("Cobros a Clientes");
		mntmCobrosAClientes.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmCobrosAClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarRecibo2 grc = new GenerarRecibo2();
				grc.setVisible(true);				
			}
		});
		mnRecibo.add(mntmCobrosAClientes);
		
		
		
		JMenu mnCargar = new JMenu("Cargar");
		mnCargar.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		menuBar.add(mnCargar);
		mnCargar.setEnabled(false);
		
		JMenuItem mntmFacturasDeCompra = new JMenuItem("Facturas de Compra");
		mntmFacturasDeCompra.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmFacturasDeCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarFacturaCompra cfc= new CargarFacturaCompra();
				cfc.setVisible(true);
			}
		});
		mnCargar.add(mntmFacturasDeCompra);
		
		JMenuItem mntmFacturasDeVenta = new JMenuItem("Facturas de Venta");
		mntmFacturasDeVenta.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmFacturasDeVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarFacturaVenta cfv= new CargarFacturaVenta();
				cfv.setVisible(true);
			}
		});
		mnCargar.add(mntmFacturasDeVenta);
		
		JMenu mnListados = new JMenu("Listados");
		mnListados.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		menuBar.add(mnListados);
		mnListados.setEnabled(false);
		
		JMenuItem mntmIvaVentas = new JMenuItem("IVA Ventas");
		mntmIvaVentas.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmIvaVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoIVAVentas liv= new ListadoIVAVentas();
				liv.setVisible(true);
			}
		});
		mnListados.add(mntmIvaVentas);
		
		JMenuItem mntmIvaCompras = new JMenuItem("IVA Compras");
		mntmIvaCompras.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmIvaCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoIVACompras lic = new ListadoIVACompras();
				lic.setVisible(true);
			}
		});
		mnListados.add(mntmIvaCompras);
		
		JMenu mnArtculos = new JMenu("Art\u00EDculos");
		mnArtculos.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		menuBar.add(mnArtculos);
		mnArtculos.setEnabled(false);
		
		JMenuItem mntmNuevoArtculo = new JMenuItem("Nuevo Art\u00EDculo");
		mntmNuevoArtculo.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmNuevoArtculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					NuevoArticulo na;
					na = new NuevoArticulo();
					na.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnArtculos.add(mntmNuevoArtculo);
		
		JMenuItem mntmModificarArtculo = new JMenuItem("Modificar Art\u00EDculo");
		mntmModificarArtculo.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmModificarArtculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ModificarArticulo ma = new ModificarArticulo();
					ma.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnArtculos.add(mntmModificarArtculo);
		
		JMenuItem mntmListadoArticulos = new JMenuItem("Listado de Articulos");
		mntmListadoArticulos.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmListadoArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListadoArticulos la = new ListadoArticulos(new java.awt.Frame(), false);
					la.setVisible(true);
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnArtculos.add(mntmListadoArticulos);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		menuBar.add(mnUsuarios);
		mnUsuarios.setEnabled(false);
		mnUsuarios.setVisible(false);
		
		JMenuItem mntmAgregarOModificar = new JMenuItem("Agregar o Modificar Usuario");
		mntmAgregarOModificar.setFont(new Font("Verdana", Font.BOLD, 13));
		mnUsuarios.add(mntmAgregarOModificar);
		
		JMenuItem mntmListarActividadesPor = new JMenuItem("Listar Actividades por Usuario");
		mntmListarActividadesPor.setFont(new Font("Verdana", Font.BOLD, 13));
		mnUsuarios.add(mntmListarActividadesPor);
		
		JPanel panel = new JPanel();
		frmSistemaDeGestion.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Principal.class.getResource("/logos/logo4.png")));
		panel.add(lblLogo);
		
		
		mntmIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login(new java.awt.Frame(), true);
				login.setVisible(true);
				permisos=login.getPermisoUsuario();
				nombreUsuario=login.getNombreUsuario();
				if(permisos == "administrador") {
					mntmCrearEmpresa.setEnabled(true);
					mntmDefinirPerodoDe.setEnabled(true);
					mnUsuarios.setEnabled(true);
					mnProveedores.setEnabled(true);
					mnClientes.setEnabled(true);
					mnListados.setEnabled(true);
					mnGenerar.setEnabled(true);
					mnCargar.setEnabled(true);
					mnArtculos.setEnabled(true);
				}
				frmSistemaDeGestion.setTitle("Sistema de Gestion Usuario: @"+nombreUsuario);
			}
		});
	}
	
	
}
