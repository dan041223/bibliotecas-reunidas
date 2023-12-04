package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Autor;
import modelo.Libro;
import modelo.Libro.CategoriaLibro;

public class DataMetodos {

//============================= Metodos del Jpanel Autor ==============================================
	public static ArrayList<Autor> LeerTablaAutor() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Autor> arrlAutor = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select * from Autor order by id limit 100"; // porque son los primeros 100 libros que
																		// tenemos en nuestra
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
		PreparedStatement preparedStatement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = "update autor set nombre=?, nacionalidad=?, fecha_nacimiento=? where id=?";

			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setString(1, nombreNuevo);
			preparedStatement.setString(2, nacionalidadNueva);

			Date date = Date.valueOf(fechaNueva);
			preparedStatement.setDate(3, date);
			preparedStatement.setInt(4, id);

			int contador = preparedStatement.executeUpdate();

			if (contador > 0) {
				JOptionPane.showMessageDialog(null, "La Fila se ha actualizado correctamente",
						"Confirmación de los cambios", JOptionPane.INFORMATION_MESSAGE);
				actualizado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido conectar con la BBDD.\n");
		} finally {
			try {
				preparedStatement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}
		return actualizado;
	}

	public static ArrayList<Autor> filtraPorCampos(String id, String nombre, String nacionalidad,
			String fecha_nacimiento) {

		// trim sirve para quitar espacios a la derecha e izquierda
		id = id.trim();
		nombre = nombre.trim();
		nacionalidad = nacionalidad.trim();
		fecha_nacimiento = fecha_nacimiento.trim();

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Autor> autores = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet registro = null;
		Connection conexion = null;

		boolean idTieneValores = !id.equals("");// que NO este vacio = TIENE VALORES
		boolean nombreTieneValores = !nombre.equals(""); // que NO este vacio = TIENE VALORES
		boolean nacionalidadTieneValores = !nacionalidad.equals(""); // que NO este vacio = TIENE VALORES
		boolean fechaDeNacimientoTieneValores = !fecha_nacimiento.equals(""); // que NO este vacio = TIENE VALORES

		try {
			conexion = conextor.connect();
			String query = "Select id, nombre, nacionalidad, fecha_nacimiento from Autor where 1=1";

			if (idTieneValores) {
				query = query + " and id = ?";
			}

			// UPPER - sirve para convertir el valor del campo en Mayuscula
			// LOWER - sirve para convertir el valor del campor en minuscula
			// si el campo de texto tiene contenido = que no este vacio
			if (nombreTieneValores) {
				query = query + " and upper(nombre) like ?";
			}

			// si el campo de texto tiene contenido = que no este vacio
			if (nacionalidadTieneValores) {
				query = query + " and upper(nacionalidad) like ?";
			}

			if (fechaDeNacimientoTieneValores) {
				query = query + " and fecha_nacimiento=?";
			}

			preparedStatement = conexion.prepareStatement(query);

			int indice = 1;
			if (idTieneValores) {
				preparedStatement.setInt(indice, Integer.parseInt(id));
				indice++;
			}

			if (nombreTieneValores) {
				preparedStatement.setString(indice, "%" + nombre.toUpperCase() + "%");
				indice++;
			}

			if (nacionalidadTieneValores) {
				preparedStatement.setString(indice, "%" + nacionalidad.toUpperCase() + "%");
				indice++;
			}

			if (fechaDeNacimientoTieneValores) {
				Date date = Date.valueOf(fecha_nacimiento);
				preparedStatement.setDate(indice, date);
				indice++;
			}
			// base de datos
			registro = preparedStatement.executeQuery();

			while (registro.next()) {
				Autor autor = new Autor();
				autor.setId(registro.getInt("id"));
				autor.setNombre(registro.getString("nombre"));
				autor.setNacionalidad(registro.getString("nacionalidad"));
				autor.setFecha_nacimiento(registro.getString("fecha_nacimiento"));
				autores.add(autor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}

		return autores;
	}

	/// Crear autor nuevo
	public static void insertarAutor(String nombre, String nacionalidad, String fecha_nacimiento) {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Autor> arrlAutor = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {

			conexion = conextor.connect();

			String query = String.format("insert into autor (nombre, nacionalidad, fecha_nacimiento)values(?,?,?);");

			preparedStatement = conexion.prepareStatement(query);

			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, nacionalidad);

			Date date = Date.valueOf(fecha_nacimiento);
			preparedStatement.setDate(3, date);

			int contador = preparedStatement.executeUpdate();

			if (contador > 0) {
				JOptionPane.showMessageDialog(null, "La Fila se ha insertado correctamente",
						"Confirmación de los inserción", JOptionPane.INFORMATION_MESSAGE);
			}

			System.out.println("Inserción exitosa.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al cerrar.\n");
			} catch (NullPointerException e) {

			}
		}

	}

