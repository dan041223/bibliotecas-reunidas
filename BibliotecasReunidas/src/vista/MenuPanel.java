package vista;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	/**
	 * Create the panel.
	 */
	public MenuPanel(JFrame frame) {
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
				Ventana_Principal.getInstance().cambiarPanel(new Login(frame));
			}
		});
		imgs.setBounds(10, 11, 47, 40);
		
		add(imgs);
		
		JLabel lblCerrarSesion = new JLabel("Cerrar sesión");
		lblCerrarSesion.setForeground(new Color(255, 255, 255));
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
				Ventana_Principal.getInstance().cambiarPanel(new Login(frame));
			}
		});
		add(lblCerrarSesion);
		
		BufferedImage img2 = null;
		try {
			img2 = ImageIO.read(new File("res\\imagenes\\button_autores.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth2 = 178;
	    int labelHeight2 = 74;
	    
	    Image scaledImg2 = img2.getScaledInstance(labelWidth2, labelHeight2, Image.SCALE_SMOOTH);
		
	    ImageIcon icon2 = new ImageIcon(scaledImg2);
		
		JLabel lblNewLabel_1 = new JLabel(icon2);
		lblNewLabel_1.setText("a");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new AutorPanel(frame));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		lblNewLabel_1.setBounds(141, 100, 218, 74);
		add(lblNewLabel_1);
		
		BufferedImage img3 = null;
		try {
			img3 = ImageIO.read(new File("res\\imagenes\\button_libros.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth3 = 178;
	    int labelHeight3 = 74;
	    
	    Image scaledImg3 = img3.getScaledInstance(labelWidth3, labelHeight3, Image.SCALE_SMOOTH);
		
	    ImageIcon icon3 = new ImageIcon(scaledImg3);
		
		JLabel lblNewLabel_1_1 = new JLabel(icon3);
		lblNewLabel_1_1.setText("a");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new LibroPanel(frame));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		lblNewLabel_1_1.setBounds(141, 258, 218, 74);
		add(lblNewLabel_1_1);
		
		BufferedImage img4 = null;
		try {
			img4 = ImageIO.read(new File("res\\imagenes\\button_prestamo.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth4 = 178;
	    int labelHeight4 = 74;
	    
	    Image scaledImg4 = img4.getScaledInstance(labelWidth4, labelHeight4, Image.SCALE_SMOOTH);
		
	    ImageIcon icon4 = new ImageIcon(scaledImg4);
		
		JLabel lblNewLabel_1_2 = new JLabel(icon4);
		lblNewLabel_1_2.setText("a");
		lblNewLabel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new PrestamoPanel());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		lblNewLabel_1_2.setBounds(141, 465, 218, 74);
		add(lblNewLabel_1_2);
		
		BufferedImage img5 = null;
		try {
			img5 = ImageIO.read(new File("res\\imagenes\\button_usuarios.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth5 = 178;
	    int labelHeight5 = 74;
	    
	    Image scaledImg5 = img5.getScaledInstance(labelWidth5, labelHeight5, Image.SCALE_SMOOTH);
		
	    ImageIcon icon5 = new ImageIcon(scaledImg5);
		
		JLabel lblNewLabel_1_3 = new JLabel(icon5);
		lblNewLabel_1_3.setText("a");
		lblNewLabel_1_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new UsuarioPanel());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		lblNewLabel_1_3.setBounds(141, 596, 218, 74);
		add(lblNewLabel_1_3);
		
		BufferedImage img6 = null;
		try {
			img6 = ImageIO.read(new File("res\\imagenes\\button_socios.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth6 = 178;
	    int labelHeight6 = 74;
	    
	    Image scaledImg6 = img6.getScaledInstance(labelWidth6, labelHeight6, Image.SCALE_SMOOTH);
		
	    ImageIcon icon6 = new ImageIcon(scaledImg6);
		
		JLabel lblNewLabel_1_4 = new JLabel(icon6);
		lblNewLabel_1_4.setText("a");
		lblNewLabel_1_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new SocioPanel(frame));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		lblNewLabel_1_4.setBounds(1048, 100, 218, 74);
		add(lblNewLabel_1_4);
		
		BufferedImage img7 = null;
		try {
			img7 = ImageIO.read(new File("res\\imagenes\\button_bibliotecas.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth7 = 178;
	    int labelHeight7 = 74;
	    
	    Image scaledImg7 = img7.getScaledInstance(labelWidth7, labelHeight7, Image.SCALE_SMOOTH);
		
	    ImageIcon icon7 = new ImageIcon(scaledImg7);
		
		JLabel lblNewLabel_1_5 = new JLabel(icon7);
		lblNewLabel_1_5.setText("a");
		lblNewLabel_1_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new BibliotecaPanel(frame));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		lblNewLabel_1_5.setBounds(1048, 258, 218, 74);
		add(lblNewLabel_1_5);
		
		BufferedImage img8 = null;
		try {
			img8 = ImageIO.read(new File("res\\imagenes\\button_incidencias.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth8 = 178;
	    int labelHeight8 = 74;
	    
	    Image scaledImg8 = img8.getScaledInstance(labelWidth8, labelHeight8, Image.SCALE_SMOOTH);
		
	    ImageIcon icon8 = new ImageIcon(scaledImg8);
		
		JLabel lblNewLabel_1_6 = new JLabel(icon8);
		lblNewLabel_1_6.setText("a");
		lblNewLabel_1_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new TodasIncidenciasPanel(frame));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		lblNewLabel_1_6.setBounds(1048, 465, 218, 74);
		add(lblNewLabel_1_6);
		
		BufferedImage img9 = null;
		try {
			img9 = ImageIO.read(new File("res\\imagenes\\button_ubicaciones.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth9 = 178;
	    int labelHeight9 = 74;
	    
	    Image scaledImg9 = img9.getScaledInstance(labelWidth9, labelHeight9, Image.SCALE_SMOOTH);
		
	    ImageIcon icon9 = new ImageIcon(scaledImg9);
		
		JLabel lblNewLabel_1_7 = new JLabel(icon9);
		lblNewLabel_1_7.setText("a");
		lblNewLabel_1_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new UbicacionPanel(frame));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		lblNewLabel_1_7.setBounds(1048, 596, 218, 74);
		add(lblNewLabel_1_7);
		
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
