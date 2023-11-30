package vista;

import javax.swing.JPanel;

import modelo.Usuario;
import modelo.Usuario.TIPO_PERFIL;

import java.awt.Cursor;
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
import javax.swing.JLabel;

public class SocioPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public Usuario usuario = Login.usuario;

	/**
	 * Create the panel.
	 */
	public SocioPanel() {
setLayout(null);
		
		JButton btnAgregarAdministrativo = new JButton("AgregarAdministrativo");
		btnAgregarAdministrativo.setBounds(45, 113, 139, 23);
		add(btnAgregarAdministrativo);
		
		if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRATIVO)) {
			btnAgregarAdministrativo.setEnabled(false);
		}else if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRADOR)) {
			btnAgregarAdministrativo.setEnabled(true);
		}
		
		JButton btnAgregarLibro = new JButton("Agregar libro");
		btnAgregarLibro.setBounds(268, 113, 139, 23);
		add(btnAgregarLibro);
		
		if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRATIVO)) {
			btnAgregarLibro.setEnabled(true);
		}else if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRADOR)) {
			btnAgregarLibro.setEnabled(true);
		}
		
		try {
			BufferedImage img = ImageIO.read(new File("C:\\Development\\Projects\\bibliotecas-reunidas\\BibliotecasReunidas\\src\\images\\flechita_atras.png"));
			
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
					Ventana_Principal.getInstance().cambiarPanel(new Login());
				}
			});
			imgs.setBounds(0, 0, 47, 40);
			
			add(imgs);
			
			JLabel lblCerrarSesion = new JLabel("Cerrar sesión");
			lblCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCerrarSesion.setBounds(45, 12, 110, 14);
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
					Ventana_Principal.getInstance().cambiarPanel(new Login());
				}
			});
			add(lblCerrarSesion);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