	// Eliminar fisico

	public static void eliminarAutor(int id) {

		ConectorBBDD conextor = new ConectorBBDD();

		boolean actualizado = false;
		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			int confirmacion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar la fila ??",
					"Confirmación de eliminacion ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (confirmacion == JOptionPane.YES_OPTION) {
				conexion = conextor.connect();
				statement = conexion.createStatement();
				System.out.println(id);
				String query = String.format("delete from autor where id = %d;", id);
				int count = statement.executeUpdate(query);// esta funcion devuelve el numero de filas que han sido
															// afectadas

				if (count > 0) {
					JOptionPane.showMessageDialog(null, "El proceso de eliminacion ha terminado correctamente",
							"Confirmación de eliminacion", JOptionPane.INFORMATION_MESSAGE);
					actualizado = true;
				}
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
	}

//====================================Aqui comienzan los metodos de Libros ============================

	public static ArrayList<Libro> LeerTablaLibro() {

		ConectorBBDD conextor = new ConectorBBDD();

		ArrayList<Libro> arrlLibro = new ArrayList<>();

		Statement statement = null;
		ResultSet registro = null;
		Connection conexion = null;

		try {
			conexion = conextor.connect();
			statement = conexion.createStatement();
			String query = "Select * from libros order by id limit 100"; // porque son los primeros 100 libros que
																		// tenemos en nuestra
			// base de datos
			registro = statement.executeQuery(query);

			while (registro.next()) {

				Libro libro = new Libro();
				libro.setId(registro.getInt("id"));
				libro.setTitulo(registro.getString("titulo"));
				libro.setCategoria(obtenerCategoriaLibro(registro.getString("categoria")));
				libro.setIdioma(registro.getString("idioma"));
				libro.setFecha_publicacion(registro.getString("fecha_publicacion"));
				libro.setId_editorial(registro.getInt("id_editorial"));
				libro.setId_ubicacion(registro.getInt("id_ubicacion"));
				libro.setIsbn(registro.getInt("isbn"));
				arrlLibro.add(libro);
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

		return arrlLibro;

	}

	public static CategoriaLibro obtenerCategoriaLibro(String categoria) {
		/*
		 * ROMANCE, DRAMA, TERROR, SUSPENSE, CIENCIA_FICCION, POESIA,
		 * LITERATURA_INFANTIL, AVENTURA, HISTORIA, GEOGRAFIA, OTRO
		 * 
		 */
		CategoriaLibro result = null;

		switch (categoria.toUpperCase()) {
		case "ROMANCE":
			result = CategoriaLibro.ROMANCE;
			break;

		case "DRAMA":
			result = CategoriaLibro.DRAMA;
			break;

		case "TERROR":
			result = CategoriaLibro.TERROR;
			break;

		case "SUSPENSE":
			result = CategoriaLibro.SUSPENSE;
			break;

		case "CIENCIA_FICCION":
			result = CategoriaLibro.CIENCIA_FICCION;
			break;

		case "POESIA":
			result = CategoriaLibro.POESIA;
			break;

		case "LITERATURA_INFANTIL":
			result = CategoriaLibro.LITERATURA_INFANTIL;
			break;

		case "AVENTURA":
			result = CategoriaLibro.AVENTURA;
			break;

		case "HISTORIA":
			result = CategoriaLibro.HISTORIA;
			break;

		case "GEOGRAFIA":
			result = CategoriaLibro.GEOGRAFIA;
			break;

		case "OTRO":
			result = CategoriaLibro.OTRO;
			break;

		default:
			result = CategoriaLibro.OTRO;
		}
		return result;

	}
	
	public static ArrayList<Object[]> obtenerFilasTablaLibro() {

		ArrayList<Libro> libros = LeerTablaLibro();

		ArrayList<Object[]> arrlLibros = new ArrayList<>();

		for (Libro libro : libros) {

			Object[] fila = new Object[] { 
					libro.getId(),
					libro.getTitulo(),
					libro.getCategoria(),
					libro.getIdioma(),
					libro.getFecha_publicacion(),
					libro.getId_editorial(),
					libro.getId_ubicacion(),
					libro.getIsbn()};
				arrlLibros.add(fila);
		}

		return arrlLibros;
	}

}
