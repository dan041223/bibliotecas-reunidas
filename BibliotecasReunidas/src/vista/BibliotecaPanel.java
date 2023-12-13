package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import modelo.Autor;
import modelo.Biblioteca;

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

public class BibliotecaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	DefaultTableModel modeloBiblioteca = new DefaultTableModel();
	private JTable tableBiblioteca;
	private JTextField textField_id;
	private JTextField textField_provincia;
	private JTextField textField_telefono;
	private JTextField textField_cominudad;
	private JTextField textField_calle;
	private JTextField textField_codigoPostal;
	private JFrame frame;

	JButton btnBuscar;
	JButton btnConfirmarCambios;
	JButton btnEliminar;
	JButton btnGuardarBiblioteca;
	JButton btnCancelar;
	JButton btnAnyadir;
	JButton btnModificar_Superior;
	JButton btnBuscar_Superior;

	/**
	 * Create the panel.
	 */
	public BibliotecaPanel(JFrame frame) {
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

		JLabel lblNewLabel = new JLabel("Biblioteca");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(37, 64, 159, 40);
		add(lblNewLabel);

		// -------------------------Creacion de tabla---------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 165, 742, 485);
		add(scrollPane);

		tableBiblioteca = new JTable() {
			private static final long serialVersionUID = 1L;

			// Vamos actualizar la IsCellEditerTable para impedir que las celdas de la tabla
			// sean modificadas.
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(tableBiblioteca);

		modeloBiblioteca.setColumnIdentifiers(
				new Object[] { "Código ID", "Provincia", "Codigo Postal", "Telefono", "Comunidad Autonama", "Calle" });

		tableBiblioteca.setModel(modeloBiblioteca);

		JLabel lblCodigo = new JLabel("Codigo ID");
		lblCodigo.setForeground(new Color(255, 255, 255));
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(942, 115, 95, 25);
		add(lblCodigo);

		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setForeground(new Color(255, 255, 255));
		lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProvincia.setBounds(942, 166, 95, 25);
		add(lblProvincia);

		JLabel lblCodigoPostal = new JLabel("Codigo Postal");
		lblCodigoPostal.setForeground(new Color(255, 255, 255));
		lblCodigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoPostal.setBounds(942, 219, 95, 25);
		add(lblCodigoPostal);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setForeground(new Color(255, 255, 255));
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(942, 271, 95, 25);
		add(lblTelefono);

		JLabel lblComunidadAutonama = new JLabel("Comunidad Autonama");
		lblComunidadAutonama.setForeground(new Color(255, 255, 255));
		lblComunidadAutonama.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComunidadAutonama.setBounds(942, 324, 159, 25);
		add(lblComunidadAutonama);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setForeground(new Color(255, 255, 255));
		lblCalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCalle.setBounds(942, 375, 159, 25);
		add(lblCalle);

		textField_id = new JTextField();
		textField_id.setBounds(1097, 117, 194, 25);
		add(textField_id);
		textField_id.setColumns(10);

		textField_provincia = new JTextField();
		textField_provincia.setColumns(10);
		textField_provincia.setBounds(1097, 168, 194, 25);
		add(textField_provincia);

		textField_codigoPostal = new JTextField();
		textField_codigoPostal.setColumns(10);
		textField_codigoPostal.setBounds(1097, 221, 194, 25);
		add(textField_codigoPostal);

		textField_telefono = new JTextField();
		textField_telefono.setColumns(10);
		textField_telefono.setBounds(1097, 273, 194, 25);
		add(textField_telefono);

		textField_cominudad = new JTextField();
		textField_cominudad.setColumns(10);
		textField_cominudad.setBounds(1097, 326, 194, 25);
		add(textField_cominudad);

		textField_calle = new JTextField();
		textField_calle.setColumns(10);
		textField_calle.setBounds(1097, 377, 194, 25);
		add(textField_calle);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Biblioteca> bibliotecas = DataMetodos.filtraPorCamposBiblioteca(textField_id.getText(),
						textField_provincia.getText(), textField_codigoPostal.getText(), textField_telefono.getText(),
						textField_cominudad.getText(), textField_calle.getText());
				limpiarTextFields();
				recargarTablaBiblioteca(bibliotecas);
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar.setBounds(940, 463, 245, 40);
		add(btnBuscar);

		btnGuardarBiblioteca = new JButton("Guardar nueva biblioteca");
		btnGuardarBiblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (todosLosCamposEstanRellenosParaCrearNuevoAutor()) {
					DataMetodos.insertarbiblioteca(textField_provincia.getText(), textField_codigoPostal.getText(),
							textField_telefono.getText(), textField_cominudad.getText(), textField_calle.getText());
					limpiarTextFields();
					recargarTablaBiblioteca();

				} else {
					String mensaje = "Todos los campos son necesarios. Por favor rellene todos los campos";
					JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnGuardarBiblioteca.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnGuardarBiblioteca.setBounds(940, 413, 245, 40);
		add(btnGuardarBiblioteca);

		btnConfirmarCambios = new JButton("Confirmar cambio");
		btnConfirmarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (todosLosCamposEstanRellenosParaCrearNuevoAutor()) {
					int id = Integer
							.parseInt(modeloBiblioteca.getValueAt(tableBiblioteca.getSelectedRow(), 0).toString());
					DataMetodos.modificarTablaBiblioteca(id, textField_provincia.getText(),
							textField_codigoPostal.getText(), textField_telefono.getText(),
							textField_cominudad.getText(), textField_calle.getText());
					limpiarTextFields();
					recargarTablaBiblioteca();
					btnModificar_Superior.setEnabled(false);
					btnEliminar.setEnabled(false);

				} else {
					String mensaje = "Todos los campos son necesarios. Por favor rellene todos los campos";
					JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnConfirmarCambios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnConfirmarCambios.setBounds(940, 513, 245, 40);
		add(btnConfirmarCambios);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(modeloBiblioteca.getValueAt(tableBiblioteca.getSelectedRow(), 0).toString());
				DataMetodos.eliminarBiblioteca(id);
				limpiarTextFields();
				recargarTablaBiblioteca();

			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(399, 115, 148, 40);
		add(btnEliminar);

		tableBiblioteca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Solo se puede seleccionar una fila a la
		// vez

		tableBiblioteca.getTableHeader().setReorderingAllowed(false);// Impedir la reordenación de columnas

		// Obtener el modelo de selección de la tabla
		ListSelectionModel selectionModel = tableBiblioteca.getSelectionModel();

		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Verificar si la selección está cambiando y no está ajustando
				if (!e.getValueIsAdjusting()) {
					// Habilito estos botones
					btnModificar_Superior.setEnabled(true);
					btnEliminar.setEnabled(true);

					if (tableBiblioteca.getSelectedRow() >= 0) {
						// volcar los campos de la fila en los textfields correspondientes
						String id = modeloBiblioteca.getValueAt(tableBiblioteca.getSelectedRow(), 0).toString();
						String provincia = modeloBiblioteca.getValueAt(tableBiblioteca.getSelectedRow(), 1).toString();
						String codigo_postal = modeloBiblioteca.getValueAt(tableBiblioteca.getSelectedRow(), 2)
								.toString();
						String telefono = modeloBiblioteca.getValueAt(tableBiblioteca.getSelectedRowCount(), 3)
								.toString();
						String comunidad_autonoma = modeloBiblioteca.getValueAt(tableBiblioteca.getSelectedRow(), 4)
								.toString();
						String calle = modeloBiblioteca.getValueAt(tableBiblioteca.getSelectedRow(), 5).toString();

						textField_id.setText(id);
						textField_provincia.setText(provincia);
						textField_codigoPostal.setText(codigo_postal);
						textField_telefono.setText(telefono);
						textField_cominudad.setText(comunidad_autonoma);
						textField_calle.setText(calle);

					}

				}
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				limpiarTextFields();
				disminuirTamanyo();
				btnConfirmarCambios.setVisible(false);
				btnBuscar.setVisible(false);
				btnGuardarBiblioteca.setVisible(false);
				recargarTablaBiblioteca();
				btnEliminar.setEnabled(false);
				btnModificar_Superior.setEnabled(false);

			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCancelar.setBounds(940, 574, 245, 39);
		add(btnCancelar);

		btnAnyadir = new JButton("Añadir");
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				lblCodigo.setVisible(false);
				textField_id.setVisible(false);
				btnGuardarBiblioteca.setVisible(true);
			}
		});
		btnAnyadir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnAnyadir.setBounds(38, 115, 148, 40);
		add(btnAnyadir);

		btnModificar_Superior = new JButton("Modificar");
		btnModificar_Superior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				textField_id.setEnabled(true);
				textField_id.setEnabled(false);
				btnConfirmarCambios.setVisible(true);
			}
		});
		btnModificar_Superior.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar_Superior.setBounds(209, 115, 148, 40);
		add(btnModificar_Superior);

		btnBuscar_Superior = new JButton("Buscar");
		btnBuscar_Superior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				btnBuscar.setVisible(true);
				textField_id.setEnabled(true);
			}
		});
		btnBuscar_Superior.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar_Superior.setBounds(628, 115, 148, 40);
		add(btnBuscar_Superior);

		recargarTablaBiblioteca();

		// ocultar botones laterales
		btnConfirmarCambios.setVisible(false);
		btnBuscar.setVisible(false);
		btnGuardarBiblioteca.setVisible(false);

		// deshabilitar
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

	// ====================== metodos para esta tablas==============

	private void limpiarTextFields() {
		textField_id.setText("");
		textField_provincia.setText("");
		textField_codigoPostal.setText("");
		textField_telefono.setText("");
		textField_cominudad.setText("");
		textField_calle.setText("");
	}

	private void recargarTablaBiblioteca() {

		// Cargo los elementos de la tabla en el modelo
		modeloBiblioteca.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		for (Object[] filaDebiblioteca : DataMetodos.obtenerFilasTablaBiblioteca()) {
			modeloBiblioteca.addRow(filaDebiblioteca);
		}

	}

	// Para buscar
	private void recargarTablaBiblioteca(ArrayList<Biblioteca> bibliotecas) {

		modeloBiblioteca.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA

		// cargo todas las filas correspondientes al array pasado por parametro
		for (Biblioteca biblioteca : bibliotecas) {
			modeloBiblioteca
					.addRow(new Object[] { biblioteca.getId(), biblioteca.getProvincia(), biblioteca.getCodigo_postal(),
							biblioteca.getTelefono(), biblioteca.getComunidad_autonoma(), biblioteca.getCalle() });

		}

	}

	private void disminuirTamanyo() {
		if (frame != null) {
			int nuevoAncho = 850;
			int nuevoAlto = frame.getHeight();
			frame.setSize(nuevoAncho, nuevoAlto);

		}
	}

	private void aumentarTamanyo() {
		if (frame != null) {
			int nuevoAncho = 1250;
			int nuevoAlto = frame.getHeight();
			frame.setSize(nuevoAncho, nuevoAlto);

		}
	}

	// Metodo controla que todos los campos de nuevo autor esten llenos.
	public boolean todosLosCamposEstanRellenosParaCrearNuevoAutor() {
		boolean resultado = !textField_provincia.getText().equals("") && !textField_codigoPostal.getText().equals("")
				&& !textField_telefono.getText().equals("") && !textField_cominudad.getText().equals("")
				&& !textField_calle.getText().equalsIgnoreCase("");

		return resultado;
	}

}
