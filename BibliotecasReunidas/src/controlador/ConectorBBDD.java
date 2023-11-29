package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConectorBBDD {

	final String url = "jdbc:postgresql://db.sbuicfeenrshatyqggbt.supabase.co:5432/postgres";
	final String user = "postgres";
	final String password = "Dalexiana1223";

	/*
	 * Este metodo se conecta a la BBDD y devuelve la conexion,
	 * la cual utilizaremos para realizar todas las operaciones
	 */
	
	 public Connection connect() {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url, user, password);
				System.out.println("Conectado correctamente");
			} catch (SQLException e) {
				e.printStackTrace();
			}   	
	        return conn;
    }
	 
	 public void agregarAdministrativo() {
		 
	 }
}
