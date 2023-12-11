package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controlador.DataMetodos;
import modelo.Item;
import modelo.Libro;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class LibroPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldCod;
	private JTextField textField_Titulo;
	private JTextField textField_Idioma;
	private JTextField textField_Fecha;
	private JTable tableLibros;
	DefaultTableModel modeloLibro = new DefaultTableModel();
	private JTextField textField_isbn;

	private JFrame frame;

	// Creamos los JButton
	JButton btnBuscar;
	JButton btnCancelar;
	JButton btnGuardarCambios;
	JButton btnAnyadir;
	JButton btnModificar;
	JButton btnEliminar;
	JButton btnBuscarSuperior;
	JButton btnCrearLibro;
	JComboBox comboBoxCategoria;
	private JComboBox comboBoxUbicacion;
	private JComboBox comboBoxEditorial;

	/**
	 * Create the panel.
	 */
	public LibroPanel(JFrame frame) {

		this.frame = frame;
		setLayout(null);
		setBounds(0, 0, 1186, 711);

		// btnGuardarCambios
		btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(modeloLibro.getValueAt(tableLibros.getSelectedRow(), 0).toString());
				DataMetodos.modificarTablaLibro(id, textField_Titulo.getText(),
						comboBoxCategoria.getSelectedItem().toString(), textField_Idioma.getText(),
						textField_Fecha.getText(), ((Item) comboBoxEditorial.getSelectedItem()).getIdEnLaTabla(),
						((Item) comboBoxUbicacion.getSelectedItem()).getIdEnLaTabla(), textField_isbn.getText());
				limpiarTextFields();
				recargarTablaAutor();
				disminuirTamanyo();

			}
		});

		btnCrearLibro = new JButton("Guardar nuevo libro");
		btnCrearLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean camposRellenados = todosLosCamposEstanRellenosParaCrearNuevoLibro();

				if (camposRellenados) {
					DataMetodos.insertarLibro(textField_Titulo.getText(),
							comboBoxCategoria.getSelectedItem().toString(), textField_Idioma.getText(),
							textField_Fecha.getText(), ((Item) comboBoxEditorial.getSelectedItem()).getIdEnLaTabla(),
							((Item) comboBoxUbicacion.getSelectedItem()).getIdEnLaTabla(),
							Integer.parseInt(textField_isbn.getText()));

					limpiarTextFields();
					recargarTablaAutor();
					disminuirTamanyo();
				} else {
					String mensaje = "Todos los campos son necesarios. Por favor rellene todos los campos";
					JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCrearLibro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCrearLibro.setBounds(872, 529, 220, 30);
		add(btnCrearLibro);
		btnCrearLibro.setVisible(false);
		btnGuardarCambios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnGuardarCambios.setBounds(872, 529, 220, 30);
		add(btnGuardarCambios);
		btnGuardarCambios.setVisible(false);

		// Creamos los JLabels
		JLabel lblCodigo = new JLabel("Código ID");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(810, 89, 89, 30);
		add(lblCodigo);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(810, 132, 89, 30);
		add(lblTitulo);

		JLabel lblCategoriaDelLibro = new JLabel("Categoria Del Libro");
		lblCategoriaDelLibro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoriaDelLibro.setBounds(810, 172, 120, 30);
		add(lblCategoriaDelLibro);

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdioma.setBounds(810, 212, 120, 30);
		add(lblIdioma);

		JLabel lblFechaDePublicacin = new JLabel("Fecha de Publicación");
		lblFechaDePublicacin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaDePublicacin.setBounds(810, 252, 156, 30);
		add(lblFechaDePublicacin);

		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditorial.setBounds(810, 292, 120, 24);
		add(lblEditorial);

		JLabel lblUbicacion = new JLabel("Ubicacion ");
		lblUbicacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUbicacion.setBounds(810, 332, 113, 24);
		add(lblUbicacion);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsbn.setBounds(810, 366, 156, 30);
		add(lblIsbn);

		JLabel lblLibro = new JLabel("Libro");
		lblLibro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblLibro.setBounds(43, 28, 79, 45);
		add(lblLibro);

		// Creando TextFields

		textFieldCod = new JTextField();
		textFieldCod.setBounds(949, 92, 212, 30);
		add(textFieldCod);
		textFieldCod.setColumns(10);

		textField_Titulo = new JTextField();
		textField_Titulo.setColumns(10);
		textField_Titulo.setBounds(949, 132, 212, 30);
		add(textField_Titulo);

		textField_Idioma = new JTextField();
		textField_Idioma.setColumns(10);
		textField_Idioma.setBounds(949, 215, 212, 30);
		add(textField_Idioma);

		textField_Fecha = new JTextField();
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(949, 255, 212, 30);
		add(textField_Fecha);

		textField_isbn = new JTextField();
		textField_isbn.setColumns(10);
		textField_isbn.setBounds(949, 369, 212, 30);
		add(textField_isbn);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disminuirTamanyo();
				limpiarTextFields();
				recargarTablaAutor();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCancelar.setBounds(872, 569, 220, 30);
		add(btnCancelar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Libro> libros = DataMetodos.filtraPorCamposLibros(textFieldCod.getText(),
						textField_Titulo.getText(), comboBoxCategoria.getSelectedItem().toString(),
						textField_Idioma.getText(), textField_Fecha.getText(),
						((Item) comboBoxEditorial.getSelectedItem()).getIdEnLaTabla(),
						((Item) comboBoxUbicacion.getSelectedItem()).getIdEnLaTabla(), textField_isbn.getText());
				limpiarTextFields();
				recargarTablaAutor(libros);

				// deshabilitar botones
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);

			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar.setBounds(872, 529, 220, 30);
		add(btnBuscar);

		btnAnyadir = new JButton("Añadir");
		btnAnyadir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();

				// Ocultar label
				lblCodigo.setVisible(false);

				// Ocultar TextField Cod
				textFieldCod.setVisible(false);

				// Ocultar botones
				btnBuscar.setVisible(false);
				btnGuardarCambios.setVisible(false);

				// hacer visible los botones
				btnCrearLibro.setVisible(true);
			}
		});
		btnAnyadir.setBounds(130, 41, 89, 32);
		add(btnAnyadir);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Hacer visible label
				lblCodigo.setVisible(true);

				// Hacer visible TextField Cod pero deshabilitado
				textFieldCod.setVisible(true);
				textFieldCod.setEnabled(false);

				// ocultar botones del panel lateral
				btnBuscar.setVisible(false);
				btnCrearLibro.setVisible(false);
				// mostrar botones en el panel lateral
				btnGuardarCambios.setVisible(true);

				aumentarTamanyo();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(230, 41, 120, 32);
		add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(modeloLibro.getValueAt(tableLibros.getSelectedRow(), 0).toString());
				// DataMetodos.eliminarLibro(id);
				DataMetodos.eliminarLibroLogicamente(id);
				limpiarTextFields();
				recargarTablaAutor();

				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);

			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(360, 43, 99, 30);
		add(btnEliminar);

		// ------------------Creo la tabla ---------------

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 83, 742, 540);
		add(scrollPane);

		// Vamos actualizar la IsCellEditerTable para impedir que las celdas de la tabla
		// sean modificadas.
		tableLibros = new JTable() {
			/**
			 * Por defecto el JTable debe tener un serialVersionUID. Agregamos el valor por
			 * defecto
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		scrollPane.setViewportView(tableLibros);
		modeloLibro.setColumnIdentifiers(new Object[] { "ID", "Titulo", "Categoria del Libro", "Idioma",
				"Fecha de publicacion", "nombre", "ubicacion", "ISBN" });
		tableLibros.setModel(modeloLibro);

		btnBuscarSuperior = new JButton("Buscar");
		btnBuscarSuperior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();

				// habilitar campos
				textFieldCod.setEnabled(true);

				// hacer visible textfield y label del codigo
				textFieldCod.setVisible(true);
				lblCodigo.setVisible(true);

				// deshabilitar botones
				btnGuardarCambios.setVisible(false);
				btnCrearLibro.setVisible(false);

				// habilitar botones
				btnBuscar.setVisible(true);

				// limpiar campos
				limpiarTextFields();
			}
		});
		btnBuscarSuperior.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscarSuperior.setBounds(469, 43, 120, 32);
		add(btnBuscarSuperior);

		// Obtener el modelo de selección de la tabla
		ListSelectionModel selectionModel = tableLibros.getSelectionModel();
		// Agregar un ListSelectionListener al modelo de selección
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Verificar si la selección está cambiando y no está ajustando
				if (!e.getValueIsAdjusting()) {
					// Habilito estos botones
					btnGuardarCambios.setEnabled(true);
					btnEliminar.setEnabled(true);

					if (tableLibros.getSelectedRow() >= 0) {// si tenemos una fila seleccionada
						// volcar los campos de la fila en los textfields correspondientes
						String id = modeloLibro.getValueAt(tableLibros.getSelectedRow(), 0).toString();
						String titulo = modeloLibro.getValueAt(tableLibros.getSelectedRow(), 1).toString();
						String categoria = modeloLibro.getValueAt(tableLibros.getSelectedRow(), 2).toString();
						String idioma = modeloLibro.getValueAt(tableLibros.getSelectedRow(), 3).toString();
						String fecha = modeloLibro.getValueAt(tableLibros.getSelectedRow(), 4).toString();
						String editorial = modeloLibro.getValueAt(tableLibros.getSelectedRow(), 5).toString();
						String ubicacion = modeloLibro.getValueAt(tableLibros.getSelectedRow(), 6).toString();
						String isbn = modeloLibro.getValueAt(tableLibros.getSelectedRow(), 7).toString();

						textFieldCod.setText(id);
						textField_Titulo.setText(titulo);
						comboBoxCategoria.setSelectedItem(categoria);
						textField_Idioma.setText(idioma);
						textField_Fecha.setText(fecha);

						//
						Item itemEditorial = getItemPorNombre(comboBoxEditorial, editorial);
						comboBoxEditorial.setSelectedItem(itemEditorial);

						Item itemUbicacion = getItemPorNombre(comboBoxUbicacion, ubicacion);
						comboBoxUbicacion.setSelectedItem(itemUbicacion);
						textField_isbn.setText(isbn);

						// Habilitar campos
						btnEliminar.setEnabled(true);
						btnModificar.setEnabled(true);

					}
				}
			}
		});

		recargarTablaAutor();
		disminuirTamanyo();

		// Deshabilitar los botones por defecto
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);

		String[] categorias = { "romance", "drama", "terror", "suspense", "ciencia_ficcion", "poesia",
				"literatura_infantil", "aventura", "historia", "geografia", "otros" };
		comboBoxCategoria = new JComboBox(categorias);
		comboBoxCategoria.setBounds(949, 181, 212, 24);
		add(comboBoxCategoria);

		Object[] ubicaciones = DataMetodos.obtenerUbicacionesParaJComboBox();
		comboBoxUbicacion = new JComboBox(ubicaciones);
		comboBoxUbicacion.setBounds(949, 336, 212, 30);
		add(comboBoxUbicacion);

		Object[] editoriales = DataMetodos.obtenerEditorialesParaJComboBox();
		comboBoxEditorial = new JComboBox(editoriales);
		comboBoxEditorial.setBounds(949, 302, 212, 24);
		add(comboBoxEditorial);

		// ocultar los botones del panel derecho por defecto
		// se irán mostrando segun lo vayamos necesitando
		btnBuscar.setVisible(false);

		// Poner ancho a las celdas de las tablas
		tableLibros.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableLibros.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableLibros.getColumnModel().getColumn(6).setPreferredWidth(230);

	}

	private void recargarTablaAutor() {

		// Cargo los elementos de la tabla en el modelo
		modeloLibro.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		for (Object[] filaLibro : DataMetodos.obtenerFilasTablaLibro()) {
			modeloLibro.addRow(filaLibro);
		}
		/*
		 * // Si hay filas en la tabla, que seleccione la primera if
		 * (modeloAutor.getRowCount() > 0) { tablaAutores.setRowSelectionInterval(0, 0);
		 * }
		 */
	}

	private void recargarTablaAutor(ArrayList<Libro> libros) {

		modeloLibro.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		for (Libro libro : libros) {
			modeloLibro.addRow(new Object[] { libro.getId(), libro.getTitulo(), libro.getCategoria(), libro.getIdioma(),
					libro.getFecha_publicacion(), libro.getNombreDeEditorial(), libro.getUbicacion(),
					libro.getIsbn() });
		}

	}

	private void limpiarTextFields() {
		textFieldCod.setText("");
		textField_Titulo.setText("");
		comboBoxCategoria.setSelectedIndex(0);
		textField_Idioma.setText("");
		textField_Fecha.setText("");
		comboBoxEditorial.setSelectedIndex(0);
		comboBoxUbicacion.setSelectedIndex(0);
		textField_isbn.setText("");
	}

	private void disminuirTamanyo() {
		if (frame != null) {
			int nuevoAncho = 810;
			int nuevoAlto = frame.getHeight();
			frame.setSize(nuevoAncho, nuevoAlto);
			// frame.invalidate();
			// frame.repaint();
			System.out.println("Disminuido");
		}
	}

	private void aumentarTamanyo() {
		if (frame != null) {
			int nuevoAncho = 1350;
			int nuevoAlto = frame.getHeight();
			frame.setSize(nuevoAncho, nuevoAlto);
			System.out.println("Aumentado");
		}
	}

	public boolean todosLosCamposEstanRellenosParaCrearNuevoLibro() {
		boolean resultado = !textField_Titulo.getText().equals("") && !textField_Idioma.getText().equals("")
				&& !textField_Fecha.getText().equals("") && !textField_isbn.getText().equals("");

		return resultado;
	}

	private Item getItemPorNombre(JComboBox<Item> comboBox, String nombre) {
		boolean encontrado = false;
		Item item = null;

		for (int i = 0; i < comboBox.getItemCount() && encontrado == false; i++) {
			if (comboBox.getItemAt(i).getNombreMostradoEnElCombo().equals(nombre)) {
				encontrado = true;
				item = comboBox.getItemAt(i);
			}
		}

		return item;
	}
}
