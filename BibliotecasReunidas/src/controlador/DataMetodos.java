package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Autor;

public class DataMetodos {

	public static ArrayList<Autor> LeerTablaAutor() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Autor> arrlAutor = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select * from Autor limit 100"; // porque son los primeros 100 libros que tenemos en nuestra
															// base de datos
			registro = statement.executeQuery(query);

			while (registro.next()) {

				Autor autor = new Autor();
				autor.setId(registro.getInt("id"));
				autor.setNombre(registro.getString("nombre"));
				autor.setNacionalidad(registro.getString("nacionalidad"));
				autor.setFecha_nacimiento(registro.getString("fecha_nacimiento"));

				arrlAutor.add(autor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}

		return arrlAutor;

	}

	// recogemos el arrayList de autores y luego lo metemos en un arrayList de
	// Objetos
	public static ArrayList<Object[]> obtenerFilasTablaAutor() {

		ArrayList<Autor> autores = LeerTablaAutor();

		ArrayList<Object[]> arrlAutores = new ArrayList<>();

		for (Autor autor : autores) {

			Object[] fila = new Object[] { autor.getId(), autor.getNombre(), autor.getNacionalidad(),
					autor.getFecha_nacimiento() };
			arrlAutores.add(fila);
		}

		return arrlAutores;
	}

	public static boolean modificarTablaAutor(int id, String nombreNuevo, String nacionalidadNueva, String fechaNueva) {
		ConectorBBDD conextor = new ConectorBBDD();

		boolean actualizado = false;
		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = String.format(
					"update autor set nombre='%s', nacionalidad='%s', fecha_nacimiento='%s' where id=%d;",
					nombreNuevo, nacionalidadNueva, fechaNueva,id);
			int count = statement.executeUpdate(query);// esta funcion devuelve el numero de filas que han sido afectadas
														// por el update
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "La Fila se ha actualizado correctamente",
						"Confirmación de los cambios", JOptionPane.INFORMATION_MESSAGE);
				actualizado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido conectar con la BBDD.\n");
		} finally {
			try {
				statement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}
		return actualizado;
	}
	
	public static ArrayList<Autor> selectPorId(int id){
		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Autor> arrlAutor = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;
		
		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = String.format( 
					"Select id, nombre, nacionalidad, fecha_nacimiento from Autor where id=%d;", id); // porque son los primeros 100 libros que tenemos en nuestra
															// base de datos
			registro = statement.executeQuery(query);

			while (registro.next()) {

				Autor autor = new Autor();
				autor.setId(registro.getInt("id"));
				autor.setNombre(registro.getString("nombre"));
				autor.setNacionalidad(registro.getString("nacionalidad"));
				autor.setFecha_nacimiento(registro.getString("fecha_nacimiento"));

				arrlAutor.add(autor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}
		
		return arrlAutor;
	}
}
