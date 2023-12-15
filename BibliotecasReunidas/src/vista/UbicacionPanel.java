package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import modelo.Biblioteca;
import modelo.Ubicacion;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

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

public class UbicacionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableUbicacion;
	DefaultTableModel modeloUbicacion = new DefaultTableModel();
	private JTextField textField_Id;
	private JTextField textField_Pasillo;
	private JTextField textField_Estante;
	private JTextField textField_CodigoBiblioteca;
	private JFrame frame;

	JButton btnGuardarUbicacion;
	JButton btnConfirmarCambios;
	JButton btnBuscar;
	JButton btnEliminar;
	JButton btnCancelar;
	JButton btnAnyadir;
	JButton btnModificar_Superior;
	JButton btnBuscar_Superior;

	/**
	 * Create the panel.
	 */
	public UbicacionPanel(JFrame frame) {
		setBackground(new Color(128, 128, 192));
		this.frame = frame;

		setLayout(null);
		
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

		JLabel lblNewLabel = new JLabel("Ubicación");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(78, 66, 167, 39);
		add(lblNewLabel);

		// -------------------------Creando la tabla -------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 158, 611, 462);
		add(scrollPane);

		tableUbicacion = new JTable() {
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

		scrollPane.setViewportView(tableUbicacion);

		modeloUbicacion.setColumnIdentifiers(new Object[] { "Código ID", "Pasillo", "Estante", "Id_Biblioteca", });

		tableUbicacion.setModel(modeloUbicacion);

		JLabel lblCodigo = new JLabel("Codigo ID");
		lblCodigo.setForeground(new Color(255, 255, 255));
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(834, 158, 78, 33);
		add(lblCodigo);

		JLabel lblProvincia = new JLabel("Pasillo");
		lblProvincia.setForeground(new Color(255, 255, 255));
		lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProvincia.setBounds(834, 215, 78, 33);
		add(lblProvincia);

		JLabel lblCodigoPostal = new JLabel("Estante");
		lblCodigoPostal.setForeground(new Color(255, 255, 255));
		lblCodigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoPostal.setBounds(834, 288, 104, 33);
		add(lblCodigoPostal);

		JLabel lblTelefono = new JLabel("Código biblioteca ");
		lblTelefono.setForeground(new Color(255, 255, 255));
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(834, 345, 120, 33);
		add(lblTelefono);

		textField_Id = new JTextField();
		textField_Id.setBounds(950, 158, 221, 33);
		add(textField_Id);
		textField_Id.setColumns(10);

		textField_Pasillo = new JTextField();
		textField_Pasillo.setColumns(10);
		textField_Pasillo.setBounds(950, 217, 221, 33);
		add(textField_Pasillo);

		textField_Estante = new JTextField();
		textField_Estante.setColumns(10);
		textField_Estante.setBounds(950, 290, 221, 33);
		add(textField_Estante);

		textField_CodigoBiblioteca = new JTextField();
		textField_CodigoBiblioteca.setColumns(10);
		textField_CodigoBiblioteca.setBounds(950, 347, 221, 33);
		add(textField_CodigoBiblioteca);

		btnGuardarUbicacion = new JButton("Guardar nueva ubicacion");
		btnGuardarUbicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (todosLosCamposEstanRellenosParaCrearNuevoAutor()) {
					DataMetodos.insertarUbicacion(textField_Pasillo.getText(), textField_Estante.getText(),
							textField_CodigoBiblioteca.getText());
					limpiarTextfields();
					recargarTablaUbicacion();

				} else {
					String mensaje = "Todos los campos son necesarios. Por favor rellene todos los campos";
					JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnGuardarUbicacion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnGuardarUbicacion.setBounds(782, 410, 221, 39);
		add(btnGuardarUbicacion);

		btnConfirmarCambios = new JButton("Confirmar cambios");
		btnConfirmarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (todosLosCamposEstanRellenosParaCrearNuevoAutor()) {
					int id = Integer
							.parseInt(modeloUbicacion.getValueAt(tableUbicacion.getSelectedRow(), 0).toString());
					DataMetodos.modificarTablaUbicacion(id, textField_Pasillo.getText(), textField_Estante.getText(),
							textField_CodigoBiblioteca.getText());
					limpiarTextfields();
					recargarTablaUbicacion();
					btnEliminar.setEnabled(false);
					btnModificar_Superior.setEnabled(false);
				} else {
					String mensaje = "Todos los campos son necesarios. Por favor rellene todos los campos";
					JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnConfirmarCambios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnConfirmarCambios.setBounds(782, 459, 221, 39);
		add(btnConfirmarCambios);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Ubicacion> ubicaciones = DataMetodos.filtraPorCamposUbicacion(textField_Id.getText(),
						textField_Pasillo.getText(), textField_Estante.getText(), textField_CodigoBiblioteca.getText());
				limpiarTextfields();
				recargarTablaUbicacion(ubicaciones);
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar.setBounds(782, 508, 221, 39);
		add(btnBuscar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(modeloUbicacion.getValueAt(tableUbicacion.getSelectedRow(), 0).toString());
				DataMetodos.eliminarUbicacion(id);
				limpiarTextfields();
				recargarTablaUbicacion();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(385, 109, 141, 39);
		add(btnEliminar);

		tableUbicacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Solo se puede seleccionar una fila a la
		// vez

		tableUbicacion.getTableHeader().setReorderingAllowed(false);// Impedir la reordenación de columnas

		// Obtener el modelo de selección de la tabla
		ListSelectionModel selectionModel = tableUbicacion.getSelectionModel();

		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Verificar si la selección está cambiando y no está ajustando
				if (!e.getValueIsAdjusting()) {
					// Habilito estos botones
					btnModificar_Superior.setEnabled(true);
					btnEliminar.setEnabled(true);

					if (tableUbicacion.getSelectedRow() >= 0) {
						// volcar los campos de la fila en los textfields correspondientes
						String id = modeloUbicacion.getValueAt(tableUbicacion.getSelectedRow(), 0).toString();
						String pasillo = modeloUbicacion.getValueAt(tableUbicacion.getSelectedRow(), 1).toString();
						String estanteria = modeloUbicacion.getValueAt(tableUbicacion.getSelectedRow(), 2).toString();
						String id_biblioteca = modeloUbicacion.getValueAt(tableUbicacion.getSelectedRow(), 3)
								.toString();

						String provincia = modeloUbicacion.getValueAt(tableUbicacion.getSelectedRow(), 1).toString();

						textField_Id.setText(id);
						textField_Pasillo.setText(pasillo);
						textField_Estante.setText(estanteria);
						textField_CodigoBiblioteca.setText(id_biblioteca);

					}

				}
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				limpiarTextfields();
				disminuirTamanyo();
				btnConfirmarCambios.setVisible(false);
				btnGuardarUbicacion.setVisible(false);
				btnBuscar.setVisible(false);

				recargarTablaUbicacion();

				btnEliminar.setEnabled(false);
				btnModificar_Superior.setEnabled(false);

			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCancelar.setBounds(782, 572, 221, 39);
		add(btnCancelar);

		btnAnyadir = new JButton("Añadir");
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				aumentarTamanyo();
				lblCodigo.setVisible(false);
				textField_Id.setVisible(false);
				btnGuardarUbicacion.setVisible(true);
			}
		});
		btnAnyadir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnAnyadir.setBounds(72, 109, 141, 39);
		add(btnAnyadir);

		btnModificar_Superior = new JButton("Modificar");
		btnModificar_Superior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				aumentarTamanyo();
				textField_Id.setEnabled(true);
				textField_Id.setEnabled(false);
				btnConfirmarCambios.setVisible(true);

			}
		});
		btnModificar_Superior.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar_Superior.setBounds(223, 109, 141, 39);
		add(btnModificar_Superior);

		btnBuscar_Superior = new JButton("Buscar");
		btnBuscar_Superior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				btnBuscar.setVisible(true);
				textField_Id.setEnabled(true);
			}
		});
		btnBuscar_Superior.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar_Superior.setBounds(543, 109, 141, 39);
		add(btnBuscar_Superior);

		recargarTablaUbicacion();

		// ocultamos botones
		btnConfirmarCambios.setVisible(false);
		btnGuardarUbicacion.setVisible(false);
		btnBuscar.setVisible(false);

		// Deshabilitamos botones
		btnEliminar.setEnabled(false);
		btnModificar_Superior.setEnabled(false);
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
	    
		JLabel lblFondo = new JLabel(icon1);
		lblFondo.setBounds(0, 0, 1434, 825);
		add(lblFondo);
	}

	private void recargarTablaUbicacion() {

		// Cargo los elementos de la tabla en el modelo
		modeloUbicacion.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		for (Object[] filaDeUbicacion : DataMetodos.obtenerFilasTablaUbicacion()) {
			modeloUbicacion.addRow(filaDeUbicacion);
		}

	}

	private void limpiarTextfields() {

		textField_Id.setText("");
		textField_Pasillo.setText("");
		textField_Estante.setText("");
		textField_CodigoBiblioteca.setText("");

	}

	// Para buscar
	private void recargarTablaUbicacion(ArrayList<Ubicacion> ubicaciones) {

		modeloUbicacion.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA

		// cargo todas las filas correspondientes al array pasado por parametro
		for (Ubicacion ubicacion : ubicaciones) {
			modeloUbicacion.addRow(new Object[] {

					ubicacion.getUbicacion(), ubicacion.getPasillo(), ubicacion.getEstanteria(),
					ubicacion.getId_biblioteca() });
		}
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

	// Metodo controla que todos los campos de nuevo autor esten llenos.
	public boolean todosLosCamposEstanRellenosParaCrearNuevoAutor() {
		boolean resultado = !textField_Pasillo.getText().equals("") && !textField_Estante.getText().equals("")
				&& !textField_CodigoBiblioteca.getText().equals("");

		return resultado;
	}

}
