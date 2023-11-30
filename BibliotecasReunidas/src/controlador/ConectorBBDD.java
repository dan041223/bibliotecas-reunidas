package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.Usuario;
import modelo.Usuario.TIPO_PERFIL;


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
			} catch (SQLException e) {
				e.printStackTrace();
			}   	
	        return conn;
    }
	 
	 public Usuario consultarUsuario(String correo, String password) {
		 Connection con = connect();
		 Usuario usuario = null;
		 try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE email = '" + correo + "' AND password = '" + password + "';"); 
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setNombre(rs.getString("nombre"));
				usuario.setTelefono(rs.getInt("telefono"));
				usuario.setEmail(rs.getString("email"));
				usuario.setCalle(rs.getString("calle"));
				usuario.setCodigo_postal(rs.getInt("codigo_postal"));
				usuario.setDni(rs.getString("dni"));
				usuario.setPassword(rs.getString("password"));
				
				if ("administrador".equals(rs.getString("tipo_perfil"))) {
					
				    usuario.setTipo_perfil(TIPO_PERFIL.ADMINISTRADOR);
				    System.out.println(usuario.getTipo_perfil().toString());
				    
				} else if ("administrativo".equals(rs.getString("tipo_perfil"))) {
					
				    usuario.setTipo_perfil(TIPO_PERFIL.ADMINISTRATIVO);
				    System.out.println(usuario.getTipo_perfil().toString());
				    
				}
				
				JOptionPane.showMessageDialog(null, "Se ha encontrado al usuario");
			}else if(!rs.next()){
				JOptionPane.showMessageDialog(null, "No se ha encontrador ningún usuario con esta información", "Error al buscar usuario", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	 }
	 
	 public void agregarAdministrativo() {
		 
	 }
}
