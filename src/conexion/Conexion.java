package conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	public Connection conexion = null;
	private Statement instruccion = null;
	private ResultSet resultado = null;
	private String url="jdbc:sqlserver://localhost:1433;DatabaseName=SistemaGestion";
	private String userDB ="sa";
	private String pass="1234";
			
	
	public Connection conectar() {
		
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//Cambiar los datos de "nombre de Usuario" y "password" segun corresponda
			conexion = DriverManager.getConnection(url, userDB, pass);
			
			if(conexion.isValid(0)) {
				System.out.print("Conexion OK");
				//cst.execute();
			}else {
				System.out.print("Conexion Fallida");
			}
		}catch(Exception ex) {
			System.out.print(ex.getMessage());
		}
		
		return conexion;
	}
	
	public void desconectar() {
		try {
			//Libera los Statement
			if(instruccion != null) instruccion.close();
			//Libera los ResultSet
			if(resultado != null) resultado.close();
			//Cierra la conexion con la Base de Datos
			if(conexion != null) conexion.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listarClientes() {
		try {
			Statement instruccion=conexion.createStatement();
			String consulta = "SELECT * FROM cliente"; 
			ResultSet resultado = instruccion.executeQuery(consulta);
			System.out.println("Listado de Clientes: ");
			while(resultado.next()) {
				System.out.println("ID: "+resultado.getInt("id_cliente")
				+" Nombre: "+ resultado.getString("nombre")
				+" Domicilio: "+resultado.getString("domicilio")
				+" Telefono: "+resultado.getString("telefono") );
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void crearInstruccion(Statement inst) throws SQLException {
		inst=conexion.createStatement();
	}
	public ResultSet recibirResultado(String str) throws SQLException {
		return resultado = instruccion.executeQuery(str);
	}
	public Statement getInstruccion() {
		return instruccion;
	}
	public Connection getConexion() {
		return conexion;
	}

}

