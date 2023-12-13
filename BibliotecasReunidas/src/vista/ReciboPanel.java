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
import java.awt.Color;
import java.awt.Cursor;

public class ReciboPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable TablaRecibos;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;


	/**
	 * Create the panel.
	 */

	public ReciboPanel() {
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
		
		TablaRecibos = new JTable();
		scrollPane.setViewportView(TablaRecibos);
		
		JLabel LabelTitulo = new JLabel("Recibos");
		LabelTitulo.setForeground(new Color(255, 255, 255));
		LabelTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		LabelTitulo.setBounds(40, 34, 101, 36);
		add(LabelTitulo);
		
		JButton btnAnyadir = new JButton("Añadir");
		btnAnyadir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnAnyadir.setBounds(40, 81, 148, 40);
		add(btnAnyadir);
		
		JButton btnModificar_Superior = new JButton("Modificar");
		btnModificar_Superior.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnModificar_Superior.setEnabled(false);
		btnModificar_Superior.setBounds(216, 81, 148, 40);
		add(btnModificar_Superior);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(394, 81, 148, 40);
		add(btnEliminar);
		
		JButton btnBuscar_Superior = new JButton("Buscar");
		btnBuscar_Superior.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnBuscar_Superior.setBounds(641, 82, 148, 40);
		add(btnBuscar_Superior);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(975, 141, 194, 25);
		add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(975, 188, 194, 25);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(975, 238, 194, 25);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(975, 291, 194, 25);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(975, 342, 194, 25);
		add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(975, 388, 194, 25);
		add(textField_5);
		
		JLabel lblCodigoSocio = new JLabel("Codigo Socio");
		lblCodigoSocio.setForeground(new Color(255, 255, 255));
		lblCodigoSocio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoSocio.setBounds(820, 134, 95, 25);
		add(lblCodigoSocio);
		
		JLabel lblCodigoRecibo = new JLabel("Codigo Recibo");
		lblCodigoRecibo.setForeground(new Color(255, 255, 255));
		lblCodigoRecibo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoRecibo.setBounds(820, 193, 95, 25);
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
