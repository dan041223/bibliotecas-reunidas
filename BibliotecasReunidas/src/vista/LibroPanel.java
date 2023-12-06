package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import modelo.Libro;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LibroPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldCod;
	private JTextField textField_Titulo;
	private JTextField textField_Categoria;
	private JTextField textField_Idioma;
	private JTextField textField_Fecha;
	private JTextField textField_Editorial;
	private JTable tableLibros;
	DefaultTableModel modeloLibro = new DefaultTableModel();
	private JTextField textField_Ubicacion;
	private JTextField textField_isbn;

	/**
	 * Create the panel.
	 */
	public LibroPanel() {

		setLayout(null);
		setBounds(0, 0, 1186, 711);

		JLabel lblCódigo = new JLabel("Código ID");
		lblCódigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCódigo.setBounds(810, 89, 89, 30);
		add(lblCódigo);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(810, 132, 89, 30);
		add(lblTitulo);

		JLabel lblCategoriaDelLibro = new JLabel("Categoria Del Libro");
		lblCategoriaDelLibro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoriaDelLibro.setBounds(810, 184, 120, 30);
		add(lblCategoriaDelLibro);

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdioma.setBounds(810, 238, 120, 30);
		add(lblIdioma);

		JLabel lblFechaDePublicacin = new JLabel("Fecha de Publicación");
		lblFechaDePublicacin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaDePublicacin.setBounds(810, 300, 156, 30);
		add(lblFechaDePublicacin);

		JLabel lblEditorial = new JLabel("Id Editorial");
		lblEditorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditorial.setBounds(810, 360, 120, 24);
		add(lblEditorial);

		JLabel lblUbicacion = new JLabel("Id Ubicacion ");
		lblUbicacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUbicacion.setBounds(810, 413, 113, 24);
		add(lblUbicacion);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsbn.setBounds(810, 460, 156, 30);
		add(lblIsbn);

		textFieldCod = new JTextField();
		textFieldCod.setBounds(949, 77, 212, 30);
		add(textFieldCod);
		textFieldCod.setColumns(10);

		textField_Titulo = new JTextField();
		textField_Titulo.setColumns(10);
		textField_Titulo.setBounds(949, 132, 212, 30);
		add(textField_Titulo);

		textField_Categoria = new JTextField();
		textField_Categoria.setColumns(10);
		textField_Categoria.setBounds(949, 187, 212, 30);
		add(textField_Categoria);

		textField_Idioma = new JTextField();
		textField_Idioma.setColumns(10);
		textField_Idioma.setBounds(949, 241, 212, 30);
		add(textField_Idioma);

		textField_Fecha = new JTextField();
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(949, 303, 212, 30);
		add(textField_Fecha);

		textField_Editorial = new JTextField();
		textField_Editorial.setColumns(10);
		textField_Editorial.setBounds(949, 354, 212, 30);
		add(textField_Editorial);

		textField_Ubicacion = new JTextField();
		textField_Ubicacion.setColumns(10);
		textField_Ubicacion.setBounds(949, 413, 212, 30);
		add(textField_Ubicacion);

		textField_isbn = new JTextField();
		textField_isbn.setColumns(10);
		textField_isbn.setBounds(949, 460, 212, 30);
		add(textField_isbn);

		JButton btnCrear = new JButton("Añadir");
		btnCrear.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DataMetodos.insertarLibro(textField_Titulo.getText(), textField_Categoria.getText(),
						textField_Idioma.getText(), textField_Fecha.getText(),
						Integer.parseInt(textField_Editorial.getText()),
						Integer.parseInt(textField_Ubicacion.getText()), Integer.parseInt(textField_isbn.getText()));
				
						limparTextFields();
						recargarTablaAutor();

			}
		});
		btnCrear.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCrear.setBounds(835, 531, 99, 30);
		add(btnCrear);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(modeloLibro.getValueAt(tableLibros.getSelectedRow(), 0).toString());
				DataMetodos.modificarTablaLibro(id,textField_Titulo.getText(), textField_Categoria.getText(), textField_Idioma.getText(), 
												textField_Fecha.getText(), textField_Editorial.getText(), textField_Ubicacion.getText(), textField_isbn.getText());
				limparTextFields();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(835, 593, 99, 30);
		add(btnModificar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Libro> libros = DataMetodos.filtraPorCamposLibros(textFieldCod.getText(),textField_Titulo.getText(), textField_Categoria.getText(),textField_Idioma.getText(),
						 textField_Fecha.getText(),textField_Editorial.getText(),textField_Ubicacion.getText(),textField_isbn.getText());
				limparTextFields();
				recargarTablaAutor(libros);
				
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar.setBounds(1040, 531, 99, 30);
		add(btnBuscar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(modeloLibro.getValueAt(tableLibros.getSelectedRow(), 0).toString());
				DataMetodos.eliminarLibro(id);
				limparTextFields();
				recargarTablaAutor();
				
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(1040, 593, 99, 30);
		add(btnEliminar);

		JLabel lblNewLabel = new JLabel("Libro");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(43, 28, 79, 45);
		add(lblNewLabel);

		// ------------------Creo la tabla ---------------

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 83, 742, 540);
		add(scrollPane);

		// Vamos actualizar la IsCellEditerTable para impedir que las celdas de la tabla
		// sean modificadas.
		tableLibros = new JTable() {
			/**
			 * Por defecto el JTable debe tener un serialVersionUID. Agregamos el valor por defecto
			 */
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		scrollPane.setViewportView(tableLibros);
		modeloLibro.setColumnIdentifiers(new Object[] { "Código ID", "Titulo", "Categoria del Libro", "Idioma",
				"Fecha de publicacion", "Id_Editorial", "Id_Ubicacion", "ISBN" });
		tableLibros.setModel(modeloLibro);

	
		//Sirve para desabilitar la caja de texto
		textFieldCod.setEnabled(false);
		//Sirve para desabilitar el Boton
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		
		// Obtener el modelo de selección de la tabla
        ListSelectionModel selectionModel = tableLibros.getSelectionModel();
     // Agregar un ListSelectionListener al modelo de selección
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verificar si la selección está cambiando y no está ajustando
                if (!e.getValueIsAdjusting()) { 
                	//Habilito estos botones
                	btnModificar.setEnabled(true);
            		btnEliminar.setEnabled(true);
                	
                }
            }
        });
        
		
		recargarTablaAutor();
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
			modeloLibro.addRow(new Object[] {
					libro.getId(),
					libro.getTitulo(),
					libro.getCategoria(),
					libro.getIdioma(),
					libro.getFecha_publicacion(),
					libro.getId_editorial(),
					libro.getId_ubicacion(),
					libro.getIsbn()
			});
		}
		
	}
	
	
	
	private void limparTextFields() {
		textFieldCod.setText("");
		textField_Titulo.setText("");
		textField_Categoria.setText("");
		textField_Idioma.setText("");
		textField_Fecha.setText("");
		textField_Editorial.setText("");
		textField_Ubicacion.setText("");
		textField_isbn.setText("");
		
	}
}
