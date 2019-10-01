package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import conexion.Conexion;

import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//javax.swing.UIManager.setLookAndFeel("Windows");
		Conexion nc = new Conexion();
		nc.conectar();
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}  catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.updateComponentTreeUI(frame);

		//updateComponentTreeUI(frame);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
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
				 mc = new ModificarCliente();
				 mc.setVisible(true);				
			}
		});
		mnClientes.add(mntmModificarCliente);
		
		
		JMenu mnListadoDeClientes = new JMenu("Listado de Clientes");
		mnClientes.add(mnListadoDeClientes);
		
		JMenuItem mntmTodos = new JMenuItem("Todos");
		mnListadoDeClientes.add(mntmTodos);
		
		JMenuItem mntmPorCategoria = new JMenuItem("Por Categoria");
		mnListadoDeClientes.add(mntmPorCategoria);
		
		JMenuItem mntmListadoDeSaldos = new JMenuItem("Listado de Saldos");
		mnClientes.add(mntmListadoDeSaldos);
		
		JMenuItem mntmResumenDeCliente = new JMenuItem("Resumen de Cliente");
		mnClientes.add(mntmResumenDeCliente);
		
		JMenu mnProveedores = new JMenu("Proveedores");
		menuBar.add(mnProveedores);
		
		JMenuItem mntmNuevoProveedor = new JMenuItem("Nuevo Proveedor");
		mntmNuevoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoProveedor np = new NuevoProveedor();
				np.setVisible(true);
			}
		});
		mnProveedores.add(mntmNuevoProveedor);
		
		JMenuItem mntmModificarProveedor = new JMenuItem("Modificar Proveedor");
		mntmModificarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarProveedor mp = new ModificarProveedor();
				mp.setVisible(true);
			}
		});
		mnProveedores.add(mntmModificarProveedor);
		
		JMenu mnListadoDeProveedores = new JMenu("Listado de Proveedores");
		mnProveedores.add(mnListadoDeProveedores);
		
		JMenuItem mntmTodos_1 = new JMenuItem("Todos");
		mntmTodos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoProvTodos lp= new ListadoProvTodos();
				lp.setVisible(true);
			}
		});
		mnListadoDeProveedores.add(mntmTodos_1);
		
		JMenuItem mntmPorCategorias = new JMenuItem("Por Categorias");
		mntmPorCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoProveCategoria lpc = new ListadoProveCategoria();
				lpc.setVisible(true);
			}
		});
		mnListadoDeProveedores.add(mntmPorCategorias);
		
		JMenuItem mntmListadoDeSaldos_1 = new JMenuItem("Listado de Saldos");
		mntmListadoDeSaldos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoSaldosProv lsp = new ListadoSaldosProv();
				lsp.setVisible(true);
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
				GenerarFactura facturar= new GenerarFactura();
				facturar.setVisible(true);
			}
		});
		mnGenerar.add(mntmFactura);
		
		JMenuItem mntmRecibo = new JMenuItem("Recibo");
		mntmRecibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarRecibo recibo = new GenerarRecibo();
				recibo.setVisible(true);
			}
		});
		mnGenerar.add(mntmRecibo);
		
		JMenu mnCargar = new JMenu("Cargar");
		menuBar.add(mnCargar);
		
		JMenuItem mntmFacturasDeCompra = new JMenuItem("Facturas de Compra");
		mnCargar.add(mntmFacturasDeCompra);
		
		JMenuItem mntmFacturasDeVenta = new JMenuItem("Facturas de Venta");
		mnCargar.add(mntmFacturasDeVenta);
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		JMenuItem mntmIvaVentas = new JMenuItem("IVA Ventas");
		mnListados.add(mntmIvaVentas);
		
		JMenuItem mntmIvaCompras = new JMenuItem("IVA Compras");
		mnListados.add(mntmIvaCompras);
		
		JMenu mnArtculos = new JMenu("Art\u00EDculos");
		menuBar.add(mnArtculos);
		
		JMenuItem mntmNuevoArtculo = new JMenuItem("Nuevo Art\u00EDculo");
		mnArtculos.add(mntmNuevoArtculo);
		
		JMenuItem mntmModificarArtculo = new JMenuItem("Modificar Art\u00EDculo");
		mnArtculos.add(mntmModificarArtculo);
		
		JMenuItem mntmListadoArticulos = new JMenuItem("Listado de Articulos");
		mnArtculos.add(mntmListadoArticulos);
		
		JMenuItem mntmCambiarPrecios = new JMenuItem("Cambiar Precios");
		mnArtculos.add(mntmCambiarPrecios);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAgregarOModificar = new JMenuItem("Agregar o Modificar Usuario");
		mnUsuarios.add(mntmAgregarOModificar);
		
		JMenuItem mntmListarActividadesPor = new JMenuItem("Listar Actividades por Usuario");
		mnUsuarios.add(mntmListarActividadesPor);
	}
	
	
}
