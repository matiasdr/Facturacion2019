package ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
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
		mnProveedores.add(mntmNuevoProveedor);
		
		JMenuItem mntmModificarProveedor = new JMenuItem("Modificar Proveedor");
		mnProveedores.add(mntmModificarProveedor);
		
		JMenu mnListadoDeProveedores = new JMenu("Listado de Proveedores");
		mnProveedores.add(mnListadoDeProveedores);
		
		JMenuItem mntmTodos_1 = new JMenuItem("Todos");
		mnListadoDeProveedores.add(mntmTodos_1);
		
		JMenuItem mntmPorCategorias = new JMenuItem("Por Categorias");
		mnListadoDeProveedores.add(mntmPorCategorias);
		
		JMenuItem mntmListadoDeSaldos_1 = new JMenuItem("Listado de Saldos");
		mnProveedores.add(mntmListadoDeSaldos_1);
		
		JMenuItem mntmResumenDeProveedor = new JMenuItem("Resumen de Proveedor");
		mnProveedores.add(mntmResumenDeProveedor);
		
		JMenu mnGenerar = new JMenu("Generar");
		menuBar.add(mnGenerar);
		
		JMenuItem mntmFactura = new JMenuItem("Factura");
		mnGenerar.add(mntmFactura);
		
		JMenuItem mntmRecibo = new JMenuItem("Recibo");
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
	}
	
	
}