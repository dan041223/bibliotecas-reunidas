package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;
import modelo.Libro;
import modelo.Recibo;
import modelo.Socio;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

public class ReciboPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTable TablaRecibos;
	DefaultTableModel modeloRecibo = new DefaultTableModel();
	private JTextField textFieldMonto;
	private JTextField textFieldFechaRecibo;


	// Creamos los JButton
	JButton btnModificar;
    JButton btnEliminar;
    private JComboBox comboBoxRecibo;
    private JComboBox comboBoxSocio;
    private JComboBox comboBoxLibro;
    private JComboBox comboBoxPago;
    
	public ReciboPanel() {
		this.frame = frame;
		setBackground(new Color(128, 128, 192));
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
				Ventana_Principal.getInstance().cambiarPanel(new MenuPanel(null));
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
				Ventana_Principal.getInstance().cambiarPanel(new MenuPanel(null));
			}
		});
		add(lblCerrarSesion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 133, 749, 478);
		add(scrollPane);
		
		TablaRecibos = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(TablaRecibos);
		
		modeloRecibo.setColumnIdentifiers(
				new Object[] {"Id Recibo", "Id Socios", "Id Libros", "Monto", "Fecha Recibos", "Tipo de Pago"});
		
		TablaRecibos.setModel(modeloRecibo);
		
		JLabel LabelTitulo = new JLabel("Recibos");
		LabelTitulo.setForeground(new Color(255, 255, 255));
		LabelTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		LabelTitulo.setBounds(40, 34, 101, 36);
		add(LabelTitulo);
		
 	   comboBoxRecibo = new JComboBox();
 	   for(Recibo recibo: DataMetodos.obtenerCodigoRecibo()) {
			comboBoxRecibo.addItem(recibo.getId());
		}
 	    comboBoxRecibo.setBounds(975, 142, 194, 22);
 	    add(comboBoxRecibo);
 	    
 	   comboBoxSocio = new JComboBox();
 	   for(Socio socio: DataMetodos.obtenerCodigoSocio()) {
			comboBoxSocio.addItem(socio.getId());
		}
 	    comboBoxSocio.setBounds(975, 189, 194, 22);
 	    add(comboBoxSocio);
 	    
 	   comboBoxLibro = new JComboBox();
 	   for(Libro libro: DataMetodos.obtenerCodigoLibro()) {
			comboBoxLibro.addItem(libro.getId());
		}
 	    comboBoxLibro.setBounds(975, 246, 194, 22);
 	    add(comboBoxLibro);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setColumns(10);
		textFieldMonto.setBounds(975, 291, 194, 25);
		add(textFieldMonto);
		
		textFieldFechaRecibo = new JTextField();
		textFieldFechaRecibo.setColumns(10);
		textFieldFechaRecibo.setBounds(975, 342, 194, 25);
		add(textFieldFechaRecibo);
		
		String[] Pagos = { "efectivo", "tarjeta" };
		comboBoxPago = new JComboBox(Pagos);
		comboBoxPago.setBounds(975, 396, 194, 22);
		add(comboBoxPago);
		
		JLabel lblCodigoSocio = new JLabel("Codigo Socio");
		lblCodigoSocio.setForeground(new Color(255, 255, 255));
		lblCodigoSocio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoSocio.setBounds(820, 186, 95, 25);
		add(lblCodigoSocio);
		
		JLabel lblCodigoRecibo = new JLabel("Codigo Recibo");
		lblCodigoRecibo.setForeground(new Color(255, 255, 255));
		lblCodigoRecibo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoRecibo.setBounds(820, 139, 95, 25);
		add(lblCodigoRecibo);
		
		JLabel lblCodigoLibro = new JLabel("Codigo Libro");
		lblCodigoLibro.setForeground(new Color(255, 255, 255));
		lblCodigoLibro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoLibro.setBounds(820, 243, 95, 25);
		add(lblCodigoLibro);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setForeground(new Color(255, 255, 255));
		lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMonto.setBounds(830, 289, 95, 25);
		add(lblMonto);
		
		JLabel lblFechaRecibo = new JLabel("Fecha Recibo");
		lblFechaRecibo.setForeground(new Color(255, 255, 255));
		lblFechaRecibo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaRecibo.setBounds(820, 342, 95, 25);
		add(lblFechaRecibo);
		
		JLabel lblTipoPago = new JLabel("Tipo Pago");
		lblTipoPago.setForeground(new Color(255, 255, 255));
		lblTipoPago.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoPago.setBounds(820, 393, 95, 25);
		add(lblTipoPago);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 aumentarTamanyo();
				 
				 int id_recibo = (int) comboBoxSocio.getSelectedItem();
				 int id_socio = (int) comboBoxSocio.getSelectedItem();
				 int id_libro = (int) comboBoxLibro.getSelectedItem();
				 String tipo_pago = (String) comboBoxPago.getSelectedItem();
				 
				 DataMetodos.modificarRecibo(id_recibo, id_socio, id_libro, tipo_pago);
				 
				 recargarTablaRecibo();
	             disminuirTamanyo();
	             btnEliminar.setEnabled(false);
	      	     btnModificar.setEnabled(false);    
	      	   	 comboBoxSocio.setEnabled(false);   
	      	  	 comboBoxLibro.setEnabled(false);        
	      	  	 comboBoxPago.setEnabled(false); 
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar.setBounds(40, 81, 148, 40);
		add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 aumentarTamanyo();
				 
				 int id_recibo = (int) comboBoxSocio.getSelectedItem();
				 
				 DataMetodos.eliminarRecibo(id_recibo);
				 
				 recargarTablaRecibo();
	             disminuirTamanyo();
	             btnEliminar.setEnabled(false);
	      	     btnModificar.setEnabled(false);    
	      	   	 comboBoxSocio.setEnabled(false);   
	      	  	 comboBoxLibro.setEnabled(false);        
	      	  	 comboBoxPago.setEnabled(false); 
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setBounds(249, 82, 148, 40);
		add(btnEliminar);
		
		// Obtener el modelo de selección de la tabla
 		ListSelectionModel selectionModel = TablaRecibos.getSelectionModel();
 		// Agregar un ListSelectionListener al modelo de selección
 		selectionModel.addListSelectionListener(new ListSelectionListener() {
 			@Override
 			 public void valueChanged(ListSelectionEvent e) {
 		        if (!e.getValueIsAdjusting()) {
 		            if (TablaRecibos.getSelectedRow() >= 0) {
 		                String id_recibo = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 0).toString();
 		                String id_socio = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 1).toString();
 		                String id_libro = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 2).toString();
 		                String monto = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 3).toString();
 		                String fecha_recibo = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 4).toString();
 		                String tipo_pago = modeloRecibo.getValueAt(TablaRecibos.getSelectedRow(), 5).toString();

 		               comboBoxRecibo.setSelectedItem(id_recibo);
 		               comboBoxSocio.setSelectedItem(id_socio);
 		               comboBoxLibro.setSelectedItem(id_libro);
 		                textFieldMonto.setText(monto);
 		                textFieldFechaRecibo.setText(fecha_recibo);
 		                comboBoxPago.setSelectedItem(tipo_pago);

 		                // Habilitar y deshabilitar campos según sea necesario
 		                btnEliminar.setEnabled(true);
 		                btnModificar.setEnabled(true);   
 		                comboBoxSocio.setEnabled(true);   
 		       	  		comboBoxLibro.setEnabled(true); 
 		       	  		comboBoxPago.setEnabled(true); 
 		            }
 		        }
 		    }
 		});
 		
 		recargarTablaRecibo();
 		btnEliminar.setEnabled(false);
 	    btnModificar.setEnabled(false);   
 	    comboBoxRecibo.setEnabled(false);   
 	   	comboBoxSocio.setEnabled(false);   
 	  	comboBoxLibro.setEnabled(false);   
 	  	textFieldMonto.setEnabled(false);   
 	  	textFieldFechaRecibo.setEnabled(false);   
 	  	comboBoxPago.setEnabled(false);   
	}
	
	private void recargarTablaRecibo() {
		modeloRecibo.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
		for (Object[] filaDeRecibo : DataMetodos.obtenerFilasTablaRecibo()) {
			modeloRecibo.addRow(filaDeRecibo);
		}

	}
	
	private void disminuirTamanyo() {
        if (frame != null) {
            int nuevoAncho = 810;
            int nuevoAlto = frame.getHeight();
            frame.setSize(nuevoAncho, nuevoAlto);
            System.out.println("Disminuido");
        }
    }
	
	private void aumentarTamanyo() {
		if (frame != null) {
			int nuevoAncho = 1250;
			int nuevoAlto = frame.getHeight();
			frame.setSize(nuevoAncho, nuevoAlto);
		}
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
}
