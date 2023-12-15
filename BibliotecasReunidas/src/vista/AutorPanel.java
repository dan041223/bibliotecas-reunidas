package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controlador.DataMetodos;
import modelo.Autor;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

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
		setBackground(new Color(128, 128, 192));

		// para saber quien contiene el panel.
		this.frame = frame;
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("res\\imagenes\\flechita_atras.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int labelWidth = 40;
	    int labelHeight = 40;
	    
	    Image scaledImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
		
	    ImageIcon icon = new ImageIcon(scaledImg);
	    
		JLabel imgs = new JLabel(icon);
		imgs.setForeground(new Color(255, 255, 255));
		imgs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new MenuPanel(frame));
			}
		});
		imgs.setBounds(10, 11, 47, 40);
		
		add(imgs);
		
		JLabel lblCerrarSesion = new JLabel("Volver al menu");
		lblCerrarSesion.setForeground(new Color(255, 255, 255));
		lblCerrarSesion.setBackground(new Color(255, 255, 255));
		lblCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCerrarSesion.setBounds(55, 23, 181, 14);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new MenuPanel(frame));
			}
		});
		add(lblCerrarSesion);

		setLayout(null);
		setBounds(0, 0, 1186, 644);

		JLabel lblcodigoID = new JLabel("Código ID");
		lblcodigoID.setForeground(new Color(255, 255, 255));
		lblcodigoID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblcodigoID.setBounds(899, 123, 80, 34);
		add(lblcodigoID);

		JLabel lblNombre = new JLabel("Nombre ");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(899, 206, 60, 34);
		add(lblNombre);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setForeground(new Color(255, 255, 255));
		lblNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNacionalidad.setBounds(899, 297, 80, 34);
		add(lblNacionalidad);

		JLabel lblFecha = new JLabel("Fecha de Nacimiento");
		lblFecha.setForeground(new Color(255, 255, 255));
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(888, 381, 137, 34);
		add(lblFecha);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(1051, 209, 228, 34);
		add(textField_nombre);
		textField_nombre.setColumns(10);

		textField_Cod = new JTextField();
		textField_Cod.setColumns(10);
		textField_Cod.setBounds(1051, 126, 228, 34);
		add(textField_Cod);

		textField_Nacionalidad = new JTextField();
		textField_Nacionalidad.setColumns(10);
		textField_Nacionalidad.setBounds(1051, 300, 228, 34);
		add(textField_Nacionalidad);

		textField_Fecha = new JTextField();
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(1051, 384, 228, 34);
		add(textField_Fecha);

		JLabel lblTitulo = new JLabel("AUTOR ");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblTitulo.setBounds(30, 69, 94, 24);
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

		scrollPane.setBounds(30, 150, 683, 437);
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
		btnEliminar.setBounds(383, 106, 160, 34);
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
		btnAnyadir.setBounds(30, 104, 160, 36);
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
		btnModificar.setBounds(200, 105, 160, 36);
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
		btnBuscarSuperior.setBounds(553, 104, 160, 36);
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
		
		BufferedImage img1 = null;
		try {
			img1 = ImageIO.read(new File("res\\imagenes\\posibleFondo.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth1 = 1424;
	    int labelHeight1 = 825;
	    
	    Image scaledImg1 = img1.getScaledInstance(labelWidth1, labelHeight1, Image.SCALE_SMOOTH);
		
	    ImageIcon icon1 = new ImageIcon(scaledImg1);
	    
		JLabel lblNewLabel = new JLabel(icon1);
		lblNewLabel.setBounds(0, 0, 1434, 825);
		add(lblNewLabel);
		
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
