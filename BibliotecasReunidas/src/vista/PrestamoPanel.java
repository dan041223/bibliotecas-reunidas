package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import modelo.Biblioteca;
import modelo.Libro;
import modelo.Socio;
import modelo.Usuario;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Cursor;

public class PrestamoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	DefaultTableModel modeloPrestamo = new DefaultTableModel();
	private JTable TablaPrestamos;
	
	private JFrame frame;
	
	// Creamos los JButton
    JButton btnAnyadir;
    JButton btnModificar;
    JButton btnEliminar;
    private JTextField textFieldFechaEntrega;
    private JTextField textFieldFechaPrevista;
    private JTextField textFieldFechaPrestamo;
    private JTextField textFieldIdPrestamo;

	public PrestamoPanel() {
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
		textFieldIdPrestamo = new JTextField();
		textFieldIdPrestamo.setText("");
		textFieldIdPrestamo.setEnabled(false);
		textFieldIdPrestamo.setColumns(10);
		textFieldIdPrestamo.setBounds(1059, 195, 164, 25);
		add(textFieldIdPrestamo);
		
		JComboBox comboBoxSocio = new JComboBox();
		for(Socio socio: DataMetodos.obtenerCodigoSocio()) {
			comboBoxSocio.addItem(socio.getId());
		}
		comboBoxSocio.setBounds(1059, 251, 164, 22);
		add(comboBoxSocio);
		
		JComboBox comboBoxLibro = new JComboBox();
		for(Libro libro: DataMetodos.obtenerCodigoLibro()) {
			comboBoxLibro.addItem(libro.getId());
		}
		comboBoxLibro.setBounds(1059, 302, 164, 22);
		add(comboBoxLibro);
		
		JComboBox comboBoxUsuario = new JComboBox();
		for(Usuario usuario: DataMetodos.obtenerCodigoUsuario()) {
			comboBoxUsuario.addItem(usuario.getId());
		}
		comboBoxUsuario.setBounds(1059, 353, 164, 22);
		add(comboBoxUsuario);
		
		textFieldFechaEntrega = new JTextField();
		textFieldFechaEntrega.setText("");
		textFieldFechaEntrega.setBounds(1059, 507, 164, 25);
		add(textFieldFechaEntrega);
		textFieldFechaEntrega.setColumns(10);
		
		textFieldFechaPrevista = new JTextField();
		textFieldFechaPrevista.setText("");
		textFieldFechaPrevista.setEnabled(false);
		textFieldFechaPrevista.setColumns(10);
		textFieldFechaPrevista.setBounds(1059, 455, 164, 25);
		add(textFieldFechaPrevista);
		
		textFieldFechaPrestamo = new JTextField();
		textFieldFechaPrestamo.setText("");
		textFieldFechaPrestamo.setEnabled(false);
		textFieldFechaPrestamo.setColumns(10);
		textFieldFechaPrestamo.setBounds(1059, 405, 164, 25);
		add(textFieldFechaPrestamo);
		
		JLabel lblPrestamos = new JLabel("Prestamos");
		lblPrestamos.setForeground(new Color(255, 255, 255));
		lblPrestamos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblPrestamos.setBounds(31, 41, 159, 40);
		add(lblPrestamos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 148, 742, 485);
		add(scrollPane);
		
		TablaPrestamos = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(TablaPrestamos);
		
		modeloPrestamo.setColumnIdentifiers(
				new Object[] { "Código Prestamo", "Código Socio", "Codigo libro", "Código Usuario", "Fecha Prestamo", "Fecha Prevista", "Fecha Entrega" });
		
		TablaPrestamos.setModel(modeloPrestamo);
		
		JButton btnAnyadir = new JButton("Añadir");
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTamanyo();
				
				 int codigoSocio = (int) comboBoxSocio.getSelectedItem();
				 int codigoLibro = (int) comboBoxLibro.getSelectedItem();
				 int codigoUsuario = (int) comboBoxUsuario.getSelectedItem();

		        DataMetodos.insertarPrestamo(codigoSocio, codigoLibro, codigoUsuario);

				recargarTablaPrestamo();
				disminuirTamanyo();
			}
		});
		btnAnyadir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnAnyadir.setBounds(31, 92, 148, 40);
		add(btnAnyadir);
		
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(227, 92, 148, 40);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(421, 92, 148, 40);
		add(btnEliminar);
		
		JLabel lblCodigo_1 =new JLabel("Codigo Socio");
		lblCodigo_1.setForeground(new Color(255, 255, 255));
		lblCodigo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_1.setBounds(918, 248, 95, 25);
		add(lblCodigo_1);
		
		JLabel lblCodigo_2 = new JLabel("Codigo Libro");
		lblCodigo_2.setForeground(new Color(255, 255, 255));
		lblCodigo_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_2.setBounds(918, 299, 95, 25);
		add(lblCodigo_2);
		
		JLabel lblCodigo_3 = new JLabel("Codigo Usuario");
		lblCodigo_3.setForeground(new Color(255, 255, 255));
		lblCodigo_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_3.setBounds(918, 350, 114, 25);
		add(lblCodigo_3);
		
		JLabel lblFechaEntrega = new JLabel("Fecha Entrega");
		lblFechaEntrega.setForeground(new Color(255, 255, 255));
		lblFechaEntrega.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaEntrega.setBounds(918, 505, 114, 25);
		add(lblFechaEntrega);
		
		JLabel lblFechaPrevista = new JLabel("Fecha Prevista");
		lblFechaPrevista.setForeground(new Color(255, 255, 255));
		lblFechaPrevista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaPrevista.setBounds(918, 455, 114, 25);
		add(lblFechaPrevista);
		
		JLabel lblFechaPrestamo = new JLabel("Fecha Prestamo");
		lblFechaPrestamo.setForeground(new Color(255, 255, 255));
		lblFechaPrestamo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaPrestamo.setBounds(918, 403, 114, 25);
		add(lblFechaPrestamo);
		
		JLabel lblCodigo_1_1 = new JLabel("Codigo Prestamo");
		lblCodigo_1_1.setForeground(new Color(255, 255, 255));
		lblCodigo_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo_1_1.setBounds(918, 193, 131, 25);
		add(lblCodigo_1_1);
		
		 // Obtener el modelo de selección de la tabla
 		ListSelectionModel selectionModel = TablaPrestamos.getSelectionModel();
 		// Agregar un ListSelectionListener al modelo de selección
 		selectionModel.addListSelectionListener(new ListSelectionListener() {
 			@Override
 			 public void valueChanged(ListSelectionEvent e) {
 		        if (!e.getValueIsAdjusting()) {
 		            if (TablaPrestamos.getSelectedRow() >= 0) {
 		                String id = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 0).toString();
 		                String id_socio = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 1).toString();
 		                String id_libro = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 2).toString();
 		                String id_usuario = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 3).toString();
 		                String fecha_prestamo = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 4).toString();
 		                String fecha_prevista = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 5).toString();
 		                String fecha_entrega = "No entregado"; // Valor predeterminado si está vacío

 		                // Verifica si la columna 6 no está vacía antes de intentar obtener su valor
 		                Object valorColumna6 = modeloPrestamo.getValueAt(TablaPrestamos.getSelectedRow(), 6);
 		                if (valorColumna6 != null) {
 		                    fecha_entrega = valorColumna6.toString();
 		                }

 		                textFieldIdPrestamo.setText(id);
 		                comboBoxSocio.setSelectedItem(id_socio);
 		                comboBoxLibro.setSelectedItem(id_libro);
 		                comboBoxUsuario.setSelectedItem(id_usuario);
 		                textFieldFechaPrestamo.setText(fecha_prestamo);
 		                textFieldFechaPrevista.setText(fecha_prevista);
 		                textFieldFechaEntrega.setText(fecha_entrega);

 		                // Habilitar y deshabilitar campos según sea necesario
 		                btnEliminar.setEnabled(true);
 		                btnModificar.setEnabled(true);
 		                btnAnyadir.setEnabled(false);
 		            }
 		        }
 		    }
 		});
		
		recargarTablaPrestamo();
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		textFieldIdPrestamo.setEnabled(false);	
		textFieldFechaPrestamo.setEnabled(false);	
		textFieldFechaPrevista.setEnabled(false);	
		textFieldFechaEntrega.setEnabled(false);	
	
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
	
	// ====================== metodos para esta tablas==============

		private void limpiarTextFields() {
			
		}

		private void recargarTablaPrestamo() {

			// Cargo los elementos de la tabla en el modelo
			modeloPrestamo.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
			for (Object[] filaDePrestamo : DataMetodos.obtenerFilasTablaPrestamos()) {
				modeloPrestamo.addRow(filaDePrestamo);
			}

		}
		
		private void disminuirTamanyo() {
	        if (frame != null) {
	            int nuevoAncho = 810;
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
}
