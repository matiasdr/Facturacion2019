package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class InteraccionBD {
	
	private Conexion conexion;
	private String consulta;
	private String resultado; 
	
	public InteraccionBD(Conexion con) {
		con=this.conexion;
	}
	public void insertar() {
		
	}
	
	public void Eliminar() {
		
	}
	
	public String Listar() {
		return resultado;
	}
	/*
	public void listarClientes() {
		try {
			conexion.conectar();
			Statement instruccion = conexion. 
			consulta = "SELECT * FROM cliente"; 
			ResultSet resultado = conexion.recibirResultado(consulta);
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
	*/

}
