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
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	/**
	 * Create the panel.
	 */
	public MenuPanel(JFrame frame) {
		this.frame = frame;
		setLayout(null);
		
		JButton btnAutores = new JButton("Autores");
		btnAutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new AutorPanel(frame));
			}
		});
		btnAutores.setBounds(573, 239, 89, 23);
		add(btnAutores);
		
		JButton btnLibros = new JButton("Libros");
		btnLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new LibroPanel(frame));
			}
		});
		btnLibros.setBounds(573, 309, 89, 23);
		add(btnLibros);
		
		JButton btnPrestamos = new JButton("Prestamos");
		btnPrestamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new PrestamoPanel());
			}
		});
		btnPrestamos.setBounds(573, 371, 89, 23);
		add(btnPrestamos);
		
		JButton btnSocios = new JButton("Socios");
		btnSocios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new SocioPanel(frame));
			}
		});
		btnSocios.setBounds(812, 239, 89, 23);
		add(btnSocios);
		
		JButton btnBiblio = new JButton("Bibliotecas");
		btnBiblio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new BibliotecaPanel(frame));
			}
		});
		btnBiblio.setBounds(812, 309, 89, 23);
		add(btnBiblio);
		
		JButton btnIncidencias = new JButton("Incidencias");
		btnIncidencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new TodasIncidenciasPanel(frame));
			}
		});
		btnIncidencias.setBounds(812, 439, 89, 23);
		add(btnIncidencias);
		
		JButton btnUsuario = new JButton("Usuarios");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new UsuarioPanel());
			}
		});
		btnUsuario.setBounds(573, 439, 89, 23);
		add(btnUsuario);
    
		JButton btnUbicacion = new JButton("Ubicacion");
		btnUbicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new UbicacionPanel(frame));
			}
		});
		btnUbicacion.setBounds(812, 383, 89, 23);
		add(btnUbicacion);
		
		BufferedImage img1 = null;
		try {
			img1 = ImageIO.read(new File("imagenes\\fondoEstanteria.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth1 = 1424;
	    int labelHeight1 = 825;
	    
	    Image scaledImg1 = img1.getScaledInstance(labelWidth1, labelHeight1, Image.SCALE_SMOOTH);
		
	    ImageIcon icon = new ImageIcon(scaledImg1);
	    
		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setBounds(10, 0, 1424, 825);
		add(lblNewLabel);
		
		BufferedImage img2 = null;
		try {
			img2 = ImageIO.read(new File("imagenes\\button_autores.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth2 = 1424;
	    int labelHeight2 = 825;
	    
	    Image scaledImg2 = img2.getScaledInstance(labelWidth2, labelHeight2, Image.SCALE_SMOOTH);
		
	    ImageIcon icon2 = new ImageIcon(scaledImg2);
		
		JLabel lblNewLabel_1 = new JLabel("Autores");
		lblNewLabel_1.setBounds(356, 100, 46, 14);
		add(lblNewLabel_1);
		
		BufferedImage img3 = null;
		try {
			img3 = ImageIO.read(new File("imagenes\\button_libros.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth3 = 1424;
	    int labelHeight3 = 825;
	    
	    Image scaledImg3 = img3.getScaledInstance(labelWidth3, labelHeight3, Image.SCALE_SMOOTH);
		
	    ImageIcon icon3 = new ImageIcon(scaledImg3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Libros");
		lblNewLabel_1_1.setBounds(356, 257, 46, 14);
		add(lblNewLabel_1_1);
		
		BufferedImage img4 = null;
		try {
			img4 = ImageIO.read(new File("imagenes\\button_prestamos.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth4 = 1424;
	    int labelHeight4 = 825;
	    
	    Image scaledImg4 = img4.getScaledInstance(labelWidth4, labelHeight4, Image.SCALE_SMOOTH);
		
	    ImageIcon icon4 = new ImageIcon(scaledImg4);
		
		JLabel lblNewLabel_1_2 = new JLabel("Prestamos");
		lblNewLabel_1_2.setBounds(356, 443, 46, 14);
		add(lblNewLabel_1_2);
		
		BufferedImage img5 = null;
		try {
			img5 = ImageIO.read(new File("imagenes\\button_usuarios.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth5 = 1424;
	    int labelHeight5 = 825;
	    
	    Image scaledImg5 = img5.getScaledInstance(labelWidth5, labelHeight5, Image.SCALE_SMOOTH);
		
	    ImageIcon icon5 = new ImageIcon(scaledImg5);
		
		JLabel lblNewLabel_1_3 = new JLabel("Usuarios");
		lblNewLabel_1_3.setBounds(356, 618, 46, 14);
		add(lblNewLabel_1_3);
		
		BufferedImage img6 = null;
		try {
			img6 = ImageIO.read(new File("imagenes\\button_socios.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth6 = 1424;
	    int labelHeight6 = 825;
	    
	    Image scaledImg6 = img6.getScaledInstance(labelWidth6, labelHeight6, Image.SCALE_SMOOTH);
		
	    ImageIcon icon6 = new ImageIcon(scaledImg6);
		
		JLabel lblNewLabel_1_4 = new JLabel("Socios");
		lblNewLabel_1_4.setBounds(1100, 100, 46, 14);
		add(lblNewLabel_1_4);
		
		BufferedImage img7 = null;
		try {
			img7 = ImageIO.read(new File("imagenes\\button_bibliotecas.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth7 = 1424;
	    int labelHeight7 = 825;
	    
	    Image scaledImg7 = img7.getScaledInstance(labelWidth7, labelHeight7, Image.SCALE_SMOOTH);
		
	    ImageIcon icon7 = new ImageIcon(scaledImg7);
		
		JLabel lblNewLabel_1_5 = new JLabel("Bibliotecas");
		lblNewLabel_1_5.setBounds(1100, 257, 46, 14);
		add(lblNewLabel_1_5);
		
		BufferedImage img8 = null;
		try {
			img8 = ImageIO.read(new File("imagenes\\button_incidencias.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth8 = 1424;
	    int labelHeight8 = 825;
	    
	    Image scaledImg8 = img8.getScaledInstance(labelWidth8, labelHeight8, Image.SCALE_SMOOTH);
		
	    ImageIcon icon8 = new ImageIcon(scaledImg8);
		
		JLabel lblNewLabel_1_6 = new JLabel("Incidencias");
		lblNewLabel_1_6.setBounds(1100, 618, 46, 14);
		add(lblNewLabel_1_6);
		
		BufferedImage img9 = null;
		try {
			img9 = ImageIO.read(new File("imagenes\\button_ubicacion.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth9 = 1424;
	    int labelHeight9 = 825;
	    
	    Image scaledImg9 = img9.getScaledInstance(labelWidth9, labelHeight9, Image.SCALE_SMOOTH);
		
	    ImageIcon icon9 = new ImageIcon(scaledImg9);
		
		JLabel lblNewLabel_1_7 = new JLabel("Ubicacion");
		lblNewLabel_1_7.setBounds(1100, 428, 46, 14);
		add(lblNewLabel_1_7);

	}
}
