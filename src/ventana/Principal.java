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
//import ui.MenuInicio;

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
		menuBar.add(mnArchivo);
		
		JMenuItem mntmCrearEmpresa = new JMenuItem("Crear Empresa");
		mntmCrearEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearEmpresa ce;
				ce = new CrearEmpresa();
				ce.setVisible(true);
			}
		});
		mnArchivo.add(mntmCrearEmpresa);
		
		JMenuItem mntmDefinirPerodoDe = new JMenuItem("Definir Per\u00EDodo de Trabajo");
		mntmDefinirPerodoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefinirPeriodoDeTrabajo dp;
				dp = new DefinirPeriodoDeTrabajo();
				dp.setVisible(true);
			}
		});
		mnArchivo.add(mntmDefinirPerodoDe);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnClientes);
		
		JMenuItem mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
		mntmNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoCliente nc;
				nc = new NuevoCliente();
				nc.setVisible(true);
			}
		});
		mnClientes.add(mntmNuevoCliente);
		
		JMenuItem mntmModificarCliente = new JMenuItem("Modificar Cliente");
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
		mnClientes.add(mnListadoDeClientes);
		
		JMenuItem mntmTodos = new JMenuItem("Todos");
		mntmTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListadoClieTodos ltc = new ListadoClieTodos(); 
			    ltc.setVisible(true);
				
			}
		});
		mnListadoDeClientes.add(mntmTodos);
		
		JMenuItem mntmListadoDeSaldos = new JMenuItem("Listado de Saldos");
		mntmListadoDeSaldos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 ListadoSaldosClie lsc;
				lsc = new ListadoSaldosClie();
				lsc.setVisible(true);
				 				
			}
		});
		mnClientes.add(mntmListadoDeSaldos);
		
		JMenuItem mntmResumenDeCliente = new JMenuItem("Resumen de Cliente");
		mnClientes.add(mntmResumenDeCliente);
		
		JMenu mnProveedores = new JMenu("Proveedores");
		menuBar.add(mnProveedores);
		
		JMenuItem mntmNuevoProveedor = new JMenuItem("Nuevo Proveedor");
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
		mntmListadoDeSaldos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoSaldosProv lsp = new ListadoSaldosProv();
				lsp.setVisible(true);
			}
		});
		
		JMenuItem mntmTodos_1 = new JMenuItem("Listado de Proveedores");
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
		mntmResumenDeProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResumenProveedor rp = new ResumenProveedor();
				rp.setVisible(true);
			}
		});
		mnProveedores.add(mntmResumenDeProveedor);
		
		JMenu mnGenerar = new JMenu("Generar");
		menuBar.add(mnGenerar);
		
		JMenuItem mntmFactura = new JMenuItem("Factura");
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
		mnGenerar.add(mnRecibo);
		
		JMenuItem mntmPagosAProveedores = new JMenuItem("Pagos a Proveedores");
		mntmPagosAProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarRecibo gr = new GenerarRecibo();
				gr.setVisible(true);
			}
		});
		mnRecibo.add(mntmPagosAProveedores);
		
		JMenuItem mntmCobrosAClientes = new JMenuItem("Cobros a Clientes");
		mntmCobrosAClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarRecibo2 grc = new GenerarRecibo2();
				grc.setVisible(true);				
			}
		});
		mnRecibo.add(mntmCobrosAClientes);
		
		
		
		JMenu mnCargar = new JMenu("Cargar");
		menuBar.add(mnCargar);
		
		JMenuItem mntmFacturasDeCompra = new JMenuItem("Facturas de Compra");
		mntmFacturasDeCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarFacturaCompra cfc= new CargarFacturaCompra();
				cfc.setVisible(true);
			}
		});
		mnCargar.add(mntmFacturasDeCompra);
		
		JMenuItem mntmFacturasDeVenta = new JMenuItem("Facturas de Venta");
		mntmFacturasDeVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarFacturaVenta cfv= new CargarFacturaVenta();
				cfv.setVisible(true);
			}
		});
		mnCargar.add(mntmFacturasDeVenta);
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		JMenuItem mntmIvaVentas = new JMenuItem("IVA Ventas");
		mntmIvaVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoIVAVentas liv= new ListadoIVAVentas();
				liv.setVisible(true);
			}
		});
		mnListados.add(mntmIvaVentas);
		
		JMenuItem mntmIvaCompras = new JMenuItem("IVA Compras");
		mntmIvaCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoIVACompras lic = new ListadoIVACompras();
				lic.setVisible(true);
			}
		});
		mnListados.add(mntmIvaCompras);
		
		JMenu mnArtculos = new JMenu("Art\u00EDculos");
		menuBar.add(mnArtculos);
		
		JMenuItem mntmNuevoArtculo = new JMenuItem("Nuevo Art\u00EDculo");
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
		
		JMenuItem mntmCambiarPrecios = new JMenuItem("Cambiar Precios");
		mntmCambiarPrecios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnArtculos.add(mntmCambiarPrecios);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAgregarOModificar = new JMenuItem("Agregar o Modificar Usuario");
		mnUsuarios.add(mntmAgregarOModificar);
		
		JMenuItem mntmListarActividadesPor = new JMenuItem("Listar Actividades por Usuario");
		mnUsuarios.add(mntmListarActividadesPor);
		
		JPanel panel = new JPanel();
		frmSistemaDeGestion.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Principal.class.getResource("/logos/logo4.png")));
		panel.add(lblLogo);
	}
	
	
}
