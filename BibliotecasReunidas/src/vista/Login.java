package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.ConectorBBDD;
import modelo.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfCorreo;
	private JTextField tfPassword;
	static public Usuario usuario;
	public Boolean iniciadoSesion = false;

	/**
	 * Create the panel.
	 */
	public Login() {
		
		
		setLayout(null);
		
		JLabel lblLoginTitle = new JLabel("INICIO DE SESION");
		lblLoginTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLoginTitle.setBounds(444, 108, 269, 53);
		add(lblLoginTitle);
		
		JLabel lblCorreo = new JLabel("Correo electronico:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCorreo.setBounds(479, 199, 177, 25);
		add(lblCorreo);
		
		tfCorreo = new JTextField();
		tfCorreo.setBounds(479, 247, 178, 23);
		add(tfCorreo);
		tfCorreo.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(479, 340, 177, 25);
		add(tfPassword);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasena.setBounds(479, 301, 114, 25);
		add(lblContrasena);
		
		JButton btnNewButton = new JButton("Acceder");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConectorBBDD con = new ConectorBBDD();
				
				String correo = tfCorreo.getText();
				String password = tfPassword.getText();
				usuario = con.consultarUsuario(correo, password);
				
				if(usuario != null) {
					iniciadoSesion = true;
				}
				
				if(iniciadoSesion == true) {					
					Ventana_Principal.getInstance().cambiarPanel(new SocioPanel());
				}
			}
		});
		btnNewButton.setBounds(519, 409, 89, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("He olvidado mi contraseña");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblNewLabel_1.setBounds(479, 458, 191, 14);
		add(lblNewLabel_1);

	}
}
