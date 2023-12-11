package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controlador.DataMetodos;
import modelo.Autor;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AutorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_nombre;
	private JTextField textField_Cod;
	private JTextField textField_Nacionalidad;
	private JTextField textField_Fecha;
	DefaultTableModel modeloAutor = new DefaultTableModel();
	private JFrame frame;
	private JTable tablaAutores;
	
	JButton btnGuardarNuevoAutor;
	JButton btnConfirmarCambios;
	JButton btnBuscar;
	JButton btnEliminar;
	JButton btnAnyadir;
	JButton btnModificar;
	JButton btnBuscarSuperior;
	JButton btnCancelar;
	/**
	 * Create the panel.
	 */
	public AutorPanel(JFrame frame) {

		// para saber quien contiene el panel.
		this.frame = frame;

		System.out.println("Ventana Autor Panel");

		setLayout(null);
		setBounds(0, 0, 1186, 644);

		JLabel lblcodigoID = new JLabel("Código ID");
		lblcodigoID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblcodigoID.setBounds(776, 121, 80, 34);
		add(lblcodigoID);

		JLabel lblNombre = new JLabel("Nombre ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(776, 204, 60, 34);
		add(lblNombre);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNacionalidad.setBounds(776, 295, 80, 34);
		add(lblNacionalidad);

		JLabel lblFecha = new JLabel("Fecha de Nacimiento");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(765, 379, 137, 34);
		add(lblFecha);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(928, 207, 228, 34);
		add(textField_nombre);
		textField_nombre.setColumns(10);

		textField_Cod = new JTextField();
		textField_Cod.setColumns(10);
		textField_Cod.setBounds(928, 124, 228, 34);
		add(textField_Cod);

		textField_Nacionalidad = new JTextField();
		textField_Nacionalidad.setColumns(10);
		textField_Nacionalidad.setBounds(928, 298, 228, 34);
		add(textField_Nacionalidad);

		textField_Fecha = new JTextField();
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(928, 382, 228, 34);
		add(textField_Fecha);

		JLabel lblTitulo = new JLabel("AUTOR ");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblTitulo.setBounds(22, 29, 94, 24);
		add(lblTitulo);

		//
		btnGuardarNuevoAutor = new JButton("Guardar Nuevo Autor");

		btnGuardarNuevoAutor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(todosLosCamposEstanRellenosParaCrearNuevoAutor()) {
					DataMetodos.insertarAutor(textField_nombre.getText(), textField_Nacionalidad.getText(),
							textField_Fecha.getText());
					limpiarTextFields();
					recargarTablaAutor(); // es para actualizar la tabla en el Jpanel una vez insertado.
				}else {
					String mensaje = "Todos los campos son necesarios. Por favor rellene todos los campos";
			        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnGuardarNuevoAutor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnGuardarNuevoAutor.setBounds(850, 440, 255, 34);

		add(btnGuardarNuevoAutor);

		//
		btnConfirmarCambios = new JButton("Confirmar cambios");
		btnConfirmarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(todosLosCamposEstanRellenosParaCrearNuevoAutor()) {
					DataMetodos.modificarTablaAutor(Integer.parseInt(textField_Cod.getText()), textField_nombre.getText(),
							textField_Nacionalidad.getText(), textField_Fecha.getText());
					limpiarTextFields();
					recargarTablaAutor();
					disminuirTamanyo();
					btnEliminar.setEnabled(false);
					btnModificar.setEnabled(false);
				}else {
					String mensaje = "Todos los campos son necesarios. Por favor rellene todos los campos";
			        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnConfirmarCambios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnConfirmarCambios.setBounds(850, 531, 255, 34);
		add(btnConfirmarCambios);

		//
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(22, 132, 683, 437);
		add(scrollPane);

		//
		tablaAutores = new JTable() {
			/**
			 * Por defecto el JTable debe tener un serialVersionUID. Agregamos el valor por
			 * defecto
			 */
			private static final long serialVersionUID = 1L;

			// Vamos actualizar la IsCellEditerTable para impedir que las celdas de la tabla
			// sean modificadas.
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		scrollPane.setViewportView(tablaAutores);

		modeloAutor.setColumnIdentifiers(new Object[] { "Código ID", "Nombre", "Nacionalidad", "Fecha de Nacimiento" });

		tablaAutores.setModel(modeloAutor);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Autor> autores = DataMetodos.filtraPorCampos(textField_Cod.getText(),
						textField_nombre.getText(), textField_Nacionalidad.getText(), textField_Fecha.getText());
				// desminuirTamanyo();
				limpiarTextFields();
				recargarTablaAutor(autores);
			}
		});

		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar.setBounds(850, 487, 255, 34);
		add(btnBuscar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(modeloAutor.getValueAt(tablaAutores.getSelectedRow(), 0).toString());
				DataMetodos.eliminarAutor(id);
				limpiarTextFields();
				recargarTablaAutor();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(375, 88, 160, 34);
		add(btnEliminar);

		tablaAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Solo se puede seleccionar una fila a la
																			// vez

		tablaAutores.getTableHeader().setReorderingAllowed(false);// Impedir la reordenación de columnas

		// Obtener el modelo de selección de la tabla
		ListSelectionModel selectionModel = tablaAutores.getSelectionModel();
		// Agregar un ListSelectionListener al modelo de selección
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Verificar si la selección está cambiando y no está ajustando
				if (!e.getValueIsAdjusting()) {
					// Habilito estos botones
					btnEliminar.setEnabled(true);
					btnModificar.setEnabled(true);

					if (tablaAutores.getSelectedRow() >= 0) {
						// volcar los campos de la fila en los textfields correspondientes
						String id = modeloAutor.getValueAt(tablaAutores.getSelectedRow(), 0).toString();
						String nombre = modeloAutor.getValueAt(tablaAutores.getSelectedRow(), 1).toString();
						String nacionalidad = modeloAutor.getValueAt(tablaAutores.getSelectedRow(), 2).toString();
						String fecha = modeloAutor.getValueAt(tablaAutores.getSelectedRow(), 3).toString();

						textField_Cod.setText(id);
						textField_nombre.setText(nombre);
						textField_Nacionalidad.setText(nacionalidad);
						textField_Fecha.setText(fecha);

					}

				}
			}
		});
		

		// Sirve para desabilitar la caja de texto
		textField_Cod.setEnabled(false);
		
		btnAnyadir = new JButton("Añadir");
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				aumentarTamanyo();
				lblcodigoID.setVisible(false);
				textField_Cod.setVisible(false);
				btnGuardarNuevoAutor.setVisible(true);
				
				
			}
		});
		btnAnyadir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnAnyadir.setBounds(22, 86, 160, 36);
		add(btnAnyadir);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				textField_Cod.setVisible(true);
				textField_Cod.setEnabled(false);
				btnConfirmarCambios.setVisible(true);
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(192, 87, 160, 36);
		add(btnModificar);
		
		btnBuscarSuperior = new JButton("Buscar");
		btnBuscarSuperior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				btnBuscar.setVisible(true);
				textField_Cod.setEnabled(true);
			}
		});
		btnBuscarSuperior.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscarSuperior.setBounds(545, 86, 160, 36);
		add(btnBuscarSuperior);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTextFields();
				disminuirTamanyo();
				btnConfirmarCambios.setVisible(false);
				btnBuscar.setVisible(false);
				btnGuardarNuevoAutor.setVisible(false);
				recargarTablaAutor();
				btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCancelar.setBounds(850, 575, 255, 36);
		add(btnCancelar);
		
		recargarTablaAutor();
		
		//ocultar botones laterales
		btnConfirmarCambios.setVisible(false);
		btnBuscar.setVisible(false);
		btnGuardarNuevoAutor.setVisible(false);
		
		//deshabilitar
		btnEliminar.setEnabled(false);
		btnModificar.setEnabled(false);
		disminuirTamanyo();
		
	}

	private void limpiarTextFields() {
		// Metodo para limpiar los 3 Text Field
		textField_Cod.setText("");
		textField_nombre.setText("");
		textField_Nacionalidad.setText("");
		textField_Fecha.setText("");
	}

	private void recargarTablaAutor() {

		// Cargo los elementos de la tabla en el modelo
		modeloAutor.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		for (Object[] filaDeAutor : DataMetodos.obtenerFilasTablaAutor()) {
			modeloAutor.addRow(filaDeAutor);
		}
		/*
		 * // Si hay filas en la tabla, que seleccione la primera if
		 * (modeloAutor.getRowCount() > 0) { tablaAutores.setRowSelectionInterval(0, 0);
		 * }
		 */
	}

	// Para buscar
	private void recargarTablaAutor(ArrayList<Autor> autores) {

		modeloAutor.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA

		// cargo todas las filas correspondientes al array pasado por parametro
		for (Autor autor : autores) {
			modeloAutor.addRow(new Object[] { autor.getId(), autor.getNombre(), autor.getNacionalidad(),
					autor.getFecha_nacimiento() });
		}
		/*
		 * //Si hay filas en la tabla, que seleccione la primera
		 * if(modeloAutor.getRowCount()>0) { tablaAutores.setRowSelectionInterval(0, 0);
		 * }
		 */
	}

	private void disminuirTamanyo() {
		if (frame != null) {
			int nuevoAncho = 750;
			int nuevoAlto = frame.getHeight();
			frame.setSize(nuevoAncho, nuevoAlto);

		}
	}

	private void aumentarTamanyo() {
		if (frame != null) {
			int nuevoAncho = 1200;
			int nuevoAlto = frame.getHeight();
			frame.setSize(nuevoAncho, nuevoAlto);

		}
	}

	//Metodo controla que todos los campos de nuevo autor esten llenos.
	public boolean todosLosCamposEstanRellenosParaCrearNuevoAutor() {
		boolean resultado = !textField_nombre.getText().equals("") && !textField_Nacionalidad.getText().equals("")
				&& !textField_Fecha.getText().equals("");

		return resultado;
	}

}
